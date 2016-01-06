package raven.sqdev.editors;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

/**
 * A scanner that scans for keywords and colors them with the
 * <code>ISQDevColorConstants.KEYWORD</code> color
 * 
 * @author Raven
 *		
 */
public class KeywordScanner extends RuleBasedScanner {
	
	/**
	 * Creates an instance of this scanner
	 * 
	 * @param provider
	 *            An <code>IKeyowrdProvider</code> that will supply this method
	 *            with keywords
	 * @param color
	 *            The color in which these keywords should be highlighted (they
	 *            are bold in all cases)
	 */
	public KeywordScanner(IKeywordProvider provider, Color color) {
		IToken keyword = new Token(new TextAttribute(color, null, SWT.BOLD));
		
		String[] keywords = provider.getKeywords();
		
		WordRule keywordRule = new WordRule(new WordDetector());
		
		// add keywords
		for (String currentKeyword : keywords) {
			keywordRule.addWord(currentKeyword, keyword);
		}
		
		IRule[] rules = { keywordRule };
		
		this.setRules(rules);
	}
	
}
