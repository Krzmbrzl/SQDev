package raven.sqdev.editors;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

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
		String prefix = EditorUtil.getWordPartBeforeOffset(viewer.getDocument(), offset).toLowerCase();
		
		Map<String, Keyword> keywords = editor.getBasicConfiguration().getAllConfiguredKeywords();
		
		ArrayList<ICompletionProposal> proposals = new ArrayList<ICompletionProposal>();
		
		// create proposals
		Stream<Keyword> keywordStream = keywords.values().stream().filter(new Predicate<Keyword>() {

			@Override
			public boolean test(Keyword keyword) {
				return keyword.getKeyword().toLowerCase().startsWith(prefix);
			}
		});
		
		keywordStream = keywordStream.sorted();
		
		keywordStream.forEachOrdered(new Consumer<Keyword>() {

			@Override
			public void accept(Keyword keyword) {
				// add a proposal
				proposals.add(new BasicCompletionProposal(keyword, offset - prefix.length(),
						prefix.length()));
			}
		});
		
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
