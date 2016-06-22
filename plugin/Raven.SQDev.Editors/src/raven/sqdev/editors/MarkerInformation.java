package raven.sqdev.editors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import raven.sqdev.exceptions.SQDevEditorException;

/**
 * A container for all information necessary to create an IMarker from it
 * 
 * @author Raven
 *
 */
public class MarkerInformation {
	/**
	 * The line information of the potential marker
	 */
	protected int line;
	/**
	 * The offset information of the potential marker
	 */
	protected int offset;
	/**
	 * The length information of the potential marker
	 */
	protected int length;
	/**
	 * The line severity of the potential marker
	 */
	protected int severity;
	/**
	 * The type information of the potential marker
	 */
	protected String type;
	/**
	 * The message information of the potential marker
	 */
	protected String message;
	
	
	/**
	 * Creates a new, empty instance of this information. Use at your own risk
	 * as someneeded information may not be set to the point they are needed
	 */
	public MarkerInformation() {
	}
	
	/**
	 * Creates a new instance with the given information
	 * 
	 * @param type
	 *            The Marker type
	 * @param line
	 *            The line the marker should be associated with
	 * @param offset
	 *            The marker's offset
	 * @param length
	 *            The marker's length
	 * @param severity
	 *            The marker's severity
	 * @param message
	 *            The marker's message
	 */
	public MarkerInformation(String type, int line, int offset, int length, int severity,
			String message) {
		setType(type);
		setLine(line);
		setOffset(offset);
		setLength(length);
		setSeverity(severity);
		setMessage(message);
	}
	
	
	public int getLine() {
		return line;
	}
	
	public void setLine(int line) {
		this.line = line;
	}
	
	public int getOffset() {
		return offset;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	public int getSeverity() {
		return severity;
	}
	
	public void setSeverity(int severity) {
		this.severity = severity;
	}
	
	public String getType() {
		return (type == null) ? "" : type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getMessage() {
		return (message == null) ? "" : message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Applies this information to the given resource in form of an IMarker
	 * 
	 * @param resource
	 *            The resource ot create the marker on
	 * @return The created marker or <code>null</code> if the marker couldn't be
	 *         created
	 */
	public IMarker applyOn(IResource resource) {
		try {
			IMarker marker = resource.createMarker(getType());
			
			marker.setAttribute(IMarker.LINE_NUMBER, getLine());
			marker.setAttribute(IMarker.MESSAGE, getMessage());
			marker.setAttribute(IMarker.SEVERITY, getSeverity());
			marker.setAttribute(IMarker.CHAR_START, getOffset());
			marker.setAttribute(IMarker.CHAR_END, getOffset() + getLength());
			
			return marker;
		} catch (CoreException e) {
			try {
				throw new SQDevEditorException("Failed at creating marker", e);
			} catch (SQDevEditorException e1) {
				e1.printStackTrace();
				
				return null;
			}
		}
	}
	
}
