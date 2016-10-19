package raven.sqdev.wizards;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

import raven.sqdev.util.EProjectType;
import raven.sqdev.util.SQDevInfobox;

/**
 * The base class of all new project wizards used throughout the SQDev plugin
 * 
 * @author Raven
 *
 */
public abstract class SQDevBaseNewProjectWizard extends Wizard
		implements INewWizard, IExecutableExtension {
	
	/**
	 * The configuration element of this wizard
	 */
	protected IConfigurationElement configElement;
	
	
	public SQDevBaseNewProjectWizard() {
		super();
	}
	
	/**
	 * Gets the project type this wizard should create. Will be called on
	 * finishing this wizard
	 * 
	 * @return The respective project type with all necessary information set
	 */
	public abstract EProjectType getProjectType();
	
	@Override
	public abstract void init(IWorkbench workbench, IStructuredSelection selection);
	
	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName,
			Object data) throws CoreException {
		// store config element
		configElement = config;
	}
	
	@Override
	public boolean performFinish() {
		try {
			getProjectType().create(ResourcesPlugin.getWorkspace().getRoot());
			
			BasicNewProjectResourceWizard.updatePerspective(configElement);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			
			SQDevInfobox info = new SQDevInfobox("Failed at creating project", e);
			info.open(false);
			
			return false;
		}
	}
	
}
