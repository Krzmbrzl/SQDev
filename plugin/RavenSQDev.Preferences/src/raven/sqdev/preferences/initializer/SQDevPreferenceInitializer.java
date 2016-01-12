package raven.sqdev.preferences.initializer;

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
		store.setDefault(SQDevPreferenceConstants.SQDEV_ARMA_MAIN_DIRECTORY, "main");
		store.setDefault(SQDevPreferenceConstants.SQDEV_ARMA_DOCUMENTS_DIRECTORY, "documents");
	}
	
}
