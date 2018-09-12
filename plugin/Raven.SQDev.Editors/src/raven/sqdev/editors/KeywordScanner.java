package raven.sqdev.editors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import raven.sqdev.infoCollection.base.Keyword;
import raven.sqdev.infoCollection.base.KeywordList;
import raven.sqdev.interfaces.IKeywordListChangeListener;
import raven.sqdev.interfaces.IKeywordProvider;
import raven.sqdev.interfaces.IUpdateListener;
import raven.sqdev.misc.ColorUtils;
import raven.sqdev.misc.SQDevPreferenceUtil;

/**
 * A scanner that scans for keywords and colors them with the given color.<br>
 * <br>
 * <b>Note:</b> This <code>KeywordScanner</code> can only handle one set of
 * keywords that are all colored in the same way. If you need support for
 * multiple catgeories see {@link MultiKeywordScanner}
 * 
 * @author Raven
 * 
 */
public class KeywordScanner extends RuleBasedScanner implements IKeywordListChangeListener {
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
	 * A list of <code>IKeywordListChangeListeners</code>
	 */
	protected List<IKeywordListChangeListener> keywordListListeners;

	/**
	 * A list of <code>IUpdateListener</code>
	 */
	protected List<IUpdateListener> updateListeners;

	/**
	 * Indicates whether this scanner is part of a <code>MultiKeywordScanner</code>
	 */
	protected boolean isPartOfMultiScanner;

	/**
	 * Creates an instance of this scanner
	 * 
	 * @param provider
	 *            An <code>IKeyowrdProvider</code> that will supply this method with
	 *            keywords
	 * @param colorPreferenceKey
	 *            The key of the preference describing the color of the highlighting
	 *            of the keywords
	 * @param editor
	 *            The editor this scanner is working on
	 * @param caseSensitive
	 *            Whether or not to match keywords case sensitive
	 */
	public KeywordScanner(IKeywordProvider provider, String colorPreferenceKey, BasicCodeEditor editor,
			boolean caseSensitive) {
		String strColor = SQDevPreferenceUtil.getPreferenceStore().getString(colorPreferenceKey);

		if (strColor == null || strColor.isEmpty()) {
			throw new IllegalArgumentException("Invalid preference key \"" + colorPreferenceKey + "\"");
		}

		// assign variables
		preferenceKey = colorPreferenceKey;
		this.provider = provider;
		this.editor = editor;
		this.caseSensitive = caseSensitive;
		defaultToken = Token.UNDEFINED;
		keywordListListeners = new ArrayList<IKeywordListChangeListener>();
		updateListeners = new ArrayList<IUpdateListener>();

		Color color = new Color(Display.getCurrent(), ColorUtils.decodeRGB(strColor));

		token = new Token(new TextAttribute(color, null, SWT.BOLD));

		updateRules(provider.getKeywordList().getKeywords().size() > 0);

		// add this as a listener for the keyword provider
		this.provider.addKeywordListChangeListener(this);
	}

	/**
	 * Creates an instance of this scanner (case sensitive)
	 * 
	 * @param provider
	 *            An <code>IKeyowrdProvider</code> that will supply this method with
	 *            keywords
	 * @param colorPreferenceKey
	 *            The key of the preference describing the color of the highlighting
	 *            of the keywords
	 * @param editor
	 *            The editor this scanner is working on
	 */
	public KeywordScanner(IKeywordProvider provider, String colorPreferenceKey, BasicCodeEditor editor) {
		this(provider, colorPreferenceKey, editor, true);
	}

	/**
	 * Synchronizes this scanner to a PropertyChangeEvent (adjusts the color of the
	 * highlighting
	 * 
	 * @param event
	 *            The respective PropertyChangeEvent. Must be the same as the one
	 *            this scanner has been initialized with
	 */
	public void syncToPropertyChange(PropertyChangeEvent event) {
		if (event.getProperty().equals(preferenceKey)) {
			// if the changed property is the one for the color this scanner
			// depends on
			if (event.getNewValue() != null) {
				Color color = new Color(Display.getCurrent(), ColorUtils.decodeRGB((String) event.getNewValue()));

				token = new Token(new TextAttribute(color, null, SWT.BOLD));

				updateRules(true);
			}
		}
	}

	/**
	 * Will update the applied rules for this scanner according to the
	 * keywordProvider and the {@link #token}.<br>
	 * Will apply the newly created rule immediately
	 * 
	 * @param updateEditor
	 *            Indicates if the editor shoukd be updated
	 */
	protected void updateRules(boolean updateEditor) {
		Map<String, Keyword> keywordList = provider.getKeywordList().getKeywords();

		Keyword[] keywords = keywordList.values().toArray(new Keyword[keywordList.size()]);

		// create the respective WordRule
		WordRule keywordRule = new WordRule(new WordDetector(), getDefaultToken(), !isCaseSensitive());

		// add keywords
		for (Keyword currentKeyword : keywords) {
			keywordRule.addWord(currentKeyword.getKeyword(), token);
		}

		IRule[] rules = { keywordRule };

		this.setRules(rules);

		if (!isPartOfMultiScanner && updateEditor) {
			// only update editor if this scanner is autonomous
			editor.update(false);
		}

		notifyUpdateListener();
	}

	/**
	 * Gets the rule for this <code>KeywordScanner</code> that describes which
	 * keywords to color in the respective color of this scanner
	 * 
	 * @return The respective <code>IRule</code> or <code>null</code> if no such
	 *         rule exists
	 */
	public IRule getRule() {
		if (fRules == null || fRules.length == 0) {
			return null;
		}

		return fRules[0];
	}

	/**
	 * Gets the keywordProvider of this scanner
	 */
	public IKeywordProvider getKeywordProvider() {
		return provider;
	}

	/**
	 * Sets the keywordProvider for this scanner and updates the editor accordingly
	 * 
	 * @param provider
	 *            The new keywordProvider
	 */
	public void setKeywordProvider(IKeywordProvider provider) {
		this.provider = provider;

		updateRules(true);

		notifyKeywordListChangeListeners(IKeywordListChangeListener.CTX_LIST_REMOVED);
		
		provider.addKeywordListChangeListener(this);
	}

	/**
	 * Sets the keywords for this scanner
	 * 
	 * @param list
	 *            The new keyword list
	 */
	public void setKeywords(KeywordList list) {
		IKeywordProvider provider = getKeywordProvider();
		provider.setKeywordList(list);

		setKeywordProvider(provider);

		// notify listeners about change
		notifyKeywordListChangeListeners(IKeywordListChangeListener.CTX_LIST_CHANGED);
	}

	/**
	 * Gets the token of this scanner
	 */
	public IToken getToken() {
		if (token == null) {
			token = new Token(new TextAttribute(getColor(), null, SWT.BOLD));
		}

		return token;
	}

	/**
	 * Gets the color for the token of this keywordScanner
	 */
	protected Color getColor() {
		return new Color(Display.getCurrent(),
				ColorUtils.decodeRGB(SQDevPreferenceUtil.getPreferenceStore().getString(preferenceKey)));
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
		updateRules(true);
	}

	/**
	 * Checks whether this scanner matches keywords case sensitivelys
	 */
	public boolean isCaseSensitive() {
		return caseSensitive;
	}

	/**
	 * Sets the default token the WordRule corresponding to this KeywordScanner will
	 * use as the default token
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

	/**
	 * Checks whether this <code>KeywordScanner</code> contains the given
	 * <code>Keyword</code>
	 * 
	 * @param keyword
	 *            The <code>Keyword</code> to search for
	 */
	public boolean contains(Keyword keyword) {
		return getKeywordProvider().getKeywordList().contains(keyword);
	}

	/**
	 * Checks whether this <code>KeywordScanner</code> contains a
	 * <code>Keyword</code> matching the given word
	 * 
	 * @param word
	 *            The word to search for
	 */
	public boolean contains(String word) {
		return getKeywordProvider().getKeywordList().getKeyword(word) != null;
	}

	/**
	 * Notifies all <code>IKeywordListChangeListeners</code>
	 * 
	 * @param ctx
	 *            The context of the change
	 */
	protected void notifyKeywordListChangeListeners(String ctx) {
		for (IKeywordListChangeListener listener : keywordListListeners) {
			listener.keywordListChanged(ctx);
		}
	}

	/**
	 * Notifies all <code>IUpdateListeners</code>
	 */
	protected void notifyUpdateListener() {
		for (IUpdateListener listener : updateListeners) {
			listener.updated();
		}
	}

	/**
	 * Adds the given <code>IKeywordListChangeListener</code> if it is not already
	 * registered
	 * 
	 * @param listener
	 *            The listener to add
	 */
	public void addKeywordListChangeListener(IKeywordListChangeListener listener) {
		if (!keywordListListeners.contains(listener)) {
			keywordListListeners.add(listener);
		}
	}

	/**
	 * Removes the given <code>IKeywordListChangeListener</code>
	 * 
	 * @param listener
	 *            The listener to remove
	 */
	public void removeKeywordListChangeListener(IKeywordListChangeListener listener) {
		keywordListListeners.remove(listener);
	}

	/**
	 * Adds the given listener if it is not already added
	 * 
	 * @param listener
	 *            The <code>IUpdateListener</code> to add
	 */
	public void addUpdateListener(IUpdateListener listener) {
		if (!updateListeners.contains(listener)) {
			updateListeners.add(listener);
		}
	}

	/**
	 * Removes the given listener
	 * 
	 * @param listener
	 *            The <code>IUpdateListener</code> to remove
	 */
	public void removeUpdateListener(IUpdateListener listener) {
		updateListeners.remove(listener);
	}

	@Override
	public void keywordListChanged(String ctx) {
		if (ctx.equals(IKeywordListChangeListener.CTX_LIST_CHANGED)) {
			// update rules

			updateRules(true);
		}

		// forward event
		notifyKeywordListChangeListeners(ctx);
	}
}
