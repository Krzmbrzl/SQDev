package raven.sqdev.editors.sqfeditor.parsing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import raven.sqdev.editors.sqfeditor.SQF_Editor;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.AssignmentContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.CodeContext;
import raven.sqdev.exceptions.SQDevEditorException;
import raven.sqdev.infoCollection.base.Keyword;
import raven.sqdev.interfaces.IKeywordProvider;

public class SQFParseListener extends SQFBaseListener {
	
	/**
	 * A map with instances of this listener
	 */
	private static Map<IKeywordProvider, SQFParseListener> instances = new HashMap<IKeywordProvider, SQFParseListener>();
	
	/**
	 * The keywordProvider to feed with keywords
	 */
	private IKeywordProvider keywordProvider;
	/**
	 * The editor this listener should report to
	 */
	private SQF_Editor editor;
	
	/**
	 * A list of Keywords that should be added to the provider
	 */
	private List<Keyword> variablesToAdd;
	/**
	 * A list of keywords that were added during last parse
	 */
	private List<Keyword> previouslyAddedVariables;
	
	/**
	 * Indicates whether the keywords have upadted
	 */
	public boolean updated;
	
	/**
	 * Creates a new instance of this listener.<br>
	 * <b> You can only create one instance per <code>IKeywordProvider</code>
	 * otherwise an <code>IllegalAccessError</codes> is thrown</b>. Use 
	 * {@link #instanceExists(IKeywordProvider)} to check if an instance has already been created and 
	 * {@link #getInstance(IKeywordProvider)} to get it
	 * @param provider The <code>IkeywordProvider</code> the created listener
	 *            should be configured to
	 * 
	 * @param editor
	 *            The editor the created listener should be configured to
	 */
	public SQFParseListener(IKeywordProvider provider, SQF_Editor editor) {
		if (instances.containsKey(provider)) {
			throw new IllegalAccessError("There is already an instance for the given arguments!");
		}
		
		keywordProvider = provider;
		this.editor = editor;
		
		variablesToAdd = new ArrayList<Keyword>();
		previouslyAddedVariables = new ArrayList<Keyword>();
		
		instances.put(provider, this);
	}
	
	/**
	 * Gets the instance that is configured for the given
	 * <code>IKeywordProvider</code>
	 * 
	 * @param provider
	 *            The provider to search for
	 * @return The respective instance or <code>null</code> if none could be
	 *         found
	 */
	public static SQFParseListener getInstance(IKeywordProvider provider) {
		SQFParseListener instance = instances.get(provider);
		
		if (instance != null) {
			// reset flag
			instance.updated = false;
		}
		
		return instance;
	}
	
	/**
	 * Checks if an instance for the given <code>IKeywordProvider</code> does
	 * exist
	 * 
	 * @param provider
	 *            The provider to search for
	 */
	public static boolean instanceExists(IKeywordProvider provider) {
		return getInstance(provider) != null;
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
		
		Keyword variable = new Keyword(variableName);
		
		if (!variablesToAdd.contains(variable)) {
			variablesToAdd.add(variable);
			
			System.out.println("Variable: " + variableName);
		}
	}
	
	@Override
	public void exitCode(CodeContext ctx) {
		if (ctx.getParent() == null) {
			// end of document has been reached
			
			// process variables
			for (Keyword currentKeyword : previouslyAddedVariables) {
				if (variablesToAdd.contains(currentKeyword)) {
					// it is already added
					variablesToAdd.remove(currentKeyword);
				} else {
					// the variable does no longer exist -> delete
					keywordProvider.removeKeyword(currentKeyword);
					
					updated = true;
				}
			}
			
			for (Keyword currentKeyword : variablesToAdd) {
				// these are new variables -> add
				keywordProvider.addKeyword(currentKeyword);
				
				previouslyAddedVariables.add(currentKeyword);
				
				updated = true;
			}
			
			// reset list
			variablesToAdd.clear();
		}
	}
}
