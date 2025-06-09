// Generated from gscript.g4 by ANTLR 4.13.1

package com.grammar.antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link gscriptParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface gscriptVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link gscriptParser#script}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScript(gscriptParser.ScriptContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtExpr}
	 * labeled alternative in {@link gscriptParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtExpr(gscriptParser.StmtExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtIf}
	 * labeled alternative in {@link gscriptParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtIf(gscriptParser.StmtIfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtWhile}
	 * labeled alternative in {@link gscriptParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtWhile(gscriptParser.StmtWhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtFor}
	 * labeled alternative in {@link gscriptParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtFor(gscriptParser.StmtForContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtSwitch}
	 * labeled alternative in {@link gscriptParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtSwitch(gscriptParser.StmtSwitchContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtBlock}
	 * labeled alternative in {@link gscriptParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtBlock(gscriptParser.StmtBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtSemicolon}
	 * labeled alternative in {@link gscriptParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtSemicolon(gscriptParser.StmtSemicolonContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtBreak}
	 * labeled alternative in {@link gscriptParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtBreak(gscriptParser.StmtBreakContext ctx);
	/**
	 * Visit a parse tree produced by {@link gscriptParser#if_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_(gscriptParser.If_Context ctx);
	/**
	 * Visit a parse tree produced by {@link gscriptParser#while_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_(gscriptParser.While_Context ctx);
	/**
	 * Visit a parse tree produced by {@link gscriptParser#for_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_(gscriptParser.For_Context ctx);
	/**
	 * Visit a parse tree produced by {@link gscriptParser#for_in}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_in(gscriptParser.For_inContext ctx);
	/**
	 * Visit a parse tree produced by {@link gscriptParser#switch_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitch_(gscriptParser.Switch_Context ctx);
	/**
	 * Visit a parse tree produced by {@link gscriptParser#case_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCase_(gscriptParser.Case_Context ctx);
	/**
	 * Visit a parse tree produced by {@link gscriptParser#default_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefault_(gscriptParser.Default_Context ctx);
	/**
	 * Visit a parse tree produced by {@link gscriptParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(gscriptParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprMulDivMod}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMulDivMod(gscriptParser.ExprMulDivModContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprPlusMinus}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPlusMinus(gscriptParser.ExprPlusMinusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprBrackets}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprBrackets(gscriptParser.ExprBracketsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprAssign}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAssign(gscriptParser.ExprAssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprEqNeq}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprEqNeq(gscriptParser.ExprEqNeqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprUnary}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprUnary(gscriptParser.ExprUnaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprLogicalOr}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLogicalOr(gscriptParser.ExprLogicalOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprCompare}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprCompare(gscriptParser.ExprCompareContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprIn}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprIn(gscriptParser.ExprInContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprSelfIncDecPre}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprSelfIncDecPre(gscriptParser.ExprSelfIncDecPreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprBitXor}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprBitXor(gscriptParser.ExprBitXorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprLogicalAnd}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLogicalAnd(gscriptParser.ExprLogicalAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprId}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprId(gscriptParser.ExprIdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprPow}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPow(gscriptParser.ExprPowContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprShift}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprShift(gscriptParser.ExprShiftContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprConst}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprConst(gscriptParser.ExprConstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprMap}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMap(gscriptParser.ExprMapContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprSelfIncDecPost}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprSelfIncDecPost(gscriptParser.ExprSelfIncDecPostContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprDelete}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprDelete(gscriptParser.ExprDeleteContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprInvoke}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprInvoke(gscriptParser.ExprInvokeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprTypeof}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprTypeof(gscriptParser.ExprTypeofContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprBitAnd}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprBitAnd(gscriptParser.ExprBitAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprDot}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprDot(gscriptParser.ExprDotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprConditional}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprConditional(gscriptParser.ExprConditionalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprBitOr}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprBitOr(gscriptParser.ExprBitOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprParenthesis}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprParenthesis(gscriptParser.ExprParenthesisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprArray}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprArray(gscriptParser.ExprArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link gscriptParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(gscriptParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link gscriptParser#array}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(gscriptParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link gscriptParser#map}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMap(gscriptParser.MapContext ctx);
	/**
	 * Visit a parse tree produced by {@link gscriptParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(gscriptParser.FieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link gscriptParser#expr_semicolon}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_semicolon(gscriptParser.Expr_semicolonContext ctx);
	/**
	 * Visit a parse tree produced by {@link gscriptParser#expr_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_list(gscriptParser.Expr_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link gscriptParser#var_with_member}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_with_member(gscriptParser.Var_with_memberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code memberBrackets}
	 * labeled alternative in {@link gscriptParser#member}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberBrackets(gscriptParser.MemberBracketsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code memberDot}
	 * labeled alternative in {@link gscriptParser#member}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberDot(gscriptParser.MemberDotContext ctx);
	/**
	 * Visit a parse tree produced by {@link gscriptParser#lval}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLval(gscriptParser.LvalContext ctx);
}