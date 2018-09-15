package raven.sqdev.ui.widgets;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;

/**
 * A widget that does not allow setting its Layout except through the
 * protected function {@link #doSetLayout(Layout)}. This is useful for Composites that need to
 * manage their layout on their own.
 * 
 * @author Raven
 *
 */
public class LayedOutComposite extends Composite {

	public LayedOutComposite(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void setLayout(Layout layout) {
		try {
			throw new IllegalAccessException(
					"Setting layout is not permitted for this composite as it sets its own layout!");
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Sets the layout for this composite without throwing an exception
	 * 
	 * @param layout
	 *            The {@linkplain Layout} to use
	 */
	protected void doSetLayout(Layout layout) {
		super.setLayout(layout);
	}
}
