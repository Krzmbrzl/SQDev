package raven.sqdev.editors.sqfeditor;

import java.io.FileNotFoundException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;

import raven.sqdev.editors.BasicCodeEditor;
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

public class SQF_Editor extends BasicCodeEditor {
	
	public SQF_Editor() {
		super();
		this.setSourceViewerConfiguration(new SQFConfiguration(this.getColorManager(), this));
		this.setDocumentProvider(new SQFDocumentProvider());
	}
	
	@Override
	public void doSave(IProgressMonitor progressMonitor) {
		super.doSave(progressMonitor);
		
		// manage auto export
		IEditorInput input = this.getEditorInput();
		
		if (input instanceof IFileEditorInput) {
			// get the containing project
			IProject containingProject = ((IFileEditorInput) input).getFile().getProject();
			
			if (containingProject != null && Util.isSQDevProject(containingProject)) {
				try {
					// get the linking file
					SQDevFile linkFile = new SQDevFile(containingProject
							.getFile(ESQDevFileType.LINK + EFileType.SQDEV.getExtension()));
							
					// check if autoExport is enabled for this project
					boolean autoExport = linkFile.parseAttribute(ESQDevFileAttribute.AUTOEXPORT)
							.getValue().equals("true");
							
					if (autoExport) {
						// export the project
						ProjectUtil.export(containingProject,
								Util.getExportPathFor(containingProject),
								linkFile.parseAnnotation(ESQDevFileAnnotation.IGNORE).getValues(),
								linkFile.parseAnnotation(ESQDevFileAnnotation.PRESERVE).getValues());
					}
					
				} catch (FileNotFoundException | IllegalAccessStateException e) {
					e.printStackTrace();
					
					// inform the user
					String message = "Couldn't perform linking process!\n\nReason: "
							+ ((e.getMessage() == null || e.getMessage().isEmpty()) ? "Unknown"
									: e.getMessage());
									
					SQDevInfobox info = new SQDevInfobox(message, SWT.ICON_ERROR);
					info.open();
				} catch (SQDevFileIsInvalidException e) {
					e.printStackTrace();
					
					// inform the user
					String message = "The linking file is invalid!\n\nReason: "
							+ ((e.getMessage() == null || e.getMessage().isEmpty()) ? "Unknown"
									: e.getMessage());
									
					SQDevInfobox info = new SQDevInfobox(message, SWT.ICON_ERROR);
					info.open();
				}
			}
		}
	}
}
