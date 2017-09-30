package raven.sqdev.editors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.DefaultAnnotationHover;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

import raven.sqdev.constants.ISQDevColorConstants;
import raven.sqdev.constants.SQDevPreferenceConstants;
import raven.sqdev.infoCollection.base.Keyword;
import raven.sqdev.misc.SQDevPreferenceUtil;

/**
 * Basic implementation of a <code>SourceViewerConfiguration</code>
 * 
 * @author Raven
 * 
 * @see {@linkplain SourceViewerConfiguration}
 * 
 */
public class BasicSourceViewerConfiguration extends SourceViewerConfiguration
		implements IPropertyChangeListener {
	
	/**
	 * The color manager
	 */
	protected ColorManager colorManager;
	
	/**
	 * The configured keywordScanner providing the keywords for the syntax
	 * highlighting<br>
	 * They are sorted according to the PreferenceKey they use for the color of
	 * their keyword highlighting
	 */
	protected Map<String, KeywordScanner> configuredKeywordScanners;
	
	/**
	 * The <code>MultiKeywordScanner</code> that will be applied for this
	 * <code>SourceViewerConfiguration</code>
	 */
	protected MultiKeywordScanner multiScanner;
	
	/**
	 * The editor this SourceViewer is applied on
	 */
	protected BasicCodeEditor editor;
	
	/**
	 * The contentAssistant for this editor
	 */
	protected ContentAssistant assistant;
	
	public BasicSourceViewerConfiguration(ColorManager manager, BasicCodeEditor editor) {
		this.setColorManager(manager);
		this.editor = editor;
		
		this.configuredKeywordScanners = new HashMap<String, KeywordScanner>();
		this.multiScanner = new MultiKeywordScanner(editor);
		
		// register to get notified about preference changes
		SQDevPreferenceUtil.getPreferenceStore().addPropertyChangeListener(this);
	}
	
	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return editor.getBasicProvider().getPartitionScanner().getConfiguredContentTypes();
	}
	
	public ColorManager getColorManager() {
		return colorManager;
	}
	
	public void setColorManager(ColorManager colorManager) {
		this.colorManager = colorManager;
	}
	
	/**
	 * Gets all configured Keywords
	 */
	public List<Keyword> getAllConfiguredKeywords() {
		ArrayList<Keyword> keywordList = new ArrayList<Keyword>();
		
		Iterator<Entry<String, KeywordScanner>> iterator = configuredKeywordScanners.entrySet()
				.iterator();
		
		while (iterator.hasNext()) {
			// add the respective keywords to the list
			keywordList.addAll(
					iterator.next().getValue().getKeywordProvider().getKeywordList().getKeywords());
		}
		
		return keywordList;
	}
	
	/**
	 * Gets a list of all configured Keywords starting with the given character
	 * 
	 * @param c
	 *            The starting charcter
	 */
	public List<Keyword> getConfiguredKeywordsFor(char c) {
		ArrayList<Keyword> keywordList = new ArrayList<Keyword>();
		
		Iterator<Entry<String, KeywordScanner>> iterator = configuredKeywordScanners.entrySet()
				.iterator();
		
		while (iterator.hasNext()) {
			// add the respective keywords to the list
			keywordList.addAll(
					iterator.next().getValue().getKeywordProvider().getKeywordList().getListFor(c));
		}
		
		return keywordList;
	}
	
	/**
	 * Gets the keywordScanner for this configuration
	 * 
	 * @param colorPreferenceKey
	 *            The preference key for the color of the desired
	 *            <code>KeywordScanner</code>
	 * @return The <code>KeywordScanner</code> working for the given preference
	 *         key or <code>null</code> if no such scanner could be found
	 */
	public KeywordScanner getKeywordScanner(String colorPreferenceKey) {
		if (!configuredKeywordScanners.containsKey(colorPreferenceKey)) {
			return null;
		}
		
		// return scanner
		return configuredKeywordScanners.get(colorPreferenceKey);
	}
	
	/**
	 * Creates a <code>KeywordScanner</code> for the given preference key that
	 * is automatically registered to this
	 * <code>BasicSourceViewerConfiguration</code> if there is no other scanner
	 * for that key
	 * 
	 * @param colorPreferenceKey
	 *            The key the scanner should be configured for
	 * @param caseSensitive
	 *            Whether or not the created scanner should be case sensitive
	 */
	public void createKeywordScanner(String colorPreferenceKey, boolean caseSensitive) {
		if (!configuredKeywordScanners.containsKey(colorPreferenceKey)) {
			// create new scanner
			KeywordScanner scanner = new KeywordScanner(new BasicKeywordProvider(),
					colorPreferenceKey, this.editor);
			
			if (!caseSensitive) {
				scanner.makeCaseSensitive(caseSensitive);
			}
			
			configuredKeywordScanners.put(colorPreferenceKey, scanner);
			
			// add to multiScanner
			multiScanner.addScanner(scanner);
		}
	}
	
	/**
	 * Checks if a <code>KeywordScanner</code> for the given preference key does
	 * exist in this <code>BasicSourceViewerConfiguration</code>
	 * 
	 * @param colorPreferenceKey
	 *            The preference key the scanner has to work on
	 */
	public boolean scannerExists(String colorPreferenceKey) {
		return configuredKeywordScanners.containsKey(colorPreferenceKey);
	}
	
	/**
	 * Gets the <code>KeywordScanner</code> that contains the given
	 * <code>Keyword</code>
	 * 
	 * @param keyword
	 *            The <code>Keyword</code> to search for
	 * @return The matching <code>KeywordScanner</code> or <code>null</code> if
	 *         none could be found
	 */
	public KeywordScanner getKeywordScannerContaining(Keyword keyword) {
		Iterator<Entry<String, KeywordScanner>> iterator = configuredKeywordScanners.entrySet()
				.iterator();
		
		while (iterator.hasNext()) {
			KeywordScanner currentScanner = iterator.next().getValue();
			
			if (currentScanner.contains(keyword)) {
				return currentScanner;
			}
		}
		
		return null;
	}
	
	/**
	 * Gets the <code>KeywordScanner</code> that contains a <code>Keyword</code>
	 * matching the given word
	 * 
	 * @param word
	 *            The word to search for
	 * @return The matching <code>KeywordScanner</code> or <code>null</code> if
	 *         none could be found
	 */
	public KeywordScanner getKeywordScannerContaining(String word) {
		Iterator<Entry<String, KeywordScanner>> iterator = configuredKeywordScanners.entrySet()
				.iterator();
		
		while (iterator.hasNext()) {
			KeywordScanner currentScanner = iterator.next().getValue();
			
			if (currentScanner.contains(word)) {
				return currentScanner;
			}
		}
		
		return null;
	}
	
	@Override
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();
		
		// syntax highlighting for keywords
		DefaultDamagerRepairer dr_Default = new DefaultDamagerRepairer(multiScanner);
		reconciler.setDamager(dr_Default, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr_Default, IDocument.DEFAULT_CONTENT_TYPE);
		
		boolean containsString = false;
		boolean containsComment = false;
		
		for (String currentType : editor.getBasicProvider().getPartitionScanner()
				.getConfiguredContentTypes()) {
			switch (currentType) {
				case BasicPartitionScanner.BASIC_STRING:
					containsString = true;
					break;
				
				case BasicPartitionScanner.BASIC_COMMENT:
					containsComment = true;
					break;
			}
		}
		
		if (containsString) {
			// colorize strings
			NonRuleBasedDamagerRepairer ndr_String = new NonRuleBasedDamagerRepairer(
					new TextAttribute(colorManager.getColor(ISQDevColorConstants.STRING)));
			reconciler.setDamager(ndr_String, BasicPartitionScanner.BASIC_STRING);
			reconciler.setRepairer(ndr_String, BasicPartitionScanner.BASIC_STRING);
		}
		
		if (containsComment) {
			// colorize comments
			NonRuleBasedDamagerRepairer ndr_Comment = new NonRuleBasedDamagerRepairer(
					new TextAttribute(colorManager.getColor(ISQDevColorConstants.COMMENT)));
			reconciler.setDamager(ndr_Comment, BasicPartitionScanner.BASIC_COMMENT);
			reconciler.setRepairer(ndr_Comment, BasicPartitionScanner.BASIC_COMMENT);
		}
		
		return reconciler;
	}
	
	@Override
	public IContentAssistant getContentAssistant(ISourceViewer viewer) {
		assistant = new ContentAssistant();
		assistant.enableAutoInsert(SQDevPreferenceUtil.isAutoCompleteEnabled());
		assistant.enableColoredLabels(true);
		
		IContentAssistProcessor processor = new BasicContentAssistProcessor(editor);
		
		assistant.setContentAssistProcessor(processor, IDocument.DEFAULT_CONTENT_TYPE);
		
		assistant.setInformationControlCreator(getInformationControlCreator(viewer));
		
		return assistant;
	}
	
	@Override
	public ITextHover getTextHover(ISourceViewer sv, String contentType) {
		return new BasicTextHover(editor);
	}
	
	@Override
	public IAnnotationHover getAnnotationHover(ISourceViewer sourceViewer) {
		return new DefaultAnnotationHover();
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getNewValue() == null) {
			// if there is no useful new value just ignore it
			return;
		}
		
		// watch out for a change concerning the autoCompletion
		switch (event.getProperty()) {
			case SQDevPreferenceConstants.SQDEV_EDITOR_ENABLE_AUTOCOMPLETE_KEY:
				assistant.enableAutoActivation((boolean) event.getNewValue());
				break;
			
			default:
				if (configuredKeywordScanners.containsKey(event.getProperty())) {
					// configure respective keyword scanner
					configuredKeywordScanners.get(event.getProperty()).syncToPropertyChange(event);
				} else {
					// don't update editor
					return;
				}
		}
		
		editor.update(true);
	}
}
