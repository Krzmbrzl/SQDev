package raven.sqdev.parser.sqf;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.Position;

import raven.sqdev.infoCollection.base.Variable;
import raven.sqdev.parser.misc.ParseResult;

/**
 * A basic implementation of an {@link ISQFParseResult}
 * 
 * @author Raven
 *
 */
public class SQFParseResult extends ParseResult {
	
	/**
	 * A list of local variables declared during the parsing
	 */
	private List<Variable> declaredLocalVariables;
	
	/**
	 * A list of global variables declared during the parsing
	 */
	private List<Variable> declaredGlobalVariables;
	/**
	 * A list of folding areas
	 */
	private List<Position> foldingAreas;
	
	
	public SQFParseResult() {
		foldingAreas = new ArrayList<Position>();
	}
	
	/**
	 * Sets the found variables
	 * 
	 * @param localVariables
	 *            The list of found local variables
	 * @param globalVariables
	 *            The list of found global variables
	 */
	public void setVariables(List<Variable> localVariables,
			List<Variable> globalVariables) {
		
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
	
	public List<Variable> getDeclaredLocalVariables() {
		return declaredLocalVariables;
	}
	
	public List<Variable> getDeclaredGlobalVariables() {
		return declaredGlobalVariables;
	}
	
	public List<Position> getFoldingAreas() {
		return foldingAreas;
	}
	
}
