package raven.sqdev.preferences.pages;

import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;

import raven.sqdev.constants.SQDevPreferenceConstants;
import raven.sqdev.preferences.preferenceEditors.BooleanSQDevPreferenceEditor;

/**
 * The preference page containing all preferences about the project linking
 * 
 * @author Raven
 *		
 */
public class SQDevLinkingPreferencePage extends SQDevPreferencePage {
	
	public SQDevLinkingPreferencePage() {
		super();
	}
	
	@Override
	public void init(IWorkbench workbench) {
		setDescription("Preferences affecting the link of a project to the ArmA directory");
		
		Group export = createGroup("Export");
		
		createDescription(export, "Settings about the export of files/projects");
		
		addPreferenceEditor(new BooleanSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EXPORT_AUTOCLEAN, "Auto clean",
				"Indicates whether a directory should get cleaned without asking for permission when exporting a project",
				export));
	}
	
}
