// Generated from C:\Users\Robert Adam\Documents\Antlr\SQFCommand.g4 by ANTLR 4.1
package SQFBaseParser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SQFCommandParser}.
 */
public interface SQFCommandListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SQFCommandParser#commandName}.
	 * @param ctx the parse tree
	 */
	void enterCommandName(@NotNull SQFCommandParser.CommandNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQFCommandParser#commandName}.
	 * @param ctx the parse tree
	 */
	void exitCommandName(@NotNull SQFCommandParser.CommandNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link SQFCommandParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(@NotNull SQFCommandParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQFCommandParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(@NotNull SQFCommandParser.ParameterContext ctx);

	/**
	 * Enter a parse tree produced by {@link SQFCommandParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(@NotNull SQFCommandParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQFCommandParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(@NotNull SQFCommandParser.StartContext ctx);

	/**
	 * Enter a parse tree produced by {@link SQFCommandParser#alternative}.
	 * @param ctx the parse tree
	 */
	void enterAlternative(@NotNull SQFCommandParser.AlternativeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQFCommandParser#alternative}.
	 * @param ctx the parse tree
	 */
	void exitAlternative(@NotNull SQFCommandParser.AlternativeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SQFCommandParser#commandSyntax}.
	 * @param ctx the parse tree
	 */
	void enterCommandSyntax(@NotNull SQFCommandParser.CommandSyntaxContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQFCommandParser#commandSyntax}.
	 * @param ctx the parse tree
	 */
	void exitCommandSyntax(@NotNull SQFCommandParser.CommandSyntaxContext ctx);

	/**
	 * Enter a parse tree produced by {@link SQFCommandParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(@NotNull SQFCommandParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQFCommandParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(@NotNull SQFCommandParser.TypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SQFCommandParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(@NotNull SQFCommandParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQFCommandParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(@NotNull SQFCommandParser.CommandContext ctx);
}