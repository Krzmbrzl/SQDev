package raven.sqdev.interfaces;

import java.util.List;

import org.eclipse.jface.text.Position;

import raven.sqdev.infoCollection.base.Variable;

/**
 * An interface describing an object that can hold the informationa and results
 * of the parsing process of a SQF file
 * 
 * @author Raven
 *
 */
public interface ISQFParseResult {
	
	/**
	 * Sets the variables that have been declared during the parsing
	 * 
	 * @param localVariables
	 *            The list of local variables
	 * @param globalVariables
	 *            The list of declared global variables
	 */
	public void setVariables(List<Variable> localVariables,
			List<Variable> globalVariables);
	
	/**
	 * Adds a new Marker
	 * 
	 * @param type
	 *            The marker's type
	 * @param offset
	 *            The marker's offset
	 * @param length
	 *            The length of the affected area
	 * @param message
	 *            The marker's message
	 * @param severity
	 *            The marker's severity
	 */
	public void addMarker(String type, int offset, int length, String message,
			int severity);
	
	/**
	 * Adds a folding area
	 * 
	 * @param position
	 *            The position of the folding area
	 */
	public void addFoldingArea(Position position);
}
