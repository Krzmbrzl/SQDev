package raven.sqdev.parser.misc;

import java.util.ArrayList;
import java.util.List;

import raven.sqdev.interfaces.IMarkerSupport;
import raven.sqdev.misc.Marker;

public class ParseResult implements IMarkerSupport {
	
	/**
	 * The list of reported markers
	 */
	private List<Marker> markers;
	
	
	public ParseResult() {
		markers = new ArrayList<Marker>();
	}
	
	/**
	 * Adds a new marker to this parse result
	 * 
	 * @param type
	 *            The marker's type
	 * @param offset
	 *            The marker's offset
	 * @param length
	 *            The marker's length
	 * @param message
	 *            The marker's message
	 * @param severity
	 *            The marker's severity
	 */
	public void addMarker(String type, int offset, int length, String message,
			int severity) {
		markers.add(new Marker(type, offset, length, message, severity));
	}
	
	/**
	 * Gets all the markers that are a result of the respective parsing
	 */
	public List<Marker> getMarkers() {
		return markers;
	}
	
	/**
	 * Applies the respective markers to the given MarkerSupport
	 * 
	 * @param support
	 *            The MarkerSupport to add the markers to
	 */
	public void applyMarkersTo(IMarkerSupport support) {
		for (Marker currentMarker : markers) {
			support.createMarker(currentMarker.getType(),
					currentMarker.getOffset(), currentMarker.getLength(),
					currentMarker.getMessage(), currentMarker.getSeverity());
		}
	}
	
	/**
	 * Merges the given parse result into this one
	 * 
	 * @param other
	 *            The other parse result to merge into this one
	 */
	public void mergeWith(ParseResult other) {
		markers.addAll(other.getMarkers());
	}
	
	/**
	 * Dummy method used for compability reasons. Reroutes all calls to
	 * {@link #addMarker(String, int, int, String, int)}
	 */
	@Override
	public void createMarker(String type, int offset, int length,
			String message, int severity) {
		// dummy method for compability
		addMarker(type, offset, length, message, severity);
	}
}
