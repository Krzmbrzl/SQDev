package raven.sqdev.pluginManagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.Version;

import raven.sqdev.exceptions.SQDevCoreException;
import raven.sqdev.exceptions.SQDevException;
import raven.sqdev.exceptions.SQDevSyntaxException;
import raven.sqdev.interfaces.IVersionListener;
import raven.sqdev.misc.VersionChangeEvent;

/**
 * A manager dealing with the plugin versions inside the SQDev feature
 * 
 * @author Raven
 * 		
 */
public class VersionManager {
	
	/**
	 * A list of listeners
	 */
	private List<IVersionListener> listeners;
	
	/**
	 * The manager instance itself
	 */
	private static VersionManager manager;
	
	private VersionManager() {
		listeners = new ArrayList<IVersionListener>(0);
	}
	
	// TODO: check versions on start
	
	/**
	 * Gets the manager instance
	 */
	public static VersionManager getManager() {
		if (manager == null) {
			manager = new VersionManager();
		}
		
		return manager;
	}
	
	/**
	 * Registers an appropriate listener if it has not been before
	 * 
	 * @param listener
	 *            The Listener to register
	 */
	public void addVersionListener(IVersionListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}
	
	/**
	 * Removes the given listener
	 * 
	 * @param listener
	 *            The listener to remove
	 */
	public void removeVersionListener(IVersionListener listener) {
		listeners.remove(listener);
	}
	
	/**
	 * Checks whether the current plugin versions are the same as listed in the
	 * respective resource and if not it will update them after notifying the
	 * <code>IVersionListeners</code> that a new version of a plugin has started
	 */
	public void checkVersions() {
		ResourceManager manager = ResourceManager.getManager();
		
		String content = manager.getResourceContent(ResourceManager.VERSION_RESOURCE);
		
		String versionContent = "";
		boolean versionHasChanged = false;
		
		if (content.isEmpty()) {
			// versions of all plugins have changed
			versionHasChanged = true;
			
			for (ESQDevPlugin current : ESQDevPlugin.values()) {
				// assume that the fictional version 0.0.0 was the old version
				versionChanged(current, new Version(0, 0, 0), current.getVersion());
				
				versionContent += current.getID() + ": " + current.getVersion() + "\n";
			}
		} else {
			for (String currentLine : content.split("\n")) {
				currentLine = currentLine.trim();
				
				if (!currentLine.contains(":")) {
					// the colon has to be present
					throw new SQDevSyntaxException(
							"Wrong syntax in resource: " + ResourceManager.VERSION_RESOURCE);
				}
				
				String id = currentLine.substring(0, currentLine.indexOf(":"));
				Version storedVersion = Version
						.valueOf(currentLine.substring(currentLine.indexOf(":") + 1));
				ESQDevPlugin plugin = ESQDevPlugin.resolve(id);
				
				if (storedVersion == null || plugin == null) {
					// these values have to be resolvable
					throw new SQDevSyntaxException(
							"Wrong syntax in resource: " + ResourceManager.VERSION_RESOURCE);
				}
				
				if (storedVersion.compareTo(plugin.getVersion()) != 0) {
					// version has changed
					versionChanged(plugin, storedVersion, plugin.getVersion());
					
					versionHasChanged = true;
				}
			}
		}
		
		// store current versions
		if (versionHasChanged) {
			if (versionContent.isEmpty()) {
				// gather new versions
				for (ESQDevPlugin current : ESQDevPlugin.values()) {
					versionContent += current.getID() + ": " + current.getVersion() + "\n";
				}
			}
			// update the versions file
			try {
				manager.updateResource(ResourceManager.VERSION_RESOURCE, versionContent);
			} catch (IOException | SQDevException e) {
				throw new SQDevCoreException("Couldn't update version list!", e);
			}
		}
	}
	
	/**
	 * Notifies the listeners that a version has changed
	 * 
	 * @param plugin
	 *            The <code>ESQDevPlugin</code> this change occured on
	 * @param oldVersion
	 *            The old version
	 * @param newVersion
	 *            The new version
	 */
	private void versionChanged(ESQDevPlugin plugin, Version oldVersion, Version newVersion) {
		for (IVersionListener currentListener : listeners) {
			currentListener.versionChanged(new VersionChangeEvent(plugin, oldVersion, newVersion));
		}
	}
}
