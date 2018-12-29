package raven.sqdev.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import raven.sqdev.exceptions.SQDevCoreException;
import raven.sqdev.misc.FileSystemUtil;
import raven.sqdev.misc.SQDevInfobox;
import raven.sqdev.misc.SQDevPreferenceUtil;
import raven.sqdev.pluginManagement.ESQDevPlugin;
import raven.sqdev.pluginManagement.ResourceManager;

/**
 * A class containing general util methods
 * 
 * @author Raven
 * 
 */
public class Util {

	/**
	 * The name for the plugin info file
	 */
	public static final String PLUGIN_INFO_FILE_NAME = "SQDevPluginInfo.zip";

	/**
	 * Gets all available user profiles
	 */
	public static ArrayList<String> getProfiles() {
		ArrayList<String> profiles = new ArrayList<String>();

		// add the default profile that is always present
		profiles.add(System.getProperty("user.name"));

		File profileDir = new File(SQDevPreferenceUtil.getProfilesDocumentDirectory());

		if (!profileDir.exists() || !profileDir.isDirectory()) {
			return profiles;
		}

		// find the other profile names
		for (File currentFile : profileDir.listFiles()) {
			profiles.add(currentFile.getName());
		}

		return profiles;
	}

	/**
	 * Gets the path to the mission directory for the given profile
	 * 
	 * @param profile
	 *            The profile whose missions directory should be obtained. <b>Has to
	 *            exist!</b>
	 * @return The path to the respective missions directory
	 */
	public static IPath getMissionsDirectory(String profile) {
		// make sure the profile exists
		if (!getProfiles().contains(profile)) {
			String message = "The requested profile \"" + profile + "\" does not exist!";

			SQDevInfobox info = new SQDevInfobox(message, SWT.ICON_ERROR);

			info.open(false);

			throw new SQDevCoreException(message);
		}

		Path requestedPath = null;

		if (isSystemUserProfile(profile)) {
			// make sure the path leads to the correct folder
			requestedPath = new Path(SQDevPreferenceUtil.getDefaultDocumentsDirectory());

			if (requestedPath.lastSegment().contains("-")) {
				String lastSegment = requestedPath.lastSegment();

				requestedPath = (Path) requestedPath.removeLastSegments(1);

				lastSegment = lastSegment.substring(0, lastSegment.indexOf("-")).trim();

				// append the necessary segments
				requestedPath = (Path) requestedPath.append(lastSegment).append("missions");
			}
		} else {
			File profilesDir = new File(SQDevPreferenceUtil.getProfilesDocumentDirectory());

			if (!profilesDir.exists()) {
				String message = "Unable to locate profile \"" + profile + "\"";

				SQDevInfobox info = new SQDevInfobox(message, SWT.ICON_ERROR);

				info.open(false);

				throw new SQDevCoreException(message);
			}

			for (File current : profilesDir.listFiles()) {
				if (current.isDirectory() && current.getName().equals(profile)) {
					requestedPath = new Path(current.getAbsolutePath());

					requestedPath = (Path) requestedPath.append("missions");
				}
			}
		}

		if (requestedPath == null || !requestedPath.toFile().exists()) {
			String message = "The missions directory for the profile \"" + profile + "\" could not be found!";

			SQDevInfobox info = new SQDevInfobox(message, SWT.ICON_ERROR);

			info.open(false);

			throw new SQDevCoreException(message);
		}

		return requestedPath;
	}
	
	/**
	 * Gets the path to the mpMission directory for the given profile
	 * 
	 * @param profile
	 *            The profile whose missions directory should be obtained. <b>Has to
	 *            exist!</b>
	 * @return The path to the respective missions directory
	 */
	public static IPath getMpMissionsDirectory(String profile) {
		IPath missionsPath = getMissionsDirectory(profile);
		
		return missionsPath.removeLastSegments(1).append("mpMissions");
	}

	/**
	 * Finds out whether the given profile is the default profile Arma has created
	 * with the system's user name
	 * 
	 * @param profile
	 *            The profile name to check
	 */
	public static boolean isSystemUserProfile(String profile) {
		return System.getProperty("user.name").equals(profile);
	}

	/**
	 * Checks whether the given file is a mission folder
	 * 
	 * @param file
	 *            The file to check
	 */
	public static boolean isMissionFolder(File file) {
		Assert.isNotNull(file);

		if (!file.exists() || !file.isDirectory() || !file.getName().contains(".")) {
			return false;
		}

		for (File currentFile : file.listFiles()) {
			if (currentFile.getName().toLowerCase().equals("mission.sqm")) {
				// if it contains a mission.sqm it's a mission
				return true;
			}
		}

		return false;
	}

	/**
	 * Finds all available terrains
	 * 
	 * @return An array of terrain names
	 */
	public static String[] getTerrains() {
		return new String[] { "Altis", "Stratis", "VR", "Tanoa" };
	}

	/**
	 * Gets some standard information about the OS and the running eclipse version
	 * 
	 * @return All the information formatted into a String
	 */
	public static String getStandardInformation() {
		StringBuilder info = new StringBuilder();

		info.append("OS: " + System.getProperty("os.name") + "\n");
		info.append("Architecture: " + System.getProperty("osgi.arch") + "\n");
		info.append("Eclipse version: " + System.getProperty("eclipse.buildId") + "\n");
		info.append("SQDev plugin versions:\n");
		info.append("\t" + getSQDevpluginversions().replaceAll("\n", "\n\t"));

		return info.toString();
	}

	/**
	 * Gets the versions of the currently running SQDev plugins
	 */
	public static String getSQDevpluginversions() {
		StringBuilder builder = new StringBuilder();

		for (ESQDevPlugin plugin : ESQDevPlugin.values()) {
			builder.append(plugin.name() + ": " + plugin.getVersion() + "\n");
		}

		return builder.toString().trim();
	}

	/**
	 * Gets all necessary information about the plugin and the running eclipse
	 * version that should be provided along with an error report
	 * 
	 * @return The file pointing to the zip file
	 * @throws IOException
	 */
	public static File getPluginInfoZipAsFile() throws IOException {
		String zipPath = System.getProperty("java.io.tmpdir") + File.separator + PLUGIN_INFO_FILE_NAME;

		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipPath));

		out.putNextEntry(new ZipEntry("SQDevGeneralInformation.txt"));
		out.write(getStandardInformation().getBytes());
		out.closeEntry();

		IPath errorFilePath = ResourcesPlugin.getWorkspace().getRoot().getLocation();
		errorFilePath = errorFilePath.append(".metadata" + File.separator + ".log");

		out.putNextEntry(new ZipEntry("EclipseLog.txt"));
		FileInputStream in = new FileInputStream(errorFilePath.toFile());
		String logContent = FileSystemUtil.readAll(in);
		in.close();
		out.write(logContent.getBytes());
		out.closeEntry();

		String keywords = ResourceManager.getManager().getResourceContent(ResourceManager.SQF_KEYWORDS_RESOURCE);
		out.putNextEntry(new ZipEntry("SQFKeywords.txt"));
		out.write(keywords.getBytes());
		out.closeEntry();

		out.close();

		return new File(zipPath);
	}

	/**
	 * Closes and restarts all currently opened editors
	 */
	public static void restartAllEditors() {
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {

			@Override
			public void run() {
				// restart all SQF editors
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

				IEditorReference[] editors = page.getEditorReferences();

				page.closeAllEditors(true);

				for (IEditorReference currentEditor : editors) {
					try {
						page.openEditor(currentEditor.getEditorInput(), currentEditor.getId());
					} catch (PartInitException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
}
