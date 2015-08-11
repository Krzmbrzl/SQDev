package OutputParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
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

	// TODO: JavaDoc
	public void write(String outputDirectory, String modelDirectory) {
		File grammarFile = new File(outputDirectory);
		try {
			BufferedReader grammarReader = new BufferedReader(new FileReader(
					grammarFile));
			String currentLine;
			String grammarContent = "";

			while ((currentLine = grammarReader.readLine()) != null) {
				if (currentLine.indexOf("////////Generated////////") >= 0) {
					while(grammarContent.endsWith("\n")) {
						//prevent too many newlines
						grammarContent = grammarContent.substring(0, grammarContent.length() - 1);
					}
					
					// skip previous generated block
					break;
				}
				grammarContent += currentLine + "\n";
			}
			grammarReader.close();

			grammarContent += "\n\n\n////////Generated////////\n";

			// write commands in suitable rules

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
				
				// System.out.println(rule.toString() + "\n"); //TODO: remove
				
				rule.createAssignments();
				
				if (!rule.isEmpty()) {
					grammarContent += rule.toString();
				}
			}

			System.out.println(grammarContent);

			FileWriter grammarWriter = new FileWriter(grammarFile);

			grammarWriter.write(grammarContent);

			grammarWriter.close();

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
