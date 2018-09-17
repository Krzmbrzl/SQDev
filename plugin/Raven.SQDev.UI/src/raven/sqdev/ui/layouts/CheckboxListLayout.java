package raven.sqdev.ui.layouts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;

import raven.sqdev.ui.widgets.CheckboxList;

/**
 * A {@linkplain Layout} specifically designed for the use by
 * {@linkplain CheckboxList}
 * 
 * @author Raven
 *
 */
public class CheckboxListLayout extends Layout {

	@Override
	protected Point computeSize(Composite composite, int wHint, int hHint, boolean flushCache) {
		return composite.computeSize(wHint, hHint);
	}

	@Override
	protected void layout(Composite composite, boolean flushCache) {
		Point size = composite.getSize();

		Control topBar = composite.getChildren()[0];
		topBar.setLocation(0, 0);
		int topHeight = topBar.computeSize(size.x, SWT.DEFAULT).y;
		topBar.setSize(size.x, topHeight);

		Control switchBar = composite.getChildren()[1];
		switchBar.setLocation(0, topHeight);
		int switchHeight = switchBar.computeSize(size.x, SWT.DEFAULT).y;
		switchBar.setSize(size.x, switchHeight);

		Control scroller = composite.getChildren()[2];
		scroller.setLocation(0, topHeight + switchHeight);
		scroller.setSize(size.x, size.y - topHeight - switchHeight);
	}

}
