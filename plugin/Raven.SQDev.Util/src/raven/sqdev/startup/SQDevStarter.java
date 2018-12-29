package raven.sqdev.startup;

import java.io.IOException;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IStartup;
import org.osgi.framework.Version;
import org.osgi.service.prefs.BackingStoreException;

import raven.sqdev.constants.SQDevPreferenceConstants;
import raven.sqdev.exceptions.SQDevCoreException;
import raven.sqdev.exceptions.SQDevException;
import raven.sqdev.interfaces.IVersionListener;
import raven.sqdev.misc.FileSystemUtil;
import raven.sqdev.misc.SQDevInfobox;
import raven.sqdev.misc.SQDevPreferenceUtil;
import raven.sqdev.misc.SQDevProjectNature;
import raven.sqdev.misc.VersionChangeEvent;
import raven.sqdev.pluginManagement.ResourceManager;
import raven.sqdev.pluginManagement.VersionManager;

/**
 * The SQDev class that gets loaded when the workbench is initialized
 * 
 * @author Raven
 * 
 */
public class SQDevStarter implements IStartup, IVersionListener {

	private boolean didCompampability;
	private boolean isDev;
	private boolean isNewInstall;

	@Override
	public void earlyStartup() {
		// check the versions
		VersionManager.getManager().addVersionListener(this);
		VersionManager.getManager().checkVersions();

		if (!isNewInstall && didCompampability) {
			didCompampability = false;

			// open dialog to inform the user about changes
			SQDevInfobox info = new SQDevInfobox("Performed some compability changes in order to match new version",
					SWT.ICON_INFORMATION);

			info.open(false);
		}

		if (isDev) {
			isDev = false;

			SQDevInfobox info = new SQDevInfobox("You are running a development version of this plugin!\n"
					+ "Be aware that there might be some broken functions and/or other bugs "
					+ "in this software.\n\nThanks for supporting this plugin!", SWT.ICON_INFORMATION);

			info.open(false);
		}
	}

	@Override
	public void versionChanged(VersionChangeEvent event) {
		if (event.getNewVersion().getQualifier().startsWith("dev")) {
			isDev = true;
		}

		if (event.getOldVersion().equals(new Version(0, 0, 0))) {
			isNewInstall = true;
		}

		// perform compability operation and/or notifications
		switch (event.getPlugin()) {
		case EDITORS:
			break;
		case MISC:
			miscVersionChanged(event);
			break;
		case PREFERENCES:
			preferenceVersionChange(event);
			break;
		case SQFEDITOR:
			break;
		case UTIL:
			break;
		case WIZARDS:
			break;
		default:
			break;
		}
	}

	private void preferenceVersionChange(VersionChangeEvent event) {
		if (event.isUpdate()) {
			String msg;

			if (isNewInstall) {
				msg = "Check out the preferences in order to make sure " + "everything works as you want it to";
			} else {
				msg = "The preferences have been updated.\n\nMake sure you check them "
						+ "out in order to make the plugin work properly";
			}

			// inform the user about possible additions in the preferences
			SQDevInfobox info = new SQDevInfobox(msg, SWT.ICON_INFORMATION);

			info.open(false);
		}

		if ((!isNewInstall && event.getOldVersion().compareTo(new Version("0.4.1")) <= 0) || isDev) {
			// changed parse delay from seconds to milliseconds -> use default
			// value as current value is most likely invalid
			SQDevPreferenceUtil.getPreferenceStore().setToDefault(SQDevPreferenceConstants.SQDEV_EDITOR_PARSE_DELAY);

			SQDevPreferenceUtil.getPreferenceStore()
					.setToDefault(SQDevPreferenceConstants.SQDEV_INFO_ARMA_DOCUMENTS_DIRECTORY);

			didCompampability = true;
		}
	}

	private void miscVersionChanged(VersionChangeEvent event) {
		if (isNewInstall) {
			return;
		}

		if (event.getOldVersion().compareTo(new Version(0, 4, 3)) <= 0 || isDev) {
			// update the keyword list on the hard drive as there is the new
			// syntax attribute or the new syntax of the file itself
			ResourceManager manager = ResourceManager.getManager();

			try {
				manager.updateResource(ResourceManager.SQF_KEYWORDS_RESOURCE,
						FileSystemUtil.readAll(manager.getInternalResourceStream(ResourceManager.INTERNAL_KEYWORDS)));
			} catch (IOException | SQDevException e) {
				throw new SQDevCoreException("Failed at updating local keywords", e);
			}

			// add the new project nature, the new builder and the UTF-8
			// encoding to all existingSQDev-projects
			IProject[] allProjects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
			for (IProject project : allProjects) {
				if (project.getFile("link.sqdev").exists()) {
					try {
						boolean opened = project.isOpen();

						if (!opened) {
							project.open(new NullProgressMonitor());
						}

						// Make sure the project is being encoded in UTF-8
						IScopeContext projectScope = new ProjectScope(project);
						IEclipsePreferences projectNode = projectScope.getNode("org.eclipse.core.resources");
						projectNode.node("encoding").put("<project>", "UTF-8");
						projectNode.flush();

						// configure the SQDevProjectBuilder
						IProjectDescription description = project.getDescription();
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
						description.setNatureIds(new String[] { SQDevProjectNature.ID });

						project.setDescription(description, new NullProgressMonitor());
						
						if(!opened) {
							project.close(new NullProgressMonitor());
						}
					} catch (CoreException | BackingStoreException e) {
						e.printStackTrace();
					}

				}
			}

			didCompampability = true;
		}
	}
}
