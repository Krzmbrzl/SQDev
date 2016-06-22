package raven.sqdev.editors.stringTableEditor;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.source.ISourceViewer;

import raven.sqdev.constants.ISQDevColorConstants;
import raven.sqdev.editors.BasicCodeEditor;
import raven.sqdev.editors.BasicSourceViewerConfiguration;
import raven.sqdev.editors.ColorManager;
import raven.sqdev.editors.NonRuleBasedDamagerRepairer;

public class StringTableSourceViewerConfiguration extends BasicSourceViewerConfiguration {
	
	public StringTableSourceViewerConfiguration(ColorManager manager, BasicCodeEditor editor) {
		super(manager, editor);
	}
	
	@Override
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = (PresentationReconciler) super.getPresentationReconciler(
				sourceViewer);
		
		NonRuleBasedDamagerRepairer dr = new NonRuleBasedDamagerRepairer(
				new TextAttribute(colorManager.getColor(ISQDevColorConstants.TAG)));
		
		reconciler.setDamager(dr, StringTableXMLEditor.TAG);
		reconciler.setRepairer(dr, StringTableXMLEditor.TAG);
		
		return reconciler;
	}
	
}
