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
	 * The sequence separating the single keywords in the saveable String format of
	 * this class
	 */
	public static final String LIST_SEPERATOR_SAVESEQUENCE = "</NextListItem>";
	/**
	 * The sequence indicating the start of the keyword-type specification in the
	 * saveable String format of this class
	 */
	public static final String LIST_KEYWORDTYPE_START_SAVESEQUENCE = "<KeywordType>";
	/**
	 * The sequence indicating the end of the keyword-type specification in the
	 * saveable String format of this class
	 */
	public static final String LIST_KEYWORDTYPE_END_SAVESEQUENCE = "</KeywordType>";

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
	 * A flag indicating whether this list contains mixed keyword types
	 */
	private boolean mixed;


	/**
	 * Creates an instance of this <code>KeywordList</code>
	 */
	public KeywordList() {
		keywords = new HashMap<String, Keyword>();

		failures = new ArrayList<Throwable>();

		mixed = false;
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
			if (!recreateFrom(saveFormat)) {
				System.err.println("Invalid format for keywordlist!");
				// TODO: log
			}
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
		if (!mixed && !keywords.isEmpty()) {
			mixed = !keywords.entrySet().iterator().next().getClass().equals(keyword.getClass());
		} else {
			if (keywords.isEmpty()) {
				mixed = false;
			}
		}

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
			addKeyword(currentKeyword);
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
		StringBuilder saveableFormat = new StringBuilder();
		saveableFormat.append(LIST_START_SAVESEQUENCE + "\n");

		if (!mixed && !keywords.isEmpty()) {
			String keywordType = keywords.entrySet().iterator().next().getClass().getSimpleName();

			if (!keywordType.isEmpty()) {
				saveableFormat.append(LIST_KEYWORDTYPE_START_SAVESEQUENCE + "\n\t" + keywordType + "\n"
						+ LIST_KEYWORDTYPE_END_SAVESEQUENCE);
			}
		}

		Iterator<Entry<String, Keyword>> it = keywords.entrySet().iterator();

		while (it.hasNext()) {
			Keyword currentKeyword = it.next().getValue();

			saveableFormat.append("\n\t" + currentKeyword.getSaveableFormat().replace("\n", "\n\t"));

			saveableFormat.append("\n\n" + LIST_SEPERATOR_SAVESEQUENCE + "\n");
		}

		if (getKeywords().size() > 0) {
			// remove last seperator
			saveableFormat.setLength(saveableFormat.length() - (LIST_SEPERATOR_SAVESEQUENCE.length() + 1));
		}

		saveableFormat.append("\n\n" + LIST_END_SAVESEQUENCE);

		return saveableFormat.toString().replace("\n", "\r\n");
	}

	@Override
	public boolean recreateFrom(String savedFormat) {
		savedFormat = savedFormat.replace("\r\n", "\n");
		String keywordType = null;

		String listContent = savedFormat
				.substring(savedFormat.indexOf(LIST_START_SAVESEQUENCE) + LIST_START_SAVESEQUENCE.length(),
						savedFormat.indexOf(LIST_END_SAVESEQUENCE))
				.trim();

		// check for keyword-type specification
		if (listContent.contains(LIST_KEYWORDTYPE_START_SAVESEQUENCE)) {
			keywordType = listContent.substring(
					listContent.indexOf(LIST_KEYWORDTYPE_START_SAVESEQUENCE)
							+ LIST_KEYWORDTYPE_START_SAVESEQUENCE.length(),
					listContent.indexOf(LIST_KEYWORDTYPE_END_SAVESEQUENCE)).trim();

			if (keywordType.isEmpty()) {
				keywordType = null;
			}

			// trim list content
			listContent = listContent.substring(
					listContent.indexOf(LIST_KEYWORDTYPE_END_SAVESEQUENCE) + LIST_KEYWORDTYPE_END_SAVESEQUENCE.length())
					.trim();
		}

		for (String currentKeywordContent : listContent.split(LIST_SEPERATOR_SAVESEQUENCE)) {
			currentKeywordContent = currentKeywordContent.trim();

			Keyword currentKeyword = null;

			if (keywordType != null) {
				// use specified type
				boolean found = false;

				if (keywordType.equals(Keyword.class.getSimpleName())) {
					currentKeyword = new Keyword();
					found = true;
				}

				if (!found && keywordType.equals(SQFElement.class.getSimpleName())) {
					currentKeyword = new SQFElement();
					found = true;
				}

				if (!found && keywordType.equals(SQFCommand.class.getSimpleName())) {
					currentKeyword = new SQFCommand();
					found = true;
				}

				if (!found && keywordType.equals(O2ScriptCommand.class.getSimpleName())) {
					currentKeyword = new O2ScriptCommand();
					found = true;
				}

				if (!found) {
					System.err.println("Unknown keyword-type: \"" + keywordType + "\"!");
					// TODO: log
					return false;
				}
			} else {
				// Try to guess based on the given content as a fallback
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

		// check for keyword-type specification
		if (format.contains(LIST_KEYWORDTYPE_START_SAVESEQUENCE)) {
			startPos = format.indexOf(LIST_KEYWORDTYPE_START_SAVESEQUENCE);
			endPos = format.indexOf(LIST_KEYWORDTYPE_END_SAVESEQUENCE);

			if (endPos < startPos) {
				return false;
			}
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
