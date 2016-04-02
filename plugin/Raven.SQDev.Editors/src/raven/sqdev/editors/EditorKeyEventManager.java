package raven.sqdev.editors;

import java.util.ArrayList;

import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.VerifyEvent;

import raven.sqdev.interfaces.IEditorKeyHandler;

/**
 * This class provides management for KeyEvents. <br>
 * It will ask every registered handler if it wants to handle the current event
 * and if so it executes the respective method and afterwards continues with the
 * next one
 * 
 * @author Raven
 * 		
 */
public class EditorKeyEventManager implements VerifyKeyListener {
	// TODO: add the SourceView to a local variable for later access
	private ArrayList<IEditorKeyHandler> handlerlist;
	
	public EditorKeyEventManager() {
		this.setHandlerlist(new ArrayList<IEditorKeyHandler>());
	}
	
	@Override
	public void verifyKey(VerifyEvent event) {
		for (IEditorKeyHandler currentHandler : getHandlerlist()) {
			// check if this handler wants to handle the event
			if (currentHandler.willHandle(event)) {
				// let the handler handle the event
				if (IEditorKeyHandler.isDeletion(event.character)) {
					currentHandler.handleDeletion(event);
				} else {
					currentHandler.handleAddition(event);
				}
			}
		}
	}
	
	public ArrayList<IEditorKeyHandler> getHandlerlist() {
		return this.handlerlist;
	}
	
	public void setHandlerlist(ArrayList<IEditorKeyHandler> handlerlist) {
		this.handlerlist = handlerlist;
	}
	
	/**
	 * Adds an <code>IEditorKeyHandler</code> to this manager<br>
	 * <br>
	 * If the given handler is already registered nothing happens
	 * 
	 * @param handler
	 *            The handler to be added
	 */
	public void addEditorKeyHandler(IEditorKeyHandler handler) {
		if (!getHandlerlist().contains(handler)) {
			getHandlerlist().add(handler);
		}
	}
	
	/**
	 * Removes the given <code>IEditorKeyHandler</code> from this manager
	 * 
	 * @param handler
	 *            The handler to remove
	 */
	public void removeEditorKeyHandler(IEditorKeyHandler handler) {
		getHandlerlist().remove(handler);
	}
	
}
