package raven.sqdev.misc;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;

import raven.sqdev.interfaces.IUpdateListener;

/**
 * A class providing all necessary methods for an update-listener framework
 * 
 * @author Ravem
 *
 */
public class UpdateReporter {
	
	/**
	 * A list of all registered <code>IUpdateListener</code>
	 */
	protected List<IUpdateListener> updateListenerList;
	
	protected boolean allowDuplicates;
	
	/**
	 * Initializes the listener framework
	 * 
	 * @param allowDuplicates
	 *            Indicates whether listener duplicates should be allowed
	 */
	public UpdateReporter(boolean allowDuplicates) {
		updateListenerList = new ArrayList<IUpdateListener>();
	}
	
	/**
	 * Adds the given update listener. If it is a duplicate of a listener that
	 * is already added it might not get added
	 * 
	 * @param listener
	 *            The <code>IUpdateListener</code> to add
	 */
	public void addUpdateListener(IUpdateListener listener) {
		Assert.isNotNull(listener);
		
		if (allowDuplicates || !updateListenerList.contains(listener)) {
			updateListenerList.add(listener);
		}
	}
	
	/**
	 * Removes the given update listener
	 * 
	 * @param listener
	 *            The <code>IUpdateListener</code> to remove
	 */
	public void removeUpdateListener(IUpdateListener listener) {
		updateListenerList.remove(listener);
	}
	
	/**
	 * Removes all registered <code>IUpdateListener</code>
	 */
	public void removeAllUpdateListener() {
		updateListenerList.clear();
	}
	
	/**
	 * Notifies all registered <code>IUpdateListener</code>
	 */
	protected void notifyUpdateListener() {
		for (IUpdateListener listener : updateListenerList) {
			listener.updated();
		}
	}
	
}
