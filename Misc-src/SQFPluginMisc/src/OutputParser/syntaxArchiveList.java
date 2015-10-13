package OutputParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class syntaxArchiveList {
	private ArrayList<syntaxArchive> list;

	public syntaxArchiveList() {
		list = new ArrayList<syntaxArchive>();

	}

	/**
	 * Adds an archive to this list
	 * 
	 * @param synArchive
	 *            The archive to add
	 */
	public void add(syntaxArchive synArchive) {
		list.add(synArchive);
	}

	/**
	 * Sorts all the syntaxes of the different archives regarding the
	 * syntaxVariants
	 */
	public void sort() {
		for (syntaxArchive current : list) {
			current.sort();
		}
	}

	/**
	 * @return The size fo this archive
	 */
	public int size() {
		return this.list.size();
	}

	/**
	 * @param index
	 * @return The syntaxArchive at this index
	 */
	public syntaxArchive get(int index) {
		return this.list.get(index);
	}

	/**
	 * This functions generates a grammar out of the stored syntaxes and writes
	 * it into the given file
	 * 
	 * @param outputDirectory
	 *            Where the grammar should be stored
	 * @param modelDirectory
	 *            The directory for any rule-models that should be injected into
	 *            the grammar
	 */
	public void write(String outputDirectory, String modelDirectory) {
		File grammarFile = new File(outputDirectory);
		try {
			BufferedReader grammarReader = new BufferedReader(new FileReader(
					grammarFile));
			String currentLine;
			String grammarContent = "";

			while ((currentLine = grammarReader.readLine()) != null) {
				if (currentLine.indexOf("////////Generated////////") >= 0) {
					while (grammarContent.endsWith("\n")) {
						// prevent too many newlines
						grammarContent = grammarContent.substring(0,
								grammarContent.length() - 1);
					}

					// skip previous generated block
					break;
				}
				grammarContent += currentLine + "\n";
			}
			grammarReader.close();

			grammarContent += "\n\n\n////////////////Generated////////////////\n";

			// write commands in suitable rules

			Grammar grammar = new Grammar(grammarContent);

			System.out
					.println("\nCreating ParserRules for the syntaxVariants...");

			for (syntaxArchive currentArchive : this.list) {
				String name = currentArchive.getName();

				if (name.equals("NoneReturner")) {
					name = "Commands";
				}

				ParserRule rule = new ParserRule(name);
				rule.create();
				rule.checkForModel(modelDirectory);

				for (syntaxVariant currentVariant : currentArchive.getArchive()
						.getArchive()) {
					rule.addSyntaxVariant(currentVariant);
				}

				grammar.addRule(rule);
			}

			System.out.println("Finished creating ParserRules\n");

			/*
			 * grammar.sort();
			 * 
			 * System.out.println(grammar);
			 */

			grammar.sort();
			//grammar.simplify();

			System.out.println("\nAppending anything rule\n");

			grammar.appendAnythingRule();

			System.out.println("\nStarting creating ruleForecasts...");

			grammar.createStartRuleForecasts();
			grammar.createRuleForecast();

			// System.out.println(grammar.getRule("NumberAtomic") + "\n\n\n");

			System.out.println("Finished ruleForecasts\n");

			System.out.println("\nDealing with recursion...");

			if (grammar.isLeftRecursive()) {
				System.out.println("\tDealing with left-recursion");

				grammar.removeLeftRecursion();

				System.out.println("\tRecreating ruleForecasts...");

				grammar.createStartRuleForecasts();
				grammar.createRuleForecast();

				System.out
						.println("\tFinished recreation of ruleForecasts\n\n");
			}

			// System.out.println("\n" + grammar.needsLeftFactoring_I() + "\n");

			System.out.println("\tLeft-factoring grammar");

			int counter = 0;

			while (grammar.needsLeftFactoring()) {
				if (counter > 20) {
					// prevent endless loop
					System.err
							.println("Failed at left-factoring the grammar (>20 iterations)!!!");
					break;
				}

				if (grammar.needsLeftFactoring_I()) {
					grammar.leftFactor_I();
				}

				if (grammar.needsLeftFacoring_II()) {
					// TODO: implement
				}

				System.out.println("\tRecreating ruleForecasts...");

				grammar.createStartRuleForecasts();
				grammar.createRuleForecast();

				System.out.println("\tFinished recreation of ruleForecasts\n");

				counter++;
			}

			// System.out.println("\n" + grammar.needsLeftFacoring_II() + "\n");

			// System.out.println(grammar.getRule("NumberAtomic"));

			System.out.println("Finished dealing with recursion\n");

			/*
			 * grammarContent = grammar.toString();
			 * 
			 * System.out.println(grammarContent);
			 */

			System.out
					.println("\nChecking for empty rules and invalid ruleCalls...");

			grammar.removeEmptyRules();
			grammar.removeNonExistingRuleCalls();

			System.out
					.println("Finished check for empty rules and invalid ruleCalls\n");

			System.out.println("\nSorting grammar\n");

			grammar.sort();
			
			System.out.println(grammar);

			if (!grammar.isLeftRecursive() && !grammar.needsLeftFactoring()) {
				System.out.println("\nCreating Assignments...");

				grammar.createAssignments();

				System.out.println("Assignments created\n");

				grammarContent = grammar.toString();

				// System.out.println(grammarContent);

				System.out.println("\nWriting grammar in file " + grammarFile);

				FileWriter grammarWriter = new FileWriter(grammarFile);

				grammarWriter.write(grammarContent);

				grammarWriter.close();

				System.out.println("Finished writing grammar");
			} else {
				if (grammar.isLeftRecursive()) {
					System.err
							.println("\nGrammar is still left-recursive -> Hasn't been written in file!\n");
				}

				if (grammar.needsLeftFactoring()) {
					System.err
							.println("\nGrammar still needs to be left factored -> Hasn't been written in file!\n");
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * formats all archives
	 */
	public void format() {
		for (syntaxArchive current : this.list) {
			current.format();
		}
	}

}
