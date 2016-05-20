package raven.sqdev.editors.sqfeditor;

import java.io.FileNotFoundException;

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

import raven.sqdev.editors.BasicCodeEditor;
import raven.sqdev.editors.BasicErrorListener;
import raven.sqdev.editors.BasicPartitionScanner;
import raven.sqdev.editors.KeywordScanner;
import raven.sqdev.editors.sqfeditor.parsing.SQFLexer;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser;
import raven.sqdev.exceptions.IllegalAccessStateException;
import raven.sqdev.exceptions.SQDevFileIsInvalidException;
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
public class SQF_Editor extends BasicCodeEditor {
	
	public SQF_Editor() {
		super();
		
		// get keywordScanner
		KeywordScanner keywordScanner = getBasicConfiguration().getKeywordScanner();
		
		// set KeywordProvider
		keywordScanner.setKeywordProvider(new SQFKeywordProvider());
		
		// make cas insensitive
		keywordScanner.makeCaseSensitive(false);
		
		// get PartitionScanner
		BasicPartitionScanner partitionScanner = getBasicProvider().getPartitionScanner();
		
		// exchange the string rule of the partitionScanner
		partitionScanner.removeRule(BasicPartitionScanner.DOUBLE_QUOTE_STRING_RULE);
		partitionScanner
				.addRule(new SQFStringPartitionRule(new Token(BasicPartitionScanner.BASIC_STRING)));
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
		if(getEditorInput() instanceof IFileEditorInput) {
			file = ((IFileEditorInput) getEditorInput()).getFile();
		}else {
			SQDevInfobox info = new SQDevInfobox("An unexpected input occured (not a FileEditorInput)."
					+ "\n\nPlease contact the developer", SWT.ERROR);
			
			info.open();
			
			return null;
		}
		
		try {
			file.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
		} catch (CoreException e) {
			SQDevInfobox info = new SQDevInfobox("Problem while removing markers...", e);
			info.open();
		}
		
		ANTLRInputStream in = new ANTLRInputStream(input);
		
		SQFLexer lexer = new SQFLexer(in); // TODO: get binary operators
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		SQFParser parser = new SQFParser(tokens);
		
		parser.removeErrorListeners();
		parser.addErrorListener(new BasicErrorListener(this));
		
		return parser.code();
	}
}
