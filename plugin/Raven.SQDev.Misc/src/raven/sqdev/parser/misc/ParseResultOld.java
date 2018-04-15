package raven.sqdev.parser.misc;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import dataStructures.IBuildableIndexTree;
import dataStructures.IToken;
import dataStructures.ITokenSource;
import raven.sqdev.interfaces.IMarkerSupport;
import raven.sqdev.interfaces.IParseResult;
import raven.sqdev.misc.Marker;

@Deprecated
public class ParseResultOld implements IMarkerSupport, IParseResult {

	/**
	 * The list of reported markers
	 */
	private List<Marker> markers;
	/**
	 * The actual parse-tree
	 */
	private ParseTree tree;


	public ParseResultOld() {
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
	public void addMarker(String type, int offset, int length, String message, int severity) {
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
			support.createMarker(currentMarker.getType(), currentMarker.getOffset(),
					currentMarker.getLength(), currentMarker.getMessage(), currentMarker.getSeverity());
		}
	}

	/**
	 * Merges the given parse result into this one.<br>
	 * 
	 * <b>NOTE:</b> If both results have a parseTree set, then the one of this
	 * parseResult will be kept!
	 * 
	 * @param other
	 *            The other parse result to merge into this one
	 */
	public void mergeWith(ParseResultOld other) {
		other.merging();

		markers.addAll(other.getMarkers());

		if (tree == null) {
			tree = other.getParseTree();
		}
	}

	/**
	 * Dummy method used for compability reasons. Reroutes all calls to
	 * {@link #addMarker(String, int, int, String, int)}
	 */
	@Override
	public void createMarker(String type, int offset, int length, String message, int severity) {
		// dummy method for compability
		addMarker(type, offset, length, message, severity);
	}

	/**
	 * Sets the parseTree of this result
	 * 
	 * @param tree
	 *            The ParseTree representing the parsed input
	 */
	public void setParseTree(ParseTree tree) {
		this.tree = tree;
	}

	/**
	 * Clears the currently stored parseTree
	 */
	public void clearParseTree() {
		tree = null;
	}

	/**
	 * Checks whether this result does provide a parseTree
	 */
	public final boolean providesParseTree() {
		return tree != null;
	}

	/**
	 * Gets the parseTree representing the parsed input. May be <code>null</code>
	 */
	public ParseTree getParseTree() {
		return tree;
	}

	/**
	 * A hook that gets called if this object is being merged into another one
	 */
	protected void merging() {
		// Do nothing
	}

	/**
	 * Gets a list of marker types that have been reported
	 */
	public List<String> getMarkerTypes() {
		List<String> names = new ArrayList<String>();

		for (Marker current : getMarkers()) {
			if (!names.contains(current.getType())) {
				names.add(current.getType());
			}
		}

		return names;
	}

	@Override
	@Deprecated
	public IBuildableIndexTree getTree() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public ITokenSource<? extends IToken> getTokenBuffer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParseTree getANTRLParseTree() {
		// TODO Auto-generated method stub
		return tree;
	}

	@Override
	@Deprecated
	public int getLine(int offset) {
		// TODO Auto-generated method stub
		return 0;
	}
}
