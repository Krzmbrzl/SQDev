package raven.sqdev.interfaces;

import java.util.Collection;
import java.util.Map;

import org.eclipse.jface.text.Position;

import raven.sqdev.infoCollection.base.Variable;
import raven.sqdev.misc.Marker;

/**
 * Interface for an object that holds the information about the result of
 * processing a given parse tree
 * 
 * @author Raven
 *
 */
public interface ITreeProcessingResult {

	/**
	 * Gets a collection of all areas that have been determined to be foldable
	 */
	public Collection<Position> getFoldableAreas();

	/**
	 * Gets the markers that have been produced during tree processing
	 */
	public Collection<Marker> getMarkers();

	/**
	 * Gets a map of all encountered global variable declarations
	 */
	public Map<String, Variable> getDeclaredGlobalVariables();

	/**
	 * Gets a map of all encountered local variable declarations
	 */
	public Map<String, Variable> getDeclaredLocalVariables();
}
