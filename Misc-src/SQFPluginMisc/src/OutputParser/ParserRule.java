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
	private ArrayList<String> reachableRules;

	public ParserRule(String name) {
		if (name.indexOf("Returner") >= 0) {
			name = name.substring(0, name.indexOf("Returner"));
		}

		setName(name);
		this.counter = 0;
		this.terminalRules = "";
		this.terminalRulePrefix = "";
		setReachableRules(new ArrayList<String>());
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
	 * @return The content of the baseRule
	 */
	public String getBaseRuleContent() {
		String content = this.ruleContent;

		// get basic rule content
		content = content.substring(content.indexOf(":") + 1);
		content = content.substring(0, content.indexOf(";"));

		content = cleanString(content);

		return content;
	}

	/**
	 * @return The content of the atomicRule
	 */
	public String getAtomicRuleContent() {
		String content = this.ruleContent;

		if (content.contains("terminal")) {
			// remove terminal rules
			content = content.substring(0, content.indexOf("terminal"));
			content = cleanString(content);
		}

		// get atomic rule content
		content = content.substring(content.lastIndexOf(":") + 1);
		content = content.substring(0, content.indexOf(";"));

		content = cleanString(content);

		return content;
	}

	/**
	 * Sets the content of the baseRule (takes care about formatting -> only
	 * insert newlines)
	 * 
	 * @param content
	 *            The new content of the baseRule
	 */
	public void setBaseRuleContent(String content) {
		content = cleanString(content);

		String fragment1 = ruleContent.substring(ruleContent.indexOf(":") + 1);
		while (fragment1.endsWith("\n") || fragment1.endsWith("\t")
				|| fragment1.endsWith(" ")) {
			// make sure cleanString does not cut at the end
			fragment1 = fragment1.substring(0, fragment1.length() - 1);
		}

		int offset = fragment1.length() - cleanString(fragment1).length();

		// final assignment of fragment1
		fragment1 = ruleContent.substring(0, ruleContent.indexOf(":") + 1
				+ offset);

		String fragment2 = ruleContent.substring(0, ruleContent.indexOf(";"));
		while (fragment2.startsWith("\n") || fragment2.startsWith("\t")
				|| fragment2.startsWith(" ")) {
			// make sure cleanString does not cut at the beginning
			fragment2 = fragment2.substring(1);
		}

		int secondOffset = fragment2.length() - cleanString(fragment2).length();

		fragment2 = ruleContent.substring(ruleContent.indexOf(";")
				- secondOffset);

		// formatting
		if (content.indexOf("\n") >= 0) {
			content = content.replaceAll("\n", "\n\t");
		}
		while (content.indexOf("\n\t\t") >= 0) {
			content = content.replaceAll("\n\t\t", "\n\t");
		}

		ruleContent = fragment1 + content + fragment2;
	}

	/**
	 * Sets the content of the atomicRule (takes care about formatting -> only
	 * insert newlines)
	 * 
	 * @param content
	 *            The new content of the atomicRule
	 */
	public void setAtomicRuleContent(String content) {
		content = cleanString(content);
		
		if(content.startsWith("NUMBERS")) {
			String dummy = "";
		}

		String fragment1 = ruleContent
				.substring(ruleContent.lastIndexOf(":") + 1);
		while (fragment1.endsWith("\n") || fragment1.endsWith("\t")
				|| fragment1.endsWith(" ")) {
			// make sure cleanString does not cut at the end
			fragment1 = fragment1.substring(0, fragment1.length() - 1);
		}

		int offset = fragment1.length() - cleanString(fragment1).length();

		// final assignmentof fragment1
		fragment1 = ruleContent.substring(0, ruleContent.lastIndexOf(":") + 1
				+ offset);
		
		if(fragment1.endsWith("\n\t\t")) {
			//get proper format
			fragment1 += "\t";
		}

		String fragment2 = ruleContent.substring(0,
				ruleContent.lastIndexOf(";"));
		while (fragment2.startsWith("\n") || fragment2.startsWith("\t")
				|| fragment2.startsWith(" ")) {
			// make sure cleanString does not cut at the beginning
			fragment2 = fragment2.substring(1);
		}

		int secondOffset = fragment2.length() - cleanString(fragment2).length();
		// final assignment of fragment2
		fragment2 = ruleContent.substring(ruleContent.lastIndexOf(";")
				- secondOffset);

		// formatting
		if (content.indexOf("\n") >= 0) {
			content = content.replaceAll("\n", "\n\t\t\t");
		}
		while (content.indexOf("\n\t\t\t\t") >= 0) {
			content = content.replaceAll("\n\t\t\t\t", "\n\t\t\t");
		}

		ruleContent = fragment1 + content + fragment2;
	}

	public ArrayList<String> getReachableRules() {
		ArrayList<String> copy = new ArrayList<String>(reachableRules);
		return copy;
	}

	public void setReachableRules(ArrayList<String> ruleNames) {
		reachableRules = ruleNames;
	}

	/**
	 * Increases the counter one step
	 */
	public void count() {
		this.setCounter(this.getCounter() + 1);
	}

	public String toString() {
		String content = ruleContent + "\n\n" + terminalRules + "\n\n\n";
		return content;
	}

	/**
	 * This creates the basic ruleStructure with an atomic-rule
	 */
	public void create() {
		ruleContent = this.ruleName + ":\n" + "\t" + this.ruleName
				+ "Atomic\n;" + "\n\t\t" + this.ruleName + "Atomic:\n\t\t;";
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
					String starter = "";
					for (String current : startElements) {
						// write the ruleCalls together again
						starter += " | " + current;
					}

					starter = "(" + starter.substring(3) + ")";
					elements[0] = starter;
					newFragment = Functions.ArrayToString(elements);
					newFragment = newFragment.replace("[", "(");
					newFragment = newFragment.replace("]", ")");
					newFragment = newFragment.replace(") *", ")*");
					newFragment = newFragment.replace(") ?", ")?");
				} else {
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
			String atomicMainFragment = this.getAtomicRuleContent();

			if (atomicMainFragment.isEmpty()) {
				// if there are no rules in atomic yet
				atomicMainFragment += "\t\t\t" + newFragment;
			} else {
				// insert a "|" to seperate rules
				atomicMainFragment += "\n| " + newFragment;
			}

			this.setAtomicRuleContent(atomicMainFragment);

		} else {
			System.out.println(newFragment);
			// if syntax is left-recursive it has to go in the rule header after
			// the atomic
			String mainContent = this.getBaseRuleContent();

			newFragment = newFragment.substring(newFragment.indexOf(" ") + 1);

			// can be used zero or more times -> numberAtomic can be used
			// without it; add syntactic predicate o avoid ambiguity
			newFragment = "=>(" + newFragment + ")*";

			if (mainContent.indexOf(")*") >= 0) {
				// if it already contains a non-atomic rule
				
				String synPred = ""; //eventually store syntactic predicate
				
				String fragment1 = mainContent.substring(0,
						mainContent.indexOf("("));
				String fragment2 = mainContent.substring(mainContent
						.indexOf("("));
				
				if(fragment1.endsWith("=>")) {
					//remove syntactic predicate
					fragment1 = fragment1.substring(0, fragment1.length() - 2);
					synPred = "=>";
				}

				StringBuilder builder = new StringBuilder(mainContent);
				builder = builder.reverse();

				int pos1 = builder.indexOf("*");
				int pos2 = builder.indexOf(")");

				if (pos1 > pos2) {
					// if there is a bracket without star already
					StringBuilder helper = new StringBuilder(newFragment);
					helper = helper.reverse();
					builder = new StringBuilder(")" + helper + " | "
							+ builder.substring(pos2 + 1));

					builder = builder.reverse();

					mainContent = builder.toString();
				} else {
					mainContent = fragment1 + "(" + synPred + fragment2 + " | "
							+ newFragment + ")";
				}
			} else {
				mainContent += " " + newFragment;
			}

			this.setBaseRuleContent(mainContent);
		}
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
		String terminalRuleContent = this.getTerminalRules();

		if (!terminalRuleContent.isEmpty()) {
			// seperate different rules
			terminalRuleContent += "\n\n";
		}

		terminalRuleContent += "terminal " + name + ":\n\t";

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
		while (terminalRuleContent.endsWith(" ")
				|| terminalRuleContent.endsWith("|")
				|| terminalRuleContent.endsWith("\t")
				|| terminalRuleContent.endsWith("\n")) {

			terminalRuleContent = terminalRuleContent.substring(0,
					terminalRuleContent.length() - 1);
		}

		// end Rule
		terminalRuleContent += "\n;";

		this.setTerminalRules(terminalRuleContent);
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
	 * Adds a statement for every segment of the rule so that Xtext can refer to
	 * it. <br>
	 * Assumes that there are no assignments already
	 */
	public void createAssignments() {
		this.setBaseRuleContent(createAssignments(this.getBaseRuleContent()));
		this.setAtomicRuleContent(createAssignments(this.getAtomicRuleContent()));
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
		
		//seperate this from the other elements
		content = content.replace("\n\t|", " \n\t|");

		String[] contentElements = Functions.getElements(content);

		for (int k = 0; k < contentElements.length; k++) {
			String currentElement = contentElements[k];

			String toCheck = cleanString(currentElement);

			if (toCheck.equals("*") || toCheck.equals("|")
					|| toCheck.startsWith("{") || toCheck.startsWith("\"")
					|| (toCheck.contains("=") && !toCheck.contains("=>")) || toCheck.equals("?")
					|| toCheck.startsWith("//") || toCheck.equals("=>")) {
				// These elements don't need an assignmnet or already have one
				continue;
			}

			if (toCheck.startsWith("*")) {
				contentElements[k] = null;
				continue;
			}

			if (currentElement.startsWith("[[") || currentElement.startsWith("[=>[")) {
				String giveAway = currentElement.substring(1,
						currentElement.length() - 1);
				giveAway = createAssignments(giveAway, usedNames);

				// processing finished -> can maintain round brackets
				currentElement = "(" + giveAway + ")";

				contentElements[k] = currentElement;

				continue;
			}

			if (currentElement.startsWith("[") || currentElement.startsWith("=>[")) {
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
				
				if(startIndex > 0) {
					//if it should start with a syntactic predicate
					synPred = "=>";
				}
				
				// finished processing -> can maintain round brackets
				currentElement = synPred + "(" + giveAway.replace("=", connector) + ")";
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

				while (usedNames.contains(assignmentName)) {
					// if this name has been used already
					counter++;

					// remove number from name again
					int offset;
					if (counter <= 10) {
						offset = 1;
					} else {
						if (counter <= 100) {
							offset = 2;
						} else {
							System.err.println("Unexpected high counter in '"
									+ currentElement + "'");
							return null;
						}
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
	 * @return True if the rule is empty (baseRule only contains atomicRule-call
	 *         and atomicRule is empty)
	 */
	public boolean isEmpty() {
		String baseContent = this.getBaseRuleContent();
		String atomicContent = this.getAtomicRuleContent();
		String atomicName = this.getName() + "Atomic";
		String atomicNameWithAssignment = "atomic=" + atomicName;

		if ((baseContent.equals(atomicName) || baseContent
				.equals(atomicNameWithAssignment)) && atomicContent.isEmpty()) {
			// if baseRule only contains the call for the atomic rule and the
			// atomic rule is empty the complete rule is empty
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
		ArrayList<String> reachableRuleNames = this.getReachableRules();

		for (String name : newRuleNames) {
			if (!reachableRuleNames.contains(name)) {
				reachableRuleNames.add(name);
			}
		}

		this.setReachableRules(reachableRuleNames);
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

			while (!currentContent.isEmpty()) {
				String firstElement = Grammar.getFirstElement(currentContent);
				firstElement = cleanString(firstElement);

				if (firstElement.startsWith("|")) {
					firstElement = firstElement.substring(1);
					firstElement = ParserRule.cleanString(firstElement);
				}

				if (!Functions.startsWithLetter(firstElement)) {
					if (!firstElement.startsWith("(")) {
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
		ArrayList<String> reachableRuleNames = this.getReachableRules();

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
	 * Prevent that an AtomicRuleCall is listed in this.reachableRules if it's
	 * mainRule is listed, too.
	 */
	public void formatReachableRules() {
		ArrayList<String> newReachableRuleNames = new ArrayList<String>();
		ArrayList<String> reachableRuleNames = this.getReachableRules();

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

		this.setReachableRules(newReachableRuleNames);
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
}
