package raven.sqdev.editors.stringtableParsing;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link StringTableParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
@SuppressWarnings("deprecation")
public interface StringTableVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link StringTableParser#packageStartTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageStartTag(@NotNull StringTableParser.PackageStartTagContext ctx);

	/**
	 * Visit a parse tree produced by {@link StringTableParser#container}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContainer(@NotNull StringTableParser.ContainerContext ctx);

	/**
	 * Visit a parse tree produced by {@link StringTableParser#keyCloseTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyCloseTag(@NotNull StringTableParser.KeyCloseTagContext ctx);

	/**
	 * Visit a parse tree produced by {@link StringTableParser#keyStartTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyStartTag(@NotNull StringTableParser.KeyStartTagContext ctx);

	/**
	 * Visit a parse tree produced by {@link StringTableParser#project}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProject(@NotNull StringTableParser.ProjectContext ctx);

	/**
	 * Visit a parse tree produced by {@link StringTableParser#languageCloseTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLanguageCloseTag(@NotNull StringTableParser.LanguageCloseTagContext ctx);

	/**
	 * Visit a parse tree produced by {@link StringTableParser#projectStartTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProjectStartTag(@NotNull StringTableParser.ProjectStartTagContext ctx);

	/**
	 * Visit a parse tree produced by {@link StringTableParser#language}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLanguage(@NotNull StringTableParser.LanguageContext ctx);

	/**
	 * Visit a parse tree produced by {@link StringTableParser#pkg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPkg(@NotNull StringTableParser.PkgContext ctx);

	/**
	 * Visit a parse tree produced by {@link StringTableParser#content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContent(@NotNull StringTableParser.ContentContext ctx);

	/**
	 * Visit a parse tree produced by {@link StringTableParser#containerStartTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContainerStartTag(@NotNull StringTableParser.ContainerStartTagContext ctx);

	/**
	 * Visit a parse tree produced by {@link StringTableParser#containerEndTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContainerEndTag(@NotNull StringTableParser.ContainerEndTagContext ctx);

	/**
	 * Visit a parse tree produced by {@link StringTableParser#packageEndTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageEndTag(@NotNull StringTableParser.PackageEndTagContext ctx);

	/**
	 * Visit a parse tree produced by {@link StringTableParser#projectEndTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProjectEndTag(@NotNull StringTableParser.ProjectEndTagContext ctx);

	/**
	 * Visit a parse tree produced by {@link StringTableParser#key}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKey(@NotNull StringTableParser.KeyContext ctx);

	/**
	 * Visit a parse tree produced by {@link StringTableParser#languageOpenTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLanguageOpenTag(@NotNull StringTableParser.LanguageOpenTagContext ctx);
}