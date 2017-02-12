package raven.sqdev.preferences.preferenceEditors;

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import raven.sqdev.preferences.pages.ISQDevPreferencePage;
import raven.sqdev.preferences.util.SQDevChangeEvent;

/**
 * A preference editor that lets the user choose between a set of predefined
 * choices and depending on the settings for this editor type in his own choices
 * 
 * @author Raven
 *		
 */
public class ComboSQDevPreferenceEditor extends AbstractSQDevPreferenceEditor {
	
	/**
	 * The alternatives this preference should provide
	 */
	protected String[] alternatives;
	
	/**
	 * Defines whether this editor should accept custom input
	 */
	protected boolean isWritable;
	
	/**
	 * The initial input of this editor
	 */
	protected String initialInput;
	
	/**
	 * The ComboBox that provides the different alternatives
	 */
	protected Combo alternativesComboBox;
	
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
	 * @param alternatives
	 *            The alternatives this preference should provide
	 * @param isWritable
	 *            Defines whether this editor should accept custom input
	 * @param container
	 *            The container the GUI elements should be placed in
	 */
	public ComboSQDevPreferenceEditor(String preferenceKey, String labelText, String[] alternatives,
			boolean isWritable, Composite container) {
		super(preferenceKey, labelText, container);
		
		Assert.isTrue(alternatives.length > 0);
		
		this.alternatives = alternatives;
		this.isWritable = isWritable;
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
	 * @param alternatives
	 *            The alternatives this preference should provide
	 * @param isWritable
	 *            Defines whether this editor should accept custom input
	 * @param tooltip
	 *            The tooltip that will be displayed on the editor's preference
	 *            value-field
	 * @param container
	 *            The container the GUI elements should be placed in
	 */
	public ComboSQDevPreferenceEditor(String preferenceKey, String labelText, String[] alternatives,
			boolean isWritable, String tooltip, Composite container) {
		super(preferenceKey, labelText, tooltip, container);
		
		Assert.isTrue(alternatives.length > 0);
		
		this.alternatives = alternatives;
		this.isWritable = isWritable;
	}
	
	/**
	 * Creates a new <code>SQDevPreferenceEditor</code>
	 * 
	 * @param preferenceKey
	 *            The key of the preference to work on
	 * @param labelText
	 *            The text of the label
	 * @param alternatives
	 *            The alternatives this preference should provide
	 * @param isWritable
	 *            Defines whether this editor should accept custom input
	 * @param container
	 *            The container the GUI elements should be placed in
	 * @param page
	 *            The <code>ISQDevPreferencePage</code> this editor is apllied
	 *            to
	 */
	public ComboSQDevPreferenceEditor(String preferenceKey, String labelText, String[] alternatives,
			boolean isWritable, Composite container, ISQDevPreferencePage page) {
		super(preferenceKey, labelText, container, page);
		
		Assert.isTrue(alternatives.length > 0);
		
		this.alternatives = alternatives;
		this.isWritable = isWritable;
	}
	
	/**
	 * Creates a new <code>SQDevPreferenceEditor</code>
	 * 
	 * @param preferenceKey
	 *            The key of the preference to work on
	 * @param labelText
	 *            The text of the label
	 * @param alternatives
	 *            The alternatives this preference should provide
	 * @param isWritable
	 *            Defines whether this editor should accept custom input
	 * @param tooltip
	 *            The tooltip that will be displayed on the editor's preference
	 *            value-field
	 * @param container
	 *            The container the GUI elements should be placed in
	 * @param page
	 *            The <code>ISQDevPreferencePage</code> this editor is apllied
	 *            to
	 */
	public ComboSQDevPreferenceEditor(String preferenceKey, String labelText, String[] alternatives,
			boolean isWritable, String tooltip, Composite container, ISQDevPreferencePage page) {
		super(preferenceKey, labelText, tooltip, container, page);
		
		Assert.isTrue(alternatives.length > 0);
		
		this.alternatives = alternatives;
		this.isWritable = isWritable;
	}
	
	@Override
	public boolean doSave() {
		super.doSave();
		
		getPreferenceStore().setValue(getPreferenceKey(), alternativesComboBox.getText());
		
		updateSaveStatus(alternativesComboBox.getText());
		
		return true;
	};
	
	@Override
	public boolean needsSave() {
		return willNeedSave(alternativesComboBox.getText());
	}
	
	@Override
	public void doLoad() {
		load(getPreferenceStore().getString(getPreferenceKey()));
	}
	
	@Override
	public void doLoadDefault() {
		load(getPreferenceStore().getDefaultString(getPreferenceKey()));
	}
	
	/**
	 * Loads the given value in this preference editor. If this editor is not
	 * set to writable the value is expected to be one of the set alternatives.
	 * 
	 * @param value
	 *            The new value of this preferenceEditor
	 */
	private void load(String value) {
		if (!isWritable) {
			// check that value is one of the specified alternatives
			boolean matched = false;
			
			if (alternatives == null) {
				matched = true;
			} else {
				for (String currentAlternative : alternatives) {
					if (currentAlternative.equals(value)) {
						matched = true;
						break;
					}
				}
			}
			
			Assert.isTrue(matched);
		}
		
		Assert.isNotNull(value);
		Assert.isTrue(!value.isEmpty());
		
		if (alternativesComboBox == null) {
			// if the comboBox has not yet been created store the value
			initialInput = value;
		} else {
			alternativesComboBox.setText(value);
			
			evaluateInput();
			updateSaveStatus(value);
		}
		
		changed(new SQDevChangeEvent(SQDevChangeEvent.SQDEV_VALUE_LOADED));
	}
	
	@Override
	public void evaluateInput() {
	}
	
	@Override
	public int getComponentCount() {
		return 2;
	}
	
	@Override
	public void createComponents(Composite container) {
		// create the nameLabel
		label = new Label(container, SWT.NULL);
		setLabelText(getLabelText());
		label.setToolTipText(getTooltip());
		
		if (isWritable) {
			// allow custominput in the ComboBox
			alternativesComboBox = new Combo(container, SWT.DROP_DOWN);
			
			alternativesComboBox.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		} else {
			// don't allow custom input
			alternativesComboBox = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
			alternativesComboBox.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		}
		
		alternativesComboBox.setToolTipText(getTooltip());
		
		// add the alternatives
		for (String currentAlternatives : alternatives) {
			alternativesComboBox.add(currentAlternatives);
		}
		
		alternativesComboBox.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				// fire change event
				changed(new SQDevChangeEvent(SQDevChangeEvent.SQDEV_VALUE_ABOUT_TO_CHANGE));
				
				evaluateInput();
				
				updateSaveStatus(alternativesComboBox.getText());
			}
		});
		
		if (initialInput != null) {
			load(initialInput);
		} else {
			alternativesComboBox.select(0);
		}
	}
	
	@Override
	public boolean willNeedSave(String content) {
		if (getPreferenceStore().getString(getPreferenceKey()).equals(content)) {
			return false;
		} else {
			return true;
		}
	}
	
}
