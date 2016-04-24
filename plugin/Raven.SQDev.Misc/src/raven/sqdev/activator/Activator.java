package raven.sqdev.activator;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import raven.sqdev.pluginManager.SQDevPluginManager;


public class Activator extends AbstractUIPlugin {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.
	 * BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		super.start(bundleContext);
		SQDevPluginManager.getManager().register(this);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		SQDevPluginManager.getManager().unregister(this);
		
		super.stop(bundleContext);
	}
	
}
