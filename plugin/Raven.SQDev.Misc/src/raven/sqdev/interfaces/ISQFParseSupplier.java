package raven.sqdev.interfaces;

import java.util.Map;

import dataStructures.AbstractSQFTokenFactory;
import raven.sqdev.misc.Macro;

/**
 * An interface describing an object that supplies the necessary information and
 * infrastructure for setting up a SQF lexer and parser
 * 
 * @author Raven
 *
 */
public interface ISQFParseSupplier {
	/**
	 * Gets the map of defined macros
	 */
	public Map<String, Macro> getMacros();

	/**
	 * Gets the {@linkplain AbstractSQFTokenFactory} that should be used in order to
	 * create the tokens in the lexer
	 */
	public AbstractSQFTokenFactory getTokenFactory();
}
