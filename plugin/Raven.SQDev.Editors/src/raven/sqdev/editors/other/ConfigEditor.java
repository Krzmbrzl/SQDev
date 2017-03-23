package raven.sqdev.editors.other;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.Token;

import raven.sqdev.constants.SQDevPreferenceConstants;
import raven.sqdev.editors.BasicCodeEditor;
import raven.sqdev.editors.BasicPartitionScanner;
import raven.sqdev.editors.IMacroSupport;
import raven.sqdev.editors.KeywordScanner;
import raven.sqdev.editors.Macro;
import raven.sqdev.infoCollection.base.Keyword;
import raven.sqdev.infoCollection.base.KeywordList;

public class ConfigEditor extends BasicCodeEditor implements IMacroSupport {
	
	private List<Macro> macros;
	
	public ConfigEditor() {
		super();
		
		macros = new ArrayList<Macro>();
		
		// configure macro syntax highlighting
		configuration.createKeywordScanner(
				SQDevPreferenceConstants.SQDEV_EDITOR_MACROHIGHLIGHTING_COLOR_KEY,
				true);
		configuration.createKeywordScanner(
				SQDevPreferenceConstants.SQDEV_EDITOR_KEYWORDHIGHLIGHTING_COLOR_KEY,
				true);
		
		// set keywords for this scanner
		setKeywords(configuration.getKeywordScanner(
				SQDevPreferenceConstants.SQDEV_EDITOR_KEYWORDHIGHLIGHTING_COLOR_KEY));
		
		// get PartitionScanner
		BasicPartitionScanner partitionScanner = getBasicProvider()
				.getPartitionScanner();
		
		// exchange the string rule of the partitionScanner
		partitionScanner
				.removeRule(BasicPartitionScanner.DOUBLE_QUOTE_STRING_RULE);
		partitionScanner.addRule(new MultiLineRule("\"", "\"",
				new Token(BasicPartitionScanner.BASIC_STRING), (char) 0, true));
	}
	
	/**
	 * Sets the keywords available in this editor
	 * 
	 * @param scanner
	 *            The <code>KeywordScanner</code> the Keywords have to be added
	 *            to (e.g. in form of a <code>KeywordProvider</code>). By
	 *            default this scanner is <b>case-sensitive</b>
	 */
	protected void setKeywords(KeywordScanner scanner) {
		scanner.getKeywordProvider().addKeyword(new Keyword("class"));
	}
	
	@Override
	public boolean setMacros(List<Macro> macros, boolean update) {
		if (!this.macros.equals(macros)) {
			this.macros = new ArrayList<Macro>(macros);
			
			configuration
					.getKeywordScanner(
							SQDevPreferenceConstants.SQDEV_EDITOR_MACROHIGHLIGHTING_COLOR_KEY)
					.getKeywordProvider()
					.setKeywordList(new KeywordList(this.macros));
			
			if (update) {
				update(false);
			}
			
			return true;
		}
		
		
		return false;
	}
	
	@Override
	public List<Macro> getMacros() {
		return macros;
	}
	
}
