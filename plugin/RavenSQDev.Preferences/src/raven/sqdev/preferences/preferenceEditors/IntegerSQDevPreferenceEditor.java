package raven.sqdev.preferences.preferenceEditors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import raven.sqdev.preferences.pages.ISQDevPreferencePage;
import raven.sqdev.preferences.util.EStatus;
import raven.sqdev.preferences.util.SQDevChangeEvent;

/**
 * A preference editor for integer values
 * 
 * @author Raven
 *
 */
public class IntegerSQDevPreferenceEditor extends AbstractSQDevPreferenceEditor {
	/**
	 * The number selection widget
	 */
	private Spinner numberSelector;
	/**
	 * The initial value of this editor
	 */
	private int initialValue;
	/**
	 * The minimum value this editor may have
	 */
	private int min;
	/**
	 * Indicates whether a minimum has been specified
	 */
	private boolean minIsSet;
	
	/**
	 * The maximum value this editor may have
	 */
	private int max;
	/**
	 * Indicating whether a maximum has been set
	 */
	private boolean maxIsSet;
	
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
	public IntegerSQDevPreferenceEditor(String preferenceKey, String labelText,
			Composite container) {
		super(preferenceKey, labelText, container);
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
	public IntegerSQDevPreferenceEditor(String preferenceKey, String labelText, String tooltip,
			Composite container) {
		super(preferenceKey, labelText, tooltip, container);
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
	public IntegerSQDevPreferenceEditor(String preferenceKey, String labelText, Composite container,
			ISQDevPreferencePage page) {
		super(preferenceKey, labelText, container, page);
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
	public IntegerSQDevPreferenceEditor(String preferenceKey, String labelText, String tooltip,
			Composite container, ISQDevPreferencePage page) {
		super(preferenceKey, labelText, tooltip, container, page);
	}
	
	@Override
	public boolean needsSave() {
		return willNeedSave(numberSelector.getText());
	}
	
	@Override
	public void doLoad() {
		load(getPreferenceStore().getInt(getPreferenceKey()));
	}
	
	@Override
	public void doLoadDefault() {
		load(getPreferenceStore().getDefaultInt(getPreferenceKey()));
	}
	
	/**
	 * Loads the given value in the widget
	 * 
	 * @param value
	 *            The new value
	 */
	private void load(int value) {
		if (numberSelector == null) {
			initialValue = value;
		} else {
			if (numberSelector.isDisposed()) {
				return;
			}
			
			numberSelector.setSelection(value);
			
			evaluateInput();
			updateSaveStatus(String.valueOf(value));
		}
		
		changed(new SQDevChangeEvent(SQDevChangeEvent.SQDEV_VALUE_LOADED));
	}
	
	@Override
	public void evaluateInput() {
		EStatus status = EStatus.OK;
		
		int value = numberSelector.getSelection();
		
		if (minIsSet && value < min) {
			status = EStatus.ERROR;
			status.setHint("The specified value has to be at least " + min + "!");
			setStatus(status);
			return;
		}
		
		if (maxIsSet && value > max) {
			status = EStatus.ERROR;
			status.setHint("The specified status exceeds " + max + "!");
			setStatus(status);
			return;
		}
		
		setStatus(status);
	}
	
	@Override
	public int getComponentCount() {
		return 2;
	}
	
	@Override
	public void createComponents(Composite container) {
		// label
		label = new Label(container, SWT.NONE);
		label.setText(getLabelText());
		label.setToolTipText(getTooltip());
		
		numberSelector = new Spinner(container, SWT.BORDER);
		numberSelector.setToolTipText(getTooltip());
		
		if (minIsSet) {
			numberSelector.setMinimum(min);
		}
		if (maxIsSet) {
			numberSelector.setMaximum(max);
		}
		
		numberSelector.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				// fire change event
				changed(new SQDevChangeEvent(SQDevChangeEvent.SQDEV_VALUE_ABOUT_TO_CHANGE));
				
				evaluateInput();
				
				updateSaveStatus(String.valueOf(numberSelector.getSelection()));
			}
		});
		
		load(initialValue);
	}
	
	@Override
	public boolean willNeedSave(String content) {
		return getPreferenceStore().getInt(getPreferenceKey()) != Integer.parseInt(content);
	}
	
	@Override
	public boolean doSave() {
		if (super.doSave()) {
			getPreferenceStore().setValue(getPreferenceKey(), numberSelector.getSelection());
			
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * Sets the minimum value this editor may contain
	 * 
	 * @param value
	 *            The minimum value (inclusive)
	 */
	public void setMinValue(int value) {
		if (numberSelector == null) {
			min = value;
		} else {
			if (numberSelector.isDisposed()) {
				return;
			}
			
			numberSelector.setMinimum(value);
		}
		
		minIsSet = true;
	}
	
	/**
	 * Sets the maximum value this editor may contain
	 * 
	 * @param value
	 *            The maximum value (inclusive)
	 */
	public void setMaxValue(int value) {
		if (numberSelector.isDisposed()) {
			return;
		}
		
		if (numberSelector == null) {
			max = value;
		} else {
			numberSelector.setMaximum(value);
		}
		
		maxIsSet = true;
	}
	
}
