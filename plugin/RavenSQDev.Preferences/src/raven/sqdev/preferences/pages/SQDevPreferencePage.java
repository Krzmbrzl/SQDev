package raven.sqdev.preferences.pages;


import java.util.ArrayList;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import raven.sqdev.exceptions.SQDevException;
import raven.sqdev.preferences.preferenceEditors.ISQDevPreferenceEditor;
import raven.sqdev.preferences.util.ISQDevPreferenceEditorListener;
import raven.sqdev.preferences.util.SQDevChangeEvent;
import raven.sqdev.preferences.util.SQDevPreferenceComposite;
import raven.sqdev.util.SQDevPreferenceUtil;

/**
 * The base class for all SQDevPreferencePages that contains the low level
 * communication with the preferencePage Framework as well as an integrated
 * LayoutManager that lays out the different preferenceEditors.
 * 
 * @author Raven
 * 		
 */
public abstract class SQDevPreferencePage extends PreferencePage
		implements ISQDevPreferencePage, ISQDevPreferenceEditorListener {
		
	private SQDevPreferenceComposite SQDevPreferencePageContainer;
	private ArrayList<ISQDevPreferenceEditor> editors;
	
	public SQDevPreferencePage() {
		// create the main page with a dummy parent
		SQDevPreferencePageContainer = new SQDevPreferenceComposite(new Shell(), SWT.NULL, this);
		
		setEditors(new ArrayList<ISQDevPreferenceEditor>());
	}
	
	@Override
	protected Control createContents(Composite parent) {
		SQDevPreferencePageContainer.setParent(parent);
		
		SQDevPreferencePageContainer.setLayout(createDefaultGridLayout(1));
		SQDevPreferencePageContainer.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
		SQDevPreferencePageContainer.setFont(parent.getFont());
		
		initialize();
		
		Composite spacer = new Composite(SQDevPreferencePageContainer, SWT.NULL);
		spacer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		return SQDevPreferencePageContainer;
	}
	
	/**
	 * Sets the page up
	 */
	public void initialize() {
		// set this as the listener of the editors
		for (ISQDevPreferenceEditor currentEditor : getEditors()) {
			currentEditor.addChangeListener(this);
		}
		
		ArrayList<Composite> containerList = new ArrayList<Composite>();
		
		// get all different containers
		for (ISQDevPreferenceEditor currentEditor : getEditors()) {
			Composite container = currentEditor.getContainer();
			
			container.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
			
			if (!containerList.contains(container)) {
				containerList.add(container);
			}
		}
		
		// create an underlying component that will hold all of the added
		// components
		Composite mainContainer = new Composite(SQDevPreferencePageContainer, SWT.NULL);
		GridLayout mainContainerLayout = new GridLayout();
		mainContainerLayout.verticalSpacing = 25;
		mainContainer.setLayout(mainContainerLayout);
		mainContainer.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
		
		for (Composite current : containerList) {
			// put current on the mainContainer
			if (current instanceof SQDevPreferenceComposite) {
				current.setParent(mainContainer);
			} else {
				Composite previousContainer = current;
				Composite underlyingContainer = current.getParent();
				
				while (underlyingContainer != null) {
					if (underlyingContainer instanceof SQDevPreferenceComposite) {
						previousContainer.setParent(mainContainer);
						break;
					} else {
						previousContainer = underlyingContainer;
						underlyingContainer = underlyingContainer.getParent();
					}
				}
				
				if (underlyingContainer == null) {
					// indicate that a problem occured
					try {
						throw new SQDevException("Failed at finding the underlying container!");
					} catch (SQDevException e) {
						e.printStackTrace();
					}
				}
			}
			
			
			// create the shared container
			Composite sharedContainer = new Composite(current, SWT.NULL);
			
			
			// find out the maximum amount of components of the editors placed
			// on this shared container
			int maxComponents = -1;
			for (ISQDevPreferenceEditor currentEditor : getEditors()) {
				if ((currentEditor.getComponentCount() > maxComponents)
						&& currentEditor.getContainer().equals(current)) {
					maxComponents = currentEditor.getComponentCount();
				}
			}
			
			for (ISQDevPreferenceEditor currentEditor : getEditors()) {
				if (currentEditor.getContainer().equals(current)) {
					// put all editors that have the same container on one new
					// sharedContainer
					currentEditor.setContainer(sharedContainer);
					// make all editors fill out the later crated layout
					currentEditor.matchComponentCount(maxComponents);
					// create the GUI components of this editor
					currentEditor.createComponents();
				}
			}
			
			GridLayout layout;
			
			if (sharedContainer.getLayout() == null
					|| !(sharedContainer.getLayout() instanceof GridLayout)) {
				layout = new GridLayout();
			} else {
				layout = (GridLayout) sharedContainer.getLayout();
			}
			
			layout.numColumns = maxComponents;
			
			sharedContainer.setLayout(layout);
			sharedContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		}
	}
	
	/**
	 * Creates a composite for the use as the container for a component that
	 * should be added to the preference page
	 * 
	 * @return
	 */
	public Composite createContainer() {
		return createDefaultComposite(SQDevPreferencePageContainer);
	}
	
	/**
	 * Creates a default composite with preset GridLayout
	 * 
	 * @param parent
	 *            The parent to the composite ot create
	 * @return
	 */
	public Composite createDefaultComposite(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		
		container.setLayout(layout);
		container.setFont(parent.getFont());
		
		container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		return container;
	}
	
	/**
	 * Creates a group for use to add a group of components to this page
	 * 
	 * @param text
	 *            The text the group should display on top
	 * @return The created group
	 */
	public Group createGroup(String text) {
		Group group = new Group(createContainer(), SWT.SHADOW_ETCHED_IN);
		group.setText(text);
		group.setLayout(createDefaultGridLayout(1));
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		return group;
	}
	
	/**
	 * Creates a default <code>GridLayout</code> with the given amount of
	 * columns
	 * 
	 * @param columns
	 *            The amount of columns to use
	 * @return
	 */
	public GridLayout createDefaultGridLayout(int columns) {
		GridLayout layout = new GridLayout();
		layout.numColumns = columns;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		
		return layout;
	}
	
	/**
	 * Creates a container with a label on it for providing a description
	 * 
	 * @param parent
	 *            The parent of the conatainer
	 * @param description
	 *            The description text
	 * @return The container with the label on top of it
	 */
	public Composite createDescription(Composite parent, String description) {
		Composite container = new Composite(parent, SWT.NULL);
		
		container.setLayout(createDefaultGridLayout(1));
		container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Label descriptionLabel = new Label(container, SWT.NULL);
		
		descriptionLabel.setText(description);
		
		return container;
	}
	
	/**
	 * Adds an <code>FieldEditor</code> to this site and registers it
	 * 
	 * @param prefEditor
	 *            The editor to be added and registered
	 */
	public void addPreferenceEditor(ISQDevPreferenceEditor prefEditor) {
		registerEditor(prefEditor);
	}
	
	@Override
	public void performApply() {
		save();
	}
	
	/**
	 * Saves all unsaved preferences (only when the page is in a valid state
	 */
	public void save() {
		if (!isValid()) {
			return;
		}
		
		for (ISQDevPreferenceEditor currentEditor : getEditors()) {
			if (currentEditor.needsSave()) {
				if (!currentEditor.doSave()) {
					return;
				}
			}
		}
	}
	
	@Override
	public boolean performOk() {
		for (ISQDevPreferenceEditor currentEditor : getEditors()) {
			if (currentEditor.needsSave()) {
				if (!SQDevPreferenceUtil.alwaysSaveOnExit()) {
					MessageBox messageBox = new MessageBox(
							PlatformUI.getWorkbench().getDisplay().getActiveShell(),
							SWT.ICON_WARNING | SWT.YES | SWT.NO);
					messageBox.setMessage(
							"There are some unsaved preference changes. Do you want to save them now?");
					messageBox.setText("Unsaved Changes");
					int response = messageBox.open();
					if (response == SWT.YES) {
						// save and then exit
						save();
						return true;
					} else {
						// exit without saving
						return true;
					}
				} else {
					// save unpromted
					save();
					return true;
				}
			}
		}
		
		return true;
		
	}
	
	@Override
	public boolean isValid() {
		for (ISQDevPreferenceEditor currentEditor : getEditors()) {
			if (!currentEditor.isValid()) {
				setErrorMessage(currentEditor.getStatusHint());
				return false;
			}
		}
		
		if (getErrorMessage() != null) {
			setErrorMessage(null);
		}
		
		return true;
	}
	
	public ArrayList<ISQDevPreferenceEditor> getEditors() {
		return editors;
	}
	
	private void setEditors(ArrayList<ISQDevPreferenceEditor> editors) {
		this.editors = editors;
	}
	
	/**
	 * Registers an editor
	 * 
	 * @param editor
	 *            The editor to be registered
	 */
	private void registerEditor(ISQDevPreferenceEditor editor) {
		getEditors().add(editor);
	}
	
	@Override
	public void editorStateChanged(SQDevChangeEvent event) {
		if (event.getContext().equals(SQDevChangeEvent.SQDEV_STATUS_CHANGED)
				|| event.getContext().equals(SQDevChangeEvent.SQDEV_VALUE_LOADED))
			// update the validation state of the site when an editor has
			// changed it's value
			setValid(isValid());
	}
	
	@Override
	public void performDefaults() {
		for (ISQDevPreferenceEditor currentEditor : getEditors()) {
			currentEditor.doLoadDefault();
		}
	}
	
}
