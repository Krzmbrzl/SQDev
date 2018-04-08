package raven.sqdev.editors;

import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextHoverExtension;
import org.eclipse.jface.text.ITextHoverExtension2;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.swt.widgets.Shell;

import raven.sqdev.miscellaneous.AdditionalKeywordProposalInformation;
import raven.sqdev.util.EditorUtil;

public class BasicTextHover implements ITextHover, ITextHoverExtension, ITextHoverExtension2 {

	/**
	 * The editor this assist works on
	 */
	private BasicCodeEditor editor;

	/**
	 * Creates an instance of this hover assist that will use the keywords of the
	 * given editor as a foundation
	 * 
	 * @param editor
	 *            The editor this assist works on
	 */
	public BasicTextHover(BasicCodeEditor editor) {
		this.editor = editor;
	}

	@Override
	public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion) {
		// won't get called
		return null;
	}

	@Override
	public IRegion getHoverRegion(ITextViewer textViewer, int offset) {
		return new Region(offset, 0);
	}

	@Override
	public IInformationControlCreator getHoverControlCreator() {
		return new IInformationControlCreator() {

			@Override
			public IInformationControl createInformationControl(Shell parent) {
				return new BasicInformationControl(parent, false);
			}
		};
	}

	@Override
	public Object getHoverInfo2(ITextViewer textViewer, IRegion hoverRegion) {
		// get the respective word
		String word = EditorUtil.getWordAroundOffset(textViewer.getDocument(), hoverRegion.getOffset());

		AdditionalKeywordProposalInformation info = null;

		if (!word.isEmpty()) {
			// check if there is a corresponding keyword
			KeywordScanner scanner = editor.getBasicConfiguration().getKeywordScannerContaining(word);

			if (scanner != null) {
				// create the info for this keyword
				info = new AdditionalKeywordProposalInformation(
						scanner.getKeywordProvider().getKeywordList().getKeyword(word));

				if (info.isEmpty()) {
					// There isn't actually information to display
					info = null;
				}
			}
		}

		return info;
	}

}
