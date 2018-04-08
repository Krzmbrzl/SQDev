package raven.sqdev.parser.misc;

import org.eclipse.core.resources.IMarker;

import dataStructures.IErrorListener;
import dataStructures.SQFToken;
import raven.sqdev.interfaces.IMarkerSupport;

public class SQFLexAndParseListener implements IErrorListener {

	/**
	 * The parse result to add the errors to
	 */
	protected IMarkerSupport markerStorage;

	public SQFLexAndParseListener(IMarkerSupport markerStorage) {
		assert (markerStorage != null);

		this.markerStorage = markerStorage;
	}

	public SQFLexAndParseListener() {

	}

	@Override
	public void error(String msg, SQFToken token) {
		if (markerStorage == null) {
			throw new IllegalStateException(
					"Marker storage may not be null. Has to be set via setMarkerStorage() before calling this method!");
		}
		markerStorage.createMarker(IMarker.PROBLEM, token.start(), token.length(), msg, IMarker.SEVERITY_ERROR);
	}

	/**
	 * Gets the parse result
	 */
	public IMarkerSupport getMarkerStorage() {
		return markerStorage;
	}

	/**
	 * Sets the parse result
	 * 
	 * @param storage
	 *            The {@linkplain IMarkerSupport} to report errors to
	 */
	public void setMarkerStorage(IMarkerSupport storage) {
		this.markerStorage = storage;
	}

}
