package com.grammar.compiler;

import com.grammar.antlr.*;
import com.grammar.fsm.*;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * ANTLR listener for parsing Sense grammar and building FSM components
 * Based on the pattern from SenseFileParser.java
 */
public class SenseGrammarListener extends SenseBaseListener {
    
    // Grammar components
    private String namespaceName;
    private List<ImportDeclaration> imports = new ArrayList<>();
    private List<PrimeDeclaration> primes = new ArrayList<>();
    private List<ProductionDeclaration> productions = new ArrayList<>();
    private List<StatementDeclaration> statements = new ArrayList<>();
    
    // Parse tree properties for building expressions
    private ParseTreeProperty<ExpressionNode> nodeMap = new ParseTreeProperty<>();
    private ParseTreeProperty<Object> valueMap = new ParseTreeProperty<>();
    
    // Name conflict checking
    private Map<String, Declaration> nameMap = new HashMap<>();
    
    @Override
    public void enterNsSentence(SenseParser.NsSentenceContext ctx) {
        if (ctx.exception == null) {
            namespaceName = ctx.namespace().getText();
        }
    }
    
    @Override
    public void exitImportSentence(SenseParser.ImportSentenceContext ctx) {
        ImportDeclaration imp = new ImportDeclaration();
        imp.namespaceName = ctx.namespace().getText();
        
        if (ctx.ID() != null && !ctx.ID().isEmpty()) {
            imp.names = ctx.ID().stream()
                .map(TerminalNode::getText)
                .toArray(String[]::new);
        }
        
        imports.add(imp);
    }
    
    @Override
    public void exitPrimeSentence(SenseParser.PrimeSentenceContext ctx) {
        String name = ctx.name.getText();
        
        if (!nameMap.containsKey(name)) {
            PrimeDeclaration prime = new PrimeDeclaration();
            prime.name = name;
            prime.expression = nodeMap.get(ctx.expr());
            prime.annotations = extractAnnotations(ctx.annotation());
            
            nameMap.put(name, prime);
            primes.add(prime);
        }
    }
    
    @Override
    public void exitProductionSentence(SenseParser.ProductionSentenceContext ctx) {
        String name = ctx.name.getText();
        
        if (!nameMap.containsKey(name)) {
            ProductionDeclaration prod = new ProductionDeclaration();
            prod.name = name;
            if (ctx.expr() != null) {
                prod.expression = nodeMap.get(ctx.expr());
            }
            prod.annotations = extractAnnotations(ctx.annotation());
            
            // Check if this is a statement (has @statement annotation)
            if (hasStatementAnnotation(prod.annotations)) {
                StatementDeclaration stmt = new StatementDeclaration();
                stmt.name = name;
                stmt.expression = prod.expression;
                stmt.annotations = prod.annotations;
                stmt.intentName = extractIntentName(prod.annotations);
                statements.add(stmt);
            } else {
                nameMap.put(name, prod);
                productions.add(prod);
            }
        }
    }
    
    @Override
    public void exitExprSequence(SenseParser.ExprSequenceContext ctx) {
        SequenceExpressionNode node = new SequenceExpressionNode();
        node.children = ctx.repUnit().stream()
            .map(nodeMap::get)
            .toArray(ExpressionNode[]::new);
        nodeMap.put(ctx, node);
    }
    
    @Override
    public void exitExprChoice(SenseParser.ExprChoiceContext ctx) {
        ChoiceExpressionNode node = new ChoiceExpressionNode();
        node.children = ctx.branch().stream()
            .map(nodeMap::get)
            .toArray(ExpressionNode[]::new);
        nodeMap.put(ctx, node);
    }
    
    @Override
    public void exitExprUnit(SenseParser.ExprUnitContext ctx) {
        nodeMap.put(ctx, nodeMap.get(ctx.repUnit()));
    }
    
    @Override
    public void exitBranch(SenseParser.BranchContext ctx) {
        List<SenseParser.RepUnitContext> units = ctx.repUnit();
        
        if (units.size() == 1) {
            nodeMap.put(ctx, nodeMap.get(units.get(0)));
        } else {
            SequenceExpressionNode node = new SequenceExpressionNode();
            node.children = units.stream()
                .map(nodeMap::get)
                .toArray(ExpressionNode[]::new);
            nodeMap.put(ctx, node);
        }
    }
    
    @Override
    public void exitRepUnit(SenseParser.RepUnitContext ctx) {
        ExpressionNode baseNode = nodeMap.get(ctx.unit());
        
        if (ctx.rep() != null) {
            RepeatExpressionNode repeatNode = new RepeatExpressionNode();
            repeatNode.child = baseNode;
            
            SenseParser.RepContext rep = ctx.rep();
            if (rep instanceof SenseParser.RepZeroOrMoreContext) {
                repeatNode.minCount = 0;
                repeatNode.maxCount = Integer.MAX_VALUE;
            } else if (rep instanceof SenseParser.RepOneOrMoreContext) {
                repeatNode.minCount = 1;
                repeatNode.maxCount = Integer.MAX_VALUE;
            } else if (rep instanceof SenseParser.RepZeroOrOneContext) {
                repeatNode.minCount = 0;
                repeatNode.maxCount = 1;
            }
            
            nodeMap.put(ctx, repeatNode);
        } else {
            nodeMap.put(ctx, baseNode);
        }
    }
    
    @Override
    public void exitUnitLiteral(SenseParser.UnitLiteralContext ctx) {
        LiteralExpressionNode node = new LiteralExpressionNode();
        node.text = stripQuotes(ctx.STRING().getText());
        nodeMap.put(ctx, node);
    }
    
    @Override
    public void exitUnitPrime(SenseParser.UnitPrimeContext ctx) {
        PrimeExpressionNode node = new PrimeExpressionNode();
        node.primeName = getQualifiedName(ctx.qualifiedName());
        if (ctx.alias != null) {
            node.alias = ctx.alias.getText();
        }
        nodeMap.put(ctx, node);
    }
    
    @Override
    public void exitUnitReference(SenseParser.UnitReferenceContext ctx) {
        ReferenceExpressionNode node = new ReferenceExpressionNode();
        node.referenceName = getQualifiedName(ctx.qualifiedName());
        if (ctx.alias != null) {
            node.alias = ctx.alias.getText();
        }
        nodeMap.put(ctx, node);
    }
    
    @Override
    public void exitUnitParenthesis(SenseParser.UnitParenthesisContext ctx) {
        nodeMap.put(ctx, nodeMap.get(ctx.expr()));
    }
    
    @Override
    public void exitValue(SenseParser.ValueContext ctx) {
        if (ctx.INT() != null) {
            try {
                valueMap.put(ctx, Integer.parseInt(ctx.INT().getText()));
            } catch (NumberFormatException e) {
                // Handle error
            }
        } else if (ctx.STRING() != null) {
            valueMap.put(ctx, stripQuotes(ctx.STRING().getText()));
        } else if (ctx.BOOLEAN() != null) {
            valueMap.put(ctx, Boolean.parseBoolean(ctx.BOOLEAN().getText()));
        }
    }
    
    // Helper methods
    private String stripQuotes(String quotedString) {
        if (quotedString.length() >= 2) {
            return quotedString.substring(1, quotedString.length() - 1);
        }
        return quotedString;
    }
    
    private QualifiedName getQualifiedName(SenseParser.QualifiedNameContext ctx) {
        String ns = ctx.namespace() == null ? namespaceName : ctx.namespace().getText();
        String name = ctx.ID().getText();
        return new QualifiedName(ns, name);
    }
    
    private AnnotationInfo[] extractAnnotations(List<SenseParser.AnnotationContext> annotations) {
        if (annotations == null || annotations.isEmpty()) {
            return new AnnotationInfo[0];
        }
        
        return annotations.stream().map(ctx -> {
            AnnotationInfo ann = new AnnotationInfo();
            ann.name = ctx.ID().getText();
            
            if (ctx.assigns() != null) {
                ann.attributes = new HashMap<>();
                ctx.assigns().assign().forEach(assign -> {
                    String key = assign.ID().getText();
                    Object value = valueMap.get(assign.value());
                    ann.attributes.put(key, value);
                });
            }
            
            return ann;
        }).toArray(AnnotationInfo[]::new);
    }
    
    private boolean hasStatementAnnotation(AnnotationInfo[] annotations) {
        if (annotations == null) return false;
        
        for (AnnotationInfo ann : annotations) {
            if ("statement".equals(ann.name)) {
                return true;
            }
        }
        return false;
    }
    
    private String extractIntentName(AnnotationInfo[] annotations) {
        if (annotations == null) return "unknown";
        
        for (AnnotationInfo ann : annotations) {
            if ("attr".equals(ann.name) && ann.attributes != null) {
                Object intention = ann.attributes.get("intention");
                if (intention instanceof String) {
                    return (String) intention;
                }
            }
        }
        return "unknown";
    }
    
    // Getters
    public String getNamespaceName() { return namespaceName; }
    public List<ImportDeclaration> getImports() { return imports; }
    public List<PrimeDeclaration> getPrimes() { return primes; }
    public List<ProductionDeclaration> getProductions() { return productions; }
    public List<StatementDeclaration> getStatements() { return statements; }
    
    // Data classes
    public static class Declaration {
        public String name;
        public ExpressionNode expression;
        public AnnotationInfo[] annotations;
    }
    
    public static class ImportDeclaration {
        public String namespaceName;
        public String[] names;
    }
    
    public static class PrimeDeclaration extends Declaration {}
    public static class ProductionDeclaration extends Declaration {}
    
    public static class StatementDeclaration extends Declaration {
        public String intentName;
    }
    
    public static class AnnotationInfo {
        public String name;
        public Map<String, Object> attributes;
    }
    
    public static class QualifiedName {
        public String namespaceName;
        public String name;
        
        public QualifiedName(String namespaceName, String name) {
            this.namespaceName = namespaceName;
            this.name = name;
        }
        
        @Override
        public String toString() {
            return namespaceName != null ? namespaceName + "::" + name : name;
        }
    }
    
    // Expression node types
    public static abstract class ExpressionNode {}
    
    public static class LiteralExpressionNode extends ExpressionNode {
        public String text;
    }
    
    public static class PrimeExpressionNode extends ExpressionNode {
        public QualifiedName primeName;
        public String alias;
    }
    
    public static class ReferenceExpressionNode extends ExpressionNode {
        public QualifiedName referenceName;
        public String alias;
    }
    
    public static class SequenceExpressionNode extends ExpressionNode {
        public ExpressionNode[] children;
    }
    
    public static class ChoiceExpressionNode extends ExpressionNode {
        public ExpressionNode[] children;
    }
    
    public static class RepeatExpressionNode extends ExpressionNode {
        public ExpressionNode child;
        public int minCount = 1;
        public int maxCount = 1;
    }
}