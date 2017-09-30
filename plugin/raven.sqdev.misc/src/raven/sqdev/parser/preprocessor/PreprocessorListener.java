// Generated from Preprocessor.g4 by ANTLR 4.5.3

	package raven.sqdev.parser.preprocessor;

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
	 * Enter a parse tree produced by {@link PreprocessorParser#preprocessorStatement}.
	 * @param ctx the parse tree
	 */
	void enterPreprocessorStatement(PreprocessorParser.PreprocessorStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreprocessorParser#preprocessorStatement}.
	 * @param ctx the parse tree
	 */
	void exitPreprocessorStatement(PreprocessorParser.PreprocessorStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreprocessorParser#include}.
	 * @param ctx the parse tree
	 */
	void enterInclude(PreprocessorParser.IncludeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreprocessorParser#include}.
	 * @param ctx the parse tree
	 */
	void exitInclude(PreprocessorParser.IncludeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreprocessorParser#define}.
	 * @param ctx the parse tree
	 */
	void enterDefine(PreprocessorParser.DefineContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreprocessorParser#define}.
	 * @param ctx the parse tree
	 */
	void exitDefine(PreprocessorParser.DefineContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreprocessorParser#macroArgs}.
	 * @param ctx the parse tree
	 */
	void enterMacroArgs(PreprocessorParser.MacroArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreprocessorParser#macroArgs}.
	 * @param ctx the parse tree
	 */
	void exitMacroArgs(PreprocessorParser.MacroArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreprocessorParser#undefine}.
	 * @param ctx the parse tree
	 */
	void enterUndefine(PreprocessorParser.UndefineContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreprocessorParser#undefine}.
	 * @param ctx the parse tree
	 */
	void exitUndefine(PreprocessorParser.UndefineContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreprocessorParser#prepIf}.
	 * @param ctx the parse tree
	 */
	void enterPrepIf(PreprocessorParser.PrepIfContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreprocessorParser#prepIf}.
	 * @param ctx the parse tree
	 */
	void exitPrepIf(PreprocessorParser.PrepIfContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreprocessorParser#error}.
	 * @param ctx the parse tree
	 */
	void enterError(PreprocessorParser.ErrorContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreprocessorParser#error}.
	 * @param ctx the parse tree
	 */
	void exitError(PreprocessorParser.ErrorContext ctx);
	/**
	 * Enter a parse tree produced by {@link PreprocessorParser#other}.
	 * @param ctx the parse tree
	 */
	void enterOther(PreprocessorParser.OtherContext ctx);
	/**
	 * Exit a parse tree produced by {@link PreprocessorParser#other}.
	 * @param ctx the parse tree
	 */
	void exitOther(PreprocessorParser.OtherContext ctx);
}