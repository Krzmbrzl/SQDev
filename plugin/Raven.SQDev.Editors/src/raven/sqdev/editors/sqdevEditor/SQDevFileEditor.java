package raven.sqdev.editors.sqdevEditor;

import java.io.InputStream;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorInput;

import raven.sqdev.constants.SQDevPreferenceConstants;
import raven.sqdev.editors.BasicCodeEditor;
import raven.sqdev.editors.BasicPartitionScanner;
import raven.sqdev.infoCollection.base.Keyword;
import raven.sqdev.infoCollection.base.KeywordList;
import raven.sqdev.sqdevFile.ESQDevFileAnnotation;
import raven.sqdev.sqdevFile.ESQDevFileAttribute;
import raven.sqdev.sqdevFile.ESQDevFileType;
import raven.sqdev.sqdevFile.ESQDevFileVersion;
import raven.sqdev.sqdevFile.ISQDevFileErrorListener;
import raven.sqdev.util.EFileType;

/**
 * An editor for the SQDev file type
 * 
 * @author Raven
 * 
 */
public class SQDevFileEditor extends BasicCodeEditor {

	public SQDevFileEditor() {
		super();

		setKeywords();

		// remove the multi-line comment
		getBasicProvider().getPartitionScanner().removeRule(BasicPartitionScanner.MULTILINE_COMMENT_RULE);

		// getBasicConfiguration()
		// .getKeywordScanner(
		// SQDevPreferenceConstants.SQDEV_EDITOR_KEYWORDHIGHLIGHTING_COLOR_KEY)
		// .makeCaseSensitive(false);
	}

	/**
	 * Sets the keywords for this editor according to the values of
	 * <code>ESQDevFileAttributes</code> and <code>ESQDevFileAnnotation</code>
	 * 
	 * @see {@linkplain ESQDevFileAttribute}
	 * @see {@linkplain ESQDevFileAnnotation}
	 */
	protected void setKeywords() {
		KeywordList keywordList = new KeywordList();

		// add all attributes
		for (ESQDevFileAttribute currentAttribute : ESQDevFileAttribute.values()) {
			keywordList.addKeyword(new Keyword(currentAttribute.toString(), currentAttribute.getDescription()));
		}


		// add all annotations including the "@"
		for (ESQDevFileAnnotation currentAnnotation : ESQDevFileAnnotation.values()) {
			keywordList.addKeyword(new Keyword("@" + currentAnnotation.toString(), currentAnnotation.getDescription()));
		}

		if (!getBasicConfiguration()
				.scannerExists(SQDevPreferenceConstants.SQDEV_EDITOR_KEYWORDHIGHLIGHTING_COLOR_KEY)) {
			// create keyword scanner
			getBasicConfiguration()
					.createKeywordScanner(SQDevPreferenceConstants.SQDEV_EDITOR_KEYWORDHIGHLIGHTING_COLOR_KEY, false);
		}

		// set the keywords
		getBasicConfiguration().getKeywordScanner(SQDevPreferenceConstants.SQDEV_EDITOR_KEYWORDHIGHLIGHTING_COLOR_KEY)
				.setKeywords(keywordList);
	}

	@Override
	protected void doPreprocessorParsing(InputStream input) {
		// Do nothing
	}

	/**
	 * A call to this method will validate the current content of this editor but
	 * without taking the suspend flag into account and without creating a
	 * ParseTree. In order to prevent a call to processParseTree this method will
	 * always return false.
	 */
	@Override
	public boolean parseInput(boolean suspend) {
		IEditorInput editorIn = getEditorInput();

		IDocument document = getBasicProvider().getDocument(editorIn);

		if (document == null) {
			return false;
		}

		String content = document.get();

		if (content.trim().isEmpty()) {
			return false;
		}

		String header = content.contains("\n") ? content.substring(0, content.indexOf("\n")) : content;

		ESQDevFileVersion version = ESQDevFileVersion.getVersion(header.trim());

		if (version == null) {
			// no validation for older file-versions
			return false;
		}

		ESQDevFileType type = ESQDevFileType.NULLTYPE;

		String name = editorIn.getName();
		if (name.toLowerCase().endsWith(EFileType.SQDEV.getExtension().toLowerCase())) {
			name = name.substring(0, name.lastIndexOf("."));

			if (name.equalsIgnoreCase(ESQDevFileType.LINK.toString())) {
				type = ESQDevFileType.LINK;
			} else {
				if (name.equalsIgnoreCase(ESQDevFileType.PROJECT.toString())) {
					type = ESQDevFileType.PROJECT;
				}
			}
		}

		version.validate(content, new ISQDevFileErrorListener() {

			@Override
			public boolean error(int start, int length, String errorMsg) {
				SQDevFileEditor.this.createMarker(IMarker.PROBLEM, start, length, errorMsg, IMarker.SEVERITY_ERROR);
				return false;
			}
		}, type);

		applyParseChanges();

		return false;
	}

}
