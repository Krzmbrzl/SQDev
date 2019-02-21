package raven.sqdev.editors;

import org.eclipse.jface.text.rules.IWordDetector;

import raven.sqdev.misc.TextUtils;

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
		return TextUtils.isWordStart(c);
	}
	
	@Override
	public boolean isWordPart(char c) {
		return TextUtils.isWordPart(c);
	}
	
}
