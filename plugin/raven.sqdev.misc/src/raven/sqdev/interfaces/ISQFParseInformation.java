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
	 * Gets the unary SQF operators
	 */
	public List<SQFCommand> getUnaryOperators();
	
	/**
	 * Gets the nular SQF operators
	 */
	public List<SQFCommand> getNularOperators();
	
	/**
	 * Gets the configured magic variables
	 */
	public List<Variable> getMagicVariables();
	
	/**
	 * Gets the configured macros
	 */
	public List<Macro> getMacros();
}
