package raven.sqdev.editors;

import java.util.ArrayList;

import raven.sqdev.exceptions.IllegalModificationException;

/**
 * This class can queue <code>IEditorKeyHandler</code> for later assignment to
 * an <code>EditorKeyEventManager</code>. <br>
 * The handlers will get assigned right after the <code>SourceViewer</code> is
 * created.<br>
 * <br>
 * After the <code>SourceViewer</code> has been created any handler that gets
 * queued to this class will immediately be assigend
 * 
 * @author Raven
 * 		
 */
public class EditorKeyEventQueue {
	
	private ArrayList<IEditorKeyHandler> queuedHandler;
	
	private EditorKeyEventManager manager;
	
	public EditorKeyEventQueue() {
		setQueuedHandler(new ArrayList<IEditorKeyHandler>());
	}
	
	public ArrayList<IEditorKeyHandler> getQueuedHandler() {
		return queuedHandler;
	}
	
	public void setQueuedHandler(ArrayList<IEditorKeyHandler> queuedHandler) {
		this.queuedHandler = queuedHandler;
	}
	
	/**
	 * Queues an <code>IEditorKeyHandler</code> for later implementation<br>
	 * If the corresponding <code>EditorKeyEventManager</code> has already been
	 * created the handler will be directly forwarded.
	 * 
	 * @param handler
	 */
	public void queueEditorKeyHandler(IEditorKeyHandler handler) {
		if (getManager() == null) {
			// if the manager has not yet been set add the handler to the queue
			getQueuedHandler().add(handler);
		} else {
			// if the manager has been set register the event directly to the manager
			getManager().addEditorKeyHandler(handler);
		}
	}
	
	public EditorKeyEventManager getManager() {
		return manager;
	}
	
	/**
	 * Sets the <code>EditorKeyEventhandler</code> for this <code>EditorKeyEventQueue</code><br>
	 * Afterwards it will forward all events that are currently queued to the new manager<br>
	 * <br>
	 * If the manager has already been set it <b>mustn't be changed</b>
	 * @param manager
	 */
	public void setManager(EditorKeyEventManager manager) {
		if (getManager() != null) {
			try {
				throw new IllegalModificationException("Can't change the manager of"
						+" EditorKeyEventQueue after it has been already set");
			} catch (IllegalModificationException e) {
				e.printStackTrace();
			}
		}else {
			this.manager = manager;
			
			//forward all queued handlers to the manager
			for(IEditorKeyHandler currentHandler : getQueuedHandler()) {
				getManager().addEditorKeyHandler(currentHandler);
			}
		}
	}
	
}
