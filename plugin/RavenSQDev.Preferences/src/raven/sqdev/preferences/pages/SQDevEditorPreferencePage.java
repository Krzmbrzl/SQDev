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

		createDescription(behaviour, "Here you can change the behaviour of the editor");

		BooleanSQDevPreferenceEditor enableCurrentLineHighlightingEditor = new BooleanSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_HIGHLIGHT_CURRENTLINE_KEY, "&Enable current line highlighting:",
				"Enables/Disables the highlighting of the current selected line in the editor", behaviour);
		addPreferenceEditor(enableCurrentLineHighlightingEditor);


		BooleanSQDevPreferenceEditor enableBracketMatchEditor = new BooleanSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_MATCHING_BRACKETS_KEY, "&Enable bracket highlighting:",
				"Enables/Disables the highlighting of matching bracket pairs in the editor", behaviour);
		addPreferenceEditor(enableBracketMatchEditor);

		addPreferenceEditor(new BooleanSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_ENABLE_AUTOCOMPLETE_KEY, "&Enable autoComplete:",
				"Enables/Disables autoComplete meaning that content assist will insert the proposal automatically if there is only one choice",
				behaviour));

		addPreferenceEditor(new BooleanSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_AUTOCOMPLETE_CHARACTERPAIRS_KEY, "&Enable CharacterPair completion:",
				"Enables/Disables the automatic completion of character pairs such as quotes and brackets", behaviour));

		IntegerSQDevPreferenceEditor parseDelayEditor = new IntegerSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_PARSE_DELAY, "&Parse delay:",
				"Specifies the delay in milliseconds between a user input in the editor and the actual parsing of the editor's content",
				behaviour);
		parseDelayEditor.setMinValue(100);
		parseDelayEditor.setMaxValue(4000);

		addPreferenceEditor(parseDelayEditor);


		// preferences for the coloring
		Group colors = createGroup("Colors");

		createDescription(colors, "Here you can change the colors of the editor");

		ColorSQDevPreferenceEditor keywordHighlightingColorEditor = new ColorSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_KEYWORDHIGHLIGHTING_COLOR_KEY, "&Commands:",
				"Defines the color in which commands are being highlighted", colors);
		addPreferenceEditor(keywordHighlightingColorEditor);

		ColorSQDevPreferenceEditor localVariableHighlightingEditor = new ColorSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_LOCALVARIABLEHIGHLIGHTING_COLOR_KEY, "&Local variables:",
				"Defines the color in which local variables are being highlighted", colors);
		addPreferenceEditor(localVariableHighlightingEditor);

		ColorSQDevPreferenceEditor globalVariableHighlightingEditor = new ColorSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_GLOBALVARIABLEHIGHLIGHTING_COLOR_KEY, "&Global variables:",
				"Defines the color in which global variables are being highlighted", colors);
		addPreferenceEditor(globalVariableHighlightingEditor);

		ColorSQDevPreferenceEditor magicVariableHighlightingEditor = new ColorSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_MAGICVARIABLEHIGHLIGHTING_COLOR_KEY, "&Magic variables:",
				"Defines the color in which magic variables (e.g. \"_this\") are being highlighted", colors);
		addPreferenceEditor(magicVariableHighlightingEditor);

		ColorSQDevPreferenceEditor functionsHighlightingEditor = new ColorSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_FUNCTIONHIGHLIGHTING_COLOR_KEY, "&Functions:",
				"Defines the color in which functions are being highlighted", colors);
		addPreferenceEditor(functionsHighlightingEditor);

		ColorSQDevPreferenceEditor macroHighlightingEditor = new ColorSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_MACROHIGHLIGHTING_COLOR_KEY, "&Macros:",
				"Defines the color in which macros are being highlighted", colors);
		addPreferenceEditor(macroHighlightingEditor);

		ColorSQDevPreferenceEditor currentLineHighlightingColorEditor = new ColorSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_HIGHLIGHT_CURRENTLINE_COLOR_KEY, "&Current line:",
				"Defines the color in which the current line int the editor is being highlighted", colors);
		addPreferenceEditor(currentLineHighlightingColorEditor);

		ColorSQDevPreferenceEditor matchingBracketHighlightingColorEditor = new ColorSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_MATCHING_BRACKETS_COLOR_KEY, "&Matching brackets:",
				"Defines the color in which matching bracket pairs are being highlighted", colors);
		addPreferenceEditor(matchingBracketHighlightingColorEditor);

		ColorSQDevPreferenceEditor stringHighlightingColorEditor = new ColorSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_STRINGHIGHLIGHTING_COLOR_KEY, "&Strings:",
				"Defines the color in which Strings are being highlighted", colors);
		addPreferenceEditor(stringHighlightingColorEditor);

		ColorSQDevPreferenceEditor commentHighlightingColorEditor = new ColorSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_EDITOR_COMMENTHIGHLIGHTING_COLOR_KEY, "&Comments:",
				"Defines the color in which comments are being highlighted", colors);
		addPreferenceEditor(commentHighlightingColorEditor);
	}

}
