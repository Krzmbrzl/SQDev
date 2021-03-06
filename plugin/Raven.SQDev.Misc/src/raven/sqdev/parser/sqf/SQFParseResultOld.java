package raven.sqdev.parser.sqf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.eclipse.jface.text.Position;

import raven.sqdev.infoCollection.base.Variable;
import raven.sqdev.parser.misc.ParseResultOld;

/**
 * A basic implementation of an {@link ISQFParseResult}
 * 
 * @author Raven
 *
 */
@Deprecated
public class SQFParseResultOld extends ParseResultOld {

	/**
	 * A list of local variables declared during the parsing
	 */
	private Map<String, Variable> declaredLocalVariables;

	/**
	 * A list of global variables declared during the parsing
	 */
	private Map<String, Variable> declaredGlobalVariables;
	/**
	 * A list of folding areas
	 */
	private List<Position> foldingAreas;
	/**
	 * A list containing the parser-rule names
	 */
	private List<String> parserRuleNames;
	/**
	 * The TokenStream of the parsing
	 */
	private BufferedTokenStream tokenStream;


	public SQFParseResultOld() {
		foldingAreas = new ArrayList<Position>();
		declaredLocalVariables = new HashMap<String, Variable>();
		declaredGlobalVariables = new HashMap<String, Variable>();
	}

	/**
	 * Sets the found variables
	 * 
	 * @param localVariables
	 *            The list of found local variables
	 * @param globalVariables
	 *            The list of found global variables
	 */
	public void setVariables(Map<String, Variable> localVariables, Map<String, Variable> globalVariables) {

		declaredLocalVariables = localVariables;
		declaredGlobalVariables = globalVariables;
	}

	/**
	 * Adds a folding area
	 * 
	 * @param position
	 *            The position describing the location that should be foldable
	 */
	public void addFoldingArea(Position position) {
		foldingAreas.add(position);
	}

	/**
	 * Gets a list of found local variable declarations
	 */
	public Map<String, Variable> getDeclaredLocalVariables() {
		return declaredLocalVariables;
	}

	/**
	 * Gets a list of found global variable declarations
	 */
	public Map<String, Variable> getDeclaredGlobalVariables() {
		return declaredGlobalVariables;
	}

	/**
	 * Gets a list of found folding areas
	 */
	public List<Position> getFoldingAreas() {
		return foldingAreas;
	}

	/**
	 * Sets the names of the parser rules
	 * 
	 * @param names
	 *            The list of names
	 */
	public void setParserRuleNames(List<String> names) {
		parserRuleNames = names;
	}

	/**
	 * Gets a list of parser-rule names. May be <code>null</code>
	 */
	public List<String> getParserRulenames() {
		return parserRuleNames;
	}

	/**
	 * Checks whether this result provides parser rule names
	 */
	public boolean providesParserRuleNames() {
		return parserRuleNames != null;
	}

	/**
	 * Sets the tokenStream
	 * 
	 * @param stream
	 *            The respective stream object
	 */
	public void setTokenStream(BufferedTokenStream stream) {
		this.tokenStream = stream;
	}

	/**
	 * Gets the used TokenStream. May be <code>null</code>.
	 */
	public BufferedTokenStream getTokenStream() {
		return tokenStream;
	}

	/**
	 * Checks if this result provides a TokenStream
	 */
	public boolean providesTokenStream() {
		return tokenStream != null;
	}

	/**
	 * Merges the given SQFParseResult with this one. Note that fields like
	 * {@link #parserRuleNames} and {@link #tokenStream} will not be overwritten
	 * during that process (unless they are null).
	 * 
	 * If you want to only merge the reported markers then cast the argument to a
	 * {@link SQFParseResultOld}}
	 * 
	 * @param other
	 *            The SQFParseResult to merge into this one
	 */
	public void mergeWith(SQFParseResultOld other) {
		super.mergeWith(other);

		foldingAreas.addAll(other.getFoldingAreas());
		declaredLocalVariables.putAll(other.getDeclaredLocalVariables());
		declaredGlobalVariables.putAll(other.getDeclaredGlobalVariables());

		if (parserRuleNames == null) {
			parserRuleNames = other.getParserRulenames();
		}
		if (tokenStream == null) {
			tokenStream = other.getTokenStream();
		}
	}

}
