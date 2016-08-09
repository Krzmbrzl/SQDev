// Generated from SQF.g4 by ANTLR 4.5.3
package raven.sqdev.editors.sqfeditor.parsing;
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
	 * Enter a parse tree produced by {@link SQFParser#preprocessor}.
	 * @param ctx the parse tree
	 */
	void enterPreprocessor(SQFParser.PreprocessorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQFParser#preprocessor}.
	 * @param ctx the parse tree
	 */
	void exitPreprocessor(SQFParser.PreprocessorContext ctx);
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
	 * Enter a parse tree produced by the {@code Array}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArray(SQFParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Array}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArray(SQFParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanExpression}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanExpression(SQFParser.BooleanExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanExpression}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanExpression(SQFParser.BooleanExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MacroExpression}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMacroExpression(SQFParser.MacroExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MacroExpression}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMacroExpression(SQFParser.MacroExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ElseExpression}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterElseExpression(SQFParser.ElseExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ElseExpression}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitElseExpression(SQFParser.ElseExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ModifyExpression}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterModifyExpression(SQFParser.ModifyExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ModifyExpression}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitModifyExpression(SQFParser.ModifyExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Nular}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNular(SQFParser.NularContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Nular}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNular(SQFParser.NularContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Parenthese}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenthese(SQFParser.ParentheseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Parenthese}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenthese(SQFParser.ParentheseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InlineCode}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterInlineCode(SQFParser.InlineCodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InlineCode}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitInlineCode(SQFParser.InlineCodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArithmeticExpression}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticExpression(SQFParser.ArithmeticExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArithmeticExpression}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticExpression(SQFParser.ArithmeticExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Unary}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnary(SQFParser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Unary}
	 * labeled alternative in {@link SQFParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnary(SQFParser.UnaryContext ctx);
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