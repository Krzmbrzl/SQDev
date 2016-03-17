package raven.sqdev.preferences.pages;


import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;

import raven.sqdev.constants.SQDevPreferenceConstants;
import raven.sqdev.preferences.preferenceEditors.BooleanSQDevPreferenceEditor;
import raven.sqdev.preferences.preferenceEditors.ComboSQDevPreferenceEditor;
import raven.sqdev.preferences.preferenceEditors.DirectorySQDevPreferenceEditor;
import raven.sqdev.util.Util;

/**
 * The preferencePage that contains all general settings for this plugin
 * 
 * @author Robert Adam
 * 		
 */
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
				SQDevPreferenceConstants.SQDEV_INFO_ARMA_MAIN_DIRECTORY, "&Program:",
				"The path to the directory in which your arma3.exe is located", armaDirs);
				
		dirEditor.addFileToMatch("arma3.exe");
		
		addPreferenceEditor(dirEditor);
		
		DirectorySQDevPreferenceEditor docDirEditor = new DirectorySQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_INFO_ARMA_DOCUMENTS_DIRECTORY, "&Documents:",
				"The path to the \"Arma 3\" or \"Arma 3 - Other Profiles\" directory in"
						+ " your Documents (has to contain the \"missions\" folder)",
				armaDirs);
				
		docDirEditor.addFolderToMatch("missions");
		addPreferenceEditor(docDirEditor);
		
		// infos
		Group info = createGroup("Info");
		
		createDescription(info, "Some information that will be used by the plugin");
		
		addPreferenceEditor(new BooleanSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_INFO_DEFAULT_AUTOEXPORT, "&Default auto-export:",
				"Indicating whether auto-export is enabled by default "
						+ "(can be overwritten by each project individually)",
				info));
				
		addPreferenceEditor(
				new ComboSQDevPreferenceEditor(SQDevPreferenceConstants.SQDEV_INFO_DEFAULT_TERRAIN,
						"&Default terrain:", Util.getTerrains(), true,
						"Defines the default terrain newly created projects will be located on per"
								+ " default (Can be overwritten by each project individually)",
						info));
						
		addPreferenceEditor(
				new ComboSQDevPreferenceEditor(SQDevPreferenceConstants.SQDEV_INFO_DEFAULT_PROFILE,
						"&Default profile:",
						Util.getProfiles().toArray(new String[Util.getProfiles().size()]), false,
						"Defines the default profile newly created projects will be associated with"
								+ " per default (Can be overwritten by each project individually)",
						info));
						
						
		// miscellaneous preferences
		Group misc = createGroup("Miscellaneous");
		
		createDescription(misc,
				"Miscellaneous preferences defining the behaviour of the SQDev-plugin");
				
		addPreferenceEditor(new BooleanSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_PREF_ALWAYS_SAVE_ON_EXIT, "&Always save on exit:",
				"Whether unsaved preferences should get saved automatically when clicking \"OK\" without a popup asking for it",
				misc));
	}
}
