package raven.sqdev.editors.sqfeditor;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;

import dataStructures.AbstractSQFTokenFactory;
import raven.sqdev.constants.SQDevPreferenceConstants;
import raven.sqdev.editors.BasicCodeEditor;
import raven.sqdev.editors.BasicPartitionScanner;
import raven.sqdev.editors.BasicSourceViewerConfiguration;
import raven.sqdev.editors.KeywordScanner;
import raven.sqdev.exceptions.IllegalAccessStateException;
import raven.sqdev.exceptions.SQDevCoreException;
import raven.sqdev.exceptions.SQDevFileIsInvalidException;
import raven.sqdev.infoCollection.base.Keyword;
import raven.sqdev.infoCollection.base.KeywordList;
import raven.sqdev.infoCollection.base.SQFCommand;
import raven.sqdev.infoCollection.base.Variable;
import raven.sqdev.interfaces.IKeywordListChangeListener;
import raven.sqdev.interfaces.IMacroSupport;
import raven.sqdev.interfaces.IParseResult;
import raven.sqdev.interfaces.ISQFInformation;
import raven.sqdev.interfaces.ISQFParseSupplier;
import raven.sqdev.interfaces.ITreeProcessingResult;
import raven.sqdev.misc.Macro;
import raven.sqdev.misc.Marker;
import raven.sqdev.misc.SQDevInfobox;
import raven.sqdev.parser.misc.ParseUtil;
import raven.sqdev.parser.misc.SQFParseResult;
import raven.sqdev.parser.misc.SQFTokenFactory;
import raven.sqdev.sqdevFile.ESQDevFileAnnotation;
import raven.sqdev.sqdevFile.ESQDevFileAttribute;
import raven.sqdev.sqdevFile.ESQDevFileType;
import raven.sqdev.sqdevFile.SQDevFile;
import raven.sqdev.util.EFileType;
import raven.sqdev.util.ProjectUtil;
import raven.sqdev.util.Util;

/**
 * The editor for working with SQF files
 * 
 * @author Raven
 * 
 */
public class SQF_Editor extends BasicCodeEditor
		implements IKeywordListChangeListener, IMacroSupport, ISQFInformation, ISQFParseSupplier {

	/**
	 * The KeywordProvider for the SQF keywords
	 */
	private SQFKeywordProvider provider;

	/**
	 * A list of all commands that can be used as a binary operator
	 */
	private Map<String, SQFCommand> binaryCommands;
	/**
	 * A list of all commands that can be used as a unary operator
	 */
	private Map<String, SQFCommand> unaryCommands;
	/**
	 * A list of all commands that can be used as a nular operator
	 */
	private Map<String, SQFCommand> nularCommands;
	/**
	 * A list of local variables in this editor
	 */
	private Map<String, Variable> localVariables;
	/**
	 * A list of global variables in this editor
	 */
	private Map<String, Variable> globalVariables;
	/**
	 * A list of magic variables available in this editor
	 */
	private Map<String, Variable> magicVariables;
	/**
	 * A list of defined macros for this editor
	 */
	protected Map<String, Macro> macros;
	/**
	 * A list of the macro-names defined for this editor
	 */
	protected List<String> macroNames;
	/**
	 * The token factory to use for parsing
	 */
	protected AbstractSQFTokenFactory tokenFactory;

	public SQF_Editor() {
		super();

		BasicSourceViewerConfiguration configuration = getBasicConfiguration();

		// create respective keywordScanners
		configuration.createKeywordScanner(SQDevPreferenceConstants.SQDEV_EDITOR_MACROHIGHLIGHTING_COLOR_KEY, true);
		configuration.createKeywordScanner(SQDevPreferenceConstants.SQDEV_EDITOR_KEYWORDHIGHLIGHTING_COLOR_KEY, false);
		configuration.createKeywordScanner(SQDevPreferenceConstants.SQDEV_EDITOR_LOCALVARIABLEHIGHLIGHTING_COLOR_KEY,
				false);
		configuration.createKeywordScanner(SQDevPreferenceConstants.SQDEV_EDITOR_GLOBALVARIABLEHIGHLIGHTING_COLOR_KEY,
				false);
		configuration.createKeywordScanner(SQDevPreferenceConstants.SQDEV_EDITOR_MAGICVARIABLEHIGHLIGHTING_COLOR_KEY,
				false);

		// get keywordScanner
		KeywordScanner keywordScanner = configuration
				.getKeywordScanner(SQDevPreferenceConstants.SQDEV_EDITOR_KEYWORDHIGHLIGHTING_COLOR_KEY);

		provider = new SQFKeywordProvider();

		// set KeywordProvider
		keywordScanner.setKeywordProvider(provider);

		// configure this editor as a keyword list listener
		provider.addKeywordListChangeListener(this);
		keywordScanner.addKeywordListChangeListener(this);

		// get PartitionScanner
		BasicPartitionScanner partitionScanner = getBasicProvider().getPartitionScanner();

		// exchange the string rule of the partitionScanner
		partitionScanner.removeRule(BasicPartitionScanner.DOUBLE_QUOTE_STRING_RULE);
		partitionScanner.addRule(new SQFStringPartitionRule(new Token(BasicPartitionScanner.BASIC_STRING)));

		binaryCommands = new HashMap<String, SQFCommand>();
		unaryCommands = new HashMap<String, SQFCommand>();
		nularCommands = new HashMap<String, SQFCommand>();
		localVariables = new HashMap<String, Variable>();
		globalVariables = new HashMap<String, Variable>();
		magicVariables = new HashMap<String, Variable>();

		// populate the magic vars with the stadard ones
		setMagicVariables(ParseUtil.getDefaultMagicVars(), false);

		macros = new HashMap<String, Macro>();
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
			IProject containingProject = ((IFileEditorInput) input).getFile().getProject();

			if (containingProject != null && ProjectUtil.isSQDevProject(containingProject)) {
				try {
					// get the linking file
					SQDevFile linkFile = new SQDevFile(
							containingProject.getFile(ESQDevFileType.LINK + EFileType.SQDEV.getExtension()));

					// check if autoExport is enabled for this project
					linkFile.processAttribute(ESQDevFileAttribute.AUTOEXPORT);

					if (Boolean.parseBoolean(ESQDevFileAttribute.AUTOEXPORT.getValue())) {
						// outsource the export process to another thread
						Job exportJob = new Job("Export") {

							@Override
							protected IStatus run(IProgressMonitor monitor) {
								monitor.beginTask("Export project \"" + containingProject.getName() + "\"", 1);
								try {
									linkFile.processAnnotation(ESQDevFileAnnotation.IGNORE);
									linkFile.processAnnotation(ESQDevFileAnnotation.PRESERVE);

									ProjectUtil.export(containingProject, Util.getExportPathFor(containingProject),
											ESQDevFileAnnotation.IGNORE.getValues(),
											ESQDevFileAnnotation.PRESERVE.getValues());

									monitor.worked(1);
								} catch (SQDevFileIsInvalidException | IOException e) {
									e.printStackTrace();

									SQDevInfobox info = new SQDevInfobox("Errors ocured during the export of project "
											+ containingProject.getName() + "!", e);
									info.open();
								}

								monitor.done();

								return Status.OK_STATUS;
							}
						};

						exportJob.schedule();
					}

				} catch (IOException | IllegalAccessStateException e) {
					e.printStackTrace();

					SQDevInfobox info = new SQDevInfobox("Couldn't perform linking process!", e);
					info.open();
				} catch (SQDevFileIsInvalidException e) {
					e.printStackTrace();

					// inform the user

					SQDevInfobox info = new SQDevInfobox("The linking file is invalid!", e);
					info.open();
				}
			}
		}
	}

	@Override
	protected IParseResult doParse(InputStream input) {
		SQFParseResult result;
		try {
			result = ParseUtil.parseSQF(input, this);
		} catch (IOException e) {
			e.printStackTrace();

			SQDevInfobox info = new SQDevInfobox("Error during SQF-parsing!", e);
			info.open(false);
			return null;
		}

		for (Marker currentMarker : result.getMarkers()) {
			this.createMarker(currentMarker.getType(), currentMarker.getOffset(), currentMarker.getLength(),
					currentMarker.getMessage(), currentMarker.getSeverity());
		}

		return result;
	}

	@Override
	public boolean processParseTree(IParseResult parseResult) {
		if (!(parseResult instanceof SQFParseResult)) {
			throw new IllegalArgumentException("The given parse-result has to be of type SQFParseResult!");
		}
		// process parse result
		ITreeProcessingResult result = ParseUtil.processSQF((SQFParseResult) parseResult, this);

		setVariables(result.getDeclaredLocalVariables(), result.getDeclaredGlobalVariables());

		for (Marker currentMarker : result.getMarkers()) {
			this.createMarker(currentMarker.getType(), currentMarker.getOffset(), currentMarker.getLength(),
					currentMarker.getMessage(), currentMarker.getSeverity());
		}

		for (Position currentFoldingPos : result.getFoldableAreas()) {
			addFoldingArea(currentFoldingPos);
		}

		applyParseChanges();

		return true;
	}

	/**
	 * Categorizes the commands according to their ability to be used as a
	 * binary/unary/nular operator
	 */
	private void categorizeCommands() {
		Iterator<Entry<String, Keyword>> it = provider.getKeywordList().getKeywords().entrySet().iterator();

		while (it.hasNext()) {
			Keyword currentKeyword = it.next().getValue();

			if (currentKeyword instanceof SQFCommand) {
				SQFCommand currentCommand = (SQFCommand) currentKeyword;

				if (currentCommand.isBinaryOperator()) {
					binaryCommands.put(currentCommand.getKeyword().toLowerCase(), currentCommand);
				}

				if (currentCommand.isUnaryOperator()) {
					unaryCommands.put(currentCommand.getKeyword().toLowerCase(), currentCommand);
				}

				if (currentCommand.isNularOperator()) {
					nularCommands.put(currentCommand.getKeyword().toLowerCase(), currentCommand);
				}
			}
		}
	}

	@Override
	public Map<String, SQFCommand> getBinaryOperators() {
		return binaryCommands;
	}

	@Override
	public Map<String, SQFCommand> getUnaryOperators() {
		return unaryCommands;
	}

	@Override
	public Map<String, SQFCommand> getNularOperators() {
		return nularCommands;
	}

	@Override
	public List<String> getBinaryKeywords() {
		List<String> list = new ArrayList<String>();

		list.addAll(binaryCommands.keySet());

		return list;
	}

	@Override
	public List<String> getUnaryKeywords() {
		List<String> list = new ArrayList<String>();

		list.addAll(unaryCommands.keySet());

		return list;
	}

	@Override
	public List<String> getNularKeywords() {
		List<String> list = new ArrayList<String>();

		list.addAll(nularCommands.keySet());

		return list;
	}

	@Override
	public void keywordListChanged(String ctx) {
		switch (ctx) {
		case IKeywordListChangeListener.CTX_LIST_CHANGED:
			categorizeCommands();
			break;

		case IKeywordListChangeListener.CTX_LIST_REMOVED:
			throw new SQDevCoreException("Unimplemented behaviour necessary");
		}
	}

	/**
	 * Sets the local variables for this editor. If there is a change compared to
	 * the current set of local variables the editor will update itself
	 * 
	 * @param variables
	 *            The local variables to add
	 * @param update
	 *            Whether to allow an update of the editor
	 * 
	 * @return <code>True</code> when variables were updated
	 */
	public boolean setLocalVariables(Map<String, Variable> variables, boolean update) {
		if (!localVariables.equals(variables)) {
			localVariables = new HashMap<String, Variable>(variables);

			// update respective scanner/provider
			getBasicConfiguration()
					.getKeywordScanner(SQDevPreferenceConstants.SQDEV_EDITOR_LOCALVARIABLEHIGHLIGHTING_COLOR_KEY)
					.getKeywordProvider().setKeywordList(new KeywordList(new ArrayList<>(variables.values())));

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
	public Map<String, Variable> getLocalVariables() {
		return localVariables;
	}

	/**
	 * Gets all defined local and all available magic variables for this editor in
	 * one list
	 */
	public List<Variable> getLocalAndMagicVariables() {
		List<Variable> variables = new ArrayList<Variable>(localVariables.values());
		variables.addAll(magicVariables.values());

		return variables;
	}

	/**
	 * Sets the magic variables for this editor. If there is a change compared to
	 * the current set of local variables the editor will update itself
	 * 
	 * @param variables
	 *            The magic variables to add
	 * @param update
	 *            Whether to allow an update of the editor
	 * 
	 * @return <code>True</code> when variables were updated
	 */
	public boolean setMagicVariables(Map<String, Variable> variables, boolean update) {
		if (!magicVariables.equals(variables)) {
			magicVariables = new HashMap<String, Variable>(variables);

			// update respective scanner/provider
			getBasicConfiguration()
					.getKeywordScanner(SQDevPreferenceConstants.SQDEV_EDITOR_MAGICVARIABLEHIGHLIGHTING_COLOR_KEY)
					.getKeywordProvider().setKeywordList(new KeywordList(new ArrayList<>(variables.values())));

			if (update) {
				update(false);
			}

			return true;
		}

		return false;
	}

	/**
	 * Gets the set of available magic variables for this editor
	 */
	public Map<String, Variable> getMagicVariables() {
		return magicVariables;
	}

	/**
	 * Sets the global variables for this editor. If there is a change compared to
	 * the current set of global variables the editor will update itself
	 * 
	 * @param variables
	 *            The gloabl variables to add
	 * @param update
	 *            Whether to update the editor
	 * 
	 * @return <code>True</code> when variables were updated
	 */
	public boolean setGlobalVariables(Map<String, Variable> variables, boolean update) {
		if (!globalVariables.equals(variables)) {
			globalVariables = variables;

			// update respective scanner/provider
			getBasicConfiguration()
					.getKeywordScanner(SQDevPreferenceConstants.SQDEV_EDITOR_GLOBALVARIABLEHIGHLIGHTING_COLOR_KEY)
					.getKeywordProvider().setKeywordList(new KeywordList(new ArrayList<>(variables.values())));

			if (update) {
				update(false);
			}

			return true;
		}

		return false;
	}

	/**
	 * Gets the set of global variables that are defined in this editor
	 */
	public Map<String, Variable> getGlobalVariables() {
		return globalVariables;
	}

	/**
	 * Sets the variables for this editor. If there are any changes in comparison to
	 * the current set of variables the editor will updates itself
	 * 
	 * @param localVariables
	 *            The new set of local variables
	 * @param globalVariables
	 *            The new set of global variables
	 */
	public void setVariables(Map<String, Variable> localVariables, Map<String, Variable> globalVariables) {
		boolean localUpdate = setLocalVariables(localVariables, false);
		boolean globalUpdate = setGlobalVariables(globalVariables, false);

		if (localUpdate || globalUpdate) {
			update(false);
		}
	}

	@Override
	public boolean setMacros(Map<String, Macro> macros, boolean update) {
		macroNames.clear();
		for (Macro currentMacro : macros.values()) {
			macroNames.add(currentMacro.getKeyword());
		}

		if (!this.macros.equals(macros)) {
			this.macros = new HashMap<String, Macro>(macros);

			// update respective scanner/provider
			getBasicConfiguration().getKeywordScanner(SQDevPreferenceConstants.SQDEV_EDITOR_MACROHIGHLIGHTING_COLOR_KEY)
					.getKeywordProvider().setKeywordList(new KeywordList(new ArrayList<>(macros.values())));

			if (update) {
				update(false);
			}

			return true;
		}

		return false;
	}

	@Override
	public Map<String, Macro> getMacros() {
		return macros;
	}

	/**
	 * Gets the names of all macros that are configured for this editor
	 */
	public List<String> getMacroNames() {
		return macroNames;
	}

	@Override
	public List<String> getMagicVariableNames() {
		List<String> names = new ArrayList<String>();

		for (Variable current : magicVariables.values()) {
			names.add(current.getKeyword());
		}

		return names;
	}

	@Override
	public AbstractSQFTokenFactory getTokenFactory() {
		if (tokenFactory == null) {
			tokenFactory = new SQFTokenFactory(getBinaryKeywords(), getUnaryKeywords());
		}
		return tokenFactory;
	}
}
