package com.ecarx.ai.grammar.parser;

import com.ecarx.ai.grammar.criteria.*;
import com.ecarx.ai.grammar.datatype.IntegerRange;
import com.ecarx.ai.grammar.datatype.Snippet;
import com.ecarx.ai.grammar.file.*;
import com.ecarx.ai.grammar.meta.QualifiedName;
import com.ecarx.ai.grammar.structure.expr.*;
import com.ecarx.ai.gscript.CodeErrorListener;
import com.ecarx.ai.gscript.CodeManager;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 单个文件内部需要检查的错误：
 * [命名检查]
 *   1. 重复导入同一个对象                                  [done]
 *   2. 本文件定义的prime/macro/production与导入对象冲突    [done]
 *   3. 本文件定义的prime/macro/production之间命名冲突      [done]
 *   4. 导入自身所在命名空间                                [done]
 * [标注检查]
 *   5. 内置支持的annotation命名检查                        [done]
 *   6. 仅@attr / @type可以带有赋值
 *   7. @type仅能用在prime上，其只能包含min, max, name属性，且类型必须对，且1 <= min <= max
 *   8. @attr仅能用在prime和statement上，不可用在clause上面
 *   9. 同一对象上同名标注不可多次声明
 *   10.prime, macro, clause实体可以有@internal标注，表明其不能被其他namespace导入，只能在本namespace中使用
 *
 * [macro参数检查]
 *   11. macro的声明体内，需检查引用参数名字是否合法
 *
 *   TODO: 用@noskip控制prime/clause/statement的行为，有此标注的实体，在匹配时不可使用skipper
 */
public class SenseFileParser extends SenseBaseListener {
    private SenseFile senseFile;
    private int fileIndex;
    private boolean debugMode;

    private List<ImportDeclaration> imports = new LinkedList<>();
    private List<ScriptFunction> scripts = new LinkedList<>();
    private List<PrimeDeclaration> primes = new LinkedList<>();
    private List<MacroDeclaration> macros = new LinkedList<>();
    private List<ProductionDeclaration> productions = new LinkedList<>();

    private List<TargetReference> references = new LinkedList<>();

    //用于检查命名冲突。当某个名字第一次被定义时放进此map
    private Map<String,Declaration> nameMap = new HashMap<>();

    private Map<String,TerminalNode> importNameMap = new HashMap<>();

    private ParseTreeProperty<Node> nodeMap = new ParseTreeProperty<>();
    private ParseTreeProperty<Object> valueMap = new ParseTreeProperty<>();

    private ParseTreeProperty<Criterion> criterionMap = new ParseTreeProperty<>();
    private ParseTreeProperty<AttributeCriterion> attrCriterionMap = new ParseTreeProperty<>();

    //进入Macro定义体时设置为true，用于检查Macro Argument只能在Macro定义体内出现
    private boolean inMacroExpression = false;
    private Map<String,Integer> macroArgumentIndexMap = new HashMap<>();
    private Supplier<String> scriptNameGenerator;
    private Function<String, String> clauseNameGenerator;

    private CodeManager gscriptManager;

    //相同源代码的脚本片段，重用同一匿名脚本函数
    private Map<String,QualifiedName> anonymousScriptFunctionMap;

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public SenseFileParser(String name, int fileIndex, boolean debugMode, Supplier<String> scriptNameGenerator, Function<String, String> clauseNameGenerator, CodeManager gscriptManager) {
        this.senseFile = new SenseFile(name);
        this.fileIndex = fileIndex;
        this.debugMode = debugMode;
        this.gscriptManager = gscriptManager;

        this.scriptNameGenerator = scriptNameGenerator;
        this.clauseNameGenerator = clauseNameGenerator;
    }

    public SenseFile getSenseFile() {
        return senseFile;
    }

    @Override
    public void exitSense(SenseParser.SenseContext ctx) {
        senseFile.setImports(imports.toArray(new ImportDeclaration[imports.size()]));
        senseFile.setScriptFunctions(scripts.toArray(new ScriptFunction[scripts.size()]));

        macros.forEach(x -> x.setFile(senseFile));
        primes.forEach(x -> x.setFile(senseFile));
        productions.forEach(x -> x.setFile(senseFile));

        senseFile.setMacroDeclarations(macros.toArray(new MacroDeclaration[macros.size()]));
        senseFile.setPrimeDeclarations(primes.toArray(new PrimeDeclaration[primes.size()]));
        senseFile.setProductionDeclarations(productions.toArray(new ProductionDeclaration[productions.size()]));

        senseFile.setReferences(references.toArray(new TargetReference[references.size()]));

        senseFile.setLoaded(true);
    }

    /**
     * Here we override enterNsSentence() instead of exitNsSentence() so that in exitImportSentence() we know
     * the file's own namespace.
     * @param ctx
     */
    @Override
    public void enterNsSentence(SenseParser.NsSentenceContext ctx) {
        if(ctx.exception == null) {
            String namespaceName = ctx.namespace().getText();
            senseFile.setNamespaceName(namespaceName);
        } else {
            //TODO: if "namespace xxxx;" is not present, then ctx.exception is not null
        }
    }

    @Override
    public void exitImportSentence(SenseParser.ImportSentenceContext ctx) {
        String namespaceName = ctx.namespace().getText();

        if(Objects.equals(namespaceName, senseFile.getNamespaceName()))
        {
            ErrorGenerator.generateWarning(senseFile, ctx.namespace(), ErrorMessage.ERR_IMPORT_SELF_NS);
        }
        else {
            //TODO: 检查重复导入同一命名空间
            //    1. 都是全量导入的情况: 忽略新的导入语句
            //    2. 前者是全量导入，当前是显式导入: 忽略新的导入语句
            //    3. 前者是显式导入，当前是全量导入: 删除前面的显式导入，保留新的
            //    4. 两者都是显式导入，检查是否有同名的被导入对象 [后面已经通过importNameMap实现了]

            ImportDeclaration imp = new ImportDeclaration();
            imp.setContext(ctx);
            imp.setNamespaceName(namespaceName);

            if (ctx.ID() != null && !ctx.ID().isEmpty()) {
                ctx.ID().forEach(node -> {
                    TerminalNode existedNode = importNameMap.get(node.getText());

                    if (existedNode != null) {
                        //导入同名对象
                        ErrorGenerator.generateError(senseFile, node, ErrorMessage.ERR_IMPORT_NAME_CONFLICT, node.getText(), existedNode.getSymbol().getLine(), existedNode.getSymbol().getCharPositionInLine() + 1);
                    } else {
                        importNameMap.put(node.getText(), node);
                    }
                });

                imp.setNames(ctx.ID().stream().map(TerminalNode::getText).toArray(String[]::new));
            }

            imports.add(imp);
        }
    }

    @Override
    public void exitScriptSentence(SenseParser.ScriptSentenceContext ctx) {
        String name = ctx.FUNCTION().getText().substring(1);

        boolean nameConflict = false;
        for(ScriptFunction f : scripts) {
            if(f.getName().equals(name)) {
                nameConflict = true;

                ErrorGenerator.generateError(senseFile, ctx.FUNCTION(), ErrorMessage.ERR_SCRIPT_NAME_CONFLICT, name, "", ((SenseParser.ScriptSentenceContext) f.getContext()).FUNCTION().getSymbol().getLine(), ((SenseParser.ScriptSentenceContext) f.getContext()).FUNCTION().getSymbol().getCharPositionInLine() + 1);
                break;
            }
        }

        String body = ctx.SCRIPT().getText();
        body = body.substring(2, body.length() - 2);

        CodeErrorListener gscriptListener = new CodeErrorListener() {
            @Override
            public void onError(int row, int col, String filename, String msg) {
                ErrorGenerator.generateError(senseFile, ctx.SCRIPT(), ErrorMessage.ERR_SCRIPT_COMPILE, name, "(" + row + ":" + col + ") " + msg);
            }
        };

        int scriptId = gscriptManager.compile("", body, gscriptListener);

        if(!nameConflict) {
            ScriptFunction f = new ScriptFunction();
            f.setName(name);
            f.setBody(body);
            f.setFile(senseFile);
            f.setContext(ctx);
            f.setScriptTerminalNode(ctx.SCRIPT());
            f.setScriptId(scriptId);
            scripts.add(f);
        }
    }

    @Override
    public void exitPrimeSentence(SenseParser.PrimeSentenceContext ctx) {
        String name = ctx.ID().getText();

        if(nameMap.containsKey(name)) {
            reportNameConflict(ctx.ID(), nameMap.get(name));
        }
        else if(importNameMap.containsKey(name)) {
            TerminalNode existedNode = importNameMap.get(name);
            ErrorGenerator.generateError(senseFile, ctx.ID(), ErrorMessage.ERR_IMPORT_NAME_CONFLICT, name, existedNode.getSymbol().getLine(), existedNode.getSymbol().getCharPositionInLine() + 1);
        }
        else {
            PrimeDeclaration prime = new PrimeDeclaration();

            if(debugMode) {
                prime.setSnippet(createSnippet(ctx));
            }

            prime.setContext(ctx);
            prime.setName(name);

            //put into nameMap
            nameMap.put(name, prime);

            Expression expr = new Expression();
            expr.setRootNode(nodeMap.get(ctx.expr()));
            prime.setExpression(expr);

            Annotation[] annotations = getAnnotations(ctx.annotation());
            prime.setAnnotations(annotations);

            if(ctx.criteria() != null) {
                Criterion criterion = criterionMap.get(ctx.criteria());

                if(criterion != null)
                    prime.setCriterion(criterion);
            }

            if(ctx.exclusion() != null) {
                Expression exclusion = new Expression();
                exclusion.setRootNode(nodeMap.get(ctx.exclusion().expr()));
                prime.setExclusion(exclusion);
            }

            primes.add(prime);
        }
    }

    @Override
    public void enterMacroSentence(SenseParser.MacroSentenceContext ctx) {
        inMacroExpression = true;

        if (ctx.idList() != null) {
            List<TerminalNode> nodes = ctx.idList().ID();


            //检查形参列表中是否存在同名的参数
            int argCount = nodes.size();
            for(int i = 0; i < argCount; i++) {
                TerminalNode n = nodes.get(i);

                if(macroArgumentIndexMap.containsKey(n.getText())) {
                    //Error: duplicated argument name
                    ErrorGenerator.generateError(senseFile, n, ErrorMessage.ERR_MACRO_ARG_DUPLICATED, n.getText());
                } else {
                    macroArgumentIndexMap.put(n.getText(), i);
                }
            }
        }
    }

    @Override
    public void exitMacroSentence(SenseParser.MacroSentenceContext ctx) {
        inMacroExpression = false;
        macroArgumentIndexMap.clear();

        String name = ctx.ID().getText();

        if(nameMap.containsKey(name)) {
            reportNameConflict(ctx.ID(), nameMap.get(name));
        }
        else if(importNameMap.containsKey(name)) {
            TerminalNode existedNode = importNameMap.get(name);
            ErrorGenerator.generateError(senseFile, ctx.ID(), ErrorMessage.ERR_IMPORT_NAME_CONFLICT, name, existedNode.getSymbol().getLine(), existedNode.getSymbol().getCharPositionInLine() + 1);
        }
        else {
            MacroDeclaration macro = new MacroDeclaration();

            if(debugMode) {
                macro.setSnippet(createSnippet(ctx));
            }

            macro.setContext(ctx);
            macro.setName(name);

            nameMap.put(name, macro);

            if(ctx.idList() != null) {
                macro.setArgs(ctx.idList().ID().stream().map(id -> id.getText()).toArray(String[]::new));

            } else {
                macro.setArgs(new String[0]);
            }

            Expression expr = new Expression();
            expr.setRootNode(nodeMap.get(ctx.expr()));
            macro.setExpression(expr);

            Annotation[] annotations = getAnnotations(ctx.annotation());
            macro.setAnnotations(annotations);

            macros.add(macro);
        }
    }

    @Override
    public void exitProductionSentence(SenseParser.ProductionSentenceContext ctx) {
        String name = ctx.ID().getText();

        if(nameMap.containsKey(name)) {
            reportNameConflict(ctx.ID(), nameMap.get(name));
        }
        else if(importNameMap.containsKey(name)) {
            TerminalNode existedNode = importNameMap.get(name);
            ErrorGenerator.generateError(senseFile, ctx.ID(), ErrorMessage.ERR_IMPORT_NAME_CONFLICT, name, existedNode.getSymbol().getLine(), existedNode.getSymbol().getCharPositionInLine() + 1);
        }
        else {
            ProductionDeclaration prod = new ProductionDeclaration();

            if(debugMode) {
                prod.setSnippet(createSnippet(ctx));
            }

            prod.setContext(ctx);
            prod.setName(name);

            nameMap.put(name, prod);

            if (ctx.expr() != null) {
                Expression expr = new Expression();
                expr.setRootNode(nodeMap.get(ctx.expr()));
                prod.setExpression(expr);
            }

            Annotation[] annotations = getAnnotations(ctx.annotation());
            prod.setAnnotations(annotations);

            if(ctx.exclusion() != null) {
                Expression exclusion = new Expression();
                exclusion.setRootNode(nodeMap.get(ctx.exclusion().expr()));
                prod.setExclusion(exclusion);
            }

            productions.add(prod);
        }
    }

    @Override
    public void exitExprSequence(SenseParser.ExprSequenceContext ctx) {
        SequenceNode n = new SequenceNode();
        n.setNodes(ctx.repUnit().stream().map(nodeMap::get).toArray(Node[]::new));

        if(debugMode) {
            n.setSnippet(createSnippet(ctx));
        }

        nodeMap.put(ctx, n);
    }

    @Override
    public void exitExprChoice(SenseParser.ExprChoiceContext ctx) {
        ChoiceNode n = new ChoiceNode();
        n.setNodes(ctx.branch().stream().map(nodeMap::get).toArray(Node[]::new));

        if(debugMode) {
            n.setSnippet(createSnippet(ctx));
        }

        nodeMap.put(ctx, n);
    }

    @Override
    public void exitBranch(SenseParser.BranchContext ctx) {
        List<SenseParser.RepUnitContext> units = ctx.repUnit();

        if(units.size() == 1) {
            nodeMap.put(ctx, nodeMap.get(units.get(0)));
        } else {
            SequenceNode n = new SequenceNode();
            n.setNodes(units.stream().map(nodeMap::get).toArray(Node[]::new));

            if(debugMode) {
                n.setSnippet(createSnippet(ctx));
            }

            nodeMap.put(ctx, n);
        }
    }

    @Override
    public void exitExprUnit(SenseParser.ExprUnitContext ctx) {
        nodeMap.put(ctx, nodeMap.get(ctx.repUnit()));
    }

    @Override
    public void exitRepUnit(SenseParser.RepUnitContext ctx) {
        Node n = nodeMap.get(ctx.unit());

        if(ctx.rep() != null) {
            //TODO: 如果某些类型的unit不允许重复，则在此处做检查

            SenseParser.RepContext rep = ctx.rep();

            if(rep instanceof SenseParser.RepPreferContext) {
                PreferNode preferNode = new PreferNode();

                if (debugMode) {
                    preferNode.setSnippet(createSnippet(ctx));
                }

                preferNode.setNode(n);
                nodeMap.put(ctx, preferNode);
            } else {
                RepeatNode repNode = new RepeatNode();

                if (debugMode) {
                    repNode.setSnippet(createSnippet(ctx));
                }

                repNode.setNode(n);

                if (rep instanceof SenseParser.RepZeroOrMoreContext) {
                    repNode.setRange(IntegerRange.lower(0));
                } else if (rep instanceof SenseParser.RepOneOrMoreContext) {
                    repNode.setRange(IntegerRange.lower(1));
                } else if (rep instanceof SenseParser.RepZeroOrOneContext) {
                    repNode.setRange(IntegerRange.lowerAndUpper(0, 1));
                } else if (rep instanceof SenseParser.RepRangeContext) {
                    SenseParser.RepRangeContext rangeRep = (SenseParser.RepRangeContext) rep;

                    int min = 0;
                    int max = Integer.MAX_VALUE;

                    if (rangeRep.minCount != null) {
                        try {
                            min = Integer.parseInt(rangeRep.minCount.getText());

                            if (min < 0) {
                                ErrorGenerator.generateError(senseFile, rangeRep.minCount, ErrorMessage.ERR_LOWERBOUND);
                            }
                        } catch (NumberFormatException e) {
                            ErrorGenerator.generateError(senseFile, rangeRep.minCount, ErrorMessage.ERR_INT_FORMAT);
                        }
                    }

                    if (rangeRep.maxCount != null) {
                        try {
                            max = Integer.parseInt(rangeRep.maxCount.getText());

                            if (max <= 0) {
                                ErrorGenerator.generateError(senseFile, rangeRep.maxCount, ErrorMessage.ERR_UPPERBOUND);
                            }
                            if (max < min) {
                                ErrorGenerator.generateError(senseFile, rangeRep.maxCount, ErrorMessage.ERR_UPPER_GE_LOWER);
                            }
                        } catch (NumberFormatException e) {
                            ErrorGenerator.generateError(senseFile, rangeRep.maxCount, ErrorMessage.ERR_INT_FORMAT);
                        }
                    } else {
                        // if max is missing, then just let max = min
                        max = min;
                    }
                    repNode.setRange(IntegerRange.lowerAndUpper(min, max));
                }

                nodeMap.put(ctx, repNode);
            }
        } else {
            nodeMap.put(ctx, n);
        }
    }

    @Override
    public void exitUnitLiteral(SenseParser.UnitLiteralContext ctx) {
        boolean continuous = ctx.STRING().getText().startsWith("\'");
        String literal = stripStringOrRegex(ctx.STRING().getText(), true);

        if(literal.length() == 0) {
            //Literal string can't be zero-length
            ErrorGenerator.generateError(senseFile, ctx.STRING(), ErrorMessage.ERR_LITERAL_EMPTY);
        }

        LiteralNode n = new LiteralNode();

        if(debugMode) {
            n.setSnippet(createSnippet(ctx));
        }

        n.setText(literal);
        n.setContinuous(continuous);
        nodeMap.put(ctx, n);
    }

    @Override
    public void exitUnitTags(SenseParser.UnitTagsContext ctx) {
        TaggingNode n = new TaggingNode();

        if(debugMode) {
            n.setSnippet(createSnippet(ctx));
        }

        n.setTagNames(ctx.ID().stream().map(ParseTree::getText).distinct().toArray(String[]::new));

        nodeMap.put(ctx, n);
    }

    @Override
    public void exitUnitLookaheadLiteral(SenseParser.UnitLookaheadLiteralContext ctx) {
        String literal = stripStringOrRegex(ctx.STRING().getText(), true);

        if(literal.length() == 0) {
            //Literal string can't be zero-length
            ErrorGenerator.generateError(senseFile, ctx.STRING(), ErrorMessage.ERR_LITERAL_EMPTY);
        }

        String opText = ctx.op.getText();

        LiteralLookaheadNode n = new LiteralLookaheadNode();

        if(debugMode) {
            n.setSnippet(createSnippet(ctx));
        }

        n.setEquals(opText.equals("=="));
        n.setText(literal);
        nodeMap.put(ctx, n);
    }

    @Override
    public void exitUnitCharList(SenseParser.UnitCharListContext ctx) {
        String text = stripStringOrRegex(ctx.STRING().getText(), true);

        if(text.length() == 0) {
            //Literal string can't be zero-length
            ErrorGenerator.generateError(senseFile, ctx.STRING(), ErrorMessage.ERR_LITERAL_EMPTY);
        }

        CharListNode n = new CharListNode();

        if(debugMode) {
            n.setSnippet(createSnippet(ctx));
        }

        n.setChars(text);
        nodeMap.put(ctx, n);
    }

    @Override
    public void exitUnitAnyChar(SenseParser.UnitAnyCharContext ctx) {
        AnyCharNode n = new AnyCharNode();

        if(debugMode) {
            n.setSnippet(createSnippet(ctx));
        }

        nodeMap.put(ctx, n);
    }

    private TargetReference createReference(SenseParser.QualifiedNameContext ctx, TargetReference.Type type, Node refNode) {
        TargetReference ref = new TargetReference();
        ref.setType(type);
        ref.setNameNode(ctx.ID());

        if(ctx.namespace() != null) {
            if(ctx.namespace().ID() != null)
                ref.setNamespaceNode(ctx.namespace().ID());
            else
                ref.setNamespaceNode(ctx.namespace().NAMESPACE());
        }

        ref.setRefNode(refNode);

        references.add(ref);

        return ref;
    }

    @Override
    public void exitUnitReference(SenseParser.UnitReferenceContext ctx) {
        ReferenceNode n = new ReferenceNode();

        if(debugMode) {
            n.setSnippet(createSnippet(ctx));
        }

        n.setClauseName(getQualifiedName(ctx.qualifiedName()));

        if(ctx.alias != null) {
            n.setAlias(ctx.alias.getText());
        }

        createReference(ctx.qualifiedName(), TargetReference.Type.CLAUSE, n);

        nodeMap.put(ctx, n);
    }

    @Override
    public void exitUnitMacro(SenseParser.UnitMacroContext ctx) {
        MacroNode n = new MacroNode();

        if(debugMode) {
            n.setSnippet(createSnippet(ctx));
        }

        n.setMacroName(getQualifiedName(ctx.qualifiedName()));
        n.setArguments(ctx.expr().stream().map(nodeMap::get).toArray(Node[]::new));

        if(n.getArguments().length > 0) {
            Node[] args = n.getArguments();

            List<Node> replacedArgs = new ArrayList<>(args.length);

            for(Node arg : args) {
                if(!(arg instanceof ContainerNode || arg instanceof MacroNode || arg instanceof RepeatNode || arg instanceof PreferNode) || arg.hasNoSkipOrMacroArgument()) {
                    replacedArgs.add(arg);
                } else {
                    String name = clauseNameGenerator.apply(senseFile.getNamespaceName());

                    ProductionDeclaration prod = new ProductionDeclaration();

                    if (debugMode) {
                        prod.setSnippet(arg.getSnippet());
                    }

                    prod.setContext(ctx);
                    prod.setName(name);
                    prod.setAnonymous(true);

                    nameMap.put(name, prod);

                    Expression expr = new Expression();
                    expr.setRootNode(arg);
                    prod.setExpression(expr);

                    productions.add(prod);

                    ReferenceNode refNode = new ReferenceNode();
                    refNode.setClauseName(new QualifiedName(senseFile.getNamespaceName(), name));
                    if(debugMode) {
                        refNode.setSnippet(arg.getSnippet());
                    }
                    replacedArgs.add(refNode);
                }
            }
            n.setArguments(replacedArgs.toArray(new Node[args.length]));
        }

        createReference(ctx.qualifiedName(), TargetReference.Type.MACRO, n);
        nodeMap.put(ctx, n);
    }



    @Override
    public void exitUnitPrime(SenseParser.UnitPrimeContext ctx) {
        PrimeNode n = new PrimeNode();
        n.setPrimeName(getQualifiedName(ctx.qualifiedName()));

        if(debugMode) {
            n.setSnippet(createSnippet(ctx));
        }


        if(ctx.alias != null) {
            n.setAlias(ctx.alias.getText());
        }

        createReference(ctx.qualifiedName(), TargetReference.Type.PRIME, n);

        nodeMap.put(ctx, n);
    }

    @Override
    public void exitUnitPrimeAssign(SenseParser.UnitPrimeAssignContext ctx) {
        Map<String,Object> attributes = getAttributes(ctx.assigns());

        String attrJson = null;
        try {
            attrJson = mapper.writeValueAsString(attributes);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        PrimeAssignNode n = new PrimeAssignNode();

        if(debugMode) {
            n.setSnippet(createSnippet(ctx));
        }

        if(ctx.qualifiedName() != null) {
            n.setPrimeName(getQualifiedName(ctx.qualifiedName()));
        }
        n.setAttributes(attributes);
        n.setJsonAttr(attrJson);

        if(ctx.qualifiedName() != null) {
            createReference(ctx.qualifiedName(), TargetReference.Type.PRIME, n);
        }
        nodeMap.put(ctx, n);
    }

    @Override
    public void exitUnitNoskip(SenseParser.UnitNoskipContext ctx) {
        NoSkipNode n = new NoSkipNode();

        if(debugMode) {
            n.setSnippet(createSnippet(ctx));
        }

        nodeMap.put(ctx, n);
    }

    @Override
    public void exitUnitStatementAssign(SenseParser.UnitStatementAssignContext ctx) {
        Map<String,Object> attributes = getAttributes(ctx.assigns());

        String attrJson = null;
        try {
            attrJson = mapper.writeValueAsString(attributes);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        StatementAssignNode n = new StatementAssignNode();
        n.setAttributes(attributes);
        n.setJsonAttr(attrJson);

        if(debugMode) {
            n.setSnippet(createSnippet(ctx));
        }

        nodeMap.put(ctx, n);
    }

    @Override
    public void exitUnitParenthesis(SenseParser.UnitParenthesisContext ctx) {
        nodeMap.put(ctx, nodeMap.get(ctx.expr()));
    }

    @Override
    public void exitUnitMacroArg(SenseParser.UnitMacroArgContext ctx) {
        if(!inMacroExpression) {
            ErrorGenerator.generateError(senseFile, ctx.MACROARG(), ErrorMessage.ERR_MACRO_ARG_OUT_OF_CONTEXT);
        }

        //检查参数名称与形参名称是否匹配
        String argName = ctx.MACROARG().getText().substring(1);
        Integer argIndex = macroArgumentIndexMap.get(argName);
        if(argIndex == null)
        {
            //Error: no such argument
            ErrorGenerator.generateError(senseFile, ctx.MACROARG(), ErrorMessage.ERR_MACRO_ARG_NOT_EXISTS, argName);
        }

        MacroArgumentNode n = new MacroArgumentNode();
        n.setArgName(argName);

        if(debugMode) {
            n.setSnippet(createSnippet(ctx));
        }

        if(argIndex != null) {
            n.setArgIndex(argIndex);
        }

        nodeMap.put(ctx, n);
    }

    @Override
    public void exitUnitRegex(SenseParser.UnitRegexContext ctx) {
        String regex = stripStringOrRegex(ctx.REGEX().getText(), false);

        if(regex.length() == 0) {
            //regex string can't be zero-length
            ErrorGenerator.generateError(senseFile, ctx.REGEX(), ErrorMessage.ERR_REGEX_EMPTY);
        }

        try {
            Pattern.compile(regex);
        } catch(Exception e) {
            ErrorGenerator.generateError(senseFile, ctx.REGEX(), ErrorMessage.ERR_REGEX_INVALID);
        }

        RegexNode n = new RegexNode();

        if(debugMode) {
            n.setSnippet(createSnippet(ctx));
        }

        n.setRegex(regex);
        nodeMap.put(ctx, n);

        super.exitUnitRegex(ctx);
    }

    @Override
    public void exitUnitScriptFunction(SenseParser.UnitScriptFunctionContext ctx) {
        ScriptFunctionNode n = new ScriptFunctionNode();
        n.setFunctionName(new QualifiedName(senseFile.getNamespaceName(), ctx.FUNCTION().getText().substring(1)));

        if(debugMode) {
            n.setSnippet(createSnippet(ctx));
        }


        nodeMap.put(ctx, n);
    }

    @Override
    public void exitUnitScript(SenseParser.UnitScriptContext ctx) {
        String body = ctx.SCRIPT().getText();
        body = body.substring(2, body.length() - 2).trim();

        QualifiedName qName = null;

        if(anonymousScriptFunctionMap != null && anonymousScriptFunctionMap.containsKey(body))
        {
            qName = anonymousScriptFunctionMap.get(body);
        } else {

            String name = scriptNameGenerator.get();

            CodeErrorListener gscriptListener = (row, col, filename, msg) -> ErrorGenerator.generateError(senseFile, ctx.SCRIPT(), ErrorMessage.ERR_SCRIPT_COMPILE, name, "(" + row + ":" + col + ") " + msg);

            int scriptId = gscriptManager.compile(name, body, gscriptListener);

            ScriptFunction f = new ScriptFunction();
            f.setName(name);
            f.setBody(body);
            f.setFile(senseFile);
            f.setScriptTerminalNode(ctx.SCRIPT());
            f.setScriptId(scriptId);
            scripts.add(f);

            qName = new QualifiedName(senseFile.getNamespaceName(), name);

            if(anonymousScriptFunctionMap == null)
                anonymousScriptFunctionMap = new HashMap<>();

            anonymousScriptFunctionMap.put(body, qName);
        }

        ScriptFunctionNode n = new ScriptFunctionNode();

        if(debugMode) {
            n.setSnippet(createSnippet(ctx));
        }

        n.setFunctionName(qName);
        nodeMap.put(ctx, n);
    }

    @Override
    public void exitValue(SenseParser.ValueContext ctx) {
        if(ctx.INT() != null) {
            try {
                int value = Integer.parseInt(ctx.INT().getText());
                valueMap.put(ctx, value);
            } catch(NumberFormatException e) {
                ErrorGenerator.generateError(senseFile, ctx.INT(), ErrorMessage.ERR_INT_FORMAT);
            }
        }
        else if(ctx.FLOAT() != null) {
            try {
                float value = Float.parseFloat(ctx.FLOAT().getText());
                valueMap.put(ctx, value);
            } catch(NumberFormatException e) {
                ErrorGenerator.generateError(senseFile, ctx.INT(), ErrorMessage.ERR_INT_FORMAT);
            }
        }
        else if(ctx.BOOLEAN() != null) {
            boolean value = Boolean.parseBoolean(ctx.BOOLEAN().getText());
            valueMap.put(ctx, value);
        }
        else if(ctx.STRING() != null) {
            String value = stripStringOrRegex(ctx.STRING().getText(), true);
            valueMap.put(ctx, value);
        }
        else if(ctx.array() != null) {
            valueMap.put(ctx, ctx.array().value().stream().map(c -> valueMap.get(c)).toArray(Object[]::new));
        }
        else if(ctx.dict() != null) {
            Map<String, Object> dict = new HashMap<>();

            ctx.dict().dictItem().forEach(c -> {
                String key = c.key.getText();
                if(key.startsWith("\"") && key.endsWith("\"")) {
                    key = stripStringOrRegex(key, true);
                }
                if (dict.get(key) != null) {
                    //Error: duplicated key
                    ErrorGenerator.generateError(senseFile, c.key, ErrorMessage.ERR_DUPLICATED_KEY);
                }

                dict.put(key, valueMap.get(c.value()));
            });
            valueMap.put(ctx, dict);
        }
    }

    @Override
    public void exitCriteria(SenseParser.CriteriaContext ctx) {
        criterionMap.put(ctx, criterionMap.get(ctx.catExpr()));
    }

    @Override
    public void exitCat(SenseParser.CatContext ctx) {
        String catName = ctx.catName.getText();

        CategoryCriterion categoryCriterion = new CategoryCriterion();

        if(catName.startsWith("$")) {
            categoryCriterion.setCategory(catName.substring(1));
            categoryCriterion.setDynamic(true);
        } else {
            categoryCriterion.setCategory(catName);
        }
        if(ctx.wordAttrExpr() != null) {
            AttributeCriterion attrCriterion = attrCriterionMap.get(ctx.wordAttrExpr());
            categoryCriterion.setAttributeCriterion(attrCriterion);
        }

        criterionMap.put(ctx, categoryCriterion);
    }

    @Override
    public void exitCatUnit(SenseParser.CatUnitContext ctx) {
        if(ctx.cat() != null) {
            criterionMap.put(ctx, criterionMap.get(ctx.cat()));
        } else if(ctx.not != null && ctx.catUnit() != null) {
            NotCriterion notCriterion = new NotCriterion();
            notCriterion.setCriterion(criterionMap.get(ctx.catUnit()));
            criterionMap.put(ctx, notCriterion);
        } else if(ctx.catExpr() != null) {
            criterionMap.put(ctx, criterionMap.get(ctx.catExpr()));
        }
    }

    @Override
    public void exitCatBranch(SenseParser.CatBranchContext ctx) {
        List<SenseParser.CatUnitContext> catBranches =  ctx.catUnit();

        if(catBranches.size() == 1) {
            criterionMap.put(ctx, criterionMap.get(catBranches.get(0)));
        } else {
            AndCriterion andCriterion = new AndCriterion();
            andCriterion.setCriteria(catBranches.stream().map(criterionMap::get).toArray(Criterion[]::new));
            criterionMap.put(ctx, andCriterion);
        }
    }

    @Override
    public void exitCatExprAnd(SenseParser.CatExprAndContext ctx) {
        AndCriterion andCriterion = new AndCriterion();
        andCriterion.setCriteria(ctx.catUnit().stream().map(criterionMap::get).toArray(Criterion[]::new));
        criterionMap.put(ctx, andCriterion);
    }

    @Override
    public void exitCatExprOr(SenseParser.CatExprOrContext ctx) {
        OrCriterion orCriterion = new OrCriterion();
        orCriterion.setCriteria(ctx.catBranch().stream().map(criterionMap::get).toArray(Criterion[]::new));
        criterionMap.put(ctx, orCriterion);
    }

    @Override
    public void exitCatExprUnit(SenseParser.CatExprUnitContext ctx) {
        criterionMap.put(ctx, criterionMap.get(ctx.catUnit()));
    }

    @Override
    public void exitWordAttrExprAnd(SenseParser.WordAttrExprAndContext ctx) {
        AttributeAndCriterion andCriterion = new AttributeAndCriterion();
        andCriterion.setCriteria(ctx.wordAttrUnit().stream().map(attrCriterionMap::get).toArray(AttributeCriterion[]::new));
        attrCriterionMap.put(ctx, andCriterion);
    }

    @Override
    public void exitWordAttrExprOr(SenseParser.WordAttrExprOrContext ctx) {
        AttributeOrCriterion orCriterion = new AttributeOrCriterion();
        orCriterion.setCriteria(ctx.wordAttrBranch().stream().map(attrCriterionMap::get).toArray(AttributeCriterion[]::new));
        attrCriterionMap.put(ctx, orCriterion);
    }

    @Override
    public void exitWordAttrExprUnit(SenseParser.WordAttrExprUnitContext ctx) {
        attrCriterionMap.put(ctx, attrCriterionMap.get(ctx.wordAttrUnit()));
    }

    @Override
    public void exitWordAttrBranch(SenseParser.WordAttrBranchContext ctx) {
        List<SenseParser.WordAttrUnitContext> branches = ctx.wordAttrUnit();

        if(branches.size() == 1) {
            attrCriterionMap.put(ctx, attrCriterionMap.get(branches.get(0)));
        } else {
            AttributeAndCriterion andCriterion = new AttributeAndCriterion();
            andCriterion.setCriteria(branches.stream().map(attrCriterionMap::get).toArray(AttributeCriterion[]::new));
            attrCriterionMap.put(ctx, andCriterion);
        }
    }

    @Override
    public void exitWordAttrUnit(SenseParser.WordAttrUnitContext ctx) {
        if(ctx.wordAttr() != null) {
            attrCriterionMap.put(ctx, attrCriterionMap.get(ctx.wordAttr()));
        } else if(ctx.not != null && ctx.wordAttrUnit() != null) {
            AttributeNotCriterion notCriterion = new AttributeNotCriterion();
            notCriterion.setCriterion(attrCriterionMap.get(ctx.wordAttrUnit()));
            attrCriterionMap.put(ctx, notCriterion);
        } else if(ctx.wordAttrExpr() != null) {
            attrCriterionMap.put(ctx, attrCriterionMap.get(ctx.wordAttrExpr()));
        }
    }

    @Override
    public void exitWordAttr(SenseParser.WordAttrContext ctx) {
        String attrName = ctx.attrName.getText();

        if(ctx.attrValue == null) {
            //no value, so it is a flag
            AttributeFlagCriterion flagCriterion = new AttributeFlagCriterion();

            if(attrName.startsWith("$")) {
                flagCriterion.setFlag(attrName.substring(1));
                flagCriterion.setDynamic(true);
            } else {
                flagCriterion.setFlag(attrName);
            }
            attrCriterionMap.put(ctx, flagCriterion);
        } else {
            AttributeVariableCriterion variableCriterion = new AttributeVariableCriterion();

            if(attrName.startsWith("$")) {
                variableCriterion.setName(attrName.substring(1));
                variableCriterion.setDynamicName(true);
            } else {
                variableCriterion.setName(attrName);
            }

            if(ctx.attrValue == null) {
                variableCriterion.setValue("*");
            } else {
                String attrValue = ctx.attrValue.getText();
                if(attrValue.startsWith("$")) {
                    variableCriterion.setValue(attrValue.substring(1));
                    variableCriterion.setDynamicValue(true);
                } else {
                    variableCriterion.setValue(stripStringOrRegex(ctx.attrValue.getText(), true));
                }
            }

            attrCriterionMap.put(ctx, variableCriterion);
        }
    }

    private String stripStringOrRegex(String quotedString, boolean isString) {
        String value = quotedString.substring(1, quotedString.length() - 1);
        if (isString && value.contains("\\")) {

            // 这里需要用到 "负向零宽断言", 是为了防止将 "\\n" 转换为 ("\" + 换行)
            value = value.replaceAll("\\\\\"", "\"");
            value = value.replaceAll("\\\\\'", "\'");
            value = value.replaceAll("(?<![\\\\])\\\\r", "\r");
            value = value.replaceAll("(?<![\\\\])\\\\n", "\n");
            value = value.replaceAll("\\\\\\\\", "\\\\");
        }
        return value;
    }

    private QualifiedName getQualifiedName(SenseParser.QualifiedNameContext ctx) {
        String ns = ctx.namespace() == null ? null : ctx.namespace().getText();
        String name = ctx.ID().getText();

        QualifiedName qName = new QualifiedName();
        qName.setNamespaceName(ns);
        qName.setName(name);
        return qName;
    }

    private Annotation[] getAnnotations(List<SenseParser.AnnotationContext> annotations) {
        if(annotations == null)
            return null;

        annotations.stream()
                .filter(a -> !AnnotationUtils.isValid(a.ID().getText()))
                .forEach(a -> {
                    ErrorGenerator.generateError(senseFile, a.ID(), ErrorMessage.ERR_INVALID_ANNOTATION, a.ID().getText());
                });

        return annotations.stream().map(actx -> {
                        Annotation annotation = new Annotation();
                        annotation.setName(actx.ID().getText());

                        Map<String,Object> attributes = getAttributes(actx.assigns());

                        if(attributes != null)
                            annotation.setAttributes(attributes);

                        return annotation;
                    }).toArray(Annotation[]::new);
    }

    private Map<String,Object> getAttributes(SenseParser.AssignsContext ctx) {
        if (ctx != null) {
            Map<String,Object> attributes = new HashMap<>();

            ctx.assign().forEach(a -> {
                String key = a.ID().getText();
                if (attributes.get(key) != null) {
                    //Error: duplicated key
                    ErrorGenerator.generateError(senseFile, a, ErrorMessage.ERR_DUPLICATED_KEY);
                }
                attributes.put(key, valueMap.get(a.value()));
            });

            return attributes;
        }
        return null;
    }

    private void reportNameConflict(TerminalNode idNode, Declaration declaration)
    {
        ErrorGenerator.generateError(senseFile, idNode, ErrorMessage.ERR_DECLARE_NAME_CONFLICT, idNode.getText(), "", declaration.getNameToken().getLine(), declaration.getNameToken().getCharPositionInLine() + 1);
    }

    private Snippet createSnippet(ParserRuleContext ctx) {
        int rowStart = ctx.getStart().getLine();
        int colStart = ctx.getStart().getCharPositionInLine() + 1;

        int rowStop = ctx.getStop().getLine();
        int colStop = ctx.getStop().getCharPositionInLine() + ctx.getStop().getText().length() + 1;

        Snippet snippet = new Snippet();
        snippet.setFileIndex(fileIndex);
        snippet.setColBegin(colStart);
        snippet.setColEnd(colStop);
        snippet.setRowBegin(rowStart);
        snippet.setRowEnd(rowStop);

        return snippet;
    }
}
