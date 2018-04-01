package raven.sqdev.parser.sqf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import raven.sqdev.infoCollection.base.Keyword;
import raven.sqdev.infoCollection.base.KeywordList;
import raven.sqdev.infoCollection.base.SQFCommand;
import raven.sqdev.infoCollection.base.Variable;
import raven.sqdev.interfaces.ISQFInformation;
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
public class SQFInformation implements ISQFInformation {
	/**
	 * The SQF keywords to use
	 */
	private static KeywordList keywords;

	private static Map<String, SQFCommand> binaryOperator;
	private static Map<String, SQFCommand> unaryOperator;
	private static Map<String, SQFCommand> nularOperator;
	private static Map<String, Variable> magicVars;

	private Map<String, Macro> macros;

	/**
	 * Creates a new instance of this class
	 */
	public SQFInformation(Map<String, Macro> macros) {
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
	public SQFInformation(boolean refresh, Map<String, Macro> macros) {
		this.macros = macros;

		if (refresh || keywords == null) {
			binaryOperator = new HashMap<String, SQFCommand>();
			unaryOperator = new HashMap<String, SQFCommand>();
			nularOperator = new HashMap<String, SQFCommand>();

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
		}
	}

	/**
	 * Gets the saved keywords in their storage-form
	 */
	protected String getKeywordContent() {
		ResourceManager manager = ResourceManager.getManager();
		return manager.getResourceContent("SQFKeywords.txt");
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
	public Collection<String> getBinaryKeywords() {
		return new ArrayList<String>(binaryOperator.keySet());
	}

	@Override
	public Collection<String> getUnaryKeywords() {
		return new ArrayList<String>(unaryOperator.keySet());
	}

	@Override
	public Collection<String> getNularKeywords() {
		return new ArrayList<String>(nularOperator.keySet());
	}

	@Override
	public Collection<String> getMagicVariableNames() {
		return new ArrayList<String>(magicVars.keySet());
	}

	@Override
	public Collection<String> getMacroNames() {
		return new ArrayList<String>(macros.keySet());
	}

}
