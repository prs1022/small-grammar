// Generated from gscript.g4 by ANTLR 4.13.1

package com.grammar.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link gscriptParser}.
 */
public interface gscriptListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link gscriptParser#script}.
	 * @param ctx the parse tree
	 */
	void enterScript(gscriptParser.ScriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link gscriptParser#script}.
	 * @param ctx the parse tree
	 */
	void exitScript(gscriptParser.ScriptContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtExpr}
	 * labeled alternative in {@link gscriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtExpr(gscriptParser.StmtExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtExpr}
	 * labeled alternative in {@link gscriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtExpr(gscriptParser.StmtExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtIf}
	 * labeled alternative in {@link gscriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtIf(gscriptParser.StmtIfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtIf}
	 * labeled alternative in {@link gscriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtIf(gscriptParser.StmtIfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtWhile}
	 * labeled alternative in {@link gscriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtWhile(gscriptParser.StmtWhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtWhile}
	 * labeled alternative in {@link gscriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtWhile(gscriptParser.StmtWhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtFor}
	 * labeled alternative in {@link gscriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtFor(gscriptParser.StmtForContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtFor}
	 * labeled alternative in {@link gscriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtFor(gscriptParser.StmtForContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtSwitch}
	 * labeled alternative in {@link gscriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtSwitch(gscriptParser.StmtSwitchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtSwitch}
	 * labeled alternative in {@link gscriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtSwitch(gscriptParser.StmtSwitchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtBlock}
	 * labeled alternative in {@link gscriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtBlock(gscriptParser.StmtBlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtBlock}
	 * labeled alternative in {@link gscriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtBlock(gscriptParser.StmtBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtSemicolon}
	 * labeled alternative in {@link gscriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtSemicolon(gscriptParser.StmtSemicolonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtSemicolon}
	 * labeled alternative in {@link gscriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtSemicolon(gscriptParser.StmtSemicolonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtBreak}
	 * labeled alternative in {@link gscriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtBreak(gscriptParser.StmtBreakContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtBreak}
	 * labeled alternative in {@link gscriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtBreak(gscriptParser.StmtBreakContext ctx);
	/**
	 * Enter a parse tree produced by {@link gscriptParser#if_}.
	 * @param ctx the parse tree
	 */
	void enterIf_(gscriptParser.If_Context ctx);
	/**
	 * Exit a parse tree produced by {@link gscriptParser#if_}.
	 * @param ctx the parse tree
	 */
	void exitIf_(gscriptParser.If_Context ctx);
	/**
	 * Enter a parse tree produced by {@link gscriptParser#while_}.
	 * @param ctx the parse tree
	 */
	void enterWhile_(gscriptParser.While_Context ctx);
	/**
	 * Exit a parse tree produced by {@link gscriptParser#while_}.
	 * @param ctx the parse tree
	 */
	void exitWhile_(gscriptParser.While_Context ctx);
	/**
	 * Enter a parse tree produced by {@link gscriptParser#for_}.
	 * @param ctx the parse tree
	 */
	void enterFor_(gscriptParser.For_Context ctx);
	/**
	 * Exit a parse tree produced by {@link gscriptParser#for_}.
	 * @param ctx the parse tree
	 */
	void exitFor_(gscriptParser.For_Context ctx);
	/**
	 * Enter a parse tree produced by {@link gscriptParser#for_in}.
	 * @param ctx the parse tree
	 */
	void enterFor_in(gscriptParser.For_inContext ctx);
	/**
	 * Exit a parse tree produced by {@link gscriptParser#for_in}.
	 * @param ctx the parse tree
	 */
	void exitFor_in(gscriptParser.For_inContext ctx);
	/**
	 * Enter a parse tree produced by {@link gscriptParser#switch_}.
	 * @param ctx the parse tree
	 */
	void enterSwitch_(gscriptParser.Switch_Context ctx);
	/**
	 * Exit a parse tree produced by {@link gscriptParser#switch_}.
	 * @param ctx the parse tree
	 */
	void exitSwitch_(gscriptParser.Switch_Context ctx);
	/**
	 * Enter a parse tree produced by {@link gscriptParser#case_}.
	 * @param ctx the parse tree
	 */
	void enterCase_(gscriptParser.Case_Context ctx);
	/**
	 * Exit a parse tree produced by {@link gscriptParser#case_}.
	 * @param ctx the parse tree
	 */
	void exitCase_(gscriptParser.Case_Context ctx);
	/**
	 * Enter a parse tree produced by {@link gscriptParser#default_}.
	 * @param ctx the parse tree
	 */
	void enterDefault_(gscriptParser.Default_Context ctx);
	/**
	 * Exit a parse tree produced by {@link gscriptParser#default_}.
	 * @param ctx the parse tree
	 */
	void exitDefault_(gscriptParser.Default_Context ctx);
	/**
	 * Enter a parse tree produced by {@link gscriptParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(gscriptParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link gscriptParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(gscriptParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprMulDivMod}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprMulDivMod(gscriptParser.ExprMulDivModContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprMulDivMod}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprMulDivMod(gscriptParser.ExprMulDivModContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprPlusMinus}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprPlusMinus(gscriptParser.ExprPlusMinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprPlusMinus}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprPlusMinus(gscriptParser.ExprPlusMinusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprBrackets}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprBrackets(gscriptParser.ExprBracketsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprBrackets}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprBrackets(gscriptParser.ExprBracketsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprAssign}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprAssign(gscriptParser.ExprAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprAssign}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprAssign(gscriptParser.ExprAssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprEqNeq}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprEqNeq(gscriptParser.ExprEqNeqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprEqNeq}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprEqNeq(gscriptParser.ExprEqNeqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprUnary}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprUnary(gscriptParser.ExprUnaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprUnary}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprUnary(gscriptParser.ExprUnaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprLogicalOr}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprLogicalOr(gscriptParser.ExprLogicalOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprLogicalOr}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprLogicalOr(gscriptParser.ExprLogicalOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprCompare}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprCompare(gscriptParser.ExprCompareContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprCompare}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprCompare(gscriptParser.ExprCompareContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprIn}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprIn(gscriptParser.ExprInContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprIn}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprIn(gscriptParser.ExprInContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprSelfIncDecPre}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprSelfIncDecPre(gscriptParser.ExprSelfIncDecPreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprSelfIncDecPre}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprSelfIncDecPre(gscriptParser.ExprSelfIncDecPreContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprBitXor}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprBitXor(gscriptParser.ExprBitXorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprBitXor}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprBitXor(gscriptParser.ExprBitXorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprLogicalAnd}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprLogicalAnd(gscriptParser.ExprLogicalAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprLogicalAnd}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprLogicalAnd(gscriptParser.ExprLogicalAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprId}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprId(gscriptParser.ExprIdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprId}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprId(gscriptParser.ExprIdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprPow}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprPow(gscriptParser.ExprPowContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprPow}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprPow(gscriptParser.ExprPowContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprShift}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprShift(gscriptParser.ExprShiftContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprShift}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprShift(gscriptParser.ExprShiftContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprConst}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprConst(gscriptParser.ExprConstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprConst}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprConst(gscriptParser.ExprConstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprMap}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprMap(gscriptParser.ExprMapContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprMap}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprMap(gscriptParser.ExprMapContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprSelfIncDecPost}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprSelfIncDecPost(gscriptParser.ExprSelfIncDecPostContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprSelfIncDecPost}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprSelfIncDecPost(gscriptParser.ExprSelfIncDecPostContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprDelete}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprDelete(gscriptParser.ExprDeleteContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprDelete}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprDelete(gscriptParser.ExprDeleteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprInvoke}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprInvoke(gscriptParser.ExprInvokeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprInvoke}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprInvoke(gscriptParser.ExprInvokeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprTypeof}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprTypeof(gscriptParser.ExprTypeofContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprTypeof}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprTypeof(gscriptParser.ExprTypeofContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprBitAnd}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprBitAnd(gscriptParser.ExprBitAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprBitAnd}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprBitAnd(gscriptParser.ExprBitAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprDot}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprDot(gscriptParser.ExprDotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprDot}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprDot(gscriptParser.ExprDotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprConditional}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprConditional(gscriptParser.ExprConditionalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprConditional}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprConditional(gscriptParser.ExprConditionalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprBitOr}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprBitOr(gscriptParser.ExprBitOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprBitOr}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprBitOr(gscriptParser.ExprBitOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprParenthesis}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprParenthesis(gscriptParser.ExprParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprParenthesis}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprParenthesis(gscriptParser.ExprParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprArray}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprArray(gscriptParser.ExprArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprArray}
	 * labeled alternative in {@link gscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprArray(gscriptParser.ExprArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link gscriptParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(gscriptParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link gscriptParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(gscriptParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link gscriptParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(gscriptParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link gscriptParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(gscriptParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link gscriptParser#map}.
	 * @param ctx the parse tree
	 */
	void enterMap(gscriptParser.MapContext ctx);
	/**
	 * Exit a parse tree produced by {@link gscriptParser#map}.
	 * @param ctx the parse tree
	 */
	void exitMap(gscriptParser.MapContext ctx);
	/**
	 * Enter a parse tree produced by {@link gscriptParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(gscriptParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link gscriptParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(gscriptParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link gscriptParser#expr_semicolon}.
	 * @param ctx the parse tree
	 */
	void enterExpr_semicolon(gscriptParser.Expr_semicolonContext ctx);
	/**
	 * Exit a parse tree produced by {@link gscriptParser#expr_semicolon}.
	 * @param ctx the parse tree
	 */
	void exitExpr_semicolon(gscriptParser.Expr_semicolonContext ctx);
	/**
	 * Enter a parse tree produced by {@link gscriptParser#expr_list}.
	 * @param ctx the parse tree
	 */
	void enterExpr_list(gscriptParser.Expr_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link gscriptParser#expr_list}.
	 * @param ctx the parse tree
	 */
	void exitExpr_list(gscriptParser.Expr_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link gscriptParser#var_with_member}.
	 * @param ctx the parse tree
	 */
	void enterVar_with_member(gscriptParser.Var_with_memberContext ctx);
	/**
	 * Exit a parse tree produced by {@link gscriptParser#var_with_member}.
	 * @param ctx the parse tree
	 */
	void exitVar_with_member(gscriptParser.Var_with_memberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code memberBrackets}
	 * labeled alternative in {@link gscriptParser#member}.
	 * @param ctx the parse tree
	 */
	void enterMemberBrackets(gscriptParser.MemberBracketsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code memberBrackets}
	 * labeled alternative in {@link gscriptParser#member}.
	 * @param ctx the parse tree
	 */
	void exitMemberBrackets(gscriptParser.MemberBracketsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code memberDot}
	 * labeled alternative in {@link gscriptParser#member}.
	 * @param ctx the parse tree
	 */
	void enterMemberDot(gscriptParser.MemberDotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code memberDot}
	 * labeled alternative in {@link gscriptParser#member}.
	 * @param ctx the parse tree
	 */
	void exitMemberDot(gscriptParser.MemberDotContext ctx);
	/**
	 * Enter a parse tree produced by {@link gscriptParser#lval}.
	 * @param ctx the parse tree
	 */
	void enterLval(gscriptParser.LvalContext ctx);
	/**
	 * Exit a parse tree produced by {@link gscriptParser#lval}.
	 * @param ctx the parse tree
	 */
	void exitLval(gscriptParser.LvalContext ctx);
}