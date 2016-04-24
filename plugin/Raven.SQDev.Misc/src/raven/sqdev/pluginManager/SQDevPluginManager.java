package raven.sqdev.pluginManager;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

import raven.sqdev.constants.ESQDevPlugin;
import raven.sqdev.exceptions.SQDevCoreException;
import raven.sqdev.exceptions.SQDevException;
import raven.sqdev.interfaces.IPluginListener;

/**
 * An manager for all running SQDev plugins
 * 
 * @author Raven
 * 		
 */
public class SQDevPluginManager {
	
	/**
	 * The manager instance
	 */
	protected static SQDevPluginManager manager;
	
	/**
	 * All plugin listeners
	 */
	private List<IPluginListener> pluginListeners;
	
	/**
	 * The list of all registered plugins
	 */
	protected ArrayList<AbstractUIPlugin> pluginList;
	
	/**
	 * Creates an <code>SQDevPluginManager</code> instance. It's not meant to be
	 * done manually
	 */
	private SQDevPluginManager() {
		pluginList = new ArrayList<AbstractUIPlugin>();
		pluginListeners = new ArrayList<IPluginListener>(0);
	}
	
	/**
	 * Gets the PluginManager holding all the references to running SQDevPlugins
	 * 
	 * @return The <code>SQDevPLuginManager<code>
	 */
	public static SQDevPluginManager getManager() {
		if (manager == null) {
			manager = new SQDevPluginManager();
		}
		
		return manager;
	}
	
	/**
	 * Checks if the given plugin is currently registered in this manager
	 * 
	 * @param plugin
	 *            The plugin to lok for
	 * @return
	 */
	public boolean contains(AbstractUIPlugin plugin) {
		return pluginList.contains(plugin);
	}
	
	/**
	 * Checks if the given plugin is currently registered in this manager
	 * 
	 * @param pluginName
	 *            The name (ID) of the plugin to searchs
	 * @return
	 */
	public boolean contains(String pluginName) {
		for (AbstractUIPlugin currentPlugin : pluginList) {
			if (currentPlugin.getBundle().getSymbolicName().toLowerCase()
					.equals(pluginName.toLowerCase())) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Gets the plugin with the given name (ID). If no plugin is registered and
	 * the name starts with "raven.sqdev" it will try to find and strat this
	 * plugin
	 * 
	 * @param pluginName
	 *            The name (ID) of the plugin to searchs
	 * @return The desired plugin or <code>null</code> if none is found
	 */
	public AbstractUIPlugin get(String pluginName) {
		// search by name
		for (AbstractUIPlugin currentPlugin : pluginList) {
			if (currentPlugin.getBundle().getSymbolicName().toLowerCase()
					.equals(pluginName.toLowerCase())) {
				return currentPlugin;
			}
		}
		
		if (pluginName.toLowerCase().startsWith("raven.sqdev")) {
			Bundle bundle = Platform.getBundle(pluginName);
			
			if (bundle != null) {
				try {
					int prevCount = pluginList.size();
					
					// try to start the bundle
					bundle.start();
					
					if (pluginList.size() > prevCount) {
						// if the count of registered plugins changed try to
						// fins it again
						return get(pluginName);
					} else {
						throw new SQDevException("The started plugin \"" + pluginName
								+ "\" has not registered to the SQDevPluginManage!");
					}
				} catch (BundleException | SQDevException e) {
					e.printStackTrace();
					
					throw new SQDevCoreException(e);
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Registers the given plugin if it's not already registered
	 * 
	 * @param plugin
	 */
	public void register(AbstractUIPlugin plugin) {
		Assert.isNotNull(plugin);
		
		if (!pluginList.contains(plugin)) {
			pluginList.add(plugin);
		}
		
		// notify listeners
		for (IPluginListener current : pluginListeners) {
			current.started(ESQDevPlugin.resolve(plugin.getBundle().getSymbolicName()));
		}
	}
	
	/**
	 * Unregisters the given plugin from this manager
	 * 
	 * @param plugin
	 *            The plugin to unregister
	 */
	public void unregister(AbstractUIPlugin plugin) {
		pluginList.remove(plugin);
		
		// notify listeners
		for (IPluginListener current : pluginListeners) {
			current.stopped(ESQDevPlugin.resolve(plugin.getBundle().getSymbolicName()));
		}
	}
	
	/**
	 * Adds a plugin listener if it has not been added before
	 * 
	 * @param listener
	 *            The listener to add
	 */
	public void addPluginListener(IPluginListener listener) {
		if (!pluginListeners.contains(listener)) {
			pluginListeners.add(listener);
		}
	}
	
	/**
	 * Removes the given plugin listener
	 * 
	 * @param listener
	 *            The listener to remove
	 */
	public void removePluginListener(IPluginListener listener) {
		pluginListeners.remove(listener);
	}
}
