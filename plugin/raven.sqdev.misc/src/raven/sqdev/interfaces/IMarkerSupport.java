package raven.sqdev.interfaces;

/**
 * This interface describes an object thatr does support the creation of markers
 * 
 * @author Raven
 *
 */
public interface IMarkerSupport {
	
	/**
	 * Creates a problem marker that will be visible in the editor.<br>
	 * Note: Can only be called if {@link #getEditorInput()} does not return
	 * null and is of type {@linkplain IFileEditorInput}
	 * 
	 * @param type
	 *            The marker type
	 * @param offset
	 *            The offset of this marker's area
	 * @param length
	 *            The length of the marker area
	 * @param message
	 *            The message of this marker
	 * @param severity
	 *            The severity of the marker (has to be one specified by
	 *            <code>IMarker</code>)
	 */
	public void createMarker(String type, int offset, int length,
			String message, int severity);
}
