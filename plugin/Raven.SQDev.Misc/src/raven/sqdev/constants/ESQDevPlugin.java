package raven.sqdev.constants;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;

/**
 * An enumeration for the various plugins used inside the SQDev feature.
 * 
 * @author Raven
 * 		
 */
public enum ESQDevPlugin {
	
	/**
	 * The editors plugin
	 */
	EDITORS {
		@Override
		public String getID() {
			return "raven.sqdev.editors";
		}
	},
	/**
	 * The misc plugin
	 */
	MISC {
		@Override
		public String getID() {
			return "raven.sqdev.misc";
		}
	},
	/**
	 * The sqfEditor plugin
	 */
	SQFEDITOR {
		@Override
		public String getID() {
			return "raven.sqdev.editors.sqfeditor";
		}
	},
	/**
	 * The util plugin
	 */
	UTIL {
		@Override
		public String getID() {
			return "raven.sqdev.util";
		}
	},
	/**
	 * The wizards plugin
	 */
	WIZARDS {
		@Override
		public String getID() {
			return "raven.sqdev.wizards";
		}
	},
	/**
	 * The preferences plugin
	 */
	PREFERENCES {
		@Override
		public String getID() {
			return "raven.sqdev.preferences";
		}
	};
	
	/**
	 * Gets the ID of this plugin
	 */
	public abstract String getID();
	
	/**
	 * Gets the Bundle of this plugin
	 */
	public Bundle getBundle() {
		return Platform.getBundle(getID());
	}
	
	/**
	 * Gets the version of this plugin
	 */
	public Version getVersion() {
		return getBundle().getVersion();
	}
	
	@Override
	public String toString() {
		return getID();
	}
	
	/**
	 * Gets the <code>ESQDevPlugin</code> that is represented by this String
	 * 
	 * @param str
	 *            The String whose corresponding <code>ESQDevPlugin</code>
	 *            should be obtained. Leading and trailing whitespace will be
	 *            ignored
	 * @return The corresponding <code>ESQDevPlugin</code> or <code>null</code>
	 *         if none could be found
	 */
	public static ESQDevPlugin resolve(String str) {
		str = str.trim();
		
		if (str.isEmpty()) {
			return null;
		}
		
		for (ESQDevPlugin current : ESQDevPlugin.values()) {
			if (current.toString().equals(str)) {
				return current;
			}
		}
		
		return null;
	}
}
