package raven.sqdev.wizards.stringtable;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import raven.sqdev.util.EFileType;

public class StringTableWizard extends Wizard implements INewWizard {
	
	private StringTableWizardPage page;
	
	public StringTableWizard() {
		super();
	}
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		page = new StringTableWizardPage("StringTableWizardPage");
		addPage(page);
	}
	
	@Override
	public boolean performFinish() {
		IProject project = page.getProject();
		
		EFileType stringTable = EFileType.STRINGTABLE;
		stringTable.setPath(project.getLocation().toOSString());
		stringTable.create("StringTable");
		
		return true;
	}
	
}
