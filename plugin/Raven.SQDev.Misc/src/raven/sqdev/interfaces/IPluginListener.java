package raven.sqdev.interfaces;

import raven.sqdev.constants.ESQDevPlugin;

/**
 * An interface describing a plugin listener that will handle events occuring to
 * plugins
 * 
 * @author Raven
 * 		
 */
public interface IPluginListener {
	
	/**
	 * Gets called whenever a plugin starts
	 * 
	 * @param plugin
	 *            The started plugin
	 */
	public void started(ESQDevPlugin plugin);
	
	/**
	 * Gets called whenever a plugin stops
	 * 
	 * @param plugin
	 *            The stopped plugin
	 */
	public void stopped(ESQDevPlugin plugin);
}
