package raven.sqdev.util;


import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;

import raven.sqdev.exceptions.FailedAtCreatingFileException;
import raven.sqdev.sqdevFile.ESQDevFileType;

public enum EProjectType {
	MISSION("Mission", "Creates a new mission-project"), MOD("Mod",
			"Creates a new mod-project");
	
	private String displayName;
	private String creationDescription;
	
	protected SQDevInformation info;
	
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
	public void create(IWorkspaceRoot root) {
		String projectName = info.getName();
		
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
						"A project with the name \"" + projectName
								+ "\" does already exist!");
			} catch (FailedAtCreatingFileException e) {
				e.printStackTrace();
				return;
			}
		}
		
		switch (this) {
			case MOD:
				createModProject(project, root);
				break;
			case MISSION:
				createMissionProject(project, root);
				break;
			default:
				break;
		}
	}
	
	private void createModProject(IProject project, IWorkspaceRoot root) {
		IFolder addonsFolder = project.getFolder("addons");
		
		try {
			// open the project
			project.open(new NullProgressMonitor());
		} catch (CoreException e2) {
			e2.printStackTrace();
		}
		
		if (!addonsFolder.exists()) {
			// create addonsFolder
			try {
				addonsFolder.create(IResource.NONE, true, null);
			} catch (CoreException e) {
				try {
					throw new FailedAtCreatingFileException(e);
				} catch (FailedAtCreatingFileException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		// create config file
		EFileType file = EFileType.CPP;
		file.setPath(project.getFullPath().toOSString());
		file.setInformation(getInformation());
		file.create("config");
		
		// create the link.sqdev
		file = EFileType.SQDEV;
		file.setPath(project.getFullPath().toOSString());
		file.setInformation(getInformation());
		file.create(ESQDevFileType.LINK.toString(), false);
	}
	
	/**
	 * Creates an SQF project
	 * 
	 * @param project
	 *            The project the content should be added to
	 * @param root
	 *            The root of the project
	 */
	private void createMissionProject(IProject project, IWorkspaceRoot root) {
		IFolder scriptFolder = project.getFolder("scripts");
		
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
		
		// create init file //TODO: could also use CfgFunctions -> create
		// setting
		EFileType file = EFileType.SQF;
		file.setPath(project.getFullPath().toOSString());
		file.setInformation(getInformation());
		file.create("init");
		
		// create the Description.ext
		file = EFileType.EXT;
		file.setPath(project.getFullPath().toOSString());
		file.setInformation(getInformation());
		file.create("description", false);
		
		// create the link.sqdev
		file = EFileType.SQDEV;
		file.setPath(project.getFullPath().toOSString());
		file.setInformation(getInformation());
		file.create(ESQDevFileType.LINK.toString(), false);
		
		// create the mission.sqm file
		file = EFileType.SQM;
		file.setPath(project.getFullPath().toOSString());
		file.setInformation(getInformation());
		file.create("mission", false);
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
	
	/**
	 * Checks if the information has been set
	 */
	public boolean isInformationSet() {
		return info != null;
	}
	
	public SQDevInformation getInformation() {
		return (isInformationSet()) ? info : new SQDevInformation();
	}
	
	public void setInformation(SQDevInformation info) {
		this.info = info;
	}
}
