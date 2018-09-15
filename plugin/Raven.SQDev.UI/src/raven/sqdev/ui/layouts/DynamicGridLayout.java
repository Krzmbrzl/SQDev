package raven.sqdev.ui.layouts;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;

public class DynamicGridLayout extends Layout {

	Map<Control, Point> cachedSizes;
	Map<Control, Point> cachedLocations;
	Point cacheComputedForSize;
	int rowHeight, columnWidth;
	boolean verticalOverflow;

	int fixedMargin, dynamicMarginHint, horizontalSpacing, verticalSpacing;

	public DynamicGridLayout() {
		this(true, SWT.DEFAULT, SWT.DEFAULT, SWT.DEFAULT, SWT.DEFAULT);
	}

	public DynamicGridLayout(boolean verticalOverflow) {
		this(verticalOverflow, SWT.DEFAULT, SWT.DEFAULT, SWT.DEFAULT, SWT.DEFAULT);
	}

	public DynamicGridLayout(boolean verticalOverflow, int fixedMargin, int dynamicMarginHint) {
		this(verticalOverflow, fixedMargin, dynamicMarginHint, SWT.DEFAULT, SWT.DEFAULT);
	}

	public DynamicGridLayout(int fixedMargin, int dynamicMarginHint) {
		this(true, fixedMargin, dynamicMarginHint, SWT.DEFAULT, SWT.DEFAULT);
	}

	public DynamicGridLayout(int fixedMargin, int dynamicMarginHint, int horizontalSpacing, int verticalSpacing) {
		this(true, fixedMargin, dynamicMarginHint, horizontalSpacing, verticalSpacing);
	}

	public DynamicGridLayout(boolean verticalOverflow, int fixedMargin, int dynamicMarginHint, int horizontalSpacing,
			int verticalSpacing) {
		cachedSizes = new HashMap<>();
		cachedLocations = new HashMap<>();
		cacheComputedForSize = new Point(0, 0);

		this.verticalOverflow = verticalOverflow;

		rowHeight = columnWidth = -1;

		if (fixedMargin < 0) {
			fixedMargin = 7;
		}
		if (dynamicMarginHint < 0) {
			dynamicMarginHint = 7;
		}
		if (horizontalSpacing < 0) {
			horizontalSpacing = 5;
		}
		if (verticalSpacing < 0) {
			verticalSpacing = 5;
		}

		this.fixedMargin = fixedMargin;
		this.dynamicMarginHint = dynamicMarginHint;
		this.horizontalSpacing = horizontalSpacing;
		this.verticalSpacing = verticalSpacing;
	}

	@Override
	public Point computeSize(Composite composite, int wHint, int hHint, boolean flushCache) {
		if (flushCache) {
			flushCache();
		}
		computeLayout(composite, new Point(wHint == SWT.DEFAULT ? composite.getSize().x : wHint,
				hHint == SWT.DEFAULT ? composite.getSize().y : hHint));

		return cachedSizes.get(composite);
	}

	@Override
	protected void layout(Composite composite, boolean flushCache) {
		if (flushCache) {
			flushCache();
		}

		computeLayout(composite, composite.getSize());

		for (Control current : composite.getChildren()) {
			current.setSize(getSize(current));
			current.setLocation(cachedLocations.get(current));
		}
	}

	protected void computeLayout(Composite composite, Point totalSize) {
		if (cacheComputedForSize.equals(totalSize)
				&& cachedLocations.keySet().containsAll(Arrays.asList(composite.getChildren()))) {
			// the cached values have already been computed
			return;
		}

		if (rowHeight < 0 || columnWidth < 0) {
			// recompute
			for (Control current : composite.getChildren()) {
				Point size = getSize(current);

				columnWidth = Math.max(columnWidth, size.x);
				rowHeight = Math.max(rowHeight, size.y);
			}

			columnWidth += verticalSpacing;
			rowHeight += verticalSpacing;
		}

		if (verticalOverflow) {
			cachedSizes.put(composite, computeLayoutWithVerticalOverflow(composite, totalSize));
		} else {
			cachedSizes.put(composite, computeLayoutWithHorizontalOverflow(composite, totalSize));
		}

		cacheComputedForSize = new Point(totalSize.x, totalSize.y);
	}

	protected Point computeLayoutWithVerticalOverflow(Composite composite, Point totalSize) {
		// don't compute for more columns than there are actual children
		int colNum = Math.min(Math.max(1, totalSize.x / columnWidth), composite.getChildren().length);
		int remainingSpace = Math.max(0, totalSize.x - columnWidth * colNum);
		int leftMargin = Math.min(remainingSpace / 2, dynamicMarginHint);

		int currentCol = 0;
		int currentRow = 0;

		for (Control current : composite.getChildren()) {
			cachedLocations.put(current,
					new Point(leftMargin + currentCol * columnWidth, currentRow * rowHeight + fixedMargin));

			if (currentCol == colNum - 1) {
				// new row
				currentRow++;
				currentCol = 0;
			} else {
				currentCol++;
			}
		}

		return new Point(Math.max(totalSize.x, columnWidth), currentRow * rowHeight + 2 * fixedMargin);
	}

	protected Point computeLayoutWithHorizontalOverflow(Composite composite, Point totalSize) {
		// don't compute for more rows than there are actual children
		int rowNum = Math.min(Math.max(1, totalSize.y / rowHeight), composite.getChildren().length);
		int remainingSpace = Math.max(0, totalSize.y - rowHeight * rowNum);
		int topMargin = Math.min(remainingSpace / 2, dynamicMarginHint);

		int currentCol = 0;
		int currentRow = 0;

		for (Control current : composite.getChildren()) {
			cachedLocations.put(current,
					new Point(currentCol * columnWidth + fixedMargin, topMargin + currentRow * rowHeight));

			if (currentRow == rowNum - 1) {
				// new column
				currentRow = 0;
				currentCol++;
			} else {
				currentRow++;
			}
		}

		return new Point(currentCol * columnWidth + 2 * fixedMargin, Math.max(totalSize.y, rowHeight));
	}

	protected Point getSize(Control ctrl) {
		return getSize(ctrl, SWT.DEFAULT, SWT.DEFAULT);
	}

	protected Point getSize(Control ctrl, int wHint, int hHint) {
		if (!cachedSizes.containsKey(ctrl)) {
			cachedSizes.put(ctrl, ctrl.computeSize(wHint, hHint));
		}

		return cachedSizes.get(ctrl);
	}

	protected void flushCache() {
		cachedSizes.clear();
		cachedLocations.clear();
		rowHeight = columnWidth = -1;
		cacheComputedForSize = new Point(0, 0);
	}

}
