package raven.sqdev.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

import raven.sqdev.preferences.util.ISQDevColorConstants;
import raven.sqdev.preferences.util.SQDevPreferenceConstants;

/**
 * Basic implementation of a <code>SourceViewerConfiguration</code>
 * 
 * @author Raven
 * 		
 * @see {@linkplain SourceViewerConfiguration}
 * 		
 */
public class BasicSourceViewerConfiguration extends SourceViewerConfiguration {
	
	/**
	 * The color manager
	 */
	protected ColorManager colorManager;
	
	/**
	 * The keywordScanner providing the keywords for the syntax highlighting
	 */
	protected KeywordScanner keywordScanner;
	
	/**
	 * The editor this SourceViewer is applied on
	 */
	protected BasicCodeEditor editor;
	
	public BasicSourceViewerConfiguration(ColorManager manager, BasicCodeEditor editor) {
		this.setColorManager(manager);
		this.editor = editor;
	}
	
	@Override
	public String getConfiguredDocumentPartitioning(ISourceViewer sourceViewer) {
		return BasicPartitionScanner.BASIC_PARTITIONING;
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
	 * Gets the keywordScanner for this configuration
	 * 
	 */
	public KeywordScanner getKeywordScanner() {
		
		if (this.keywordScanner == null) {
			this.keywordScanner = new KeywordScanner(new BasicKeywordProvider(),
					SQDevPreferenceConstants.SQDEV_EDITOR_SYNTAXHIGHLIGHTING_COLOR_KEY, editor);
		}
		
		return this.keywordScanner;
	}
	
	@Override
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();
		
		// TODO: make procedural
		
		// syntax highlighting
		DefaultDamagerRepairer dr_Default = new DefaultDamagerRepairer(this.getKeywordScanner());
		reconciler.setDamager(dr_Default, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr_Default, IDocument.DEFAULT_CONTENT_TYPE);
		
		// colorize strings
		NonRuleBasedDamagerRepairer ndr_String = new NonRuleBasedDamagerRepairer(
				new TextAttribute(colorManager.getColor(ISQDevColorConstants.STRING)));
		reconciler.setDamager(ndr_String, BasicPartitionScanner.BASIC_STRING);
		reconciler.setRepairer(ndr_String, BasicPartitionScanner.BASIC_STRING);
		
		// colorize comments
		NonRuleBasedDamagerRepairer ndr_Comment = new NonRuleBasedDamagerRepairer(
				new TextAttribute(colorManager.getColor(ISQDevColorConstants.COMMENT)));
		reconciler.setDamager(ndr_Comment, BasicPartitionScanner.BASIC_COMMENT);
		reconciler.setRepairer(ndr_Comment, BasicPartitionScanner.BASIC_COMMENT);
		
		return reconciler;
	}
}
