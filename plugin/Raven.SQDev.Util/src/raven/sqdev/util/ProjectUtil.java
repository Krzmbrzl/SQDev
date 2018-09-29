package raven.sqdev.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.swt.SWT;
import org.osgi.service.prefs.BackingStoreException;

import raven.sqdev.exceptions.FailedAtCreatingFileException;
import raven.sqdev.exceptions.IllegalAccessStateException;
import raven.sqdev.exceptions.SQDevCoreException;
import raven.sqdev.exceptions.SQDevFileIsInvalidException;
import raven.sqdev.exceptions.SQDevFileNoSuchAttributeException;
import raven.sqdev.misc.FileSystemUtil;
import raven.sqdev.misc.SQDevInfobox;
import raven.sqdev.misc.SQDevPreferenceUtil;
import raven.sqdev.misc.SQDevProjectNature;
import raven.sqdev.sqdevFile.ESQDevFileAnnotation;
import raven.sqdev.sqdevFile.ESQDevFileAttribute;
import raven.sqdev.sqdevFile.ESQDevFileType;
import raven.sqdev.sqdevFile.SQDevFile;

public class ProjectUtil {

	/**
	 * Indicating that the export was successfull
	 */
	public static final String SUCCESS = "success";

	/**
	 * Indicating that the export was canceled
	 */
	public static final String CANCELED = "canceled";

	/**
	 * Indicating that the export has failed
	 */
	public static final String FAILED = "failed";

	/**
	 * Gets the export location for the given project assuming that the given
	 * project is a valid SQDev project
	 * 
	 * @param project
	 *            The project the export path should be returned for
	 * @return
	 */
	public static IPath getExportPathFor(IProject project) {
		// it has to be an SQDevProject
		Assert.isTrue(ProjectUtil.isSQDevProject(project));

		IFile file = project.getFile(ESQDevFileType.LINK.toString() + EFileType.SQDEV.getExtension());

		IPath path = file.getRawLocation().makeAbsolute();

		SQDevFile linkFile = null;
		try {
			// create the respective SQDev file
			linkFile = new SQDevFile(path);
		} catch (IllegalAccessStateException | IOException e) {
			throw new SQDevCoreException(e);
		}

		IPath exportPath;
		try {
			// create the folder name including the map name
			linkFile.processAttribute(ESQDevFileAttribute.TERRAIN);
			String projectFolderName = project.getName() + "." + ESQDevFileAttribute.TERRAIN.getValue();

			// get the mission directory
			linkFile.processAttribute(ESQDevFileAttribute.PROFILE);
			linkFile.processAttribute(ESQDevFileAttribute.EXPORTDIRECTORY);
			String expPath = ESQDevFileAttribute.EXPORTDIRECTORY.getValue().trim();

			// create the path according to the gathered path and name; Use SQDevPath in
			// order to handle placeholders
			exportPath = new SQDevPath(expPath, ESQDevFileAttribute.PROFILE.getValue()).toPath()
					.append(projectFolderName);
		} catch (SQDevFileIsInvalidException | SQDevFileNoSuchAttributeException | IOException e) {
			throw new SQDevCoreException(e);
		}

		return exportPath;
	}

	/**
	 * Exports the given project to the given location. Hidden files and folders
	 * (prefixed by a dot) will be ignored automatically during export (not during
	 * cleaning)
	 * 
	 * @param project
	 *            The project that should get exported
	 * @param destination
	 *            The path to the export destination
	 * @param filesToIgnore
	 *            A list of files that should not get exported
	 * @param filesToPreserve
	 *            A list of files that should not get deleted when performing the
	 *            clean of the destination
	 * @return The status of this export operation. Possible values are:
	 *         <li>ProjectUtil.SUCCESS</li>
	 *         <li>ProjectUtil.CANCELED</li>
	 *         <li>ProjectUtil.FAILED</li>
	 */
	public static String export(IProject project, IPath destination, Pattern ignorePattern, Pattern preservePattern) {
		if (!new File(destination.toOSString()).exists()) {
			// check how many folders have to be created
			IPath copy = new Path(destination.toOSString());

			// search for the first existing container
			for (int i = 0; i < copy.segmentCount(); i++) {
				copy = copy.removeLastSegments(1);

				if (new File(copy.toOSString()).exists()) {
					break;
				}
			}

			// calculate how many files have to be created
			int diff = destination.segmentCount() - copy.segmentCount();

			if (diff >= 2) {
				// normally there should be only the mission folder to be
				// created
				SQDevInfobox info = new SQDevInfobox(
						"The export process would require an unusual high amount (" + diff
								+ ") of new folders being created.\n\nDo you want to proceed?",
						SWT.ICON_QUESTION | SWT.YES | SWT.NO);

				if (info.open() != SWT.YES) {
					// abort the export process
					return CANCELED;
				}
			}
		}

		File missionFolder = new File(destination.toOSString());

		if (!missionFolder.exists()) {
			// create the mission folder
			missionFolder.mkdirs();
		} else {
			if (!SQDevPreferenceUtil.autoClean()) {
				SQDevInfobox info = new SQDevInfobox(
						"The directory \"" + destination.toOSString()
								+ "\" has to be cleaned in order to export the project \"" + project.getName()
								+ "\".\nAny files that are not part of the project in the eclipse "
								+ "workspace will be deleted.\n\nDo you want to proceed?",
						SWT.ICON_QUESTION | SWT.YES | SWT.NO);

				if (info.open() != SWT.YES) {
					return CANCELED;
				}
			}

			// clean the directory
			for (File currentFile : missionFolder.listFiles()) {
				// delete the respective files
				if (!FileSystemUtil.deleteFilesWithException(currentFile, preservePattern)) {
					// report that the cleaning couldn't be performed
					SQDevInfobox info = new SQDevInfobox("Failed to delete file \"" + currentFile.getAbsolutePath()
							+ "\"\nMake sure the files are not opened somewhere and try again"
							+ "\n\nCanceled the export process.", SWT.ICON_INFORMATION);

					info.open();

					// abort export when cleaning can't be performed
					return CANCELED;
				}
			}
		}

		try {
			for (IResource currentResource : project.members()) {
				File currentFile = new File(currentResource.getRawLocationURI());

				if (!currentFile.exists()) {
					// skip non-existent files
					continue;
				}

				FileSystemUtil.copyFilesWithExceptions(currentFile, destination, ignorePattern, true);
			}
		} catch (CoreException e) {
			e.printStackTrace();

			// inform the user about the failure
			String message = "Failed at exporting \"" + project.getName() + "\"\nReason: "
					+ ((e.getMessage() == null || e.getMessage().isEmpty()) ? "Unknown" : e.getMessage());

			SQDevInfobox info = new SQDevInfobox(message, SWT.ICON_ERROR);

			info.open();

			return FAILED;
		}

		// indicate that the export was successfull
		return SUCCESS;
	}

	/**
	 * Finds out whether the given project is a valid SQDev project.<br>
	 * A project is considered a SQDev project if it contains a link.sqdev
	 * 
	 * @param project
	 *            The project to be checked
	 * @return
	 */
	public static boolean isSQDevProject(IProject project) {
		try {
			if (project.hasNature(SQDevProjectNature.ID)) {
				return true;
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}

		// use presence of link.sqdev as a fallback-check
		IFile testFile = project.getFile(ESQDevFileType.LINK.toString() + EFileType.SQDEV.getExtension());

		return testFile.exists();
	}

	/**
	 * Gets the profile specified for the given project/mission
	 * 
	 * @param project
	 *            The SQDevProject whose corresponding profile should be obtained
	 *            <b>Has to be an SQDevProject!</b>
	 * @return The corresponding profile
	 */
	public static String getMissionProfile(IProject project) {
		SQDevFile linkFile = getLinkFile(project);

		try {
			linkFile.processAttribute(ESQDevFileAttribute.PROFILE);
			String profile = ESQDevFileAttribute.PROFILE.getValue();

			return profile;
		} catch (SQDevFileIsInvalidException | SQDevFileNoSuchAttributeException | IOException e) {
			// inform the user
			SQDevInfobox info = new SQDevInfobox(
					"Errors while retrieving the profile name for the project " + project.getName() + "!",
					SWT.ICON_ERROR);
			info.open();

			// rethrow
			throw new SQDevCoreException(e);
		}
	}

	/**
	 * Gets the linkFile of this SQDevProject
	 * 
	 * @param project
	 *            The SQDevProject whose linkFile should be obtained. <b>Has to be
	 *            an SQDevProject!</b>
	 * @return The corresponding linkFile
	 */
	public static SQDevFile getLinkFile(IProject project) {
		Assert.isTrue(isSQDevProject(project));

		IResource linkMember = project.findMember(ESQDevFileType.LINK + EFileType.SQDEV.getExtension());

		if (linkMember.getType() == IResource.FILE) {
			try {
				SQDevFile linkFile = new SQDevFile((IFile) linkMember);

				return linkFile;
			} catch (IllegalAccessStateException | IOException e) {
				// rethrow
				throw new SQDevCoreException(e);
			}
		} else {
			throw new SQDevCoreException("Selected linkFile is not a file!");
		}
	}

	/**
	 * Gets the <code>SQDevInformation</code> corresponding to the given
	 * SQDevProject
	 * 
	 * @param project
	 *            The <b>SQDevProject</b>
	 * @return The respective <code>SQDevInformation</code> or <code>null</code> if
	 *         the given project is no SQDevProject
	 */
	public static SQDevInformation getInformation(IProject project) {
		if (!isSQDevProject(project)) {
			return null;
		}

		SQDevFile linkFile = getLinkFile(project);

		if (linkFile == null) {
			return null;
		}

		return new SQDevInformation(linkFile);
	}

	/**
	 * Checks whether a project with the given name already exists in the workspace
	 * 
	 * @param name
	 *            The name of the project
	 */
	public static boolean exists(String name) {
		for (IProject currentProject : ResourcesPlugin.getWorkspace().getRoot()
				.getProjects(IContainer.INCLUDE_HIDDEN)) {
			if (currentProject.getName().equals(name)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Imports the given mission folder into the workspace as an SQDevProject
	 * 
	 * @param path
	 *            The path to the folder to import
	 * @param copyContent
	 *            Indicates whether all content should get copied into the
	 *            workspace. If this is set to <code>false</code> then the
	 *            respective resources will not be copied and only get linked
	 *            instead.
	 * @return
	 */
	public static String importAsProject(IPath path, boolean copyContent) {
		Assert.isTrue(path.isAbsolute() && path.toFile().exists());

		boolean isMission = Util.isMissionFolder(new File(path.toOSString()));

		String possibleName = path.lastSegment();

		if (isMission) {
			possibleName = possibleName.substring(0, possibleName.indexOf("."));
		}

		final String projectName = possibleName;

		try {
			// create the project
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IProject project = createSQDevProject(root, projectName, copyContent ? null : path);

			project.open(new NullProgressMonitor());

			// gather information
			SQDevInformation information = new SQDevInformation();
			information.setProfile(SQDevPreferenceUtil.getDefaultProfile());
			if (path.lastSegment() != null && path.lastSegment().contains(".")) {
				information.setTerrain(path.lastSegment().substring(path.lastSegment().indexOf(".") + 1));
				if (copyContent) {
					information.autoExport = SQDevPreferenceUtil.getAutoExportDefaultEnabled();
				} else {
					// turn off autoexport for all projects that originate from the Arma-dir and are
					// not copied
					IPath armaDirPath = new Path(SQDevPreferenceUtil.getArmaDocumentsDirectory());
					information.autoExport = !armaDirPath.isPrefixOf(project.getLocation())
							&& SQDevPreferenceUtil.getAutoExportDefaultEnabled();
				}
			} else {
				information.terrain = SQDevInformation.NOT_SET;
				information.autoExport = false;
			}
			information.name = projectName;

			// create linkFile
			EFileType type = EFileType.SQDEV;
			type.setInformation(information);
			type.setPath(project.getLocation().toOSString());
			type.create(ESQDevFileType.LINK.toString(), false);

			if (copyContent) {
				// copy files and folders
				for (File currentFile : path.toFile().listFiles()) {
					if (currentFile.isDirectory()) {
						FileSystemUtil.copyFolder(currentFile, (Path) project.getLocation(), true);
					} else {
						FileSystemUtil.copyFile(currentFile, (Path) project.getLocation());
					}
				}
			}

			// refresh project
			project.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());

			// initial build
			Job buildJob = new Job("Building " + projectName) {

				@Override
				protected IStatus run(IProgressMonitor monitor) {
					try {
						project.build(IncrementalProjectBuilder.FULL_BUILD, monitor);
					} catch (CoreException e) {
						e.printStackTrace();

						SQDevInfobox info = new SQDevInfobox("Failed at building project " + projectName, e);
						info.open(false);
					}

					return Status.OK_STATUS;
				}
			};
			buildJob.schedule();

		} catch (CoreException | FailedAtCreatingFileException | BackingStoreException e) {
			String message = "Failed at importing \"" + path.toOSString() + "\"";

			SQDevInfobox info = new SQDevInfobox(message, e);
			info.open();

			e.printStackTrace();
		}

		return SUCCESS;
	}

	/**
	 * Creates a new project with the given name in the given workspace root.
	 * 
	 * @param root
	 *            The workspace root to create the project in
	 * @param projectName
	 *            The name of the new project
	 * @param externalLocation
	 *            <code>null</code> if the project should be created from scratch.
	 *            Otherwise this can point to the source files to include in the
	 *            project but that are stored elsewhere
	 * @return The newly created project
	 * @throws FailedAtCreatingFileException
	 *             If there is a problem with creating the problem such as another
	 *             problem with the same name does already exist
	 * @throws CoreException
	 * @throws BackingStoreException
	 */
	public static IProject createSQDevProject(IWorkspaceRoot root, String projectName, IPath externalLocation)
			throws FailedAtCreatingFileException, CoreException, BackingStoreException {
		IProject project = root.getProject(projectName);

		// configure the SQDevProjectBuilder
		IProjectDescription description = ResourcesPlugin.getWorkspace().newProjectDescription(projectName);
		ICommand[] commands = description.getBuildSpec();

		// add builder to project
		ICommand command = description.newCommand();
		command.setBuilderName(SQDevProjectBuilder.ID);
		ICommand[] nc = new ICommand[commands.length + 1];
		// Add it before other builders.
		System.arraycopy(commands, 0, nc, 1, commands.length);
		nc[0] = command;
		description.setBuildSpec(nc);

		// set project nature
		description.setNatureIds(new String[] { SQDevProjectNature.ID });

		description.setLocation(externalLocation);

		// create the project; If anything goes wrong return
		if (!project.exists()) {
			try {
				project.create(description, new NullProgressMonitor());
			} catch (CoreException e) {
				throw new FailedAtCreatingFileException(e);
			}
		} else {
			throw new FailedAtCreatingFileException(
					"A project with the name \"" + projectName + "\" does already exist!");
		}

		project.open(new NullProgressMonitor());

		// Make sure the project is being encoded in UTF-8
		IScopeContext projectScope = new ProjectScope(project);
		IEclipsePreferences projectNode = projectScope.getNode("org.eclipse.core.resources");
		projectNode.node("encoding").put("<project>", "UTF-8");
		projectNode.flush();

		project.close(new NullProgressMonitor());

		return project;
	}

	/**
	 * Gets the project.sqdev from the given project
	 * 
	 * @param project
	 *            The project to process
	 * @return The {@linkplain SQDevFile} or <code>null</code> if it couldn't be
	 *         found
	 */
	public static SQDevFile getProjectFile(IProject project) {
		IResource file = project.findMember(ESQDevFileType.PROJECT + EFileType.SQDEV.getExtension());
		if (file != null && file.exists() && file instanceof IFile) {
			try {
				return new SQDevFile((IFile) file);
			} catch (IllegalAccessStateException | IOException e) {
				e.printStackTrace();

				SQDevInfobox info = new SQDevInfobox("Failed at retrieving project.sqdev!!", e);
				info.open(false);
			}
		}

		return null;
	}

	/**
	 * Creates a new project.sqdev-file in the given project if it doesn't exist yet
	 * 
	 * @param project
	 *            The project to create the file in
	 * @return The created {@linkplain SQDevFile}
	 * @throws IllegalAccessStateException
	 * @throws IOException
	 * @throws CoreException
	 */
	public static SQDevFile createProjectFile(IProject project)
			throws IllegalAccessStateException, IOException, CoreException {
		IFile file = project.getFile(ESQDevFileType.PROJECT + EFileType.SQDEV.getExtension());

		if (file.exists()) {
			return new SQDevFile(file);
		}

		file.create(new ByteArrayInputStream(ESQDevFileType.PROJECT.getInitialInput().getBytes()), false, null);

		return new SQDevFile(file);
	}

	/**
	 * Gets the names of the mods that are set as a dependency for the given project
	 * 
	 * @param project
	 *            The {@linkplain IProject} to check
	 * @return A {@linkplain List} containing the names of all mods that are
	 *         specified as a dependency in the given project
	 */
	public static List<String> getProjectModNames(IProject project) {
		SQDevFile projectFile = getProjectFile(project);

		if (projectFile != null) {
			try {
				projectFile.processAnnotation(ESQDevFileAnnotation.MOD);

				return ESQDevFileAnnotation.MOD.getValues();
			} catch (SQDevFileIsInvalidException | IOException e) {
				e.printStackTrace();

				SQDevInfobox info = new SQDevInfobox("Failed at retrieving project-mods!", e);
				info.open(false);
			}

		}

		return new ArrayList<>();
	}
}
