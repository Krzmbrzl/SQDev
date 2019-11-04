package raven.sqdev.preferences.initializer;

import java.io.File;
import javax.swing.filechooser.FileSystemView;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import raven.sqdev.constants.ISQDevColorConstants;
import raven.sqdev.constants.SQDevPreferenceConstants;
import raven.sqdev.misc.ColorUtils;
import raven.sqdev.misc.SQDevPreferenceUtil;
import raven.sqdev.preferences.activator.Activator;

/**
 * Class used to initialize default preference values.
 */
public class SQDevPreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();

		// find the ArmA directory in programs folder
		store.setDefault(SQDevPreferenceConstants.SQDEV_INFO_ARMA_MAIN_DIRECTORY, locateArmaMainDirectory());

		// find ArmA directory in documents folder
		store.setDefault(SQDevPreferenceConstants.SQDEV_INFO_ARMA_DOCUMENTS_DIRECTORY, locateArmaDocumentsDirectory());

		// set default profile
		store.setDefault(SQDevPreferenceConstants.SQDEV_INFO_DEFAULT_PROFILE, System.getProperty("user.name"));

		// set default map
		store.setDefault(SQDevPreferenceConstants.SQDEV_INFO_DEFAULT_TERRAIN, "Altis");

		// set default autoExport
		store.setDefault(SQDevPreferenceConstants.SQDEV_INFO_DEFAULT_AUTOEXPORT, false);

		// let the preference page always ask for saving
		store.setDefault(SQDevPreferenceConstants.SQDEV_PROMPT_ALWAYS_SAVE_ON_EXIT, false);

		// ask for deleting files
		store.setDefault(SQDevPreferenceConstants.SQDEV_PROMPT_ASK_FOR_DELETION, true);

		// enable matching brackets highlighting
		store.setDefault(SQDevPreferenceConstants.SQDEV_EDITOR_MATCHING_BRACKETS_KEY, true);
		store.setDefault(SQDevPreferenceConstants.SQDEV_EDITOR_MATCHING_BRACKETS_COLOR_KEY,
				ColorUtils.getRGBValuesAsString(ISQDevColorConstants.BRACKETMATCH));

		// enable currentLine highlighting
		store.setDefault(SQDevPreferenceConstants.SQDEV_EDITOR_HIGHLIGHT_CURRENTLINE_KEY, true);
		store.setDefault(SQDevPreferenceConstants.SQDEV_EDITOR_HIGHLIGHT_CURRENTLINE_COLOR_KEY,
				ColorUtils.getRGBValuesAsString(ISQDevColorConstants.CURRENTLINE));

		// enable autoComplete
		store.setDefault(SQDevPreferenceConstants.SQDEV_EDITOR_ENABLE_AUTOCOMPLETE_KEY, true);

		// set keyword highlighting
		store.setDefault(SQDevPreferenceConstants.SQDEV_EDITOR_KEYWORDHIGHLIGHTING_COLOR_KEY,
				ColorUtils.getRGBValuesAsString(ISQDevColorConstants.KEYWORD));

		// set local variable highlighting
		store.setDefault(SQDevPreferenceConstants.SQDEV_EDITOR_LOCALVARIABLEHIGHLIGHTING_COLOR_KEY,
				ColorUtils.getRGBValuesAsString(ISQDevColorConstants.LOCAL_VARIABLE));

		// set global variable highlighting
		store.setDefault(SQDevPreferenceConstants.SQDEV_EDITOR_GLOBALVARIABLEHIGHLIGHTING_COLOR_KEY,
				ColorUtils.getRGBValuesAsString(ISQDevColorConstants.GLOBAL_VARIABLE));

		// set magic variable highlighting
		store.setDefault(SQDevPreferenceConstants.SQDEV_EDITOR_MAGICVARIABLEHIGHLIGHTING_COLOR_KEY,
				ColorUtils.getRGBValuesAsString(ISQDevColorConstants.MAGIC_VARIABLE));

		// set function highlighting
		store.setDefault(SQDevPreferenceConstants.SQDEV_EDITOR_FUNCTIONHIGHLIGHTING_COLOR_KEY,
				ColorUtils.getRGBValuesAsString(ISQDevColorConstants.FUNCTION));

		// set macro highlighting
		store.setDefault(SQDevPreferenceConstants.SQDEV_EDITOR_MACROHIGHLIGHTING_COLOR_KEY,
				ColorUtils.getRGBValuesAsString(ISQDevColorConstants.MACRO));

		// set String highlighting
		store.setDefault(SQDevPreferenceConstants.SQDEV_EDITOR_STRINGHIGHLIGHTING_COLOR_KEY,
				ColorUtils.getRGBValuesAsString(ISQDevColorConstants.STRING));

		// set comment highlighting
		store.setDefault(SQDevPreferenceConstants.SQDEV_EDITOR_COMMENTHIGHLIGHTING_COLOR_KEY,
				ColorUtils.getRGBValuesAsString(ISQDevColorConstants.COMMENT));

		// set parsing interval
		store.setDefault(SQDevPreferenceConstants.SQDEV_EDITOR_PARSE_DELAY, 1000);

		// set autoClean
		store.setDefault(SQDevPreferenceConstants.SQDEV_EXPORT_AUTOCLEAN, false);
		
		// autoCompletion of character pairs
		store.setDefault(SQDevPreferenceConstants.SQDEV_AUTOCOMPLETE_CHARACTERPAIRS_KEY, true);

		// set default API address
		store.setDefault(SQDevPreferenceConstants.SQDEV_COLLECTION_API_ADRESS,
				"https://community.bistudio.com/wikidata/api.php");

		// set default API main page
		store.setDefault(SQDevPreferenceConstants.SQDEV_COLLECTION_API_MAINPAGE, "Category:Scripting_Commands_Arma_3");

		// set path to the RPT logs
		store.setDefault(SQDevPreferenceConstants.SQDEV_VIEWS_RPTVIEWER_RPT_PATH, locateRPT());

		// set default formatting preference to true
		store.setDefault(SQDevPreferenceConstants.SQDEV_VIEWS_RPTVIEWER_FORMAT, true);

		// set default prefixes for RPT formatting
		store.setDefault(SQDevPreferenceConstants.SQDEV_VIEWS_RPTVIEWER_FORMAT_PREFIXES,
				"Updating base class" + SQDevPreferenceUtil.STRING_SEPERATOR + "Attempt to override final function");

		// set default max blank lines
		store.setDefault(SQDevPreferenceConstants.SQDEV_VIEWS_RPTVIEWER_MAX_BLANK_REPETITION, 2);
	}

	/**
	 * Finds the Arma 3 directory in the programs folder
	 * 
	 * @return The path of the directory or an empty string if the directory
	 *         couldn't be found
	 */
	public static String locateArmaMainDirectory() {
		// navigate to the desired folder through the Steam folder
		String path;

		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			// Windows
			if (System.getenv("ProgramFiles(x86)") != null) {
				// 64 bit
				path = "C:/Program Files (x86)".replace("/", File.separator);
			} else {
				// 32 bit
				path = "C:/Program Files".replace("/", File.separator);
			}
		} else {
			// linux
			path = System.getProperty("user.home") + "/.local/share/";
		}

		path += "\\Steam\\steamapps\\common\\Arma 3".replace("\\", File.separator);

		if (new File(path).exists()) {
			return path;
		} else {
			return "";
		}
	}

	/**
	 * Finds the Arma 3 directory in the documents folder
	 * 
	 * @return The path of the directory or an empty string if the directory
	 *         couldn't be found
	 */
	public static String locateArmaDocumentsDirectory() {
		String path;

		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			// Windows
			path = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
		} else {
			// Linux
			path = System.getProperty("user.home") + File.separator
					+ ".local/share/bohemiainteractive/arma3/GameDocuments";
		}

		String tempPath = path + File.separator + "Arma 3";

		if (new File(tempPath).exists()) {
			return path;
		} else {
			return "";
		}
	}

	/**
	 * Gets the default RPT-directory
	 */
	public static String locateRPT() {
		String path = System.getProperty("user.home");

		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			path += "/AppData/Local/Arma 3".replace("/", File.separator);
		} else {
			path += "/.local/share/bohemiainteractive/arma3/AppDataLocal/Arma 3/".replace("/", File.separator);
		}

		if (new File(path).exists()) {
			return path;
		} else {
			return "";
		}
	}
}
