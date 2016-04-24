package raven.sqdev.editors;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposalExtension3;
import org.eclipse.jface.text.contentassist.ICompletionProposalExtension5;
import org.eclipse.jface.text.contentassist.ICompletionProposalExtension6;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import raven.sqdev.infoCollection.base.Keyword;
import raven.sqdev.miscellaneous.AdditionalKeywordProposalInformation;

public class BasicCompletionProposal implements ICompletionProposal, 
		ICompletionProposalExtension3, ICompletionProposalExtension5,
		ICompletionProposalExtension6 {
		
	/** The string to be displayed in the completion proposal popup. */
	private String displayString;
	/** The replacement string. */
	private String replacementString;
	/** The replacement offset. */
	private int replacementOffset;
	/** The replacement length. */
	private int replacementLength;
	/** The cursor position after this proposal has been applied. */
	private int cursorPosition;
	/** The image to be displayed in the completion proposal popup. */
	private Image image;
	/** The context information of this proposal. */
	private IContextInformation contextInformation;
	/** The additional info of this proposal. */
	private Object additionalProposalInfo;
	
	/**
	 * Creates a <code>CompletionProposal</code> based on the given
	 * <code>Keyword</code>
	 * 
	 * @param keyword
	 *            The respective <code>Keyword</code> this proposal should be
	 *            craeted for
	 * @param replacementOffset
	 *            The offset of the text to be replaced
	 * @param replacementLength
	 *            The length of the text to be replaced
	 */
	public BasicCompletionProposal(Keyword keyword, int replacementOffset, int replacementLength) {
		// create instance according to the keyword
		// TODO: add image
		this(keyword.getKeyword(), replacementOffset, replacementLength,
				keyword.getKeyword().length(), null, keyword.getKeyword(), null,
				new AdditionalKeywordProposalInformation(keyword));
	}
	
	/**
	 * Creates a new completion proposal based on the provided information. The
	 * replacement string is considered being the display string too. All
	 * remaining fields are set to <code>null</code>.
	 *
	 * @param replacementString
	 *            the actual string to be inserted into the document
	 * @param replacementOffset
	 *            the offset of the text to be replaced
	 * @param replacementLength
	 *            the length of the text to be replaced
	 * @param cursorPosition
	 *            the position of the cursor following the insert relative to
	 *            replacementOffset
	 */
	public BasicCompletionProposal(String replacementString, int replacementOffset,
			int replacementLength, int cursorPosition) {
		this(replacementString, replacementOffset, replacementLength, cursorPosition, null, null,
				null, null);
	}
	
	/**
	 * Creates a new completion proposal. All fields are initialized based on
	 * the provided information.
	 *
	 * @param replacementString
	 *            the actual string to be inserted into the document
	 * @param replacementOffset
	 *            the offset of the text to be replaced
	 * @param replacementLength
	 *            the length of the text to be replaced
	 * @param cursorPosition
	 *            the position of the cursor following the insert relative to
	 *            replacementOffset
	 * @param image
	 *            the image to display for this proposal
	 * @param displayString
	 *            the string to be displayed for the proposal
	 * @param contextInformation
	 *            the context information associated with this proposal
	 * @param additionalProposalInfo
	 *            the additional information associated with this proposal
	 */
	public BasicCompletionProposal(String replacementString, int replacementOffset,
			int replacementLength, int cursorPosition, Image image, String displayString,
			IContextInformation contextInformation, Object additionalProposalInfo) {
		Assert.isNotNull(replacementString);
		Assert.isTrue(replacementOffset >= 0);
		Assert.isTrue(replacementLength >= 0);
		Assert.isTrue(cursorPosition >= 0);
		
		this.replacementString = replacementString;
		this.replacementOffset = replacementOffset;
		this.replacementLength = replacementLength;
		this.cursorPosition = cursorPosition;
		this.image = image;
		this.displayString = displayString;
		this.contextInformation = contextInformation;
		this.additionalProposalInfo = additionalProposalInfo;
	}
	
	/*
	 * @see ICompletionProposal#apply(IDocument)
	 */
	public void apply(IDocument document) {
		try {
			document.replace(replacementOffset, replacementLength, replacementString);
		} catch (BadLocationException x) {
			// ignore
		}
	}
	
	/*
	 * @see ICompletionProposal#getSelection(IDocument)
	 */
	public Point getSelection(IDocument document) {
		return new Point(replacementOffset + cursorPosition, 0);
	}
	
	/*
	 * @see ICompletionProposal#getContextInformation()
	 */
	public IContextInformation getContextInformation() {
		return contextInformation;
	}
	
	/*
	 * @see ICompletionProposal#getImage()
	 */
	public Image getImage() {
		return image;
	}
	
	/*
	 * @see ICompletionProposal#getDisplayString()
	 */
	public String getDisplayString() {
		if (displayString != null)
			return displayString;
		return replacementString;
	}
	
	@Override
	public IInformationControlCreator getInformationControlCreator() {
		return new IInformationControlCreator() {
			public IInformationControl createInformationControl(Shell parent) {
				return new BasicInformationControl(parent);
			}
		};
	}
	
	@Override
	public CharSequence getPrefixCompletionText(IDocument document, int completionOffset) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getPrefixCompletionStart(IDocument document, int completionOffset) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String getAdditionalProposalInfo() {
		// does not get called
		return additionalProposalInfo.toString();
	}
	
	@Override
	public Object getAdditionalProposalInfo(IProgressMonitor monitor) {
		return additionalProposalInfo;
	}
	
	@Override
	public StyledString getStyledDisplayString() {
		StyledString str = new StyledString(displayString);
		
		str.setStyle(0, replacementLength, new StyledString.Styler() {
			
			@Override
			public void applyStyles(TextStyle textStyle) {
				textStyle.foreground = PlatformUI.getWorkbench().getDisplay()
						.getSystemColor(SWT.COLOR_BLUE);
			}
		});
		
		return str;
	}
}
