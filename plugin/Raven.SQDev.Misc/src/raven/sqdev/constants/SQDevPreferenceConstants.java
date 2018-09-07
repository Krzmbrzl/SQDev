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
	 * The name of the preference indicating whether the preference page should ask
	 * to save unsaved preferences when clicking OK
	 */
	public static final String SQDEV_PROMPT_ALWAYS_SAVE_ON_EXIT = "raven.sqdev.AlwaysSaveOnExit";

	/**
	 * The name of the preference indicating whether the user should be asked again
	 * if he selected an action that will delete a file
	 */
	public static final String SQDEV_PROMPT_ASK_FOR_DELETION = "raven.sqdev.askForDeletion";

	/**
	 * The name of the preference indicating whether matching brackets should get
	 * highlighted
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
	 * The preferences indicating the color of the keyword highlighting
	 */
	public static final String SQDEV_EDITOR_KEYWORDHIGHLIGHTING_COLOR_KEY = "raven.sqdev.keywordHighlightingColor";

	/**
	 * The preference indicating the color of the local variable highlighting
	 */
	public static final String SQDEV_EDITOR_LOCALVARIABLEHIGHLIGHTING_COLOR_KEY = "raven.sqdev.localVariableHighlightingColor";

	/**
	 * The preference indicating the color of the global variable highlighting
	 */
	public static final String SQDEV_EDITOR_GLOBALVARIABLEHIGHLIGHTING_COLOR_KEY = "raven.sqdev.globalVariableHighlightingColor";

	/**
	 * The preference indicating the color of the magic variable highlighting
	 */
	public static final String SQDEV_EDITOR_MAGICVARIABLEHIGHLIGHTING_COLOR_KEY = "raven.sqdev.magicVariableHighlightingColor";
	
	/**
	 * The preference indicating the color of the functionhighlighting
	 */
	public static final String SQDEV_EDITOR_FUNCTIONHIGHLIGHTING_COLOR_KEY = "raven.sqdev.functionHighlightingColor";

	/**
	 * The preference indicating the color of the macro highlighting
	 */
	public static final String SQDEV_EDITOR_MACROHIGHLIGHTING_COLOR_KEY = "raven.sqdev.macroHighlightingColor";

	/**
	 * The preference indicating the color of the string highlighting
	 */
	public static final String SQDEV_EDITOR_STRINGHIGHLIGHTING_COLOR_KEY = "raven.sqdev.stringHighlightingColor";

	/**
	 * The preference indicating the color of the string highlighting
	 */
	public static final String SQDEV_EDITOR_COMMENTHIGHLIGHTING_COLOR_KEY = "raven.sqdev.commentHighlightingColor";

	/**
	 * The preference indicating the delay before parsing after the user typed in
	 * something
	 */
	public static final String SQDEV_EDITOR_PARSE_DELAY = "raven.sqdev.parsingInterval";

	/**
	 * The preference indicating if a directory should be cleaned without asking for
	 * permission when exporting a project
	 */
	public static final String SQDEV_EXPORT_AUTOCLEAN = "raven.sqdev.autoClean";

	/**
	 * The preference holding the web adress to the BIKI API
	 */
	public static final String SQDEV_COLLECTION_API_ADRESS = "raven.sqdev.collection.apiAdress";

	/**
	 * The preference holding the wname of the main page that lists all the SQF
	 * commands
	 */
	public static final String SQDEV_COLLECTION_API_MAINPAGE = "raven.sqdev.collection.apiMainPage";

	/**
	 * The preference holding the path to the folder where ArmA stores it's RPTs
	 */
	public static final String SQDEV_VIEWS_RPTVIEWER_RPT_PATH = "raven.sqdev.rptViewer.rptPath";
	/**
	 * The preference indicating whether the RPTViewer should format it's content
	 */
	public static final String SQDEV_VIEWS_RPTVIEWER_FORMAT = "raven.sqdev.views.rptViewer.autoFormatRPTContent";
	/**
	 * The preference storing the prefixes of the lines that should be processed
	 * during formatting the RPTs
	 */
	public static final String SQDEV_VIEWS_RPTVIEWER_FORMAT_PREFIXES = "raven.sqdev.views.rptViewer.formatPrefixes";
	/**
	 * The preferences storing the maximum amount of blank lines that may occur in a
	 * formatted RPT in a row
	 */
	public static final String SQDEV_VIEWS_RPTVIEWER_MAX_BLANK_REPETITION = "raven.sqdev.views.rptViewer.maxBlankRepetition";
}
