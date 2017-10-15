package raven.sqdev.preferences.pages;

import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;

import raven.sqdev.constants.SQDevPreferenceConstants;
import raven.sqdev.preferences.preferenceEditors.BooleanSQDevPreferenceEditor;
import raven.sqdev.preferences.preferenceEditors.DirectorySQDevPreferenceEditor;
import raven.sqdev.preferences.preferenceEditors.IntegerSQDevPreferenceEditor;
import raven.sqdev.preferences.preferenceEditors.MultiStringPreferenceEditor;

/**
 * The preference page that contains the preferences for the different views
 * 
 * @author Raven
 *
 */
public class SQDevViewsPreferencePage extends SQDevPreferencePage {
	
	public SQDevViewsPreferencePage() {
		super();
	}
	
	@Override
	public void init(IWorkbench workbench) {
		setDescription("Preferences for the different views");
		
		Group rptGroup = createGroup("RPTViewer");
		createDescription(rptGroup, "All preferences for the RPTViewer");
		
		addPreferenceEditor(new DirectorySQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_VIEWS_RPTVIEWER_RPT_PATH, "&RPTs:",
				"The path to the folder ArmA is storing it's RPTs (Affects the same preference"
						+ " as the box on the general page)",
				rptGroup));
		
		addPreferenceEditor(new BooleanSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_VIEWS_RPTVIEWER_FORMAT, "&Format RPTs:",
				"Whether the RPTViewer should format the displayed RPT content", rptGroup));
		
		addPreferenceEditor(new MultiStringPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_VIEWS_RPTVIEWER_FORMAT_PREFIXES, "&Format prefixes:",
				"The prefixes of lines that should be cut off during formatting of the RPT content. "
						+ "Each can be toggled from the RPTViewer",
				rptGroup));
		
		
		IntegerSQDevPreferenceEditor maxBlankLinesEditor = new IntegerSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_VIEWS_RPTVIEWER_MAX_BLANK_REPETITION,
				"&Max blank lines:",
				"Determines how many blank lines in a row a formatted RPT may contain", rptGroup);
		
		maxBlankLinesEditor.setMinValue(1);
		
		addPreferenceEditor(maxBlankLinesEditor);
	}
	
}
