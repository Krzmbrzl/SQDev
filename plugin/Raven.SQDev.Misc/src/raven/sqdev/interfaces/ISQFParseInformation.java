package raven.sqdev.interfaces;

import java.util.List;

import raven.sqdev.infoCollection.base.SQFCommand;
import raven.sqdev.infoCollection.base.Variable;
import raven.sqdev.misc.Macro;

/**
 * An interface describing an object that can provide the needed information for
 * the parsing of a SQF file
 * 
 * @author Raven
 *
 */
public interface ISQFParseInformation {

	/**
	 * Gets the binary SQF operators
	 */
	public List<SQFCommand> getBinaryOperators();

	/**
	 * Gets the binary SQF operators keywords as string
	 */
	public List<String> getBinaryKeywords();

	/**
	 * Gets the unary SQF operators
	 */
	public List<SQFCommand> getUnaryOperators();

	/**
	 * Gets the unary SQF operators keywords as string
	 */
	public List<String> getUnaryKeywords();

	/**
	 * Gets the nular SQF operators
	 */
	public List<SQFCommand> getNularOperators();

	/**
	 * Gets the nular SQF operators keywords as string
	 */
	public List<String> getNularKeywords();

	/**
	 * Gets the configured magic variables
	 */
	public List<Variable> getMagicVariables();

	/**
	 * Gets the configured magic variable-keywords as string
	 */
	public List<String> getMagicVariableNames();

	/**
	 * Gets the configured macros
	 */
	public List<Macro> getMacros();

	/**
	 * Gets the configured macro-keywords as string
	 */
	public List<String> getMacroNames();
}
