package raven.sqdev.editors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

import raven.sqdev.infoCollection.base.Keyword;
import raven.sqdev.util.EditorUtil;

/**
 * A basic implementation of an <code>IContentAssistProcessor</code> using the
 * keywords provided by the {@linkplain BasicKeywordProvider} to generate
 * content assist for them
 * 
 * @author Raven
 * 
 */
public class BasicContentAssistProcessor implements IContentAssistProcessor {
	/**
	 * The editor this processor is working on
	 */
	protected BasicCodeEditor editor;
	
	public BasicContentAssistProcessor(BasicCodeEditor editor) {
		this.editor = editor;
	}
	
	@Override
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
		String prefix = EditorUtil.getWordPartBeforeOffset(viewer.getDocument(), offset);
		
		List<Keyword> keywords;
		// get the respective list of keywords
		if (prefix.isEmpty()) {
			keywords = editor.getBasicConfiguration().getAllConfiguredKeywords();
		} else {
			keywords = editor.getBasicConfiguration().getConfiguredKeywordsFor(prefix.charAt(0));
		}
		
		ArrayList<ICompletionProposal> proposals = new ArrayList<ICompletionProposal>();
		
		// create proposals
		for (Keyword currentKeyword : keywords) {
			if (currentKeyword.getKeyword().toLowerCase().startsWith(prefix.toLowerCase())) {
				// add a proposal
				proposals.add(new BasicCompletionProposal(currentKeyword, offset - prefix.length(),
						prefix.length()));
			}
		}
		
		if (proposals.size() > 0) {
			return proposals.toArray(new ICompletionProposal[proposals.size()]);
		} else {
			return new ICompletionProposal[] { new CompletionProposal("", offset, 0, 0, null,
					"No proposals available!", null, null) };
		}
	}
	
	@Override
	public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset) {
		return null;
	}
	
	@Override
	public char[] getCompletionProposalAutoActivationCharacters() {
		return null;
	}
	
	@Override
	public char[] getContextInformationAutoActivationCharacters() {
		return null;
	}
	
	@Override
	public String getErrorMessage() {
		return null;
	}
	
	@Override
	public IContextInformationValidator getContextInformationValidator() {
		return null;
	}
	
}
