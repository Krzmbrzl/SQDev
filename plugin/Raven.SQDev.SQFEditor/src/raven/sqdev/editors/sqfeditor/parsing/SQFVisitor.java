package raven.sqdev.editors.sqfeditor.parsing;

// Generated from SQF.g4 by ANTLR 4.5.3
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
	 * Visit a parse tree produced by {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(SQFParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQFParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpression(SQFParser.UnaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQFParser#nularExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNularExpression(SQFParser.NularExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQFParser#macro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMacro(SQFParser.MacroContext ctx);
}