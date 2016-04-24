package raven.sqdev.misc;

import org.eclipse.core.runtime.Assert;
import org.osgi.framework.Version;

import raven.sqdev.constants.ESQDevPlugin;

/**
 * A <code>VersionChangeEvent</code> that contains information about the change
 * 
 * @author Raven
 * 		
 */
public class VersionChangeEvent {
	
	/**
	 * The old version
	 */
	private Version oldVersion;
	/**
	 * The new version
	 */
	private Version newVersion;
	/**
	 * The ID of the plugin whose version has changed
	 */
	private ESQDevPlugin plugin;
	
	
	public VersionChangeEvent(ESQDevPlugin plugin, Version oldVersion, Version newVersion) {
		Assert.isTrue(plugin != null && oldVersion != null && newVersion != null,
				"Null arguments in VersionChangeEvent");
				
		this.plugin = plugin;
		this.oldVersion = oldVersion;
		this.newVersion = newVersion;
	}
	
	/**
	 * Gets the plugin this change occured on
	 */
	public ESQDevPlugin getPlugin() {
		return plugin;
	}
	
	/**
	 * Gets the old version of the plugin (The version before this change)
	 */
	public Version getOldVersion() {
		return oldVersion;
	}
	
	/**
	 * Gets the new version of the plugin (The version after this change)
	 */
	public Version getNewVersion() {
		return newVersion;
	}
	
	/**
	 * Checks whether this version change is an update (The old version is less
	 * tha the new version)
	 */
	public boolean isUpdate() {
		return oldVersion.compareTo(newVersion) < 0;
	}
}
