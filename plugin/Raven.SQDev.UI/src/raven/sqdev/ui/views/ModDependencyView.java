package raven.sqdev.ui.views;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.navigator.IResourceNavigator;

import raven.sqdev.exceptions.IllegalAccessStateException;
import raven.sqdev.exceptions.SQDevException;
import raven.sqdev.misc.ModUtils;
import raven.sqdev.misc.SQDevInfobox;
import raven.sqdev.misc.SQDevPreferenceUtil;
import raven.sqdev.sqdevFile.ESQDevFileAnnotation;
import raven.sqdev.sqdevFile.SQDevFile;
import raven.sqdev.ui.widgets.CheckboxList;
import raven.sqdev.util.ProjectUtil;

/**
 * This view represents a graphical way to edit the project.sqdev-file in a
 * project in terms of mod-dependencies
 * 
 * @author Raven
 *
 */
@SuppressWarnings("deprecation")
public class ModDependencyView extends ViewPart implements IPartListener, ISelectionChangedListener, Listener {

	public static final String ID = "raven.sqdev.ui.modDependencyView";

	protected static final String NO_ACTIVE_PROJECT = "There is currently no active SQDev-project available ...";

	CheckboxList modList;
	IProject activeProject;
	IWorkbenchPart activePart;
	IWorkbenchPart previouslyActivePart;
	Collection<String> installedModFoldersNames;
	ISelectionProvider listenedProvider;

	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);

		site.getPage().addPartListener(this);
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());

		modList = new CheckboxList(parent, SWT.NONE);
		modList.setText(NO_ACTIVE_PROJECT);

		partActivated(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor());

		modList.addListener(SWT.Selection, this);
	}

	protected void updateList(IProject project, boolean refreshInstalledMods) {
		if (project != null) {
			modList.setText("Select the mod-dependencies of the \"" + project.getName() + "\" project.");

			ArrayList<File> modFolders = new ArrayList<>();

			if (refreshInstalledMods || installedModFoldersNames == null) {
				// get installed mods
				ModUtils.getModFolders(new File(SQDevPreferenceUtil.getArmaProgramDirectory()), modFolders);

				installedModFoldersNames = new ArrayList<>(modFolders.size());
				for (File current : modFolders) {
					installedModFoldersNames.add(current.getName());
				}
			}

			// get mods defined in the project-file
			List<String> projectMods = ProjectUtil.getProjectModNames(project);

			// Make sure all mod-names start with an @
			for (int i = 0; i < projectMods.size(); i++) {
				String current = projectMods.get(i);

				if (!current.startsWith("@")) {
					projectMods.set(i, "@" + current);
				}
			}

			List<String> selectedMods = new ArrayList<>(projectMods);

			// add all installed mods but avoid duplicates
			for (String currentMod : installedModFoldersNames) {
				if (!projectMods.contains(currentMod)) {
					projectMods.add(currentMod);
				}
			}

			modList.setLabels(projectMods);
			modList.setSelection(selectedMods);

			// disable if the active project's project.sqdev is currently opened in the
			// active editor
			// re-enable otherwise
			IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			if (editor != null && editor.getEditorInput() instanceof IFileEditorInput) {
				IFile file = ((IFileEditorInput) editor.getEditorInput()).getFile();

				IPath location = file.getRawLocation();
				SQDevFile projectFile = ProjectUtil.getProjectFile(activeProject);

				// the user is currently editing the project.sqdev-file manually -> disable the
				// list
				modList.setEnabled(!(location != null && location.toFile().equals(projectFile)));
			} else {
				modList.setEnabled(true);
			}
		} else {
			modList.clearSelection();
			modList.setText(NO_ACTIVE_PROJECT);
		}
	}

	@Override
	public void setFocus() {
		modList.setFocus();
	}

	@Override
	public void partActivated(IWorkbenchPart part) {
		if (part == this) {
			// ignore focus on self
			// the list has been set to display no-project selected on deactivation though
			// -> pretend that the previously selected par is being "re-selected"
			if (previouslyActivePart != null) {
				part = previouslyActivePart;
			} else {
				return;
			}
		}

		IProject associatedProject = null;

		if (part instanceof IEditorPart) {
			// select project based in active editor
			IEditorPart editor = (IEditorPart) part;

			if (editor.getEditorInput() instanceof IFileEditorInput) {
				IFile openedFile = ((IFileEditorInput) editor.getEditorInput()).getFile();

				associatedProject = openedFile.getProject();
			}
		} else {
			ISelection selection = null;
			if (part instanceof IResourceNavigator) {
				// get selection from ResourceNavigator
				IResourceNavigator nav = ((IResourceNavigator) part);
				selection = nav.getViewer().getSelection();

				listenedProvider = nav.getViewer();
			} else {
				if (part instanceof CommonNavigator) {
					// get selection from CommonNavigator
					CommonNavigator nav = (CommonNavigator) part;
					selection = nav.getCommonViewer().getSelection();

					listenedProvider = nav.getCommonViewer();
				}
			}

			if (selection instanceof ITreeSelection) {
				ITreeSelection treeSelection = (ITreeSelection) selection;

				if (treeSelection.getFirstElement() instanceof IResource) {
					associatedProject = ((IResource) treeSelection.getFirstElement()).getProject();
				}

				// watch out for selection changes
				listenedProvider.addSelectionChangedListener(this);
			} else {
				listenedProvider = null;
			}
		}

		if (associatedProject != null && activeProject != associatedProject
				&& ProjectUtil.isSQDevProject(associatedProject)) {
			activeProject = associatedProject;
			activePart = part;
			updateList(activeProject, true);
		}
	}

	@Override
	public void partBroughtToTop(IWorkbenchPart part) {
		// ignore
	}

	@Override
	public void partClosed(IWorkbenchPart part) {
		// ignore
	}

	@Override
	public void partDeactivated(IWorkbenchPart part) {
		if (activePart == part) {
			previouslyActivePart = activePart;
			activePart = null;
			activeProject = null;

			if (listenedProvider != null) {
				listenedProvider.removeSelectionChangedListener(this);
				listenedProvider = null;
			}

			updateList(null, false);
		}
	}

	@Override
	public void partOpened(IWorkbenchPart part) {
		// ignore
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		ISelection selection = event.getSelection();

		if (selection instanceof ITreeSelection) {
			ITreeSelection treeSelection = (ITreeSelection) selection;

			if (treeSelection.getFirstElement() instanceof IResource) {
				IProject associatedProject = ((IResource) treeSelection.getFirstElement()).getProject();

				if (associatedProject != null && associatedProject != activeProject) {
					activeProject = associatedProject;
					updateList(associatedProject, false);
				}
			}
		}
	}

	@Override
	public void handleEvent(Event event) {
		// change project.sqdev accordingly
		SQDevFile projectFile = ProjectUtil.getProjectFile(activeProject);
		if (projectFile == null) {
			// create new one
			try {
				projectFile = ProjectUtil.createProjectFile(activeProject);
			} catch (IllegalAccessStateException | IOException | CoreException e) {
				e.printStackTrace();

				SQDevInfobox info = new SQDevInfobox("Failed at creating project.sqdev", e);
				info.open();
				return;
			}
		}

		ESQDevFileAnnotation.MOD.clear();
		// remove the @ before setting the value
		ESQDevFileAnnotation.MOD.addValue(event.text.substring(1));

		try {
			if (((boolean) event.data)) {
				// Add to SQDevFile
				projectFile.addAnnotation(ESQDevFileAnnotation.MOD);
			} else {
				projectFile.removeAll(
						Pattern.compile("(\\r?\\n|^)" + Pattern.quote("@" + ESQDevFileAnnotation.MOD) + "\\h*\".*\""));
			}

			activeProject.refreshLocal(IProject.DEPTH_ONE, null);
		} catch (IOException | SQDevException | CoreException e) {
			SQDevInfobox info = new SQDevInfobox("Failed at modifying project.sqdev", e);
			info.open();
		}
	}

	@Override
	public void dispose() {
		getSite().getPage().removePartListener(this);
		super.dispose();
	}

}
