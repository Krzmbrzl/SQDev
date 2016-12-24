// Generated from SQF.g4 by ANTLR 4.5.3

	package raven.sqdev.editors.sqfeditor.parsing;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SQFParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SQFVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SQFParser#code}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCode(SQFParser.CodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQFParser#macro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMacro(SQFParser.MacroContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQFParser#macroArgument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMacroArgument(SQFParser.MacroArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQFParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(SQFParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQFParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(SQFParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQFParser#binaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryExpression(SQFParser.BinaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryOperator}
	 * labeled alternative in {@link SQFParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOperator(SQFParser.UnaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nularOperator}
	 * labeled alternative in {@link SQFParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNularOperator(SQFParser.NularOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code macroExpression}
	 * labeled alternative in {@link SQFParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMacroExpression(SQFParser.MacroExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Number}
	 * labeled alternative in {@link SQFParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(SQFParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code String}
	 * labeled alternative in {@link SQFParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(SQFParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InlineCode}
	 * labeled alternative in {@link SQFParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInlineCode(SQFParser.InlineCodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Array}
	 * labeled alternative in {@link SQFParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(SQFParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parenthesis}
	 * labeled alternative in {@link SQFParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesis(SQFParser.ParenthesisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Error}
	 * labeled alternative in {@link SQFParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitError(SQFParser.ErrorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQFParser#commonError}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommonError(SQFParser.CommonErrorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQFParser#nularExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNularExpression(SQFParser.NularExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQFParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpression(SQFParser.UnaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQFParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(SQFParser.OperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQFParser#punctuation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPunctuation(SQFParser.PunctuationContext ctx);
}