// Generated from SQF.g4 by ANTLR 4.5.3

	package raven.sqdev.editors.sqfeditor.parsing;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SQFParser}.
 */
public interface SQFListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SQFParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(SQFParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQFParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(SQFParser.StartContext ctx);
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
	 * Enter a parse tree produced by {@link SQFParser#macro}.
	 * @param ctx the parse tree
	 */
	void enterMacro(SQFParser.MacroContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQFParser#macro}.
	 * @param ctx the parse tree
	 */
	void exitMacro(SQFParser.MacroContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQFParser#macroArgument}.
	 * @param ctx the parse tree
	 */
	void enterMacroArgument(SQFParser.MacroArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQFParser#macroArgument}.
	 * @param ctx the parse tree
	 */
	void exitMacroArgument(SQFParser.MacroArgumentContext ctx);
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
	 * Enter a parse tree produced by {@link SQFParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression(SQFParser.PrimaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQFParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression(SQFParser.PrimaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NularOperator}
	 * labeled alternative in {@link SQFParser#nularExpression}.
	 * @param ctx the parse tree
	 */
	void enterNularOperator(SQFParser.NularOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NularOperator}
	 * labeled alternative in {@link SQFParser#nularExpression}.
	 * @param ctx the parse tree
	 */
	void exitNularOperator(SQFParser.NularOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Number}
	 * labeled alternative in {@link SQFParser#nularExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumber(SQFParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Number}
	 * labeled alternative in {@link SQFParser#nularExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumber(SQFParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code String}
	 * labeled alternative in {@link SQFParser#nularExpression}.
	 * @param ctx the parse tree
	 */
	void enterString(SQFParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code String}
	 * labeled alternative in {@link SQFParser#nularExpression}.
	 * @param ctx the parse tree
	 */
	void exitString(SQFParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InlineCode}
	 * labeled alternative in {@link SQFParser#nularExpression}.
	 * @param ctx the parse tree
	 */
	void enterInlineCode(SQFParser.InlineCodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InlineCode}
	 * labeled alternative in {@link SQFParser#nularExpression}.
	 * @param ctx the parse tree
	 */
	void exitInlineCode(SQFParser.InlineCodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Array}
	 * labeled alternative in {@link SQFParser#nularExpression}.
	 * @param ctx the parse tree
	 */
	void enterArray(SQFParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Array}
	 * labeled alternative in {@link SQFParser#nularExpression}.
	 * @param ctx the parse tree
	 */
	void exitArray(SQFParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Parenthesis}
	 * labeled alternative in {@link SQFParser#nularExpression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesis(SQFParser.ParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Parenthesis}
	 * labeled alternative in {@link SQFParser#nularExpression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesis(SQFParser.ParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Error}
	 * labeled alternative in {@link SQFParser#nularExpression}.
	 * @param ctx the parse tree
	 */
	void enterError(SQFParser.ErrorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Error}
	 * labeled alternative in {@link SQFParser#nularExpression}.
	 * @param ctx the parse tree
	 */
	void exitError(SQFParser.ErrorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQFParser#commonError}.
	 * @param ctx the parse tree
	 */
	void enterCommonError(SQFParser.CommonErrorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQFParser#commonError}.
	 * @param ctx the parse tree
	 */
	void exitCommonError(SQFParser.CommonErrorContext ctx);
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
	 * Enter a parse tree produced by {@link SQFParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(SQFParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQFParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(SQFParser.OperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQFParser#punctuation}.
	 * @param ctx the parse tree
	 */
	void enterPunctuation(SQFParser.PunctuationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQFParser#punctuation}.
	 * @param ctx the parse tree
	 */
	void exitPunctuation(SQFParser.PunctuationContext ctx);
}