package raven.sqdev.startup;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.ui.IStartup;
import org.osgi.framework.Version;

import raven.sqdev.exceptions.SQDevCoreException;
import raven.sqdev.exceptions.SQDevException;
import raven.sqdev.interfaces.IVersionListener;
import raven.sqdev.misc.VersionChangeEvent;
import raven.sqdev.pluginManagement.ResourceManager;
import raven.sqdev.pluginManagement.VersionManager;
import raven.sqdev.util.FileUtil;
import raven.sqdev.util.SQDevInfobox;

/**
 * The SQDev class that gets loaded when the workbench is initialized
 * 
 * @author Raven
 * 
 */
public class SQDevStarter implements IStartup, IVersionListener {
	
	private boolean didCompampability;
	
	@Override
	public void earlyStartup() {
		// check the versions
		VersionManager.getManager().addVersionListener(this);
		VersionManager.getManager().checkVersions();
		
		if (didCompampability) {
			didCompampability = false;
			
			// open dialog to inform the user about changes
			SQDevInfobox info = new SQDevInfobox(
					"Performed some compability changes in order to match new version",
					SWT.ICON_INFORMATION);
			
			info.open(false);
		}
	}
	
	@Override
	public void versionChanged(VersionChangeEvent event) {
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
			// inform the user about possible additions in the preferences
			SQDevInfobox info = new SQDevInfobox(
					"The preferences where updated.\n\nMake sure you check them "
							+ "out in order to make the plugin work properly",
					SWT.ICON_INFORMATION);
			
			info.open();
		}
	}
	
	private void miscVersionChanged(VersionChangeEvent event) {
		if (event.getNewVersion().compareTo(new Version(0, 2, 0)) > 0) {
			// update the keyword list on the hard drive as there is the new
			// syntax attribute
			ResourceManager manager = ResourceManager.getManager();
			
			try {
				manager.updateResource(ResourceManager.KEYWORDS_RESOURCE, FileUtil.readAll(
						manager.getInternalResourceStream(ResourceManager.INTERNAL_KEYWORDS)));
			} catch (IOException | SQDevException e) {
				throw new SQDevCoreException("Failed at updating local keywords", e);
			}
			
			didCompampability = true;
		}
	}
	
}
