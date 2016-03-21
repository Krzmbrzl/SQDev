package raven.sqdev.util;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.IDocument;

/**
 * A class containing various static util methods for usage with editors
 * 
 * @author Raven
 * 		
 */
public class EditorUtil {
	
	/**
	 * Gets the word(part) in this document that occurs directly before the
	 * given offset. It will take all characters that are considered valid by
	 * <code>TextUtil.isWordPart(char c)</code>
	 * 
	 * @param document
	 *            The document to search on
	 * @param offset
	 *            The offset the search should use (has to be in range of the
	 *            document!)
	 * @return The found word(part)
	 */
	public static String getWordBeforeOffset(IDocument document, int offset) {
		Assert.isTrue(document.getLength() >= offset);
		
		// get relevant content in reverse order
		String relevantContent = new StringBuilder(document.get().substring(0, offset)).reverse()
				.toString();
				
		String word = "";
		
		for (char currentChar : relevantContent.toCharArray()) {
			if (TextUtils.isWordPart(currentChar)) {
				word += currentChar;
			} else {
				// word has ended
				break;
			}
		}
		
		// bring the word in correct order again
		word = new StringBuilder(word).reverse().toString();
		
		return word;
	}
}
