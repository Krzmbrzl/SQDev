package raven.sqdev.preferences.pages;


import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;

import raven.sqdev.preferences.preferenceEditors.DirectorySQDevPreferenceEditor;
import raven.sqdev.preferences.util.SQDevPreferenceConstants;

public class SQDevGeneralPreferencePage extends SQDevPreferencePage {
	
	public SQDevGeneralPreferencePage() {
		super();
	}
	
	@Override
	public void init(IWorkbench workbench) {
		setDescription("General SQDev preferences");
		
		Group armaDirs = createGroup("ArmA directories");
		
		createDescription(armaDirs, "Here the location of your ArmA folders are specified "
				+ "(The program folder as well as the document folder)  ");
				
		addPreferenceEditor(new DirectorySQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_ARMA_MAIN_DIRECTORY, "&Program:",
				"The path to the directory in which your arma3.exe is located", armaDirs));
				
		addPreferenceEditor(new DirectorySQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_ARMA_DOCUMENTS_DIRECTORY, "&Documents:",
				"The path to the \"Arma 3\" or \"Arma 3 - Other Profiles\" directory in your Documents",
				armaDirs));
	}
	
}
