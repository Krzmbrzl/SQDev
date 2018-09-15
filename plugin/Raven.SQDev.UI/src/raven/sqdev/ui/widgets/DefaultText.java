package raven.sqdev.ui.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

public class DefaultText extends Text implements FocusListener {

	Color textColor;
	Color defaultTextColor;
	String defaultText;
	String prevText;
	boolean isShowingDefaultText;

	public DefaultText(Composite parent, int style) {
		super(parent, style);

		textColor = Display.getCurrent().getSystemColor(SWT.COLOR_BLACK);
		defaultTextColor = Display.getCurrent().getSystemColor(SWT.COLOR_GRAY);

		prevText = "";

		this.addFocusListener(this);
	}

	@Override
	protected void checkSubclass() {
		// do nothing
	}

	public void setDefaultText(String defaultText) {
		this.defaultText = defaultText;

		if (!isShowingDefaultText && super.getText().isEmpty()) {
			setForeground(defaultTextColor);
			setText(defaultText);
			isShowingDefaultText = true;
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (isShowingDefaultText) {
			this.setForeground(textColor);
			this.setText("");
			isShowingDefaultText = false;
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (super.getText().isEmpty()) {
			this.setForeground(defaultTextColor);
			this.setText(defaultText);
			isShowingDefaultText = true;
		}
	}

	@Override
	public Point computeSize(int wHint, int hHint) {
		if (getText().trim().isEmpty()) {
			Point p = super.computeSize(SWT.DEFAULT, SWT.DEFAULT);

			Point extend = new GC(this).stringExtent(defaultText);

			return new Point(extend.x, Math.max(extend.y, p.y));
		} else {
			return super.computeSize(wHint, hHint);
		}
	}
	
	@Override
	public String getText() {
		return isShowingDefaultText ? "" : super.getText();
	}

}
