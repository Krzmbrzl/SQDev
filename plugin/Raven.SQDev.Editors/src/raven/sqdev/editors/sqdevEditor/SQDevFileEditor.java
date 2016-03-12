package raven.sqdev.editors.sqdevEditor;

import java.util.ArrayList;

import raven.sqdev.editors.BasicCodeEditor;
import raven.sqdev.editors.BasicPartitionScanner;
import raven.sqdev.sqdevFile.ESQDevFileAnnotation;
import raven.sqdev.sqdevFile.ESQDevFileAttribute;

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
		getBasicProvider().getPartitionScanner()
				.removeRule(BasicPartitionScanner.MULTILINE_COMMENT_RULE);
		
		getBasicConfiguration().getKeywordScanner().makeCaseSensitive(false);
	}
	
	/**
	 * Sets the keywords for this editor according to the values of
	 * <code>ESQDevFileAttributes</code> and <code>ESQDevFileAnnotation</code>
	 * 
	 * @see {@linkplain ESQDevFileAttribute}
	 * @see {@linkplain ESQDevFileAnnotation}
	 */
	protected void setKeywords() {
		ArrayList<String> keywords = new ArrayList<String>();
		
		// add all attributes
		for (ESQDevFileAttribute currentAttribute : ESQDevFileAttribute.values()) {
			keywords.add(currentAttribute.toString());
		}
		
		// add all annotations including the "@"
		for (ESQDevFileAnnotation currentAnnotation : ESQDevFileAnnotation.values()) {
			keywords.add("@" + currentAnnotation.toString());
		}
		
		// set the keywords
		getBasicConfiguration().getKeywordScanner()
				.setKeywords(keywords.toArray(new String[keywords.size()]));
	}
	
}
