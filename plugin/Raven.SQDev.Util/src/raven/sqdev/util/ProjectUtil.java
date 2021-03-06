package raven.sqdev.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

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
import raven.sqdev.misc.FileUtil;
import raven.sqdev.misc.SQDevInfobox;
import raven.sqdev.misc.SQDevPreferenceUtil;
import raven.sqdev.misc.SQDevProjectNature;
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
	public static String export(IProject project, IPath destination, ArrayList<String> filesToIgnore,
			ArrayList<String> filesToPreserve) {
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
				if (!FileSystemUtil.deleteFilesWithException(currentFile, filesToPreserve)) {
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

				if (filesToIgnore.contains(currentFile.getName()) || currentFile.getName().startsWith(".")) {
					// skip if this resource is specified to be ignored or if it
					// is hidden
					continue;
				}

				if (!currentFile.exists()) {
					// skip non-existant files
					continue;
				}

				FileSystemUtil.copyFilesWithExceptions(currentFile, destination, filesToIgnore, true);
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
			String profile = linkFile.parseAttribute(ESQDevFileAttribute.PROFILE).getValue().toString();

			return profile;
		} catch (SQDevFileIsInvalidException e) {
			// inform the user
			SQDevInfobox info = new SQDevInfobox(
					"The linkFile in the project \"" + project.getName() + "\" is invalid!", SWT.ICON_ERROR);

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
			} catch (FileNotFoundException | IllegalAccessStateException e) {
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

		return new SQDevInformation(getLinkFile(project));
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
						FileUtil.copyFolder(currentFile, (Path) project.getLocation());
					} else {
						FileUtil.copyFile(currentFile, (Path) project.getLocation());
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
		command.setBuilderName("raven.sqdev.builder.sqdevprojectbuilder");
		ICommand[] nc = new ICommand[commands.length + 1];
		// Add it before other builders.
		System.arraycopy(commands, 0, nc, 1, commands.length);
		nc[0] = command;
		description.setBuildSpec(nc);

		// set project nature
		description.setNatureIds(new String[] { SQDevProjectNature.SQDEV_NATURE_ID });

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
}
