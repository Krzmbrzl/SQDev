package raven.sqdev.parser.sqf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import raven.sqdev.infoCollection.base.Keyword;
import raven.sqdev.infoCollection.base.KeywordList;
import raven.sqdev.infoCollection.base.SQFCommand;
import raven.sqdev.infoCollection.base.Variable;
import raven.sqdev.interfaces.ISQFParseInformation;
import raven.sqdev.misc.Macro;
import raven.sqdev.misc.SQDevInfobox;
import raven.sqdev.parser.misc.ParseUtil;
import raven.sqdev.pluginManagement.ResourceManager;

/**
 * A default implementation for a ISQFParseInformation
 * 
 * @author Raven
 *
 */
public class SQFParseInformation implements ISQFParseInformation {
	/**
	 * The SQF keywords to use
	 */
	private static KeywordList keywords;

	private static Map<String, SQFCommand> binaryOperator;
	private static List<String> binaryKeywords;
	private static Map<String, SQFCommand> unaryOperator;
	private static List<String> unaryKeywords;
	private static Map<String, SQFCommand> nularOperator;
	private static List<String> nularKeywords;
	private static Map<String, Variable> magicVars;
	private static List<String> magicVarNames;

	private Map<String, Macro> macros;
	private List<String> macroNames;

	/**
	 * Creates a new instance of this class
	 */
	public SQFParseInformation(Map<String, Macro> macros) {
		this(false, macros);
	}

	/**
	 * Creates a new instance of this class
	 * 
	 * @param refresh
	 *            Indicates whether the used list of keywords should be refreshed.
	 *            Otheriwse the one from previous instances of this class will be
	 *            used. If the keywords are refreshed every existing instance will
	 *            use the refreshed list
	 * @param macros
	 *            A map of defined macros the SQF parser should be aware of
	 */
	public SQFParseInformation(boolean refresh, Map<String, Macro> macros) {
		this.macros = macros;
		macroNames = new ArrayList<String>();

		loadMacronames();

		if (refresh || keywords == null) {
			binaryOperator = new HashMap<String, SQFCommand>();
			binaryKeywords = new ArrayList<String>();
			unaryOperator = new HashMap<String, SQFCommand>();
			unaryKeywords = new ArrayList<String>();
			nularOperator = new HashMap<String, SQFCommand>();
			nularKeywords = new ArrayList<String>();
			magicVarNames = new ArrayList<String>();

			String savedKeywords = getKeywordContent();

			if (savedKeywords == null) {
				SQDevInfobox info = new SQDevInfobox("Failed at instantiating SQF parseInformation properly!\n\nReason:"
						+ "\nProblems with reading respective resource");
				info.open();

				return;
			}

			keywords = new KeywordList(savedKeywords);

			if (keywords.getFailures().size() > 0) {
				SQDevInfobox info = new SQDevInfobox("Failed to load " + keywords.getFailures().size() + " commands",
						keywords.getFailures());

				info.open(false);
			}

			for (Keyword currentKeyword : keywords.getKeywords().values()) {
				SQFCommand command = (SQFCommand) currentKeyword;

				if (command.isBinaryOperator()) {
					binaryOperator.put(command.getKeyword().toLowerCase(), command);
				} else {
					if (command.isUnaryOperator()) {
						unaryOperator.put(command.getKeyword().toLowerCase(), command);
					} else {
						nularOperator.put(command.getKeyword().toLowerCase(), command);
					}
				}
			}

			magicVars = ParseUtil.getDefaultMagicVars();

			loadNames();
		}
	}

	/**
	 * Gets the saved keywords in their storage-form
	 */
	protected String getKeywordContent() {
		ResourceManager manager = ResourceManager.getManager();
		return manager.getResourceContent("SQFKeywords.txt");
	}

	/**
	 * Initializes all name-lists except the macro-namelist (see
	 * {@link #loadMacronames()})
	 */
	private void loadNames() {
		Iterator<Entry<String, SQFCommand>> it = binaryOperator.entrySet().iterator();

		while (it.hasNext()) {
			binaryKeywords.add(it.next().getValue().getKeyword());
		}

		it = unaryOperator.entrySet().iterator();

		while (it.hasNext()) {
			unaryKeywords.add(it.next().getValue().getKeyword());
		}

		it = nularOperator.entrySet().iterator();

		while (it.hasNext()) {
			nularKeywords.add(it.next().getValue().getKeyword());
		}

		Iterator<Entry<String, Variable>> varIt = magicVars.entrySet().iterator();

		while (varIt.hasNext()) {
			magicVarNames.add(varIt.next().getValue().getKeyword());
		}
	}

	/**
	 * Initializes the macro-namelist
	 */
	private void loadMacronames() {
		Iterator<Entry<String, Macro>> it = macros.entrySet().iterator();

		while (it.hasNext()) {
			macroNames.add(it.next().getValue().getKeyword());
		}
	}

	@Override
	public Map<String, SQFCommand> getBinaryOperators() {
		return binaryOperator;
	}

	@Override
	public Map<String, SQFCommand> getUnaryOperators() {
		return new HashMap<String, SQFCommand>(unaryOperator);
	}

	@Override
	public Map<String, SQFCommand> getNularOperators() {
		return nularOperator;
	}

	@Override
	public Map<String, Variable> getMagicVariables() {
		return magicVars;
	}

	@Override
	public Map<String, Macro> getMacros() {
		return macros;
	}

	@Override
	public List<String> getBinaryKeywords() {
		return new ArrayList<String>(binaryKeywords);
	}

	@Override
	public List<String> getUnaryKeywords() {
		return new ArrayList<String>(unaryKeywords);
	}

	@Override
	public List<String> getNularKeywords() {
		return new ArrayList<String>(nularKeywords);
	}

	@Override
	public List<String> getMagicVariableNames() {
		return new ArrayList<String>(macroNames);
	}

	@Override
	public List<String> getMacroNames() {
		return new ArrayList<String>(macroNames);
	}

}
