package raven.sqdev.actions;

import java.net.URL;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import raven.sqdev.misc.SQDevInfobox;
import raven.sqdev.pluginManagement.ResourceManager;

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
		
		setToolTipText(
				"Opens the respective wiki page in the system's default browser");
		
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
			PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser()
					.openURL(wikiURL);
		} catch (PartInitException e) {
			// tell the user that something went wrong
			SQDevInfobox info = new SQDevInfobox(
					"Failed at opening the wiki page", e);
			info.open();
		}
	}
	
}
