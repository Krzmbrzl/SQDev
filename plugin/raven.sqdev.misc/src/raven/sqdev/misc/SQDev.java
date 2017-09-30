package raven.sqdev.misc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import raven.sqdev.styles.BoldStyle;
import raven.sqdev.styles.CodeStyle;
import raven.sqdev.styles.SQDevStyle;

/**
 * The SQDev base class holds various constants and utility methods for those
 * that are used throughout the plugin
 * 
 * @author Raven
 * 		
 */
public final class SQDev {
	
	/**
	 * The code style
	 */
	public static final CodeStyle CODE = new CodeStyle();
	/**
	 * The bold style
	 */
	public static final BoldStyle BOLD = new BoldStyle();
	
	/**
	 * Checks whether the given content uses SQDev-styling
	 * 
	 * @param content
	 *            The content to check
	 */
	public static final boolean isStyled(String content) {
		for (SQDevStyle currentStyle : SQDevStyle.getStyles()) {
			if (currentStyle.isUsedIn(content)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Gets the SQDev styles used in the given content
	 * 
	 * @param content
	 *            The content to check
	 * @return A list of found styles
	 */
	public static final List<SQDevStyle> getUsedStyles(String content) {
		List<SQDevStyle> usedStyles = new ArrayList<SQDevStyle>();
		
		for (SQDevStyle currentStyle : SQDevStyle.getStyles()) {
			if (currentStyle.isUsedIn(content)) {
				usedStyles.add(currentStyle);
			}
		}
		
		return usedStyles;
	}
	
	/**
	 * Display the (styled) content on the given parent
	 * 
	 * @param parent
	 *            The parent <code>Composite</code> that will hold the content
	 * @param content
	 *            The (styled) content to put on the given parent
	 * @throws IOException
	 */
	public static final void createStyledComposite(Composite parent, String content)
			throws IOException {
		// make a layout with 1 column
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		
		parent.setLayout(layout);
		
		if (isStyled(content)) {
			// get the different regions
			ArrayList<StyledRegion> regions = getStyledRegions(content);
			ArrayList<StyledText> textComposites = new ArrayList<>();
			
			int currentStyledText = -1;
			boolean needsNewStyledText = true;
			
			// create the composite according to the styled regions
			for (StyledRegion currentRegion : regions) {
				if (currentRegion.needsOwnComposite()) {
					Composite createdComposite = currentRegion.getStyle().createComposite(parent,
							currentRegion.getContent());
							
					createdComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
					
					// indicate that further content must be placed in it's own
					// styledText
					needsNewStyledText = true;
				} else {
					// create a new styledText to display information
					StyledText styledText;
					
					if (needsNewStyledText) {
						// create new StyledText
						styledText = new StyledText(parent, SWT.MULTI | SWT.WRAP | SWT.READ_ONLY);
						
						styledText.setBackground(parent.getBackground());
						styledText.setForeground(parent.getForeground());
						
						styledText.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
						
						// add to list
						textComposites.add(styledText);
						
						// update index
						currentStyledText++;
						needsNewStyledText = false;
					} else {
						styledText = textComposites.get(currentStyledText);
					}
					
					currentRegion.addToStyledText(styledText);
				}
			}
		} else {
			// simply create a Text and fill it with the given content
			Text text = new Text(parent, SWT.MULTI | SWT.WRAP | SWT.READ_ONLY);
			text.setText(content);
			text.setBackground(parent.getBackground());
			text.setForeground(parent.getForeground());
			
			text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		}
	}
	
	/**
	 * Gets the <code>SQDevStyle</code> the given content <b>starts</b> with (=
	 * The content starts with the opening tag of the respective style and the
	 * content contains the respective closing tag)
	 * 
	 * @param content
	 *            The content to process
	 * @return The starting <code>SQDevStyle</code> or <code>null</code> if none
	 *         could be found
	 */
	public static final SQDevStyle getStartingStyle(String content) {
		for (SQDevStyle currentStyle : getUsedStyles(content)) {
			if (content.startsWith(currentStyle.getOpener())) {
				return currentStyle;
			}
		}
		
		return null;
	}
	
	/**
	 * Gets the last unstyled part of a String
	 * 
	 * @param content
	 *            The content to to process
	 * @return The last unstyled part of the given content
	 */
	public static final String getLastUnstyledPart(String content) {
		int index = content.lastIndexOf(String.valueOf(SQDevStyle.STYLE_MARK));
		
		while (index > 0) {
			String fragment1 = content.substring(0, index + 1);
			String fragment2 = content.substring(index + 1);
			
			for (SQDevStyle currentStyle : SQDevStyle.getStyles()) {
				if (fragment1.endsWith(currentStyle.getCloser())) {
					return fragment2;
				}
			}
			
			// fragment1 doesn't end with a style tag closer
			index = fragment1.substring(0, fragment1.length() - 1)
					.lastIndexOf(String.valueOf(SQDevStyle.STYLE_MARK));
		}
		
		// no styled region in the content -> the complete content is the last
		// unstyled part
		return content;
	}
	
	/**
	 * Gets the first part of the goven content taht is styled with the given
	 * style
	 * 
	 * @param content
	 *            The content to process
	 * @param style
	 *            The style to searh for
	 * @return The styled part or <code>null</code> if the style is not used in
	 *         the given content
	 */
	public static final String getStyledPart(String content, SQDevStyle style) {
		if (!style.isUsedIn(content)) {
			return null;
		}
		
		String styledPart = content.substring(content.indexOf(style.getOpener()),
				content.indexOf(style.getCloser()));
				
		// remove opener tag
		styledPart = styledPart.substring(style.getOpener().length());
		
		return styledPart;
	}
	
	/**
	 * Seperates the given content in different styles regions
	 * 
	 * @param content
	 *            The content to process
	 * @return A list conatining <code>Pair</code>s of a <code>SQDevStyle</code>
	 *         (<code>null</code> if unstyled) and the content of this styled
	 *         region
	 */
	public static ArrayList<StyledRegion> getStyledRegions(String content) {
		ArrayList<StyledRegion> regions = new ArrayList<StyledRegion>(1);
		
		// gather the different styled regions within this String
		int currentIndex = 0;
		
		String styleMark = String.valueOf(SQDevStyle.STYLE_MARK);
		
		String transfer = "";
		
		while (content.substring(currentIndex).contains(styleMark)) {
			String fragment1 = content.substring(currentIndex);
			fragment1 = fragment1.substring(0, fragment1.indexOf(styleMark));
			
			String fragment2 = content.substring(currentIndex);
			fragment2 = fragment2.substring(fragment2.indexOf(styleMark));
			
			// check if fragment2 starts with a style tag
			SQDevStyle style = getStartingStyle(fragment2);
			
			if (style == null) {
				// the discovered styleMark is just part of the text
				// reassign the index of the next styleMark
				currentIndex = currentIndex + fragment1.length() + 1;
				
				transfer += fragment1;
			} else {
				// get the previous unstyled part (taking an eventual transfer
				// into consideration
				String unstyledPart = transfer + getLastUnstyledPart(fragment1);
				
				// reset transfer because it's now processed
				transfer = "";
				
				if (!unstyledPart.isEmpty()) {
					// insert unstyled region
					regions.add(new StyledRegion(null, unstyledPart));
				}
				
				String styledPart = getStyledPart(fragment2, style);
				
				if (!styledPart.isEmpty()) {
					// add styled region
					regions.add(new StyledRegion(style, styledPart));
				}
				
				String styledPartWithTags = style.getOpener() + styledPart + style.getCloser();
				
				// reassign the index of the next styleMark
				String remaining = fragment2.substring(styledPartWithTags.length());
				currentIndex = content.length() - remaining.length();
			}
		}
		
		// add eventual rest of content that is unstyled
		String lastUnstyledRegion = content.substring(currentIndex);
		
		if (!lastUnstyledRegion.isEmpty()) {
			regions.add(new StyledRegion(null, lastUnstyledRegion));
		}
		
		return regions;
	}
	
	private SQDev() {
	}
	
}
