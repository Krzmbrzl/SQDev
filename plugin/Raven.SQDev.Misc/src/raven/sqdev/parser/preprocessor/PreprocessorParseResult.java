package raven.sqdev.parser.preprocessor;

import java.util.HashMap;
import java.util.Map;

import raven.sqdev.misc.Macro;
import raven.sqdev.parser.misc.ParseResult;

public class PreprocessorParseResult extends ParseResult {

	/**
	 * The list of defined macros
	 */
	private Map<String, Macro> macros;

	/**
	 * Creates a new instance of this class
	 */
	public PreprocessorParseResult() {
		macros = new HashMap<String, Macro>();
	}


	/**
	 * Merges the given parse result into this one
	 * 
	 * @param other
	 *            The other parse result to merge into this one
	 */
	public void mergeWith(PreprocessorParseResult other) {
		super.mergeWith(other);

		macros.putAll(other.getMacros());
	}

	/**
	 * Adds the given Macro
	 * 
	 * @param macro
	 *            The Macro to add
	 */
	public void addMacro(Macro macro) {
		macros.put(macro.getKeyword(), macro);
	}

	/**
	 * Gets all defined macros
	 */
	public Map<String, Macro> getMacros() {
		return macros;
	}
}
