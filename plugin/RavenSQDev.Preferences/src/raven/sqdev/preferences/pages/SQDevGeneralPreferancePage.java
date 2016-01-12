package raven.sqdev.preferences.pages;


import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;

import raven.sqdev.preferences.util.SQDevPreferenceConstants;

public class SQDevGeneralPreferancePage extends SQDevPreferencePage {
	
	public SQDevGeneralPreferancePage() {
		super();
		
		Group armaDirs = createGroup("ArmA directories");
		
		createDescription(armaDirs, "Here the location of your ArmA folders are specified "
				+ "(The program folder as well as the document folder)");
				
		createDirectoryPreference(armaDirs, SQDevPreferenceConstants.SQDEV_ARMA_MAIN_DIRECTORY,
				"&Program:");
				
		createDirectoryPreference(armaDirs, SQDevPreferenceConstants.SQDEV_ARMA_DOCUMENTS_DIRECTORY,
				"&Documents:");
	}
	
	@Override
	public void init(IWorkbench workbench) {
		setDescription("General SQDev preferences");
	}
	
}
