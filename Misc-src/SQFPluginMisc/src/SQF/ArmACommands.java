package SQF;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

import Exceptions.NotProperlyProcessedException;

public class ArmACommands {

	public static String[] ErrorMessages;
	public static int NoParameter = 0;

	public static void main(String[] args) {
		boolean net = false; // indicates if the program shall process online or
								// a local file
		String logFilePath = "ArmACommands.log"; // the path where the logFile
													// shall be saved ->
													// default:
													// where the program is
													// running
		boolean save = false; // shall the program save the html-files?
		String saveFilePath = "";
		String commandSavePath = "";
		String sBaseURL = "";

		for (int i = 0; i < args.length; i++) { // verarbeite Argumente
			String argument = args[i];

			if (argument.startsWith("/")) {
				if (argument.startsWith("/n=")) { // local or online
					argument = argument.substring(3);
					net = argument.equals("true");
				}
				if (argument.startsWith("/f=")) { // where shall the logFile be
													// saved?
					argument = argument.substring(3);
					logFilePath = argument;
				}
				if (argument.startsWith("/s=")) { // save-mode on/off
					argument = argument.substring(3);
					save = argument.equals("true");
				}
				if (argument.startsWith("/sf=") || argument.startsWith("/sF=")) { // where
																					// shall
																					// it´be
																					// saved
					argument = argument.substring(4);
					saveFilePath = argument;
				}
				if (argument.startsWith("/cs=")) {
					argument = argument.substring(4);
					commandSavePath = argument;
				}
				if (argument.startsWith("/bu=") || argument.startsWith("/bU=")) {
					argument = argument.substring(4);
					sBaseURL = argument;
				}
			}
		}

		if (save && !net) {
			net = true; // setze net auf true, da sonst nicht gespeichert werden
						// kann
		}

		if (net) {
			if (save) {
				System.out.println("Save-Modus aktiviert!");
			}
			System.out.println("Suche online \n\n");
		} else {
			System.out.println("Suche lokal \n\n");
		}

		try {
			int add = 0;
			if (save) {
				add = 3000;
			}
			Thread.sleep(2000 + add);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		try {
			BufferedWriter saveWriter = null;
			URL startURL;
			BufferedReader in;
			String inputLine;
			int errorCount = 0;
			int totalCommands = 0;
			int properCommands = 0;

			File oldLog = new File(logFilePath + ".back");
			File log = new File(logFilePath);
			if (log.exists()) {
				// make backup
				if (oldLog.exists()) {
					oldLog.delete(); // Lösche, falls schon vorhanden
				}

				log.renameTo(oldLog); // create backup
			}

			FileOutputStream logFile = new FileOutputStream(logFilePath); // create
																			// logFile
			BufferedWriter logWriter = new BufferedWriter(
					new OutputStreamWriter(logFile));
			logWriter.write("LogFile:\r\n\r\n");
			logWriter.flush();

			if (net) {
				sBaseURL = "https://community.bistudio.com";
				startURL = new URL(sBaseURL
						+ "/wiki/Category:Scripting_Commands_Arma_3");
				in = new BufferedReader(new InputStreamReader(
						startURL.openStream()));
			} else {
				// TODO
				startURL = new URL(sBaseURL
						+ "/wiki/Category_Scripting_Commands_Arma_3.html");
				in = new BufferedReader(new InputStreamReader(
						startURL.openStream()));
			}

			boolean match = false;

			if (save) { // create saveFile
				File saveFile = new File(saveFilePath
						+ "/wiki/Category_Scripting_Commands_Arma_3.html");
				File parent = saveFile.getParentFile();
				if (!parent.exists()) {
					parent.mkdirs(); // erstelle Pfad, wenn noch nicht vorhanden
				}
				saveWriter = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(saveFile)));
			}

			FileOutputStream commandWriterStream = new FileOutputStream(
					commandSavePath); // create commandWriter
			BufferedWriter commandWriter = new BufferedWriter(
					new OutputStreamWriter(commandWriterStream));

			while ((inputLine = in.readLine()) != null) {
				// Process each line.

				if (save) {
					System.out.println(inputLine);

					saveWriter.write(inputLine);
					saveWriter.newLine();
					saveWriter.flush();

					if (inputLine
							.equals("<ul><li><a href=\"/wiki/abs\" title=\"abs\">abs</a></li>")) {
						match = true;
					}

					if (inputLine.equals("</tr></table></div>")) {
						match = false;
					}

					if (match && inputLine.indexOf("href=\"") >= 0) {
						try {
							int pos = inputLine.indexOf("href=");
							String commandLink = inputLine.substring(pos + 6);
							commandLink = commandLink.substring(0,
									commandLink.indexOf("\""));

							File commandFile = new File(saveFilePath
									+ commandLink + ".html");
							File parent = commandFile.getParentFile();
							if (!parent.exists()) {
								parent.mkdirs(); // erstelle Pfad, wenn noch
													// nicht vorhanden
							}

							URL commandURL = new URL(sBaseURL + commandLink);
							BufferedReader inC = new BufferedReader(
									new InputStreamReader(
											commandURL.openStream()));

							String commandFileLine;
							// create a writer to write the website's html in
							// the corresponding subdirectory
							BufferedWriter websiteContentWriter = new BufferedWriter(
									new FileWriter(commandFile));
							while ((commandFileLine = inC.readLine()) != null) {
								websiteContentWriter.write(commandFileLine);
								websiteContentWriter.newLine();
							}
							websiteContentWriter.close();
						} catch (Exception sE) {
							System.err.println(sE);
							// log Exception
							logWriter.write("saveLog:");
							logWriter.newLine();
							logWriter.write(sE.toString());
							logWriter.newLine();
							logWriter.newLine();
							logWriter.flush();
						}

					}
				} else {

					int pos;

					if (inputLine
							.equals("<ul><li><a href=\"/wiki/abs\" title=\"abs\">abs</a></li>")) {
						match = true;
					}
					if (inputLine
							.equals("<ul><li><a href=\"abs.html\" title=\"abs\">abs</a></li>")
							&& !net) {
						match = true;
					}
					if (inputLine.equals("</tr></table></div>") && match) { // setze
																			// match
																			// am
																			// ende
																			// der
																			// commandListe
																			// wieder
																			// auf
																			// false
						match = false;
					}
					// Search for hint...
					// System.out.println(match);
					if (((pos = inputLine.indexOf("href=\"")) >= 0) && match) { // fängt
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
						int len;
						String command;
						URL commandURL;

						// System.out.println(inputLine);

						if (net) {
							len = inputLine.substring(pos + 6).indexOf('"');
							sCommandURL = inputLine.substring(pos + 6, pos + 6
									+ len);
							System.out.println(sCommandURL);
							command = sCommandURL.substring(6);
							commandURL = new URL(sBaseURL + sCommandURL);
						} else {
							len = inputLine.substring(pos + 6).indexOf('"');
							sCommandURL = inputLine.substring(pos + 6, pos + 6
									+ len);
							System.out.println(sCommandURL);
							command = sCommandURL.substring(6);
							commandURL = new URL(sBaseURL + sCommandURL
									+ ".html");
						}

						String[] controlStructuresHtml = { "/wiki/catch.html",
								"/wiki/exitWith.html", "/wiki/from.html",
								"/wiki/if.html", "/wiki/step.html",
								"/wiki/switch.html", "/wiki/and.html",
								"/wiki/call.html", "/wiki/or.html",
								"/wiki/then.html", "/wiki/to.html",
								"/wiki/while.html", "/wiki/case.html",
								"/wiki/do.html", "/wiki/with.html",
								"/wiki/else.html", "/wiki/forEach.html",
								"/wiki/try.html" };
						String[] controlStructures = { "/wiki/catch",
								"/wiki/exitWith", "/wiki/from", "/wiki/if",
								"/wiki/step", "/wiki/switch", "/wiki/and",
								"/wiki/call", "/wiki/or", "/wiki/then",
								"/wiki/to", "/wiki/while", "/wiki/case",
								"/wiki/do", "/wiki/with", "/wiki/else",
								"/wiki/forEach", "/wiki/try" };

						if (Functions
								.isIn(controlStructures, sCommandURL, true)
								|| Functions.isIn(controlStructuresHtml,
										sCommandURL, true)) { // controlStructures
																// have to be
																// implemented
																// serperately
																// (manually)
							System.out.println("ControlStructure \n");
							continue;
						}

						totalCommands++;

						try {
							BufferedReader inC = new BufferedReader(
									new InputStreamReader(
											commandURL.openStream()));
							String inputLineC;

							boolean syntaxMatch = false;
							boolean examplesMatch = false;
							String inputLineA = "";

							while (!examplesMatch
									&& (inputLineC = inC.readLine()) != null) {
								syntaxMatch |= inputLineC
										.indexOf("<dt>Syntax:</dt>") >= 0;

								if (inputLineC
										.indexOf("href=\"/wiki/Category:Introduced_with_Take_On_Helicopters_version") >= 0
										&& !(inputLineC
												.indexOf("href=\"/wiki/Category:Introduced_with_Arma") >= 0)) {
									System.out.println("Nur für TKOH \n");
									break; // Wenn der command nur für TKOH
											// existiert, dann lass ihn in
											// Frieden und gehe zum nächsten
								}

								if (inputLineC.toLowerCase().indexOf(
										"no longer available") >= 0) {
									System.out
											.println("No longer available \n");
									break;
								}

								if (inputLineC.toLowerCase().indexOf(
										"this command is completely broken") >= 0) {
									System.out.println("currently broken");
									break;
								}

								if (syntaxMatch) {
									examplesMatch = inputLineC
											.indexOf("Examples") >= 0;
									if (examplesMatch) {
										// Abschnitt gefunden.
										// Alle Infos stehen in inputLineA.
										// Verarbeite Informationen ...

										if (command.equals("lineIntersects")) {
											String dummy = "";
										}

										inputLineA = Functions.markCommand(
												inputLineA, command); // schreibt
																		// den
																		// command
																		// in
																		// Groï¿½buchstaben,
																		// damits
																		// spï¿½ter
																		// keine
																		// Verwechslungen
																		// gibt
										inputLineA = Functions.removeFromTo(
												inputLineA, "<", ">"); // entfernt
																		// alles
																		// zwichen
																		// den
																		// eckigen
																		// Klammern
																		// (Html-Teile)

										inputLineA = inputLineA.replaceAll(
												"structured *text",
												"structuredtext"); // Ersetzt
																	// alle
																	// einzelnen
																	// "Structured"
																	// und
																	// "Text",
																	// die mit
																	// Leerzeichen
																	// getrennt
																	// sind mit
																	// "StructuredText"
										inputLineA = inputLineA.replaceAll(
												"structuredtext", "STRING");
										inputLineA = inputLineA.replaceAll(
												" *:", ":");
										inputLineA = inputLineA.replaceAll(
												"string", "STRING");
										inputLineA = inputLineA.replaceAll(
												"&#...;", ""); // entferne
																// html-tags

										inputLineA = inputLineA.replaceAll(
												"number", "NUMBER");
										inputLineA = inputLineA.replaceAll(
												"boolean", "BOOLEAN");

										while (inputLineA.indexOf("(handle)") >= 0) {
											inputLineA += " ";
											String fragment1 = inputLineA
													.substring(
															0,
															inputLineA
																	.indexOf("(handle)"));
											String fragment2 = inputLineA
													.substring(inputLineA
															.indexOf("(handle)") + 8);

											inputLineA = fragment1 + "Handle"
													+ fragment2;
										}

										inputLineA = command + " " + inputLineA;
										inputLineA = Functions
												.reduceSpaceBetween(inputLineA);
										inputLineA = Functions
												.ArrayToString(Functions
														.checkHasParameters(Functions
																.getElements(inputLineA)));
										System.out.println(inputLineA);

										String toCheck = inputLineA
												.toLowerCase();
										toCheck = Functions
												.reduceSpaceBetween(toCheck);

										int synPos = toCheck.indexOf("syntax:");
										int paramPos = toCheck
												.indexOf("parameters:");
										int altPos = toCheck
												.indexOf("alternative syntax");
										boolean proceed = true;

										while (proceed) { // entferne
															// Versionsangaben
															// aus der Syntax
											String fragment1 = inputLineA
													.substring(0, synPos);
											String fragment2 = inputLineA
													.substring(paramPos + 11);
											String syntax = inputLineA
													.substring(synPos,
															paramPos + 11);
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
												syntax = syntax.substring(0,
														pos1 + 1)
														+ syntax.substring(pos1 + 2);
											}

											String checkSyntax = syntax
													.toLowerCase();

											int check1 = checkSyntax
													.indexOf("(since");
											int check2 = checkSyntax
													.indexOf("(available");
											int check3 = checkSyntax
													.indexOf("(introduced");
											int check4 = checkSyntax
													.indexOf("(arma");

											if (check1 != -1) {
												syntax = syntax.substring(0,
														check1)
														+ syntax.substring(syntax
																.indexOf("parameters:"));
											}
											if (check2 != -1) {
												syntax = syntax.substring(0,
														check2)
														+ syntax.substring(syntax
																.indexOf("parameters:"));
											}
											if (check3 != -1) {
												syntax = syntax.substring(0,
														check3)
														+ syntax.substring(syntax
																.indexOf("parameters:"));
											}

											if (check4 != -1) {
												syntax = syntax.substring(0,
														check4)
														+ syntax.substring(syntax
																.indexOf("parameters:"));
											}

											inputLineA = fragment1 + syntax
													+ fragment2;

											if (altPos == -1) {
												proceed = false;
											} else {
												toCheck = inputLineA
														.toLowerCase();

												synPos = toCheck.substring(
														altPos).indexOf(
														"syntax:")
														+ altPos;
												paramPos = toCheck.substring(
														altPos).indexOf(
														"parameters:")
														+ altPos;
												String toCheckPart = toCheck
														.substring(altPos + 10);
												int oldAltPos = altPos;
												altPos = toCheckPart
														.indexOf("alternative syntax");
												if (altPos != -1) {
													String leng = toCheck
															.substring(
																	0,
																	oldAltPos + 10);
													altPos = altPos
															+ leng.length();
												}
											}
										}

										String[] Syntax1 = Functions
												.getElements(inputLineA); // String
																			// in
																			// einzelne
																			// Elemente
																			// aufteilen

										String[] oldSyntax = Syntax1; // erstelle
																		// Kopie
																		// um
																		// später
																		// zu
																		// vergleichen

										// TODO: implement Syntax1 = Functions
										// .markRepeatingParameter(Syntax1); //
										// markiere
										// sich
										// wiederholende
										// Parameter,
										// für
										// checkSpecialParameter

										Syntax1 = Functions.getSyntax(Syntax1,
												command);

										Syntax1 = Functions
												.filterCommand(Syntax1); // schreibt
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
										Syntax1 = Functions.unmarkCommand(
												Syntax1, command);

										Functions.checkIfProcessed(oldSyntax,
												Syntax1, command); // check
																	// if
																	// everything
																	// went
																	// right

										if (Functions.isIn(Syntax1, "unknown",
												true)
												|| Functions.isIn(Syntax1,
														"Unknown", true)) {
											// might exist unknown
											// parameter/commands
											throw new NotProperlyProcessedException(
													"unknown parameter not viable!");
										}

										Functions.printArray(Syntax1);

										Syntax1[0] = command; // first element
																// is command
																// name -> makes
																// sure it's not
																// all in
																// lowercase
																// letters

										String output = Functions
												.ArrayToString(Syntax1);
										commandWriter.write(output);
										commandWriter.newLine();
										commandWriter.flush();

										properCommands++;
										// Informationen verarbeitet.
										// Verlasse lesen von Befehls.html
										break;
									} else {
										inputLineA += inputLineC;
									}

								}
							}
							inC.close();
						} catch (Exception e) {
							errorCount++;
							System.out.println(e);

							logWriter.write(command + "\r\n");
							logWriter.write(inputLine + "\r\n");

							if (ArmACommands.ErrorMessages != null) {
								for (int i = 0; i < ArmACommands.ErrorMessages.length; i++) {
									logWriter.write("Message: "
											+ ArmACommands.ErrorMessages[i]
											+ "\r\n");
								}
							}

							ArmACommands.ErrorMessages = null;

							logWriter.write("Exception: " + e.toString()
									+ "\r\n");

							StringBuilder sb = new StringBuilder();
							StackTraceElement[] stackTrace = e.getStackTrace();
							for (int i = 0; i < stackTrace.length; i++) {
								sb.append(stackTrace[i]);
								sb.append("\r\n");
							}
							logWriter.write("Stack: " + sb.toString());

							logWriter.newLine();

							logWriter.flush();
						}
					}
				}
			}
			in.close();
			logWriter.close();
			commandWriter.close();
			if (save) {
				saveWriter.close();
				System.out.println("Saving completed");
			} else {
				System.out.println("Errors: " + errorCount);
				System.out.println("No Parameter given: " + NoParameter);
				System.out.println("Properly: " + properCommands);
				System.out.println("Total: " + totalCommands);
			}

		} catch (MalformedURLException me) {
			System.out.println(me);

		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}// end main
}