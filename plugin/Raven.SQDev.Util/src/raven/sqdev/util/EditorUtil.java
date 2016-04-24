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
	 * Gets the wordpart in this document that occurs directly before the given
	 * offset. It will take all characters that are considered valid by
	 * <code>TextUtil.isWordPart(char c)</code>
	 * 
	 * @param document
	 *            The document to search on
	 * @param offset
	 *            The offset the search should use (has to be in range of the
	 *            document!)
	 * @return The found wordpart (may be empty)
	 */
	public static String getWordPartBeforeOffset(IDocument document, int offset) {
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
	
	/**
	 * Gets the wordpart in this document that occurs directly after the given
	 * offset. It will take all characters that are considered valid by
	 * <code>TextUtil.isWordPart(char c)</code>
	 * 
	 * @param document
	 *            The document to search on
	 * @param offset
	 *            The offset the search should use (has to be in range of the
	 *            document!)
	 * @return The found wordpart (may be empty)
	 */
	public static String getWordPartAfterOffset(IDocument document, int offset) {
		Assert.isTrue(document.getLength() >= offset);
		
		String relevantContent = document.get().substring(offset);
		
		String word = "";
		
		for (char currentChar : relevantContent.toCharArray()) {
			if (TextUtils.isWordPart(currentChar)) {
				word += currentChar;
			} else {
				// word has ended
				break;
			}
		}
		
		return word;
	}
	
	/**
	 * Gets the word the given offset corresponds to
	 * 
	 * @param document
	 *            The doument to search in
	 * @param offset
	 *            The offset to search for. Has to bein range of the document!
	 * @return The found word (may be empty)
	 */
	public static String getWordAroundOffset(IDocument document, int offset) {
		return getWordPartBeforeOffset(document, offset) + getWordPartAfterOffset(document, offset);
	}
}
