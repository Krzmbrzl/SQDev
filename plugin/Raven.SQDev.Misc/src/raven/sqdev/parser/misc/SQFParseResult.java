package raven.sqdev.parser.misc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import dataStructures.IBuildableIndexTree;
import dataStructures.ITokenSource;
import dataStructures.SQFToken;
import raven.sqdev.interfaces.IMarkerSupport;
import raven.sqdev.interfaces.IParseResult;
import raven.sqdev.misc.Marker;

public class SQFParseResult implements IParseResult, IMarkerSupport {

	/**
	 * The tree stored in this result
	 */
	protected IBuildableIndexTree tree;
	/**
	 * The token buffer holding the tokens corresponding to the stored tree
	 */
	protected ITokenSource<SQFToken> tokenBuffer;
	/**
	 * A collection of markers that have been created during parse-tree-creation
	 */
	protected Collection<Marker> markers;
	/**
	 * The ANTLR parse tree
	 */
	protected ParseTree antlrTree;
	/**
	 * A list of indices of newlines
	 */
	protected List<Integer> linieIndices;

	public SQFParseResult() {
		markers = new ArrayList<>();
	}

	@Override
	public IBuildableIndexTree getTree() {
		return tree;
	}

	/**
	 * Sets the tree for this instance
	 * 
	 * @param tree
	 *            The tree to store
	 */
	public void setTree(IBuildableIndexTree tree) {
		assert (tree != null);

		this.tree = tree;
	}

	@Override
	public ITokenSource<SQFToken> getTokenBuffer() {
		return tokenBuffer;
	}

	/**
	 * Sets the token buffer for this instance
	 * 
	 * @param buffer
	 *            The token buffer corresponding to the stored tree
	 */
	public void setTokenBuffer(ITokenSource<SQFToken> buffer) {
		assert (buffer != null);

		this.tokenBuffer = buffer;
	}

	@Override
	public Collection<Marker> getMarkers() {
		return markers;
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

	/**
	 * Resets all fields of this instance
	 */
	public void reset() {
		markers = null;
		tree = null;
		tokenBuffer = null;
	}

	@Override
	public ParseTree getANTRLParseTree() {
		return antlrTree;
	}

	public void setANTLRParseTree(ParseTree tree) {
		antlrTree = tree;
	}

	@Override
	public int getLine(int offset) {
		if (offset < 0) {
			throw new IllegalArgumentException("Offset may not be negative!");
		}

		for (int i = 0; i < linieIndices.size(); i++) {
			if (linieIndices.get(i) > offset) {
				return linieIndices.get(i - 1);
			}
		}

		return -1;
	}

	/**
	 * Sets the newline indices
	 * 
	 * @param lineIndices
	 *            The respective indices
	 */
	public void setLineIndices(List<Integer> lineIndices) {
		this.linieIndices = lineIndices;
	}

}
