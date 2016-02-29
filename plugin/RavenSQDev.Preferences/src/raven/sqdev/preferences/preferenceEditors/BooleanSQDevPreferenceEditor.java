package raven.sqdev.preferences.preferenceEditors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import raven.sqdev.preferences.pages.ISQDevPreferencePage;
import raven.sqdev.preferences.util.SQDevChangeEvent;

/**
 * A preference editor that let the user change a boolean preference.
 * @author Raven
 *
 */
public class BooleanSQDevPreferenceEditor extends AbstractSQDevPreferenceEditor {
	
	/**
	 * The checkbox button
	 */
	protected Button checkBox;
	
	/**
	 * The initial state of the checkBox
	 */
	private boolean initialState;
	
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
	public BooleanSQDevPreferenceEditor(String preferenceKey, String labelText,
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
	public BooleanSQDevPreferenceEditor(String preferenceKey, String labelText, String tooltip,
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
	public BooleanSQDevPreferenceEditor(String preferenceKey, String labelText, Composite container,
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
	public BooleanSQDevPreferenceEditor(String preferenceKey, String labelText, String tooltip,
			Composite container, ISQDevPreferencePage page) {
		super(preferenceKey, labelText, tooltip, container, page);
	}
	
	@Override
	public boolean doSave() {
		if (super.doSave()) {
			getPreferenceStore().setValue(getPreferenceKey(), checkBox.getSelection());
			
			updateSaveStatus(String.valueOf(checkBox.getSelection()));
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean needsSave() {
		return willNeedSave(String.valueOf(checkBox.getSelection()));
	}
	
	@Override
	public boolean willNeedSave(String content) {
		if (!content.equals("true") && !content.equals("false")) {
			// has to be one of the two
			throw new IllegalArgumentException(
					"Expected \"true\" or \"false\" but got \"" + content + "\"");
		}
		
		boolean newState = (content.equals("true")) ? true : false;
		
		if (newState != getPreferenceStore().getBoolean(getPreferenceKey())) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void doLoad() {
		load(getPreferenceStore().getBoolean(getPreferenceKey()));
	}
	
	@Override
	public void doLoadDefault() {
		load(getPreferenceStore().getDefaultBoolean(getPreferenceKey()));
	}
	
	private void load(boolean state) {
		if (checkBox == null) {
			// if the checkBox has not yet been created store the state and
			// initialize with it
			initialState = state;
		} else {
			if (checkBox.isDisposed()) {
				return;
			}
			
			checkBox.setSelection(state);
			
			evaluateInput();
			updateSaveStatus(String.valueOf(state));
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
		label = new Label(container, SWT.NULL);
		setLabelText(getLabelText());
		label.setToolTipText(getTooltip());
		
		checkBox = new Button(container, SWT.CHECK);
		checkBox.setToolTipText(getTooltip());
		checkBox.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				// fire change event
				changed(new SQDevChangeEvent(SQDevChangeEvent.SQDEV_VALUE_ABOUT_TO_CHANGE));
				
				evaluateInput();
				
				updateSaveStatus(String.valueOf(checkBox.getSelection()));
			}
		});
		
		load(initialState);
		
		if (getContainer().getLayout() instanceof GridLayout) {
			// make the layout's gaps a little wider
			GridLayout layout = (GridLayout) getContainer().getLayout();
			if (layout.horizontalSpacing < 20) {
				layout.horizontalSpacing = 20;
			}
			
			getContainer().setLayout(layout);
		} else {
			if (getContainer().getLayout() == null) {
				GridLayout layout = new GridLayout();
				layout.horizontalSpacing = 20;
				
				getContainer().setLayout(layout);
			}
		}
	}
	
}
