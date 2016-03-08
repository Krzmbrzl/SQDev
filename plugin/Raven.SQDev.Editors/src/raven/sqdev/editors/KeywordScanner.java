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
	
	/**
	 * The preferenceKey for the color of the token
	 */
	protected String preferenceKey;
	
	/**
	 * The keyworProvider for this token
	 */
	protected IKeywordProvider provider;
	
	/**
	 * The editor this scanner is working for
	 */
	protected BasicCodeEditor editor;
	
	/**
	 * The token this scanner produces
	 */
	protected IToken token;
	
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
	public KeywordScanner(IKeywordProvider provider, String colorPreferenceKey,
			BasicCodeEditor editor) {
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
		
		IToken keywordToken = new Token(new TextAttribute(color, null, SWT.BOLD));
		
		updateRules(keywordToken);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getProperty().equals(preferenceKey)) {
			// if the changed property is the one for the color this scanner
			// depends on
			if (event.getNewValue() != null) {
				Color color = new Color(Display.getCurrent(),
						ColorUtils.decodeRGB((String) event.getNewValue()));
						
				IToken token = new Token(new TextAttribute(color, null, SWT.BOLD));
				
				updateRules(token);
			}
		}
	}
	
	/**
	 * Will update the applied rules for this scanner according to the
	 * keywordProvider.<br>
	 * Will apply the newly created rule immediately
	 * 
	 * @param token
	 *            The token the rule should use
	 */
	protected void updateRules(IToken token) {
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
	
	/**
	 * Gets the keywordProvider of this scanner
	 */
	protected IKeywordProvider getKeywordProvider() {
		return provider;
	}
	
	/**
	 * Sets the keywordProvider for this scanner and updates the editor
	 * accordingly
	 * 
	 * @param provider
	 *            The new keywordProvider
	 */
	protected void setKeywordProvider(IKeywordProvider provider) {
		this.provider = provider;
		
		updateRules(getToken());
	}
	
	/**
	 * Sets the keywords for this scanner
	 * 
	 * @param keywords
	 *            The new keywords
	 */
	public void setKeywords(String[] keywords) {
		IKeywordProvider provider = getKeywordProvider();
		provider.setKeywords(keywords);
		
		setKeywordProvider(provider);
	}
	
	/**
	 * Gets the token of this scanner
	 */
	protected IToken getToken() {
		return (token != null) ? token : new Token(new TextAttribute(getColor(), null, SWT.BOLD));
	}
	
	/**
	 * Gets the color for the token of this keywordScanner
	 */
	protected Color getColor() {
		return new Color(Display.getCurrent(), ColorUtils
				.decodeRGB(SQDevPreferenceUtil.getPreferenceStore().getString(preferenceKey)));
	}
}
