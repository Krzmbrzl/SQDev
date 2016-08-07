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
	 * Visit a parse tree produced by {@link SQFParser#preprocessor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreprocessor(SQFParser.PreprocessorContext ctx);
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
	 * Visit a parse tree produced by the {@code Array}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(SQFParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanExpression}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanExpression(SQFParser.BooleanExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ElseExpression}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseExpression(SQFParser.ElseExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ModifyExpression}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModifyExpression(SQFParser.ModifyExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Nular}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNular(SQFParser.NularContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parenthese}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthese(SQFParser.ParentheseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InlineCode}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInlineCode(SQFParser.InlineCodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArithmeticExpression}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticExpression(SQFParser.ArithmeticExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Unary}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary(SQFParser.UnaryContext ctx);
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