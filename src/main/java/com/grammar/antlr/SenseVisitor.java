// Generated from Sense.g4 by ANTLR 4.13.1

package com.grammar.antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SenseParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SenseVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SenseParser#sense}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSense(SenseParser.SenseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SenseParser#nsSentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNsSentence(SenseParser.NsSentenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link SenseParser#importSentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportSentence(SenseParser.ImportSentenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link SenseParser#scriptSentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScriptSentence(SenseParser.ScriptSentenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link SenseParser#primeSentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimeSentence(SenseParser.PrimeSentenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link SenseParser#macroSentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMacroSentence(SenseParser.MacroSentenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link SenseParser#productionSentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProductionSentence(SenseParser.ProductionSentenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link SenseParser#exclusion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExclusion(SenseParser.ExclusionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SenseParser#annotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotation(SenseParser.AnnotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SenseParser#assigns}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssigns(SenseParser.AssignsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SenseParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(SenseParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link SenseParser#criteria}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCriteria(SenseParser.CriteriaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code catExprAnd}
	 * labeled alternative in {@link SenseParser#catExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCatExprAnd(SenseParser.CatExprAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code catExprOr}
	 * labeled alternative in {@link SenseParser#catExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCatExprOr(SenseParser.CatExprOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code catExprUnit}
	 * labeled alternative in {@link SenseParser#catExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCatExprUnit(SenseParser.CatExprUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link SenseParser#catUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCatUnit(SenseParser.CatUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link SenseParser#catBranch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCatBranch(SenseParser.CatBranchContext ctx);
	/**
	 * Visit a parse tree produced by {@link SenseParser#cat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCat(SenseParser.CatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code wordAttrExprAnd}
	 * labeled alternative in {@link SenseParser#wordAttrExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWordAttrExprAnd(SenseParser.WordAttrExprAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code wordAttrExprOr}
	 * labeled alternative in {@link SenseParser#wordAttrExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWordAttrExprOr(SenseParser.WordAttrExprOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code wordAttrExprUnit}
	 * labeled alternative in {@link SenseParser#wordAttrExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWordAttrExprUnit(SenseParser.WordAttrExprUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link SenseParser#wordAttrBranch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWordAttrBranch(SenseParser.WordAttrBranchContext ctx);
	/**
	 * Visit a parse tree produced by {@link SenseParser#wordAttr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWordAttr(SenseParser.WordAttrContext ctx);
	/**
	 * Visit a parse tree produced by {@link SenseParser#wordAttrUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWordAttrUnit(SenseParser.WordAttrUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link SenseParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(SenseParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link SenseParser#array}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(SenseParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link SenseParser#dict}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDict(SenseParser.DictContext ctx);
	/**
	 * Visit a parse tree produced by {@link SenseParser#dictItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDictItem(SenseParser.DictItemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprSequence}
	 * labeled alternative in {@link SenseParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprSequence(SenseParser.ExprSequenceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprChoice}
	 * labeled alternative in {@link SenseParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprChoice(SenseParser.ExprChoiceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprUnit}
	 * labeled alternative in {@link SenseParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprUnit(SenseParser.ExprUnitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unitLiteral}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnitLiteral(SenseParser.UnitLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unitCharList}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnitCharList(SenseParser.UnitCharListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unitLookaheadLiteral}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnitLookaheadLiteral(SenseParser.UnitLookaheadLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unitReference}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnitReference(SenseParser.UnitReferenceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unitMacro}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnitMacro(SenseParser.UnitMacroContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unitPrime}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnitPrime(SenseParser.UnitPrimeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unitPrimeAssign}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnitPrimeAssign(SenseParser.UnitPrimeAssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unitStatementAssign}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnitStatementAssign(SenseParser.UnitStatementAssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unitParenthesis}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnitParenthesis(SenseParser.UnitParenthesisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unitAnyChar}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnitAnyChar(SenseParser.UnitAnyCharContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unitTags}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnitTags(SenseParser.UnitTagsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unitMacroArg}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnitMacroArg(SenseParser.UnitMacroArgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unitScriptFunction}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnitScriptFunction(SenseParser.UnitScriptFunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unitScript}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnitScript(SenseParser.UnitScriptContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unitRegex}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnitRegex(SenseParser.UnitRegexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unitNoskip}
	 * labeled alternative in {@link SenseParser#unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnitNoskip(SenseParser.UnitNoskipContext ctx);
	/**
	 * Visit a parse tree produced by {@link SenseParser#idList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdList(SenseParser.IdListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SenseParser#qualifiedName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiedName(SenseParser.QualifiedNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SenseParser#namespace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespace(SenseParser.NamespaceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code repOneOrMore}
	 * labeled alternative in {@link SenseParser#rep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepOneOrMore(SenseParser.RepOneOrMoreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code repZeroOrMore}
	 * labeled alternative in {@link SenseParser#rep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepZeroOrMore(SenseParser.RepZeroOrMoreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code repZeroOrOne}
	 * labeled alternative in {@link SenseParser#rep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepZeroOrOne(SenseParser.RepZeroOrOneContext ctx);
	/**
	 * Visit a parse tree produced by the {@code repPrefer}
	 * labeled alternative in {@link SenseParser#rep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepPrefer(SenseParser.RepPreferContext ctx);
	/**
	 * Visit a parse tree produced by the {@code repRange}
	 * labeled alternative in {@link SenseParser#rep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepRange(SenseParser.RepRangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SenseParser#repUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepUnit(SenseParser.RepUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link SenseParser#branch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranch(SenseParser.BranchContext ctx);
}