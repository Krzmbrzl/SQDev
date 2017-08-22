package raven.sqdev.wizards;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

import raven.sqdev.util.EProjectType;
import raven.sqdev.util.SQDevInfobox;
import raven.sqdev.utilInterfaces.ISQDevInformationProvider;

/**
 * The base class of all new project wizards used throughout the SQDev plugin
 * 
 * @author Raven
 *
 */
public abstract class SQDevBaseNewProjectWizard extends Wizard
		implements INewWizard, IExecutableExtension, ISQDevInformationProvider {
	
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
	public abstract void init(IWorkbench workbench,
			IStructuredSelection selection);
	
	@Override
	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
		// store config element
		configElement = config;
	}
	
	@Override
	public boolean performFinish() {
		try {
			EProjectType project = getProjectType();
			project.setInformation(getInformation());
			IProject createdProject = project
					.create(ResourcesPlugin.getWorkspace().getRoot());
			
			// Make sure the project is being encoded in UTF-8
			IScopeContext projectScope = new ProjectScope(createdProject);
			IEclipsePreferences projectNode = projectScope
					.getNode("org.eclipse.core.resources");
			projectNode.node("encoding").put("<project>", "UTF-8");
			projectNode.flush();
			
			// configure the SQDevProjectBuilder
			boolean addBuilder = true;
			IProjectDescription description = createdProject.getDescription();
			ICommand[] commands = description.getBuildSpec();
			for (int i = 0; i < commands.length; ++i) {
				if (commands[i].getBuilderName()
						.equals("raven.sqdev.builder.sqdevprojectbuilder")) {
					addBuilder = false;
				}
			}
			if (addBuilder) {
				// add builder to project
				ICommand command = description.newCommand();
				command.setBuilderName(
						"raven.sqdev.builder.sqdevprojectbuilder");
				ICommand[] nc = new ICommand[commands.length + 1];
				// Add it before other builders.
				System.arraycopy(commands, 0, nc, 1, commands.length);
				nc[0] = command;
				description.setBuildSpec(nc);
				createdProject.setDescription(description, null);
			}
			
			BasicNewProjectResourceWizard.updatePerspective(configElement);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			
			SQDevInfobox info = new SQDevInfobox("Failed at creating project",
					e);
			info.open(false);
			
			return false;
		}
	}
	
}
