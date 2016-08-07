// Generated from Preprocessor.g4 by ANTLR 4.5.3
package raven.sqdev.editors.parser.preprocessor;
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
	 * Visit a parse tree produced by {@link PreprocessorParser#preprocessing}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreprocessing(PreprocessorParser.PreprocessingContext ctx);
	/**
	 * Visit a parse tree produced by {@link PreprocessorParser#ifBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfBlock(PreprocessorParser.IfBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Define}
	 * labeled alternative in {@link PreprocessorParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefine(PreprocessorParser.DefineContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Undefine}
	 * labeled alternative in {@link PreprocessorParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUndefine(PreprocessorParser.UndefineContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Include}
	 * labeled alternative in {@link PreprocessorParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInclude(PreprocessorParser.IncludeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Error}
	 * labeled alternative in {@link PreprocessorParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitError(PreprocessorParser.ErrorContext ctx);
}