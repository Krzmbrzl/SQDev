package raven.sqdev.preferences.util;

/**
 * An interface for defing an <code>ISQDevPreferenceEditorListener</code> that
 * will get notified about every change int the value of the respective editor
 * 
 * @author Raven
 *		
 */
public interface ISQDevPreferenceEditorListener {
	/**
	 * Gets notified after an editor has changed his state
	 */
	public void editorStateChanged(SQDevChangeEvent event);
}
