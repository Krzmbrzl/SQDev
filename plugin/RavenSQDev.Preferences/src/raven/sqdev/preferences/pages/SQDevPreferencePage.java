package raven.sqdev.preferences.pages;


import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import raven.sqdev.preferences.initializer.SQDevPreferenceInitializer;
import raven.sqdev.preferences.preferenceEditors.SQDevDirectoryPreferenceEditor;

public class SQDevPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {
	
	private Composite SQDevPreferencePageParent;
	
	public SQDevPreferencePage() {
		// create the main page with a dummy parent
		SQDevPreferencePageParent = new Composite(new Shell(), SWT.NULL);
	}
	
	@Override
	protected Control createContents(Composite parent) {
		SQDevPreferencePageParent.setParent(parent);
		
		SQDevPreferencePageParent.setLayout(createDefaultGridLayout(1));
		SQDevPreferencePageParent.setFont(parent.getFont());
		
		initialize();
		
		return SQDevPreferencePageParent;
	}
	
	@Override
	public void init(IWorkbench workbench) {
	}
	
	public void initialize() {
	
	}
	
	/**
	 * Creates a composite for the use as the container for a component that
	 * should be added to the preference page
	 * 
	 * @return
	 */
	public Composite createContainer() {
		return createDefaultComposite(SQDevPreferencePageParent);
	}
	
	/**
	 * Creates a default composite with preset GridLayout
	 * 
	 * @param parent
	 *            The parent of the composite ot create
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
		
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
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
	 * Creates a <code>DirectoryFieldEditor</code> and packs it into a container
	 * @param parent The parent of the container
	 * @param preferenceName the name of the preference this editor should work on
	 * @param labelName The text of the label that is displayed in front of the editor
	 * @return The container with the editor packed onto it
	 */
	public Composite createDirectoryPreference(Composite parent, String preferenceName,
			String labelName) {
		Composite container = createDefaultComposite(parent);
		
		new SQDevDirectoryPreferenceEditor(preferenceName, labelName, container).doLoad();
		
		return container;
	}
	
	/**
	 * Adds an <code>FieldEditor</code> to this site and registers it
	 * @param prefEditor The editor to be added and registered
	 */
	public void addPreferenceEditor(FieldEditor prefEditor) {
		// TODO: register preference editor + add respectively
	}
	
	/**
	 * Adds an <code>FieldEditor</code> to the given parent and registers the editor
	 * @param prefEditor The editor to be added and registered
	 * @param prefEditor The parent the editor will be attached to
	 */
	public void addPreferenceEditor(Composite parent, FieldEditor prefEditor) {
		
	}
	
	public void addGroup(Group group) {
		// TODO: add group and register Preference editors
	}
	
	//TODO: implement mehtods
	
	@Override
	public void performApply() {
		performOk();
	}
	
	@Override
	public boolean performOk() {
		return true;
	}
	
	@Override
	protected void performDefaults() {
		new SQDevPreferenceInitializer().initializeDefaultPreferences();
		
        updateApplyButton();
    }
	
	@Override
	public boolean isValid() {
		return true;
	}
	
}
