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
 * @author Raven
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
		
		createDescription(armaDirs, "Here the location of your ArmA folders are specified");
		
		
		String exeName;
		String docTooltip;
		
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			exeName = "arma3.exe";
			docTooltip = "The path to the directory that contains the \"Arma 3\" (and \"Arma 3 - "
					+ "Other Profiles\") directory (Normally this is your documents directory)";
		} else {
			exeName = "arma3.i386";
			docTooltip = "The path to the directory that contains the \"Arma 3\" (and \"Arma 3 - "
					+ "Other profiles\") directory (Normally this is \"$HOME/.local/share/"
					+ "bohemiainteractive/arma3/GameData/\"";
		}
		
		DirectorySQDevPreferenceEditor dirEditor = new DirectorySQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_INFO_ARMA_MAIN_DIRECTORY, "&Program:",
				"The path to the directory in which your " + exeName + " is located", armaDirs);
		dirEditor.addFileToMatch(exeName);
		addPreferenceEditor(dirEditor);
		
		DirectorySQDevPreferenceEditor docDirEditor = new DirectorySQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_INFO_ARMA_DOCUMENTS_DIRECTORY, "&Documents:",
				docTooltip, armaDirs);
		docDirEditor.addFolderToMatch("Arma 3");
		addPreferenceEditor(docDirEditor);
		
		addPreferenceEditor(new DirectorySQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_VIEWS_RPTVIEWER_RPT_PATH, "&RPTs:",
				"The path to the folder ArmA is storing it's RPTs (Affects the same "
						+ "preference as the box on the Views page)",
				armaDirs));
		
		// infos
		Group defaults = createGroup("Defaults");
		
		createDescription(defaults, "Some defaults that will be used by the plugin");
		
		addPreferenceEditor(new BooleanSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_INFO_DEFAULT_AUTOEXPORT, "&Default auto-export:",
				"Indicating whether auto-export is enabled by default "
						+ "(can be overwritten by each project individually)",
				defaults));
		
		addPreferenceEditor(
				new ComboSQDevPreferenceEditor(SQDevPreferenceConstants.SQDEV_INFO_DEFAULT_TERRAIN,
						"&Default terrain:", Util.getTerrains(), true,
						"Defines the default terrain newly created projects will be located on per"
								+ " default (Can be overwritten by each project individually)",
						defaults));
		
		addPreferenceEditor(new ComboSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_INFO_DEFAULT_PROFILE, "&Default profile:",
				Util.getProfiles().toArray(new String[Util.getProfiles().size()]), false,
				"Defines the default profile newly created projects will be associated with"
						+ " per default (Can be overwritten by each project individually)",
				defaults));
	}
}
