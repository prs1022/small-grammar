// Generated from Sense.g4 by ANTLR 4.13.1

package com.grammar.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SenseParser}.
 */
public interface SenseListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SenseParser#sense}.
	 * @param ctx the parse tree
	 */
	void enterSense(SenseParser.SenseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SenseParser#sense}.
	 * @param ctx the parse tree
	 */
	void exitSense(SenseParser.SenseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SenseParser#nsSentence}.
	 * @param ctx the parse tree
	 */
	void enterNsSentence(SenseParser.NsSentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link SenseParser#nsSentence}.
	 * @param ctx the parse tree
	 */
	void exitNsSentence(SenseParser.NsSentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link SenseParser#importSentence}.
	 * @param ctx the parse tree
	 */
	void enterImportSentence(SenseParser.ImportSentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link SenseParser#importSentence}.
	 * @param ctx the parse tree
	 */
	void exitImportSentence(SenseParser.ImportSentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link SenseParser#scriptSentence}.
	 * @param ctx the parse tree
	 */
	void enterScriptSentence(SenseParser.ScriptSentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link SenseParser#scriptSentence}.
	 * @param ctx the parse tree
	 */
	void exitScriptSentence(SenseParser.ScriptSentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link SenseParser#primeSentence}.
	 * @param ctx the parse tree
	 */
	void enterPrimeSentence(SenseParser.PrimeSentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link SenseParser#primeSentence}.
	 * @param ctx the parse tree
	 */
	void exitPrimeSentence(SenseParser.PrimeSentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link SenseParser#macroSentence}.
	 * @param ctx the parse tree
	 */
	void enterMacroSentence(SenseParser.MacroSentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link SenseParser#macroSentence}.
	 * @param ctx the parse tree
	 */
	void exitMacroSentence(SenseParser.MacroSentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link SenseParser#productionSentence}.
	 * @param ctx the parse tree
	 */
	void enterProductionSentence(SenseParser.ProductionSentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link SenseParser#productionSentence}.
	 * @param ctx the parse tree
	 */
	void exitProductionSentence(SenseParser.ProductionSentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link SenseParser#exclusion}.
	 * @param ctx the parse tree
	 */
	void enterExclusion(SenseParser.ExclusionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SenseParser#exclusion}.
	 * @param ctx the parse tree
	 */
	void exitExclusion(SenseParser.ExclusionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SenseParser#annotation}.
	 * @param ctx the parse tree
	 */
	void enterAnnotation(SenseParser.AnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SenseParser#annotation}.
	 * @param ctx the parse tree
	 */
	void exitAnnotation(SenseParser.AnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SenseParser#assigns}.
	 * @param ctx the parse tree
	 */
	void enterAssigns(SenseParser.AssignsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SenseParser#assigns}.
	 * @param ctx the parse tree
	 */
	void exitAssigns(SenseParser.AssignsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SenseParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(SenseParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link SenseParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(SenseParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link SenseParser#criteria}.
	 * @param ctx the parse tree
	 */
	void enterCriteria(SenseParser.CriteriaContext ctx);
	/**
	 * Exit a parse tree produced by {@link SenseParser#criteria}.
	 * @param ctx the parse tree
	 */
	void exitCriteria(SenseParser.CriteriaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code catExprAnd}
	 * labeled alternative in {@link SenseParser#catExpr}.
	 * @param ctx the parse tree
	 */
	void enterCatExprAnd(SenseParser.CatExprAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code catExprAnd}
	 * labeled alternative in {@link SenseParser#catExpr}.
	 * @param ctx the parse tree
	 */
	void exitCatExprAnd(SenseParser.CatExprAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code catExprOr}
	 * labeled alternative in {@link SenseParser#catExpr}.
	 * @param ctx the parse tree
	 */
	void enterCatExprOr(SenseParser.CatExprOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code catExprOr}
	 * labeled alternative in {@link SenseParser#catExpr}.
	 * @param ctx the parse tree
	 */
	void exitCatExprOr(SenseParser.CatExprOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code catExprUnit}
	 * labeled alternative in {@link SenseParser#catExpr}.
	 * @param ctx the parse tree
	 */
	void enterCatExprUnit(SenseParser.CatExprUnitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code catExprUnit}
	 * labeled alternative in {@link SenseParser#catExpr}.
	 * @param ctx the parse tree
	 */
	void exitCatExprUnit(SenseParser.CatExprUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link SenseParser#catUnit}.
	 * @param ctx the parse tree
	 */
	void enterCatUnit(SenseParser.CatUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link SenseParser#catUnit}.
	 * @param ctx the parse tree
	 */
	void exitCatUnit(SenseParser.CatUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link SenseParser#catBranch}.
	 * @param ctx the parse tree
	 */
	void enterCatBranch(SenseParser.CatBranchContext ctx);
	/**
	 * Exit a parse tree produced by {@link SenseParser#catBranch}.
	 * @param ctx the parse tree
	 */
	void exitCatBranch(SenseParser.CatBranchContext ctx);
	/**
	 * Enter a parse tree produced by {@link SenseParser#cat}.
	 * @param ctx the parse tree
	 */
	void enterCat(SenseParser.CatContext ctx);
	/**
	 * Exit a parse tree produced by {@link SenseParser#cat}.
	 * @param ctx the parse tree
	 */
	void exitCat(SenseParser.CatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code wordAttrExprAnd}
	 * labeled alternative in {@link SenseParser#wordAttrExpr}.
	 * @param ctx the parse tree
	 */
	void enterWordAttrExprAnd(SenseParser.WordAttrExprAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code wordAttrExprAnd}
	 * labeled alternative in {@link SenseParser#wordAttrExpr}.
	 * @param ctx the parse tree
	 */
	void exitWordAttrExprAnd(SenseParser.WordAttrExprAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code wordAttrExprOr}
	 * labeled alternative in {@link SenseParser#wordAttrExpr}.
	 * @param ctx the parse tree
	 */
	void enterWordAttrExprOr(SenseParser.WordAttrExprOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code wordAttrExprOr}
	 * labeled alternative in {@link SenseParser#wordAttrExpr}.
	 * @param ctx the parse tree
	 */
	void exitWordAttrExprOr(SenseParser.WordAttrExprOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code wordAttrExprUnit}
	 * labeled alternative in {@link SenseParser#wordAttrExpr}.
	 * @param ctx the parse tree
	 */
	void enterWordAttrExprUnit(SenseParser.WordAttrExprUnitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code wordAttrExprUnit}
	 * labeled alternative in {@link SenseParser#wordAttrExpr}.
	 * @param ctx the parse tree
	 */
	void exitWordAttrExprUnit(SenseParser.WordAttrExprUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link SenseParser#wordAttrBranch}.
	 * @param ctx the parse tree
	 */
	void enterWordAttrBranch(SenseParser.WordAttrBranchContext ctx);
	/**
	 * Exit a parse tree produced by {@link SenseParser#wordAttrBranch}.
	 * @param ctx the parse tree
	 */
	void exitWordAttrBranch(SenseParser.WordAttrBranchContext ctx);
	/**
	 * Enter a parse tree produced by {@link SenseParser#wordAttr}.
	 * @param ctx the parse tree
	 */
	void enterWordAttr(SenseParser.WordAttrContext ctx);
	/**
	 * Exit a parse tree produced by {@link SenseParser#wordAttr}.
	 * @param ctx the parse tree
	 */
	void exitWordAttr(SenseParser.WordAttrContext ctx);
	/**
	 * Enter a parse tree produced by {@link SenseParser#wordAttrUnit}.
	 * @param ctx the parse tree
	 */
	void enterWordAttrUnit(SenseParser.WordAttrUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link SenseParser#wordAttrUnit}.
	 * @param ctx the parse tree
	 */
	void exitWordAttrUnit(SenseParser.WordAttrUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link SenseParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(SenseParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SenseParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(SenseParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link SenseParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(SenseParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link SenseParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(SenseParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link SenseParser#dict}.
	 * @param ctx the parse tree
	 */
	void enterDict(SenseParser.DictContext ctx);
	/**
	 * Exit a parse tree produced by {@link SenseParser#dict}.
	 * @param ctx the parse tree
	 */
	void exitDict(SenseParser.DictContext ctx);
	/**
	 * Enter a parse tree produced by {@link SenseParser#dictItem}.
	 * @param ctx the parse tree
	 */
	void enterDictItem(SenseParser.DictItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link SenseParser#dictItem}.
	 * @param ctx the parse tree
	 */
	void exitDictItem(SenseParser.DictItemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprSequence}
	 * labeled alternative in {@link SenseParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprSequence(SenseParser.ExprSequenceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprSequence}
	 * labeled alternative in {@link SenseParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprSequence(SenseParser.ExprSequenceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprChoice}
	 * labeled alternative in {@link SenseParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprChoice(SenseParser.ExprChoiceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprChoice}
	 * labeled alternative in {@link SenseParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprChoice(SenseParser.ExprChoiceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprUnit}
	 * labeled alternative in {@link SenseParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprUnit(SenseParser.ExprUnitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprUnit}
	 * labeled alternative in {@link SenseParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprUnit(SenseParser.ExprUnitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unitLiteral}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void enterUnitLiteral(SenseParser.UnitLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unitLiteral}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void exitUnitLiteral(SenseParser.UnitLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unitCharList}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void enterUnitCharList(SenseParser.UnitCharListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unitCharList}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void exitUnitCharList(SenseParser.UnitCharListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unitLookaheadLiteral}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void enterUnitLookaheadLiteral(SenseParser.UnitLookaheadLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unitLookaheadLiteral}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void exitUnitLookaheadLiteral(SenseParser.UnitLookaheadLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unitReference}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void enterUnitReference(SenseParser.UnitReferenceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unitReference}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void exitUnitReference(SenseParser.UnitReferenceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unitMacro}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void enterUnitMacro(SenseParser.UnitMacroContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unitMacro}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void exitUnitMacro(SenseParser.UnitMacroContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unitPrime}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void enterUnitPrime(SenseParser.UnitPrimeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unitPrime}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void exitUnitPrime(SenseParser.UnitPrimeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unitPrimeAssign}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void enterUnitPrimeAssign(SenseParser.UnitPrimeAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unitPrimeAssign}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void exitUnitPrimeAssign(SenseParser.UnitPrimeAssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unitStatementAssign}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void enterUnitStatementAssign(SenseParser.UnitStatementAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unitStatementAssign}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void exitUnitStatementAssign(SenseParser.UnitStatementAssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unitParenthesis}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void enterUnitParenthesis(SenseParser.UnitParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unitParenthesis}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void exitUnitParenthesis(SenseParser.UnitParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unitAnyChar}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void enterUnitAnyChar(SenseParser.UnitAnyCharContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unitAnyChar}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void exitUnitAnyChar(SenseParser.UnitAnyCharContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unitTags}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void enterUnitTags(SenseParser.UnitTagsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unitTags}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void exitUnitTags(SenseParser.UnitTagsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unitMacroArg}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void enterUnitMacroArg(SenseParser.UnitMacroArgContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unitMacroArg}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void exitUnitMacroArg(SenseParser.UnitMacroArgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unitScriptFunction}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void enterUnitScriptFunction(SenseParser.UnitScriptFunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unitScriptFunction}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void exitUnitScriptFunction(SenseParser.UnitScriptFunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unitScript}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void enterUnitScript(SenseParser.UnitScriptContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unitScript}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void exitUnitScript(SenseParser.UnitScriptContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unitRegex}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void enterUnitRegex(SenseParser.UnitRegexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unitRegex}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void exitUnitRegex(SenseParser.UnitRegexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unitNoskip}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void enterUnitNoskip(SenseParser.UnitNoskipContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unitNoskip}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 */
	void exitUnitNoskip(SenseParser.UnitNoskipContext ctx);
	/**
	 * Enter a parse tree produced by {@link SenseParser#idList}.
	 * @param ctx the parse tree
	 */
	void enterIdList(SenseParser.IdListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SenseParser#idList}.
	 * @param ctx the parse tree
	 */
	void exitIdList(SenseParser.IdListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SenseParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedName(SenseParser.QualifiedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SenseParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedName(SenseParser.QualifiedNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SenseParser#namespace}.
	 * @param ctx the parse tree
	 */
	void enterNamespace(SenseParser.NamespaceContext ctx);
	/**
	 * Exit a parse tree produced by {@link SenseParser#namespace}.
	 * @param ctx the parse tree
	 */
	void exitNamespace(SenseParser.NamespaceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code repOneOrMore}
	 * labeled alternative in {@link SenseParser#rep}.
	 * @param ctx the parse tree
	 */
	void enterRepOneOrMore(SenseParser.RepOneOrMoreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code repOneOrMore}
	 * labeled alternative in {@link SenseParser#rep}.
	 * @param ctx the parse tree
	 */
	void exitRepOneOrMore(SenseParser.RepOneOrMoreContext ctx);
	/**
	 * Enter a parse tree produced by the {@code repZeroOrMore}
	 * labeled alternative in {@link SenseParser#rep}.
	 * @param ctx the parse tree
	 */
	void enterRepZeroOrMore(SenseParser.RepZeroOrMoreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code repZeroOrMore}
	 * labeled alternative in {@link SenseParser#rep}.
	 * @param ctx the parse tree
	 */
	void exitRepZeroOrMore(SenseParser.RepZeroOrMoreContext ctx);
	/**
	 * Enter a parse tree produced by the {@code repZeroOrOne}
	 * labeled alternative in {@link SenseParser#rep}.
	 * @param ctx the parse tree
	 */
	void enterRepZeroOrOne(SenseParser.RepZeroOrOneContext ctx);
	/**
	 * Exit a parse tree produced by the {@code repZeroOrOne}
	 * labeled alternative in {@link SenseParser#rep}.
	 * @param ctx the parse tree
	 */
	void exitRepZeroOrOne(SenseParser.RepZeroOrOneContext ctx);
	/**
	 * Enter a parse tree produced by the {@code repPrefer}
	 * labeled alternative in {@link SenseParser#rep}.
	 * @param ctx the parse tree
	 */
	void enterRepPrefer(SenseParser.RepPreferContext ctx);
	/**
	 * Exit a parse tree produced by the {@code repPrefer}
	 * labeled alternative in {@link SenseParser#rep}.
	 * @param ctx the parse tree
	 */
	void exitRepPrefer(SenseParser.RepPreferContext ctx);
	/**
	 * Enter a parse tree produced by the {@code repRange}
	 * labeled alternative in {@link SenseParser#rep}.
	 * @param ctx the parse tree
	 */
	void enterRepRange(SenseParser.RepRangeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code repRange}
	 * labeled alternative in {@link SenseParser#rep}.
	 * @param ctx the parse tree
	 */
	void exitRepRange(SenseParser.RepRangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SenseParser#repUnit}.
	 * @param ctx the parse tree
	 */
	void enterRepUnit(SenseParser.RepUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link SenseParser#repUnit}.
	 * @param ctx the parse tree
	 */
	void exitRepUnit(SenseParser.RepUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link SenseParser#branch}.
	 * @param ctx the parse tree
	 */
	void enterBranch(SenseParser.BranchContext ctx);
	/**
	 * Exit a parse tree produced by {@link SenseParser#branch}.
	 * @param ctx the parse tree
	 */
	void exitBranch(SenseParser.BranchContext ctx);
}