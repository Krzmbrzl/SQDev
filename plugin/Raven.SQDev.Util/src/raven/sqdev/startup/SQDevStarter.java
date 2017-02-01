package raven.sqdev.startup;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.ui.IStartup;
import org.osgi.framework.Version;

import raven.sqdev.constants.SQDevPreferenceConstants;
import raven.sqdev.exceptions.SQDevCoreException;
import raven.sqdev.exceptions.SQDevException;
import raven.sqdev.interfaces.IVersionListener;
import raven.sqdev.misc.VersionChangeEvent;
import raven.sqdev.pluginManagement.ResourceManager;
import raven.sqdev.pluginManagement.VersionManager;
import raven.sqdev.util.FileUtil;
import raven.sqdev.util.SQDevInfobox;
import raven.sqdev.util.SQDevPreferenceUtil;

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
			SQDevInfobox info = new SQDevInfobox(
					"Performed some compability changes in order to match new version",
					SWT.ICON_INFORMATION);
			
			info.open(false);
		}
		
		if (isDev) {
			isDev = false;
			
			SQDevInfobox info = new SQDevInfobox(
					"You are running a development version of this plugin!\n"
							+ "Be aware that there might be some broken functions and/or other bugs "
							+ "in this software.\n\nThanks for supporting this plugin!",
					SWT.ICON_INFORMATION);
			
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
				msg = "Check out the preferences in order to make sure "
						+ "everything works as you want it to";
			} else {
				msg = "The preferences have been updated.\n\nMake sure you check them "
						+ "out in order to make the plugin work properly";
			}
			
			// inform the user about possible additions in the preferences
			SQDevInfobox info = new SQDevInfobox(msg, SWT.ICON_INFORMATION);
			
			info.open(false);
		}
		
		if ((!isNewInstall
				&& event.getOldVersion().compareTo(new Version("0.4.1")) <= 0)
				|| isDev) {
			// changed parse delay from seconds to milliseconds -> use default
			// value as current value is most likely invalid
			SQDevPreferenceUtil.getPreferenceStore().setToDefault(
					SQDevPreferenceConstants.SQDEV_EDITOR_PARSE_DELAY);
			
			SQDevPreferenceUtil.getPreferenceStore().setToDefault(
					SQDevPreferenceConstants.SQDEV_INFO_ARMA_DOCUMENTS_DIRECTORY);
			
			didCompampability = true;
		}
	}
	
	private void miscVersionChanged(VersionChangeEvent event) {
		if (isNewInstall) {
			return;
		}
		
		if (event.getOldVersion().compareTo(new Version(0, 3, 1)) < 0
				|| isDev) {
			// update the keyword list on the hard drive as there is the new
			// syntax attribute or the new syntax of the file itself
			ResourceManager manager = ResourceManager.getManager();
			
			try {
				manager.updateResource(ResourceManager.KEYWORDS_RESOURCE,
						FileUtil.readAll(manager.getInternalResourceStream(
								ResourceManager.INTERNAL_KEYWORDS)));
			} catch (IOException | SQDevException e) {
				throw new SQDevCoreException(
						"Failed at updating local keywords", e);
			}
			
			didCompampability = true;
		}
	}
}
