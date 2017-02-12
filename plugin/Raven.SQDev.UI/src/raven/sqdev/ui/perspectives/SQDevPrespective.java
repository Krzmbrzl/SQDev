package raven.sqdev.ui.perspectives;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class SQDevPrespective implements IPerspectiveFactory {
	
	@Override
	public void createInitialLayout(IPageLayout layout) {
		// add navigator
		layout.addView("org.eclipse.ui.views.ResourceNavigator", IPageLayout.LEFT, (float) 0.2,
				layout.getEditorArea());
		
		// add additional views to a bottom folder
		IFolderLayout folder = layout.createFolder("raven.sqdev.buttonFolder", IPageLayout.BOTTOM,
				(float) 0.2, layout.getEditorArea());
		
		folder.addView("raven.sqdev.ui.rptViewer");
		folder.addView("org.eclipse.ui.views.ProblemView");
		folder.addView("org.eclipse.ui.views.ProgressView");
		
		
		// add new default new file wizards
		layout.addNewWizardShortcut("raven.sqdev.wizards.sqfNewFileWizard");
		layout.addNewWizardShortcut("raven.sqdev.wizards.stringtable");
		layout.addNewWizardShortcut("raven.sqdev.wizards.sqdevproject");
	}
	
}
