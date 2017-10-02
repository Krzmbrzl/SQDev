package raven.sqdev.parser.sqf;

import java.util.ArrayList;
import java.util.List;

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

	private static List<SQFCommand> binaryOperator;
	private static List<String> binaryKeywords;
	private static List<SQFCommand> unaryOperator;
	private static List<String> unaryKeywords;
	private static List<SQFCommand> nularOperator;
	private static List<String> nularKeywords;
	private static List<Variable> magicVars;
	private static List<String> magicVarNames;

	private List<Macro> macros;
	private List<String> macroNames;

	/**
	 * Creates a new instance of this class
	 */
	public SQFParseInformation(List<Macro> macros) {
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
	 *            A list of defined macros the SQF parser should be aware of
	 */
	public SQFParseInformation(boolean refresh, List<Macro> macros) {
		this.macros = macros;
		macroNames = new ArrayList<String>();

		loadMacronames();

		if (refresh || keywords == null) {
			binaryOperator = new ArrayList<SQFCommand>();
			binaryKeywords = new ArrayList<String>();
			unaryOperator = new ArrayList<SQFCommand>();
			unaryKeywords = new ArrayList<String>();
			nularOperator = new ArrayList<SQFCommand>();
			nularKeywords = new ArrayList<String>();
			magicVarNames = new ArrayList<String>();

			ResourceManager manager = ResourceManager.getManager();
			String savedKeywords = manager.getResourceContent("SQFKeywords.txt");

			if (savedKeywords == null) {
				SQDevInfobox info = new SQDevInfobox("Failed at instantiating SQF editor properly!\n\nReason:"
						+ "\nProblems with reading respective resource");
				info.open();

				return;
			}

			keywords = new KeywordList(savedKeywords);

			if (keywords.getFailures().size() > 0) {
				SQDevInfobox info = new SQDevInfobox(
						"Failed to load " + keywords.getFailures().size() + " commands",
						keywords.getFailures());

				info.open(false);
			}

			for (Keyword currentKeyword : keywords.getKeywords()) {
				SQFCommand command = (SQFCommand) currentKeyword;

				if (command.isBinaryOperator()) {
					binaryOperator.add(command);
				} else {
					if (command.isUnaryOperator()) {
						unaryOperator.add(command);
					} else {
						nularOperator.add(command);
					}
				}
			}

			magicVars = ParseUtil.getDefaultMagicVars();

			loadNames();
		}
	}

	/**
	 * Initializes all name-lists except the macro-namelist (see
	 * {@link #loadMacronames()})
	 */
	private void loadNames() {
		for (Keyword current : binaryOperator) {
			binaryKeywords.add(current.getKeyword());
		}
		for (Keyword current : unaryOperator) {
			unaryKeywords.add(current.getKeyword());
		}
		for (Keyword current : nularOperator) {
			nularKeywords.add(current.getKeyword());
		}
		for (Keyword current : magicVars) {
			magicVarNames.add(current.getKeyword());
		}
	}

	/**
	 * Initializes the macro-namelist
	 */
	private void loadMacronames() {
		for (Macro currentMacro : macros) {
			macroNames.add(currentMacro.getKeyword());
		}
	}

	@Override
	public List<SQFCommand> getBinaryOperators() {
		return new ArrayList<SQFCommand>(binaryOperator);
	}

	@Override
	public List<SQFCommand> getUnaryOperators() {
		return new ArrayList<SQFCommand>(unaryOperator);
	}

	@Override
	public List<SQFCommand> getNularOperators() {
		return new ArrayList<SQFCommand>(nularOperator);
	}

	@Override
	public List<Variable> getMagicVariables() {
		return new ArrayList<Variable>(magicVars);
	}

	@Override
	public List<Macro> getMacros() {
		return new ArrayList<Macro>(macros);
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
