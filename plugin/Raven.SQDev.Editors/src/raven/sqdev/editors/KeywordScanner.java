package raven.sqdev.editors;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import raven.sqdev.preferences.util.SQDevPreferenceUtil;
import raven.sqdev.util.ColorUtils;

/**
 * A scanner that scans for keywords and colors them with the
 * <code>ISQDevColorConstants.KEYWORD</code> color
 * 
 * @author Raven
 * 		
 */
public class KeywordScanner extends RuleBasedScanner implements IPropertyChangeListener {
	
	protected String preferenceKey;
	protected IKeywordProvider provider;
	protected BasicCodeEditor editor;
	
	/**
	 * Creates an instance of this scanner
	 * 
	 * @param provider
	 *            An <code>IKeyowrdProvider</code> that will supply this method
	 *            with keywords
	 * @param colorPreferenceKey
	 *            The key of the preference describing the color of the
	 *            highlighting of the keywords
	 */
	public KeywordScanner(IKeywordProvider provider, String colorPreferenceKey, BasicCodeEditor editor) {
		String strColor = SQDevPreferenceUtil.getPreferenceStore().getString(colorPreferenceKey);
		
		if (strColor == null || strColor.isEmpty()) {
			throw new IllegalArgumentException(
					"Invalid preference key \"" + colorPreferenceKey + "\"");
		}
		
		SQDevPreferenceUtil.getPreferenceStore().addPropertyChangeListener(this);
		
		preferenceKey = colorPreferenceKey;
		this.provider = provider;
		this.editor = editor;
		
		Color color = new Color(Display.getCurrent(), ColorUtils.decodeRGB(strColor));
		
		IToken keyword = new Token(new TextAttribute(color, null, SWT.BOLD));
		
		setToken(keyword);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getProperty().equals(preferenceKey)) {
			// if the changed property is the one for the color this scanner
			// depends on
			if(event.getNewValue() != null) {
				Color color = new Color(Display.getCurrent(), ColorUtils.decodeRGB((String) event.getNewValue()));
				IToken token = new Token(new TextAttribute(color, null, SWT.BOLD));
				
				setToken(token);
			}
		}
	}
	
	/**
	 * Sets the token for this scanner.<br>
	 * Will apply the newly created rule immediately
	 * @param token
	 */
	protected void setToken(IToken token) {
		String[] keywords = provider.getKeywords();
		
		WordRule keywordRule = new WordRule(new WordDetector());
		
		// add keywords
		for (String currentKeyword : keywords) {
			keywordRule.addWord(currentKeyword, token);
		}
		
		IRule[] rules = { keywordRule };
		
		this.setRules(rules);
		
		editor.update();
	}
}
