package raven.sqdev.editors;

import org.eclipse.jface.text.rules.IWordDetector;

/**
 * A word detector for determining what is a word and what not
 * 
 * @author Raven
 * 		
 */
public class WordDetector implements IWordDetector {
	
	public WordDetector() {
	}
	
	@Override
	public boolean isWordStart(char c) {
		return (Character.isLetter(c) || c == '_' || c == '@');
	}
	
	@Override
	public boolean isWordPart(char c) {
		return (Character.isLetterOrDigit(c) || c == '_');
	}
	
}
