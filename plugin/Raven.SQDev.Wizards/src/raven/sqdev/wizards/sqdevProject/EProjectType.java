package raven.sqdev.wizards.sqdevProject;

import java.io.ByteArrayInputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;

import raven.sqdev.editors.BasicTextEditor;
import raven.sqdev.wizards.exceptions.FailedAtCreatingFileException;

public enum EProjectType {
	SQF("SQF", "Creates a new SQF-project"), OOS("OOS", "Creates a new OOS-project"), MIXED("Mixed",
			"Creates new project that will contain both: SQF and OOS");
			
	private String displayName;
	private String creationDescription;
	
	private final String INITIAL_SQF_CONTENT = "scopeName = \"init\";\n\n";
	
	private EProjectType(String name, String creationDescription) {
		setDisplayName(name);
		setCreationDescription(creationDescription);
	}
	
	/**
	 * Gets the enum for the given index
	 * 
	 * @param index
	 *            The index of the enum entry
	 * @return
	 */
	public static EProjectType getIndex(int index) {
		if (index > EProjectType.values().length) {
			throw new IllegalArgumentException(
					"Can't access index " + index + " for EProjectType!");
		}
		
		return EProjectType.values()[index];
	}
	
	/**
	 * Creates the project with the given name in the given
	 * <code>IworkspaceRoot</code><br>
	 * This mehtod assumes that the given location is accessible and the given
	 * name is valid
	 * 
	 * @param projectName
	 *            The name of the project to be created
	 * @param root
	 *            The location where the project should be created
	 */
	public void create(String projectName, IWorkspaceRoot root) {
		IProject project = root.getProject(projectName);
		
		// create the project; If anything goes wrong return
		if (!project.exists()) {
			try {
				project.create(null);
			} catch (CoreException e) {
				try {
					// rethrow with custom exception
					throw new FailedAtCreatingFileException(e);
				} catch (FailedAtCreatingFileException e1) {
					e1.printStackTrace();
					return;
				}
			}
		} else {
			try {
				throw new FailedAtCreatingFileException(
						"A project with the name \"" + projectName + "\" does already exist!");
			} catch (FailedAtCreatingFileException e) {
				e.printStackTrace();
				return;
			}
		}
		
		switch (this) {
			case MIXED:
				break;
			case OOS:
				break;
			case SQF:
				createSQFProject(project, root);
				break;
			default:
				break;
		}
	}
	
	/**
	 * Creates an SQF project
	 * 
	 * @param project
	 *            The project the content should be added to
	 * @param root
	 *            The root of the project
	 */
	private void createSQFProject(IProject project, IWorkspaceRoot root) {
		IFolder scriptFolder = project.getFolder("scripts");
		IFile initFile = project.getFile("init.sqf");
		
		try {
			// open the project
			project.open(new NullProgressMonitor());
		} catch (CoreException e2) {
			e2.printStackTrace();
		}
		
		if (!scriptFolder.exists()) {
			// create scriptFolder
			try {
				scriptFolder.create(IResource.NONE, true, null);
			} catch (CoreException e) {
				try {
					throw new FailedAtCreatingFileException(e);
				} catch (FailedAtCreatingFileException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		if (!initFile.exists()) {
			try {
				initFile.create(getSQFInitFileContent(), IResource.NONE, null);
			} catch (CoreException e) {
				try {
					throw new FailedAtCreatingFileException(e);
				} catch (FailedAtCreatingFileException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		// open the init file
		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
		IWorkbenchPage page = win.getActivePage();
		
		IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry()
				.getDefaultEditor(initFile.getName());
				
		try {
			page.openEditor(new FileEditorInput(initFile), desc.getId());
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		
		// set initial caret offset so that it is at the end of the file
		try {
			BasicTextEditor editor = (BasicTextEditor) IDE.openEditor(page, initFile, true);
			
			editor.selectAndReveal(INITIAL_SQF_CONTENT.length(), 0);
		} catch (PartInitException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Creates an InputStream with the initial content of the init.sqf
	 * 
	 * @return
	 */
	private ByteArrayInputStream getSQFInitFileContent() {
		byte[] content = INITIAL_SQF_CONTENT.getBytes();
		
		return new ByteArrayInputStream(content);
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public String getCreationDescription() {
		return creationDescription;
	}
	
	public void setCreationDescription(String creationDescription) {
		this.creationDescription = creationDescription;
	}
}
