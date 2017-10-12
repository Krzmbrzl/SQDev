package raven.sqdev.misc;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import raven.sqdev.interfaces.IProposalInformationCategory;

/**
 * An information category for displaying a plain String as an
 * InformationCategory
 * 
 * @author Raven
 *		
 */
public class StringProposalInformationCategory implements IProposalInformationCategory {
	
	/**
	 * The category's name
	 */
	private String name;
	/**
	 * The category's content
	 */
	private String content;
	
	public StringProposalInformationCategory(String name, String content) {
		if (name == null || content == null) {
			// don't allow null values
			throw new NullPointerException(
					(name == null) ? "Name may not be null!" : "Content may not be null!");
		}
		
		this.name = name;
		this.content = content;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public Control getControl(Composite parent) {
		if (!(parent.getLayout() instanceof GridLayout)) {
			// make sure parent uses a grid Layout
			parent.setLayout(new GridLayout());
		}
		
		// create a Text showing the content of this category
		Text text = new Text(parent, SWT.READ_ONLY | SWT.MULTI | SWT.WRAP);
		text.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
		text.setText(content);
		
		// inherit color scheme
		text.setForeground(parent.getForeground());
		text.setBackground(parent.getBackground());
		
		return parent;
	}
	
}
