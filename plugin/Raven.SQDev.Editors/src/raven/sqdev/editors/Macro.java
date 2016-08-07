package raven.sqdev.editors;

import raven.sqdev.infoCollection.base.Keyword;

/**
 * A class representing a defined macro expression
 * 
 * @author Raven
 *
 */
public class Macro extends Keyword {
	
	protected int arguments;
	
	/**
	 * Creates a new macro that has no arguments
	 * 
	 * @param name
	 *            The name of the macro
	 */
	public Macro(String name) {
		super(name);
		arguments = 0;
	}
	
	/**
	 * Creates a new macro
	 * 
	 * @param name
	 *            The name of the macro
	 * @param arguments
	 *            The number of arguments this macro can take
	 */
	public Macro(String name, int arguments) {
		super(name);
		
		this.arguments = arguments;
	}
	
	/**
	 * Gets the number of arguments this macro takes
	 */
	public int getArgumentCount() {
		return arguments;
	}
	
	/**
	 * Checks whether this macro uses any arguments
	 */
	public boolean hasArguments() {
		return getArgumentCount() != 0;
	}
	
}
