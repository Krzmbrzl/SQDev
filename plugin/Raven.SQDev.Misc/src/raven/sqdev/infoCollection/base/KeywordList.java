package raven.sqdev.infoCollection.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import raven.sqdev.exceptions.BadSyntaxException;
import raven.sqdev.interfaces.ISaveable;

/**
 * A list containing multiple <code>Keyword</code>s
 * 
 * @author Raven
 * 
 */
public class KeywordList implements ISaveable {

	/**
	 * The sequence indicating the start of the keywordList in the saveable String
	 * format of this class
	 */
	public static final String LIST_START_SAVESEQUENCE = "<KeywordList>";
	/**
	 * The sequence indicating the end of the keywordList in the saveable String
	 * format of this class
	 */
	public static final String LIST_END_SAVESEQUENCE = "</KeywordList>";
	/**
	 * The sequence seperating the single keywords in the saveable String format of
	 * this class
	 */
	public static final String LIST_SEPERATOR_SAVESEQUENCE = "</NextListItem>";

	/**
	 * The list of keywords where every starting letter has it's own list. Therefore
	 * <code>get('b'-('a'+1))</code> will get the list for the starting letter b
	 */
	private Map<String, Keyword> keywords;
	/**
	 * A list of keywords this list has failed to recreate
	 */
	private List<Throwable> failures;


	/**
	 * Creates an instance of this <code>KeywordList</code>
	 */
	public KeywordList() {
		keywords = new HashMap<String, Keyword>();

		failures = new ArrayList<Throwable>();
	}

	/**
	 * Recreates an instance of this <code>KeywordList</code> from the given
	 * saveFormat. If saveFormat is not considered a valid saveFormat the list will
	 * be initialized as if no parameter was given
	 * 
	 * @param saveFormat
	 *            The saveFormat which should be used to initialize this list from
	 */
	public KeywordList(String saveFormat) {
		this();

		if (isSaveFormat(saveFormat)) {
			recreateFrom(saveFormat);
		}
	}

	/**
	 * Creates a <code>KeywordList</code> out of the given list of keywords
	 * 
	 * @param keywords
	 *            The list of variables that should be transformed into a
	 *            <code>KeywordList</code>
	 */
	public KeywordList(List<? extends Keyword> keywords) {
		this();

		addKeywords(keywords);
	}
	
	/**
	 * Creates a <code>KeywordList</code> out of the given list of keywords
	 * 
	 * @param keywords
	 *            The list of variables that should be transformed into a
	 *            <code>KeywordList</code>
	 */
	public KeywordList(Map<String, Keyword> keywords) {
		this();
		
		this.keywords = keywords;
	}

	/**
	 * Adds a keyword to this list
	 * 
	 * @param keyword
	 *            The keyword to add
	 */
	public void addKeyword(Keyword keyword) {
		keywords.put(keyword.getKeyword().toLowerCase(), keyword);
	}

	/**
	 * Adds all given keywords to this list
	 * 
	 * @param keywords
	 *            The <code>Collection</code> of keywords to add
	 */
	public void addKeywords(Collection<? extends Keyword> keywords) {
		// add all keywords
		for (Keyword currentKeyword : keywords) {
			this.keywords.put(currentKeyword.getKeyword().toLowerCase(), currentKeyword);
		}
	}

	/**
	 * removes a keyword from this list
	 * 
	 * @param keyword
	 *            The keyword to remove
	 */
	public void removeKeyword(Keyword keyword) {
		keywords.remove(keyword.getKeyword().toLowerCase());
	}

	/**
	 * Checks if the given Keyword is contained in this list
	 * 
	 * @param keyword
	 *            The keyword to search for
	 */
	public boolean contains(Keyword keyword) {
		return keywords.get(keyword.getKeyword().toLowerCase()) != null;
	}

	/**
	 * Gets all of the keywords stored in this list as one single list
	 * 
	 * @return The list of keywords contained in this List in alphabetical order
	 */
	public Map<String, Keyword> getKeywords() {
		return keywords;
	}

	/**
	 * Gets the keyword with the respective String representation. The search will
	 * be done case insensitive.
	 * 
	 * @param keyword
	 *            The String representation of the desired keyword
	 * @return The desired <code>Keyword</code> or <code>null</code> if none could
	 *         be found
	 */
	public Keyword getKeyword(String keyword) {
		return keywords.get(keyword.toLowerCase());
	}

	/**
	 * Checks whether this list contains a keyword with the given String
	 * representation
	 * 
	 * @param keyword
	 *            The String representation of the desired keyword
	 */
	public boolean contains(String keyword) {
		return getKeyword(keyword) != null;
	}

	@Override
	public String getSaveableFormat() {
		String saveableFormat = LIST_START_SAVESEQUENCE + "\n\n";

		Iterator<Entry<String, Keyword>> it = keywords.entrySet().iterator();

		while (it.hasNext()) {
			Keyword currentKeyword = it.next().getValue();

			saveableFormat += "\n\t" + currentKeyword.getSaveableFormat().replace("\n", "\n\t");

			saveableFormat += "\n\n" + LIST_SEPERATOR_SAVESEQUENCE + "\n";
		}

		if (getKeywords().size() > 0) {
			// remove last seperator
			saveableFormat = saveableFormat.substring(0,
					saveableFormat.length() - (LIST_SEPERATOR_SAVESEQUENCE.length() + 1));
		}

		saveableFormat += "\n\n" + LIST_END_SAVESEQUENCE;

		return saveableFormat.replace("\n", "\r\n");
	}

	@Override
	public boolean recreateFrom(String savedFormat) {
		savedFormat = savedFormat.replace("\r\n", "\n");

		String listContent = savedFormat
				.substring(savedFormat.indexOf(LIST_START_SAVESEQUENCE) + LIST_START_SAVESEQUENCE.length(),
						savedFormat.indexOf(LIST_END_SAVESEQUENCE))
				.trim();

		for (String currentKeywordContent : listContent.split(LIST_SEPERATOR_SAVESEQUENCE)) {
			currentKeywordContent = currentKeywordContent.trim();

			Keyword currentKeyword;

			if (currentKeywordContent.contains(SQFCommand.SYNTAX_START_SAVESEQUENCE)) {
				// if the info corresponds to a SQF command
				currentKeyword = new SQFCommand();
			} else {
				if (currentKeywordContent.contains(SQFElement.WIKI_START_SAVESEQUENCE)) {
					// if the info corresponds to a SQFElement
					currentKeyword = new SQFElement();
				} else {
					// else it's just a normal Keyword
					currentKeyword = new Keyword();
				}
			}

			try {
				if (!currentKeyword.recreateFrom(currentKeywordContent)) {
					return false;
				} else {
					addKeyword(currentKeyword);
				}
			} catch (BadSyntaxException e) {
				failures.add(e);
			}
		}

		return true;
	}

	@Override
	public boolean isSaveFormat(String format) {
		if (!format.contains(LIST_START_SAVESEQUENCE) || !format.contains(LIST_END_SAVESEQUENCE)) {
			return false;
		}

		int startPos = format.indexOf(LIST_START_SAVESEQUENCE);
		int endPos = format.indexOf(LIST_END_SAVESEQUENCE);

		if (endPos < startPos) {
			return false;
		}

		return true;
	}

	/**
	 * Gets the list of exceptions thrown during recreation
	 */
	public List<Throwable> getFailures() {
		return failures;
	}

	@Override
	public String toString() {
		return keywords.keySet().toString();
	}
}
