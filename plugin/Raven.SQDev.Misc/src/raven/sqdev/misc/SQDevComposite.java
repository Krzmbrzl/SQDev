package raven.sqdev.misc;

import org.eclipse.swt.widgets.Composite;

/**
 * A composite used to determine a GUI element to be part of SQDev
 * 
 * @author Raven
 *		
 */
public class SQDevComposite extends Composite {
	
	/**
	 * Creates an instance of this SQDevComposite
	 * 
	 * @param parent
	 *            The parent composite
	 * @param style
	 *            The style of this composite. Can be any defined in
	 *            <code>SWT</code>
	 */
	public SQDevComposite(Composite parent, int style) {
		super(parent, style);
	}
	
}
