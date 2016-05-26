package raven.sqdev.editors.sqfeditor.parsing;

import java.util.ArrayList;
import java.util.List;

import raven.sqdev.editors.sqfeditor.SQF_Editor;
import raven.sqdev.editors.sqfeditor.Variable;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.AssignmentContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.CodeContext;
import raven.sqdev.exceptions.SQDevEditorException;

public class SQFParseListener extends SQFBaseListener {
	/**
	 * The editor this listener should report to
	 */
	private SQF_Editor editor;
	
	/**
	 * A list of found localVariables
	 */
	private List<Variable> localVariables;
	/**
	 * A list of found global variables
	 */
	private List<Variable> globalVariables;
	
	/**
	 * Creates a new instance of this listener.
	 * 
	 * @param editor
	 *            The editor the created listener should be configured to
	 */
	public SQFParseListener(SQF_Editor editor) {
		this.editor = editor;
		
		localVariables = new ArrayList<Variable>();
		globalVariables = new ArrayList<Variable>();
	}
	
	/**
	 * Gets the editor this listener is configured on
	 */
	public SQF_Editor getConfiguredEditor() {
		return editor;
	}
	
	@Override
	public void exitAssignment(AssignmentContext ctx) {
		String variableName = null;
		
		switch (ctx.getChildCount()) {
			case 3:
				variableName = ctx.getChild(0).getText();
				break;
			
			case 4:
				variableName = ctx.getChild(1).getText();
				break;
			
			default:
				try {
					throw new SQDevEditorException("Unexpected child count in assignment");
				} catch (SQDevEditorException e) {
					// TODO: log
					e.printStackTrace();
				}
		}
		
		Variable variable = new Variable(variableName);
		
		if (variable.isLocal()) {
			if (!localVariables.contains(variable)) {
				localVariables.add(variable);
			}
		} else {
			if (!globalVariables.contains(variable)) {
				globalVariables.add(variable);
			}
		}
	}
	
	@Override
	public void exitCode(CodeContext ctx) {
		if (ctx.getParent() == null) {
			// end of document has been reached
			
			// set editors variables
			editor.setVariables(localVariables, globalVariables);
		}
	}
}
