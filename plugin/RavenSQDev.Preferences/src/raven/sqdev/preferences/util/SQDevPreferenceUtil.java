package raven.sqdev.preferences.util;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.framework.Bundle;

/**
 * This class provides functions for dealing with SQDev preferences
 * 
 * @author Raven
 *		
 */
public class SQDevPreferenceUtil {
	
	/**
	 * Gets the Bundle of the <code>raven.sqdev.preferences</code> plugin
	 * 
	 * @return
	 */
	public static Bundle getPreferenceBundle() {
		return Platform.getBundle("raven.sqdev.preferences");
	}
	
	/**
	 * Gets the preferences for the SQDev plugin
	 * @return
	 */
	public static IEclipsePreferences getPreferences() {
		IEclipsePreferences preferences = InstanceScope.INSTANCE
				.getNode("raven.sqdev.preferences");
		
		//check that preferences have been found
		Assert.isNotNull(preferences, "The requested preference can not be resolved!");
		
		return preferences;
	}
	
}
