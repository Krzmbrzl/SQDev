package raven.sqdev.editors;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import raven.sqdev.util.ColorUtils;
import raven.sqdev.util.SQDevPreferenceUtil;

/**
 * A scanner that scans for keywords and colors them with the
 * <code>ISQDevColorConstants.KEYWORD</code> color
 * 
 * @author Raven
 * 		
 */
public class KeywordScanner extends RuleBasedScanner {
	
	/**
	 * A token that indicates an unrecognized word meaning any word, that is not
	 * part of the keywords the respective Keywordcanner works on.<br>
	 * These words are not colored in any way.
	 */
	public static IToken UNRECOGNIZED_WORD_TOKEN = new Token(
			new TextAttribute(null, null, SWT.NULL));
			
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
	 * The default token the corrsponding WordRule will use
	 */
	protected IToken defaultToken;
	
	/**
	 * indicates whether the keywords should be matched case sensitively
	 */
	protected boolean caseSensitive;
	
	/**
	 * Creates an instance of this scanner
	 * 
	 * @param provider
	 *            An <code>IKeyowrdProvider</code> that will supply this method
	 *            with keywords
	 * @param colorPreferenceKey
	 *            The key of the preference describing the color of the
	 *            highlighting of the keywords
	 * @param editor
	 *            The editor this scanner is working on
	 * @param caseSensitive
	 *            Whether or not to match keywords case sensitive
	 */
	public KeywordScanner(IKeywordProvider provider, String colorPreferenceKey,
			BasicCodeEditor editor, boolean caseSensitive) {
		String strColor = SQDevPreferenceUtil.getPreferenceStore().getString(colorPreferenceKey);
		
		if (strColor == null || strColor.isEmpty()) {
			throw new IllegalArgumentException(
					"Invalid preference key \"" + colorPreferenceKey + "\"");
		}
		
		// assign variables
		preferenceKey = colorPreferenceKey;
		this.provider = provider;
		this.editor = editor;
		this.caseSensitive = caseSensitive;
		defaultToken = UNRECOGNIZED_WORD_TOKEN;
		
		Color color = new Color(Display.getCurrent(), ColorUtils.decodeRGB(strColor));
		
		IToken keywordToken = new Token(new TextAttribute(color, null, SWT.BOLD));
		
		updateRules(keywordToken);
	}
	
	/**
	 * Creates an instance of this scanner (case sensitive)
	 * 
	 * @param provider
	 *            An <code>IKeyowrdProvider</code> that will supply this method
	 *            with keywords
	 * @param colorPreferenceKey
	 *            The key of the preference describing the color of the
	 *            highlighting of the keywords
	 * @param editor
	 *            The editor this scanner is working on
	 */
	public KeywordScanner(IKeywordProvider provider, String colorPreferenceKey,
			BasicCodeEditor editor) {
		this(provider, colorPreferenceKey, editor, true);
	}
	
	/**
	 * Synchronizes this scanner to a PropertyChangeEvent (adjusts the color of
	 * the highlighting
	 * 
	 * @param event
	 *            The respective PropertyChangeEvent. Must be the same as the
	 *            one this scanner has been initialized with
	 */
	public void syncToPropertyChange(PropertyChangeEvent event) {
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
		
		// create the respective WordRule
		WordRule keywordRule = new WordRule(new WordDetector(), getDefaultToken(),
				!isCaseSensitive());
				
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
	public IKeywordProvider getKeywordProvider() {
		return provider;
	}
	
	/**
	 * Sets the keywordProvider for this scanner and updates the editor
	 * accordingly
	 * 
	 * @param provider
	 *            The new keywordProvider
	 */
	public void setKeywordProvider(IKeywordProvider provider) {
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
	public IToken getToken() {
		return (token != null) ? token : new Token(new TextAttribute(getColor(), null, SWT.BOLD));
	}
	
	/**
	 * Gets the color for the token of this keywordScanner
	 */
	protected Color getColor() {
		return new Color(Display.getCurrent(), ColorUtils
				.decodeRGB(SQDevPreferenceUtil.getPreferenceStore().getString(preferenceKey)));
	}
	
	/**
	 * Sets whether the keywords should be matched case sensitively
	 * 
	 * @param sensitive
	 *            Whether or not to be case sensitive
	 */
	public void makeCaseSensitive(boolean sensitive) {
		caseSensitive = sensitive;
		
		// apply changes
		updateRules(getToken());
	}
	
	/**
	 * Checks whether this scanner matches keywords case sensitivelys
	 */
	public boolean isCaseSensitive() {
		return caseSensitive;
	}
	
	/**
	 * Sets the default token the WordRule corresponding to this KeywordScanner
	 * will use as the default token
	 * 
	 * @param token
	 *            The new default token
	 */
	public void setDefaultToken(IToken token) {
		Assert.isNotNull(token);
		
		defaultToken = token;
	}
	
	/**
	 * Gets the default token of this KeywordScanner
	 * 
	 * @return
	 */
	public IToken getDefaultToken() {
		return defaultToken;
	}
}
