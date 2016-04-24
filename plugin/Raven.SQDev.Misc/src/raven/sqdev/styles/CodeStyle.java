package raven.sqdev.styles;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

/**
 * The style used for Code
 * 
 * @author Raven
 * 		
 */
public class CodeStyle extends SQDevStyle {
	
	/**
	 * Creates an instance of this <code>CodeStyle</code>
	 */
	public CodeStyle() {
		super("Code", "Code", "/Code", true);
	}
	
	@Override
	public Composite createComposite(Composite parent, String content) {
		if (!needsOwnComposite()) {
			return null;
		}
		
		Composite base = new Composite(parent, SWT.BORDER);
		
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = 1;
		layout.marginWidth = 1;
		base.setLayout(layout);
		
		Text code = new Text(base, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.READ_ONLY);
		code.setText(content);
		
		code.setBackground(PlatformUI.getWorkbench().getDisplay().getSystemColor(SWT.COLOR_WHITE));
		
		code.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		return base;
	}
	
}
