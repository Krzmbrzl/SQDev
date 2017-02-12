package raven.sqdev.editors.sqfeditor;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;

import raven.sqdev.constants.SQDevPreferenceConstants;
import raven.sqdev.editors.BasicCodeEditor;
import raven.sqdev.editors.BasicErrorListener;
import raven.sqdev.editors.BasicPartitionScanner;
import raven.sqdev.editors.BasicSourceViewerConfiguration;
import raven.sqdev.editors.IMacroSupport;
import raven.sqdev.editors.KeywordScanner;
import raven.sqdev.editors.Macro;
import raven.sqdev.editors.sqfeditor.parsing.SQFLexer;
import raven.sqdev.editors.sqfeditor.parsing.SQFParseListener;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser;
import raven.sqdev.exceptions.IllegalAccessStateException;
import raven.sqdev.exceptions.SQDevCoreException;
import raven.sqdev.exceptions.SQDevFileIsInvalidException;
import raven.sqdev.infoCollection.base.Keyword;
import raven.sqdev.infoCollection.base.KeywordList;
import raven.sqdev.infoCollection.base.SQFCommand;
import raven.sqdev.interfaces.IKeywordListChangeListener;
import raven.sqdev.sqdevFile.ESQDevFileAnnotation;
import raven.sqdev.sqdevFile.ESQDevFileAttribute;
import raven.sqdev.sqdevFile.ESQDevFileType;
import raven.sqdev.sqdevFile.SQDevFile;
import raven.sqdev.util.EFileType;
import raven.sqdev.util.ProjectUtil;
import raven.sqdev.util.SQDevInfobox;
import raven.sqdev.util.Util;

/**
 * The editor for working with SQF files
 * 
 * @author Raven
 * 
 */
public class SQF_Editor extends BasicCodeEditor
		implements IKeywordListChangeListener, IMacroSupport {
	
	/**
	 * The KeywordProvider for the SQF keywords
	 */
	private SQFKeywordProvider provider;
	
	/**
	 * A list of all commands that can be used as a binary operator
	 */
	private List<SQFCommand> binaryCommands;
	/**
	 * A list of all commands that can be used as a unary operator
	 */
	private List<SQFCommand> unaryCommands;
	/**
	 * A list of all commands that can be used as a nular operator
	 */
	private List<SQFCommand> nularCommands;
	/**
	 * A list of local variables in this editor
	 */
	private List<Variable> localVariables;
	/**
	 * A list of global variables in this editor
	 */
	private List<Variable> globalVariables;
	/**
	 * A list of magic variables available in this editor
	 */
	private List<Variable> magicVariables;
	/**
	 * A list of defined macros for this editor
	 */
	private List<Macro> macros;
	/**
	 * A list of the macro-names defined for this editor
	 */
	private List<String> macroNames;
	/**
	 * The <code>CommonTokenStream</code> that is associated with the current
	 * parse tree
	 */
	private CommonTokenStream currentStream;
	
	public SQF_Editor() {
		super();
		
		BasicSourceViewerConfiguration configuration = getBasicConfiguration();
		
		// create respective keywordScanners
		configuration.createKeywordScanner(
				SQDevPreferenceConstants.SQDEV_EDITOR_KEYWORDHIGHLIGHTING_COLOR_KEY,
				false);
		configuration.createKeywordScanner(
				SQDevPreferenceConstants.SQDEV_EDITOR_LOCALVARIABLEHIGHLIGHTING_COLOR_KEY,
				false);
		configuration.createKeywordScanner(
				SQDevPreferenceConstants.SQDEV_EDITOR_GLOBALVARIABLEHIGHLIGHTING_COLOR_KEY,
				false);
		configuration.createKeywordScanner(
				SQDevPreferenceConstants.SQDEV_EDITOR_MAGICVARIABLEHIGHLIGHTING_COLOR_KEY,
				false);
		configuration.createKeywordScanner(
				SQDevPreferenceConstants.SQDEV_EDITOR_MACROHIGHLIGHTING_COLOR_KEY,
				true);
		
		// get keywordScanner
		KeywordScanner keywordScanner = configuration.getKeywordScanner(
				SQDevPreferenceConstants.SQDEV_EDITOR_KEYWORDHIGHLIGHTING_COLOR_KEY);
		
		provider = new SQFKeywordProvider();
		
		// set KeywordProvider
		keywordScanner.setKeywordProvider(provider);
		
		// configure this editor as a keyword list listener
		provider.addKeywordListChangeListener(this);
		keywordScanner.addKeywordListChangeListener(this);
		
		// get PartitionScanner
		BasicPartitionScanner partitionScanner = getBasicProvider()
				.getPartitionScanner();
		
		// exchange the string rule of the partitionScanner
		partitionScanner
				.removeRule(BasicPartitionScanner.DOUBLE_QUOTE_STRING_RULE);
		partitionScanner.addRule(new SQFStringPartitionRule(
				new Token(BasicPartitionScanner.BASIC_STRING)));
		
		binaryCommands = new ArrayList<SQFCommand>();
		unaryCommands = new ArrayList<SQFCommand>();
		nularCommands = new ArrayList<SQFCommand>();
		localVariables = new ArrayList<Variable>();
		globalVariables = new ArrayList<Variable>();
		magicVariables = new ArrayList<Variable>();
		
		// populate the magic vars with the stadard ones
		List<Variable> magicVars = new ArrayList<Variable>();
		magicVars.add(new Variable("_this",
				"This variable is available inside of functions and contains the parameters given to it."));
		magicVars.add(new Variable("_fnc_scriptName",
				"A String containing the function's name. Only awaylable when the function has "
						+ "been compiled via CfgFunctions."));
		magicVars.add(new Variable("_fnc_scriptNameParent",
				"A String containing the function's parent's name. Only awaylable when the function has "
						+ "been compiled via CfgFunctions."));
		magicVars.add(new Variable("_x",
				"References the current object oin the iteration. Available inside count or forEach loops"));
		magicVars.add(new Variable("_forEachIndex",
				"References the index of the current object in the iteration. Only available in a "
						+ "forEach loop."));
		magicVars.add(new Variable("_thisEventHandler",
				"References the current event handler. Only available inside an EventHandler"));
		
		setMagicVariables(magicVars, false);
		
		macros = new ArrayList<Macro>();
		macroNames = new ArrayList<String>();
		
		categorizeCommands();
	}
	
	@Override
	public void doSave(IProgressMonitor progressMonitor) {
		super.doSave(progressMonitor);
		
		// manage auto export
		IEditorInput input = this.getEditorInput();
		
		// if this file is part of a project
		if (input instanceof IFileEditorInput) {
			// get the containing project
			IProject containingProject = ((IFileEditorInput) input).getFile()
					.getProject();
			
			if (containingProject != null
					&& ProjectUtil.isSQDevProject(containingProject)) {
				try {
					// get the linking file
					SQDevFile linkFile = new SQDevFile(
							containingProject.getFile(ESQDevFileType.LINK
									+ EFileType.SQDEV.getExtension()));
					
					// check if autoExport is enabled for this project
					boolean autoExport = linkFile
							.parseAttribute(ESQDevFileAttribute.AUTOEXPORT)
							.getValue().equals("true");
					
					if (autoExport) {
						// outsource the export process to another thread
						Job exportJob = new Job("Export") {
							
							@Override
							protected IStatus run(IProgressMonitor monitor) {
								monitor.beginTask("Export project \""
										+ containingProject.getName() + "\"",
										1);
								try {
									ProjectUtil.export(containingProject,
											Util.getExportPathFor(
													containingProject),
											linkFile.parseAnnotation(
													ESQDevFileAnnotation.IGNORE)
													.getValues(),
											linkFile.parseAnnotation(
													ESQDevFileAnnotation.PRESERVE)
													.getValues());
									
									monitor.worked(1);
								} catch (SQDevFileIsInvalidException e) {
									e.printStackTrace();
								}
								
								monitor.done();
								
								return Status.OK_STATUS;
							}
						};
						
						exportJob.schedule();
					}
					
				} catch (FileNotFoundException
						| IllegalAccessStateException e) {
					e.printStackTrace();
					
					SQDevInfobox info = new SQDevInfobox(
							"Couldn't perform linking process!", e);
					info.open();
				} catch (SQDevFileIsInvalidException e) {
					e.printStackTrace();
					
					// inform the user
					
					SQDevInfobox info = new SQDevInfobox(
							"The linking file is invalid!", e);
					info.open();
				}
			}
		}
	}
	
	@Override
	protected ParseTree doParse(String input) {
		BasicErrorListener listener = new BasicErrorListener(this);
		
		ANTLRInputStream in = new ANTLRInputStream(input);
		
		SQFLexer lexer = new SQFLexer(in, getBinaryKeywords(),
				getUnaryKeywords(), macroNames);
		lexer.removeErrorListeners();
		lexer.addErrorListener(listener);
		
		currentStream = new CommonTokenStream(lexer);
		
		SQFParser parser = new SQFParser(currentStream);
		
		if (parseRuleNames == null) {
			parseRuleNames = Arrays.asList(parser.getRuleNames());
		}
		
		parser.removeErrorListeners();
		parser.addErrorListener(listener);
		
		return parser.start();
	}
	
	@Override
	public boolean processParseTree(ParseTree parseTree) {
		ParseTreeWalker walker = new ParseTreeWalker();
		
		walker.walk(new SQFParseListener(this, currentStream), parseTree);
		
		applyParseChanges();
		
		return true;
	}
	
	/**
	 * Categorizes the commands according to their ability to be used as a
	 * binary/unary/nular operator
	 */
	private void categorizeCommands() {
		for (Keyword currentKeyword : provider.getKeywordList().getKeywords()) {
			if (currentKeyword instanceof SQFCommand) {
				SQFCommand currentCommand = (SQFCommand) currentKeyword;
				
				if (currentCommand.isBinaryOperator()) {
					binaryCommands.add(currentCommand);
				}
				
				if (currentCommand.isUnaryOperator()) {
					unaryCommands.add(currentCommand);
				}
				
				if (currentCommand.isNularOperator()) {
					nularCommands.add(currentCommand);
				}
			}
		}
	}
	
	/**
	 * Gets all SQF commands that can be used as a binary operator
	 */
	public List<SQFCommand> getBinaryOperators() {
		return new ArrayList<SQFCommand>(binaryCommands);
	}
	
	/**
	 * Gets all SQF commands that can be used as a unary operator
	 */
	public List<SQFCommand> getUnaryOperators() {
		return new ArrayList<SQFCommand>(unaryCommands);
	}
	
	/**
	 * Gets all SQF commands that can be used as a nular operator
	 */
	public List<SQFCommand> getNularOperators() {
		return new ArrayList<SQFCommand>(nularCommands);
	}
	
	/**
	 * Gets a list of all keywords that can be used as a binary operator
	 */
	public List<String> getBinaryKeywords() {
		ArrayList<String> list = new ArrayList<String>();
		
		for (SQFCommand currentCommand : getBinaryOperators()) {
			list.add(currentCommand.getKeyword());
		}
		
		return list;
	}
	
	/**
	 * Gets a list of all keywords that can be used as a unnary operator
	 */
	public List<String> getUnaryKeywords() {
		ArrayList<String> list = new ArrayList<String>();
		
		for (SQFCommand currentCommand : getUnaryOperators()) {
			list.add(currentCommand.getKeyword());
		}
		
		return list;
	}
	
	/**
	 * Gets a list of all keywords that can be used as a nular operator
	 */
	public List<String> getNularKeywords() {
		ArrayList<String> list = new ArrayList<String>();
		
		for (SQFCommand currentCommand : getNularOperators()) {
			list.add(currentCommand.getKeyword());
		}
		
		return list;
	}
	
	@Override
	public void keywordListChanged(String ctx) {
		switch (ctx) {
			case IKeywordListChangeListener.CTX_LIST_CHANGED:
				categorizeCommands();
				break;
			
			case IKeywordListChangeListener.CTX_LIST_REMOVED:
				throw new SQDevCoreException(
						"Unimplemented behaviour necessary");
		}
	}
	
	/**
	 * Sets the local variables for this editor. If there is a change compared
	 * to the current set of local variables the editor will update itself
	 * 
	 * @param variables
	 *            The local variables to add
	 * @param update
	 *            Whether to allow an update of the editor
	 * 
	 * @return <code>True</code> when variables were updated
	 */
	public boolean setLocalVariables(List<Variable> variables, boolean update) {
		if (!localVariables.equals(variables)) {
			localVariables = new ArrayList<Variable>(variables);
			
			// update respective scanner/provider
			getBasicConfiguration()
					.getKeywordScanner(
							SQDevPreferenceConstants.SQDEV_EDITOR_LOCALVARIABLEHIGHLIGHTING_COLOR_KEY)
					.getKeywordProvider()
					.setKeywordList(new KeywordList(variables));
			
			if (update) {
				update(false);
			}
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Gets the list of local variables that are defined in this editor
	 */
	public List<Variable> getLocalVariables() {
		return localVariables;
	}
	
	/**
	 * Gets all defined local and all available magic variables for this editor
	 * in one list
	 */
	public List<Variable> getLocalAndMagicVariables() {
		List<Variable> variables = (localVariables);
		variables.addAll(magicVariables);
		
		return variables;
	}
	
	/**
	 * Sets the magic variables for this editor. If there is a change compared
	 * to the current set of local variables the editor will update itself
	 * 
	 * @param variables
	 *            The magic variables to add
	 * @param update
	 *            Whether to allow an update of the editor
	 * 
	 * @return <code>True</code> when variables were updated
	 */
	public boolean setMagicVariables(List<Variable> variables, boolean update) {
		if (!magicVariables.equals(variables)) {
			magicVariables = new ArrayList<Variable>(variables);
			
			// update respective scanner/provider
			getBasicConfiguration()
					.getKeywordScanner(
							SQDevPreferenceConstants.SQDEV_EDITOR_MAGICVARIABLEHIGHLIGHTING_COLOR_KEY)
					.getKeywordProvider()
					.setKeywordList(new KeywordList(variables));
			
			if (update) {
				update(false);
			}
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Gets the list of available magic variables for this editor
	 */
	public List<Variable> getMagicVariables() {
		return magicVariables;
	}
	
	/**
	 * Sets the global variables for this editor. If there is a change compared
	 * to the current set of global variables the editor will update itself
	 * 
	 * @param variables
	 *            The gloabl variables to add
	 * @param update
	 *            Whether to update the editor
	 * 
	 * @return <code>True</code> when variables were updated
	 */
	public boolean setGlobalVariables(List<Variable> variables,
			boolean update) {
		if (!globalVariables.equals(variables)) {
			globalVariables = new ArrayList<Variable>(variables);
			
			// update respective scanner/provider
			getBasicConfiguration()
					.getKeywordScanner(
							SQDevPreferenceConstants.SQDEV_EDITOR_GLOBALVARIABLEHIGHLIGHTING_COLOR_KEY)
					.getKeywordProvider()
					.setKeywordList(new KeywordList(variables));
			
			if (update) {
				update(false);
			}
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Gets the list of global variables that are defined in this editor
	 */
	public List<Variable> getGlobalVariables() {
		return globalVariables;
	}
	
	/**
	 * Sets the variables for this editor. If there are any changes in
	 * comparison to the current set of variables the editor will updates itself
	 * 
	 * @param localVariables
	 *            The new set of local variables
	 * @param globalVariables
	 *            The new set of global variables
	 */
	public void setVariables(List<Variable> localVariables,
			List<Variable> globalVariables) {
		boolean localUpdate = setLocalVariables(localVariables, false);
		boolean globalUpdate = setGlobalVariables(globalVariables, false);
		
		if (localUpdate || globalUpdate) {
			update(false);
		}
	}
	
	@Override
	public boolean setMacros(List<Macro> macros, boolean update) {
		macroNames.clear();
		for (Macro currentMacro : macros) {
			macroNames.add(currentMacro.getKeyword());
		}
		
		if (!this.macros.equals(macros)) {
			this.macros = new ArrayList<Macro>(macros);
			
			// update respective scanner/provider
			getBasicConfiguration()
					.getKeywordScanner(
							SQDevPreferenceConstants.SQDEV_EDITOR_MACROHIGHLIGHTING_COLOR_KEY)
					.getKeywordProvider()
					.setKeywordList(new KeywordList(macros));
			
			if (update) {
				update(false);
			}
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public List<Macro> getMacros() {
		return macros;
	}
}
