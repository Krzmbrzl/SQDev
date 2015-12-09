package OutputParser;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;

import SQF.Functions;

public class Grammar {
	private String					header;
	private ArrayList<ParserRule>	ruleList;
	private boolean					isAssociated;
	
	public Grammar() {
		setRules(new ArrayList<ParserRule>());
		setHeader("");
		this.setAsAssociated(false);
	}
	
	public Grammar(String header) {
		setRules(new ArrayList<ParserRule>());
		setHeader(header);
		this.setAsAssociated(false);
	}
	
	public String getHeader() {
		return header;
	}
	
	public void setHeader(String header) {
		this.header = header;
	}
	
	public ParserRule getRule(String name) {
		// make sure name starts with a capital letter
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		
		for (ParserRule currentRule : ruleList) {
			if (currentRule.getName().toLowerCase().equals(name.toLowerCase())) {
				return currentRule;
			}
		}
		
		System.err.println("Couldn't find rule with name '" + name + "'");
		return null;
	}
	
	public ParserRule getRule(int index) {
		return this.ruleList.get(index);
	}
	
	/**
	 * Finds out the index of the rule with the given name within this grammar. <br>
	 * Returns -1 if rule hasn't been found.
	 * 
	 * @param ruleName
	 *            The name of the wanted rule
	 * @return
	 */
	public int getRuleIndex(String ruleName) {
		for (int i = 0; i < this.getRuleCount(); i++) {
			ParserRule currentRule = this.getRule(i);
			
			if (currentRule.getName().toLowerCase().equals(ruleName.toLowerCase())) {
				return i;
			}
		}
		
		System.err.println("Rule '" + ruleName + "' not found!");
		return -1;
	}
	
	public ArrayList<ParserRule> getRules() {
		ArrayList<ParserRule> copy = new ArrayList<ParserRule>(ruleList);
		return copy;
	}
	
	/**
	 * @return A list of all baseRules in this grammar
	 */
	public ArrayList<ParserRule> getBaseRules() {
		ArrayList<ParserRule> baseRules = new ArrayList<ParserRule>();
		
		for (ParserRule currentRule : this.getRules()) {
			if (currentRule.isBaseRule()) {
				baseRules.add(currentRule);
			}
		}
		
		return baseRules;
	}
	
	/**
	 * @return A list of all rules except the terminals
	 */
	public ArrayList<ParserRule> getNonTerminalRules() {
		ArrayList<ParserRule> nonTerminals = new ArrayList<ParserRule>();
		
		for (ParserRule currentRule : this.getRules()) {
			if (!currentRule.isTerminal()) {
				// add to list if it's a nonTerminal rule
				nonTerminals.add(currentRule);
			}
		}
		
		return nonTerminals;
	}
	
	/**
	 * Sets the rule at the given position
	 * 
	 * @param rule
	 * @param index
	 */
	public void setRule(ParserRule rule, int index) {
		this.ruleList.set(index, rule);
		this.setAsAssociated(false);
	}
	
	/**
	 * @return The names of all rules the grammar contains
	 */
	public String[] getRuleNames() {
		String[] names = new String[ruleList.size()];
		
		for (int i = 0; i < ruleList.size(); i++) {
			ParserRule currentRule = ruleList.get(i);
			names[i] = currentRule.getName();
		}
		
		return names;
	}
	
	public int getRuleCount() {
		return this.ruleList.size();
	}
	
	public void setRules(ArrayList<ParserRule> rules) {
		this.ruleList = rules;
		this.setAsAssociated(false);
	}
	
	public boolean isAssociated() {
		return isAssociated;
	}
	
	public void setAsAssociated(boolean isAssociated) {
		for (ParserRule currentRule : this.getRules()) {
			currentRule.setAsAssociated(isAssociated);
		}
		this.isAssociated = isAssociated;
	}
	
	/**
	 * Adds a rule to the grammar
	 * 
	 * @param rule
	 *            The rule to be added
	 * @param preventDuplicates
	 *            false to allow duplicates of rules
	 */
	public void addRule(ParserRule rule, boolean preventDuplicates) {
		if (this.containsRule(rule.getName()) && preventDuplicates) {
			// merge rules if the rule already exists in the grammar
			this.getRule(rule.getName()).mergeWith(rule);
			
			return;
		}
		
		// check for appended rules
		if (!rule.getAppendedRules().isEmpty()) {
			for (ParserRule currentAppendedRule : rule.getAppendedRules()) {
				if (currentAppendedRule.getBaseRuleName().isEmpty()) {
					// set baseRuleName if not set already
					currentAppendedRule.setBaseRuleName(rule.getName());
				}
				this.addRule(currentAppendedRule);
				
				// reset appended rules
				currentAppendedRule.setAppendedRules(new ArrayList<ParserRule>());
			}
		}
		
		ruleList.add(rule);
		this.setAsAssociated(false);
		
		// reset appended rules
		rule.setAppendedRules(new ArrayList<ParserRule>());
	}
	
	/**
	 * Adds a rule to the grammar
	 * 
	 * @param rule
	 *            The rule to be added
	 */
	public void addRule(ParserRule rule) {
		this.addRule(rule, true);
	}
	
	public String toString(boolean associate) {
		if (!this.isAssociated() && associate) {
			// create associations first
			System.out.println("\nPerforming association before returning stringRepresentaion...");
			
			this.associate();
			
			System.out.println("Grammar associated!\n");
		}
		
		String grammarContent = this.getHeader();
		if (!grammarContent.endsWith("\n")) {
			// make sure there is a newline after the header
			grammarContent += "\n";
		}
		
		for (ParserRule currentRule : this.ruleList) {
			currentRule.format();
			
			if (!currentRule.isSubRule()) {
				// seperate new rule complex
				grammarContent += "\n\n\n";
			}
			grammarContent += "\n\n" + currentRule.toString();
		}
		
		// format
		grammarContent = ParserRule.cleanString(grammarContent) + "\n";
		
		return grammarContent;
	}
	
	public String toString() {
		return this.toString(true);
	}
	
	/**
	 * Finds out if this grammar contains a rule with the given name
	 * 
	 * @param name
	 * @return
	 */
	public boolean containsRule(String name) {
		String[] names = this.getRuleNames();
		
		// make sure name starts with a capital letter
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		
		return Functions.isIn(names, name, true);
	}
	
	/**
	 * Adds a statement for every ruleCall in the rules so that Xtext can refer to it. <br>
	 * Assumes that there are no assignments yet
	 */
	public void createAssignments() {
		for (ParserRule currentRule : this.getRules()) {
			if (!currentRule.isTerminal()) {
				currentRule.createAssignments();
			}
		}
	}
	
	/**
	 * @return True if the grammar contains left-recursive ruleCalls
	 */
	public boolean isLeftRecursive() {
		boolean isLeftRecursive = false;
		
		for (ParserRule currentRule : this.getRules()) {
			if (currentRule.getReachableStartRules().contains(currentRule.getName())) {
				isLeftRecursive = true;
				break;
			}
		}
		
		return isLeftRecursive;
	}
	
	/**
	 * Checks if the given ruleContent contains a left-recursive ruleCall according to the given
	 * ruleNames
	 * 
	 * @param ruleContent
	 *            The content to be checked
	 * @param ruleNames
	 *            The names of all rules the method should check for
	 * @return
	 */
	public boolean containsLeftRecursion_FIRSTATTEMPT(String ruleContent, ArrayList<String> ruleNames,
			String currentRuleName) {
		
		String firstElement = getFirstElement(ruleContent);
		if (firstElement.endsWith("|")) {
			firstElement = firstElement.substring(0, firstElement.length() - 1);
		}
		firstElement = ParserRule.cleanString(firstElement);
		
		if (ruleNames.contains(firstElement)) {
			// check if the called rule itself can start with a rule call to
			// this rule again
			ParserRule toCheck = this.getRule(firstElement);
			if (toCheck.containsStartRuleCallOf(currentRuleName)) {
				return true;
			}
		} else {
			// if first Element is surrounded by brackets then check the bracket
			// content
			if (firstElement.contains("(") || firstElement.contains(")")) {
				// remove brackets and special characters
				firstElement = firstElement.replace("(", " ");
				firstElement = firstElement.replace(")", " ");
				firstElement = firstElement.replace("*", " ");
				firstElement = firstElement.replace("?", " ");
				firstElement = firstElement.replace("|", " ");
				firstElement = ParserRule.cleanString(firstElement);
				firstElement = Functions.reduceSpaceBetween(firstElement);
				
				String[] elements = Functions.getElements(firstElement);
				
				for (String currentFirstElement : elements) {
					if (ruleNames.contains(currentFirstElement)) {
						// check if the called rule itself can start with a rule
						// call to this rule again
						ParserRule toCheck = this.getRule(firstElement);
						if (toCheck.containsStartRuleCallOf(currentRuleName)) {
							return true;
						}
					}
				}
			}
		}
		
		while (ruleContent.contains("\t|")) {
			ruleContent = ruleContent.substring(ruleContent.indexOf("\t|") + 2);
			ruleContent = ParserRule.cleanString(ruleContent);
			firstElement = getFirstElement(ruleContent);
			if (firstElement.endsWith("|")) {
				firstElement = firstElement.substring(0, firstElement.length() - 1);
			}
			firstElement = ParserRule.cleanString(firstElement);
			
			if (ruleNames.contains(firstElement)) {
				// check if the called rule itself can start with a rule call to
				// this rule again
				ParserRule toCheck = this.getRule(firstElement);
				if (toCheck.containsStartRuleCallOf(currentRuleName)) {
					return true;
				} else {
					ParserRule currentRule = this.getRule(currentRuleName);
					if (!currentRule.getBaseRuleContent().equalsIgnoreCase(ruleContent)) {
						// we are in the atmomic rule
						String atomicName = currentRuleName + "Atomic";
						if (toCheck.containsStartRuleCallOf(atomicName)) {
							return true;
						}
					}
				}
			} else {
				// if first Element is surrounded by brackets thencheck the
				// bracket content
				if (firstElement.contains("(") || firstElement.contains(")")) {
					// remove brackets and special characters
					firstElement = firstElement.replace("(", " ");
					firstElement = firstElement.replace(")", " ");
					firstElement = firstElement.replace("*", " ");
					firstElement = firstElement.replace("?", " ");
					firstElement = firstElement.replace("|", " ");
					firstElement = ParserRule.cleanString(firstElement);
					firstElement = Functions.reduceSpaceBetween(firstElement);
					
					String[] elements = Functions.getElements(firstElement);
					
					for (String currentFirstElement : elements) {
						if (ruleNames.contains(currentFirstElement)) {
							// check if the called rule itself can start with a
							// rule call to this rule again
							ParserRule toCheck = this.getRule(currentFirstElement);
							if (toCheck.containsStartRuleCallOf(currentRuleName)) {
								return true;
							}
						}
					}
				}
			}
		}
		
		// if previous didn't match then there is no left-recursion
		return false;
	}
	
	/**
	 * Left-factores the grammar to remove the left-recursion<br>
	 * <b>NOTE:</b> In this version a few parsing alternatives get lost
	 */
	public void removeLeftRecursion() {
		for (ParserRule currentRule : this.getRules()) {
			if (currentRule.isTerminal()) {
				continue;
			}
			
			if (currentRule.isLeftRecursive()) {
				// process every left-recursive rule
				for (String currentReachableRuleName : currentRule.getReachableStartRules()) {
					// process every reachable rule
					boolean onlyAtomic = false;
					
					if (currentReachableRuleName.contains("Atomic")) {
						// get name of the mainRule
						currentReachableRuleName = currentReachableRuleName.substring(0,
								currentReachableRuleName.indexOf("Atomic"));
						// indicate that only the atomic content is relevant
						onlyAtomic = true;
					}
					
					// get target rule
					ParserRule targetRule = this.getRule(currentReachableRuleName);
					
					// find out whether or not this rule is guilty about the
					// left-recursion of the currentlyprocessed rule
					boolean isGuilty = false;
					
					if (onlyAtomic) {
						isGuilty = targetRule.containsStartRuleCallOf(currentRule.getName(), false);
					} else {
						isGuilty = targetRule.containsStartRuleCallOf(currentRule.getName());
					}
					
					if (isGuilty) {
						// start left-factoring
						ParserRule ruleA;
						ParserRule ruleB;
						
						if (currentRule.howManyStartRuleCallsOf(currentReachableRuleName) >= targetRule
								.howManyStartRuleCallsOf(currentRule.getName())) {
							// if there are more ruleCalls in currentRule
							ruleA = currentRule;
							ruleB = targetRule;
						} else {
							// if there are more ruleCalls in targetRule
							ruleA = targetRule;
							ruleB = currentRule;
						}
						
						// move parseAlternatives to the BaseRule in ruleA
						// change ruleCall to atomic in ruleB
						
						// process ruleA
						String ruleAAtomicContent = ruleA.getAtomicRuleContent();
						String newAtomicRuleContent = "";
						
						while (!ruleAAtomicContent.isEmpty()) {
							int index = ruleAAtomicContent.indexOf("\n");
							
							if (index < 0) {
								// if there is only one more line left without
								// newLine
								index = ruleAAtomicContent.length();
							}
							
							String currentLine = ruleAAtomicContent.substring(0, index);
							currentLine = ParserRule.cleanString(currentLine);
							
							// remove processed line
							ruleAAtomicContent = ruleAAtomicContent.substring(index);
							ruleAAtomicContent = ParserRule.cleanString(ruleAAtomicContent);
							if (ruleAAtomicContent.startsWith("|")) {
								ruleAAtomicContent = ruleAAtomicContent.substring(1);
								ruleAAtomicContent = ParserRule.cleanString(ruleAAtomicContent);
							}
							
							String firstElement = getFirstElement(currentLine);
							
							if (Functions.startsWithLetter(firstElement)) {
								// single ruleCall
								if (firstElement.equals(ruleB.getName())) {
									// left-factor -> move line to BaseRule
									String newBaseRuleContent = ruleA.getBaseRuleContent();
									newBaseRuleContent += "\n| " + currentLine;
									
									ruleA.setBaseRuleContent(newBaseRuleContent);
								} else {
									// this line stays in the AtomicContent
									newAtomicRuleContent += "\n| " + currentLine;
								}
							} else {
								// possible multiRuleCall or optional parameter
								
								if (!firstElement.contains(ruleB.getName())) {
									// move on if the searched ruleCall is not
									// in there
									newAtomicRuleContent += "\n| " + currentLine;
									continue;
								}
								
								if (firstElement.charAt(0) != '(') {
									// it's not a multiRuleCall
									if (currentLine.contains("<Injected>")) {
										// it is a user-injected line
										System.out.println("Couldn't process user-injected line '"
												+ currentLine + "' in Grammar.removeLeftRecursion in ruleA");
									} else {
										System.err.println("Couldn't process line '" + currentLine
												+ "' in Grammar.removeLeftRecursion in ruleA");
									}
									// don't process line but keep it where it
									// was
									newAtomicRuleContent += "\n| " + currentLine;
								} else {
									// differ multiRuleCall - optional parameter
									
									if (currentLine.startsWith(firstElement + "?")
											&& !firstElement.contains("|")) {
										// process optional parameter
										int startIndex = firstElement.length() + 1;
										currentLine = currentLine.substring(startIndex); // remove
																							// optional
																							// parameter
										
										firstElement = firstElement.substring(1, startIndex - 2); // remove
																									// brackets
										
										// move optional alternative to the
										// BaseContent
										String newBaseRuleContent = ruleA.getBaseRuleContent();
										newBaseRuleContent += "\n| " + firstElement + currentLine;
										
										ruleA.setBaseRuleContent(newBaseRuleContent);
										
										currentLine = ParserRule.cleanString(currentLine);
										
										// let fix part (without optional rule
										// call) in the atomicContent
										newAtomicRuleContent += "\n| " + currentLine;
									} else {
										// process multi-ruleCalls
										
										// remove firstElement from currentLine
										currentLine = currentLine.substring(currentLine.indexOf(")") + 1);
										
										// remove brackets and clean up
										firstElement = firstElement.substring(1, firstElement.length() - 1);
										firstElement = firstElement.replace("|", " ");
										firstElement = Functions.reduceSpaceBetween(firstElement);
										
										String[] aElements = Functions.getElements(firstElement);
										
										// reset first element
										firstElement = "";
										
										String newAtomicStartRuleCall = "";
										
										// sort startRuleCall possibilities in
										// atomic and base
										for (String currentElement : aElements) {
											if (currentElement.equals(ruleB.getName())) {
												if (firstElement.isEmpty()) {
													firstElement = currentElement;
												} else {
													// shouldn't be more than
													// one element
													System.err
															.println("Multiple alternatives in ruleA in Grammar.removeLeftRecursion");
												}
											} else {
												newAtomicStartRuleCall += " | " + currentElement;
											}
										}
										
										if (newAtomicStartRuleCall.startsWith(" | ")) {
											// get rid of "|" and startSpaces
											newAtomicStartRuleCall = newAtomicStartRuleCall.substring(3);
										}
										
										if (newAtomicStartRuleCall.contains("|")) {
											// add brackets again because there
											// are still multiple alternatives
											newAtomicStartRuleCall = "(" + newAtomicStartRuleCall + ")";
										}
										
										// assemble and add line to the new
										// atomicContent
										newAtomicRuleContent += "\n| " + newAtomicStartRuleCall + currentLine;
										
										if (currentLine.startsWith("?")) {
											// if it's an optional parameter
											// then add brackets as well
											firstElement = "(" + firstElement + ")";
										}
										
										String newBaseRuleContent = ruleA.getBaseRuleContent();
										newBaseRuleContent += "\n| " + firstElement + currentLine;
										ruleA.setBaseRuleContent(newBaseRuleContent);
									}
								}
							}
						}
						
						newAtomicRuleContent = ParserRule.cleanString(newAtomicRuleContent);
						if (newAtomicRuleContent.startsWith("|")) {
							newAtomicRuleContent = newAtomicRuleContent.substring(1);
							newAtomicRuleContent = ParserRule.cleanString(newAtomicRuleContent);
						}
						ruleA.setAtomicRuleContent(newAtomicRuleContent);
						
						// process ruleB
						String ruleBAtomicContent = ruleB.getAtomicRuleContent();
						newAtomicRuleContent = ""; // reset
						
						while (!ruleBAtomicContent.isEmpty()) {
							int index = ruleBAtomicContent.indexOf("\n");
							
							if (index < 0) {
								index = ruleBAtomicContent.length();
							}
							
							// get current line
							String currentLine = ruleBAtomicContent.substring(0, index);
							currentLine = ParserRule.cleanString(currentLine);
							
							// remove line from ruleBAtomicContent
							ruleBAtomicContent = ruleBAtomicContent.substring(index);
							ruleBAtomicContent = ParserRule.cleanString(ruleBAtomicContent);
							if (ruleBAtomicContent.startsWith("|")) {
								ruleBAtomicContent = ruleBAtomicContent.substring(1);
								ruleBAtomicContent = ParserRule.cleanString(ruleBAtomicContent);
							}
							
							// get first element
							String firstElement = getFirstElement(currentLine);
							
							if (Functions.startsWithLetter(firstElement)) {
								// single rule call
								if (firstElement.equals(ruleA.getName())) {
									// change firstElement to an atomicRuleCall
									// and replace old ruleCall
									firstElement += "Atomic";
									currentLine = firstElement
											+ currentLine.substring(currentLine.indexOf(" "));
									newAtomicRuleContent += "\n| " + currentLine;
								} else {
									// just keep line where it was
									newAtomicRuleContent += "\n| " + currentLine;
								}
							} else {
								// multi rule call or optional parameter
								
								if (!firstElement.contains(ruleA.getName())) {
									// move on if the searched ruleCall is not
									// in there
									newAtomicRuleContent += "\n| " + currentLine;
									continue;
								}
								
								if (firstElement.charAt(0) != '(') {
									// it's not a multiRuleCall
									if (currentLine.contains("<Injected>")) {
										// it is a user-injected line
										System.out.println("Couldn't process user-injected line '"
												+ currentLine + "' in Grammar.removeLeftRecursion in ruleB");
									} else {
										System.err.println("Couldn't process line '" + currentLine
												+ "' in Grammar.removeLeftRecursion in ruleB");
									}
									// don't process line but keep it where it
									// was
									newAtomicRuleContent += "\n| " + currentLine;
								} else {
									if (currentLine.startsWith(firstElement + "?")
											&& !firstElement.contains("|")) {
										// remove brackets
										firstElement = firstElement.substring(1, firstElement.length() - 1);
										
										firstElement = ParserRule.cleanString(firstElement);
										
										// change to atomicRuleCall and replace
										firstElement += "Atomic";
										firstElement = "(" + firstElement + ")"; // add
																					// brackets
																					// again
										
										currentLine = firstElement
												+ currentLine.substring(currentLine.indexOf("?"));
										
										newAtomicRuleContent += "\n| " + currentLine;
									} else {
										// remove brackets
										firstElement = firstElement.substring(1, firstElement.length() - 1);
										
										firstElement = ParserRule.cleanString(firstElement);
										firstElement = firstElement.replace("|", " ");
										firstElement = Functions.reduceSpaceBetween(firstElement);
										
										String[] aElements = Functions.getElements(firstElement);
										firstElement = ""; // reset
										
										for (String currentElement : aElements) {
											if (currentElement.equals(ruleA.getName())) {
												currentElement += "Atomic";
											}
											
											firstElement += " | " + currentElement;
										}
										
										if (firstElement.startsWith(" | ")) {
											firstElement = firstElement.substring(3);
										}
										// format and add brackets again
										firstElement = "(" + ParserRule.cleanString(firstElement) + ")";
										
										// replace in currentLine
										currentLine = firstElement
												+ currentLine.substring(currentLine.indexOf(")") + 1);
										
										newAtomicRuleContent += "\n| " + currentLine;
									}
								}
							}
						}
						
						newAtomicRuleContent = ParserRule.cleanString(newAtomicRuleContent);
						if (newAtomicRuleContent.startsWith("|")) {
							newAtomicRuleContent = newAtomicRuleContent.substring(1);
							newAtomicRuleContent = ParserRule.cleanString(newAtomicRuleContent);
						}
						ruleB.setAtomicRuleContent(newAtomicRuleContent);
					}
				}
				// update reachableRules
				this.createStartRuleForecasts();
			}
		}
	}
	
	public void removeLeftRecursion_FIRSTATTEMPT() {
		String[] ruleNames = this.getRuleNames();
		
		for (int i = 0; i < this.getRuleCount(); i++) {
			ParserRule currentRule = this.getRule(i);
			
			currentRule.setBaseRuleContent(removeLeftRecursion_FIRSTATTEMPT(currentRule.getBaseRuleContent(),
					ruleNames, this, currentRule));
			currentRule.setAtomicRuleContent(removeLeftRecursion_FIRSTATTEMPT(
					currentRule.getAtomicRuleContent(), ruleNames, this, currentRule));
			
			this.setRule(currentRule, i);
		}
	}
	
	public static String removeLeftRecursion_FIRSTATTEMPT(String ruleContent, String[] ruleNames,
			Grammar grammar, ParserRule currentRule) {
		/*
		 * process each line of the string and check whether or not it's left recursive and fix that
		 * -> consider left-recursion due to multiple rule calls
		 */
		boolean currentIsAtomic = !currentRule.getBaseRuleContent().equalsIgnoreCase(ruleContent);
		int lineNumber = 0;
		String contentCopy = ruleContent;
		String currentRuleName = currentRule.getName();
		
		if (!contentCopy.contains("\n")) {
			// make sure string is processed at least once
			contentCopy += "\n";
		}
		
		while (contentCopy.contains("\n")) {
			// process each line
			lineNumber++; // remember which line is currently processed
			
			String firstElement = getFirstElement(contentCopy);
			if (firstElement.endsWith("|")) {
				firstElement = firstElement.substring(0, firstElement.length() - 1);
			}
			
			firstElement = ParserRule.cleanString(firstElement);
			
			boolean canBeLeftRecursive = Functions.isIn(ruleNames, firstElement, true);
			boolean isLeftRecursive = false;
			boolean isSurroundedByBrackets = false;
			
			if (!canBeLeftRecursive && firstElement.startsWith("(")) {
				isSurroundedByBrackets = true;
				
				// remove brackets and special characters
				firstElement = firstElement.replace("(", " ");
				firstElement = firstElement.replace(")", " ");
				firstElement = firstElement.replace("*", " ");
				firstElement = firstElement.replace("?", " ");
				firstElement = firstElement.replace("|", " ");
				firstElement = ParserRule.cleanString(firstElement);
				firstElement = Functions.reduceSpaceBetween(firstElement);
				
				String[] aElements = Functions.getElements(firstElement);
				
				for (int i = 0; i < aElements.length; i++) {
					firstElement = aElements[i];
					
					canBeLeftRecursive = Functions.isIn(ruleNames, firstElement, true);
					
					if (canBeLeftRecursive) {
						if (grammar.getRule(firstElement).containsStartRuleCallOf(currentRuleName)) {
							break;
						}
					}
				}
			}
			
			if (canBeLeftRecursive) {
				isLeftRecursive = grammar.getRule(firstElement).containsStartRuleCallOf(currentRuleName);
				
				if (!isLeftRecursive && currentIsAtomic) {
					isLeftRecursive = grammar.getRule(firstElement).containsStartRuleCallOf(
							currentRuleName + "Atomic");
				}
			}
			
			if (isLeftRecursive) {
				ParserRule targetRule = grammar.getRule(firstElement);
				
				if (isSurroundedByBrackets) {
					// implement
				} else { // get rid of else?
					if (targetRule.containsStartRuleCallOf(currentRuleName + "Atomic", true, false)) {
						// if target rule has already been left-factored
						
						firstElement += "Atomic";
						
						contentCopy = firstElement + contentCopy.substring(contentCopy.indexOf(" "));
					} else {
						firstElement += "Atomic";
						// get line that has to be moved to the baseRule
						String currentLine = contentCopy.substring(0, contentCopy.indexOf("\n"));
						if (currentLine.contains(" ")) {
							currentLine = firstElement + currentLine.substring(currentLine.indexOf(" "));
						} else {
							currentLine = firstElement;
						}
						// remove the line from current ruleContent
						contentCopy = contentCopy.substring(contentCopy.indexOf("\n") + 1);
						contentCopy = ParserRule.cleanString(contentCopy);
						if (contentCopy.startsWith("|")) {
							contentCopy = contentCopy.substring(1);
							contentCopy = ParserRule.cleanString(contentCopy);
						}
						
						// add the respective line to the baseRuleContent
						String baseRuleContent = currentRule.getBaseRuleContent();
						baseRuleContent += "\n| " + currentLine;
						currentRule.setBaseRuleContent(baseRuleContent);
						
						// grammar.setRule(currentRule,
						// grammar.getRuleIndex(currentRuleName));
					}
				}
				
				String newRuleContent = "";
				
				for (int k = 1; k < lineNumber; k++) {
					newRuleContent += ruleContent.substring(0, ruleContent.indexOf("\n") + 1);
					ruleContent = ruleContent.substring(ruleContent.indexOf("\n") + 1);
					ruleContent = ParserRule.cleanString(ruleContent);
				}
				
				newRuleContent += "| " + contentCopy;
				
				ruleContent = newRuleContent;
			}
			
			contentCopy = contentCopy.substring(contentCopy.indexOf("\n") + 1);
			
			contentCopy = ParserRule.cleanString(contentCopy);
			
			if (contentCopy.startsWith("|")) {
				contentCopy = contentCopy.substring(1);
				contentCopy = ParserRule.cleanString(contentCopy);
			}
		}
		
		return ruleContent;
	}
	
	/**
	 * Sets the reachableStartRules for every rule in this grammar
	 */
	public void createStartRuleForecasts() {
		for (ParserRule currentRule : this.getRules()) {
			if (!currentRule.isTerminal()) {
				// create first layer
				if (!currentRule.isEmpty()) {
					currentRule.setReachableStartRules(currentRule.getStartRuleCalls());
					// indicate that forecast was done
					currentRule.setStartRuleForecastDone(true);
				} else {
					currentRule.setReachableStartRules(new ArrayList<String>());
				}
			}
		}
		
		boolean proceed = true;
		
		while (proceed) {
			ArrayList<ArrayList<String>> previousReachableRules = new ArrayList<ArrayList<String>>();
			for (ParserRule currentRule : this.getRules()) {
				
				ArrayList<String> reachableRules = currentRule.getReachableStartRules();
				previousReachableRules.add(reachableRules); // store previous
															// list for
															// comparison
				
				for (String currentReachableRuleName : reachableRules) {
					
					ParserRule currentReachableRule;
					boolean onlyAtomic = false;
					
					// find referenced rule in this grammar
					if (this.containsRule(currentReachableRuleName)) {
						currentReachableRule = this.getRule(currentReachableRuleName);
					} else {
						if (currentReachableRuleName.contains("Atomic")) {
							currentReachableRuleName = currentReachableRuleName.substring(0,
									currentReachableRuleName.indexOf("Atomic"));
							
							if (this.containsRule(currentReachableRuleName)) {
								currentReachableRule = this.getRule(currentReachableRuleName);
								onlyAtomic = true; // indicate that only the
													// atomicRule is referenced
							} else {
								System.err.println("Couldn't find reference to rule '"
										+ currentReachableRuleName + "' in Grammar.createStartruleForecast");
								return;
							}
						} else {
							System.err.println("Couldn't find reference to rule '" + currentReachableRuleName
									+ "' in Grammar.createStartruleForecast");
							return;
						}
					}
					
					// add reachable rules to the origin rule
					if (onlyAtomic) {
						// if only atomicRule was referenced then only add
						// startingRuleCalls from the atomicRule
						currentRule.addReachableRules(currentReachableRule.getStartRuleCalls(false));
					} else {
						currentRule.addReachableRules(currentReachableRule.getReachableStartRules());
					}
				}
				
				// indicate that forecast was done
				currentRule.setStartRuleForecastDone(true);
			}
			
			proceed = false; // reset proceed
			
			for (int k = 0; k < this.getRuleCount(); k++) {
				ParserRule currentRule = this.getRule(k);
				
				if (!previousReachableRules.get(k).equals(currentRule.getReachableStartRules())) {
					// if last loop iteration didn change something
					proceed = true;
					break; // skip next comparisons
				}
			}
		}
		
		// format reachableRules
		for (ParserRule currentRule : this.getRules()) {
			currentRule.formatReachableStartRules();
		}
	}
	
	/**
	 * Sets the reachableRules for every rule
	 */
	public void createRuleForecast() {
		for (ParserRule currentRule : this.getNonTerminalRules()) {
			if (!currentRule.isEmpty()) {
				currentRule.setReachableRules(currentRule.getRuleCalls());
			} else {
				currentRule.setReachableRules(new ArrayList<String>());
			}
		}
		
		boolean proceed = true;
		
		while (proceed) {
			ArrayList<ArrayList<String>> previousRuleForecast = new ArrayList<ArrayList<String>>();
			
			for (ParserRule currentRule : this.getRules()) {
				previousRuleForecast.add(currentRule.getReachableRules());
			}
			
			for (ParserRule currentRule : this.getRules()) {
				ArrayList<String> reachableRuleNames = currentRule.getReachableRules();
				
				for (String currentReachableRuleName : currentRule.getReachableRules()) {
					// boolean atomic = false;
					
					/*
					 * if (currentReachableRuleName.contains("Atomic")) { atomic = true; // get
					 * baseRule name currentReachableRuleName = currentReachableRuleName
					 * .substring(0, currentReachableRuleName .indexOf("Atomic")); }
					 */
					
					// combine reachable rules
					if (this.containsRule(currentReachableRuleName)) {
						ParserRule targetRule = this.getRule(currentReachableRuleName);
						
						for (String currentTargetReachableRuleName : targetRule.getReachableRules()) {
							if (!reachableRuleNames.contains(currentTargetReachableRuleName)) {
								// if not already contained
								reachableRuleNames.add(currentTargetReachableRuleName);
							}
						}
					} else {
						// rule not found
						
						// check if rule is defined in the header
						if (this.getHeader().contains(currentReachableRuleName + ":")) {
							/*
							 * System.out.println("Skipped headerRule '" + currentReachableRuleName
							 * + "' in Grammar.createRuleForecast()");
							 */
						} else {
							System.err.println("Couldn't find reference to rule '" + currentReachableRuleName
									+ "' in Grammar.createRuleForecast");
						}
					}
				}
				// update reachable rules
				currentRule.setReachableRules(reachableRuleNames);
			}
			
			// reset proceed
			proceed = false;
			
			for (int i = 0; i < this.getRuleCount(); i++) {
				ParserRule checkRule = this.getRule(i);
				
				if (!checkRule.getReachableRules().equals(previousRuleForecast.get(i))) {
					// if list has been updated
					proceed = true;
					break;
				}
			}
		}
	}
	
	/**
	 * Returns the first element in a string (seperated by newline or surrounded by brackets)
	 * 
	 * @param string
	 *            The string containing the wished first element
	 * @return
	 */
	public static String getFirstElement(String string) {
		if (string.isEmpty()) {
			return "";
		}
		
		boolean converted = false;
		if (!string.contains("[") && !string.contains("]")) {
			string = string.replace("(", "[");
			string = string.replace(")", "]");
			converted = true;
		}
		String[] elements = Functions.getElements(string);
		
		String element = elements[0];
		
		if (converted) {
			element = element.replace("[", "(");
			element = element.replace("]", ")");
		}
		
		return element;
	}
	
	/**
	 * Appends a anything-rule to this grammar
	 */
	public void appendAnythingRule() {
		ParserRule anythingRule = new ParserRule("AbsolutelyAnything");
		
		for (ParserRule currentRule : this.getBaseRules()) {
			anythingRule.addSyntax(currentRule.getName());
		}
		
		this.addRule(anythingRule);
	}
	
	/**
	 * Indicates if the grammar contains a recursive rule
	 * 
	 * @return
	 */
	public boolean isRecursive() {
		for (ParserRule currentRule : this.getRules()) {
			if (currentRule.isTerminal()) {
				continue;
			}
			
			if (currentRule.isRecursive()) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Creates associations between base- and subRules
	 */
	public void associate() {
		for (ParserRule currentRule : this.getRules()) {
			// check if currentRule is a subRule
			if (currentRule.isSubRule()) {
				String baseRuleName = currentRule.getBaseRuleName();
				
				if (this.containsRule(baseRuleName)) {
					ParserRule baseRule = this.getRule(baseRuleName);
					
					// assign currentRule as a subRule of baseRule
					baseRule.addSubRule(currentRule);
					
					// assign baseRule as the baseRule of currentRule and set
					// subLevel
					currentRule.setBaseRule(baseRule);
					currentRule.setSubLevel(baseRule.getSubLevel() + 1);
					
					if (currentRule.hasSubRules()) {
						// update subLevel of subSubRules
						for (ParserRule currentSubSubRule : currentRule.getSubRules()) {
							currentSubSubRule.setSubLevel(currentRule.getSubLevel() + 1);
						}
					}
				} else {
					System.err.println("Couldn't find rule '" + baseRuleName + "' for Grammar.associate()");
				}
			}
		}
		this.setAsAssociated(true);
	}
	
	/**
	 * Rearranges the rules in the grammar according to the association of the rules
	 */
	public void sort() {
		if (!this.isAssociated()) {
			this.associate();
		}
		
		ArrayList<ParserRule> newRuleList = new ArrayList<ParserRule>();
		
		for (ParserRule currentRule : this.getRules()) {
			if (!newRuleList.contains(currentRule) && !currentRule.isSubRule()) {
				// only process new baseRules
				newRuleList.add(currentRule);
				
				// add atomicRule
				if (currentRule.hasAtomicRule()) {
					newRuleList.add(currentRule.getAtomicRule());
				}
				
				// add possible subRules
				if (currentRule.hasSubRules()) {
					ArrayList<ParserRule> terminals = new ArrayList<ParserRule>();
					
					// add subRules
					for (ParserRule currentSubRule : currentRule.getAllSubRules()) {
						if (currentSubRule.isTerminal()) {
							// add terminals in the end
							terminals.add(currentSubRule);
						} else {
							if (!newRuleList.contains(currentSubRule)) {
								newRuleList.add(currentSubRule);
							}
						}
					}
					
					// add terminal rules
					for (ParserRule currentTerminalRule : terminals) {
						newRuleList.add(currentTerminalRule);
					}
				}
			}
		}
		
		this.setRules(newRuleList);
		
		this.setAsAssociated(true);
	}
	
	/**
	 * Formats every rule in this grammar accoring to it's respective position
	 */
	public void format() {
		for (ParserRule currentRule : this.getRules()) {
			currentRule.format();
		}
	}
	
	public void removeEmptyRules() {
		for (ParserRule currentRule : this.getRules()) {
			if (currentRule.isEmpty()) {
				this.deleteRule(currentRule);
			}
		}
	}
	
	/**
	 * Removes given rule from the grammar
	 * 
	 * @param rule
	 *            The rule to remove
	 */
	public void deleteRule(ParserRule rule) {
		if (!this.containsRule(rule.getName())) {
			// make sure the rule exists in this grammar
			System.err.println("Gramamr doesn't contain rule '" + rule.getName()
					+ "'! (Grammar.deleteRule(ParserRule rule)");
			return;
		}
		
		ArrayList<ParserRule> ruleList = this.getRules();
		
		int index = this.getRules().indexOf(rule);
		
		ruleList.remove(index);
		
		this.setRules(ruleList);
	}
	
	/**
	 * Deletes rule with the given name from this grammar
	 * 
	 * @param name
	 *            The name of the rule that should be removed
	 */
	public void deleteRule(String name) {
		if (this.containsRule(name)) {
			// make sure the rule exists in this grammar
			this.deleteRule(this.getRule(name));
		} else {
			System.err.println("Gramamr doesn't contain rule '" + name
					+ "'! (Grammar.deleteRule(String name)");
		}
	}
	
	public void removeNonExistingRuleCalls() {
		for (ParserRule currentRule : this.getNonTerminalRules()) {
			for (String currentName : currentRule.getReachableRules()) {
				if (!this.containsRule(currentName) && !this.getHeader().contains(currentName + ":")) {
					// remove respective ruleCall
					currentRule.removeRuleCall(currentName);
				}
			}
		}
		
		// update forecast
		this.createRuleForecast();
		
		// check if there are still empty rules
		for (ParserRule currentRule : this.getNonTerminalRules()) {
			for (String currentName : currentRule.getReachableRules()) {
				if (!this.containsRule(currentName)) {
					// call this function again
					this.removeEmptyRules();
					break;
				}
			}
		}
	}
	
	/**
	 * Left factores every single rule of the grammar for itself
	 */
	public void leftFactor_I() {
		// process each rule
		for (ParserRule currentRule : this.getNonTerminalRules()) {
			if (currentRule.needsLeftFactoring_I()) {
				// process each alternative of this rule
				String[] ruleAlternatives = currentRule.getLines();
				
				for (String currentLine : currentRule.getLines()) {
					String firstRuleCall = ParserRule.getFirstRuleCall(currentLine, true, true);
					
					if (firstRuleCall.isEmpty()) {
						// if no non-terminal ruleCall was found proceed with the
						// next alternative
						continue;
					}
					
					firstRuleCall = firstRuleCall.replace("(", " ");
					firstRuleCall = firstRuleCall.replace(")", " ");
					firstRuleCall = firstRuleCall.replace("|", " ");
					
					firstRuleCall = Functions.reduceSpaceBetween(firstRuleCall);
					firstRuleCall = Functions.removeStartBlank(firstRuleCall);
					
					String[] aRuleCalls = Functions.getElements(firstRuleCall);
					
					for (String firstRuleName : aRuleCalls) {
						
						if (!this.containsRule(firstRuleName)
								&& !this.getHeader().contains(firstRuleName + ":")) {
							// if rule can't be found skip it and continue with
							// other
							// rules
							System.err.println("Couldn't find reference to rule '" + firstRuleName
									+ "' in Grammar.leftFactor_I()");
							continue;
						}
						
						ParserRule firstRule = this.getRule(firstRuleName);
						
						if (currentRule.howManyStartRuleCallsOf(firstRule) > 1) {
							// if this rule has more than one alternative starting
							// with the same ruleCall
							
							// find all alternatives starting with this ruleCall
							
							if (this.containsRule(currentRule.getName() + "_" + firstRuleName + "Beginner")) {
								// if the grammar already contains this rule it has
								// been
								// processed already
								continue;
							}
							
							ArrayList<String> alternatives = new ArrayList<String>();
							
							int innerCounter = 0;
							
							boolean hasSingleCall = false;
							
							for (String currentAlternative : ruleAlternatives) {
								if (!currentAlternative.contains(" ")
										&& currentAlternative.equals(firstRuleName)) {
									hasSingleCall = true;
								}
								
								if (ParserRule.getFirstRuleCall(currentAlternative, true, true).equals(
										firstRuleName)) {
									// store alternatives and delete from original
									// alternatives
									// process singleRuleCalls
									alternatives.add(currentAlternative);
									ruleAlternatives[innerCounter] = null;
								} else {
									
									if (ParserRule.getFirstRuleCall(currentAlternative, true, true).contains(
											firstRuleName + " ")
											|| ParserRule.getFirstRuleCall(currentAlternative, true, true)
													.contains(" " + firstRuleName)) {
										// process multiRuleCalls and optional
										// parameter
										if (ParserRule.cleanString(currentAlternative).startsWith("(")) {
											// double check
											
											// format for getElements
											currentAlternative = currentAlternative.replace("(", "[");
											currentAlternative = currentAlternative.replace(")", "]");
											
											String[] aElements = Functions.getElements(currentAlternative);
											
											if (aElements[0].contains("|")) {
												String fragment1 = aElements[0].substring(0,
														aElements[0].indexOf(firstRuleName));
												
												String fragment2 = aElements[0].substring(aElements[0]
														.indexOf(firstRuleName) + firstRuleName.length());
												
												fragment1 = ParserRule.cleanString(fragment1);
												fragment2 = ParserRule.cleanString(fragment2);
												
												if (fragment1.endsWith("|")) {
													fragment1 = fragment1
															.substring(0, fragment1.length() - 1);
												} else {
													if (fragment2.startsWith("|")) {
														fragment2 = fragment2.substring(1);
													}
												}
												
												// create syntax that will be
												// relocated
												String outsourceSyntax = firstRuleName
														+ " "
														+ Functions.ArrayToString(Functions
																.getArrayContentFrom(aElements, 1));
												alternatives.add(outsourceSyntax);
												
												// create syntax that stays in place
												String mainFragment = fragment1 + " " + fragment2;
												
												if (!mainFragment.contains("|")
														|| (!aElements[1].startsWith("*")
																&& !aElements[1].startsWith("+") && !aElements[1]
																	.startsWith("?"))) {
													// remove brackets
													mainFragment = mainFragment.substring(1,
															mainFragment.length() - 1);
													mainFragment = ParserRule.cleanString(mainFragment);
												}
												
												// get rest of the syntax
												String fragment3 = Functions.ArrayToString(Functions
														.getArrayContentFrom(aElements, 1));
												
												mainFragment += " " + fragment3;
												
												// reformat
												mainFragment = mainFragment.replace("[", "(");
												mainFragment = mainFragment.replace("]", ")");
												
												ruleAlternatives[innerCounter] = mainFragment;
											}
										}
									}
								}
								
								innerCounter++;
							}
							
							if (alternatives.isEmpty()) {
								// no need to process further more if there is no
								// alternative with the respective startingRuleCAll
								continue;
							}
							
							ruleAlternatives = Functions.removeNullElements(ruleAlternatives);
							
							// make one empty line for the new alternative
							String[] cAlternatives = new String[ruleAlternatives.length + 1];
							
							for (int i = 0; i < ruleAlternatives.length; i++) {
								cAlternatives[i] = ruleAlternatives[i];
							}
							
							ruleAlternatives = cAlternatives;
							
							// create new subRule for the alternatives
							ParserRule altsRule = new ParserRule(currentRule.getName() + "_" + firstRuleName
									+ "Beginner", currentRule.getName());
							
							if (altsRule.getName().contains("Code_CodeAtomic")) {
								String dummy = "";
							}
							
							// pretend this is an atomic rule so no extra atomic
							// rule is
							// created
							altsRule.setAsAtomicRule(true);
							
							// add rule to grammar
							this.addRule(altsRule);
							
							boolean addSynPred = false;
							
							// get syntaxes from stored alternatives
							for (String currentAlt : alternatives) {
								// mark firstRuleName
								currentAlt = currentAlt.replace(firstRuleName, "§³");
								
								String fragment1 = currentAlt.substring(0, currentAlt.indexOf("§³"));
								String fragment2 = currentAlt.substring(currentAlt.indexOf("§³") + 2);
								
								currentAlt = fragment1 + fragment2;
								
								// if there where multiple ruleCalls
								currentAlt = currentAlt.replace("§³", firstRuleName);
								
								currentAlt = ParserRule.cleanString(currentAlt);
								
								// reformat
								currentAlt = currentAlt.replace("[", "(");
								currentAlt = currentAlt.replace("]", ")");
								
								if (currentAlt.startsWith("=>")) {
									addSynPred = true;
									currentAlt = currentAlt.substring(2);
									currentAlt = ParserRule.cleanString(currentAlt);
								}
								
								if (currentAlt.startsWith("(") && currentAlt.endsWith(")*")) {
									// make sure be a single call will be possible
									hasSingleCall = true;
									
									currentAlt = currentAlt.substring(1, currentAlt.lastIndexOf(")*"));
								}
								
								// add syntax to new rule
								altsRule.addSyntax(currentAlt);
							}
							
							String synPred = " ";
							
							if (addSynPred) {
								synPred = " =>";
							}
							
							// set new alternative with the new syntax
							if (hasSingleCall) {
								// assume the rule can be appliedmultiple times
								ruleAlternatives[ruleAlternatives.length - 1] = firstRuleName + synPred + "("
										+ altsRule.getName() + ")*";
							} else {
								ruleAlternatives[ruleAlternatives.length - 1] = firstRuleName + " "
										+ altsRule.getName();
							}
						}
					}
				}
				
				// clean rule content
				currentRule.setRuleContent("");
				
				// write new rule content
				for (String currentLine : ruleAlternatives) {
					currentRule.addLineToRuleContent(currentLine);
				}
			}
		}
	}
	
	/**
	 * Left factores the rule according to ambiguities caused by the grammar context
	 */
	public void leftFactor_II() {		
		for (ParserRule currentRule : sortByLevel(this.getNonTerminalRules(), true)) {			
			// check each rule if it has to get left factored (starting with the lowest rules)
			
			if (currentRule.needsLeftFactoring_II(this)) {
				// check for bracket alternatives
				boolean factoredBrackets = false;
				
				for (int x = 0; x < currentRule.getLines().length; x++) {
					String currentLine = currentRule.getLines()[x];
					
					if (currentLine.contains("(")) {
						String[] fragments = Functions.getElements(currentLine);
						
						for (int i = 0; i < fragments.length; i++) {
							String currentFragment = fragments[i];
							
							if (currentFragment.startsWith("(") && currentFragment.contains("|")) {
								// if there is an alternative in the bracket
								
								// remove unnecessary characters
								currentFragment = currentFragment.replace("(", " ");
								currentFragment = currentFragment.replace(")", " ");
								currentFragment = currentFragment.replace("|", " ");
								currentFragment = Functions.reduceSpaceBetween(currentFragment);
								currentFragment = Functions.removeStartBlank(currentFragment);
								
								String[] elements = Functions.getElements(currentFragment);
								
								ArrayList<ParserRule> alternatives = new ArrayList<ParserRule>();
								
								// gather alternatives as ParserRules
								for (String currentElement : elements) {
									if (this.containsRule(currentElement)) {
										alternatives.add(this.getRule(currentElement));
									} else {
										System.err.println("Couldn't find reference to rule '"
												+ currentElement + "' in Grammar.leftFactorII()");
									}
								}
								
								ArrayList<String> commonStartRules = new ArrayList<String>();
								
								// check for common startRules
								for (int k = 0; k < alternatives.size(); k++) {
									ParserRule firstRule = alternatives.get(k);
									
									// compare rule with each following rule
									for (int j = k + 1; j < alternatives.size(); j++) {
										ParserRule secondRule = alternatives.get(j);
										
										for (String currentCommonStartRule : getCommonStartRulesSorted(
												firstRule, secondRule)) {
											if (!commonStartRules.contains(currentCommonStartRule)) {
												// only take rules that havn't been registered
												// already
												commonStartRules.add(currentCommonStartRule);
											}
										}
									}
								}
								
								if (!commonStartRules.isEmpty()) {
									factoredBrackets = true;
								}else {
									// don't proceed if there are no common startRules
									continue;
								}
								
								int counter = 1;
								String helperName = currentRule.getName() + "_BracketHelper" + counter;
								
								while(this.containsRule(helperName)) {
									if(counter >= 10) {
										System.err.println("FuckThatShit!!!");
									}
									
									helperName = helperName.substring(0, helperName.length() - 1) + counter;
									
									counter++;
								}
								
								// left factor rules with aid of a small helper rule
								ParserRule helperRule = new ParserRule(helperName);
								helperRule.setAsAtomicRule(true);
								
								for (ParserRule currentAlt : alternatives) {
									helperRule.addSyntax(currentAlt.getName());
								}
								
								// add helperRule temporary to the grammar
								this.addRule(helperRule);
								
								/*for (String currentCommonStartRule : commonStartRules) {
									// TODO: loop this until it's no longer needed
									this.leftFactor_II(helperRule, currentCommonStartRule);
								}*/
								
								// refactor one step after another
								this.leftFactor_II(helperRule, commonStartRules.get(0));
								
								// set currentRule as the base rule for the new helper rules
								for (ParserRule currentSubRule : helperRule.getSubRules()) {
									currentSubRule.setBaseRule(currentRule);
								}
								
								// delete helper rule from grammar
								this.deleteRule(helperRule);
								
								// clear currentFragment
								currentFragment = "(";
								
								// get factored content
								for (String line : helperRule.getLines()) {
									currentFragment += line + " | ";
								}
								
								currentFragment = currentFragment.substring(0, currentFragment.length() - 3);
								
								currentFragment += ")";
								
							}
							// reassign the current fragment
							fragments[i] = currentFragment;
							
							currentLine = Functions.ArrayToString(fragments);
						}
					}
					
					// reassign the current line
					currentRule.setLine(x, currentLine);
				}
				
				if (factoredBrackets) {
					// factor step by step
					return;
				}
				
				// process startRules
				
				// update startRulesForecast
				this.createStartRuleForecasts();
				
				ArrayList<String> startRuleNames = currentRule.getStartRuleCalls();
				ArrayList<ParserRule> startRules = new ArrayList<ParserRule>();
				
				// gather startRule alts (as ParserRules)
				for (String currentName : startRuleNames) {
					if (this.containsRule(currentName)) {
						startRules.add(this.getRule(currentName));
					} else {
						if (!this.getHeader().contains(currentName + ":")) {
							System.err.println("Couldn't find reference to rule '" + currentName
									+ "' in Grammar.leftFactor_II");
						}
					}
				}
				
				int index1 = 0;
				for (ParserRule currentStartRule : startRules) {
					// check startRule against the other rules
					for (int i = 0; i < startRules.size(); i++) {
						if (i == index1) {
							// don't compare with self
							continue;
						}
						
						ParserRule compareRule = startRules.get(i);
						
						ArrayList<String> commonStartRules = this.getCommonStartRulesSorted(currentStartRule,
								compareRule);
						
						
						// if one of the rules can also start with the other rule this is the
						// highest priority to left-factor -> write at index 0 in list
						if (currentRule.canStartWith(compareRule)
								&& currentStartRule.canStartWith(compareRule)) {
							commonStartRules.add(0, compareRule.getName());
						}
						
						if (currentRule.canStartWith(currentStartRule)
								&& compareRule.canStartWith(currentStartRule)) {
							commonStartRules.add(0, currentStartRule.getName());
						}
						
						
						for (String currentStartRuleName : commonStartRules) {
							// left factor
							if (currentStartRule.canStartWith(currentStartRuleName)) {
								this.leftFactor_II(currentRule, currentStartRuleName);
								
								// process only one rule at a time
								return;
							} else {
								if (compareRule.canStartWith(currentStartRuleName)) {
									// this.leftFactor_II(compareRule, currentStartRuleName);
									this.leftFactor_II(currentRule, currentStartRuleName);
									
									// process only one rule at a time
									return;
								} else {
									String[] names = this.leftFactor_II(currentStartRule, compareRule);
									
									// assign new rule names in currentRule
									currentRule.replaceStartRuleCall(currentStartRule.getName(), names[0]);
									currentRule.replaceStartRuleCall(compareRule.getName(), names[1]);
									
									// process only one rule at a time
									return;
								}
							}
							
						}
					}
					
					index1++;
				}
				
				// TODO: Fucking check generic naming!!!
			}
		}
	}
	
	/**
	 * Left factores the given rules and returns the names of the resulting rules
	 * 
	 * @param rule1
	 * @param rule2
	 */
	private String[] leftFactor_II(ParserRule rule1, ParserRule rule2) {
		String[] names = new String[2];
		names[0] = rule1.getName();
		names[1] = rule2.getName();
		
		// check if the left factoring has to be applied at this level.
		boolean properLevel = false;
		String commonStartRule = "";
		
		for (String currentStartRule : rule1.getStartRuleCalls()) {
			if (rule2.canReachStartRule(currentStartRule)) {
				// if they have a startRule in common stop searching and start
				// processing
				commonStartRule = currentStartRule;
				properLevel = true;
				break;
			}
		}
		
		if (!properLevel) {
			// find out which startRule they have in common
			ArrayList<String> starters1 = rule1.getReachableStartRules();
			ArrayList<String> starters2 = rule2.getReachableStartRules();
			
			for (String currentStartRule : starters1) {
				if (starters2.contains(currentStartRule)) {
					commonStartRule = currentStartRule;
					break;
				}
			}
			
			if (commonStartRule.isEmpty()) {
				System.err.println("Rule '" + rule1.getName() + "' and rule '" + rule2.getName()
						+ "' do not have any startRules in common!"
						+ " (Grammar.leftFactor_II(ParserRule, ParserRule))");
			}
			
			// check the rules if they are on the proper level
			if (!rule1.canStartWith(commonStartRule)) {
				// reassign rule1
				for (String currentStartRuleName : rule1.getStartRuleCalls(true, false)) {
					ParserRule currentStartRule = this.getRule(currentStartRuleName);
					
					if (currentStartRule.canReachStartRule(commonStartRule)) {
						rule1 = currentStartRule;
					}
				}
			}
			
			if (!rule2.canStartWith(commonStartRule)) {
				// reassign rule2
				for (String currentStartRuleName : rule2.getStartRuleCalls(true, false)) {
					ParserRule currentStartRule = this.getRule(currentStartRuleName);
					
					if (currentStartRule.canReachStartRule(commonStartRule)) {
						rule2 = currentStartRule;
					}
				}
			}
			
			this.leftFactor_II(rule1, rule2);
			// TODO: assign returned names
			
			// System.err.println("Unproper level in Grammar.leftFactor_II(ParserRule, ParserRule)");
		} else {
			// the rules are in the proper level -> start left factoring
			ParserRule[] rules = { rule1, rule2 };
			
			ArrayList<String> rule1Syntaxes = new ArrayList<String>();
			ArrayList<String> rule2Syntaxes = new ArrayList<String>();
			
			boolean singleCall = false;
			
			for (int i = 0; i < 2; i++) {
				// process each rule
				for (String currentLine : rules[i].getLines()) {
					if (currentLine.startsWith(commonStartRule)) {
						// get syntax of this alternative
						if (i == 0) {
							rule1Syntaxes.add(currentLine);
						} else {
							rule2Syntaxes.add(currentLine);
						}
						
						if (currentLine.equals(commonStartRule)) {
							singleCall = true;
						}
					}
				}
			}
			
			// generate new HelperRules
			
			// new start Rule
			String startRuleName = rule1.getName() + "_" + rule2.getName() + "__" + commonStartRule
					+ "Factored";
			ParserRule starter = new ParserRule(startRuleName, rule2.getName());
			starter.setAsAtomicRule(true);
			
			ParserRule starterHelper = new ParserRule(startRuleName + "Helper", startRuleName);
			starterHelper.setAsAtomicRule(true);
			
			// add respective syntaxes in respective rules
			if (singleCall) {
				starter.addSyntax(commonStartRule + " (" + starterHelper.getName() + ")?");
			} else {
				starter.addSyntax(commonStartRule + " " + starterHelper.getName());
			}
			
			// add syntaxes from rule1
			for (String currentLine : rule1Syntaxes) {
				// remove the commonStartRule from the start of the
				// alternative
				currentLine = currentLine.substring(commonStartRule.length());
				currentLine = ParserRule.cleanString(currentLine);
				
				if (!starterHelper.containsLine(currentLine)) {
					starterHelper.addSyntax(currentLine);
				}
			}
			
			// add syntaxes from rule2
			for (String currentLine : rule2Syntaxes) {
				// remove the commonStartRule from the start of the
				// alternative
				currentLine = currentLine.substring(commonStartRule.length());
				currentLine = ParserRule.cleanString(currentLine);
				
				if (!starterHelper.containsLine(currentLine)) {
					starterHelper.addSyntax(currentLine);
				}
			}
			
			// rule1 Helper
			ParserRule rule1Helper = new ParserRule(rule1.getName() + "_" + commonStartRule + "Factored",
					rule1.getName());
			rule1Helper.setAsAtomicRule(true);
			
			rule1Helper.addSyntax(starter.getName());
			
			for (String currentLine : rule1.getLines()) {
				String[] aElements = Functions.getElements(currentLine);
				
				if (!aElements[0].contains(commonStartRule)) {
					// add this syntax to the helper rule, as it doesn't start
					// with the commonStartRule
					rule1Helper.addSyntax(currentLine);
				}
				
				/*
				 * if (aElements[0].contains(commonStartRule)) { // if this alt starts with the
				 * commonStartRule aElements[0] = aElements[0].replace(commonStartRule,
				 * starter.getName());
				 * 
				 * currentLine = Functions.ArrayToString(aElements); currentLine =
				 * currentLine.replace(") ", ")");
				 * 
				 * starterHelper.addSyntax(currentLine); } else { // add to the helper rule
				 * rule1Helper.addSyntax(currentLine); }
				 */
			}
			
			// rule2 Helper
			ParserRule rule2Helper = new ParserRule(rule2.getName() + "_" + commonStartRule + "Factored",
					rule2.getName());
			rule2Helper.setAsAtomicRule(true);
			
			rule2Helper.addSyntax(starter.getName());
			
			for (String currentLine : rule2.getLines()) {
				String[] aElements = Functions.getElements(currentLine);
				
				if (!aElements[0].contains(commonStartRule)) {
					// add this syntax to the helper rule, as it doesn't start
					// with the commonStartRule
					rule2Helper.addSyntax(currentLine);
				}
				
				/*
				 * if (aElements[0].contains(commonStartRule)) { // if this alt starts with the
				 * commonStartRule aElements[0] = aElements[0].replace(commonStartRule,
				 * starter.getName());
				 * 
				 * currentLine = Functions.ArrayToString(aElements); currentLine =
				 * currentLine.replace(") ", ")");
				 * 
				 * starterHelper.addSyntax(currentLine); } else { // add line to the helper rule
				 * rule2Helper.addSyntax(currentLine); }
				 */
			}
			
			// add new rules to grammar
			this.addRule(starter);
			this.addRule(starterHelper);
			this.addRule(rule1Helper);
			this.addRule(rule2Helper);
			
			// update returned names
			names[0] = rule1Helper.getName();
			names[1] = rule2Helper.getName();
			
		}
		
		return names;
	}
	
	private void leftFactor_II(ParserRule rule, String commonStartRuleName) {
		// check each alternative in this rule for the commonStartRule
		ArrayList<String> alternatives = new ArrayList<String>();
		
		for (String currentLine : rule.getLines()) {
			String starter = ParserRule.getFirstRuleCall(currentLine, false, true);
			
			if (starter.equals(commonStartRuleName)) {
				// add respective alternative to the "to-leftFactor-list"
				if (!alternatives.contains(currentLine)) {
					alternatives.add(currentLine);
				}
			} else {
				if (!this.containsRule(starter)) {
					// Header rules can't be processed yet
					continue;
				}
				
				ParserRule currentStartRule = this.getRule(starter);
				
				if (currentStartRule.canStartWith(commonStartRuleName)) {
					// create helperRule for the normal alts
					String ruleName = currentStartRule.getName() + "_Except" + commonStartRuleName;
					
					if (ruleName.substring(ruleName.indexOf("_Except") + 3).contains("_Except")) {
						// only write "_except" once
						String fragment1 = ruleName.substring(ruleName.lastIndexOf("_"));
						
						// remove duplicate from name
						ruleName = ruleName.substring(0, ruleName.length() - fragment1.length());
						
						fragment1 = fragment1.substring(7); // remove "_Except"
						
						ruleName += fragment1;
					}
					
					ParserRule helper = new ParserRule(ruleName, currentStartRule.getName());
					helper.setAsAtomicRule(true);
					currentStartRule.addSubRule(helper);
					
					this.addRule(helper);
					
					// extract the respective alternatives
					for (String cLine : currentStartRule.getLines()) {
						String[] aLine = Functions.getElements(cLine);
						
						if (aLine[0].contains(commonStartRuleName)) {
							if (!alternatives.contains(cLine)) {
								// add respective alternative to the "to-leftFactor-list"
								alternatives.add(cLine);
							}
						} else {
							// store normal alt in the helper rule
							helper.addSyntax(cLine);
						}
					}
					
					// update startRules in rule
					rule.replaceStartRuleCall(currentStartRule.getName(), helper.getName());
				}
			}
		}
		
		// remove all alts starting with commonStartRule from rule
		rule.removeStartRuleCall(commonStartRuleName);
		
		String ruleName = rule.getName() + "_" + commonStartRuleName + "Beginner";
		
		ParserRule factoredRule = new ParserRule(ruleName, rule.getName());
		factoredRule.setAsAtomicRule(true);
		rule.addSubRule(factoredRule);
		
		boolean singleCall = false;
		
		for (String currentAlt : alternatives) {
			// remove commonStartRule from the beginning of this alt
			currentAlt = currentAlt.substring(commonStartRuleName.length());
			
			currentAlt = ParserRule.cleanString(currentAlt);
			
			if (currentAlt.isEmpty()) {
				// alt only contained commonStartRule -> can be a single call
				singleCall = true;
			} else {
				factoredRule.addSyntax(currentAlt);
			}
		}
		
		if (!factoredRule.isEmpty()) {
			// if there are some alts in factoredRule
			this.addRule(factoredRule);
			
			if (singleCall) {
				rule.addLineToRuleContent(commonStartRuleName + " (" + factoredRule.getName() + ")?");
			} else {
				rule.addLineToRuleContent(commonStartRuleName + " " + factoredRule.getName());
			}
		} else {
			rule.addSyntax(commonStartRuleName);
		}
	}
	
	/**
	 * Checks if one of the rules contained in this grammr needs the leftFactor_I algorithm
	 * 
	 * @return
	 */
	public boolean needsLeftFactoring_I() {
		for (ParserRule currentRule : this.getNonTerminalRules()) {
			if (currentRule.needsLeftFactoring_I()) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Checks if one of the rules contained in this grammr needs the leftFactor_II algorithm
	 * 
	 * @return
	 */
	public boolean needsLeftFacoring_II() {
		for (ParserRule currentRule : this.getNonTerminalRules()) {
			if (currentRule.needsLeftFactoring_II(this)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Checks if one of the rules contained in this grammr needs to be left factored in any way
	 * 
	 * @return
	 */
	public boolean needsLeftFactoring() {
		if (this.needsLeftFactoring_I() || this.needsLeftFacoring_II()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Simplifies every rule in this grammar (if it can be simplified)
	 */
	public void simplify() {
		for (ParserRule currentRule : this.getNonTerminalRules()) {
			currentRule.simplify();
		}
	}
	
	/**
	 * Checks if the given grammar is equal to this one
	 * 
	 * @param compareGrammar
	 *            The grammar to compare to
	 * @return
	 */
	public boolean equals(Grammar compareGrammar) {
		Field[] fields = Grammar.class.getDeclaredFields();
		
		for (Field currentField : fields) {
			try {
				if (currentField.get(this) == null || currentField.get(compareGrammar) == null) {
					// handle uninitialized variables
					if (currentField.get(this) == null && currentField.get(compareGrammar) != null) {
						return false;
					}
					
					if (currentField.get(compareGrammar) == null && currentField.get(this) != null) {
						return false;
					}
				} else {
					if (!currentField.get(this).equals(currentField.get(compareGrammar))) {
						return false;
					}
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}
	
	/**
	 * Get the startRules these two rules have in common
	 * 
	 * @param rule1
	 * @param rule2
	 * @return An ordered list of the common startRules (root-rules are listed before their
	 *         children)
	 */
	public ArrayList<String> getCommonStartRulesSorted(ParserRule rule1, ParserRule rule2) {
		ArrayList<String> commonStartRules = ParserRule.getCommonStartRules(rule1, rule2);
		
		// The list for the ordered names
		ArrayList<String> commonStartRulesSorted = new ArrayList<String>();
		
		// make an empty list of same length
		for (int i = 0; i < commonStartRules.size(); i++) {
			commonStartRulesSorted.add("");
		}
		
		int previousCount = -1;
		
		int counter = 0;
		
		// sort startRules
		while (!commonStartRules.isEmpty()) {
			for (int i = 0; i < commonStartRules.size(); i++) {
				String currentRuleName = commonStartRules.get(i);
				
				if (this.containsRule(currentRuleName)) {
					ParserRule currentRule = this.getRule(currentRuleName);
					
					int count = 0;
					
					for (String currentStartRule : commonStartRules) {
						if (currentRule.canReachStartRule(currentStartRule)) {
							count++;
						}
					}
					
					if (count > previousCount) {
						// add the respective element at the respective position in the list
						commonStartRulesSorted.set(counter, currentRuleName);
						
						previousCount = count;
					}
				} else {
					// check if only HeaderRules are left
					
					boolean onlyHeadersLeft = true;
					
					for (String currentStartRuleName : commonStartRules) {
						if (this.containsRule(currentStartRuleName)) {
							// there are still rules that can be found in the grammar body
							onlyHeadersLeft = false;
							break;
						}
					}
					
					if (onlyHeadersLeft) {
						for (String current : commonStartRules) {
							// add all these rules to the sorted list
							commonStartRulesSorted.add(current);
						}
						
						// empty the old list
						commonStartRules.clear();
					}
				}
			}
			
			// remove the just placed element from the former list
			commonStartRules.remove(commonStartRulesSorted.get(counter));
			
			counter++;
			
			// reset
			previousCount = -1;
		}
		
		return commonStartRulesSorted;
	}
	
	/**
	 * Sorts the given list of ParserRules according to their subLevel
	 * @param rules The ruleList that should be sorted
	 * @param lowestFirst Should the ones with the lowest levels be listed first?
	 * @return The sorted list
	 */
	public static ArrayList<ParserRule> sortByLevel(ArrayList<ParserRule> rules, boolean lowestFirst) {
		int highestSubLevel = getHighestSubLevel(rules);
		
		ArrayList<ParserRule> sortedRules = new ArrayList<ParserRule>();
		
		for(int i=highestSubLevel; i>0; i--) {
			for(ParserRule currentRule : rules) {
				if(currentRule.getSubLevel() == i) {
					sortedRules.add(currentRule);
				}
			}
		}
		
		if(!lowestFirst) {
			Collections.reverse(sortedRules);
		}
		
		return sortedRules;
	}
	
	/**
	 * Gets the highest subrule level of the rules contained in the given list
	 * @param rules A list of ParserRules
	 */
	public static int getHighestSubLevel(ArrayList<ParserRule> rules) {
		int level = -1;
		
		for(ParserRule currentRule : rules) {
			int currentLevel = currentRule.getSubLevel();
			
			if(currentLevel > level) {
				level = currentLevel;
			}
		}
		
		return level;
	}
}
