package raven.sqdev.parser.preprocessor;

import java.util.ArrayList;
import java.util.List;

import raven.sqdev.misc.Macro;
import raven.sqdev.parser.misc.ParseResult;

public class PreprocessorParseResult extends ParseResult {

	/**
	 * The list of defined macros
	 */
	private List<Macro> macros;

	/**
	 * Creates a new instance of this class
	 */
	public PreprocessorParseResult() {
		macros = new ArrayList<Macro>();
	}


	/**
	 * Merges the given parse result into this one
	 * 
	 * @param other
	 *            The other parse result to merge into this one
	 */
	public void mergeWith(PreprocessorParseResult other) {
		super.mergeWith(other);

		macros.addAll(other.getMacros());
	}

	/**
	 * Adds the given Macro
	 * 
	 * @param macro
	 *            The Macro to add
	 */
	public void addMacro(Macro macro) {
		macros.add(macro);
	}

	/**
	 * Gets all defined macros
	 */
	public List<Macro> getMacros() {
		return macros;
	}
}
