package raven.sqdev.editors.activator;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import raven.sqdev.pluginManager.SQDevPluginManager;
import raven.sqdev.util.SQDevPreferenceUtil;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {
	
	// The plug-in ID
	public static final String PLUGIN_ID = "Raven.SQDev.Editors"; //$NON-NLS-1$
	
	// The shared instance
	private static Activator plugin;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.
	 * BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		SQDevPluginManager.getManager().register(this);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.
	 * BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		
		SQDevPluginManager.getManager().unregister(this);
		
		super.stop(context);
	}
	
	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}
	
	/**
	 * This will return the preference store of
	 * <code>raven.sqdev.preferences</code> as this is where all SQDev
	 * preference are stored
	 */
	@Override
	public IPreferenceStore getPreferenceStore() {
		return SQDevPreferenceUtil.getPreferenceStore();
	}
	
}
