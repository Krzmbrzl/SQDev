package raven.sqdev.util;

import java.io.File;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.Bundle;

import raven.sqdev.constants.SQDevPreferenceConstants;
import raven.sqdev.exceptions.SQDevInvalidPreferenceException;
import raven.sqdev.pluginManager.SQDevPluginManager;

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
	 * 
	 * @return
	 */
	public static IEclipsePreferences getPreferences() {
		IEclipsePreferences preferences = InstanceScope.INSTANCE.getNode("raven.sqdev.preferences");
		
		// check that preferences have been found
		Assert.isNotNull(preferences, "The requested preference can not be resolved!");
		
		return preferences;
	}
	
	/**
	 * Gets the preference store
	 * 
	 * @return
	 */
	public static IPreferenceStore getPreferenceStore() {
		return SQDevPluginManager.getManager().get("raven.sqdev.preferences").getPreferenceStore();
	}
	
	/**
	 * Gets the value of the
	 * <code>SQDevPreferenceConstants.SQDEV_ALWAYS_SAVE_ON_EXIT</code>
	 * preference that indicates whether the preference page shall prompt for
	 * save when clicking OK
	 * 
	 * @see {@linkplain SQDevPreferenceConstants}
	 */
	public static boolean alwaysSaveOnExit() {
		return getPreferenceStore()
				.getBoolean(SQDevPreferenceConstants.SQDEV_PREF_ALWAYS_SAVE_ON_EXIT);
	}
	
	/**
	 * Gets the value of the
	 * <code>SQDevPreferenceConstants.SQDEV_ARMA_DOCUMENTS_DIRECTORY</code>
	 * preference that holds the path to the ArmA folder in the documents
	 * directory
	 * 
	 * @see {@linkplain SQDevPreferenceConstants}
	 */
	public static String getArmaDocumentsDirectory() {
		return getPreferenceStore()
				.getString(SQDevPreferenceConstants.SQDEV_INFO_ARMA_DOCUMENTS_DIRECTORY);
	}
	
	/**
	 * Get the path to the default directory in the documents coresponding to
	 * the default user profile
	 * 
	 * @return
	 */
	public static String getDefaultDocumentsDirectory() {
		IPath path = new Path(getArmaDocumentsDirectory());
		
		if (path.lastSegment().equals("Arma 3")) {
			return (path.toFile().exists()) ? path.toOSString() : getArmaDocumentsDirectory();
		} else {
			path = path.removeLastSegments(1);
			path = path.append("Arma 3");
			
			return (path.toFile().exists()) ? path.toOSString() : getArmaDocumentsDirectory();
		}
	}
	
	/**
	 * Gets the path to the profile's directory in myDocuments
	 * 
	 * @return
	 */
	public static String getProfilesDocumentDirectory() {
		IPath path = new Path(getArmaDocumentsDirectory());
		
		if (path.lastSegment().equals("Arma 3 - Other Profiles")) {
			return (path.toFile().exists()) ? path.toOSString() : getArmaDocumentsDirectory();
		} else {
			path = path.removeLastSegments(1);
			path = path.append("Arma 3 - Other Profiles");
			
			return (path.toFile().exists()) ? path.toOSString() : getArmaDocumentsDirectory();
		}
	}
	
	/**
	 * Gets the first missions directory that corresponds with the specified
	 * ArmADocumentsDirectory.
	 */
	public static File getMissionsDirectory() {
		File docDir = new File(getArmaDocumentsDirectory());
		
		if (docDir == null || !docDir.exists()) {
			throw new SQDevInvalidPreferenceException(
					"The ArmA folder in the documents directory is invalid");
		}
		
		File missionsDir = FileSystemUtil.getFolder(docDir, "missions");
		
		if (missionsDir == null) {
			throw new SQDevInvalidPreferenceException("Couldn't find the \"missions\" directory");
		}
		
		return missionsDir;
	}
	
	/**
	 * Gets the value of the
	 * <code>SQDevPreferenceConstants.SQDEV_ARMA_MAIN_DIRECTORY</code>
	 * preference that holds the path to the ArmA folder in the programs
	 * directory
	 * 
	 * @see {@linkplain SQDevPreferenceConstants}
	 */
	public static String getArmaProgramDirectory() {
		return getPreferenceStore()
				.getString(SQDevPreferenceConstants.SQDEV_INFO_ARMA_MAIN_DIRECTORY);
	}
	
	/**
	 * Gets the value of the
	 * <code>SQDevPreferenceConstants.SQDEV_EDITOR_MATCHING_BRACKETS_KEY</code>
	 * preference that holds the path to the ArmA folder in the programs
	 * directory
	 * 
	 * @see {@linkplain SQDevPreferenceConstants}
	 */
	public static boolean areMatchingBracketsShown() {
		return getPreferenceStore()
				.getBoolean(SQDevPreferenceConstants.SQDEV_EDITOR_MATCHING_BRACKETS_KEY);
	}
	
	/**
	 * Gets the value of the
	 * <code>SQDevPreferenceConstants.SQDEV_EDITOR_HIGHLIGHT_CURRENTLINE_KEY</code>
	 * preference that holds the path to the ArmA folder in the programs
	 * directory
	 * 
	 * @see {@linkplain SQDevPreferenceConstants}
	 */
	public static boolean isCurrentLineHighlighted() {
		return getPreferenceStore()
				.getBoolean(SQDevPreferenceConstants.SQDEV_EDITOR_HIGHLIGHT_CURRENTLINE_KEY);
	}
	
	/**
	 * Gets the value of the
	 * <code>SQDevPreferenceConstants.SQDEV_EDITOR_MATCHING_BRACKETS_COLOR_KEY</code>
	 * preference that holds the path to the ArmA folder in the programs
	 * directory
	 * 
	 * @see {@linkplain SQDevPreferenceConstants}
	 */
	public static Color getMatchingBracketHighlightingColor() {
		return new Color(Display.getCurrent(), ColorUtils.decodeRGB(getPreferenceStore()
				.getString(SQDevPreferenceConstants.SQDEV_EDITOR_MATCHING_BRACKETS_COLOR_KEY)));
	}
	
	/**
	 * Gets the value of the
	 * <code>SQDevPreferenceConstants.SQDEV_EDITOR_HIGHLIGHT_CURRENTLINE_COLOR_KEY</code>
	 * preference that holds the path to the ArmA folder in the programs
	 * directory
	 * 
	 * @see {@linkplain SQDevPreferenceConstants}
	 */
	public static Color getCurrentLineHighlightingColor() {
		return new Color(Display.getCurrent(), ColorUtils.decodeRGB(getPreferenceStore()
				.getString(SQDevPreferenceConstants.SQDEV_EDITOR_HIGHLIGHT_CURRENTLINE_COLOR_KEY)));
	}
	
	/**
	 * Gets the value of the
	 * <code>SQDevPreferenceConstants.SQDEV_EDITOR_SYNTAXHIGHLIGHTING_COLOR_KEY</code>
	 * preference that holds the path to the ArmA folder in the programs
	 * directory
	 * 
	 * @see {@linkplain SQDevPreferenceConstants}
	 */
	public static Color getSyntaxHighlightingColor() {
		return new Color(Display.getCurrent(), ColorUtils.decodeRGB(getPreferenceStore()
				.getString(SQDevPreferenceConstants.SQDEV_EDITOR_SYNTAXHIGHLIGHTING_COLOR_KEY)));
	}
	
	/**
	 * Gets the value of the
	 * <code>SQDevPreferenceConstants.SQDEV_EXPORT_AUTOCLEAN</code> preference
	 * that indicates whether a directory should get cleaned without asking for
	 * permission when exporting a project
	 * 
	 * @see {@linkplain SQDevPreferenceConstants}
	 */
	public static boolean autoClean() {
		return getPreferenceStore().getBoolean(SQDevPreferenceConstants.SQDEV_EXPORT_AUTOCLEAN);
	}
	
	/**
	 * Gets the value of the
	 * <code>SQDevPreferenceConstants.SQDEV_INFO_DEFAULT_AUTOEXPORT</code>
	 * preference that indicates if autoExport is enabled by default
	 * 
	 * @see {@linkplain SQDevPreferenceConstants}
	 */
	public static boolean getAutoExportDefaultEnabled() {
		return getPreferenceStore()
				.getBoolean(SQDevPreferenceConstants.SQDEV_INFO_DEFAULT_AUTOEXPORT);
	}
	
	/**
	 * Gets the value of the
	 * <code>SQDevPreferenceConstants.SQDEV_INFO_DEFAULT_PROFILE</code>
	 * preference that holds the default profile
	 * 
	 * @see {@linkplain SQDevPreferenceConstants}
	 */
	public static String getDefaultProfile() {
		return getPreferenceStore().getString(SQDevPreferenceConstants.SQDEV_INFO_DEFAULT_PROFILE);
	}
	
	/**
	 * Gets the value of the
	 * <code>SQDevPreferenceConstants.SQDEV_INFO_DEFAULT_TERRAIN</code>
	 * preference that holds the default terrain
	 * 
	 * @see {@linkplain SQDevPreferenceConstants}
	 */
	public static String getDefaultTerrain() {
		return getPreferenceStore().getString(SQDevPreferenceConstants.SQDEV_INFO_DEFAULT_TERRAIN);
	}
}
