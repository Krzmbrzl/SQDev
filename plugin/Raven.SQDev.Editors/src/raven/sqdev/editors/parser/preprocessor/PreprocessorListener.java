// Generated from Preprocessor.g4 by ANTLR 4.5.3
package raven.sqdev.editors.parser.preprocessor;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PreprocessorParser}.
 */
public interface PreprocessorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PreprocessorParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(PreprocessorParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreprocessorParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(PreprocessorParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreprocessorParser#preprocessing}.
	 * @param ctx the parse tree
	 */
	void enterPreprocessing(PreprocessorParser.PreprocessingContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreprocessorParser#preprocessing}.
	 * @param ctx the parse tree
	 */
	void exitPreprocessing(PreprocessorParser.PreprocessingContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreprocessorParser#ifBlock}.
	 * @param ctx the parse tree
	 */
	void enterIfBlock(PreprocessorParser.IfBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreprocessorParser#ifBlock}.
	 * @param ctx the parse tree
	 */
	void exitIfBlock(PreprocessorParser.IfBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Define}
	 * labeled alternative in {@link PreprocessorParser#command}.
	 * @param ctx the parse tree
	 */
	void enterDefine(PreprocessorParser.DefineContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Define}
	 * labeled alternative in {@link PreprocessorParser#command}.
	 * @param ctx the parse tree
	 */
	void exitDefine(PreprocessorParser.DefineContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Undefine}
	 * labeled alternative in {@link PreprocessorParser#command}.
	 * @param ctx the parse tree
	 */
	void enterUndefine(PreprocessorParser.UndefineContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Undefine}
	 * labeled alternative in {@link PreprocessorParser#command}.
	 * @param ctx the parse tree
	 */
	void exitUndefine(PreprocessorParser.UndefineContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Include}
	 * labeled alternative in {@link PreprocessorParser#command}.
	 * @param ctx the parse tree
	 */
	void enterInclude(PreprocessorParser.IncludeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Include}
	 * labeled alternative in {@link PreprocessorParser#command}.
	 * @param ctx the parse tree
	 */
	void exitInclude(PreprocessorParser.IncludeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Error}
	 * labeled alternative in {@link PreprocessorParser#command}.
	 * @param ctx the parse tree
	 */
	void enterError(PreprocessorParser.ErrorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Error}
	 * labeled alternative in {@link PreprocessorParser#command}.
	 * @param ctx the parse tree
	 */
	void exitError(PreprocessorParser.ErrorContext ctx);
}