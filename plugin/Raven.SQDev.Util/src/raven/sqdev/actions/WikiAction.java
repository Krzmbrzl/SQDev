package raven.sqdev.actions;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.ImageData;

import raven.sqdev.exceptions.SQDevException;
import raven.sqdev.pluginManagement.ResourceManager;
import raven.sqdev.util.SQDevInfobox;

/**
 * The action that will take the user to the given wiki page when activated
 * 
 * @author Raven
 * 		
 */
public class WikiAction extends Action {
	
	/**
	 * The URL to the wiki page
	 */
	private URL wikiURL;
	
	public WikiAction(URL wikiURL) {
		Assert.isTrue(wikiURL != null, "Nullargument in WikiAction!");
		
		this.wikiURL = wikiURL;
		
		setToolTipText("Opens the respective wiki page in the system's default browser");
		
		setImageDescriptor(new ImageDescriptor() {
			
			@Override
			public ImageData getImageData() {
				ImageData data = new ImageData(ResourceManager.getManager()
						.getInternalResourceStream(ResourceManager.WIKI_ICON));
						
				return data;
			}
		});
	}
	
	@Override
	public void run() {
		try {
			if (Desktop.isDesktopSupported()) {
				// open the wiki page
				Desktop desktop = Desktop.getDesktop();
				desktop.browse(wikiURL.toURI());
			} else {
				throw new SQDevException("The system does not support the desktop API!");
			}
		} catch (SQDevException | IOException | URISyntaxException e) {
			// tell the user that something went wrong
			SQDevInfobox info = new SQDevInfobox("Failed at opening the wiki page", e);
			info.open();
		}
	}
	
}
