package raven.sqdev.styles;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;

public class BoldStyle extends SQDevStyle {
	
	/**
	 * Creates an instance of this <code>BoldStyle</code>
	 */
	public BoldStyle() {
		super("bold", "Bold", "/Bold", false);
	}
	
	@Override
	public StyleRange getStyleRange() {
		StyleRange range = new StyleRange();
		range.fontStyle = SWT.BOLD;
		
		return range;
	}
	
}
