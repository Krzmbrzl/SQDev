package raven.sqdev.constants;

/**
 * Constant definitions for the names of SQDev plug-in preferences
 * 
 * @author Raven
 */
public class SQDevPreferenceConstants {
	
	/**
	 * The name of the preference holding the location to the ArmA folder on the
	 * local disk
	 */
	public static final String SQDEV_INFO_ARMA_MAIN_DIRECTORY = "raven.sqdev.ArmAProgramDirectory";
	
	/**
	 * The name of the preference holding the location of the ArmA folder in the
	 * documents directory on the local disk
	 */
	public static final String SQDEV_INFO_ARMA_DOCUMENTS_DIRECTORY = "raven.sqdev.ArmADocumentsDirectory";
	
	/**
	 * The name of the preference holding the default profile of the user
	 */
	public static final String SQDEV_INFO_DEFAULT_PROFILE = "raven.sqdev.defaultProfile";
	
	/**
	 * The name of the preference holding the default terrain
	 */
	public static final String SQDEV_INFO_DEFAULT_TERRAIN = "raven.sqdev.defaultTerrain";
	
	/**
	 * The name of the preference indicating if autoExport is enabled by default
	 */
	public static final String SQDEV_INFO_DEFAULT_AUTOEXPORT = "raven.sqdev.defaultAutoExport";
	
	/**
	 * The name of the preference indicating whether the preference page should
	 * ask to save unsaved preferences when clicking OK
	 */
	public static final String SQDEV_PREF_ALWAYS_SAVE_ON_EXIT = "raven.sqdev.AlwaysSaveOnExit";
	
	/**
	 * The name of the preference indicating whether matching brackets should
	 * get highlighted
	 */
	public static final String SQDEV_EDITOR_MATCHING_BRACKETS_KEY = "raven.sqdev.enableBracketMatch";
	
	/**
	 * The name of the preference indicating the color of the highlight of a
	 * matching bracket
	 */
	public static final String SQDEV_EDITOR_MATCHING_BRACKETS_COLOR_KEY = "raven.sqdev.matchingBracketsColor";
	
	/**
	 * The name of the preference indicating whether autoCompletion should be
	 * enabled
	 */
	public static final String SQDEV_EDITOR_ENABLE_AUTOCOMPLETE_KEY = "raven.sqdev.enableAutoComplete";
	
	/**
	 * The name of the preference indicating whether the current line should get
	 * highlighted
	 */
	public static final String SQDEV_EDITOR_HIGHLIGHT_CURRENTLINE_KEY = "raven.sqdev.highlightCurrentLine";
	
	/**
	 * The preferences indicating the color of the current line highlighting
	 */
	public static final String SQDEV_EDITOR_HIGHLIGHT_CURRENTLINE_COLOR_KEY = "raven.sqdev.currentLineHighlightingColor";
	
	/**
	 * The preferences indicating the color of the syntax highlighting
	 */
	public static final String SQDEV_EDITOR_SYNTAXHIGHLIGHTING_COLOR_KEY = "raven.sqdev.syntaxHighlightingColor";
	
	/**
	 * The preference indicating if a directory should be cleaned without asking
	 * for permission when exporting a project
	 */
	public static final String SQDEV_EXPORT_AUTOCLEAN = "raven.sqdev.autoClean";
	
	/**
	 * The preference holding the command that will be the first one to be
	 * processed during keyword update
	 */
	public static final String SQDEV_COLLECTION_STARTCOMMAND = "raven.sqdev.collection.startCommand";
	
	/**
	 * The preference holding the command that will be the last one to be
	 * processed during keyword update
	 */
	public static final String SQDEV_COLLECTION_ENDCOMMAND = "raven.sqdev.collection.endCommand";
	
}
