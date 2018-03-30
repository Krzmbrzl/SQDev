package raven.sqdev.parser.misc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.text.Position;

import raven.sqdev.infoCollection.base.Variable;
import raven.sqdev.interfaces.IMarkerSupport;
import raven.sqdev.interfaces.ITreeProcessingResult;
import raven.sqdev.misc.Marker;

public class TreeProcessingResult implements ITreeProcessingResult, IMarkerSupport {

	/**
	 * A collection of markers that have been created during tree-processing
	 */
	protected Collection<Marker> markers;
	/**
	 * A map containing all encountered local variables
	 */
	protected Map<String, Variable> localVariables;
	/**
	 * A map containing all encountered global variables
	 */
	protected Map<String, Variable> globalVariables;
	/**
	 * A collection holding all areas that have been determined to be foldable
	 */
	protected Collection<Position> foldableAreas;


	public TreeProcessingResult() {
		foldableAreas = new ArrayList<>();
		localVariables = new HashMap<>();
		globalVariables = new HashMap<>();
		markers = new ArrayList<>();
	}

	@Override
	public Collection<Position> getFoldableAreas() {
		return foldableAreas;
	}

	/**
	 * Adds an area that should be foldable
	 * 
	 * @param area
	 *            The area to add
	 */
	public void addFoldableArea(Position area) {
		assert (area != null);

		foldableAreas.add(area);
	}

	@Override
	public Collection<Marker> getMarkers() {
		return markers;
	}

	@Override
	public Map<String, Variable> getDeclaredGlobalVariables() {
		return globalVariables;
	}

	@Override
	public Map<String, Variable> getDeclaredLocalVariables() {
		return localVariables;
	}

	/**
	 * Sets the variables stored in this result
	 * 
	 * @param localVariables
	 *            The local variables to set
	 * @param globalVariables
	 *            The global variables to set
	 */
	public void setVariables(Map<String, Variable> localVariables, Map<String, Variable> globalVariables) {
		this.localVariables = localVariables;
		this.globalVariables = globalVariables;
	}

	@Override
	public void createMarker(String type, int offset, int length, String message, int severity) {
		if (markers == null) {
			markers = new ArrayList<Marker>();
		}
		markers.add(new Marker(type, offset, length, message, severity));
	}

	/**
	 * Sets the markers for this result. This method is intended for marker
	 * collections that are passed from through the line.
	 * 
	 * @param markers
	 *            The marker collection to contribute to
	 */
	public void setMarkers(Collection<Marker> markers) {
		assert (markers != null);

		this.markers = markers;
	}

}
