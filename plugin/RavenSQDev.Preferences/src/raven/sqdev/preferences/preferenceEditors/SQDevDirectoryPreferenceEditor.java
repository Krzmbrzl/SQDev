package raven.sqdev.preferences.preferenceEditors;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.swt.widgets.Composite;

import raven.sqdev.preferences.util.SQDevPreferenceUtil;

public class SQDevDirectoryPreferenceEditor extends DirectoryFieldEditor{
	
	public SQDevDirectoryPreferenceEditor(String name, String labelText, Composite parent){
		super(name, labelText, parent);
	}
	
	/**
	 * Sets the text of the editors textControl
	 * @param text The new text
	 */
	public void setText(String text) {
		getTextControl().setText(text);
	}
	
	@Override
	public void doLoad() {
		// load path from preference
		String path = Platform.getPreferencesService().
				  getString("raven.sqdev.preferences", getPreferenceName(), "", null);
		
		setText(path);
	}
	
	@Override
	public void doLoadDefault() {
		String test = "";
	}
	
	@Override
	public void doStore() {
		SQDevPreferenceUtil.getPreferences().put(getPreferenceName(), getTextControl().getText());
	}
	
}
