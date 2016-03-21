package raven.sqdev.editors;

import java.util.ArrayList;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

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
		String prefix = EditorUtil.getWordBeforeOffset(viewer.getDocument(), offset);
		
		String[] keywords;
		// get the respective list of keywords
		if (prefix.isEmpty()) {
			keywords = editor.getBasicConfiguration().getKeywordScanner().getKeywordProvider()
					.getKeywords();
		} else {
			String[][] allKeywords = editor.getBasicConfiguration().getKeywordScanner()
					.getKeywordProvider().getSortedKeywords();
					
			keywords = allKeywords[prefix.toLowerCase().charAt(0) - 'a'];
		}
		
		ArrayList<ICompletionProposal> proposals = new ArrayList<ICompletionProposal>();
		
		// create proposals
		for (String currentKeyword : keywords) {
			if (currentKeyword.toLowerCase().startsWith(prefix.toLowerCase())) {
				// TODO: provide descriptions
				proposals.add(new CompletionProposal(currentKeyword, offset - prefix.length(),
						prefix.length(), currentKeyword.length()));
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
