// Generated from Preprocessor.g4 by ANTLR 4.5.3

	package raven.sqdev.parser.preprocessor;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PreprocessorParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PreprocessorVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PreprocessorParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(PreprocessorParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link PreprocessorParser#preprocessorStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreprocessorStatement(PreprocessorParser.PreprocessorStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PreprocessorParser#include}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInclude(PreprocessorParser.IncludeContext ctx);
	/**
	 * Visit a parse tree produced by {@link PreprocessorParser#define}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefine(PreprocessorParser.DefineContext ctx);
	/**
	 * Visit a parse tree produced by {@link PreprocessorParser#macroArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMacroArgs(PreprocessorParser.MacroArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PreprocessorParser#undefine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUndefine(PreprocessorParser.UndefineContext ctx);
	/**
	 * Visit a parse tree produced by {@link PreprocessorParser#prepIf}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrepIf(PreprocessorParser.PrepIfContext ctx);
	/**
	 * Visit a parse tree produced by {@link PreprocessorParser#error}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitError(PreprocessorParser.ErrorContext ctx);
	/**
	 * Visit a parse tree produced by {@link PreprocessorParser#other}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOther(PreprocessorParser.OtherContext ctx);
}