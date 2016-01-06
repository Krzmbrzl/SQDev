package raven.sqdev.editors.sqfeditor;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

import raven.sqdev.editors.ColorManager;
import raven.sqdev.editors.ISQDevColorConstants;
import raven.sqdev.editors.KeywordScanner;
import raven.sqdev.editors.NonRuleBasedDamagerRepairer;

public final class SQFConfiguration extends SourceViewerConfiguration {
	
	private ColorManager colorManager;
	private KeywordScanner keywordScanner;
	
	public SQFConfiguration(ColorManager manager) {
		this.setColorManager(manager);
	}
	
	@Override
	public String getConfiguredDocumentPartitioning(ISourceViewer sourceViewer) {
		return SQFPartitionScanner.SQF_PARTITIONING;
	}
	
	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] { IDocument.DEFAULT_CONTENT_TYPE, SQFPartitionScanner.SQF_STRING,
				SQFPartitionScanner.SQF_COMMENT };
	}
	
	public ColorManager getColorManager() {
		return colorManager;
	}
	
	public void setColorManager(ColorManager colorManager) {
		this.colorManager = colorManager;
	}
	
	public KeywordScanner getKeywordScanner() {
		
		if (this.keywordScanner == null) {			
			this.keywordScanner = new KeywordScanner(new SQFKeywordProvider(),
					this.getColorManager().getColor(ISQDevColorConstants.KEYWORD));
		}
		
		return this.keywordScanner;
	}
	
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();
		
		DefaultDamagerRepairer dr_Default = new DefaultDamagerRepairer(this.getKeywordScanner());
		reconciler.setDamager(dr_Default, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr_Default, IDocument.DEFAULT_CONTENT_TYPE);
		
		NonRuleBasedDamagerRepairer ndr_String = new NonRuleBasedDamagerRepairer(
				new TextAttribute(colorManager.getColor(ISQDevColorConstants.STRING)));
		reconciler.setDamager(ndr_String, SQFPartitionScanner.SQF_STRING);
		reconciler.setRepairer(ndr_String, SQFPartitionScanner.SQF_STRING);
		
		NonRuleBasedDamagerRepairer ndr_Comment = new NonRuleBasedDamagerRepairer(
				new TextAttribute(colorManager.getColor(ISQDevColorConstants.COMMENT)));
		reconciler.setDamager(ndr_Comment, SQFPartitionScanner.SQF_COMMENT);
		reconciler.setRepairer(ndr_Comment, SQFPartitionScanner.SQF_COMMENT);
		
		return reconciler;
	}
	
}
