package OutputParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import SQF.Functions;

public class ParserRule {
	private String ruleName;
	private String ruleContent;
	private String terminalRules;
	private String terminalRulePrefix;
	private int counter;
	private ArrayList<String> reachableStartRules;
	private ArrayList<String> reachableRules;
	private boolean isSubRule;
	private String baseRuleName;
	private ParserRule baseRule;
	private ArrayList<ParserRule> subRules;
	private int subRuleCount;
	private int subLevel; // indicates which level of subRule this is
	private boolean isAssociated;
	private boolean isTerminal;
	private ArrayList<ParserRule> appendedRules;
	private boolean isAtomicRule;

	public ParserRule(String name, boolean terminal) {
		// Constructor for baseRules
		if (name.indexOf("Returner") >= 0) {
			name = name.substring(0, name.indexOf("Returner"));
		}

		setName(name);
		this.counter = 0;
		this.terminalRules = "";
		this.terminalRulePrefix = "";
		setReachableStartRules(new ArrayList<String>());
		defineAsSubRule(false);
		setBaseRuleName("");
		setSubRules(new ArrayList<ParserRule>());
		setSubLevel(0);
		setAsTerminal(terminal);
		setAppendedRules(new ArrayList<ParserRule>());
		setReachableRules(new ArrayList<String>());

		if (this.getName().contains("Atomic")) {
			this.setAsAtomicRule(true);
		} else {
			this.setAsAtomicRule(false);
		}

		this.create();
	}

	public ParserRule(String name) {
		this(name, false);
	}

	public ParserRule(String name, String baseRule, boolean terminal) {
		// constructor for subRules
		if (name.indexOf("Returner") >= 0) {
			name = name.substring(0, name.indexOf("Returner"));
		}

		setName(name);
		this.counter = 0;
		this.terminalRules = "";
		this.terminalRulePrefix = "";
		setReachableStartRules(new ArrayList<String>());
		this.defineAsSubRule(true);
		this.setBaseRuleName(baseRule);
		setSubRules(new ArrayList<ParserRule>());
		setSubLevel(0);
		setAsTerminal(terminal);
		setAppendedRules(new ArrayList<ParserRule>());
		setReachableRules(new ArrayList<String>());

		if (this.getName().contains("Atomic")) {
			this.setAsAtomicRule(true);
		} else {
			this.setAsAtomicRule(false);
		}

		this.create();
	}

	public ParserRule(String name, String baseRule) {
		this(name, baseRule, false);
	}

	public String getName() {
		return ruleName;
	}

	public void setName(String ruleName) {
		if (!ruleName.substring(0, 1).equals(
				ruleName.substring(0, 1).toUpperCase())) {
			// if rule doesn't start with an uppercase letter
			ruleName = ruleName.substring(0, 1).toUpperCase()
					+ ruleName.substring(1);
		}

		this.ruleName = ruleName;
	}

	public String getRuleContent() {
		return ruleContent;
	}

	public void setRuleContent(String ruleContent) {
		this.ruleContent = ruleContent;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int count) {
		this.counter = count;
	}

	public void setTerminalRules(String rules) {
		this.terminalRules = rules;
	}

	public String getTerminalRules() {
		return terminalRules;
	}

	public String getTerminalRulePrefix() {
		return terminalRulePrefix;
	}

	public void setTerminalRulePrefix(String terminalRulePrefix) {
		this.terminalRulePrefix = terminalRulePrefix;
	}

	/**
	 * <b>DEPRECATED</b> only maintained for backward compability
	 * 
	 * @return The content of the baseRule
	 */
	public String getBaseRuleContent() {
		return this.getRuleContent();

		/*
		 * Deprecated method content
		 * 
		 * String content = this.ruleContent;
		 * 
		 * // get basic rule content content =
		 * content.substring(content.indexOf(":") + 1); content =
		 * content.substring(0, content.indexOf(";"));
		 * 
		 * content = cleanString(content);
		 * 
		 * return content;
		 */
	}

	/**
	 * <b> DEPRECATED</b> only maintained for backward compability
	 * 
	 * @return The content of the atomicRule
	 */
	public String getAtomicRuleContent() {
		// if this rule is an atomic rule the wished content is the content of
		// this rule
		if (this.isAtomicRule()) {
			return this.getRuleContent();
		}

		if (this.hasAtomicRule()) {
			return this.getAtomicRule().getRuleContent();
		} else {
			// if rule doesn't have an atomicRule return an empty string
			return "";
		}

		/*
		 * Deprecated method content
		 * 
		 * String content = this.ruleContent;
		 * 
		 * if (content.contains("terminal")) { // remove terminal rules content
		 * = content.substring(0, content.indexOf("terminal")); content =
		 * cleanString(content); }
		 * 
		 * // get atomic rule content content =
		 * content.substring(content.lastIndexOf(":") + 1); content =
		 * content.substring(0, content.indexOf(";"));
		 * 
		 * content = cleanString(content);
		 * 
		 * return content;
		 */
	}

	/**
	 * <b>DEPRECATED</b><br>
	 * Sets the content of the baseRule (takes care about formatting -> only
	 * insert newlines)
	 * 
	 * @param content
	 *            The new content of the baseRule
	 */
	public void setBaseRuleContent(String content) {
		this.setRuleContent(content);

		/*
		 * Old method content
		 * 
		 * content = cleanString(content);
		 * 
		 * String fragment1 = ruleContent.substring(ruleContent.indexOf(":") +
		 * 1); while (fragment1.endsWith("\n") || fragment1.endsWith("\t") ||
		 * fragment1.endsWith(" ")) { // make sure cleanString does not cut at
		 * the end fragment1 = fragment1.substring(0, fragment1.length() - 1); }
		 * 
		 * int offset = fragment1.length() - cleanString(fragment1).length();
		 * 
		 * // final assignment of fragment1 fragment1 = ruleContent.substring(0,
		 * ruleContent.indexOf(":") + 1 + offset);
		 * 
		 * String fragment2 = ruleContent.substring(0,
		 * ruleContent.indexOf(";")); while (fragment2.startsWith("\n") ||
		 * fragment2.startsWith("\t") || fragment2.startsWith(" ")) { // make
		 * sure cleanString does not cut at the beginning fragment2 =
		 * fragment2.substring(1); }
		 * 
		 * int secondOffset = fragment2.length() -
		 * cleanString(fragment2).length();
		 * 
		 * fragment2 = ruleContent.substring(ruleContent.indexOf(";") -
		 * secondOffset);
		 * 
		 * // formatting if (content.indexOf("\n") >= 0) { content =
		 * content.replaceAll("\n", "\n\t"); } while (content.indexOf("\n\t\t")
		 * >= 0) { content = content.replaceAll("\n\t\t", "\n\t"); }
		 * 
		 * ruleContent = fragment1 + content + fragment2;
		 */
	}

	/**
	 * <b>DEPRECATED</b><br>
	 * Sets the content of the atomicRule (takes care about formatting -> only
	 * insert newlines)
	 * 
	 * @param content
	 *            The new content of the atomicRule
	 */
	public void setAtomicRuleContent(String content) {
		if (!this.hasAtomicRule() && !this.isAtomicRule()) {
			// don't create an atomic rule for an atomic rule
			this.createAtomicRule();
		}

		// if this rule is the atomicRule then apply the content to this rule
		// directly
		if (this.isAtomicRule()) {
			this.setRuleContent(content);
		} else {
			this.getAtomicRule().setRuleContent(content);
		}

		/*
		 * former method content
		 * 
		 * content = cleanString(content);
		 * 
		 * String fragment1 = ruleContent
		 * .substring(ruleContent.lastIndexOf(":") + 1); while
		 * (fragment1.endsWith("\n") || fragment1.endsWith("\t") ||
		 * fragment1.endsWith(" ")) { // make sure cleanString does not cut at
		 * the end fragment1 = fragment1.substring(0, fragment1.length() - 1); }
		 * 
		 * int offset = fragment1.length() - cleanString(fragment1).length();
		 * 
		 * // final assignmentof fragment1 fragment1 = ruleContent.substring(0,
		 * ruleContent.lastIndexOf(":") + 1 + offset);
		 * 
		 * if (fragment1.endsWith("\n\t\t")) { // get proper format fragment1 +=
		 * "\t"; }
		 * 
		 * String fragment2 = ruleContent.substring(0,
		 * ruleContent.lastIndexOf(";")); while (fragment2.startsWith("\n") ||
		 * fragment2.startsWith("\t") || fragment2.startsWith(" ")) { // make
		 * sure cleanString does not cut at the beginning fragment2 =
		 * fragment2.substring(1); }
		 * 
		 * int secondOffset = fragment2.length() -
		 * cleanString(fragment2).length(); // final assignment of fragment2
		 * fragment2 = ruleContent.substring(ruleContent.lastIndexOf(";") -
		 * secondOffset);
		 * 
		 * // formatting if (content.indexOf("\n") >= 0) { content =
		 * content.replaceAll("\n", "\n\t\t\t"); } while
		 * (content.indexOf("\n\t\t\t\t") >= 0) { content =
		 * content.replaceAll("\n\t\t\t\t", "\n\t\t\t"); }
		 * 
		 * ruleContent = fragment1 + content + fragment2;
		 */
	}

	public ArrayList<String> getReachableStartRules() {
		ArrayList<String> copy = new ArrayList<String>(reachableStartRules);
		return copy;
	}

	public void setReachableStartRules(ArrayList<String> ruleNames) {
		reachableStartRules = ruleNames;
	}

	public ArrayList<String> getReachableRules() {
		ArrayList<String> copy = new ArrayList<String>(reachableRules);
		return copy;
	}

	/**
	 * Sets the reachable rules
	 * 
	 * @param reachableRuleNames
	 * @param format
	 *            if the list should be formatted
	 */
	public void setReachableRules(ArrayList<String> reachableRuleNames,
			boolean format) {
		this.reachableRules = reachableRuleNames;

		if (format) {
			this.formatReachableRules();
		}
	}

	public void setReachableRules(ArrayList<String> reachableRuleNames) {
		this.setReachableRules(reachableRuleNames, true);
	}

	/**
	 * Finds out if this rule has an atomicRule
	 * 
	 * @return
	 */
	public boolean hasAtomicRule() {
		for (ParserRule currentSubRule : this.getSubRules()) {
			if (currentSubRule.getName().equals(this.getName() + "Atomic")) {
				return true;
			}
		}

		// check appended rules as well
		for (ParserRule currentAppendedRule : this.getAppendedRules()) {
			if (currentAppendedRule.getName().equals(this.getName() + "Atomic")) {
				return true;
			}
		}

		return false;
	}

	public ParserRule getAtomicRule() {
		if (!this.hasAtomicRule()) {
			System.err.println("\nRule '" + this.getName()
					+ "' doesn't have a atomicRule!\n");
			return null;
		}

		for (ParserRule currentSubRule : this.getSubRules()) {
			if (currentSubRule.getName().equals(this.getName() + "Atomic")) {
				return currentSubRule;
			}
		}

		// try to search in the appended rules
		for (ParserRule currentAppendedRule : this.getAppendedRules()) {
			if (currentAppendedRule.getName().equals(this.getName() + "Atomic")) {
				return currentAppendedRule;
			}
		}

		System.err.println("\nInternal Error in ParserRule.getAtomicRule()\n");
		return null;
	}

	public String getBaseRuleName() {
		return baseRuleName;
	}

	public void setBaseRuleName(String baseRuleName) {
		this.baseRuleName = baseRuleName;
	}

	public ParserRule getBaseRule() {
		return baseRule;
	}

	public void setBaseRule(ParserRule baseRule) {
		this.baseRule = baseRule;
	}

	public ArrayList<ParserRule> getSubRules() {
		return this.subRules;
	}

	public void addSubRule(ParserRule subRule, boolean preventDuplicates) {
		if (this.getSubRules().contains(subRule) && preventDuplicates) {
			// don't add subRule because it already is listed
			return;
		}

		this.subRules.add(subRule);
	}

	public void addSubRule(ParserRule subRule) {
		this.addSubRule(subRule, true);
	}

	public void setSubRules(ArrayList<ParserRule> rules) {
		this.subRules = rules;
		// update counter
		this.setSubRuleCount(rules.size());
	}

	/**
	 * @return Returns a list containing all the subRules of this rule
	 */
	public ArrayList<ParserRule> getAllSubRules() {
		ArrayList<ParserRule> subRules = new ArrayList<ParserRule>();

		if (!this.isAssociated()) {
			// if the rule is not yet associated the subRules aren't set
			System.err.println("Can't get subRules for rule '" + this.getName()
					+ "' because the rule has not yet been associated!");

			return subRules;
		}

		if (this.hasSubRules()) {
			for (ParserRule currentSubRule : this.getSubRules()) {
				// add respective subRule
				subRules.add(currentSubRule);

				// check currentSubRules for other subRules

				if (currentSubRule.hasSubRules()) {
					for (ParserRule currentNextLevelSubRule : currentSubRule
							.getAllSubRules()) {
						subRules.add(currentNextLevelSubRule);
					}
				}
			}
		}

		return subRules;
	}

	public boolean isAssociated() {
		return isAssociated;
	}

	public void setAsAssociated(boolean isAssociated) {
		this.isAssociated = isAssociated;
	}

	public boolean isSubRule() {
		return isSubRule;
	}

	public boolean hasSubRules() {
		if (this.getSubRules() == null || this.getSubRules().isEmpty()) {
			return false;
		}

		return true;
	}

	public boolean hasBaseRule() {
		if (this.getBaseRule() != null || !this.getBaseRuleName().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isTerminal() {
		return isTerminal;
	}

	public void setAsTerminal(boolean isTerminal) {
		if (isTerminal) {
			// reset certain values a terminal rule can't have
			this.setSubRules(new ArrayList<ParserRule>());
		}
		this.isTerminal = isTerminal;
	}

	public void defineAsSubRule(boolean isSubRule) {
		this.isSubRule = isSubRule;
	}

	public int getSubRuleCount() {
		return subRuleCount;
	}

	public void setSubRuleCount(int subRuleCount) {
		this.subRuleCount = subRuleCount;
	}

	public int getSubLevel() {
		return subLevel;
	}

	public void setSubLevel(int subLevel) {
		if (subLevel < 0) {
			System.err.println("subLevel can't be < 0! " + "(in rule '"
					+ this.getName() + "' -> ParserRule.setSubLevel()");
		} else {
			this.subLevel = subLevel;
		}
	}

	public void increaseSubLevel() {
		this.setSubLevel(this.getSubLevel() + 1);
	}

	public void decreaseSubLevel() {
		if (this.getSubLevel() == 0) {
			// prevent subLevel from beeing less zero
			System.err.println("subLevel can't be < 0! " + "(in rule '"
					+ this.getName() + "' -> ParserRule.decreaseSubLevel()");
		} else {
			this.setSubLevel(this.getSubLevel() - 1);
		}
	}

	public ArrayList<ParserRule> getAppendedRules() {
		return new ArrayList<ParserRule>(this.appendedRules);
	}

	public void setAppendedRules(ArrayList<ParserRule> appendedRules) {
		this.appendedRules = appendedRules;
	}

	public void appendRule(ParserRule rule) {
		ArrayList<ParserRule> newAppendedRules = new ArrayList<ParserRule>(
				this.getAppendedRules());
		newAppendedRules.add(rule);
		this.setAppendedRules(newAppendedRules);
	}

	public boolean isAtomicRule() {
		return isAtomicRule;
	}

	public boolean isBaseRule() {
		if (this.getBaseRuleName().isEmpty() && this.getBaseRule() == null) {
			return true;
		}
		return false;
	}

	public void setAsAtomicRule(boolean isAtomicRule) {
		this.isAtomicRule = isAtomicRule;
	}

	/**
	 * Increases the counter one step
	 */
	public void count() {
		this.setCounter(this.getCounter() + 1);
	}

	/*
	 * public String toString() { String content = ruleContent + "\n\n" +
	 * terminalRules + "\n\n\n"; return content; }
	 */

	public String toString() {
		String ruleContent;

		String formatter = "";

		for (int i = 0; i < this.getSubLevel(); i++) {
			formatter += "\t";
		}

		ruleContent = formatter;

		if (this.isTerminal()) {
			ruleContent += "terminal " + this.getName().toUpperCase() + ":\n";
		} else {
			ruleContent += this.getName() + ":\n";
		}

		ruleContent += this.getRuleContent();

		ruleContent += "\n" + formatter + ";";

		return ruleContent;
	}

	/**
	 * This creates the basic ruleStructure with an atomic-rule
	 */
	public void create_OLD() {
		ruleContent = this.ruleName + ":\n" + "\t" + this.ruleName
				+ "Atomic\n;" + "\n\t\t" + this.ruleName + "Atomic:\n\t\t;";
	}

	public void create() {
		if (this.getRuleContent() == null) {
			this.setRuleContent("\n");
		}
	}

	/**
	 * creates an empty atomic rule, sets this rule as the baseRule and appends
	 * it to this rule
	 */
	public void createAtomicRule() {
		if (this.hasAtomicRule()) {
			return;
		}

		ParserRule atomic = new ParserRule(this.getName() + "Atomic",
				this.getName());

		this.appendRule(atomic);

		// add atomic rule call to base rule
		if (!this.containsLine(this.getAtomicRule().getName())) {
			this.addLineToRuleContent(this.getAtomicRule().getName());
		}
	}

	/**
	 * This will add a syntax to the parserRule (takes care about
	 * left-recursion)
	 * 
	 * @param syntax
	 *            The syntax that should be added
	 */
	public void addSyntax(String syntax) {
		syntaxVariant synVar = new syntaxVariant();
		synVar.setSyntax(syntax);

		this.addSyntaxVariant(synVar);
	}

	/**
	 * This will add a syntaxVariant to the parserRule (takes care about
	 * left-recursion)
	 * 
	 * @param synVar
	 *            The symtaxVariant which should be added
	 */
	public void addSyntaxVariant(syntaxVariant synVar) {
		if (ruleContent == null) {
			// create basic structure
			this.create();
		}

		this.count(); // count the amount of subRules for the naming of the
						// commandRules

		String newFragment = synVar.getSyntax();

		if (!synVar.getCommands().isEmpty()) {
			// create the name of the rule for the commands
			String commandRuleName = this.getName() + "CommandNameKeywords_"
					+ this.getCounter();
			commandRuleName = commandRuleName.toUpperCase(); // Terminals must
																// be
																// written in
																// Uppercase
																// letters

			newFragment = newFragment.replace("commandNameKeyword",
					commandRuleName);

			if (this.getTerminalRulePrefix().isEmpty()) {
				this.setTerminalRulePrefix(commandRuleName);
			}

			ArrayList<String> keywordList = synVar.getCommands();
			this.addTerminalRule(commandRuleName, keywordList);
		} else {
			// decrease counter because no terminal rule was created
			this.setCounter(this.getCounter() - 1);
		}

		boolean isAtomic = isAtomic(newFragment);

		if (isAtomic && newFragment.startsWith("(")) {
			// check that there is no ruleCall in between the brackets which is
			// left-recursive

			// convert brackets
			newFragment = newFragment.replace("(", "[");
			newFragment = newFragment.replace(")", "]");

			String[] elements = Functions.getElements(newFragment);

			String toCheck = elements[0];

			// remove brackets and unwished characters
			toCheck = toCheck.replace("[", " ");
			toCheck = toCheck.replace("]", " ");
			toCheck = toCheck.replace("|", " ");
			toCheck = cleanString(toCheck);
			toCheck = Functions.reduceSpaceBetween(toCheck);

			String[] startElements = Functions.getElements(toCheck);

			for (int i = 0; i < startElements.length; i++) {
				String currentElement = startElements[i];
				isAtomic = isAtomic(currentElement);

				if (!isAtomic) {
					// process as a baseRule syntax
					startElements[i] = null;
					String newSyntax = currentElement
							+ " "
							+ Functions.ArrayToString(Functions
									.getArrayContentFrom(elements, 1));
					this.addSyntax(newSyntax);
					// TODO: remove respective elements from the rule and pass
					// them again in this function seperately
				}
			}

			startElements = Functions.removeNullElements(startElements);

			if (startElements.length != 0) {
				// only proceed if there is an atomic part left
				if (startElements.length > 1 || elements[1].equals("*")
						|| elements[1].equals("?")) {
					// if there are still multiple startRuleCalls or the rule
					// call is optional
					String starter = "";
					for (String current : startElements) {
						// write the ruleCalls together again
						starter += " | " + current;
					}

					// add respective brackets and reformat
					starter = "(" + starter.substring(3) + ")";
					elements[0] = starter;
					newFragment = Functions.ArrayToString(elements);
					newFragment = newFragment.replace("[", "(");
					newFragment = newFragment.replace("]", ")");
					newFragment = newFragment.replace(") *", ")*");
					newFragment = newFragment.replace(") ?", ")?");
				} else {
					// if there is just a single ruleCall left

					// reformat
					elements[0] = startElements[0];
					newFragment = Functions.ArrayToString(elements);
					newFragment = newFragment.replace("[", "(");
					newFragment = newFragment.replace("]", ")");
					newFragment = newFragment.replace(") *", ")*");
					newFragment = newFragment.replace(") ?", ")?");

				}
			} else {
				// the optional part which was non-atomic has been removed ->
				// rest is atomic
				if (elements[1].equals("?") || elements[1].equals("*")) {
					elements[1] = null;
					Functions.removeNullElements(elements);
				}
				newFragment = Functions.ArrayToString(Functions
						.getArrayContentFrom(elements, 1));
				newFragment = newFragment.replace("[", "(");
				newFragment = newFragment.replace("]", ")");
				newFragment = newFragment.replace(") *", ")*");
				newFragment = newFragment.replace(") ?", ")?");

				if (!isAtomic(newFragment)) {
					System.err.println(newFragment + " is still not atomic!");
				}
			}

			isAtomic = true; // continue with the processed newFragment
			// as an atomicRule
		}

		// if syntax is atomic write it in the atomic rule
		if (isAtomic) {
			if (!this.hasAtomicRule() && !this.isAtomicRule()
					&& !this.isTerminal()) {
				// create atomicRule
				this.createAtomicRule();
			}

			if (this.isAtomicRule() || this.isTerminal()) {
				// add syntax
				this.addLineToRuleContent(newFragment);
			} else {
				// write snytax in the respective atomicRule
				ParserRule atomicRule = this.getAtomicRule();

				atomicRule.addSyntax(newFragment);
			}

			/*
			 * String atomicMainFragment = this.getAtomicRuleContent();
			 * 
			 * if (atomicMainFragment.isEmpty()) { // if there are no rules in
			 * atomic yet atomicMainFragment += "\t\t\t" + newFragment; } else {
			 * // insert a "|" to seperate rules atomicMainFragment += "\n| " +
			 * newFragment; }
			 * 
			 * this.setAtomicRuleContent(atomicMainFragment);
			 */

		} else {
			System.out.println(newFragment);
			// if syntax is left-recursive it has to go in the rule header after
			// the atomic
			// String mainContent = this.getRuleContent();

			// replace startRuleCall with the atomicRuleCall
			newFragment = newFragment.substring(newFragment.indexOf(" ") + 1);

			// can be used zero or more times -> numberAtomic can be used
			// without it; add syntactic predicate to avoid ambiguity
			newFragment = "=>(" + newFragment + ")*";

			if (!this.hasAtomicRule()) {
				this.createAtomicRule();
			}

			newFragment = this.getAtomicRule().getName() + " " + newFragment;

			this.addLineToRuleContent(newFragment);

			/*
			 * deprecated if (mainContent.indexOf(")*") >= 0) { // if it already
			 * contains a non-atomic rule
			 * 
			 * String synPred = ""; // eventually store syntactic predicate
			 * 
			 * String fragment1 = mainContent.substring(0,
			 * mainContent.indexOf("(")); String fragment2 =
			 * mainContent.substring(mainContent .indexOf("("));
			 * 
			 * if (fragment1.endsWith("=>")) { // remove syntactic predicate
			 * fragment1 = fragment1.substring(0, fragment1.length() - 2);
			 * synPred = "=>"; }
			 * 
			 * StringBuilder builder = new StringBuilder(mainContent); builder =
			 * builder.reverse();
			 * 
			 * int pos1 = builder.indexOf("*"); int pos2 = builder.indexOf(")");
			 * 
			 * if (pos1 > pos2) { // if there is a bracket without star already
			 * StringBuilder helper = new StringBuilder(newFragment); helper =
			 * helper.reverse(); builder = new StringBuilder(")" + helper +
			 * " | " + builder.substring(pos2 + 1));
			 * 
			 * builder = builder.reverse();
			 * 
			 * mainContent = builder.toString(); } else { mainContent =
			 * fragment1 + "(" + synPred + fragment2 + " | " + newFragment +
			 * ")"; } } else { mainContent += " " + newFragment; }
			 * 
			 * this.setRuleContent(mainContent);
			 */
		}
	}

	/**
	 * Adds a line to the rule content
	 * 
	 * @param line
	 *            The line that should be added
	 */
	public void addLineToRuleContent(String line) {
		if (line.isEmpty()) {
			// don't add empty lines
			return;
		}

		String newRuleContent = this.getRuleContent();

		newRuleContent += "\n\t| " + line;

		newRuleContent = cleanString(newRuleContent);

		if (newRuleContent.startsWith("|")) {
			newRuleContent = newRuleContent.substring(1);
			newRuleContent = cleanString(newRuleContent);
		}

		this.setRuleContent(newRuleContent);
	}

	/**
	 * Finds out whether this syntax is atomic for the given rule or if it would
	 * be left recursive
	 * 
	 * @param syntax
	 *            The syntax to check
	 * @return
	 */
	public boolean isAtomic(String syntax) {
		if (!syntax.contains(" ")) {
			// add a blank so the algorithm below doesn't fail to find one
			syntax += " ";
		}
		String ruleName = this.getName().toLowerCase();

		String firstParam = syntax.substring(0, syntax.indexOf(" "))
				.toLowerCase();

		if (ruleName.equals(firstParam)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Creates a terminal rule containing all the given keywords. (AutoFormat to
	 * max. 8 keywords per line)
	 * 
	 * @param name
	 *            The name of the main rule this terminal rule shall refer to
	 * @param keywordList
	 *            List of keywords this rule shall contain
	 */
	public void addTerminalRule(String name, ArrayList<String> keywordList) {
		// create rulefor the terminals
		ParserRule newTerminalRule = new ParserRule(name, this.getName(), true);
		newTerminalRule.create(); // TODO: create rules with instantiation
		this.appendRule(newTerminalRule);

		String terminalRuleContent = "";

		int counter = 0; // counts keywords in a row

		for (String currentKeyword : keywordList) {
			terminalRuleContent += '"' + currentKeyword + '"' + " | ";

			if (counter == 5) {
				// start new Line for readability
				terminalRuleContent = terminalRuleContent.substring(0,
						terminalRuleContent.length() - 3); // remove " | "
				terminalRuleContent += "\n\t| "; // start newLine

				counter = -1; // reset counter -> gets counted to zero right
								// below
			}

			counter++;
		}

		// remove last " | "
		terminalRuleContent = terminalRuleContent.substring(0,
				terminalRuleContent.length() - 3);

		newTerminalRule.addSyntax(terminalRuleContent);

		/*
		 * String terminalRuleContent = this.getTerminalRules();
		 * 
		 * if (!terminalRuleContent.isEmpty()) { // seperate different rules
		 * terminalRuleContent += "\n\n"; }
		 * 
		 * terminalRuleContent += "terminal " + name + ":\n\t";
		 * 
		 * int counter = 0; // counts keywords in a row
		 * 
		 * for (String currentKeyword : keywordList) { terminalRuleContent +=
		 * '"' + currentKeyword + '"' + " | ";
		 * 
		 * if (counter == 5) { // start new Line for readability
		 * terminalRuleContent = terminalRuleContent.substring(0,
		 * terminalRuleContent.length() - 3); // remove " | "
		 * terminalRuleContent += "\n\t| "; // start newLine
		 * 
		 * counter = -1; // reset counter -> gets counted to zero right // below
		 * }
		 * 
		 * counter++; }
		 * 
		 * // remove last " | " while (terminalRuleContent.endsWith(" ") ||
		 * terminalRuleContent.endsWith("|") ||
		 * terminalRuleContent.endsWith("\t") ||
		 * terminalRuleContent.endsWith("\n")) {
		 * 
		 * terminalRuleContent = terminalRuleContent.substring(0,
		 * terminalRuleContent.length() - 1); }
		 * 
		 * // end Rule terminalRuleContent += "\n;";
		 * 
		 * this.setTerminalRules(terminalRuleContent);
		 */
	}

	/**
	 * Checks for templates for this rule in the given file. If there is a
	 * template integrate this into the rule and mark it as "Injected" <br>
	 * NOTE: Injected parts will not be checked for left-recursion!!!
	 * 
	 * @param path
	 *            The dataPath to the template file
	 */
	public void checkForModel(String path) {
		File modelFile = new File(path);

		String modelContent = "";

		try {
			BufferedReader modelReader = new BufferedReader(new FileReader(
					modelFile));

			String currentLine;

			while ((currentLine = modelReader.readLine()) != null) {
				modelContent += currentLine;
			}

			modelReader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String ruleName = this.getName() + ":";

		if (modelContent.contains(ruleName)) {
			int startPos = modelContent.indexOf(ruleName);
			int endPos = modelContent.substring(startPos).indexOf(";")
					+ modelContent.substring(0, startPos).length() + 1;

			modelContent = modelContent.substring(startPos, endPos);

			modelContent = modelContent.substring(
					modelContent.indexOf(":") + 1, modelContent.indexOf(";"));

			// just get the rule Content
			modelContent = cleanString(modelContent);

			boolean proceed = modelContent.indexOf("|") >= 0;

			if (proceed) {
				while (proceed) {
					String giveAway = modelContent.substring(0,
							modelContent.indexOf("|"));

					modelContent = modelContent.substring(modelContent
							.indexOf("|") + 1);

					// clean up strings
					modelContent = cleanString(modelContent);
					giveAway = cleanString(giveAway);

					proceed = modelContent.indexOf("|") >= 0;

					// add to the rule
					this.addSyntax(giveAway + " //<Injected>");

					if (!proceed) {
						// The rest of modelContent is formatted and has to be
						// added as well
						this.addSyntax(modelContent + " //<Injected>");
					}
				}
			} else {
				this.addSyntax(modelContent + " //<Injected>");
			}
		}
	}

	/**
	 * Adds a statement for every ruleCall in this rule so that Xtext can refer
	 * to it. <br>
	 * Assumes that there are no assignments yet
	 */
	public void createAssignments() {
		this.setBaseRuleContent(createAssignments(this.getBaseRuleContent()));
		// this.setAtomicRuleContent(createAssignments(this.getAtomicRuleContent()));
	}

	public static String createAssignments(String content) {
		content = createAssignments(content, new ArrayList<String>());
		return content;
	}

	public static String createAssignments(String content,
			ArrayList<String> usedNames) {
		// replace "(" and ")" with "[" and "]" because getElements does not
		// support multiDim with "(" and ")"
		if (content.indexOf("(") >= 0) {
			content = content.replace("(", "[");

			if (content.indexOf(")") >= 0) {
				content = content.replace(")", "]");
			}
		}

		// seperate this from the other elements
		content = content.replace("\n\t|", " \n\t|");
		content = content.replace("\n", " \n ");
		content = content.replace("\t", " \t ");

		String[] contentElements = Functions.getElements(content);

		for (int k = 0; k < contentElements.length; k++) {
			String currentElement = contentElements[k];

			String toCheck = cleanString(currentElement);

			if (toCheck.equals("*") || toCheck.equals("|")
					|| toCheck.startsWith("{") || toCheck.startsWith("\"")
					|| (toCheck.contains("=") && !toCheck.contains("=>"))
					|| toCheck.equals("?") || toCheck.startsWith("//")
					|| toCheck.equals("=>") || toCheck.isEmpty()) {
				// These elements don't need an assignmnet or already have one
				continue;
			}

			if (toCheck.startsWith("*")) {
				contentElements[k] = null;
				continue;
			}

			if (currentElement.startsWith("[[")
					|| currentElement.startsWith("[=>[")) {
				String giveAway = currentElement.substring(1,
						currentElement.length() - 1);
				giveAway = createAssignments(giveAway, usedNames);

				// processing finished -> can maintain round brackets
				currentElement = "(" + giveAway + ")";

				contentElements[k] = currentElement;

				continue;
			}

			if (currentElement.startsWith("[")
					|| currentElement.startsWith("=>[")) {
				String nextElement = "";
				if (contentElements.length > (k + 1)) {
					nextElement = contentElements[k + 1];
				}

				String connector = "=";
				if (nextElement.startsWith("*")) {
					connector = "+=";
				}

				int startIndex = currentElement.indexOf("[");
				String giveAway = currentElement.substring(startIndex + 1,
						currentElement.length() - 1);
				giveAway = createAssignments(giveAway, usedNames);

				String synPred = "";

				if (startIndex > 0) {
					// if it should start with a syntactic predicate
					synPred = "=>";
				}

				// finished processing -> can maintain round brackets
				currentElement = synPred + "("
						+ giveAway.replace("=", connector) + ")";
			} else {
				// name consists of the first 3 letters of the element and a
				// number
				String assignmentName;
				if (currentElement.toLowerCase().indexOf("commandnamekeyword") >= 0) {
					assignmentName = "com";
				} else {
					if (currentElement.length() >= 3) {
						assignmentName = currentElement.substring(0, 3)
								.toLowerCase();
					} else {
						// if element is too short the name only consists of the
						// first letter and a number
						assignmentName = currentElement.substring(0, 1)
								.toLowerCase();
					}
				}
				Integer counter = new Integer(1);
				assignmentName += counter.toString();

				if (currentElement.toLowerCase().indexOf("atomic") >= 0) {
					// There should only be one atomic call in a rule so no
					// number is necessary
					assignmentName = "atomic";
				}

				if (usedNames.contains(assignmentName)
						&& assignmentName.equals("atomic")) {
					// prevent cutting the word "atomic"
					assignmentName = "atomic1";
				}

				while (usedNames.contains(assignmentName)) {
					// if this name has been used already
					counter++;

					// remove number from name again
					int offset = 1;

					int checkCounter = counter - 1;

					while ((checkCounter / 10) >= 1) {
						checkCounter = checkCounter / 10;

						offset++;
					}

					assignmentName = assignmentName.substring(0,
							assignmentName.length() - offset);
					// apply new number to the name
					assignmentName += counter.toString();
				}

				usedNames.add(assignmentName);

				currentElement = assignmentName + "=" + currentElement;
			}

			contentElements[k] = currentElement;
		}

		contentElements = Functions.removeNullElements(contentElements);
		content = Functions.ArrayToString(contentElements);

		// reverse transformation from the beginning
		if (content.indexOf("[") >= 0) {
			content = content.replace("[", "(");
		}
		if (content.indexOf("]") >= 0) {
			content = content.replace("]", ")");
		}

		if (content.indexOf(") *") >= 0) {
			content = content.replace(") *", ")*");
		}
		if (content.indexOf(") ?") >= 0) {
			content = content.replace(") ?", ")?");
		}

		content = content.replace(" \n ", "\n");
		content = content.replace(" \t ", "\t");

		return content;
	}

	/**
	 * Removes any whitespace characters in the beginning and in the end of the
	 * string
	 * 
	 * @param string
	 *            The string to be cleaned
	 * @return
	 */
	public static String cleanString(String string) {
		while (string.startsWith(" ") || string.startsWith("\n")
				|| string.startsWith("\t")) {

			string = string.substring(1);
		}

		while (string.endsWith(" ") || string.endsWith("\n")
				|| string.endsWith("\t")) {

			string = string.substring(0, string.length() - 1);
		}

		return string;
	}

	/**
	 * @return True if the rule is empty
	 */
	public boolean isEmpty() {
		if (cleanString(this.getRuleContent()).isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if this rule can start with a rule call with the given name
	 * 
	 * @param name
	 *            The name of the startRuleCall
	 * @return
	 */
	public boolean containsStartRuleCallOf(String name) {
		if (this.getStartRuleCalls().contains(name)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if this rule can start with a rule call with the given name
	 * 
	 * @param name
	 *            name The name of the startRuleCall
	 * @param checkBaseRule
	 *            Whether or not the BaseRule should be searched for the
	 *            startRuleCall
	 * @return
	 */
	public boolean containsStartRuleCallOf(String name, boolean checkBaseRule) {
		if (this.getStartRuleCalls(checkBaseRule).contains(name)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if this rule can start with a rule call with the given name
	 * 
	 * @param name
	 *            name The name of the startRuleCall
	 * @param checkBaseRule
	 *            Whether or not the BaseRule should be searched for the
	 *            startRuleCall
	 * @param checkAtomicRule
	 *            Whether or not the AtomicRule should be searched for the
	 *            startRuleCall
	 * @return
	 */
	public boolean containsStartRuleCallOf(String name, boolean checkBaseRule,
			boolean checkAtomicRule) {
		if (this.getStartRuleCalls(checkBaseRule, checkAtomicRule).contains(
				name)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Adds the given ruleNames to the list of reachable rules, if they aren't
	 * registered yet
	 * 
	 * @param newRuleNames
	 *            The names which should be added
	 */
	public void addReachableRules(ArrayList<String> newRuleNames) {
		ArrayList<String> reachableRuleNames = this.getReachableStartRules();

		for (String name : newRuleNames) {
			if (!reachableRuleNames.contains(name)) {
				reachableRuleNames.add(name);
			}
		}

		this.setReachableStartRules(reachableRuleNames);
	}

	/**
	 * Returns the names of the starting rule calls of each parsing possibility
	 * 
	 * @param checkBaseRule
	 *            Should the BaseRule be checked?
	 * @param checkAtomicRule
	 *            Should the AtomicRule be checked?
	 * @param dontCheckForDuplicates
	 *            Prevents the function from filtering out duplicates of
	 *            startRuleCalls
	 * 
	 * @return The names of ruleCalls this rule can start with
	 */
	public ArrayList<String> getStartRuleCalls(boolean checkBaseRule,
			boolean checkAtomicRule, boolean dontCheckForDuplicates) {

		String currentContent;
		ArrayList<String> startRuleCalls = new ArrayList<String>();

		int loopStartIndex = 0;
		int loopEndIndex = 2;

		if (!checkBaseRule) {
			loopStartIndex = 1;
		}
		if (!checkAtomicRule) {
			loopEndIndex = 1;
		}

		for (int i = loopStartIndex; i < loopEndIndex; i++) {
			// select which content to process
			if (i == 0) {
				currentContent = this.getBaseRuleContent();
			} else {
				currentContent = this.getAtomicRuleContent();
			}

			// format
			currentContent = currentContent.replace("|", " | ");
			currentContent = Functions.reduceSpaceBetween(currentContent);

			while (!cleanString(currentContent).isEmpty()) {
				String firstElement = Grammar.getFirstElement(currentContent);
				firstElement = cleanString(firstElement);

				if (firstElement.startsWith("|")) {
					firstElement = firstElement.substring(1);
					firstElement = ParserRule.cleanString(firstElement);
				}

				if (!Functions.startsWithLetter(firstElement)) {
					if (!firstElement.startsWith("(")
							&& !firstElement.startsWith("\"")) {
						// if it's not a normal bracket

						switch (firstElement.charAt(0)) {
						case '{':
							firstElement = Grammar
									.getFirstElement(currentContent
											.substring(currentContent
													.indexOf("}") + 1));
							break;

						default:
							System.err.println("Unhandled special character '"
									+ firstElement.charAt(0)
									+ "' in ParserRule.getStartRulCalls");
							break;
						}
					}
				}

				// remove processed line
				int endIndex = currentContent.indexOf("\n");

				if (endIndex < 0) {
					endIndex = currentContent.length() - 1;
				}

				currentContent = currentContent.substring(endIndex + 1);
				currentContent = ParserRule.cleanString(currentContent);
				if (currentContent.startsWith("|")) {
					currentContent = currentContent.substring(1);
					currentContent = ParserRule.cleanString(currentContent);
				}

				if (firstElement.endsWith("|")) {
					firstElement = firstElement.substring(0,
							firstElement.length() - 1);
					firstElement = cleanString(firstElement);
				}

				if (!firstElement.toUpperCase().equals(firstElement)
						&& !firstElement.startsWith("\"")) {
					// if first element is not a call for a terminal rule

					if (firstElement.startsWith("(")) {
						String names = firstElement.substring(1,
								firstElement.indexOf(")"));
						names = names.replace("|", " ");
						names = Functions.reduceSpaceBetween(names);

						String[] nameElements = Functions.getElements(names);

						for (String currentName : nameElements) {
							if (!startRuleCalls.contains(currentName)
									|| dontCheckForDuplicates) {
								startRuleCalls.add(currentName);
							}
						}
					} else {
						if (!startRuleCalls.contains(firstElement)
								|| dontCheckForDuplicates) {
							startRuleCalls.add(firstElement);
						}
					}
				}
			}
		}

		return startRuleCalls;
	}

	/**
	 * Returns the names of the starting rule calls of each parsing possibility
	 * 
	 * @param ofBaseRule
	 *            Should the BaseRule be checked?
	 * @param ofAtomicRule
	 *            Should the AtomicRule be checked?
	 * @return The names of ruleCalls this rule can start with
	 */
	public ArrayList<String> getStartRuleCalls(boolean checkBaseRule,
			boolean checkAtomicRule) {
		return getStartRuleCalls(checkBaseRule, checkAtomicRule, false);
	}

	/**
	 * @return The names of ruleCalls this rule can start with
	 */
	public ArrayList<String> getStartRuleCalls() {
		return getStartRuleCalls(true, true);
	}

	/**
	 * Returns the names of the starting rule calls of each parsing possibility
	 * 
	 * @param ofBaseRule
	 *            Should the BaseRule be checked?
	 * @return The names of ruleCalls this rule can start with
	 */
	public ArrayList<String> getStartRuleCalls(boolean checkBaseRule) {
		return getStartRuleCalls(checkBaseRule, true);
	}

	/**
	 * Check if the given rule is left-recursive according to the registered
	 * reachableRules (has to bee ste before)
	 * 
	 * @return
	 */
	public boolean isLeftRecursive() {
		if (this.isTerminal) {
			// a terminal rule can't be left-recursive
			return false;
		}

		ArrayList<String> reachableRuleNames = this.getReachableStartRules();

		if (reachableRuleNames.isEmpty()) {
			System.err
					.println("Can't check for left recursion when this.reachableRules has not been set!");
			return false;
		}

		if (reachableRuleNames.contains(this.getName())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if this rule can reach a rule of the given name according to the
	 * registered reachableRules (has to be set before)
	 * 
	 * @param name
	 *            The name of the rule to search for
	 * @return
	 */
	public boolean canReach(String name) {
		if (this.getReachableRules().isEmpty()) {
			System.err.println(this.getName()
					+ ".reachableRules has notbeen set yet!");
			return false;
		}

		if (this.getReachableRules().contains(name)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check if this rule can start with a call for a rule with the given name
	 * 
	 * @param name
	 *            The name of the potential startRule
	 * @return
	 */
	public boolean canStartWith(String name) {
		if (this.getReachableStartRules().contains(name)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if this rule can start with a call to the given rule
	 * 
	 * @param rule
	 *            The potential startRule
	 * @return
	 */
	public boolean canStartWith(ParserRule rule) {
		return this.canReach(rule.getName());
	}

	/**
	 * Prevent that an AtomicRuleCall is listed in this.reachableRules if it's
	 * mainRule is listed, too.
	 */
	public void formatReachableStartRules() {
		ArrayList<String> newReachableRuleNames = new ArrayList<String>();
		ArrayList<String> reachableRuleNames = this.getReachableStartRules();

		for (String currentName : reachableRuleNames) {
			if (!currentName.contains("Atomic")) {
				newReachableRuleNames.add(currentName);
			} else {
				String mainRuleName = currentName.substring(0,
						currentName.indexOf("Atomic"));

				if (!reachableRuleNames.contains(mainRuleName)) {
					// add atomicRuleCalls only if there is not call for their
					// mainRule already
					newReachableRuleNames.add(currentName);
				}
			}
		}

		this.setReachableStartRules(newReachableRuleNames);
	}

	/**
	 * Checks how often this rule has a startRuleCall of the given name
	 * 
	 * @param name
	 *            The name of the startRuleCall
	 * @return
	 */
	public int howManyStartRuleCallsOf(String name) {
		int quantity = 0;

		for (String currentName : this.getStartRuleCalls(true, true, true)) {
			if (currentName.equals(name)) {
				quantity++;
			}
		}

		return quantity;
	}

	/**
	 * Checks how often this rule has a startRuleCall of the given rule
	 * 
	 * @param rule
	 *            The startRule
	 * @return
	 */
	public int howManyStartRuleCallsOf(ParserRule rule) {
		return this.howManyStartRuleCallsOf(rule.getName());
	}

	/**
	 * @param checkBaseRule
	 *            Whether or not rule calls in the baseRule should be included
	 * @param checkAtomicRule
	 *            Wheter or not rule calls in the atomicRule should be included
	 * @param dontCheckForDuplicates
	 *            Should the function add duplicates to the list?
	 * @return A list of the names of all reachable rules
	 */
	public ArrayList<String> getRuleCalls(boolean checkBaseRule,
			boolean checkAtomicRule, boolean dontCheckForDuplicates) {
		ArrayList<String> reachableRuleNames = new ArrayList<String>();

		int startLoopIndex = 0;
		int endLoopIndex = 2;

		if (!checkBaseRule) {
			startLoopIndex = 1;
		}
		if (!checkAtomicRule) {
			endLoopIndex = 1;
		}

		for (int i = startLoopIndex; i < endLoopIndex; i++) {
			String content;

			if (i == 0) {
				content = this.getBaseRuleContent();
			} else {
				content = this.getAtomicRuleContent();
			}

			content = cleanString(content);

			while (!content.isEmpty()) {
				int index = content.indexOf("\n");

				if (index < 0) {
					index = content.length();
				}

				// get current line
				String currentLine = content.substring(0, index);

				// remove current line from content
				content = content.substring(index);
				content = cleanString(content);

				if (content.startsWith("|")) {
					content = content.substring(1);
					content = cleanString(content);
				}

				// remove any special characters from current line
				currentLine = currentLine.replace("|", " ");
				currentLine = currentLine.replace("(", " ");
				currentLine = currentLine.replace(")", " ");
				currentLine = currentLine.replace("*", " ");
				currentLine = currentLine.replace("?", " ");
				currentLine = Functions.reduceSpaceBetween(currentLine);

				String[] aElements = Functions.getElements(currentLine);

				for (String currentElement : aElements) {
					// process each element

					if (currentElement.contains("=")) {
						// remove assignment
						currentElement = currentElement
								.substring(currentElement.indexOf("=") + 1);
					}

					if (isValidRuleName(currentElement)
							&& !isTerminalRuleName(currentElement)) {
						// if this is a normal rule call
						if (!reachableRuleNames.contains(currentElement)) {
							reachableRuleNames.add(currentElement);
						} else {
							if (dontCheckForDuplicates) {
								reachableRuleNames.add(currentElement);
							}
						}
					}
				}
			}
		}

		return reachableRuleNames;
	}

	/**
	 * @param checkBaseRule
	 *            Whether or not rule calls in the baseRule should be included
	 * @param checkAtomicRule
	 *            Wheter or not rule calls in the atomicRule should be included
	 * @return A list of the names of all reachable rules
	 */
	public ArrayList<String> getRuleCalls(boolean checkBaseRule,
			boolean checkAtomicRule) {
		return getRuleCalls(checkBaseRule, checkAtomicRule, false);
	}

	/**
	 * @param checkBaseRule
	 *            Whether or not rule calls in the baseRule should be included
	 * @return A list of the names of all reachable rules
	 */
	public ArrayList<String> getRuleCalls(boolean checkBaseRule) {
		return getRuleCalls(checkBaseRule, true, false);
	}

	/**
	 * @return A list of the names of all reachable rules
	 */
	public ArrayList<String> getRuleCalls() {
		return getRuleCalls(true, true, false);
	}

	/**
	 * Prevent atomicRule calls from beeing listed if their baseRule is
	 * registered as well
	 */
	public void formatReachableRules() {
		ArrayList<String> newReachableRuleNames = new ArrayList<String>();

		for (String currentName : this.getReachableRules()) {
			if (currentName.contains("Atomic")) {
				// check if base rule is also included
				String baseName = currentName.substring(0,
						currentName.indexOf("Atomic"));

				if (!this.getReachableRules().contains(baseName)) {
					// only add if baseRuleis not included
					newReachableRuleNames.add(currentName);
				}
			} else {
				newReachableRuleNames.add(currentName);
			}
		}

		this.setReachableRules(newReachableRuleNames, false); // prevent
																// recursion
																// loop
	}

	/**
	 * Indicate if the rule is recursive according to the registered
	 * reachableRules
	 * 
	 * @return
	 */
	public boolean isRecursive() {
		if (this.getReachableRules().contains(this.getName())) {
			return true;
		} else {
			// TODO: implement atomic recursion
			return false;
		}
	}

	/**
	 * Formats the content of the rule according to it's subLevel
	 */
	public void format() {
		if (this.isEmpty()) {
			return;
		}

		String content = this.getRuleContent();

		content = cleanString(content);

		while (content.contains("\t\t")) {
			// remove previous formatting
			content = content.replace("\t\t", "\t");
		}

		String formatter = "\t";

		for (int i = 0; i < this.getSubLevel(); i++) {
			formatter += "\t";
		}

		content = content.replace("\t", formatter);

		content = formatter + content;

		this.setRuleContent(content);
	}

	/**
	 * Checks if the rule contains a line with the given content
	 * 
	 * @param line
	 *            The line content
	 * @return
	 */
	public boolean containsLine(String line) {
		line = cleanString(line);

		for (String currentLine : this.getLines()) {
			if (line.equals(currentLine)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * @return An array containing the lines of the ruleContent
	 */
	public String[] getLines() {
		String content = this.getRuleContent();

		int quantity = Functions.howOften(content, "\n") + 1;

		String[] lines = new String[quantity];

		for (int i = 0; i < quantity; i++) {
			int index = content.indexOf("\n") + 1;

			if (index == 0) {
				index = content.length();
			}

			String currentLine = content.substring(0, index);

			currentLine = cleanString(currentLine);

			if (currentLine.startsWith("|")) {
				currentLine = currentLine.substring(1);
				currentLine = cleanString(currentLine);
			}

			content = content.substring(index);

			lines[i] = currentLine;
		}

		return lines;
	}
	
	/**
	 * Removes the ruleCall with the given name from the syntax of this rule<br>
	 * Needs multiple calls if there are multiple ruleCalls of this name in one line
	 * @param name The name of the ruleCall taht should be removed
	 */
	public void removeRuleCall(String name) {
		String[] lines = new String[this.getLines().length];
		int counter = 0;

		for (String currentLine : this.getLines()) {
			if (currentLine.contains(name)) {
				// format for Functions.getElements()
				currentLine = currentLine.replace("(", "[");
				currentLine = currentLine.replace(")", "]");

				String[] elements = Functions.getElements(currentLine);
				String[] cElements = elements;
				boolean killNextElement = false;
				
				int innerCounter = 0;
				
				for (String currentElement : cElements) {
					if (killNextElement) {
						// remove element because a previous match decided so
						elements[innerCounter] = "";
						killNextElement = false;
					}

					if (currentElement.contains(name)) {
						if (currentElement.startsWith("[")) {
							if (currentElement.contains("|")) {
								// if there are multiple alternatives
								// remove respective element

								String fragment1 = currentElement.substring(0,
										currentElement.indexOf(name));
								String fragment2 = currentElement
										.substring(currentElement.indexOf(name)
												+ name.length());

								// remove respective seperator
								if (cleanString(fragment1).endsWith("|")) {
									fragment1 = fragment1.substring(0,
											fragment1.lastIndexOf("|"));
								} else {
									if (cleanString(fragment2).startsWith("|")) {
										fragment2 = fragment2
												.substring(fragment2
														.indexOf("|") + 1);
									}
								}

								currentElement = fragment1 + fragment2;

								if (!currentElement.contains("|")
										&& !((elements.length - 1) >= innerCounter + 1)
										|| !(elements[innerCounter + 1].equals("*")
												|| elements[innerCounter + 1]
														.equals("?") || elements[innerCounter + 1]
												.equals("+"))) {
									// if there is only one choice left and it's
									// a normal rule call
									currentElement = cleanString(currentElement);
									//remove brackets
									currentElement = currentElement.substring(1, currentElement.length() - 1);
									
									currentElement = cleanString(currentElement);
								}

								elements[innerCounter] = currentElement;
							} else {
								if ((elements.length - 1) >= innerCounter + 1) {
									String nextElement = elements[innerCounter + 1];

									if (nextElement.equals("+")) {
										// it's an unmatching syntax
										currentLine = "";
										break;
									} else {
										// this element is somehow optional (?
										// or *)
										// delete this element and the
										// respective operator
										elements[innerCounter] = null;
										elements[innerCounter + 1] = null;
									}
								} else {
									// unnecessary bracket
									// unmatching syntax
									currentLine = "";
									break;
								}
							}
						} else {
							// remove currentLine from lines because it's an
							// unmatching syntax
							elements = new String[1];
							elements[0] = "";
							break;
						}
					}
					
					innerCounter++;
				}
				
				currentLine = Functions.ArrayToString(elements);
				
				//undo formatting
				currentLine = currentLine.replace("[", "(");
				currentLine = currentLine.replace("]", "]");
				
				lines[counter] = currentLine;
			} else {
				// keep line in place
				lines[counter] = currentLine;
			}

			counter++;
		}

		// remove old ruleContent
		this.setRuleContent("");

		for (String currentLine : lines) {
			// add the lines to the rule content
			this.addLineToRuleContent(currentLine);
		}
	}

	/**
	 * Check whether the given name is the name of a terminal rule
	 * 
	 * @param name
	 *            The name to be checked
	 * @return
	 */
	public static boolean isTerminalRuleName(String name) {
		if (name.startsWith("\"")) {
			// implicit terminal keyword definition
			return true;
		}

		if (!Functions.startsWithLetter(name)) {
			// check that given name starts with a letter
			System.err
					.println("Name mustn't start with another character than a letter!"
							+ "\n\t->" + name + "\n\t-> returned false");
			return false;
		}

		if (name.toUpperCase().equals(name)) {
			// if name is written in capital letters it's a terminal
			return true;
		}

		return false;
	}

	/**
	 * Checks whether the given name is a valid rule name
	 * 
	 * @param name
	 *            The name that should be checked
	 * @return
	 */
	public static boolean isValidRuleName(String name) {
		if (!Functions.startsWithLetter(name)) {
			// a rule name has to start with a letter
			return false;
		}

		if (name.contains("|") || name.contains("(") || name.contains(")")
				|| name.contains("*") || name.contains("?")
				|| name.contains("=")) {
			// a rule name may not contain one of those characters
			return false;
		}

		return true;
	}
}
