package raven.sqdev.editors;

import org.eclipse.swt.events.VerifyEvent;

/**
 * Interface for an EditorKeyHandler that provides a method to check if the
 * respective handler will handle the given event and a method to actually do so
 * 
 * @author Raven
 * 		
 */
public interface IEditorKeyHandler {
	
	/**
	 * Checks if the respective class will handle the respective KeyEvent
	 * 
	 * @param event
	 *            The fired KeyEvent
	 * @return
	 */
	public boolean willHandle(VerifyEvent event);
	
	
	/**
	 * Handles the given KeyEvent (not if it's a deletion)
	 * 
	 * @param event
	 */
	public void handleAddition(VerifyEvent event);
	
	/**
	 * Handles the given deletion
	 * 
	 * @param event
	 */
	public void handleDeletion(VerifyEvent event);
	
	/**
	 * Checks if the typed character will trigger a deletion
	 * @param c The typed character
	 * @return
	 */
	public static boolean isDeletion(char c) {
		if (c == '\b') {
			return true;
		}else {
			return false;
		}
	}
	
}
