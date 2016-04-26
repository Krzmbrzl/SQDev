package raven.sqdev.startup;

import org.eclipse.swt.SWT;
import org.eclipse.ui.IStartup;

import raven.sqdev.interfaces.IVersionListener;
import raven.sqdev.misc.VersionChangeEvent;
import raven.sqdev.pluginManagement.VersionManager;
import raven.sqdev.util.SQDevInfobox;

/**
 * The SQDev class that gets loaded when the workbench is initialized
 * 
 * @author Raven
 * 		
 */
public class SQDevStarter implements IStartup, IVersionListener {
	
	@Override
	public void earlyStartup() {
		// check the versions
		VersionManager.getManager().addVersionListener(this);
		VersionManager.getManager().checkVersions();
	}
	
	@Override
	public void versionChanged(VersionChangeEvent event) {
		// perform compability operation and/or notifications
		switch (event.getPlugin()) {
			case EDITORS:
				break;
			case MISC:
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
	
}
