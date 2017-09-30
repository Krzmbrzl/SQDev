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
	private static List<SQFCommand> unaryOperator;
	private static List<SQFCommand> nularOperator;
	private static List<Variable> magicVars;
	
	private List<Macro> macros;
	
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
	 *            Indicates whether the used list of keywords should be
	 *            refreshed. Otheriwse the one from previous instances of this
	 *            class will be used. If the keywords are refreshed every
	 *            existing instance will use the refreshed list
	 * @param macros
	 *            A list of defined macros the SQF parser should be aware of
	 */
	public SQFParseInformation(boolean refresh, List<Macro> macros) {
		this.macros = macros;
		
		if (refresh || keywords == null) {
			binaryOperator = new ArrayList<SQFCommand>();
			unaryOperator = new ArrayList<SQFCommand>();
			nularOperator = new ArrayList<SQFCommand>();
			
			ResourceManager manager = ResourceManager.getManager();
			String savedKeywords = manager
					.getResourceContent("SQFKeywords.txt");
			
			if (savedKeywords == null) {
				SQDevInfobox info = new SQDevInfobox(
						"Failed at instantiating SQF editor properly!\n\nReason:"
								+ "\nProblems with reading respective resource");
				info.open();
				
				return;
			}
			
			keywords = new KeywordList(savedKeywords);
			
			if (keywords.getFailures().size() > 0) {
				SQDevInfobox info = new SQDevInfobox("Failed to load "
						+ keywords.getFailures().size() + " commands",
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
			
			magicVars = SQFParseUtil.getDefaultMagicVars();
		}
	}
	
	@Override
	public List<SQFCommand> getBinaryOperators() {
		return binaryOperator;
	}
	
	@Override
	public List<SQFCommand> getUnaryOperators() {
		return unaryOperator;
	}
	
	@Override
	public List<SQFCommand> getNularOperators() {
		return nularOperator;
	}
	
	@Override
	public List<Variable> getMagicVariables() {
		return magicVars;
	}
	
	@Override
	public List<Macro> getMacros() {
		return macros;
	}
	
	/**
	 * Gets the names of the defined macros
	 */
	public List<String> getMacroNames() {
		List<String> macroNames = new ArrayList<String>();
		
		for (Macro currentMacro : macros) {
			macroNames.add(currentMacro.getKeyword());
		}
		
		return macroNames;
	}
	
	/**
	 * Gets the names of all binary operators
	 */
	public List<String> getBinaryOperatorNames() {
		List<String> names = new ArrayList<String>();
		
		for (SQFCommand currentOperator : binaryOperator) {
			names.add(currentOperator.getKeyword());
		}
		
		return names;
	}
	
	/**
	 * Gets the names of all unary operators
	 */
	public List<String> getUnaryOperatorNames() {
		List<String> names = new ArrayList<String>();
		
		for (SQFCommand currentOperator : unaryOperator) {
			names.add(currentOperator.getKeyword());
		}
		
		return names;
	}
	
	/**
	 * Gets the names of all nular operators
	 */
	public List<String> getNularOperatorNames() {
		List<String> names = new ArrayList<String>();
		
		for (SQFCommand currentOperator : nularOperator) {
			names.add(currentOperator.getKeyword());
		}
		
		return names;
	}
	
}
