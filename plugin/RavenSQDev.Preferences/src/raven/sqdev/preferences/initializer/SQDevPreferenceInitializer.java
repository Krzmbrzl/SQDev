package raven.sqdev.preferences.initializer;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import raven.sqdev.constants.ISQDevColorConstants;
import raven.sqdev.constants.SQDevPreferenceConstants;
import raven.sqdev.preferences.activator.Activator;
import raven.sqdev.util.ColorUtils;

/**
 * Class used to initialize default preference values.
 */
public class SQDevPreferenceInitializer extends AbstractPreferenceInitializer {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#
	 * initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		
		// find the ArmA directory in programs folder
		store.setDefault(SQDevPreferenceConstants.SQDEV_INFO_ARMA_MAIN_DIRECTORY,
				locateArmaMainDirectory());
				
		// find ArmA directory in documents folder
		store.setDefault(SQDevPreferenceConstants.SQDEV_INFO_ARMA_DOCUMENTS_DIRECTORY,
				locateArmaDocumentsDirectory());
				
		// set default profile
		store.setDefault(SQDevPreferenceConstants.SQDEV_INFO_DEFAULT_PROFILE,
				System.getProperty("user.name"));
		
		// set deafult map
		store.setDefault(SQDevPreferenceConstants.SQDEV_INFO_DEFAULT_TERRAIN, "Altis");
		
		// set default autoExport
		store.setDefault(SQDevPreferenceConstants.SQDEV_INFO_DEFAULT_AUTOEXPORT, false);
				
		// let the preference page always ask for saving
		store.setDefault(SQDevPreferenceConstants.SQDEV_PREF_ALWAYS_SAVE_ON_EXIT, false);
		
		// enable matching brackets highlighting
		store.setDefault(SQDevPreferenceConstants.SQDEV_EDITOR_MATCHING_BRACKETS_KEY, true);
		store.setDefault(SQDevPreferenceConstants.SQDEV_EDITOR_MATCHING_BRACKETS_COLOR_KEY,
				ColorUtils.getRGBValuesAsString(ISQDevColorConstants.BRACKETMATCH));
				
		// enable currentLine highlighting
		store.setDefault(SQDevPreferenceConstants.SQDEV_EDITOR_HIGHLIGHT_CURRENTLINE_KEY, true);
		store.setDefault(SQDevPreferenceConstants.SQDEV_EDITOR_HIGHLIGHT_CURRENTLINE_COLOR_KEY,
				ColorUtils.getRGBValuesAsString(ISQDevColorConstants.CURRENTLINE));
				
		// set syntax highlighting
		store.setDefault(SQDevPreferenceConstants.SQDEV_EDITOR_SYNTAXHIGHLIGHTING_COLOR_KEY,
				ColorUtils.getRGBValuesAsString(ISQDevColorConstants.KEYWORD));
				
		// set autoClean
		store.setDefault(SQDevPreferenceConstants.SQDEV_EXPORT_AUTOCLEAN, false);
	}
	
	/**
	 * Finds the Arma 3 directory in the programs folder
	 * 
	 * @return The path of the directory or an empty string if the directory
	 *         couldn't be found
	 */
	private String locateArmaMainDirectory() {
		// navigate to the desired folder through the Steam folder
		String path = System.getenv("%programfiles% (x86)");
		
		path += "\\Steam\\steamapps\\common\\Arma 3";
		
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
	private String locateArmaDocumentsDirectory() {
		String path = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
		
		path += "\\Arma 3";
		
		if (new File(path).exists()) {
			return path;
		} else {
			return "";
		}
	}
	
}
