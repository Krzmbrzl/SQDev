// Generated from C:\Users\Robert Adam\Documents\Antlr\SQFCommand.g4 by ANTLR 4.1
package SQFBaseParser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SQFCommandParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SQFCommandVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SQFCommandParser#commandName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommandName(@NotNull SQFCommandParser.CommandNameContext ctx);

	/**
	 * Visit a parse tree produced by {@link SQFCommandParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(@NotNull SQFCommandParser.ParameterContext ctx);

	/**
	 * Visit a parse tree produced by {@link SQFCommandParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(@NotNull SQFCommandParser.StartContext ctx);

	/**
	 * Visit a parse tree produced by {@link SQFCommandParser#alternative}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlternative(@NotNull SQFCommandParser.AlternativeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SQFCommandParser#commandSyntax}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommandSyntax(@NotNull SQFCommandParser.CommandSyntaxContext ctx);

	/**
	 * Visit a parse tree produced by {@link SQFCommandParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(@NotNull SQFCommandParser.TypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SQFCommandParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommand(@NotNull SQFCommandParser.CommandContext ctx);
}