package raven.sqdev.util;

import java.util.ArrayList;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.Bundle;

import raven.sqdev.constants.SQDevPreferenceConstants;
import raven.sqdev.exceptions.SQDevCoreException;
import raven.sqdev.pluginManager.SQDevPluginManager;

/**
 * This class provides functions for dealing with SQDev preferences
 * 
 * @author Raven
 * 
 */
public class SQDevPreferenceUtil {
	
	/**
	 * The seperator that is placed in between the different Strings in the
	 * Preferences
	 */
	public static final String STRING_SEPERATOR = "_#%NextString%#_";
	
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
				.getBoolean(SQDevPreferenceConstants.SQDEV_PROMPT_ALWAYS_SAVE_ON_EXIT);
	}
	
	/**
	 * Gets the value of the
	 * <code>SQDevPreferenceConstants.SQDEV_PROMPT_ASK_FOR_DELETION</code>
	 * preference that indicates whether the user should be prompted to validate
	 * a deletion he initialized
	 * 
	 * @see {@linkplain SQDevPreferenceConstants}
	 */
	public static boolean promptUserValidationForDeletion() {
		return getPreferenceStore()
				.getBoolean(SQDevPreferenceConstants.SQDEV_PROMPT_ASK_FOR_DELETION);
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
		String path = getPreferenceStore()
				.getString(SQDevPreferenceConstants.SQDEV_INFO_ARMA_DOCUMENTS_DIRECTORY);
		
		if (path.isEmpty()) {
			SQDevInfobox info = new SQDevInfobox("Invalid document's directory in preference!",
					SWT.ICON_ERROR, false);
			info.open(false);
		}
		
		return path;
	}
	
	/**
	 * Get the path to the default directory in the documents coresponding to
	 * the default user profile
	 */
	public static String getDefaultDocumentsDirectory() {
		IPath path = new Path(getArmaDocumentsDirectory());
		
		path.append("Arma 3");
		
		if (path.toFile().exists()) {
			return path.toOSString();
		} else {
			throw new SQDevCoreException("Can't find the default user directory");
		}
	}
	
	/**
	 * Gets the path to the profile's directory in myDocuments<br>
	 * If {@link #getArmaDocumentsDirectory()} returns an invalid (e.g. empty)
	 * String this method will return an empty String.
	 * 
	 * @return The path to the directory that contains all profile folders or an
	 *         empty String if the found path does not exist or the respective
	 *         preference is invalid (see above)
	 */
	public static String getProfilesDocumentDirectory() {
		IPath path = new Path(getArmaDocumentsDirectory());
		
		if (path.segmentCount() == 0) {
			return "";
		}
		
		path = path.append("Arma 3 - Other Profiles");
		
		if (!path.toFile().exists()) {
			return "";
		} else {
			return path.toOSString();
		}
	}
	
	/**
	 * Gets the value of the
	 * <code>SQDevPreferenceConstants.SQDEV_RPT_PATH</code> preference that
	 * holds the path to the folder ArmA stores it's RPTs
	 * 
	 * @see {@linkplain SQDevPreferenceConstants}
	 */
	public static String getRPTDirectory() {
		String path = getPreferenceStore()
				.getString(SQDevPreferenceConstants.SQDEV_VIEWS_RPTVIEWER_RPT_PATH);
		
		if (path.isEmpty()) {
			SQDevInfobox info = new SQDevInfobox("Invalid RPT directory in preference!",
					SWT.ICON_ERROR, false);
			info.open(false);
		}
		
		return path;
	}
	
	/**
	 * Gets the value of the
	 * <code>SQDevPreferenceConstants.SQDEV_VIEWS_RPTVIEWER_FORMAT</code>
	 * preference that indicates whether the RPT content should be formatted
	 * before being displayed
	 * 
	 * @see {@linkplain SQDevPreferenceConstants}
	 */
	public static boolean doFormatRPTContent() {
		return getPreferenceStore()
				.getBoolean(SQDevPreferenceConstants.SQDEV_VIEWS_RPTVIEWER_FORMAT);
	}
	
	/**
	 * Gets the value of the
	 * <code>SQDevPreferenceConstants.SQDEV_VIEWS_RPTVIEWER_FORMAT_PREFIXES</code>
	 * preference that stores all the prefixes od the lines that should be
	 * removed during formatting
	 * 
	 * @see {@linkplain SQDevPreferenceConstants}
	 */
	public static ArrayList<String> getRPTFormatPrefixes() {
		ArrayList<String> list = new ArrayList<String>();
		
		for (String current : getPreferenceStore()
				.getString(SQDevPreferenceConstants.SQDEV_VIEWS_RPTVIEWER_FORMAT_PREFIXES)
				.split(STRING_SEPERATOR)) {
			if (!current.isEmpty()) {
				list.add(current);
			}
		}
		
		return list;
	}
	
	/**
	 * Gets the value of the
	 * <code>SQDevPreferenceConstants.SQDEV_VIEWS_RPTVIEWER_MAX_BLANK_REPETITION</code>
	 * preference that stores the maximum amount of blank lines in a row in a
	 * formatted RPT
	 * 
	 * @see {@linkplain SQDevPreferenceConstants}
	 */
	public static int getMaximumBlankLinesInRPT() {
		return getPreferenceStore()
				.getInt(SQDevPreferenceConstants.SQDEV_VIEWS_RPTVIEWER_MAX_BLANK_REPETITION);
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
	 * preference that indicates whether matching brackets should get
	 * highlighted
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
	 * preference that indicates whether the current line should be highlighted
	 * 
	 * @see {@linkplain SQDevPreferenceConstants}
	 */
	public static boolean isCurrentLineHighlighted() {
		return getPreferenceStore()
				.getBoolean(SQDevPreferenceConstants.SQDEV_EDITOR_HIGHLIGHT_CURRENTLINE_KEY);
	}
	
	/**
	 * Gets the value of the
	 * <code>SQDevPreferenceConstants.SQDEV_EDITOR_ENABLE_AUTOCOMPLETE_KEY</code>
	 * preference that indicates whether the content assist will insert the
	 * proposal automatically when there is only one proposal
	 * 
	 * @see {@linkplain SQDevPreferenceConstants}
	 */
	public static boolean isAutoCompleteEnabled() {
		return getPreferenceStore()
				.getBoolean(SQDevPreferenceConstants.SQDEV_EDITOR_ENABLE_AUTOCOMPLETE_KEY);
	}
	
	/**
	 * Gets the value of the
	 * <code>SQDevPreferenceConstants.SQDEV_EDITOR_MATCHING_BRACKETS_COLOR_KEY</code>
	 * preference that indicates whether matching brackets should be highlighted
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
	 * preference that defines the color for the highlight of the current line
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
	 * preference that defines the color for the hihlighting of keywords
	 * 
	 * @see {@linkplain SQDevPreferenceConstants}
	 */
	public static Color getSyntaxHighlightingColor() {
		return new Color(Display.getCurrent(), ColorUtils.decodeRGB(getPreferenceStore()
				.getString(SQDevPreferenceConstants.SQDEV_EDITOR_KEYWORDHIGHLIGHTING_COLOR_KEY)));
	}
	
	/**
	 * Gets the value of the
	 * <code>SQDevPreferenceConstants.SQDEV_EDITOR_PARSE_DELAY</code> preference
	 * that defines the color for the hihlighting of keywords
	 * 
	 * @see {@linkplain SQDevPreferenceConstants}
	 */
	public static int getParseDelay() {
		return getPreferenceStore().getInt(SQDevPreferenceConstants.SQDEV_EDITOR_PARSE_DELAY);
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
	
	/**
	 * Gets the value of the
	 * <code>SQDevPreferenceConstants.SQDEV_COLLECTION_STARTCOMMAND</code>
	 * preference that holds the first command in the BIKI that should be
	 * processed
	 * 
	 * @see {@linkplain SQDevPreferenceConstants}
	 */
	public static String getFirstCommand() {
		return getPreferenceStore()
				.getString(SQDevPreferenceConstants.SQDEV_COLLECTION_STARTCOMMAND);
	}
	
	/**
	 * Gets the value of the
	 * <code>SQDevPreferenceConstants.SQDEV_COLLECTION_ENDCOMMAND</code>
	 * preference that holds the last command in the BIKI that should be
	 * processed
	 * 
	 * @see {@linkplain SQDevPreferenceConstants}
	 */
	public static String getLastCommand() {
		return getPreferenceStore().getString(SQDevPreferenceConstants.SQDEV_COLLECTION_ENDCOMMAND);
	}
}
