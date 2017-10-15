package raven.sqdev.preferences.preferenceEditors;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;

import raven.sqdev.preferences.pages.ISQDevPreferencePage;
import raven.sqdev.preferences.util.EStatus;
import raven.sqdev.preferences.util.ISQDevPreferenceEditorListener;
import raven.sqdev.preferences.util.SQDevChangeEvent;

/**
 * An interface for all SQDev preferencesEditors
 * 
 * @author Raven
 * 		
 */
public interface ISQDevPreferenceEditor {
	/**
	 * This method will save the value for the preference this editor is working
	 * on
	 */
	public boolean doSave();
	
	/**
	 * This method will load the current value of the preference this editor is
	 * working on and displays them in the GUI. Can be used to update the GUI
	 */
	public void doLoad();
	
	/**
	 * This mehtods will load the default value for the preference this editor
	 * is working on in the GUI.<br>
	 * <b>It does not change the value of the preference itself</b>
	 */
	public void doLoadDefault();
	
	/**
	 * This method will reset the preference this editor is working on to it's
	 * default value and updates the GUI afterwards.
	 */
	public void doSetDefault();
	
	/**
	 * Checks if the editor is in a valid state (e.g. if the input is valid)
	 * @return The state of the editor
	 */
	public boolean isValid();
	
	/**
	 * This method evaluates the input and changes the editor state accordingly
	 */
	public void evaluateInput();
	
	/**
	 * Sets the status of this editor
	 * @param status The new status
	 */
	public void setStatus(EStatus status);
	
	/**
	 * Gets the status of this editor
	 * @return
	 */
	public EStatus getStatus();
	
	/**
	 * Gets the hint message stored in the status of the page.<br>
	 * Returns <code>null</code> if the status is not
	 * <code>EStatus.WARNING</code> or <code>EStatus.ERROR</code>
	 */
	public String getStatusHint();
	
	/**
	 * Defines the preferencePage this editor is working on
	 * @param page The page this editor should sent information to
	 */
	public void setPreferencePage(ISQDevPreferencePage page);
	
	/**
	 * Gets the preferencePage this editor is working on
	 * @return
	 */
	public ISQDevPreferencePage getPreferencePage();
	
	/**
	 * Gets the preferenceStore this editor is working on
	 * @return
	 */
	public IPreferenceStore getPreferenceStore();
	
	/**
	 * Sets the preferenceStore this editor is working on
	 * @param store The new preferenceStore for this editor
	 */
	public void setPreferenceStore(IPreferenceStore store);
	
	/**
	 * Gets the key (identifier) of the preference this editor is working on
	 * @return
	 */
	public String getPreferenceKey();
	
	/**
	 * Sets the key (identifier) of the preference this editor should work on
	 * @param key
	 */
	public void setPreferenceKey(String key);
	
	/**
	 * This method will get the container the GUI elements of this editor are
	 * displayed on
	 * 
	 * @return The editor's container
	 */
	public Composite getContainer();
	
	/**
	 * This method will set the container of the editor and can therefore be
	 * used to define the editor's position in the GUI
	 * 
	 * @param container
	 *            The new container this editor should be displayed on
	 */
	public void setContainer(Composite container);
	
	/**
	 * Returns the amount of GUI components this editor consists of
	 * @return
	 */
	public int getComponentCount();
	
	/**
	 * Creates the required components for this editor
	 * @param container The container the components are created in
	 */
	public void createComponents(Composite container);
	
	/**
	 * Creates the required components for this editor in the set container
	 */
	public void createComponents();
	
	/**
	 * Sets the text of the used label
	 * @param text The new text for this label
	 */
	public void setLabelText(String text);
	
	/**
	 * Gets the current text of the used label
	 */
	public String getLabelText();
	
	/**
	 * Adds as many empty components as necessary to match the given component count
	 * @param count The amount of components to match<br>
	 * may not be smaller than <code>getComponentCount()</code> of this editor
	 * 
	 * @see #getComponentCount()
	 */
	public void matchComponentCount(int count);
	
	/**
	 * Checks if the preference this editor is working on needs to be saved
	 * @return
	 */
	public boolean needsSave();
	
	/**
	 * Adds a listener to this editor that get notified whenever this editor changes
	 */
	public void addChangeListener(ISQDevPreferenceEditorListener listener);
	
	/**
	 * Removes the given listener
	 */
	public void removeChangListener(ISQDevPreferenceEditorListener listener);
	
	/**
	 * Notifies all registered listeners that the editor has changed
	 */
	public void changed(SQDevChangeEvent event);
}
