package raven.sqdev.preferences.pages;

import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;

import raven.sqdev.constants.SQDevPreferenceConstants;
import raven.sqdev.preferences.preferenceEditors.BooleanSQDevPreferenceEditor;
import raven.sqdev.preferences.preferenceEditors.ColorSQDevPreferenceEditor;
import raven.sqdev.preferences.preferenceEditors.IntegerSQDevPreferenceEditor;

/**
 * The preferencePage that contains all settings concerning the editor
 * 
 * @author Raven
 * 
 */
public class SQDevEditorPreferencePage extends SQDevPreferencePage {
	
	public SQDevEditorPreferencePage() {
		super();
	}
	
	@Override
	public void init(IWorkbench workbench) {
		setDescription("Preferences changing the appearance of the editor");
		
		// preferences for the behavior
		Group behaviour = createGroup("Behavior");
		
		createDescription(behaviour,
				"Here you can change the behaviour of the editor");
		
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
		
		addPreferenceEditor(new BooleanSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_ENABLE_AUTOCOMPLETE_KEY,
				"&Enable autoComplete:",
				"Enables/Disables autoComplete meaning that content assist will insert the proposal automatically if there is only one choice",
				behaviour));
		
		IntegerSQDevPreferenceEditor parseDelayEditor = new IntegerSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_PARSE_DELAY,
				"&Parse delay:",
				"Specifies the delay in milliseconds between a user input in the editor and the actual parsing of the editor's content",
				behaviour);
		parseDelayEditor.setMinValue(100);
		parseDelayEditor.setMaxValue(4000);
		
		addPreferenceEditor(parseDelayEditor);
		
		
		// preferences for the coloring
		Group colors = createGroup("Colors");
		
		createDescription(colors,
				"Here you can change the colors of the editor");
		
		ColorSQDevPreferenceEditor keywordHighlightingColorEditor = new ColorSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_KEYWORDHIGHLIGHTING_COLOR_KEY,
				"&Keyword highlighting:",
				"Defines the color in which keywords are highlighted", colors);
		addPreferenceEditor(keywordHighlightingColorEditor);
		
		ColorSQDevPreferenceEditor localVariableHighlightingEditor = new ColorSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_LOCALVARIABLEHIGHLIGHTING_COLOR_KEY,
				"&Local variable highlighting:",
				"Defines the color in which local variables are highlighted",
				colors);
		addPreferenceEditor(localVariableHighlightingEditor);
		
		ColorSQDevPreferenceEditor globalVariableHighlightingEditor = new ColorSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_GLOBALVARIABLEHIGHLIGHTING_COLOR_KEY,
				"&Global variable highlighting:",
				"Defines the color in which global variables are highlighted",
				colors);
		addPreferenceEditor(globalVariableHighlightingEditor);
		
		ColorSQDevPreferenceEditor magicVariableHighlightingEditor = new ColorSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_MAGICVARIABLEHIGHLIGHTING_COLOR_KEY,
				"&Magic variable highlighting:",
				"Defines the color in which magic variables (e.g. \"_this\") are highlighted",
				colors);
		addPreferenceEditor(magicVariableHighlightingEditor);
		
		ColorSQDevPreferenceEditor macroHighlightingEditor = new ColorSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_MACROHIGHLIGHTING_COLOR_KEY,
				"&Macro highlighting:",
				"Defines the color in which macros are highlighted", colors);
		addPreferenceEditor(macroHighlightingEditor);
		
		ColorSQDevPreferenceEditor currentLineHighlightingColorEditor = new ColorSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_HIGHLIGHT_CURRENTLINE_COLOR_KEY,
				"&Current line highlighting:",
				"Defines the color in which the current line is highlighted",
				colors);
		addPreferenceEditor(currentLineHighlightingColorEditor);
		
		ColorSQDevPreferenceEditor matchingBracketHighlightingColorEditor = new ColorSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_MATCHING_BRACKETS_COLOR_KEY,
				"&Matching bracket pair highlighting:",
				"Defines the color in which matching bracket pairs are highlighted",
				colors);
		addPreferenceEditor(matchingBracketHighlightingColorEditor);
		
		ColorSQDevPreferenceEditor stringHighlightingColorEditor = new ColorSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_STRINGHIGHLIGHTING_COLOR_KEY,
				"&String highlighting:",
				"Defines the color in which Strings are highlighted",
				colors);
		addPreferenceEditor(stringHighlightingColorEditor);
		
		ColorSQDevPreferenceEditor commentHighlightingColorEditor = new ColorSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_COMMENTHIGHLIGHTING_COLOR_KEY,
				"&Comment highlighting:",
				"Defines the color in which comments are highlighted",
				colors);
		addPreferenceEditor(commentHighlightingColorEditor);
	}
	
}
