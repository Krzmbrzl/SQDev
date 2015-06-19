package SQF;

import Exceptions.NotProperlyProcessedException;

/**
 * This is a collection of Functions which deal with Strings and String-Arrays
 * 
 * @author Raven
 *
 */

public class Functions {

	/**
	 * This function will cut a string out of another string
	 * 
	 * @param string
	 *            The original string
	 * @param removal
	 *            The string which should be cut form the original
	 * @return sFinished The original string which does not contain the
	 *         removal-string (or at lest one time less)
	 */

	public static String removeString(String string, String removal,
			boolean seperateWithBlank) {

		String sFinished = string;
		int len = removal.length();

		if (string.indexOf(removal) >= 0) {
			String fragment1 = string.substring(0, string.indexOf(removal));
			String fragment2 = string.substring(string.indexOf(removal) + len);

			if (seperateWithBlank) {
				sFinished = fragment1 + " " + fragment2;
			} else {
				sFinished = fragment1 + fragment2;
			}
		} else {
			Error("String enthält das zu entfernende Element nicht! -> Originalstring wird zurückgegeben");
		}

		return sFinished;
	}

	public static String removeString(String string, String removal) {
		String sFinished = removeString(string, removal, false);

		return sFinished;
	}

	/**
	 * This function will remove a part of a string from an indicator to another
	 * indicator
	 * 
	 * @param string
	 *            The original string which will be modified
	 * @param from
	 *            The indicator where the removal should start (that contains
	 *            the indicator itself to be removed)
	 * @param to
	 *            The indicator where the removal should stop (that contains the
	 *            indicator itself to be removed)
	 * @return sFinished The original string which does not contain the part
	 *         between the two indicators
	 */

	public static String removeFromTo(String string, String from, String to) {

		String sFinished = string;

		while (sFinished.indexOf(from) >= 0 && sFinished.indexOf(to) >= 0) {

			String fragment1 = sFinished.substring(0, sFinished.indexOf(from));
			String fragment2 = sFinished.substring(sFinished.indexOf(to)
					+ to.length());
			sFinished = fragment1 + " " + fragment2;
		}
		return sFinished;
	}

	/**
	 * This function will remove all blanks at hte beginning of a string
	 * 
	 * @param string
	 *            The original string which will be modified
	 * @return string The original string without any blanks at the beginning
	 */

	public static String removeStartBlank(String string) {

		while (string.startsWith(" ")) {
			string = string.substring(string.indexOf(" ") + 1);
		}

		return string;
	}

	/**
	 * This function finds out of how many element (either seperated by blanks
	 * or sourrounded by brackets) the string consists
	 * 
	 * @param string
	 *            The string where one want to know of how many elements it
	 *            consists
	 * @return quantity The amount of found elements
	 */

	public static int howManyElements(String string) {

		int quantity = 0;

		if (string == (null) || string.equals("")) { // check dass String net
														// leer ist
			Error("String darf nicht leer/undefiniert sein! -> return-Wert = -1");
			quantity = -1;
			string = "";
		} else {
			string = string + "  ";
		}

		if (string.startsWith(" ")) { // sichergehen dass String net mit
										// Leerzeichen anfï¿½ngt
			string = removeStartBlank(string);
		}

		while (string.indexOf(" ") > 0) {

			if (string.startsWith("[")) { // Element das in eckiger Klammer
											// steht

				string = removeBetweenBracketElements(string);

				if (string == null) {
					Error("\"[]\" nicht paarig! -> return-Wert = -2");
					quantity = -2;
					break;
				}

			} else {
				if (string.startsWith("(")) { // Element in runden Klammern
					if (string.indexOf(")") > 0) { // sichergehen dass Klammer
													// auch geschlossen wird
						String test1 = string
								.substring(string.indexOf(")") + 1);
						int test2 = test1.indexOf(":");
						int test3 = test1.indexOf(" ");
						int test4 = test1.indexOf("?");

						if (test2 < test3 && test2 != -1) { // wenn nach Klammer
															// zu noch ein
															// doppelpunkt kommt
															// wird der acuh
															// noch mitgenommen
							string = string.substring(string.indexOf(":") + 1);
						} else {
							if (test4 < test3 && test4 != -1) { // evtl mit
																// Fragezeichen
																// dahinter
								string = string
										.substring(string.indexOf("?") + 1);
							} else {// Wenn net dann nur bis zur runden Klammer
									// zu
								string = string
										.substring(string.indexOf(")") + 1);
							}
						}
					} else {
						Error("\"()\" nicht paarig! -> return-Wert = -3");
						quantity = -3;
						break;
					}
				} else {
					if (string.indexOf(":") > 0
							&& string.indexOf(" ") > string.indexOf(":")) { // Elemente
																			// nach
																			// denen
																			// ein
																			// Doppelpunkt
																			// steht
						string = string.substring(string.indexOf(":") + 1);
					} else { // Elemente nach denen ein Leerzeichen steht
						string = string.substring(string.indexOf(" ") + 1);
					}
				}

			}

			string = removeStartBlank(string); // Anfangsleerzeichen entfernen,
												// um eine Endlosschleife zu
												// vermeiden

			quantity++;

		}

		return quantity;
	}

	/**
	 * This function will outline a custom Errormessage (NOTE: This is a private
	 * function
	 * 
	 * @param errormessage
	 *            The custom Errormessage
	 */

	private static void Error(String errormessage) {

		System.err.println("[ERROR:] " + errormessage);

		String[] aMessage = { errormessage };

		if (ArmACommands.ErrorMessages != null) {
			ArmACommands.ErrorMessages = meltArrays(ArmACommands.ErrorMessages,
					aMessage);
		} else {
			ArmACommands.ErrorMessages = aMessage;
		}

	}

	/**
	 * This function will return an Array with all the elements (seperated by
	 * blanks or sourrounded by brackets) split up to its own Array-value
	 * 
	 * @param string
	 *            The original string which should be splitted up
	 * @return Elements The Array which contains all the single elements of the
	 *         original string
	 */

	public static String[] getElements(String string) {

		string = removeStartBlank(string);
		string = string + "  ";

		int quantity = howManyElements(string);
		int count = 0;
		if (quantity < 0) {
			Error("String fehlerhaft! (siehe vorherige Error)");
			count = quantity + 5;
		}

		String[] Elements = new String[quantity];

		while (count < quantity) {

			String element;

			if (string.startsWith("(")) { // Elemente zwischen runden Klammern
				String test1 = string.substring(string.indexOf(")") + 1);
				int test2 = test1.indexOf(":");
				int test3 = test1.indexOf(" ");
				int test4 = test1.indexOf("?");

				if (test2 < test3 && test2 != -1) { // Evtl. mit nachfolgendem
													// Doppelpunkt
					element = string.substring(0, string.indexOf(":") + 1);
					string = string.substring(string.indexOf(":") + 1);
					Elements[count] = element;
					string = removeStartBlank(string);
				} else {
					if (test4 < test3 && test4 != -1) { // evtl mit Fragezeichen
						element = string.substring(0, string.indexOf("?") + 1);
						string = string.substring(string.indexOf("?") + 1);
						Elements[count] = element;
						string = removeStartBlank(string);
					} else {// sonst eben ohne
						element = string.substring(0, string.indexOf(")") + 1);
						string = string.substring(string.indexOf(")") + 1);
						Elements[count] = element;
						string = removeStartBlank(string);
					}
				}
			} else {
				if (string.startsWith("[")) { // Elemente zwischen eckigen
												// Klammern
					Elements[count] = getBracketElements(string);
					string = removeBetweenBracketElements(string);
					string = removeStartBlank(string);

				} else {
					if (string.indexOf(":") > 0
							&& string.indexOf(" ") > string.indexOf(":")) { // Elemente
																			// mit
																			// Doppelpunkt
																			// am
																			// Ende
						element = string.substring(0, string.indexOf(":") + 1);
						string = string.substring(string.indexOf(":") + 1);
						Elements[count] = element;
						string = removeStartBlank(string);
					} else { // Elemente mit Leerzeichen getrennt
						element = string.substring(0, string.indexOf(" "));
						string = string.substring(string.indexOf(" ") + 1);
						Elements[count] = element;
						string = removeStartBlank(string);
					}
				}
			}
			count++;
		}

		return Elements;
	}

	/**
	 * This function will print out the content of an Array (it will display the
	 * first Arraycontent then a ":" and then the rest of it)
	 * 
	 * @param Array
	 *            The Array which shall be outlined
	 */

	public static void printArray(String[] Array) {

		String command = Array[0];

		Array = getArrayContentFromTo(Array, 1, Array.length - 1);

		String content = ArrayToString(Array);

		System.out.println(command + " : " + content);
		System.out.println("");
	}

	/**
	 * This function will replace the variables standing in "Syntax" with the
	 * actual parameters standing "Parameter" ("Syntax" and "Parameter" are
	 * created locally)
	 * 
	 * @param Array
	 *            The Array which contains the elements of the Syntax and the
	 *            parameters
	 * @return Syntax The Array which contains the actual Syntax for the command
	 */

	public static String[] correctSyntax(String[] Array, String command) {

		Array = checkReturn(Array);

		int elements = Array.length;
		int count = 0;
		int count1 = 0;
		int pos = whichPosition(Array, "parameters:", true, true); // Fragt ab
																	// wie viele
																	// Elemente
																	// vor dem
																	// Element
																	// "Parameter"
																	// stehen

		String[] Syntax = new String[pos - 1]; // Array für die Syntax
		String[] Parameter = new String[elements - pos - 1];

		boolean alreadyFound = false; // Falls jemand einen Parameter syntax
										// oder parameters genannt hat

		while (pos > count) { // Schreibt die Syntax in Array "Syntax"
			String element = Array[count];

			if (element.indexOf("syntax:") >= 0 && !alreadyFound) { // Sorgt
																	// dafÃ¼r
																	// dass
																	// Syntax
				// nicht reingeschrieben
				// wird
				count1 = count1 - 1;
				alreadyFound = true;
			} else {
				Syntax[count1] = element;
			}
			count++;
			count1++;
		}

		alreadyFound = false;

		/*
		 * if (isIn(Syntax, "=", true)) { // Sorgt dafÃ¼r dass eine variable
		 * nicht // als Typ (z.B. "Number") sondern als TODO // "Variable" in
		 * syntax steht, um // Verwechslungen bei koriigieren der // syntax zu
		 * vermeiden Syntax = changeToVar(Syntax); }
		 */
		count1 = 0;

		while (count < elements) { // Array für die Parameter
			String element = Array[count];

			if (element.indexOf("parameters:") >= 0 && !alreadyFound) {
				count1 = count1 - 1;
				alreadyFound = true;
			} else {
				Parameter[count1] = element;
			}
			count++;
			count1++;
		}

		checkSpecialParameter(Syntax, Parameter, false); // check for optional
															// and TKOH-only
															// Parameters

		count = 0;

		for (String content : Syntax) { // Ãœberschreibt Platzhalter in der
										// Syntax mit eigentlichem Parameter
			if (content.equals(command)) {
				count++;
				continue; // sorgt dafür dass nicht versehentlich der Name des
							// commands der ja im Array an erster stelle sthet
							// mit einem Parameter überschrieben wird.
			}

			if (content.startsWith("[")) {
				String content1 = correctBrackets(content, Parameter);
				Syntax[count] = content1;
			} else {
				boolean con = isIn(Parameter, content + ":", true);

				if (con) {
					int position = whichPosition(Parameter, content + ":",
							true, true);

					String content1 = Parameter[position + 1];
					content1 = removeDots(content1);
					int nextPos = position + 2;

					if (Parameter.length > nextPos) {
						while (Parameter[nextPos].equals("or")) { // verarbeite
																	// optionale
																	// Parameter
							String alternativeParam = Parameter[nextPos + 1];
							content1 = content1 + "/" + alternativeParam;

							nextPos = nextPos + 2;
						}
					}
					Syntax[count] = content1;

				}
			}
			count++;
		}

		if (isIn(Syntax, "number", true)) {
			Syntax = replaceWith(Syntax, "number", "DOUBLE");
		}
		return Syntax;
	}

	/**
	 * This function will return the position of an element in an Array
	 * 
	 * @param Array
	 *            The Array which contains the element
	 * @param element
	 *            the element the function shall search for
	 * @param exactValue
	 *            Indicates if the function shall search for exactly this
	 *            element or just for a content which contains the element
	 * @param useErrorReport
	 *            indicates if the function shall give an Errormessage if it
	 *            doesn't find a viable input
	 * @return position The position of the element in the Array
	 */

	public static int whichPosition(String[] Array, String element,
			boolean exactValue, boolean useErrorReport) {

		int count = 0;
		int position = -1;
		int aLen = Array.length;

		while (count < aLen) {
			String content = Array[count];

			if (content.indexOf(element) >= 0 && !exactValue) {
				position = count;
				break;
			}
			if (content.equals(element) && exactValue) {
				position = count;
				break;
			}
			count++;
			// }
		}
		if (position == -1 && useErrorReport) {
			Error("Element " + "\"" + element + "\""
					+ " nicht gefunden! -> return-Wert = -1");
		}

		return position;
	}

	/**
	 * This function will return the position of an element in an Array
	 * 
	 * @param Array
	 *            The Array which contains the element
	 * @param element
	 *            the element the function shall search for
	 * @param exactValue
	 *            Indicates if the function shall search for exactly this
	 *            element or just for a content which contains the element
	 * @param useErrorReport
	 *            indicates if the function shall give an Errormessage if it
	 *            doesn't find a viable input
	 * @return position The position of the element in the Array
	 * @param giveMultiDimPos
	 *            le the function search for nested Arrays and their content and
	 *            therefor let it return a multidimensional position as a
	 *            decimal number where the number before the comma indicates the
	 *            position in the original array and the number aufter the comma
	 *            the position in the nested array
	 * @return The multidimensional position of the element in the Array
	 */

	public static double whichPosition(String[] Array, String element,
			boolean exactValue, boolean useErrorReport, boolean giveMultiDimPos) {

		double pos2 = -1;

		int pos1 = whichPosition(Array, element, false, useErrorReport);

		double finalPos = pos1 * 100 + 5; // +5 damit unterschieden werden kann,
											// ob das Element nicht als Array
											// steht oder obs das erste element
											// im Array ist

		if (pos1 >= 0) {
			String selectedElement = Array[pos1];
			if (selectedElement.startsWith("[")) {
				selectedElement = getBracketElements(selectedElement);
				selectedElement = selectedElement.substring(1,
						selectedElement.length() - 1); // Produziere String ohne
														// die eckigen Klammern

				selectedElement = removeDots(selectedElement, true);
				selectedElement = reduceSpaceBetween(selectedElement);

				String[] aSelectedElement = getElements(selectedElement);

				pos2 = whichPosition(aSelectedElement, element, exactValue,
						useErrorReport) * 100;

				while (pos2 >= 100) { // sorgt dafür, dass die position hinter
										// dem
										// Komma steht
					pos2 = pos2 / 10;
				}
			}
		}

		if (pos2 >= 0) {
			pos2 = pos2 - 5; // wenn es eine Position im Array ist, dann mache
								// die +5 von oben rückgängig

			finalPos = finalPos + pos2;
		}
		finalPos = finalPos / 100;

		return finalPos;

		/*
		 * Dass die Positionen erst mit 100 multipliziert werden hat den
		 * Hintergrund, dass Dezimalzahlen kleiner als 0im Binärsystem nicht
		 * richtig dargestellt werden können, wodurch es bei der Verrechnung
		 * dieser inakkuraten Zahlen zu seltsamen Rundungsfehlern kommen kann.
		 * Wenn die Zahlen zum Verrechnen immer größer, als 0 sind, passieren
		 * diese Fehler nicht, da die Zahlen auch im Binärsystem korrekt
		 * dargestellt werden können.
		 */
	}

	/**
	 * This function will check if an Array contains a specific string
	 * 
	 * @param Array
	 *            The Array which will be searched
	 * @param element
	 *            The element that will be searched
	 * @param exactValue
	 *            indicates if the function shall search for exactly this
	 *            element or just for a content that conatins this element
	 * @return match The result of the search (true/false)
	 */

	public static boolean isIn(String[] Array, String element,
			boolean exactValue) { // Testet ob element in Array enthalten ist

		Array = removeNullElements(Array); // entfernt null-elemente aus "Array"
											// (z.B. bei "addAction")

		boolean match = false;

		if (exactValue) {
			for (int i = 0; i < Array.length; i++) {
				String content = Array[i];

				if (content.startsWith("[")) { // Verarbeite Wörter in klammern

					if (content.equals(element)) {
						match = true;
						break;
					}

					content = getBetweenBracketElements(content);

					if (content.equals(element)) {
						match = true;
						break; // Wenn das Element so gefunden wurde, ist es
								// kein einzelnes ArrayElement, das gesucht wird
					} else { // wenn es sich um einen Array handelt,dann
								// überprüfe die einzelnen ArrayInhalte
						content = removeDots(content, true, true); // Entferne
																	// alle
																	// Punkte
						// und ähnliches aus dem
						// Element
						content = reduceSpaceBetween(content);

						String[] aElements = getElements(content);
						match = isIn(aElements, element, exactValue);
					}
				} else {
					if (content.equals(element)) {
						match = true;
						break;
					}
				}
			}
		} else {
			for (String content : Array) {
				if (content.indexOf(element) >= 0) {
					match = true;
					break;
				}
			}
		}
		return match;
	}

	/**
	 * This unction will remove signs like ". , : _ '" from a string as long as
	 * these signs aren't included in brackets
	 * 
	 * @param string
	 *            The string where these signs shall be removed
	 * @return string The string without these signs except for those between
	 *         brackets
	 */

	public static String removeDots(String string, boolean seperateWithBlank,
			String[] ignoreElements) {

		String[] Dots = new String[5];
		Dots[0] = ".";
		Dots[1] = ",";
		Dots[2] = ":";
		Dots[3] = "_";
		Dots[4] = "'";

		for (String ignore : ignoreElements) { // Entferne Elemente, die
												// ignoriert werden sollen
			if (isIn(Dots, ignore, true)) {
				int iPos = whichPosition(Dots, ignore, true, true);
				Dots[iPos] = null;
				Dots = removeNullElements(Dots);
			}
		}

		String[] toTest = getElements(string); // packt string als einzelne
												// Elemente in Array "toTest"

		int count = 0;

		for (String test : toTest) {
			boolean proceed = true;

			if (test.startsWith("(")) {
				proceed = false;
			}
			if (proceed && test.startsWith("[")) {
				proceed = false;
			}

			if (proceed) { // Ãœberspringt diesen Part, wenn Klammern entdeckt
							// wurden
				for (String dot : Dots) {
					while (test.indexOf(dot) >= 0) { // Solange dot in test
														// enthalten ist, wird
														// dot aus test entfernt
						if (seperateWithBlank) {
							test = removeString(test, dot, true);
							toTest[count] = test;
						} else {
							test = removeString(test, dot);
							toTest[count] = test;
						}
					}
				}
			}
			count++;
		}

		string = ArrayToString(toTest);
		return string;
	}

	public static String removeDots(String string, boolean seperateWithBlank) {
		String[] ignore = {};
		String result = removeDots(string, seperateWithBlank, ignore);
		return result;
	}

	public static String removeDots(String string) {
		String result = removeDots(string, false);

		return result;
	}

	public static String removeDots(String string, boolean seperateWithBlank,
			boolean shouldIgnore) {
		String[] ignore = { "_" };
		if (string.indexOf(".") >= 0 && string.indexOf("..") < 0) {
			String[] ignore2 = { "." };
			ignore = meltArrays(ignore, ignore2);
		}

		String result = removeDots(string, seperateWithBlank, ignore);

		return result;
	}

	/**
	 * This function replaces a content of an String-Array with another string
	 * 
	 * @param Array
	 *            The Array where one element shall be replaced
	 * @param replace
	 *            The content that shall be replaced
	 * @param with
	 *            The string that shall replace the "replace"
	 * @return Array The original Array with the one element changed
	 */

	public static String[] replaceWith(String[] Array, String replace,
			String with) {
		if (isIn(Array, replace, true)) {
			double exactPos = whichPosition(Array, replace, true, true, true);
			int roughPos = (int) exactPos;

			if (roughPos * 100 + 5 == round(exactPos * 100, 1)) { // normal
																	// exchange
																	// -> normal
																	// proceed
				Array[roughPos] = with;
			} else { // the element which shall be exchanged is in an inner
						// Array -> filter that array and just replace this
						// element inside the inner array
				String contentArray = Array[roughPos]; // =der Array, in dem das
														// Element ausgetauscht
														// werden soll
				contentArray = contentArray.substring(1,
						contentArray.indexOf("]")); // wähle nur den Teil
													// zwischen den Klammern aus

				contentArray = removeDots(contentArray, true); // removes all
																// the dots
																// bewtween the
																// elements
				contentArray = reduceSpaceBetween(contentArray); // check that
																	// htere
																	// aren't
																	// multiple
																	// blanks
																	// between
																	// the
																	// elements

				String[] arrayContent = getElements(contentArray);

				int inArrayPos = (int) ((exactPos - roughPos) * 10); // the
																		// position
																		// the
																		// element
																		// stands
																		// in
																		// the
																		// inner
																		// array

				arrayContent[inArrayPos] = with; // replace the element

				contentArray = "[" + ArrayToString(arrayContent) + "]"; // convert
																		// the
																		// "array"
																		// to a
																		// string
																		// again

				contentArray = seperateElements(contentArray); // seperate the
																// elements by
																// comma again

				Array[roughPos] = contentArray;
			}

		}
		return Array;
	}

	/**
	 * Gets the actual command, because sometimes the command has some extra
	 * information in it (which this function will remove)
	 * 
	 * @param Array
	 *            The Array with the Syntax
	 * @return Array The Array with the Syntax and the correct command
	 */

	public static String[] filterCommand(String[] Array) {

		String command = Array[0];

		for (String command1 : Array) {
			if (command1.equals(command) || command.indexOf(command1) >= 0) {
				if ((command.indexOf("array") >= 0)
						&& (command1.indexOf("array") >= 0)) {

					continue; // sorgt dafür, dass net ein command, der "Array"
								// enthält einfach mit "Array" überschrieben
								// wird nur weil das in der Syntax mal vorkommt.
				}
				Array[0] = command1;
			}
		}
		return Array;
	}

	/**
	 * This function will overwrite the element before "=" with the proper data
	 * Type except that the this element is "Array"
	 * 
	 * @param Array
	 *            The Array with the Syntax
	 * @return Array The Array with the Syntax and the right Variablenames
	 */

	public static String[] changeToVar(String[] Array) {

		int pos = whichPosition(Array, "=", true, false) - 1;

		if (pos >= 0) {
			if (!Array[pos].equals("array") && pos != -1) {
				Array[pos] = "Variable";
			}
		}

		return Array;
	}

	/**
	 * This function will write the command in the html-line in uppercase
	 * letters and the rest in lowercase letters
	 * 
	 * @param string
	 *            The html-line with the command
	 * @param command
	 *            The command that shall be highlighted
	 * @return string The html-line with the highlighted command-part
	 */

	public static String markCommand(String string, String command) {

		if (command.indexOf("_") >= 0 && !(string.indexOf(command) >= 0)) {
			command = command.substring(0, command.indexOf("_"));
		}

		int[] Position;
		int posA;
		int posB;

		String lowString = string.toLowerCase();
		String lowCommand = command.toLowerCase();

		if (lowString.indexOf("<b>" + lowCommand + "</b>") >= 0
				|| lowString.indexOf("<b>" + lowCommand + " </b>") >= 0
				|| lowString.indexOf("<b> " + lowCommand + "</b>") >= 0) {
			if (lowString.indexOf("<b>" + lowCommand + "</b>") >= 0) {
				Position = findPos(lowString, "<b>" + lowCommand + "</b>");
			} else {
				if (lowString.indexOf("<b> " + lowCommand + "</b>") >= 0) {
					Position = findPos(lowString, "<b> " + lowCommand + "</b>");
				} else {
					Position = findPos(lowString, "<b>" + lowCommand + " </b>");// evtl
																				// is
																				// ein
																				// Leerzeichen
																				// drin
				}
			}
			posA = Position[0];
			posB = Position[1];
		} else {
			if (lowString.indexOf("<strong class=\"selflink\">" + lowCommand
					+ "</strong>") >= 0
					|| lowString.indexOf("<strong class=\"selflink\">"
							+ lowCommand + " </strong>") >= 0
					|| lowString.indexOf("<strong class=\"selflink\"> "
							+ lowCommand + "</strong") >= 0) { // Manchmal ist
																// der command
				// auch
				// anders markiert
				if (lowString.indexOf("<strong class=\"selflink\">"
						+ lowCommand + "</strong>") >= 0) {
					Position = findPos(lowString, "<strong class=\"selflink\">"
							+ lowCommand + "</strong>");
				} else {
					if (lowString.indexOf("<strong class=\"selflink\"> "
							+ lowCommand + "</strong") >= 0) {
						Position = findPos(lowString,
								"<strong class=\"selflink\"> " + lowCommand
										+ "</strong");
					} else {
						Position = findPos(lowString,
								"<strong class=\"selflink\">" + lowCommand
										+ " </strong>");
					}
				}
				posA = Position[0];
				posB = Position[1];
			} else {
				Error("Command \"" + command
						+ "\" konnte nicht gefunden werden");
				posA = -1; // sorge dafür, dass das Programm unten
							// rausfliegt
				posB = -1;
			}
		}

		String fragment1 = string.substring(0, posA - 1).toLowerCase();
		String fragment2 = string.substring(posB).toLowerCase();

		String commandM = string.substring(posA, posB);
		commandM = commandM.toUpperCase();

		string = fragment1 + commandM + fragment2;

		return string;
	}

	/**
	 * This function will find the start- and endposition of an element inside
	 * of a string
	 * 
	 * @param string
	 *            The string which conatins the element
	 * @param element
	 *            the element the function shall search for
	 * @return Position An Array which contains the start- and endposition (in
	 *         that order)
	 */

	public static int[] findPos(String string, String element) {

		int posA;
		int posB;

		int[] Position = new int[2];

		if (string.indexOf(element) >= 0) {
			posA = string.indexOf(element);
			posB = string.indexOf(element) + element.length(); // positon hinter
																// element

			Position[0] = posA;
			Position[1] = posB;
		} else {
			Error("Element \"" + element
					+ "\" nicht gefunden -> return-Wert = null");
		}
		return Position;
	}

	/**
	 * This function will write the command in lowercase letters
	 * 
	 * @param Array
	 *            The Array with the Syntax
	 * @param command
	 *            The command that shall be unhighlighted
	 * @return Array The Array with the Syntax and the unmarked command
	 */

	public static String[] unmarkCommand(String[] Array, String command) {

		command = command.toUpperCase();

		if (command.indexOf("_") > 0 && !isIn(Array, command, false)) {
			command = command.substring(0, command.indexOf("_"));
		}

		int count = 0;

		for (String command1 : Array) {
			if (command1.equals(command)) {
				command = command.toLowerCase();
				Array[count] = command;
				break;
			}
			count++;
		}
		return Array;
	}

	/**
	 * This function will cut an Array from an element to the end
	 * 
	 * @param Array
	 *            The original Array
	 * @param cut
	 *            The element which indicates the cut
	 * @param exactValue
	 *            indicates if the Arraycontent which indicates the cut has to
	 *            be exactly "cut" or just has to contain "cut"
	 * @return Array1 The cut Array
	 */

	public static String[] cutFrom(String[] Array, String cut,
			boolean exactValue) {

		if (isIn(Array, cut, exactValue)) {
			int count = 0;

			for (String content : Array) {

				if (content.equals(cut) && exactValue) {
					break;
				}
				if (cut.indexOf(content) >= 0 && !exactValue) {
					break;
				}
				count++;
			}

			String[] Array1;

			Array1 = new String[count];

			for (int i = 0; i < count; i++) {
				Array1[i] = Array[i];
			}

			return Array1;
		} else {
			return Array; // Wenn "cut" nicht im Array enthalten ist, wird der
							// Originalarray zurückgeben
		}
	}

	/**
	 * This function will write an eelement in an string in upper- or lowercase
	 * letters
	 * 
	 * @param string
	 *            The original string
	 * @param element
	 *            The element that shall be modified
	 * @param letter
	 *            If "UP" the element will be changed to uppercase letters. If
	 *            "LOW" the element will be changed to lowercase letters
	 * @return string The original string with the modified element
	 */

	public static String turnInto(String string, String element, String letter) {

		int sPos = findPos(string, element)[0];
		int ePos = findPos(string, element)[1];

		String fragment1 = "";
		String fragment2 = "";

		if (letter.equals("UP")) {
			String element1 = string.substring(sPos, ePos);
			element1 = element1.toUpperCase();

			if (sPos > 0) {
				fragment1 = string.substring(0, sPos);
				fragment2 = string.substring(ePos);
			} else {
				fragment2 = string.substring(ePos); // Wenn sPos gleich Null
													// ist, braucht man nur
													// fragment2, da fragment1
													// leer wäre
			}

			string = fragment1 + element1 + fragment2;
		}

		if (letter.equals("LOW")) {
			String element1 = string.substring(sPos, ePos);
			element1 = element1.toLowerCase();

			if (sPos > 0) {
				fragment1 = string.substring(0, sPos);
				fragment2 = string.substring(ePos);
			} else {
				fragment2 = string.substring(ePos); // Wenn sPos gleich Null
													// ist, braucht man nur
													// fragment2, da fragment1
													// leer wäre
			}

			string = fragment1 + element1 + fragment2;
		}

		return string;
	}

	/**
	 * This function will get the Arraycontent from a position to the end
	 * 
	 * @param Array
	 *            The original Array
	 * @param pos
	 *            The position that indicates the content-collectiion
	 * @return Array2 An Array which consists of all the elements between "pos"
	 *         and the end of the original Array
	 */

	public static String[] getArrayContentFrom(String[] Array, int pos) {

		int ALen = Array.length;

		if (pos < 0) {
			Error("Angegebene Position ist kleiner als 0! -> return-Wert = null");
			return null; // Wenn nach einer Position kleiner null gesucht wird,
							// wird null zurückgegeben
		}

		int count = 0;

		if (pos > ALen) {
			Error("Angegebene Position übersteigt Arraylänge! -> return-Wert = null");
			return null; // Wenn nach einer Position gesucht wird, die nicht
							// mehr im Array enthalten ist, wird null
							// zurückgegeben
		}

		String[] Array2 = new String[ALen - pos];

		while (pos < ALen) { // "<" weil die Lï¿½ngenabfrage bei Arrays bei 1
								// und nicht bei 0 anfï¿½ngt zu zï¿½hlen
			Array2[count] = Array[pos];
			count++;
			pos++;
		}
		return Array2;
	}

	/**
	 * This function will correct an alternative Syntax (a second or even a
	 * third/fourth/etc.)
	 * 
	 * @param Array
	 *            the Array with the syntax in it
	 * @return Syntax An Array with the alternative Syntax
	 */

	public static String[] correctAlternativeSyntax(String[] Array,
			String command) {

		int pos = whichPosition(Array, "syntax", false, true);
		// int posP;
		//
		// if (isIn(Array, "Parameter", false)) {
		// posP = whichPosition(Array, "Parameter", false);
		// if (pos > posP) { //wenn "Syntax" hinter "Parameter" steht
		// pos = whichPosition(Array, "Parameter", false) + 1; //Wenn die erste
		// Syntax Parameter enthï¿½lt, dann wird Array2 erst nach "Parameter"
		// von der ersten Syntax genommen
		// }
		// }

		String[] Syntax = new String[5];
		int count = 0;
		while (count < Syntax.length) {
			Syntax[count] = "Leer";
			count++;
		}

		String[] Array2 = getArrayContentFrom(Array, pos);

		int pos1 = whichPosition(Array2, "Syntax:", true, true);

		Array2 = getArrayContentFrom(Array2, pos1);

		String[] Test = getArrayContentFrom(Array2, pos1 + 1);

		if (isIn(Test, "syntax:", true && isIn(Test, "alternative", true))) {
			Array2 = cutFrom(Array2, "alternative", true);
			String[] Syntax2 = correctAlternativeSyntax(Test, command);
			String syntax = ArrayToString(Syntax2);
			Syntax[0] = syntax;
		}

		if (isIn(Array2, "parameter", false)) { // Verarbeiten einer
												// alternativen Syntax mit
												// Parametern
			String[] Syntax2 = correctSyntax(Array2, command);
			String syntax = ArrayToString(Syntax2);
			int count2 = 0;
			while (count2 < Syntax.length) {
				if (Syntax[count2].equals("Leer")) { // Schreibt syntax nur an
														// eine leere
														// Arraystelle
					Syntax[count2] = syntax;
					break;
				}
				count2++;
			}
		} else { // Verarbeiten einer Syntax ohne Parameter
			Array2 = checkReturn(Array2);
			Array2 = cutFrom(Array2, "return", false);
			Array2 = getArrayContentFrom(Array2, 1);
			Array2 = changeToVar(Array2);
			String syntax = ArrayToString(Array2);
			int count2 = 0;
			while (count2 < Syntax.length) {
				if (Syntax[count].equals("Leer")) { // Schreibt syntax nur an
													// eine leere Arraystelle
					Syntax[count] = syntax;
					break;
				}
				count++;
			}
		}

		Syntax = cutFrom(Syntax, "Leer", true);

		return Syntax;
	}

	/**
	 * This function will convert a String-Array into a string
	 * 
	 * @param Array
	 *            The Array that shall be transformed
	 * @return string The string with the Arraycontent
	 */

	public static String ArrayToString(String[] Array) {

		String string = "";

		if (Array.length == 1 | Array.length == 0) {
			if (Array.length == 1) {
				string = Array[0];
				string = removeStartBlank(string);
			}
		} else {
			int count = 0;

			for (String content : Array) {
				boolean proceed = true;
				if (content.equals("") | content == null) {
					proceed = false;
					count = count - 1; // Damit kein unnÃ¶tiges Leerzeichen
										// eingefÃ¼gt wird falls der Array nur
										// aus einem sinnvollen Inhalt hat
				}
				if (proceed) {
					content = removeStartBlank(content);

					if (count == 0) {
						string = content;
					} else {
						string = string + " " + content;
					}
				}
				count++;
			}
		}

		return string;
	}

	/**
	 * This function will recognize if the Parameter of the form [para1, para2,
	 * ...] are listed seperately in the Parameters or if it's just an Array
	 * 
	 * @param string
	 *            The element with brackets, that might be an Array
	 * @param Parameter
	 *            The Parameter Array
	 * @return string The result of the research (either every single element
	 *         replaced or replaced as a whole as an Array)
	 */

	public static String correctBrackets(String string, String[] Parameter) {

		boolean hasMatched = false; // stellt, fest ob ein Paramter in der
									// Klammer identifiziert werden konnte
		int count = 0;

		String elements = getBracketElements(string);
		elements = elements.substring(1, elements.length() - 1); // erstelle
																	// String
																	// ohne die
																	// eckigen
																	// KLammern

		if (elements.indexOf("[") >= 0) { // wenn da drin nochmal elemente in
											// eckigen Klammern stehen, dann jag
											// den Teil nochmal durch die
											// Funktion
			String part1 = elements.substring(0, elements.indexOf("["));
			String correctPart = elements.substring(elements.indexOf("["));
			correctPart = getBracketElements(correctPart);
			String part2 = elements.substring(elements.indexOf("]") + 1);

			boolean willMatch = false;
			String toTest = correctPart + ":";
			if (isIn(Parameter, toTest, true)) {
				willMatch = true;
			}

			if (correctPart.indexOf("[") >= 0 && correctPart.indexOf("]") >= 0) {
				String before = correctPart;
				correctPart = correctBrackets(correctPart, Parameter);

				if (!before.equals(correctPart)) {
					willMatch = false;
				}
			}

			if (willMatch) {
				correctPart = toTest.substring(0, toTest.length());
			}

			elements = part1 + correctPart + part2;
		}

		elements = removeDots(elements, true, true);

		elements = reduceSpaceBetween(elements);

		String[] aElements = getElements(elements);

		for (String content : aElements) {
			content = content + ":";
			boolean match = isIn(Parameter, content, true);

			if (!match) {
				String FirstLetterCapital = changeFirstLetter(content);
				match = isIn(Parameter, FirstLetterCapital, true);

				if (match) {
					content = FirstLetterCapital;
				}
			}

			if (match) { // ï¿½berschreibe die Platzhalter mit den Parametern
				int pos3 = whichPosition(Parameter, content, true, true) + 1;
				String content1 = Parameter[pos3];

				if (pos3 < Parameter.length) { // wenn der Array erst gar net so
												// lang is, dann steht da au net
												// "or"
					int nextPos = pos3 + 1;
					while (nextPos < Parameter.length
							&& Parameter[nextPos].equals("or")) { // Findet
						// alternative
						// Parameter
						// heraus
						String alternativeParam = Parameter[nextPos + 1];
						content1 = content1 + "/" + alternativeParam;

						nextPos = nextPos + 2;
					}
				}

				aElements[count] = content1;

				hasMatched = true; // zeigt, an dass mindestens ein parameter in
									// der KLammer ersetzt wird und es sich
									// nicht um einen beliebigen Array handelt
			}
			count++;
		}

		aElements = replaceWith(aElements, "number", "DOUBLE");

		while (isIn(aElements, "[Array]", false)) {
			aElements[whichPosition(aElements, "[Array]", false, true)] = "Array";
		}

		string = "[" + ArrayToString(aElements) + "]";

		string = seperateElements(string);

		if (!hasMatched) {
			string = "Array"; // Wenn kein parameter identifiziert werden
								// konntem, handeklt es sich einfach um einen
								// beliebigen Array
		}

		return string;
	}

	/**
	 * This Function will get the Arraycontent between two positions
	 * 
	 * @param Array
	 *            The Array which contains the content the function shall
	 *            collect
	 * @param startPos
	 *            The position, where extracting shall start
	 * @param endPos
	 *            The position, where extracting shall end
	 * @return ArrayR The Array with the wanted content
	 */

	public static String[] getArrayContentFromTo(String[] Array, int startPos,
			int endPos) {

		if (startPos < 0) {
			Error("startPos darf nicht kleiner als 0 sein! -> return-Wert = null");
			return null;
		}

		if (endPos >= Array.length) {
			Error("endPos draf Arraylï¿½nge nichr ï¿½bersteigen! -> return-Wert = null");
			return null;
		}

		if (startPos > endPos) {
			Error("startPos darf nicht grï¿½ï¿½er als endPos sein! -> return-Wert = null");
			return null;
		}

		int count = startPos;
		int elements = endPos - startPos + 1;
		String[] ArrayR = new String[elements];

		while (count <= endPos) {
			String content = Array[count];
			int place = count - startPos;
			ArrayR[place] = content;
			count++;
		}
		return ArrayR;
	}

	/**
	 * This function will check if a variable declaration has got a
	 * return-value. If yes it will give the declaration an "=" with the proper
	 * Value
	 * 
	 * @param Array
	 *            The Array with the Syntax
	 * @return Array The Array with the Syntax and the corrected Variablename
	 */

	public static String[] checkReturn(String[] Array) {

		int pos = whichPosition(Array, "return", true, false);

		boolean proceed = true;

		while (proceed) {
			if (Array[pos + 1].equals("value:")) {
				pos = pos + 2;
				proceed = false;
			} else {
				pos = whichPosition(getArrayContentFrom(Array, pos + 1),
						"return", true, true) + pos + 1;
			}
		}

		if (pos > 2) { // wird nur ausgeführt, wenn es auch ein return-Wert
						// gibt
			String returnValue = Array[pos];
			returnValue = removeDots(returnValue);

			if (Array.length > pos + 3) {
				if (Array[pos + 2].equals("format")) {
					returnValue = Array[pos + 3];
				}
			}

			if (!returnValue.equals("nothing")) { // Geht sicher dass auch
													// ein richtiger
													// return-Wert vorhanden
													// ist
				String newSyntax;

				if (isIn(
						getArrayContentFromTo(Array, 0,
								whichPosition(Array, "parameters:", true, true)),
						"=", true)) { // check if the given Snytax already has
					// an return declaration
					String[] content1 = getArrayContentFromTo(Array, 0,
							whichPosition(Array, "syntax:", true, true));

					String[] content2 = getArrayContentFrom(Array,
							whichPosition(Array, "=", true, true) + 1);

					String sContent1 = ArrayToString(content1);
					String sContent2 = ArrayToString(content2);

					newSyntax = sContent1 + " " + returnValue + " = "
							+ sContent2;
					Array = getElements(newSyntax);
				} else {
					String[] content1 = getArrayContentFromTo(Array, 0,
							whichPosition(Array, "syntax:", true, true)); // get
																			// the
																			// content
																			// from
																			// beginning
																			// up
																			// to
																			// "Syntax:"
																			// ->
																			// up
																			// to
																			// where
																			// the
																			// actual
																			// syntax
																			// starts
					String[] content2 = getArrayContentFrom(Array,
							whichPosition(Array, "syntax:", true, false) + 1);

					String sContent1 = ArrayToString(content1);
					String sContent2 = ArrayToString(content2);

					newSyntax = sContent1 + " " + returnValue + " = "
							+ sContent2;
					Array = getElements(newSyntax);

				}
			}
		}
		return Array;
	}

	/**
	 * This function will process the html-Line sothat it can return the
	 * actualSyntax of the command
	 * 
	 * @param syntax
	 *            Thehtml-line containing the syntax and the parameters
	 * @return The processed syntax (parameters already replaced)
	 */

	public static String[] getSyntax(String[] syntax, String command) {
		int count = 0;
		for (String element : syntax) {
			element = reduceSpaceBetween(element);
			syntax[count] = element;
			count++;
		}

		String[] nSyntaxPart = null;
		String[] aSyntaxPart = null;

		syntax = checkHasParameters(syntax);

		if (containsAlternativeSyntax(syntax)) {
			String[] aSyntax = getAlternativeSyntaxPart(syntax);
			aSyntaxPart = getSyntax(aSyntax, command);

			syntax = getArrayContentFromTo(syntax, 0,
					whichPosition(syntax, "alternative", true, true) - 1); // entfernt
																			// den
																			// Teil
																			// mit
																			// der
																			// alternativen
																			// Syntax
																			// aus
																			// "syntax"
			syntax = checkHasParameters(syntax);
			syntax = checkReturn(syntax);
		}

		nSyntaxPart = checkReturn(syntax);

		nSyntaxPart = correctSyntax(nSyntaxPart, command);

		if (nSyntaxPart != null && aSyntaxPart != null) {
			String[] spacer = { "alternative:" };
			nSyntaxPart = meltArrays(nSyntaxPart, spacer); // schreibt
															// "Alternative:"
															// zwischen die
															// verschieden
															// Syntaxen damit
															// man die später
															// auseinanderhalten
															// kann.
			String[] completeSyntax = meltArrays(nSyntaxPart, aSyntaxPart);
			return completeSyntax;
		} else {
			if (aSyntaxPart == null) {
				return nSyntaxPart;
			} else {
				return aSyntaxPart;
			}
		}

		/*
		 * immer erst die alternative Syntax rauswursteln und dann durch die
		 * "correctSyntax"-Funktion jagen... es müsste in der alternativen
		 * Syntax noch das "Syntax:" enthalten sein, womit "correctSyntax"
		 * anfängt
		 * 
		 * Die Funktion soll einen Array mit allen möglichen Syntaxen
		 * zurückgeben, der dann im Hauptprogramm in seine Einzelteile zerlegt
		 * werden kann (die einzelnen Syntaxen)
		 * 
		 * PROBLEME: -markCommand findet noch n paar net -> log
		 * 
		 * - actionKeysNames hat noch ne 2. Alternative Syntax ->
		 * beicorrectAlternativeSyntax einbauen, dass die neue Syntax erst ab
		 * "Sytnax:" anfängt, dann fällt die Nummerierung raus
		 * 
		 * -mit Hilfe der log-Datei mal Fehler suchen und beheben
		 * 
		 * -paramCheck noch verfeinern (auch ob die richtigen Parameter
		 * eingesetzt wurden)
		 * 
		 * -attachTo hat noch alternative Parameter in der Klammer, die
		 * ignoriert werden
		 */
	}

	/**
	 * This function will check if the given Syntaxarray contains an alternative
	 * syntax
	 * 
	 * @param aSyntax
	 *            The syntaxarray
	 * @return if there is an alternative Syntax
	 */

	public static boolean containsAlternativeSyntax(String[] aSyntax) {
		aSyntax = getArrayContentFrom(aSyntax,
				whichPosition(aSyntax, "syntax:", true, true) + 1); // gets
																	// Acontent
																	// after the
																	// first
																	// "Syntax"

		if (isIn(aSyntax, "syntax:", true)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This function will return an Array which contains the alternative Syntax
	 * part of the given Array
	 * 
	 * @param aSyntax
	 *            The SyntaxArray
	 * @return The alternative Syntax part
	 */

	public static String[] getAlternativeSyntaxPart(String[] aSyntax) {
		aSyntax = getArrayContentFrom(aSyntax,
				whichPosition(aSyntax, "syntax:", true, true) + 1); // gets
																	// Acontent
																	// after the
																	// first
																	// "Syntax"
		aSyntax = getArrayContentFrom(aSyntax,
				whichPosition(aSyntax, "syntax", true, true) + 1); // gets
																	// Acontent
																	// after
																	// "Alternative Syntax"

		String[] Numbers = { "1", "2", "3", "4", "5" };

		if (isIn(Numbers, aSyntax[0], true)) {
			aSyntax = getArrayContentFrom(aSyntax, 1);
		}

		return aSyntax;
	}

	/**
	 * This functions melts two Arrays into one
	 * 
	 * @param array1
	 * @param array2
	 * @return An Array with the content of the given two Arrays
	 */

	public static String[] meltArrays(String[] array1, String[] array2) {
		if (array1 == null || array2 == null) { // Verarbeite, wenn ein Element
												// null ist
			if (array1 == null && array2 != null) {
				return array2;
			}
			if (array2 == null && array1 != null) {
				return array1;
			} else {
				Error("Beide zu verschmelzende Arrays sind null!");
			}
		}

		int elements = array1.length + array2.length;
		String[] newArray = new String[elements];

		int count = 0;

		for (String actualElement : array1) {
			newArray[count] = actualElement;
			count++;
		}

		for (String actualElement : array2) {
			newArray[count] = actualElement;
			count++;
		}

		return newArray;
	}

	/**
	 * This function checks if the given array contains the word "Parameters".
	 * If not it will write it in before the return value
	 * 
	 * @param array
	 *            The array which should be ckecked
	 * @return The Array with "Parameters" in it.
	 */

	public static String[] checkHasParameters(String[] array) {
		if (!isIn(array, "parameters", false)) {
			String[] retValue = getArrayContentFrom(array,
					whichPosition(array, "return", false, true));
			array = cutFrom(array, "return", false);
			String[] parArray = { "parameters:", "HatGarKeine" };
			array = meltArrays(array, parArray); // Schreibt sinnlosen
													// Parameterteil in
													// SyntaxArray rein damit
													// "correctSyntax" damit
													// umgehen kann
			array = meltArrays(array, retValue); // Sorgt dafür dass die
													// Parameter vor dem
													// returnWert im Array
													// stehen damit dieser
													// später nicht in der
													// Syntax steht
		}

		return array;
	}

	/**
	 * This function will seperate elements in a String with "," which are
	 * seperate by a space;
	 * 
	 * @param string
	 *            The string which elements should be sepereated
	 * @return The function returns the properly seperated string
	 */

	public static String seperateElements(String string) {
		int pos = string.indexOf(" "); // sucht die erste Position eines
										// Leerzeichens
		int formerPos = -5; // weist formerPos einen Wert zu, den pos niemals
							// erreichen wird, damit die while schleife nicht
							// beim ersten Durchlauf durch Nullpointer
							// rausfliegt

		while (pos >= 0 && !(formerPos == pos)) {
			String subString1 = string.substring(0, pos);
			String subString2 = string.substring(pos);

			if (!subString1.endsWith(",")) { // Nur, wenn die Elemente nicht
												// schon durch ein Leerzeichen
												// getrennt sind
				string = subString1 + "," + subString2;
			} else {
				string = subString1 + subString2;
			}

			formerPos = pos;
			pos = string.indexOf(" ", pos + 2); // +2 da der string um ein
												// Zeichen verlängert wurde,
												// sodass die Position des
												// Leerzeiche um eins nach
												// rechts gerückt wurde
		}

		return string;
	}

	/**
	 * This function will change the first letter of a string to an uppercase
	 * letter if it was a lowercase letter and vise versa
	 * 
	 * @param string
	 *            The string that should be changed
	 * @return The string with the first letter changed
	 */

	public static String changeFirstLetter(String string) {
		String firstLetter = string.substring(0, 1);
		String rest = string.substring(1);

		if (firstLetter.equals(firstLetter.toUpperCase())) { // wird ausgeführt,
																// wenn der
																// erste
																// Buchstabe ine
																// Großbuchstabe
																// ist
			firstLetter = firstLetter.toLowerCase();
		} else {
			firstLetter = firstLetter.toUpperCase();
		}

		string = firstLetter + rest;

		return string;
	}

	/**
	 * This function will remove all elements of an array that are null
	 * 
	 * @param array
	 *            The array that should be checked
	 * @return The array without any null-elements
	 */

	public static String[] removeNullElements(String[] array) {
		int count = 0;
		int prevPos = -1;

		for (String element : array) {
			if (element == null) {
				if (count == prevPos + 1) {
					array = getArrayContentFrom(array, count + 1); // Wenn schon
																	// das erste
																	// Element
																	// null ist,
																	// dann nimm
																	// nur den
																	// restlichen
																	// array
					prevPos = count - 1;
					count = count - 1;
				} else {
					String[] part1 = getArrayContentFromTo(array, 0, count - 1);
					String[] part2 = getArrayContentFrom(array, count + 1);

					array = meltArrays(part1, part2);
					count = count - 1; // Sonst passts nemme, wenn mehrere
										// null-elemente drin sind
				}
			}

			count++;
		}

		return array;
	}

	/**
	 * This function will check, if there are some optional commands which are
	 * marked as such or if there are some commands which aren't used in ArmA
	 * and which will be removed from Syntax
	 * 
	 * @param Syntax
	 *            The Syntax of the actual command
	 * @param Parameter
	 *            The Parameterarray for the goven command
	 * @param rekursiv
	 *            indicates if the functions call is recursive, SET TO FALSE IN
	 *            YOUR FUNCTION-CALL!!!
	 * @return returns the syntax with the correced raw syntax so that
	 *         "correctSyntax" can process it properly (optional commands are
	 *         changed directly in the Parameterarray so taht "correctsynatx"
	 *         will do it automatically correct)
	 */

	public static String[] checkSpecialParameter(String[] Syntax,
			String[] Parameter, boolean rekursiv) {

		if (isIn(Parameter, "(optional, tkoh only):", true)) {
			int paramPos = whichPosition(Parameter, "(optional, tkoh only)",
					false, false) - 1;

			Parameter[paramPos + 1] = "HierStandMalDassDesNurFürTKOHgilt";

			boolean found = false;

			while (!found) {
				String element = Parameter[paramPos];
				element = removeDots(element);
				if (isIn(Syntax, element, true)) {
					found = true; // beende Schleife beim nächsten Durchgang, da
									// das entsprechende Element gefunden wurde

					double syntaxPos = whichPosition(Syntax, element, true,
							false, true);
					int roughPosition = (int) syntaxPos;
					boolean stillThere = false;

					if ((roughPosition * 100 + 5) == round(syntaxPos * 100, 1)) {
						// Verbeite, ganz normal
						Syntax[roughPosition] = null; // entferne das
														// entsprechende element
														// aus Syntax

						Syntax = removeNullElements(Syntax);

						if (isIn(Parameter, "(optional, tkoh only):", true)) {
							stillThere = true;
						}

					} else {
						int inArrayPosition = (int) (syntaxPos * 10 - roughPosition * 10);
						String innerArrayContent = Syntax[roughPosition]; // nehme
																			// den
																			// Inhalt
																			// aus
																			// dem
																			// String,
																			// der
																			// einen
																			// Array
																			// darstellt

						innerArrayContent = innerArrayContent.substring(1,
								innerArrayContent.indexOf("]")); // entferne die
																	// eckigen
																	// klammern
																	// aus dem
																	// String

						String[] innerArray = getElements(innerArrayContent); // erstelle
																				// einen
																				// Array
																				// aus
																				// den
																				// einzelnen
																				// Wörtern

						innerArray = meltArrays(
								getArrayContentFromTo(innerArray, 0,
										inArrayPosition - 1),
								getArrayContentFrom(innerArray,
										inArrayPosition + 1)); // füge die
																// Arrays
																// zusammen,
																// sodass das
																// TKOH-Element
																// nicht mehr
																// drin steht

						if (isIn(Parameter, "(optional, tkoh only):", true)) {
							stillThere = true;
						} else { // wenn des nemme drin is, heißt das, dass der
									// Syntax-Array soweit fertig sein wird
									// undgerade das lertzte element bearbeitet
									// wird
							int xPos = innerArray.length - 1;
							String content = innerArray[xPos];

							int len = content.length();

							if (content.substring(len - 1).equals(",")) { // Wenn
																			// das
																			// letzte
																			// Zeichen
																			// dieser
																			// Variable
																			// ein
																			// Komma
																			// ist,
																			// dann
																			// entferne
																			// es
								content = content.substring(0, len - 1);
							}

							innerArray[xPos] = content;
						}

						innerArrayContent = "[" + ArrayToString(innerArray)
								+ "]"; // Mach aus dem Array wieder einen string
										// und füge die eckigen klammern wieder
										// hinzu

						Syntax[roughPosition] = innerArrayContent; // schreibe
																	// den Array
																	// (als
																	// String),
																	// ohne das
																	// entsprechende
																	// Element,
																	// wieder in
																	// Syntax
																	// rein
					}

					if (stillThere) {
						Syntax = checkSpecialParameter(Syntax, Parameter, true); // rekursiver
						// Aufruf,
						// falls
						// mehrere
						// spezielle
						// Parameter
						// enthalten
						// sind
					}
				}

				paramPos = paramPos - 1; // gehe ein Element in "Parameter"
				// zurück
				if (!found) {
					found = paramPos < 0;
				}
			}

			// muss aus Syntax rausgeschmissen werden
		}

		if (!rekursiv) { // führe das nur einaml aus, nicht bei jedem rekursiven
							// Aufruf

			if (isIn(Parameter, "optional", false)) {
				// muss als optional gekennzeichnet werden
				/*
				 * an die stelle an der (optional): steht einfach den Parameter
				 * in klammern mit Fragezeichen schreiben, sodass der dann von
				 * correctsyntax genau so reingschrieben wird in Syntax
				 * 
				 * Außerdem an den parameterNamen noch nen Doppelpunkt dran
				 * hängen, damit correctsyntax den au als parameter erkennt
				 */

				boolean found = true;
				boolean directMatch = false;

				if (isIn(Parameter, "(optional):", true)) {
					directMatch = true;
				}

				while (found) {
					int pos = whichPosition(Parameter, "optional", false, true);

					Parameter[pos] = "HierStandMalDassDesFreiwilligWäre";

					int paramPos = pos - 1;

					String element = Parameter[paramPos];
					element = removeDots(element);

					while (!isIn(Syntax, element, true) && paramPos >= 0) {
						paramPos = paramPos - 1;
						element = Parameter[paramPos];

						element = removeDots(element, false, true);
					}

					if (isIn(Syntax, element, true) && paramPos >= 0) {

						if (!element.endsWith(":")) { // hänge ggf noch einen
														// Doppelpunkt an den
														// Parameter dran, damit
														// correctSyntax das
														// element auch
														// verwerten kann
							element = element + ":";
							Parameter[paramPos] = element;
						}

						// Der adder dient zur Neutralisation des Unterschiedes
						// bei einem directMatch und einem indirectMatch
						int adder = 0;

						if (!directMatch && paramPos == pos - 1) {
							// it's a direct match when the optional keyword
							// stands after the parameter name
							directMatch = true;
						}

						if (directMatch) {
							adder = 1;
						}

						String paramElement = Parameter[paramPos + 1 + adder];

						String nextElement = Parameter[paramPos + 2 + adder];
						int nextPos = paramPos + 2 + adder;

						while (nextElement.equals("or")) {
							String nextParam = Parameter[nextPos + 1];

							paramElement = paramElement + "/" + nextParam; // schreibe
																			// die
																			// amdere
																			// Syntax
																			// auch
																			// mit
																			// rein
							nextPos = nextPos + 2;
							nextElement = Parameter[nextPos];
						}

						paramElement = "(" + paramElement + ")?"; // kennzeichnen
																	// als
																	// optional

						Parameter[paramPos + 1] = paramElement; // setze die
																// Syntax an
						// die stelle, an der
						// correctsyntax das
						// ganze auch abliest

					} else {
						Error("Der Command, der als optional gekennzeichnet wurde, konnte in Syntax nicht gefunden werden!");
						break;
					}

					if (!isIn(Parameter, "optional", false)) {
						found = false; // beende die schleife beim nächsten
										// durchgang, da keine weiteren
										// optionalen parameter vorhanden sind
					}
				}
			}
		}

		return Syntax;
	}

	/**
	 * This function will reduce multiple blanks between different words in a
	 * string to only one
	 * 
	 * @param element
	 *            Teh element that contains too many blanks
	 * @return The string with only one blank between each word/element
	 */

	public static String reduceSpaceBetween(String element) {
		int pos = element.indexOf(" ");

		if (pos >= 0) {
			String sub1;
			String sub2 = element.substring(pos + 1);
			boolean doubledSpace = sub2.startsWith(" ");

			while (doubledSpace) {
				sub1 = element.substring(0, pos + 1);
				sub2 = element.substring(pos + 2);

				sub2 = removeStartBlank(sub2);

				if (sub2.equals("")) {
					doubledSpace = false;
					element = sub1;
				} else {
					pos = sub2.indexOf(" ");

					doubledSpace = sub2.substring(pos + 1).startsWith(" ");

					element = sub1 + sub2;

					pos = pos + sub1.length();
				}
			}
		}

		return element;
	}

	/**
	 * This function will round a double to a specific amount of positions after
	 * comma.
	 * 
	 * @param figure
	 *            The number which shall be rounded
	 * @param positions
	 *            The amount of positions the number shall be rounded at
	 * @return The rounded number limited to the amount of positions after the
	 *         comma
	 */

	public static double round(double figure, int positions) {
		String sFigure = String.valueOf(figure);

		int pos = sFigure.indexOf(".");

		if (pos > 0) {
			String sCommaDigits = sFigure.substring(pos + 1); // enthält die
																// Nachkommastellen
			String sPreCommaDigits = sFigure.substring(0, pos + 1); // enthält
																	// die
																	// Zahlen
																	// vor dem
																	// Kommaund
																	// das Komma
																	// an sich

			if (sCommaDigits.length() < positions + 1) { // erweitere die Zahl
															// ggf, damit die
															// Funktion ne Zahl
															// hinter der zu
															// Rundenden hat, um
															// zu überprüfen, ob
															// gerundet werden
															// muss
				sCommaDigits = sCommaDigits + "1";
			}

			char spot = sCommaDigits.charAt(positions); // findet die Stelle,
														// die fürs Runden
														// wichtig ist
			String sNum = String.valueOf(spot);
			int num = Integer.parseInt(sNum);

			sCommaDigits = sCommaDigits.substring(0, positions); // schneidet
																	// den
																	// String
																	// auf die
																	// entsprechenden
																	// Nachkommastellen
																	// zu

			if (num >= 5) { // mache die letzte Nachkommastelle eins größer
				char cValue = sCommaDigits.charAt(positions - 1);
				String sValue = String.valueOf(cValue);
				int value = Integer.parseInt(sValue);
				value = value + 1;
				sValue = String.valueOf(value);

				int count = 0;

				while (value >= 10) { // finde heraus, ob das Runden auch andere
										// Stellen betrifft
					value = value / 10;

					count++;
				}
				if (positions - 1 - count < 0) { // Runden betrifft die
													// Vorkommastellen
					sCommaDigits = "0"; // Wenn dei Vorlommastellen gerundet
										// werden, gibt es keine
										// Nachkommastellen mehr

					char cVal = sPreCommaDigits
							.charAt(sPreCommaDigits.length() - 2);
					String sVal = String.valueOf(cVal);
					int val = Integer.parseInt(sVal) + 1;

					int c = 1;

					boolean proceed = true;

					while (val == 10) { // welche Stelle ist betroffen?
						val = val / 10;

						if (sPreCommaDigits.length() - 2 - c < 0) { // wenn die
																	// Zahl um
																	// eine
																	// Stelle
																	// vergrößert
																	// werden
																	// würde,
																	// dan
																	// fliegt
																	// der
																	// "else"-Teil
																	// raus
							proceed = false;
							break;
						} else { // welche Stelle muss gerundet werden und
									// welcehn wert hat sie dann?
							cVal = sPreCommaDigits.charAt(sPreCommaDigits
									.length() - 2 - c);
							sVal = String.valueOf(cVal);
							val = Integer.parseInt(sVal) + 1;
						}

						c++;
					}

					sVal = String.valueOf(val);

					c = c - 1;

					if (proceed) { // verarbeite, wenn die Zahl nicht um eine
									// Stelle vergrößert wird
						int pos2 = sPreCommaDigits.length() - 2 - c;
						sPreCommaDigits = sPreCommaDigits.substring(0, pos2); // der
																				// Teil
																				// der
																				// noch
																				// vor
																				// der
																				// gerundeten
																				// Ziffer
																				// steht

						while (c > 0) {
							sVal = sVal + "0"; // Nullen ergänzen, sodass die
												// Zahl durchs Runden nicht an
												// Stellen verliert

							c = c - 1;
						}
					} else { // verlängere die ganze Zahl um eine Stelle
						c = sPreCommaDigits.length() - 1;
						sVal = String.valueOf((int) Math.pow(10, c));
						sPreCommaDigits = ""; // An diese Zahl wird nachher
												// nichts mehr vorne angehängt
					}

					sPreCommaDigits = sPreCommaDigits + sVal + ".";
				} else { // runden betrifft nur die Nachkommastellen
					sCommaDigits = sCommaDigits.substring(0, positions - 1
							- count)
							+ sValue;
				}
			}

			sCommaDigits = sPreCommaDigits + sCommaDigits; // setze den
															// ZahlenString zu
															// einer Dezimalzahl
															// zusammen

			figure = Double.parseDouble(sCommaDigits); // Konvertiere den String
														// der Zahl wieder in
														// eine richtige Zahl
		}

		return figure;
	}

	/**
	 * This functions will return the first part of a string that is sourrounded
	 * by "[]". It also supports multi Dimensional "Arrays"
	 * 
	 * @param string
	 *            The string woth the sourrounded element
	 * @param disrupt
	 *            Indicates if the method should be disrupted and return a
	 *            string with the inner "]" replaced with "§"
	 * @return The sourrounded element (with it's brackets)
	 */

	public static String getBracketElements(String string, boolean disrupt) {
		if (string.startsWith("[")) {
			boolean proceed = false;

			if (string.substring(string.indexOf("]") + 1).indexOf("]") >= 0
					&& string.substring(1).indexOf("[") < string.indexOf("]")) { // Wenn
																					// ein
																					// Multidimensionaler
																					// Array
																					// vorliegt
				proceed = true;
			}

			while (proceed) {// ersetze "]" durch "§"
				int pos = string.indexOf("]");
				if (string.endsWith("]")) {
					string = string + " ";
				}
				String fragment1 = string.substring(0, pos);
				String fragment2 = string.substring(pos + 1);
				string = fragment1 + "§" + fragment2;

				int qOpen = howOften(string.substring(0, pos + 1), "[");
				int qClose = howOften(string.substring(0, pos + 1), "§");

				if (qOpen == qClose) {
					proceed = false;
					String workStr = new StringBuilder(string).reverse()
							.toString();
					fragment1 = workStr.substring(0, workStr.indexOf("§"));
					workStr = fragment1 + "]"
							+ workStr.substring(workStr.indexOf("§") + 1);
					string = new StringBuilder(workStr).reverse().toString();
				}
			}

			if (disrupt) {
				return string; // gebe den string mit den "§" anstatt "]" zurück
			}
			if (string.indexOf("]") > 0) {
				if (string.substring(string.indexOf("]") + 1).startsWith(":")) { // Wenn
																					// der
																					// Array
																					// von
																					// einem
																					// Doppelpunkt
																					// abgeschlossen
																					// wird,
																					// dann
																					// nimm
																					// den
																					// auch
																					// noch
																					// mit
					string = string.substring(0, string.indexOf("]") + 2);
				} else {
					string = string.substring(0, string.indexOf("]") + 1); // ansonsten
																			// eben
																			// nur
																			// bis
																			// "]"
				}
			} else {
				Error("\"[]\" nicht paarig!");
				return null;
			}

			while (string.indexOf("§") >= 0) { // ersetze "§" wieder durch "]"
				String fragment1 = string.substring(0, string.indexOf("§"));
				String fragment2 = string.substring(string.indexOf("§") + 1);
				string = fragment1 + "]" + fragment2;
			}
		} else {
			Error("String startet nicht mit \"[\"!");
		}

		return string;
	}

	public static String getBracketElements(String string) {
		string = getBracketElements(string, false);
		return string;
	}

	/**
	 * This function will remove the elements from a string that are sourrounded
	 * by "[]".
	 * 
	 * @param string
	 *            The string with the sourrounded element
	 * @return The string wothout the sourrounded element
	 */

	public static String removeBetweenBracketElements(String string) {
		if (string.startsWith("[")) {
			boolean proceed = false;

			if (string.substring(string.indexOf("]") + 1).indexOf("]") >= 0
					&& string.substring(1).indexOf("[") < string.indexOf("]")) { // Wenn
																					// ein
																					// Multidimensionaler
																					// Array
																					// vorliegt
				proceed = true;
			}

			while (proceed) {// ersetze "[" durch "§"
				int pos = string.indexOf("]");
				String fragment1 = string.substring(0, pos);
				String fragment2 = string.substring(pos + 1);
				string = fragment1 + "§" + fragment2;

				int test1 = fragment2.indexOf("[");
				int qOpen = howOften(string.substring(0, pos + 1), "[");
				int qClose = howOften(string.substring(0, pos + 1), "§");

				if (((qOpen - 1) <= qClose || test1 < 0)
						&& !(fragment2.indexOf("[") > 0 && fragment2
								.indexOf("[") < fragment2.indexOf("]"))) {
					proceed = false;
				}
			}
			if (string.indexOf("]") > 0) {
				if (string.substring(string.indexOf("]") + 1).startsWith(":")) { // Wenn
																					// der
																					// Array
																					// von
																					// einem
																					// Doppelpunkt
																					// abgeschlossen
																					// wird,
																					// dann
																					// nimm
																					// den
																					// auch
																					// noch
																					// mit
					string = string.substring(string.indexOf("]") + 2);
				} else {
					string = string.substring(string.indexOf("]") + 1); // ansonsten
																		// eben
																		// nur
																		// bis
																		// "]"
				}
			} else {
				Error("\"[]\" nicht paarig!");
				return null;
			}

		} else {
			Error("String startet nicht mit \"[\"!");
		}

		return string;
	}

	/**
	 * This function will get the content bewtween brackets. Multdimensional
	 * Arrays are supported
	 * 
	 * @param string
	 *            The string with the brackets
	 * @return The content between these brackets
	 */

	public static String getBetweenBracketElements(String string) {
		string = getBracketElements(string, true); // ersetze die inneren "]"
													// mit "§"

		string = string.substring(1, string.indexOf("]")); // nimm den
															// Inhaltzwischen
															// den eckigen
															// Klammern

		while (string.indexOf("§") >= 0) { // ersetze "§" wieder durch "]"
			String fragment1 = string.substring(0, string.indexOf("§"));
			String fragment2 = string.substring(string.indexOf("§") + 1);
			string = fragment1 + "]" + fragment2;
		}

		string = removeStartBlank(string);

		return string;
	}

	/**
	 * This function will check if the parameters in the syntax have been
	 * overwritten correctly
	 * 
	 * @param oldSyntax
	 *            The unprocessed syntax
	 * @param newSyntax
	 *            the processed syntax
	 * @param command
	 *            the actual command
	 * @return Boolean if the processing was okay
	 * @throws NotProperlyProcessedException
	 *             If the parameter in the syntax is not processed properly this
	 *             eexception will be thrwon
	 */

	public static void checkIfProcessed(String[] oldSyntax, String[] newSyntax,
			String command) throws NotProperlyProcessedException {

		oldSyntax = checkHasParameters(oldSyntax); // geht sicher dass in
													// oldSyntax auch Parameter
													// stehen
		if (isIn(oldSyntax, "alternative", true)) {
			if (!isIn(newSyntax, "alternative", false)) {
				throw new NotProperlyProcessedException(command
						+ ": Alternative Syntax nicht beachtet");
			}

			if (whichPosition(newSyntax, "alternative", false, false) == 0) {
				newSyntax = getArrayContentFrom(newSyntax, 1);
			}

			String[] nextTestOld = getArrayContentFrom(oldSyntax,
					whichPosition(oldSyntax, "alternative", false, true) + 2);
			oldSyntax = getArrayContentFromTo(oldSyntax, 0,
					whichPosition(oldSyntax, "alternative", false, true) - 1);

			String[] nextTestNew = getArrayContentFrom(newSyntax,
					whichPosition(newSyntax, "alternative", false, true));
			newSyntax = getArrayContentFromTo(newSyntax, 0,
					whichPosition(newSyntax, "alternative", false, true) - 1);

			checkIfProcessed(nextTestOld, nextTestNew, command); // check the
																	// alternative
																	// Part
		}

		String[] originalOldSyntax = oldSyntax;
		String[] originalNewSyntax = newSyntax;

		String[] checkSyntax = new String[newSyntax.length - 1];
		for (int i = 0; i < newSyntax.length; i++) {
			if (i == 0) {
				continue;
			}

			String element = newSyntax[i];
			element = element.toLowerCase();
			checkSyntax[i - 1] = element;
		}

		command = command.toLowerCase();

		if (command.indexOf("_") >= 0 && !(isIn(checkSyntax, command, false))) {
			command = command.substring(0, command.indexOf("_"));
		}

		int paraPos = whichPosition(oldSyntax, "parameters:", true, true) - 1; // wo
																				// fängt
																				// der
																				// ParameterTeil
																				// an?

		oldSyntax = getArrayContentFromTo(oldSyntax, 0, paraPos); // nehme nur
																	// den
																	// syntaxTeil

		for (int i = 0; i < oldSyntax.length; i++) { // schreibe alles in
			// Kleinbuchstaben
			String element = oldSyntax[i];
			oldSyntax[i] = element.toLowerCase();
		}

		for (int i = 0; i < newSyntax.length; i++) { // schreibe alles in
			// Kleinbuchstaben
			String element = newSyntax[i];
			newSyntax[i] = element.toLowerCase();
		}

		command = command.toLowerCase(); // Schreibe auch den command in
		// Kleinbuchstaben

		// nehme nur den Teil in dem die syntax steht
		if (isIn(oldSyntax, "=", true)) {
			int eqPos = whichPosition(oldSyntax, "=", true, false) + 1;
			oldSyntax = getArrayContentFrom(oldSyntax, eqPos);
		} else {
			int synPos = whichPosition(oldSyntax, "syntax:", true, false) + 1;
			oldSyntax = getArrayContentFrom(oldSyntax, synPos);
		}

		// das gleiche mit der korrigierten Syntax
		if (isIn(newSyntax, "=", true)) {
			int eqPos = whichPosition(newSyntax, "=", true, false) + 1;
			newSyntax = getArrayContentFrom(newSyntax, eqPos);
		} else {
			if (newSyntax[0].equals(command)) {
				newSyntax = getArrayContentFrom(newSyntax, 1);
			}
		}

		int comPosOld = whichPosition(oldSyntax, command, true, true);
		int comPosNew = whichPosition(newSyntax, command, true, true);

		newSyntax = getArrayContentFrom(newSyntax, comPosNew - comPosOld); // Wenn
																			// man
																			// die
																			// Strings
																			// untereinander
																			// schreibt,
																			// dann
																			// stehen
																			// die
																			// commands
																			// genau
																			// untereinander
																			// (und
																			// nicht
																			// verschoben)

		String[] correctParams = { ";", "teammember", "side", "object",
				"string", "waypoint", "anything", "location", "group", "code",
				"display", "control", "task", "config", "array", "position",
				"color", "double", "boolean", "namespace", "scripthandle" };

		// Wenn auch nur ein Element gleich ist, dann ist was beim
		// überschreiben der Parameter schief gegangen
		for (int i = 0; i < oldSyntax.length; i++) {
			String elementOld = oldSyntax[i];
			String elementNew = newSyntax[i];

			if (elementOld.startsWith("[")) {
				if (!elementNew.equals("array")) {
					String arrayElementsOld = getBetweenBracketElements(elementOld);
					arrayElementsOld = removeDots(arrayElementsOld, true);
					arrayElementsOld = reduceSpaceBetween(arrayElementsOld);
					String[] arrayOld = getElements(arrayElementsOld);

					String arrayElementsNew = getBetweenBracketElements(elementNew);
					arrayElementsNew = removeDots(arrayElementsNew, true);
					arrayElementsNew = reduceSpaceBetween(arrayElementsNew);
					String[] arrayNew = getElements(arrayElementsNew);

					/*
					 * while (isIn(arrayNew, "?", true)) { double exactQPos =
					 * whichPosition(arrayNew, "?", true, true, true); int qPos
					 * = (int) exactQPos;
					 * 
					 * if (round(exactQPos * 100, 1) == (qPos * 100 + 5)) {
					 * arrayNew[qPos] = null; arrayNew =
					 * removeNullElements(arrayNew); } else { int innerArrayPos
					 * = (int) ((exactQPos - qPos) * 10); String
					 * innerArrayContent = arrayNew[qPos] .substring(1,
					 * arrayNew[qPos].length()); innerArrayContent =
					 * removeDots(innerArrayContent); String[] arrayElements =
					 * getElements(innerArrayContent);
					 * 
					 * arrayElements[innerArrayPos] = null; arrayElements =
					 * removeNullElements(arrayElements);
					 * 
					 * innerArrayContent = ArrayToString(arrayElements);
					 * 
					 * arrayNew[qPos] = innerArrayContent; } }
					 */

					int lNew = arrayNew.length;

					for (int j = 0; j < lNew; j++) {
						String oldElement = arrayOld[j];
						String newElement = arrayNew[j];

						if (oldElement.equals(newElement)
								&& !isIn(correctParams, newElement, true)) {
							throw new NotProperlyProcessedException(
									command
											+ ": ArrayParameter nicht richtig überschrieben!");
						}
					}

				}
			} else {
				if (elementOld.equals(elementNew)
						&& !isIn(correctParams, elementOld, false)
						&& !(elementOld.equals(command) && elementNew
								.equals(command))) { // check dass
					// die
					// parameter
					// richtig
					// überschriben
					// wurden
					throw new NotProperlyProcessedException(
							command
									+ " wurde nicht richtig verarbeitet!\r\nParameter nicht richtig überschrieben!");
				}
			}
		}

		int retPos = whichPosition(originalOldSyntax, "return", true, true);
		boolean proceed = true;

		while (proceed) {
			if (originalOldSyntax[retPos + 1].equals("value:")) {
				retPos = retPos + 2;
				proceed = false;
			} else {
				retPos = whichPosition(
						getArrayContentFrom(originalOldSyntax, retPos + 1),
						"return", true, true) + retPos + 1;
			}
		}
		String content = originalOldSyntax[retPos];

		if (!content.equals("nothing")) {
			if (!isIn(originalNewSyntax, "=", true)) {
				// wenn es einen ReturnWert gibt, dann muss uach ein "=" in
				// der Syntax sein
				throw new NotProperlyProcessedException(
						command
								+ " wurde nicht richtig verarbeitet!\r\nReturnWert nicht beachtet!");
			}
		} else {
			if (isIn(originalNewSyntax, "=", true)) {
				// wenn es keinen returnWert gibt, dann darf auch kein "="
				// in der Syntax sein
				throw new NotProperlyProcessedException(
						command
								+ ": wurde nicht richtig verarbeitet!\r\nFälschlicherweise returnWert eingebaut!");
			}

			if (isIn(originalOldSyntax, "optional", false)) { // checke ob alle
																// optionalen
																// Parameter
																// auch
																// alssolche
																// gekennzeichnet
																// wurden
				String testStringOld = ArrayToString(originalOldSyntax);
				int qOld = howOften(testStringOld, "optional");

				String testStringNew = ArrayToString(newSyntax);
				int qNew = howOften(testStringNew, ")?");

				if (qOld != qNew) {
					throw new NotProperlyProcessedException(command
							+ ": optionale Parameter unstimmig!");
				}
			}
		}
		for (String element : newSyntax) {
			if (element.indexOf("-") >= 0) {
				System.out.println();
			}
		}

		if (isIn(newSyntax, "-", false)) {
			ArmACommands.NoParameter++;
			throw new NotProperlyProcessedException("No parameters given!");
		}

		while (isIn(newSyntax, ")", false)) {
			int pos = whichPosition(newSyntax, ")", false, false);
			int compPos = whichPosition(newSyntax, ")?", false, false);

			if (pos != compPos
					&& newSyntax[pos].toLowerCase().indexOf("(handle)") < 0) {
				throw new NotProperlyProcessedException(
						"invalid closure bracket ')'");
			}

			String element = newSyntax[pos];

			String fragment1 = element.substring(0, element.indexOf(")"));
			String fragment2 = "";

			if (element.indexOf(")") + 2 < element.length()) {
				fragment2 = element.substring(element.indexOf(")") + 2);
			}
			element = fragment1 + fragment2;

			newSyntax[pos] = element;
		}
	}

	/**
	 * This function will count how often a character exists in the given string
	 * 
	 * @param string
	 *            The string that shall be checked
	 * @param character
	 *            The character to search for
	 * @return The quantity of the cahracter in the string
	 */

	public static int howOften(String string, String character) {
		int quantity = 0;

		if (string.indexOf(character) >= 0) {
			while (string.indexOf(character) >= 0) {
				int pos = string.indexOf(character);

				String partA = string.substring(0, pos);
				String partB = string.substring(pos + 1);

				string = partA + partB;
				quantity++;
			}
		}

		return quantity;
	}

}