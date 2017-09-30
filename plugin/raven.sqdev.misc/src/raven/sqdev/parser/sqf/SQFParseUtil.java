package raven.sqdev.parser.sqf;

import java.util.ArrayList;
import java.util.List;

import raven.sqdev.infoCollection.base.Variable;

/**
 * A collection of util methods for SQF parsing
 * 
 * @author Raven
 *
 */
public class SQFParseUtil {
	
	/**
	 * Gets the default magic variables that are present in SQF code
	 */
	public static final List<Variable> getDefaultMagicVars() {
		List<Variable> magicVars = new ArrayList<Variable>();
		magicVars.add(new Variable("_this",
				"This variable is available inside of functions and contains the parameters given to it."));
		magicVars.add(new Variable("_fnc_scriptName",
				"A String containing the function's name. Only awaylable when the function has "
						+ "been compiled via CfgFunctions."));
		magicVars.add(new Variable("_fnc_scriptNameParent",
				"A String containing the function's parent's name. Only awaylable when the function has "
						+ "been compiled via CfgFunctions."));
		magicVars.add(new Variable("_x",
				"References the current object oin the iteration. Available inside count or forEach loops"));
		magicVars.add(new Variable("_forEachIndex",
				"References the index of the current object in the iteration. Only available in a "
						+ "forEach loop."));
		magicVars.add(new Variable("_thisEventHandler",
				"References the current event handler. Only available inside an EventHandler"));
		
		return magicVars;
	}
	
}
