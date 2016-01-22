package raven.sqdev.preferences.pages;


import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;

import raven.sqdev.preferences.preferenceEditors.BooleanSQDevPreferenceEditor;
import raven.sqdev.preferences.preferenceEditors.DirectorySQDevPreferenceEditor;
import raven.sqdev.preferences.util.SQDevPreferenceConstants;

public class SQDevGeneralPreferencePage extends SQDevPreferencePage {
	
	public SQDevGeneralPreferencePage() {
		super();
	}
	
	@Override
	public void init(IWorkbench workbench) {
		setDescription("General SQDev preferences");
		
		// preferences concerning the ArmA directories
		Group armaDirs = createGroup("ArmA directories");
		
		createDescription(armaDirs, "Here the location of your ArmA folders are specified "
				+ "(The program folder as well as the document folder)  ");
				
		DirectorySQDevPreferenceEditor dirEditor = new DirectorySQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_ARMA_MAIN_DIRECTORY, "&Program:",
				"The path to the directory in which your arma3.exe is located", armaDirs);
		
		dirEditor.addFileToMatch("arma3.exe");
				
		addPreferenceEditor(dirEditor);
		
		addPreferenceEditor(new DirectorySQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_ARMA_DOCUMENTS_DIRECTORY, "&Documents:",
				"The path to the \"Arma 3\" or \"Arma 3 - Other Profiles\" directory in your Documents",
				armaDirs));
				
		// miscellaneous preferences
		Group misc = createGroup("Miscellaneous");
		
		createDescription(misc,
				"Miscellaneous preferences defining the behaviour of the SQDev-plugin");
				
		addPreferenceEditor(new BooleanSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_ALWAYS_SAVE_ON_EXIT, "&Always save on exit:",
				"Whether unsaved preferences should get automatically saved when clicking \"OK\" without a popup asking for it",
				misc));
	}
}
