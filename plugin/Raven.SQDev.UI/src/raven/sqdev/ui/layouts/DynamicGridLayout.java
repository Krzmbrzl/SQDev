package raven.sqdev.ui.layouts;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;

/**
 * A {@linkplain Layout} that displays its content in a grid with columns of
 * equal width and rows of equal height. The number of columns (or rows if
 * {@link #verticalOverflow} is false) is chosen so that as many elements fit on
 * the given space.
 * 
 * @author Raven
 *
 */
public class DynamicGridLayout extends Layout {

	/**
	 * A {@linkplain Map} containing all cached sizes
	 */
	Map<Control, Point> cachedSizes;
	/**
	 * A {@linkplain Map} containing all cached locations
	 */
	Map<Control, Point> cachedLocations;
	/**
	 * The {@linkplain Point} describing the size {@link #cachedLocations} and
	 * {@link #cachedSizes} have been calculated for
	 */
	Point cacheComputedForSize;
	/**
	 * The height of the displayed rows
	 */
	int rowHeight;
	/**
	 * The width of the displayed columns
	 */
	int columnWidth;
	/**
	 * A flag indicating whether overflowing content should be added vertically
	 * instead of horizontally
	 */
	boolean verticalOverflow;
	/**
	 * The fixed margin (top and bottom if {@link #verticalOverflow} is true, left
	 * and right otherwise)
	 */
	int fixedMargin;
	/**
	 * The maximum value for the dynamic margin (left if {@link #verticalOverflow}
	 * is true, top otherwise)
	 */
	int dynamicMarginHint;
	/**
	 * The horizontal spacing between the elements in the grid
	 */
	int horizontalSpacing;
	/**
	 * The vertical spacing between the elements
	 */
	int verticalSpacing;

	/**
	 * Creates a new instance of this layout in vertical-overflow-mode and with
	 * default values
	 */
	public DynamicGridLayout() {
		this(true, SWT.DEFAULT, SWT.DEFAULT, SWT.DEFAULT, SWT.DEFAULT);
	}

	/**
	 * Creates a new instance of this layout with default values
	 * 
	 * @param verticalOverflow
	 *            Whether to use vertical overflow for elements that don't fit on
	 *            the given space. If this is <code>false</code> horizontal overflow
	 *            is being used
	 */
	public DynamicGridLayout(boolean verticalOverflow) {
		this(verticalOverflow, SWT.DEFAULT, SWT.DEFAULT, SWT.DEFAULT, SWT.DEFAULT);
	}

	/**
	 * Creates a new instance of this layout with default spacing between elements
	 * 
	 * @param verticalOverflow
	 *            fixedMargin Whether to use vertical overflow for elements that
	 *            don't fit on the given space. If this is <code>false</code>
	 *            horizontal overflow is being used
	 * @param fixedMargin
	 *            The size of the fixed margin. See {@link #fixedMargin}
	 * @param dynamicMarginHint
	 *            The maximum value for the dynamic margin. See
	 *            {@link #dynamicMarginHint}
	 */
	public DynamicGridLayout(boolean verticalOverflow, int fixedMargin, int dynamicMarginHint) {
		this(verticalOverflow, fixedMargin, dynamicMarginHint, SWT.DEFAULT, SWT.DEFAULT);
	}

	/**
	 * Creates a new instance of this layout in vertical-overflow-mode and with
	 * default spacing between elements
	 * 
	 * @param fixedMargin
	 *            The size of the fixed margin. See {@link #fixedMargin}
	 * @param dynamicMarginHint
	 *            The maximum value for the dynamic margin. See
	 *            {@link #dynamicMarginHint}
	 */
	public DynamicGridLayout(int fixedMargin, int dynamicMarginHint) {
		this(true, fixedMargin, dynamicMarginHint, SWT.DEFAULT, SWT.DEFAULT);
	}

	/**
	 * Creates a new instance of this layout in vertical-overflow-mode
	 * 
	 * @param fixedMargin
	 *            The size of the fixed margin. See {@link #fixedMargin}
	 * @param dynamicMarginHint
	 *            The maximum value for the dynamic margin. See
	 *            {@link #dynamicMarginHint}
	 * @param horizontalSpacing
	 *            The value for the horizontal spacing between elements
	 * @param verticalSpacing
	 *            The value for horizontal spacing between elements
	 */
	public DynamicGridLayout(int fixedMargin, int dynamicMarginHint, int horizontalSpacing, int verticalSpacing) {
		this(true, fixedMargin, dynamicMarginHint, horizontalSpacing, verticalSpacing);
	}

	/**
	 * Creates a new instance of this layout
	 * 
	 * @param verticalOverflow
	 *            fixedMargin Whether to use vertical overflow for elements that
	 *            don't fit on the given space. If this is <code>false</code>
	 *            horizontal overflow is being used
	 * @param fixedMargin
	 *            The size of the fixed margin. See {@link #fixedMargin}
	 * @param dynamicMarginHint
	 *            The maximum value for the dynamic margin. See
	 *            {@link #dynamicMarginHint}
	 * @param horizontalSpacing
	 *            The value for the horizontal spacing between elements
	 * @param verticalSpacing
	 *            The value for horizontal spacing between elements
	 */
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

	/**
	 * Computes the layout for the given available space and populates
	 * {@link #cachedLocations} and {@link #cachedSizes}. If
	 * {@link #cacheComputedForSize} is equal to the given total size and
	 * {@link #cachedLocations} contains the given {@linkplain Composite} this
	 * method immediately returns assuming that the cached values are appropriate
	 * already
	 * 
	 * @param composite
	 *            The {@linkplain Composite} to layout
	 * @param totalSize
	 *            The total available space
	 */
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

	/**
	 * Computes the layout in vertical-overflow-mode for the given available space
	 * and populates {@link #cachedLocations} and {@link #cachedSizes}.
	 * 
	 * @param composite
	 *            The {@linkplain Composite} to layout
	 * @param totalSize
	 *            The total available space
	 * @return A {@linkplain Point} describing the total size needed for the layout
	 *         content
	 */
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

	/**
	 * Computes the layout in horizontal-overflow-mode for the given available space
	 * and populates {@link #cachedLocations} and {@link #cachedSizes}.
	 * 
	 * @param composite
	 *            The {@linkplain Composite} to layout
	 * @param totalSize
	 *            The total available space
	 * @return A {@linkplain Point} describing the total size needed for the layout
	 *         content
	 */
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

	/**
	 * Gets the size of the given control. If {@link #cachedSizes} contains an entry
	 * for it, the cached value will be returned. Otherwise the control is asked to
	 * compute its size without implying size-restrictions which is then stored in
	 * {@link #cachedSizes}.
	 * 
	 * @param ctrl
	 *            The {@linkplain Control} whose size should be determined
	 * @return The size of the control
	 */
	protected Point getSize(Control ctrl) {
		return getSize(ctrl, SWT.DEFAULT, SWT.DEFAULT);
	}

	/**
	 * Gets the size of the given control. If {@link #cachedSizes} contains an entry
	 * for it, the cached value will be returned. Otherwise the control is asked to
	 * compute its size for the given size-restrictions which is then stored in
	 * {@link #cachedSizes}.
	 * 
	 * @param ctrl
	 *            The {@linkplain Control} whose size should be determined
	 * @param wHint
	 *            The width-hint for the control if it is being asked to compute its
	 *            size
	 * @param hHint
	 *            The height-hint for the control if it is being asked to compute
	 *            its size
	 * @return The size of the control
	 */
	protected Point getSize(Control ctrl, int wHint, int hHint) {
		if (!cachedSizes.containsKey(ctrl)) {
			cachedSizes.put(ctrl, ctrl.computeSize(wHint, hHint));
		}

		return cachedSizes.get(ctrl);
	}

	/**
	 * Clears all cached values in this layout
	 */
	protected void flushCache() {
		cachedSizes.clear();
		cachedLocations.clear();
		rowHeight = columnWidth = -1;
		cacheComputedForSize = new Point(0, 0);
	}

}
