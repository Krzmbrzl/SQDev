package raven.sqdev.misc;

import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;

import raven.sqdev.styles.SQDevStyle;

public class StyledRegion extends Pair<SQDevStyle, String> {
	
	/**
	 * Creates a new <code>StyledRegion</code>
	 * 
	 * @param style
	 *            The style of this region (<code>null</code> if unstyled)
	 * @param content
	 *            The content of this styled region
	 */
	public StyledRegion(SQDevStyle obj1, String obj2) {
		super(obj1, obj2);
	}
	
	/**
	 * Gets the <code>SQDevStyle</code> of this <code>StyledRegion</code> (
	 * <code>null</code> if unstyled)
	 */
	public SQDevStyle getStyle() {
		return getFirst();
	}
	
	/**
	 * Gets the content of this <code>StyledRegion</code>
	 */
	public String getContent() {
		return getSecond();
	}
	
	/**
	 * Indicates whether this styledRegion has to be displayed in it's own
	 * composite or if it can be displayed in a <code>StyledText</code>
	 * 
	 * @return <code>True</code> if it needs an own composite
	 */
	public boolean needsOwnComposite() {
		return (getStyle() == null) ? false : getStyle().needsOwnComposite();
	}
	
	/**
	 * Adds this <code>StyledRegion</code> to the given <code>StyledText</code>
	 * if possible
	 * 
	 * @param styledText
	 *            The <code>StyledText</code> this region should be added to
	 * @return <code>True</code> if the content of this
	 *         <code>StyledRegion</code> could be added to the given
	 *         <code>StyledText</code>. <code>False</code> otherwise (when this
	 *         <code>StyledRegion</code> needs it's own <code>Composite</code>)
	 */
	public boolean addToStyledText(StyledText styledText) {
		if (needsOwnComposite()) {
			// this is not the proper way to display this region
			return false;
		}
		
		// add the previous content of the styledText
		String prevText = styledText.getText();
		
		// apply the new text
		styledText.append(getContent());
		
		if (getStyle() != null) {
			// apply the styleRange if there actually is a style
			StyleRange range = getStyle().getStyleRange();
			// set the actual range
			range.start = prevText.length();
			range.length = getContent().length();
			
			// add styleRange
			styledText.setStyleRange(range);
		}
		
		return true;
	}
	
	public String toString() {
		return "Style: \n\t" + getStyle() + "\nContent: \n\t" + getContent().replace("\n", "\n\t");
	}
	
}
