package raven.sqdev.interfaces;

import raven.sqdev.misc.VersionChangeEvent;

/**
 * An interface describing a VersionListener that will be notified when
 * something with the versions within the SQDev feature happens
 * 
 * @author Raven
 * 		
 */
public interface IVersionListener {
	
	/**
	 * This method gets called whenever a change in the version number occured
	 * 
	 * @param event
	 *            The <code>VersionChangeEvent</code> describing this change in
	 *            more detail
	 */
	public void versionChanged(VersionChangeEvent event);
}
