package raven.sqdev.ui.perspectives;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class SQDevPerspective implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
		// add navigator TODO: make default as soon as it works properly
		// layout.addView("raven.sqdev.views.sqdevnavigator", IPageLayout.LEFT, (float)
		// 0.2,
		// layout.getEditorArea());

		layout.addView("org.eclipse.ui.views.ResourceNavigator", IPageLayout.LEFT, (float) 0.2,
				layout.getEditorArea());

		// add additional views to a bottom folder
		IFolderLayout folder = layout.createFolder("raven.sqdev.buttonFolder", IPageLayout.BOTTOM,
				(float) 0.75, layout.getEditorArea());

		folder.addView("raven.sqdev.ui.rptViewer");
		folder.addView("org.eclipse.ui.views.ProblemView");
		folder.addView("raven.sqdev.ui.modDependencyView");
		folder.addView("org.eclipse.ui.views.ProgressView");

		// add new default new file wizards
		layout.addNewWizardShortcut("raven.sqdev.wizards.sqfNewFileWizard");
		layout.addNewWizardShortcut("raven.sqdev.wizards.stringtable");
		layout.addNewWizardShortcut("raven.sqdev.wizards.sqdevproject");
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.file");
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder");
		layout.addNewWizardShortcut("raven.sqdev.wizards.sqdevFile");

		// add default views
		layout.addShowViewShortcut("raven.sqdev.ui.rptViewer");
		layout.addShowViewShortcut("org.eclipse.ui.views.ProblemView");
		layout.addShowViewShortcut("org.eclipse.ui.views.ProgressView");
		layout.addShowViewShortcut("raven.sqdev.ui.modDependencyView");
	}

}
