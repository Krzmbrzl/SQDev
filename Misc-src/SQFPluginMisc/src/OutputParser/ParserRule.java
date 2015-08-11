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

	public ParserRule(String name) {
		if (name.indexOf("Returner") >= 0) {
			name = name.substring(0, name.indexOf("Returner"));
		}

		setName(name);
		this.counter = 0;
		this.terminalRules = "";
		this.terminalRulePrefix = "";
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

	/**
	 * Increases the counter one step
	 */
	public void count() {
		this.setCounter(this.getCounter() + 1);
	}

	public String toString() {
		ruleContent += "\n\n" + terminalRules + "\n\n\n";
		return ruleContent;
	}

	/**
	 * This creates the basic ruleStructure with an atomic-rule
	 */
	public void create() {
		ruleContent = this.ruleName + ":\n" + "\t" + this.ruleName
				+ "Atomic\n;" + "\n\t\t" + this.ruleName + "Atomic:\n\t\t\t;";
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

		// if syntax is atomic write it in the atomic rule
		if (this.isAtomic(newFragment)) {
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
			// without it
			newFragment = "(" + newFragment + ")*";

			if (mainContent.indexOf(")*") >= 0) {
				// if it already contains a non-atomic rule

				String fragment1 = mainContent.substring(0,
						mainContent.indexOf("("));
				String fragment2 = mainContent.substring(mainContent
						.indexOf("("));

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
					mainContent = fragment1 + "(" + fragment2 + " | "
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
		if (syntax.indexOf(" ") < 0) {
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

			if (counter == 7) {
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

		if (modelContent.indexOf(ruleName) >= 0) {
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
					this.addSyntax(giveAway);

					if (!proceed) {
						// The rest of modelContent is formatted and has to be
						// added as well
						this.addSyntax(modelContent);
					}
				}
			} else {
				this.addSyntax(modelContent);
			}
		}
	}

	/**
	 * Adds a statement for every segment of the rule so that Xtext can refer to
	 * it
	 */
	public void createAssignments() {
		if (this.getName().equals("Array")) {
			String dummy = "";
		}

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

		String[] contentElements = Functions.getElements(content);

		for (int k = 0; k < contentElements.length; k++) {
			String currentElement = contentElements[k];

			String toCheck = cleanString(currentElement);

			if (toCheck.equals("*") || toCheck.equals("|")
					|| toCheck.startsWith("{") || toCheck.startsWith("\"")
					|| toCheck.indexOf("=") >= 0 || toCheck.equals("?")) {
				// These elements don't need an assignmnet or already have one
				continue;
			}

			if (toCheck.startsWith("*")) {
				contentElements[k] = null;
				continue;
			}

			if (currentElement.startsWith("[[")) {
				String giveAway = currentElement.substring(1,
						currentElement.length() - 1);
				giveAway = createAssignments(giveAway, usedNames);

				// processing finished -> can maintain round brackets
				currentElement = "(" + giveAway + ")";

				contentElements[k] = currentElement;

				continue;
			}

			if (currentElement.startsWith("[")) {
				// TODO: implement -> difference between ()* and ()? and ()
				String nextElement = "";
				if (contentElements.length > (k + 1)) {
					nextElement = contentElements[k + 1];
				}

				String connector = "=";
				if (nextElement.equals("*")) {
					connector = "+=";
				}

				String giveAway = currentElement.substring(1,
						currentElement.length() - 1);
				giveAway = createAssignments(giveAway, usedNames);

				// finished processing -> can maintain round brackets
				currentElement = "(" + giveAway.replace("=", connector) + ")";
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
					// There should only be one atomic call in a ruleso no
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
}
