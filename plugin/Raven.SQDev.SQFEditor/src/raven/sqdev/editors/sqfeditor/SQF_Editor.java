package raven.sqdev.editors.sqfeditor;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;

import raven.sqdev.constants.SQDevPreferenceConstants;
import raven.sqdev.editors.BasicCodeEditor;
import raven.sqdev.editors.BasicErrorListener;
import raven.sqdev.editors.BasicPartitionScanner;
import raven.sqdev.editors.KeywordScanner;
import raven.sqdev.editors.sqfeditor.parsing.SQFLexer;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser;
import raven.sqdev.exceptions.IllegalAccessStateException;
import raven.sqdev.exceptions.SQDevCoreException;
import raven.sqdev.exceptions.SQDevFileIsInvalidException;
import raven.sqdev.infoCollection.base.Keyword;
import raven.sqdev.infoCollection.base.SQFCommand;
import raven.sqdev.interfaces.IKeywordListChangeListener;
import raven.sqdev.misc.ListUtils;
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
public class SQF_Editor extends BasicCodeEditor implements IKeywordListChangeListener {
	
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
	
	public SQF_Editor() {
		super();
		
		// get keywordScanner
		KeywordScanner keywordScanner = getBasicConfiguration().getKeywordScanner(
				SQDevPreferenceConstants.SQDEV_EDITOR_SYNTAXHIGHLIGHTING_COLOR_KEY);
		
		provider = new SQFKeywordProvider();
		
		// set KeywordProvider
		keywordScanner.setKeywordProvider(provider);
		
		// make cas insensitive
		keywordScanner.makeCaseSensitive(false);
		
		// configure this editor as a keyword list listener
		provider.addKeywordListChangeListener(this);
		keywordScanner.addKeywordListChangeListener(this);
		
		// get PartitionScanner
		BasicPartitionScanner partitionScanner = getBasicProvider().getPartitionScanner();
		
		// exchange the string rule of the partitionScanner
		partitionScanner.removeRule(BasicPartitionScanner.DOUBLE_QUOTE_STRING_RULE);
		partitionScanner
				.addRule(new SQFStringPartitionRule(new Token(BasicPartitionScanner.BASIC_STRING)));
		
		binaryCommands = new ArrayList<SQFCommand>();
		unaryCommands = new ArrayList<SQFCommand>();
		nularCommands = new ArrayList<SQFCommand>();
		
		categorizeCommands();
	}
	
	@Override
	public void doSave(IProgressMonitor progressMonitor) {
		super.doSave(progressMonitor);
		
		// manage auto export
		IEditorInput input = this.getEditorInput();
		
		if (input instanceof IFileEditorInput) {
			// get the containing project
			IProject containingProject = ((IFileEditorInput) input).getFile().getProject();
			
			if (containingProject != null && ProjectUtil.isSQDevProject(containingProject)) {
				try {
					// get the linking file
					SQDevFile linkFile = new SQDevFile(containingProject
							.getFile(ESQDevFileType.LINK + EFileType.SQDEV.getExtension()));
					
					// check if autoExport is enabled for this project
					boolean autoExport = linkFile.parseAttribute(ESQDevFileAttribute.AUTOEXPORT)
							.getValue().equals("true");
					
					if (autoExport) {
						// outsource the export process to another thread
						Job exportJob = new Job("Export") {
							
							@Override
							protected IStatus run(IProgressMonitor monitor) {
								monitor.beginTask(
										"Export project \"" + containingProject.getName() + "\"",
										1);
								try {
									ProjectUtil.export(containingProject,
											Util.getExportPathFor(containingProject),
											linkFile.parseAnnotation(ESQDevFileAnnotation.IGNORE)
													.getValues(),
											linkFile.parseAnnotation(ESQDevFileAnnotation.PRESERVE)
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
					
				} catch (FileNotFoundException | IllegalAccessStateException e) {
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
	protected ParseTree doParse(String input) {
		IFile file = null;
		if (getEditorInput() instanceof IFileEditorInput) {
			file = ((IFileEditorInput) getEditorInput()).getFile();
		} else {
			SQDevInfobox info = new SQDevInfobox(
					"An unexpected input occured (not a FileEditorInput)."
							+ "\n\nPlease contact the developer",
					SWT.ERROR);
			
			info.open();
			
			return null;
		}
		
		try {
			file.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
		} catch (CoreException e) {
			SQDevInfobox info = new SQDevInfobox("Problem while removing markers...", e);
			info.open();
		}
		
		BasicErrorListener listener = new BasicErrorListener(this);
		
		ANTLRInputStream in = new ANTLRInputStream(input);
		
		SQFLexer lexer = new SQFLexer(in, ListUtils.toLowerCase(getBinaryKeywords()));
		lexer.removeErrorListeners();
		lexer.addErrorListener(listener);
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		SQFParser parser = new SQFParser(tokens);
		
		parser.removeErrorListeners();
		parser.addErrorListener(listener);
		
		return parser.code();
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
		return binaryCommands;
	}
	
	/**
	 * Gets all SQF commands that can be used as a unary operator
	 */
	public List<SQFCommand> getUnaryOperators() {
		return unaryCommands;
	}
	
	/**
	 * Gets all SQF commands that can be used as a nular operator
	 */
	public List<SQFCommand> getNularOperators() {
		return nularCommands;
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
	public List<String> getUnnaryKeywords() {
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
				throw new SQDevCoreException("Unimplemented behaviour necessary");
		}
	}
}
