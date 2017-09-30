package raven.sqdev.editors.stringtableParsing;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link StringTableParser}.
 */
@SuppressWarnings("deprecation")
public interface StringTableListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link StringTableParser#packageStartTag}.
	 * @param ctx the parse tree
	 */
	void enterPackageStartTag(@NotNull StringTableParser.PackageStartTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link StringTableParser#packageStartTag}.
	 * @param ctx the parse tree
	 */
	void exitPackageStartTag(@NotNull StringTableParser.PackageStartTagContext ctx);

	/**
	 * Enter a parse tree produced by {@link StringTableParser#container}.
	 * @param ctx the parse tree
	 */
	void enterContainer(@NotNull StringTableParser.ContainerContext ctx);
	/**
	 * Exit a parse tree produced by {@link StringTableParser#container}.
	 * @param ctx the parse tree
	 */
	void exitContainer(@NotNull StringTableParser.ContainerContext ctx);

	/**
	 * Enter a parse tree produced by {@link StringTableParser#keyCloseTag}.
	 * @param ctx the parse tree
	 */
	void enterKeyCloseTag(@NotNull StringTableParser.KeyCloseTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link StringTableParser#keyCloseTag}.
	 * @param ctx the parse tree
	 */
	void exitKeyCloseTag(@NotNull StringTableParser.KeyCloseTagContext ctx);

	/**
	 * Enter a parse tree produced by {@link StringTableParser#keyStartTag}.
	 * @param ctx the parse tree
	 */
	void enterKeyStartTag(@NotNull StringTableParser.KeyStartTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link StringTableParser#keyStartTag}.
	 * @param ctx the parse tree
	 */
	void exitKeyStartTag(@NotNull StringTableParser.KeyStartTagContext ctx);

	/**
	 * Enter a parse tree produced by {@link StringTableParser#project}.
	 * @param ctx the parse tree
	 */
	void enterProject(@NotNull StringTableParser.ProjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link StringTableParser#project}.
	 * @param ctx the parse tree
	 */
	void exitProject(@NotNull StringTableParser.ProjectContext ctx);

	/**
	 * Enter a parse tree produced by {@link StringTableParser#languageCloseTag}.
	 * @param ctx the parse tree
	 */
	void enterLanguageCloseTag(@NotNull StringTableParser.LanguageCloseTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link StringTableParser#languageCloseTag}.
	 * @param ctx the parse tree
	 */
	void exitLanguageCloseTag(@NotNull StringTableParser.LanguageCloseTagContext ctx);

	/**
	 * Enter a parse tree produced by {@link StringTableParser#projectStartTag}.
	 * @param ctx the parse tree
	 */
	void enterProjectStartTag(@NotNull StringTableParser.ProjectStartTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link StringTableParser#projectStartTag}.
	 * @param ctx the parse tree
	 */
	void exitProjectStartTag(@NotNull StringTableParser.ProjectStartTagContext ctx);

	/**
	 * Enter a parse tree produced by {@link StringTableParser#language}.
	 * @param ctx the parse tree
	 */
	void enterLanguage(@NotNull StringTableParser.LanguageContext ctx);
	/**
	 * Exit a parse tree produced by {@link StringTableParser#language}.
	 * @param ctx the parse tree
	 */
	void exitLanguage(@NotNull StringTableParser.LanguageContext ctx);

	/**
	 * Enter a parse tree produced by {@link StringTableParser#pkg}.
	 * @param ctx the parse tree
	 */
	void enterPkg(@NotNull StringTableParser.PkgContext ctx);
	/**
	 * Exit a parse tree produced by {@link StringTableParser#pkg}.
	 * @param ctx the parse tree
	 */
	void exitPkg(@NotNull StringTableParser.PkgContext ctx);

	/**
	 * Enter a parse tree produced by {@link StringTableParser#content}.
	 * @param ctx the parse tree
	 */
	void enterContent(@NotNull StringTableParser.ContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link StringTableParser#content}.
	 * @param ctx the parse tree
	 */
	void exitContent(@NotNull StringTableParser.ContentContext ctx);

	/**
	 * Enter a parse tree produced by {@link StringTableParser#containerStartTag}.
	 * @param ctx the parse tree
	 */
	void enterContainerStartTag(@NotNull StringTableParser.ContainerStartTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link StringTableParser#containerStartTag}.
	 * @param ctx the parse tree
	 */
	void exitContainerStartTag(@NotNull StringTableParser.ContainerStartTagContext ctx);

	/**
	 * Enter a parse tree produced by {@link StringTableParser#containerEndTag}.
	 * @param ctx the parse tree
	 */
	void enterContainerEndTag(@NotNull StringTableParser.ContainerEndTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link StringTableParser#containerEndTag}.
	 * @param ctx the parse tree
	 */
	void exitContainerEndTag(@NotNull StringTableParser.ContainerEndTagContext ctx);

	/**
	 * Enter a parse tree produced by {@link StringTableParser#packageEndTag}.
	 * @param ctx the parse tree
	 */
	void enterPackageEndTag(@NotNull StringTableParser.PackageEndTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link StringTableParser#packageEndTag}.
	 * @param ctx the parse tree
	 */
	void exitPackageEndTag(@NotNull StringTableParser.PackageEndTagContext ctx);

	/**
	 * Enter a parse tree produced by {@link StringTableParser#projectEndTag}.
	 * @param ctx the parse tree
	 */
	void enterProjectEndTag(@NotNull StringTableParser.ProjectEndTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link StringTableParser#projectEndTag}.
	 * @param ctx the parse tree
	 */
	void exitProjectEndTag(@NotNull StringTableParser.ProjectEndTagContext ctx);

	/**
	 * Enter a parse tree produced by {@link StringTableParser#key}.
	 * @param ctx the parse tree
	 */
	void enterKey(@NotNull StringTableParser.KeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link StringTableParser#key}.
	 * @param ctx the parse tree
	 */
	void exitKey(@NotNull StringTableParser.KeyContext ctx);

	/**
	 * Enter a parse tree produced by {@link StringTableParser#languageOpenTag}.
	 * @param ctx the parse tree
	 */
	void enterLanguageOpenTag(@NotNull StringTableParser.LanguageOpenTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link StringTableParser#languageOpenTag}.
	 * @param ctx the parse tree
	 */
	void exitLanguageOpenTag(@NotNull StringTableParser.LanguageOpenTagContext ctx);
}