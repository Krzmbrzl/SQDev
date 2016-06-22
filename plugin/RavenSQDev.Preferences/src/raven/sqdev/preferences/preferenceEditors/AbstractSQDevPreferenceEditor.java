package raven.sqdev.preferences.preferenceEditors;

import java.util.ArrayList;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;

import raven.sqdev.preferences.activator.Activator;
import raven.sqdev.preferences.pages.ISQDevPreferencePage;
import raven.sqdev.preferences.util.EStatus;
import raven.sqdev.preferences.util.ISQDevPreferenceEditorListener;
import raven.sqdev.preferences.util.SQDevChangeEvent;
import raven.sqdev.preferences.util.SQDevPreferenceComposite;

/**
 * The abstract base class for every SQDevPreferenceEditor that defines some low
 * level behaviour each editor has in common
 * 
 * @author Raven
 *		
 */
public abstract class AbstractSQDevPreferenceEditor
		implements ISQDevPreferenceEditor, IPropertyChangeListener {
	/**
	 * The status of this editor
	 */
	private EStatus status;
	
	/**
	 * The key (identifier) of the preference this editor is working on
	 */
	private String preferenceKey;
	
	/**
	 * The text displayed at the created label
	 */
	private String labelText;
	
	/**
	 * The container the GUI elements of this editor are created in
	 */
	private Composite container;
	
	/**
	 * The preferenceStore this editor works on
	 */
	private IPreferenceStore store;
	
	/**
	 * The <code>ISQDevPreferencePage</code> this editor is placed on.
	 */
	private ISQDevPreferencePage page;
	
	/**
	 * A list of registered editors
	 */
	private ArrayList<ISQDevPreferenceEditorListener> listener;
	
	/**
	 * How many empty components hav to be created in order to match the gven
	 * component count
	 */
	private int emptyComponentsToCreate;
	
	/**
	 * The tooltip that belongs to this editor
	 */
	private String tooltip;
	
	/**
	 * The label used by this editor
	 */
	protected Label label;
	
	/**
	 * The ID of this editor
	 */
	private String ID;
	
	/**
	 * Creates a new <code>SQDevPreferenceEditor</code>
	 * 
	 * @param preferenceKey
	 *            The key of the preference to work on
	 * @param labelText
	 *            The text of the label
	 * @param container
	 *            The container the GUI elements should be placed in
	 * @param resolve
	 *            Indicating whether or not the program should try to resolve
	 *            the preferencePage itself
	 */
	private AbstractSQDevPreferenceEditor(String preferenceKey, String labelText,
			Composite container, boolean resolve, String tooltip) {
		// initialize the listener list
		this.listener = new ArrayList<ISQDevPreferenceEditorListener>();
		
		// store the values
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setPreferenceKey(preferenceKey);
		setLabelText(labelText);
		setContainer(container);
		setStatus(EStatus.OK);
		setTooltip(tooltip);
		setID(preferenceKey);
		
		if (resolve) {
			// try to find the preferencePage yourself
			setPreferencePage(resolvePreferencePage());
		}
		
		// initialize the editor with some content to display
		doLoad();
		
		emptyComponentsToCreate = 0;
	}
	
	/**
	 * Creates a new <code>SQDevPreferenceEditor</code><br>
	 * <b>This constructor can only be used if the given container is an
	 * instance of <code>ISQDevPreferenceComposite</code> or one of it's parents
	 * is. </b>
	 * 
	 * @param preferenceKey
	 *            The key of the preference to work on
	 * @param labelText
	 *            The text of the label
	 * @param container
	 *            The container the GUI elements should be placed in
	 */
	public AbstractSQDevPreferenceEditor(String preferenceKey, String labelText,
			Composite container) {
		this(preferenceKey, labelText, container, true, "");
	}
	
	/**
	 * Creates a new <code>SQDevPreferenceEditor</code><br>
	 * <b>This constructor can only be used if the given container is an
	 * instance of <code>ISQDevPreferenceComposite</code> or one of it's parents
	 * is. </b>
	 * 
	 * @param preferenceKey
	 *            The key of the preference to work on
	 * @param labelText
	 *            The text of the label
	 * @param tooltip
	 *            The tooltip that will be displayed on the editor's preference
	 *            value-field
	 * @param container
	 *            The container the GUI elements should be placed in
	 */
	public AbstractSQDevPreferenceEditor(String preferenceKey, String labelText, String tooltip,
			Composite container) {
		this(preferenceKey, labelText, container, true, tooltip);
	}
	
	/**
	 * Creates a new <code>SQDevPreferenceEditor</code>
	 * 
	 * @param preferenceKey
	 *            The key of the preference to work on
	 * @param labelText
	 *            The text of the label
	 * @param container
	 *            The container the GUI elements should be placed in
	 * @param page
	 *            The <code>ISQDevPreferencePage</code> this editor is apllied
	 *            to
	 */
	public AbstractSQDevPreferenceEditor(String preferenceKey, String labelText,
			Composite container, ISQDevPreferencePage page) {
			
		this(preferenceKey, labelText, container, false, "");
		
		setPreferencePage(page);
	}
	
	/**
	 * Creates a new <code>SQDevPreferenceEditor</code>
	 * 
	 * @param preferenceKey
	 *            The key of the preference to work on
	 * @param labelText
	 *            The text of the label
	 * @param tooltip
	 *            The tooltip that will be displayed on the editor's preference
	 *            value-field
	 * @param container
	 *            The container the GUI elements should be placed in
	 * @param page
	 *            The <code>ISQDevPreferencePage</code> this editor is apllied
	 *            to
	 */
	public AbstractSQDevPreferenceEditor(String preferenceKey, String labelText, String tooltip,
			Composite container, ISQDevPreferencePage page) {
		this(preferenceKey, labelText, container, false, tooltip);
		
		setPreferencePage(page);
	}
	
	@Override
	public boolean doSave() {
		if (!isValid()) {
			MessageDialog.open(MessageDialog.ERROR,
					PlatformUI.getWorkbench().getDisplay().getActiveShell(),
					"Failed at saving " + getLabelText().substring(1, getLabelText().length() - 1),
					"Couldn't save " + getLabelText().substring(1, getLabelText().length() - 1)
							+ " because it is in an invalid state!",
					SWT.NULL);
					
			return false;
		}
		
		return true;
	}
	
	/**
	 * This method will load the current value of the preference this editor is
	 * working on and displays them in the GUI. Can be used to update the GUI.
	 * <br>
	 * <b>Will be called during construction of this editor.</b>
	 */
	@Override
	public abstract void doLoad();
	
	/**
	 * This mehtods will load the default value for the preference this editor
	 * is working on in the GUI.<br>
	 * <b>It does not change the value of the preference itself</b>
	 */
	@Override
	public abstract void doLoadDefault();
	
	/**
	 * This method will reset the preference this editor is working on to it's
	 * default value and updates the GUI afterwards.
	 */
	@Override
	public void doSetDefault() {
		getPreferenceStore().setToDefault(getPreferenceKey());
	}
	
	/**
	 * Checks if the editor is in a valid state (e.g. if the input is valid)
	 * 
	 * @return The state of the editor
	 */
	@Override
	public boolean isValid() {
		// if the status is okay or a warning the editor is in a valid state
		return (getStatus().equals(EStatus.OK) || getStatus().equals(EStatus.WARNING));
	}
	
	/**
	 * This method evaluates the input and changes the editor state accordingly
	 */
	@Override
	public abstract void evaluateInput();
	
	/**
	 * Defines the preferencePage this editor is working on
	 * 
	 * @param page
	 *            The page this editor should sent information to
	 */
	@Override
	public void setPreferencePage(ISQDevPreferencePage page) {
		this.page = page;
	}
	
	/**
	 * Gets the preferencePage this editor is working on
	 * 
	 * @return
	 */
	@Override
	public ISQDevPreferencePage getPreferencePage() {
		return page;
	}
	
	/**
	 * Tries to get the <code>ISQDevPreferencePage</code> this editor is applied
	 * to according to the set container of this editor.<br>
	 * Basically this method tries to find a parent <code>Composite</code> that
	 * is of the type <code>SQDevPreferenceComposite</code>.
	 * 
	 * @return The <code>ISQDevPreferencePage</code> this editor is applied to.
	 *         <br>
	 *         Returns <code>null</code> if it can't find a suitable Composite
	 */
	protected ISQDevPreferencePage resolvePreferencePage() {
		Composite container = getContainer();
		ISQDevPreferencePage page = null;
		
		while (page == null && container != null) {
			if (container.getClass().equals(SQDevPreferenceComposite.class)) {
				page = ((SQDevPreferenceComposite) container).getPage();
				break;
			}
			
			page = resolvePreferencePage(container, new ArrayList<Control>());
			
			container = container.getParent();
		}
		
		// check that the preferencePage has been found
		Assert.isNotNull(page, "Couldn't resolve the preferencePage");
		
		return page;
	}
	
	private ISQDevPreferencePage resolvePreferencePage(Composite container,
			ArrayList<Control> inspected) {
			
		for (Control currentControl : container.getChildren()) {
			if (inspected.contains(currentControl)) {
				// Don't check them twice
				continue;
			}
			
			// add to the inspected list
			inspected.add(currentControl);
			
			if (currentControl.getClass().equals(SQDevPreferenceComposite.class)) {
				// if the composite
				return ((SQDevPreferenceComposite) currentControl).getPage();
			} else {
				if (currentControl instanceof Composite) {
					if (((Composite) currentControl).getChildren().length > 0) {
						// search recursively through all children
						ISQDevPreferencePage page = resolvePreferencePage(
								(Composite) currentControl, inspected);
								
						if (page != null) {
							return page;
						}
					}
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Gets the preferenceStore this editor is working on
	 * 
	 * @return
	 */
	@Override
	public IPreferenceStore getPreferenceStore() {
		return store;
	}
	
	/**
	 * Sets the preferenceStore this editor is working on
	 * 
	 * @param store
	 *            The new preferenceStore for this editor
	 */
	@Override
	public void setPreferenceStore(IPreferenceStore store) {
		Assert.isNotNull(store, "The preference store mustn't be null");
		
		if (getPreferenceStore() != null) {
			// unregister this as the listener of the old PreferenceStore
			getPreferenceStore().removePropertyChangeListener(this);
		}
		
		// register this editor as a listener to the preference store
		store.addPropertyChangeListener(this);
		
		this.store = store;
	}
	
	/**
	 * Gets the key (identifier) of the preference this editor is working on
	 * 
	 * @return
	 */
	@Override
	public String getPreferenceKey() {
		return preferenceKey;
	}
	
	/**
	 * Sets the key (identifier) of the preference this editor should work on
	 * 
	 * @param key
	 */
	@Override
	public void setPreferenceKey(String key) {
		Assert.isTrue(getPreferenceStore().contains(key),
				"The preference " + key + " does not exist");
				
		preferenceKey = key;
	}
	
	/**
	 * This method will get the container the GUI elements of this editor are
	 * displayed on
	 * 
	 * @return The editor's container
	 */
	@Override
	public Composite getContainer() {
		return this.container;
	}
	
	/**
	 * This method will set the container of the editor and can therefore be
	 * used to define the editor's position in the GUI
	 * 
	 * @param container
	 *            The new container this editor should be displayed on
	 */
	@Override
	public void setContainer(Composite container) {
		this.container = container;
	}
	
	/**
	 * Returns the amount of GUI components this editor consists of
	 * 
	 * @return
	 */
	@Override
	public abstract int getComponentCount();
	
	/**
	 * Adds as many empty components as necessary to match the given component
	 * count
	 * 
	 * @param count
	 *            The amount of components to match<br>
	 *            may not be smaller than <code>getComponentCount()</code> of
	 *            this editor
	 * 			
	 * @see #getComponentCount()
	 */
	@Override
	public void matchComponentCount(int count) {
		if (count < getComponentCount()) {
			throw new IllegalArgumentException(
					"Component count to match is lower than the actual component count!");
		} else {
			emptyComponentsToCreate = count - getComponentCount();
		}
	}
	
	/**
	 * Creates a sufficient amount of empty components needed to fit in the
	 * Layout
	 */
	protected void createEmptyComponents() {
		for (int i = 0; i < emptyComponentsToCreate; i++) {
			// create a sufficient amount of empty components
			Composite comp = new Composite(getContainer(), SWT.NULL);
			comp.setEnabled(false);
			
			comp.setSize(0, 0);
		}
	}
	
	/**
	 * Sets the status of this editor
	 * 
	 * @param status
	 *            The new status
	 */
	@Override
	public void setStatus(EStatus status) {
		this.status = status;
		
		// notify about change
		changed(new SQDevChangeEvent(SQDevChangeEvent.SQDEV_STATUS_CHANGED));
	}
	
	/**
	 * Gets the status of this editor
	 * 
	 * @return
	 */
	@Override
	public EStatus getStatus() {
		return status;
	}
	
	/**
	 * Gets the hint message stored in the status of the page.<br>
	 * Returns <code>null</code> if the status is not
	 * <code>EStatus.WARNING</code> or <code>EStatus.ERROR</code>
	 */
	@Override
	public String getStatusHint() {
		return getStatus().getHint();
	}
	
	/**
	 * Creates the required components for this editor.<br>
	 * <b>Should call {@linkplain #createEmptyComponents()} in the end!</b>
	 * 
	 * @param container
	 *            The container the components are created in
	 */
	@Override
	public abstract void createComponents(Composite container);
	
	/**
	 * Creates the required components for this editor in the set container.<br>
	 * <b>Should call {@linkplain #createEmptyComponents()} in the end!</b>
	 */
	@Override
	public void createComponents() {
		createComponents(getContainer());
	}
	
	/**
	 * Sets the text of the used label.<br>
	 * If the label has already been created it will update ot's text.
	 * 
	 * @param text
	 *            The new text for this label
	 */
	@Override
	public void setLabelText(String text) {
		this.labelText = text;
		
		if (label != null) {
			// update label if it has already been created
			label.setText(text);
			
			label.pack(true);
		}
	}
	
	/**
	 * Gets the current text of the used label
	 */
	@Override
	public String getLabelText() {
		return labelText;
	}
	
	/**
	 * Gets notified whenever the preference, this editor is working on, changes
	 * 
	 * @param event
	 *            The fired event
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getProperty().equals(getPreferenceKey())) {
			// load the new value into the GUI
			doLoad();
		}
	}
	
	/**
	 * Registers the given listener if it is not already registered
	 * 
	 * @param listener
	 *            The Listener to register
	 */
	@Override
	public void addChangeListener(ISQDevPreferenceEditorListener listener) {
		if (!this.listener.contains(listener)) {
			this.listener.add(listener);
		}
	}
	
	/**
	 * Unregisters the given listener
	 * 
	 * @param listener
	 *            The listener to unregister
	 */
	@Override
	public void removeChangListener(ISQDevPreferenceEditorListener listener) {
		this.listener.remove(listener);
	}
	
	@Override
	public void changed(SQDevChangeEvent event) {
		for (ISQDevPreferenceEditorListener currentListener : this.listener) {
			// notify about the change
			currentListener.editorStateChanged(event);
		}
	}
	
	/**
	 * Sets the tooltip for this editor's value-field
	 * 
	 * @param tooltip
	 *            The tooltip to display
	 */
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
	
	/**
	 * Gets the tooltip of this editor's preference value-field
	 */
	public String getTooltip() {
		return tooltip;
	}
	
	/**
	 * Checks if the editor needs to be saved when it will change to this
	 * content
	 * 
	 * @param content
	 *            The new content
	 * @return
	 */
	public abstract boolean willNeedSave(String content);
	
	/**
	 * Indicates graphically whether the editor has a value that differs from
	 * the value of the preference this editor is working on
	 * 
	 * @param newText
	 *            The text that represents the value of this editor and that
	 *            will be compared to the preference's value
	 */
	protected void updateSaveStatus(String newText) {
		// mark the editor if it needs to be saved
		String labelText = getLabelText();
		
		if (willNeedSave(newText)) {
			if (!labelText.endsWith("*")) {
				labelText += "*";
			}
		} else {
			if (labelText.endsWith("*")) {
				labelText = labelText.substring(0, labelText.length() - 1);
			}
		}
		
		setLabelText(labelText);
		getContainer().layout(true);
	}
	
	/**
	 * Gets the unique ID of this editor
	 * 
	 * @return
	 */
	public String getID() {
		return ID;
	}
	
	public void setID(String iD) {
		ID = iD;
	}
	
	/**
	 * Compares the two editors. They are considered equal when they are an
	 * instance of the same class and if they have the same ID.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AbstractSQDevPreferenceEditor) {
			AbstractSQDevPreferenceEditor editor = (AbstractSQDevPreferenceEditor) obj;
			
			if (!editor.getClass().equals(this.getClass())) {
				return false;
			}
			
			if (!editor.getID().equals(this.getID())) {
				return false;
			}
			
			return true;
			
		} else {
			return false;
		}
	}
	
}
