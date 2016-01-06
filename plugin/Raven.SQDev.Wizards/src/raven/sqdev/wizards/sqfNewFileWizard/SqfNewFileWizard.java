package raven.sqdev.wizards.sqfNewFileWizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.core.runtime.*;
import org.eclipse.jface.operation.*;
import java.lang.reflect.InvocationTargetException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.CoreException;
import java.io.*;
import org.eclipse.ui.*;
import org.eclipse.ui.ide.IDE;

import raven.sqdev.editors.BasicTextEditor;

/**
 * This is a little wizard for the creation of a new SQF-file that will have the
 * extension <code>"*.sqf"</code>
 */

public class SqfNewFileWizard extends Wizard implements INewWizard {
	private SqfNewFileWizardPage page;
	private ISelection selection;
	private int initialCaretOffset = 0;
	
	/**
	 * Constructor for SqfNewFileWizard.
	 */
	public SqfNewFileWizard() {
		super();
		setNeedsProgressMonitor(true);
	}
	
	/**
	 * Adding the page to the wizard.
	 */
	
	public void addPages() {
		page = new SqfNewFileWizardPage(selection);
		addPage(page);
	}
	
	/**
	 * This method is called when 'Finish' button is pressed in the wizard. We
	 * will create an operation and run it using wizard as execution context.
	 */
	public boolean performFinish() {
		final String containerName = page.getContainerName();
		final String fileName = page.getFileName();
		
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					doFinish(containerName, fileName, monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};
		
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			return false;
		}
		
		return true;
	}
	
	/**
	 * The worker method. It will find the container, create the file if missing
	 * or just replace its contents, and open the editor on the newly created
	 * file.
	 */
	
	private void doFinish(String containerName, String fileName, IProgressMonitor monitor)
			throws CoreException {
			
		if (!fileName.endsWith(".sqf")) {
			// if the given fileName does not yet contain the respective
			// extension -> add it
			fileName += ".sqf";
		}
		
		// create a sample file
		monitor.beginTask("Creating " + fileName, 2);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		
		if (!resource.exists() || !(resource instanceof IContainer)) {
			throwCoreException("Container \"" + containerName + "\" does not exist.");
		}
		
		IContainer container = (IContainer) resource;
		final IFile file = container.getFile(new Path(fileName));
		
		try {
			InputStream stream = openContentStream(fileName);
			if (file.exists()) {
				file.setContents(stream, true, true, monitor);
			} else {
				file.create(stream, true, monitor);
			}
			stream.close();
		} catch (IOException e) {
		}
		
		monitor.worked(1);
		monitor.setTaskName("Opening file for editing...");
		
		getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
						.getActivePage();
				try {
					BasicTextEditor editor = (BasicTextEditor) IDE.openEditor(page, file, true);
					
					editor.selectAndReveal(SqfNewFileWizard.this.initialCaretOffset, 0);
				} catch (PartInitException e) {
				}
			}
		});
		
		monitor.worked(1);
	}
	
	/**
	 * We will initialize file contents with a sample text.
	 */
	
	private InputStream openContentStream(String fileName) {
		// TODO: add interface for customized starter content
		
		// remove potential extension
		fileName = (fileName.endsWith(".sqf")) ? fileName.substring(0, fileName.length() - 4)
				: fileName;
				
		String contents = "scopeName \"" + fileName + "\";\n\n";
		this.initialCaretOffset = contents.length();
		return new ByteArrayInputStream(contents.getBytes());
	}
	
	private void throwCoreException(String message) throws CoreException {
		IStatus status = new Status(IStatus.ERROR, "raven.sqdev.wizards", IStatus.OK, message,
				null);
		throw new CoreException(status);
	}
	
	/**
	 * We will accept the selection in the workbench to see if we can initialize
	 * from it.
	 * 
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
}