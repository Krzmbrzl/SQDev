package raven.sqdev.editors.sqfeditor.parsing;

// Generated from SQF.g4 by ANTLR 4.5.3
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SQFParser}.
 */
public interface SQFListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SQFParser#code}.
	 * @param ctx the parse tree
	 */
	void enterCode(SQFParser.CodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQFParser#code}.
	 * @param ctx the parse tree
	 */
	void exitCode(SQFParser.CodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQFParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(SQFParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQFParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(SQFParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQFParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(SQFParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQFParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(SQFParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQFParser#binaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpression(SQFParser.BinaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQFParser#binaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpression(SQFParser.BinaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(SQFParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(SQFParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQFParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(SQFParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQFParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(SQFParser.UnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQFParser#nularExpression}.
	 * @param ctx the parse tree
	 */
	void enterNularExpression(SQFParser.NularExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQFParser#nularExpression}.
	 * @param ctx the parse tree
	 */
	void exitNularExpression(SQFParser.NularExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQFParser#macro}.
	 * @param ctx the parse tree
	 */
	void enterMacro(SQFParser.MacroContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQFParser#macro}.
	 * @param ctx the parse tree
	 */
	void exitMacro(SQFParser.MacroContext ctx);
}