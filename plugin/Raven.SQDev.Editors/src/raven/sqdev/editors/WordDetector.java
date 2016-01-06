package raven.sqdev.editors;

import org.eclipse.jface.text.rules.IWordDetector;

public class WordDetector implements IWordDetector {

	public WordDetector() { }

	@Override
	public boolean isWordStart(char c) {
		return Character.isLetter(c);
	}

	@Override
	public boolean isWordPart(char c) {
		return Character.isLetterOrDigit(c);
	}

}
