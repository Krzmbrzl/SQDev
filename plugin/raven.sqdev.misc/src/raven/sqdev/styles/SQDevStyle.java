package raven.sqdev.styles;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.widgets.Composite;

import raven.sqdev.exceptions.SQDevCoreException;

/**
 * This class represents a String pair with an opener and a closer.<br>
 * Subclasses must either override <code>createComposite()</code> or
 * <code>getStyleRange()</code>
 * 
 * @author Raven
 * 		
 */
public class SQDevStyle {
	
	/**
	 * A character that that is placed before and after the style tag
	 */
	public static final char STYLE_MARK = '$';
	
	/**
	 * A list with all different instantiated <code>SQDevStyles</code>
	 */
	private static List<SQDevStyle> styles = new ArrayList<SQDevStyle>();
	
	/**
	 * The name of this style
	 */
	private String name;
	/**
	 * The opening sequence
	 */
	private String opener;
	/**
	 * The closing sequence
	 */
	private String closer;
	/**
	 * Indicating whether this style needs to be displayed in it's own composite
	 * (otherwise it has to be displayable in a <code>StyledText</code> widget)
	 */
	private boolean ownComposite;
	
	
	/**
	 * Creates a new <code>Pair</code>
	 * 
	 * @param name
	 *            The name of this style
	 * 			
	 * @param opener
	 *            The opening part of this pair. Must not be used by another
	 *            <code>SQDevStyle</code>
	 * @param closer
	 *            The closing part of this pair. Must not be used by another
	 *            <code>SQDevStyle</code>
	 * @param ownComposite
	 *            Indicating whether this style needs to be displayed in it's
	 *            own composite (otherwise it has to be displayable in a
	 *            <code>StyledText</code> widget)
	 */
	public SQDevStyle(String name, String opener, String closer, boolean ownComposite) {
		Assert.isTrue(name != null && opener != null && closer != null,
				"Null argument in SQDevStyle");
				
		opener = format(opener);
		closer = format(closer);
		
		this.name = name;
		this.opener = opener;
		this.closer = closer;
		this.ownComposite = ownComposite;
		
		if (styles.contains(this)) {
			// don't allow duplicate of tags
			throw new SQDevCoreException("The speified tags are already in use!");
		} else {
			// register this style
			styles.add(this);
		}
	}
	
	@SuppressWarnings("unused")
	private SQDevStyle() {
		// don't allow to use this constructor
		throw new IllegalArgumentException("The empty constructor of SQDevSytle must not be used!");
	}
	
	/**
	 * Gets the name of this style
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the opening sequence for this Pair
	 */
	public String getOpener() {
		return opener;
	}
	
	/**
	 * Gets the closing sequence for this pair
	 */
	public String getCloser() {
		return closer;
	}
	
	/**
	 * Indicates whether this style has to be displayed in it's own composite or
	 * if it can be displayed in a <code>StyledText</code>
	 * 
	 * @return <code>True</code> if it needs an own composite
	 */
	public boolean needsOwnComposite() {
		return ownComposite;
	}
	
	/**
	 * Checks whether the given content uses this pair (opener and closer)
	 * 
	 * @param content
	 *            The content to check
	 * @return <code>True</code> if the opener <b>and</b> closer appear in this
	 *         content at least once
	 */
	public boolean isUsedIn(String content) {
		return content.contains(getOpener()) && content.contains(getCloser());
	}
	
	/**
	 * Make sure the given tag is not empty and sourrounded by the proper
	 * STYLE_MARK
	 * 
	 * @param tag
	 *            The tag to process
	 */
	private final String format(String tag) {
		tag = tag.trim();
		
		Assert.isTrue(!tag.isEmpty());
		
		if (!tag.startsWith(String.valueOf(STYLE_MARK))) {
			tag = STYLE_MARK + tag;
		}
		
		if (!tag.endsWith(String.valueOf(STYLE_MARK))) {
			tag += STYLE_MARK;
		}
		
		return tag;
	}
	
	@Override
	public String toString() {
		return "SQDevStyle - " + getName();
	}
	
	/**
	 * Creates a <code>Composite</code> representing the given content in this
	 * style.<br>
	 * The layoutData for the created <code>Composite</code> will not be set!
	 * 
	 * @param parent
	 *            The parent to the created <code>Composite</code>
	 * @param content
	 *            The content that should be displayed in this style
	 * @return The created <code>Composite</code> or usally <code>null</code> if
	 *         this style does not need to be displayed in it's own
	 *         <code>Composite</code>
	 */
	public Composite createComposite(Composite parent, String content) {
		if (needsOwnComposite()) {
			throw new SQDevCoreException("The composite creation has not yet been implemented!");
		}
		
		// does not need it's own composite
		return null;
	}
	
	/**
	 * Gets the <code>StyleRange</code> for this style.<br>
	 * The start and length of this range are not set!
	 * 
	 * @return The respective <code>StyleRange</code> or <code>null</code> if
	 *         this style needs it's own composite
	 */
	public StyleRange getStyleRange() {
		if (needsOwnComposite()) {
			// needs own composite
			return null;
		}
		
		throw new SQDevCoreException("The StyleRange has not yet been implemented!");
	}
	
	/**
	 * Get all the <code>SQDevStyle</code>s that have yet been instantiated
	 */
	public static List<SQDevStyle> getStyles() {
		return styles;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SQDevStyle)) {
			return false;
		}
		
		SQDevStyle comp = (SQDevStyle) obj;
		
		if (this.getOpener().equals(comp.getOpener())
				&& this.getCloser().equals(comp.getCloser())) {
			// the opening and closing tags are import
			return true;
		}
		
		return false;
	}
	
}
