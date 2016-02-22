package raven.sqdev.preferences.util;

import java.io.File;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.Bundle;

import raven.sqdev.preferences.activator.Activator;
import raven.sqdev.util.ColorUtils;
import raven.sqdev.util.FileSystemUtil;

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
		return Activator.getDefault().getPreferenceStore();
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
		return getPreferenceStore().getBoolean(SQDevPreferenceConstants.SQDEV_ALWAYS_SAVE_ON_EXIT);
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
				.getString(SQDevPreferenceConstants.SQDEV_ARMA_DOCUMENTS_DIRECTORY);
	}
	
	/**
	 * Gets the missions directory
	 * 
	 * @return
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
		return getPreferenceStore().getString(SQDevPreferenceConstants.SQDEV_ARMA_MAIN_DIRECTORY);
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
	 * <code>SQDevPreferenceConstants.SQDEV_EXPORT_AUTOCLEAN</code> preference
	 * that indicates whether a directory should get cleaned without asking for
	 * permission when exporting a project
	 * 
	 * @see {@linkplain SQDevPreferenceConstants}
	 */
	public static boolean autoClean() {
		return getPreferenceStore().getBoolean(SQDevPreferenceConstants.SQDEV_EXPORT_AUTOCLEAN);
	}
}
