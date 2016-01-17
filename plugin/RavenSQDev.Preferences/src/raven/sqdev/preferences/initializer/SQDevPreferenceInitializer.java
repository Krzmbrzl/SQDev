package raven.sqdev.preferences.initializer;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import raven.sqdev.preferences.activator.Activator;
import raven.sqdev.preferences.util.SQDevPreferenceConstants;

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
		store.setDefault(SQDevPreferenceConstants.SQDEV_ARMA_MAIN_DIRECTORY,
				locateArmaMainDirectory());
		store.setDefault(SQDevPreferenceConstants.SQDEV_ARMA_DOCUMENTS_DIRECTORY,
				locateArmaDocumentsDirectory());
		store.setDefault(SQDevPreferenceConstants.SQDEV_ALWAYS_SAVE_ON_EXIT, false);
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
