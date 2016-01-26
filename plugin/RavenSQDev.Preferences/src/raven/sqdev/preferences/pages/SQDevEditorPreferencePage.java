package raven.sqdev.preferences.pages;

import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;

import raven.sqdev.preferences.preferenceEditors.BooleanSQDevPreferenceEditor;
import raven.sqdev.preferences.preferenceEditors.ColorSQDevPreferenceEditor;
import raven.sqdev.preferences.util.SQDevPreferenceConstants;

public class SQDevEditorPreferencePage extends SQDevPreferencePage {
	
	public SQDevEditorPreferencePage() {
		super();
	}
	
	@Override
	public void init(IWorkbench workbench) {
		setDescription("Preferences changing the appearance of the editor");
		
		// preferences for the behaviour
		Group behaviour = createGroup("Behaviour");
		
		createDescription(behaviour, "Here you can change the behaviour of the editor");
		
		BooleanSQDevPreferenceEditor enableCurrentLineHighlightingEditor = new BooleanSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_HIGHLIGHT_CURRENTLINE_KEY,
				"&Enable current line highlighting:",
				"Enables/Disables the highlighting of the current selected line in the editor",
				behaviour);
		addPreferenceEditor(enableCurrentLineHighlightingEditor);
		
		
		BooleanSQDevPreferenceEditor enableBracketMatchEditor = new BooleanSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_MATCHING_BRACKETS_KEY,
				"&Enable bracket highlighting:",
				"Enables/Disables the highlighting of matching bracket pairs in the editor",
				behaviour);
		addPreferenceEditor(enableBracketMatchEditor);
		
		
		// preferences for the coloring
		Group colors = createGroup("Colors");
		
		createDescription(colors, "Here you can change the colors of the editor");
		
		ColorSQDevPreferenceEditor syntaxHighlightingColorEditor = new ColorSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_SYNTAXHIGHLIGHTING_COLOR_KEY,
				"&Syntax highlighting:",
				"Defines the color in which keywords are highlighted", colors);
		addPreferenceEditor(syntaxHighlightingColorEditor);
		
		ColorSQDevPreferenceEditor currentLineHighlightingColorEditor = new ColorSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_HIGHLIGHT_CURRENTLINE_COLOR_KEY,
				"&Current line highlighting:",
				"Defines the color in which the current line is highlighted", colors);
		addPreferenceEditor(currentLineHighlightingColorEditor);
		
		ColorSQDevPreferenceEditor matchingBracketHighlightingColorEditor = new ColorSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_MATCHING_BRACKETS_COLOR_KEY,
				"&Matching bracket pair highlighting:",
				"Defines the color in which matching bracket pairs are highlighted", colors);
		addPreferenceEditor(matchingBracketHighlightingColorEditor);
	}
	
}