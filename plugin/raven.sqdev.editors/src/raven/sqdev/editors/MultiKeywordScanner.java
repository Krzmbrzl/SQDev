package raven.sqdev.editors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;

import raven.sqdev.interfaces.IKeywordListChangeListener;
import raven.sqdev.interfaces.IUpdateListener;

/**
 * This is a keyword scanner that can handle multiple keyword categories that
 * are all colored differently.<br>
 * <br>
 * <b>Note:</b> A <code>MultiKeywordScanner</code> can not specify keywords by
 * itself. This work has to be done in a {@link KeywordScanner} that is added to
 * it
 * 
 * @author Raven
 *
 */
public class MultiKeywordScanner extends RuleBasedScanner
		implements IKeywordListChangeListener, IUpdateListener {
	
	/**
	 * A list of all <code>KeywordScanners</code> that are combined in this
	 * <code>MultiKeywordScanner</code>
	 */
	private List<KeywordScanner> scannerList;
	
	/**
	 * The editor this scanner works for
	 */
	protected BasicCodeEditor editor;
	
	/**
	 * Creates an instance of this scanner with no content
	 * 
	 * @param editor
	 *            The <code>BasicCodeEditor</code> this scanner works for
	 */
	public MultiKeywordScanner(BasicCodeEditor editor) {
		scannerList = new ArrayList<KeywordScanner>();
		
		this.editor = editor;
	}
	
	/**
	 * Creates an instance of this scanner with the given scanner
	 * 
	 * @param scanner
	 *            The scanner to register
	 * @param editor
	 *            The <code>BasicCodeEditor</code> this scanner works for
	 */
	public MultiKeywordScanner(KeywordScanner scanner, BasicCodeEditor editor) {
		this(editor);
		
		addScanner(scanner);
	}
	
	/**
	 * Creates an instance of this scanner with the given scanners
	 * 
	 * @param scanner
	 *            The list of scanners to register
	 * @param editor
	 *            The <code>BasicCodeEditor</code> this scanner works for
	 */
	public MultiKeywordScanner(Collection<KeywordScanner> scanners,
			BasicCodeEditor editor) {
		this(editor);
		
		addScanners(scanners);
	}
	
	/**
	 * Gets the list of all regiestered <code>KeywordScanners</code>
	 */
	protected List<KeywordScanner> getScanners() {
		return scannerList;
	}
	
	/**
	 * Adds the given scanner if it is not already registered
	 * 
	 * @param scanner
	 *            The <code>KeywordScanner</code> to add
	 * 
	 * @param update
	 *            Indicates whether the rules of this scanner should be updated
	 *            after this addition
	 */
	private void addScanner(KeywordScanner scanner, boolean update) {
		if (!scannerList.contains(scanner)) {
			scanner.addKeywordListChangeListener(this);
			scanner.addUpdateListener(this);
			
			scanner.isPartOfMultiScanner = true;
			
			scannerList.add(scanner);
			
			if (update) {
				updateRules();
			}
		}
	}
	
	/**
	 * Adds the given scanner if it is not already registered
	 * 
	 * @param scanner
	 *            The <code>KeywordScanner</code> to add
	 */
	public void addScanner(KeywordScanner scanner) {
		addScanner(scanner, true);
	}
	
	
	/**
	 * Adds all the given scanners
	 * 
	 * @param scanners
	 *            The list of <code>KeywordScanners</code> to add
	 */
	public void addScanners(Collection<KeywordScanner> scanners) {
		for (KeywordScanner scanner : scanners) {
			addScanner(scanner, false);
		}
		
		updateRules();
	}
	
	/**
	 * Removes the given scanner
	 * 
	 * @param scanner
	 *            The <code>KeywordScanner</code> to remove
	 * @param update
	 *            Indicates whether the rules of this scanner should be updated
	 *            after this deletion
	 */
	private void removeScanner(KeywordScanner scanner, boolean update) {
		scanner.removeKeywordListChangeListener(this);
		scanner.removeUpdateListener(this);
		
		scannerList.remove(scanner);
		
		if (update) {
			updateRules();
		}
	}
	
	/**
	 * Removes the given scanner if it is not already registered
	 * 
	 * @param scanner
	 *            The <code>KeywordScanner</code> to remove
	 */
	public void removeScanner(KeywordScanner scanner) {
		removeScanner(scanner, true);
	}
	
	/**
	 * Removes the given list of scanners
	 * 
	 * @param scanners
	 *            The <code>KeywordScanners</code> to remove
	 */
	public void removeScanners(Collection<KeywordScanner> scanners) {
		for (KeywordScanner scanner : scanners) {
			removeScanner(scanner, false);
		}
	}
	
	/**
	 * Checks whether the given <code>KeywordScanner</code> is contained in this
	 * <code>MultiKeywordScanner</code>
	 * 
	 * @param scanner
	 *            The <code>KeywordScanner</code> to search for
	 */
	public boolean contains(KeywordScanner scanner) {
		return scannerList.contains(scanner);
	}
	
	/**
	 * Updates the rules for this scanner according tho the registered
	 * <code>keywordScanner</code>
	 */
	protected void updateRules() {
		List<IRule> rules = new ArrayList<IRule>();
		
		for (KeywordScanner currentScanner : scannerList) {
			rules.add(currentScanner.getRule());
		}
		
		// add one final rule that will match every possible word as an
		// uncolored token in order to prevent partly highlighted words
		rules.add(new IRule() {
			
			@Override
			public IToken evaluate(ICharacterScanner scanner) {
				WordDetector detector = new WordDetector();
				
				char c = (char) scanner.read();
				
				if (!detector.isWordStart(c)) {
					scanner.unread();
					return Token.UNDEFINED;
				}
				
				// go on to the next char -> prevents endless loop in case c is
				// word start but not a word part
				c = (char) scanner.read();
				
				while (detector.isWordPart(c)) {
					c = (char) scanner.read();
				}
				scanner.unread();
				
				return new Token(null);
			}
		});
		
		setRules(rules.toArray(new IRule[rules.size()]));
		
		editor.update(false);
	}
	
	@Override
	public void keywordListChanged(String ctx) {
		updateRules();
	}
	
	@Override
	public void updated() {
		updateRules();
	}
}
