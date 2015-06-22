package SQF;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import Exceptions.NotProperlyProcessedException;

public class FixErrors {

	public static void main(String[] args) {
		try {
			boolean net = false;

			String sBaseURL = "https://community.bistudio.com";
			String inputLine;
			String inputFilePath = "ArmACommandsNET.log";
			String localBaseURL = "NotGiven";

			for (String argument : args) {
				if (argument.startsWith("/f=")) {
					argument = argument.substring(3);
					inputFilePath = argument;
				}
				if (argument.startsWith("/n=")) {
					argument = argument.substring(3);
					net = argument.equals("true");
				}
				if(argument.startsWith("/bu=") || argument.startsWith("/bU=")) {
					argument = argument.substring(4);
					localBaseURL = argument;
				}
			}

			if (!net) {
				sBaseURL = localBaseURL;
			}

			File inputFile = new File(inputFilePath);
			FileReader inputFileReader = new FileReader(inputFile);
			BufferedReader inputReader = new BufferedReader(inputFileReader);

			while ((inputLine = inputReader.readLine()) != null) {

				// try {

				int pos;

				// Search for hint...
				// System.out.println(match);
				if (((pos = inputLine.indexOf("href=\"")) >= 0)) { // fängt
																	// erst
																	// an,
																	// wenn
																	// auch
																	// die
																	// Verlinkungen
																	// auf
																	// die
																	// brauchbaren
																	// Commands
																	// anfangen
																	// (wenn
																	// match
																	// =
																	// true)
					String sCommandURL;
					int len = inputLine.substring(pos + 6).indexOf('"');
					sCommandURL = inputLine.substring(pos + 6, pos + 6 + len);
					if (!net) {
						sCommandURL = sCommandURL + ".html";
					}
					System.out.println(sCommandURL);

					String[] controlStructures = { "/wiki/catch.html",
							"/wiki/exitWith.html", "/wiki/from.html",
							"/wiki/if.html", "/wiki/step.html",
							"/wiki/switch.html" };

					if (Functions.isIn(controlStructures, sCommandURL, true)) {
						System.out.println("ControlStructure \n");
						continue;
					}

					String command = sCommandURL.substring(6);

					if (!net) {
						command = command.substring(0, command.length() - 5);
					}
					URL commandURL = new URL(sBaseURL + sCommandURL);

					BufferedReader inC = new BufferedReader(
							new InputStreamReader(commandURL.openStream()));
					String inputLineC;

					boolean syntaxMatch = false;
					boolean examplesMatch = false;
					String inputLineA = "";

					while (!examplesMatch
							&& (inputLineC = inC.readLine()) != null) {
						syntaxMatch |= inputLineC.indexOf("<dt>Syntax:</dt>") >= 0;

						if (inputLineC
								.indexOf("href=\"/wiki/Category:Introduced_with_Take_On_Helicopters_version") >= 0 && !(inputLineC.indexOf("href=\"/wiki/Category:Introduced_with_Arma") >= 0)) {
							System.out.println("Nur für TKOH \n");
							break; // Wenn der command nur für TKOH existiert,
									// dann lass ihn in Frieden und gehe zum
									// nächsten
						}

						if (syntaxMatch) {
							examplesMatch = inputLineC.indexOf("Examples") >= 0;
							if (examplesMatch) {
								// Abschnitt gefunden.
								// Alle Infos stehen in inputLineA.
								// Verarbeite Informationen ...
								inputLineA = Functions.markCommand(inputLineA,
										command); // schreibt
													// den
													// command
													// in
													// Groï¿½buchstaben,
													// damits
													// spï¿½ter
													// keine
													// Verwechslungen
													// gibt
								inputLineA = Functions.removeFromTo(inputLineA,
										"<", ">"); // entfernt
													// alles
													// zwichen
													// den
													// eckigen
													// Klammern
													// (Html-Teile)

								inputLineA = inputLineA.replaceAll(
										"structured *text", "structuredtext"); // Ersetzt
																				// alle
																				// einzelnen
																				// "Structured"
																				// und
																				// "Text",
																				// die
																				// mit
																				// Leerzeichen
																				// getrennt
																				// sind
																				// mit
																				// "StructuredText"
								inputLineA = inputLineA.replaceAll("number", "NUMBER");
								inputLineA = inputLineA.replaceAll(
										"structuredtext", "STRING");
								inputLineA = inputLineA.replaceAll(" *:", ":");
								inputLineA = inputLineA.replaceAll("string",
										"STRING");
								inputLineA = inputLineA
										.replaceAll("&#...;", ""); // entferne
																	// html-tags
								inputLineA = inputLineA.replaceAll("number", "DOUBLE");
								inputLineA = inputLineA.replaceAll("boolean", "BOOLEAN");
								inputLineA = inputLineA.replaceAll("position", "Position");
								
								while(inputLineA.indexOf("(handle)") >= 0) {
									inputLineA += " ";
									String fragment1 = inputLineA.substring(0, inputLineA.indexOf("(handle)"));
									String fragment2 = inputLineA.substring(inputLineA.indexOf("(handle)") + 8);
									
									inputLineA = fragment1 + "Handle" + fragment2;
								}
								
//								inputLineA = inputLine.replaceAll("color", "Color");
//								inputLineA = inputLineA.replaceAll("team *member", "teammember");
//								inputLineA = inputLineA.replaceAll("teammember", "TeamMember");
//								inputLineA = inputLineA.replaceAll("control", "Control");
//								inputLineA = inputLineA.replaceAll("side", "Side");
//								inputLineA = inputLineA.replaceAll("object", "Object");
//								inputLineA = inputLineA.replaceAll("waypoint", "Waypoint");
//								inputLineA = inputLineA.replaceAll("anything", "Anything");
//								inputLineA = inputLineA.replaceAll("location", "Location");
//								inputLineA = inputLineA.replaceAll("group", "Group");
//								inputLineA = inputLineA.replaceAll("code", "Code");
//								inputLineA = inputLineA.replaceAll("display", "Display");
//								inputLineA = inputLineA.replaceAll("task", "Task");
//								inputLineA = inputLineA.replaceAll("config", "Config");

								inputLineA = command + " " + inputLineA;
								inputLineA = Functions.reduceSpaceBetween(inputLineA);
								inputLineA = Functions.ArrayToString(Functions.checkHasParameters(Functions.getElements(inputLineA)));
								System.out.println(inputLineA);

								String toCheck = inputLineA.toLowerCase();
								toCheck = Functions.reduceSpaceBetween(toCheck);

								int synPos = toCheck.indexOf("syntax:");
								int paramPos = toCheck.indexOf("parameters:");
								int altPos = toCheck
										.indexOf("alternative syntax");
								boolean proceed = true;
								

								while (proceed) { //entferne Versionsangaben aus der Syntax
									String fragment1 = inputLineA.substring(0,
											synPos);
									String fragment2 = inputLineA
											.substring(paramPos + 11);
									String syntax = inputLineA.substring(
											synPos, paramPos + 11);
									while (syntax.indexOf("( ") >= 0) { // sorg
																		// dafür,
																		// dass
																		// der
																		// Klammerinhalt
																		// direkt
																		// nachder
																		// Klammer
																		// steht
										int pos1 = syntax.indexOf("( ");
										syntax = syntax.substring(0, pos1 + 1)
												+ syntax.substring(pos1 + 2);
									}
									
									String checkSyntax = syntax.toLowerCase();

									int check1 = checkSyntax.indexOf("(since");
									int check2 = checkSyntax
											.indexOf("(available");
									int check3 = checkSyntax
											.indexOf("(introduced");

									if (check1 != -1) {
										syntax = syntax.substring(0, check1)
												+ syntax.substring(syntax
														.indexOf("parameters:"));
									}
									if (check2 != -1) {
										syntax = syntax.substring(0, check2)
												+ syntax.substring(syntax
														.indexOf("parameters:"));
									}
									if (check3 != -1) {
										syntax = syntax.substring(0, check3)
												+ syntax.substring(syntax
														.indexOf("parameters:"));
									}

									inputLineA = fragment1 + syntax + fragment2;
									
									if (altPos == -1) {
										proceed = false;
									}else {
										toCheck = inputLineA.toLowerCase();
										
										synPos = toCheck.substring(altPos).indexOf("syntax:") + altPos;
										paramPos = toCheck.substring(altPos).indexOf("parameters:") + altPos;
										String toCheckPart = toCheck.substring(altPos + 10);
										int oldAltPos = altPos;
										altPos = toCheckPart.indexOf("alternative syntax");
										if (altPos != -1) {
											String leng = toCheck.substring(0, oldAltPos + 10);
											altPos = altPos + leng.length();
										}
									}
								}

								String[] Syntax1 = Functions
										.getElements(inputLineA); // String
																	// in
																	// einzelne
																	// Elemente
																	// aufteilen
								String[] oldSyntax = Syntax1;

								Syntax1 = Functions.getSyntax(Syntax1, command);

								Syntax1 = Functions.filterCommand(Syntax1); // schreibt
																			// an
																			// Anfang
																			// des
																			// Arrays
																			// den
																			// eigentlichen
																			// Befehl
																			// anstatt
																			// iwelchem
																			// kauderwelsch
																			// (z.B.
																			// hintC_Array)
								Syntax1 = Functions.unmarkCommand(Syntax1,
										command);
								Functions.printArray(Syntax1);

								Functions.checkIfProcessed(oldSyntax, Syntax1,
										command);
								// Informationen verarbeitet.
								// Verlasse lesen von Befehls.html
								break;
							} else {
								inputLineA += inputLineC;
							}

						}
					}
					inC.close();

				}
				/*
				 * } catch (Exception e) { e.printStackTrace(); }
				 */
			}
			inputReader.close();

		} catch (IOException e) {
			System.out.println(e);

		} catch (NotProperlyProcessedException e) {
			e.printStackTrace();
		}
	}
}
