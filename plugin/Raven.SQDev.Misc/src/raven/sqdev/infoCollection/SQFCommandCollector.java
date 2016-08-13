package raven.sqdev.infoCollection;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;

import raven.sqdev.exceptions.BadSyntaxException;
import raven.sqdev.exceptions.SQDevCollectionException;
import raven.sqdev.infoCollection.base.ELocality;
import raven.sqdev.infoCollection.base.KeywordList;
import raven.sqdev.infoCollection.base.SQFCommand;
import raven.sqdev.infoCollection.base.SQFElement;
import raven.sqdev.misc.SQDev;
import raven.sqdev.misc.TextUtils;
import raven.sqdev.syntax.Syntax;

/**
 * A class for collecting all SQF commands from the BIKI.
 * 
 * @author Raven
 * 
 */
public class SQFCommandCollector {
	/**
	 * The category containing general info about the command such as name,
	 * locality, etc.
	 */
	public static final int CATEGORY_COMMAND_INFO = 0;
	/**
	 * The categroy containing th command's description
	 */
	public static final int CATEGORY_DESCRIPTION = 1;
	/**
	 * The category containing the command's syntax(es)
	 */
	public static final int CATEGORY_SYNTAX = 2;
	/**
	 * The category containg examples for the usage of the command
	 */
	public static final int CATEGORY_EXAMPLES = 3;
	/**
	 * The category containing notes attached to this command
	 */
	public static final int CATEGORY_NOTES = 4;
	/**
	 * The syntaxPart containing the syntax
	 */
	public static final int SYNTAXPART_SYNTAX = 0;
	/**
	 * The syntaxpart conating the parameters
	 */
	public static final int SYNTAXPART_PARAMETERS = 1;
	/**
	 * The syntaxPart containing the return value
	 */
	public static final int SYNTAXPART_RETURN_VALUE = 2;
	/**
	 * An array containing the names o the commands this collector should not
	 * collect
	 */
	public static final String[] CONTROL_STRUCTURE_KEYWORDS_ARRAY = { "if", "then", "else", "for",
			"from", "to", "do", "step", "switch", "while", "with", "case", "default" };
	/**
	 * A list containing the values of <code>COMMANDS_TO_IGNORE_ARRAY</code>
	 */
	public static final List<String> CONTROL_STRUCTURE_KEYWORDS = Arrays
			.asList(CONTROL_STRUCTURE_KEYWORDS_ARRAY);
	
	/**
	 * The URL to the base site where all commands are listed
	 */
	private URL baseSite;
	
	/**
	 * The name of the first command in the list on the base site
	 */
	private String firstCommandName;
	/**
	 * The name of the last command in the list on the base site
	 */
	private String lastCommandName;
	/**
	 * The list of processed Keywords
	 */
	private KeywordList list;
	/**
	 * A flag indicating whether the next command should be skipped
	 */
	private boolean skipNext = false;
	
	/**
	 * Creates an instance of this collector
	 * 
	 * @param baseSite
	 *            The base site that lists all commands
	 * @param firstCommandName
	 *            The name of the first command in the list (The one to start
	 *            with)
	 * @param lastCommandName
	 *            The name of the last ommand in the list (The one to end with)
	 */
	public SQFCommandCollector(URL baseSite, String firstCommandName, String lastCommandName) {
		Assert.isNotNull(baseSite);
		Assert.isTrue(firstCommandName != null && !firstCommandName.isEmpty());
		Assert.isTrue(lastCommandName != null && !lastCommandName.isEmpty());
		
		this.baseSite = baseSite;
		this.firstCommandName = firstCommandName;
		this.lastCommandName = lastCommandName;
	}
	
	/**
	 * Starts the collection of the SQF commands
	 * 
	 * @param monitor
	 *            The <code>IProgressMonitor</code> used to watch this
	 *            collection
	 * @return The <code>KeywordList</code> conatining the gathered keywords
	 * @throws SQDevCollectionException
	 */
	public KeywordList collect(IProgressMonitor monitor) throws SQDevCollectionException {
		String siteContent;
		try {
			siteContent = getSite(baseSite);
			
			// get relevant content only
			siteContent = trimToRelevantListOnly(siteContent);
		} catch (IOException e) {
			// rethrow
			throw new SQDevCollectionException(e, null, null);
		}
		
		// compose the line where the collecting should start at
		String relevanStarttLine = "<li><a href=\"/wiki/" + firstCommandName + "\" title=\""
				+ firstCommandName + "\">" + firstCommandName + "</a></li>";
		
		String relevantEndLine = "<li><a href=\"/wiki/" + lastCommandName + "\" title=\""
				+ lastCommandName + "\">" + lastCommandName + "</a></li>";
		
		if (!siteContent.contains(relevanStarttLine) || !siteContent.contains(relevantEndLine)) {
			throw new SQDevCollectionException(
					"The specified base site \"" + baseSite.toString()
							+ "\" does not contain a line corresponding to the specified first command \""
							+ firstCommandName + "\" or last command \"" + lastCommandName + "\"",
					null, list);
		}
		
		// start the list at the first command
		siteContent = siteContent.substring(siteContent.indexOf(relevanStarttLine),
				siteContent.indexOf(relevantEndLine) + relevantEndLine.length()).trim();
		
		
		// create keywordList
		list = new KeywordList();
		
		monitor.beginTask("Gathering SQF commands", siteContent.split("\n").length);
		
		// go through each link and gather respective information
		for (String currentLine : siteContent.split("\n")) {
			if (monitor.isCanceled()) {
				// if the job was canceled return the current state of the list
				return list;
			}
			
			if (skipNext) {
				skipNext = false;
				continue;
			}
			
			// get the postfix for the wiki page of the current command
			String postfix = currentLine.substring(currentLine.indexOf("\"") + 1);
			postfix = postfix.substring(0, postfix.indexOf("\""));
			
			String name = postfix.substring(postfix.lastIndexOf("/") + 1);
			
			// display which cammand currently is processed
			monitor.subTask("Current Command: \"" + name + "\"");
			
			URL commandPageURL;
			try {
				String strURL = baseSite.toString();
				strURL = strURL.substring(0, strURL.indexOf("/wiki")) + postfix;
				
				if (strURL.equals("https://community.bistudio.com/wiki/for")) {
					// use this link as it does provide a description
					strURL = "https://community.bistudio.com/wiki/for_forspec";
				}
				
				commandPageURL = new URL(strURL);
			} catch (MalformedURLException e) {
				e.printStackTrace();
				
				// rethrow
				throw new SQDevCollectionException(e, null, list);
			}
			
			if (!CONTROL_STRUCTURE_KEYWORDS.contains(name)) {
				SQFCommand command = processCommand(new SQFCommand(name), commandPageURL);
				
				if (command != null) {
					list.addKeyword(command);
				}
			} else {
				SQFCommand control = processControlStructure(new SQFCommand(name), commandPageURL);
				try {
					switch (control.getKeyword()) {
						case "then":
						case "else":
						case "do":
							// make binary
							control.addSyntax(Syntax.parseSyntax(
									"leftConstruct " + control.getKeyword() + " rigthConstruct",
									control.getKeyword()));
							break;
						
						default:
							// make unary
							control.addSyntax(
									Syntax.parseSyntax(control.getKeyword() + " rigthConstruct",
											control.getKeyword()));
					}
				} catch (BadSyntaxException e) {
					throw new SQDevCollectionException(
							"Failed at parsing syntax of control structure: \""
									+ control.getKeyword() + "\"",
							control, list);
				}
				
				if (control != null) {
					list.addKeyword(control);
				}
			}
			
			monitor.worked(1);
		}
		
		return list;
	}
	
	/**
	 * Will process the given commandPage and feed the gathered information into
	 * the given <code>SQFControlStructure</code>
	 * 
	 * @param control
	 *            The <code>SQFControlStructure</code> the gathered information
	 *            should be associated with
	 * @param commandPage
	 *            The <code>URL</code> to the commandPage wiki page
	 * @return The <code>SQFControlStructure</code> filled with information
	 * @throws SQDevCollectionException
	 */
	private SQFCommand processControlStructure(SQFCommand control, URL commandPage)
			throws SQDevCollectionException {
		// set wiki URL
		control.setWikiPage(commandPage);
		
		String content;
		try {
			content = formatCommandPageContent(getSite(commandPage));
		} catch (IOException e) {
			// rethrow
			throw new SQDevCollectionException(e, null, list);
		}
		
		String[] categories = categorizeContent(content);
		
		// set description
		String description = categories[CATEGORY_DESCRIPTION].trim();
		if (description.toLowerCase().startsWith("description:")) {
			description = description.substring(12).trim();
		}
		control.setDescription(description);
		
		return control;
	}
	
	/**
	 * Gets the trimmed content of the specified site
	 * 
	 * @param url
	 *            The URL to the site
	 * @return
	 * @throws SQDevCollectionException
	 */
	private String getSite(URL url) throws IOException {
		// read the site's content
		InputStream in = url.openStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		byte[] bArray = new byte[in.available()];
		
		in.read(bArray);
		
		out.write(bArray);
		
		int next;
		while ((next = in.read()) > 0) {
			out.write((char) next);
		}
		
		String content = out.toString();
		
		// replace html escape characters
		Pattern escapePattern = Pattern.compile("&#[0-9]+;");
		Matcher matcher = escapePattern.matcher(content);
		
		while (matcher.find()) {
			int pos1 = matcher.start();
			int pos2 = matcher.end();
			
			String prefix = content.substring(0, pos1);
			String fragment = content.substring(pos1, pos2);
			char escapeCharacter = (char) Integer
					.parseInt(fragment.substring(2, fragment.length() - 1));
			String postfix = content.substring(pos2);
			
			if (escapeCharacter == (char) 160) {
				// replace non-breaking spaces with normal ones
				escapeCharacter = ' ';
			}
			
			content = prefix + escapeCharacter + postfix;
			
			matcher = escapePattern.matcher(content);
		}
		
		return content;
	}
	
	/**
	 * Trims the given html content so that it only contains the lines relevant
	 * for the list containing the SQF commands
	 * 
	 * @param htmlContent
	 *            The content to trim
	 * @throws SQDevCollectionException
	 */
	private String trimToRelevantListOnly(String htmlContent) throws IOException {
		BufferedReader reader = new BufferedReader(new StringReader(htmlContent));
		
		String content = "";
		
		String currentLine = "";
		
		while ((currentLine = reader.readLine()) != null) {
			// only consider lines containing a link to the wiki
			if (currentLine.contains("<li><a href=\"/wiki/")) {
				content += currentLine + "\n";
			}
		}
		
		reader.close();
		
		return content.trim();
	}
	
	/**
	 * Will process the given commandPage and feed the gathered information into
	 * the given <code>SQFCommand</code>
	 * 
	 * @param command
	 *            The command the gathered information should be associated with
	 * @param commandPage
	 *            The <code>URL</code> to the command's wiki page
	 * @return The command filled with information
	 * @throws SQDevCollectionException
	 */
	private SQFCommand processCommand(SQFCommand command, URL commandPage)
			throws SQDevCollectionException {
		// get site content
		String siteContent;
		try {
			siteContent = formatCommandPageContent(getSite(commandPage));
		} catch (IOException e) {
			// rethrow
			throw new SQDevCollectionException(e, command, list);
		}
		
		String[] categories = categorizeContent(siteContent);
		
		if (!commandPage.toString().endsWith(command.getKeyword()) || !categories[CATEGORY_SYNTAX]
				.toLowerCase().contains(command.getKeyword().toLowerCase())) {
			// The current command should be integrated in the previous command
			// with this name TODO
			return null;
		}
		
		
		// store information
		
		// commandInfo
		String commandInfo = categories[CATEGORY_COMMAND_INFO];
		applyCommandInfo(command, commandInfo);
		
		// wikiPage
		command.setWikiPage(commandPage);
		
		// description
		String description = categories[CATEGORY_DESCRIPTION];
		if (description.startsWith("Description:\n")) {
			// remove that unnecessary line
			description = description.substring(description.indexOf("\n") + 1);
		}
		
		if (!description.isEmpty()) {
			command.setDescription(description);
		}
		
		// syntax
		String syntax = categories[CATEGORY_SYNTAX];
		
		if (!syntax.isEmpty()) {
			if (command.getKeyword().toLowerCase().equals("private")) {
				// exception for private as a keyword
				syntax = syntax.substring(0,
						syntax.toLowerCase().lastIndexOf("alternative syntax"));
			}
			
			applySyntax(command, syntax);
		} else {
			throw new SQDevCollectionException(
					"The command \"" + command.getKeyword() + "\" does not specify a syntax!",
					command, list);
		}
		
		// examples
		String examples = categories[CATEGORY_EXAMPLES];
		
		if (!examples.isEmpty()) {
			applyExamples(command, examples);
		}
		
		// Notes
		String notes = categories[CATEGORY_NOTES];
		
		if (!notes.isEmpty()) {
			try {
				applyNotes(command, notes);
			} catch (SQDevCollectionException e) {
				e.printStackTrace();
			}
		}
		
		return command;
	}
	
	/**
	 * Extracts the relevant parts and formats the content of a commandPage
	 * 
	 * @param content
	 *            The content of the commandPage
	 * @return The formatted content
	 */
	private String formatCommandPageContent(String content) {
		String[] tagsToRemove = { "script", "style", "header", "head" };
		
		for (String currentTag : tagsToRemove) {
			// remove tags
			boolean proceed = content.contains("<" + currentTag)
					&& content.contains("</" + currentTag + ">");
			
			while (proceed) {
				String fragment1 = content.substring(0, content.indexOf("<" + currentTag));
				String fragment2 = content.substring(content.indexOf("<" + currentTag));
				String fragment3 = fragment2.substring(
						fragment2.indexOf("</" + currentTag + ">") + 3 + currentTag.length());
				
				content = fragment1 + fragment3;
				
				proceed = content.contains("<" + currentTag)
						&& content.contains("</" + currentTag + ">");
			}
		}
		
		if (content.contains("<dt class=\"note\">")) {
			// if there are notes attached make sure that every note has a date
			// attached (even an empty one)
			
			String fragment1 = content.substring(0, content.indexOf("<dt class=\"note\">")).trim();
			String fragment2 = content.substring(content.indexOf("<dt class=\"note\">")).trim();
			
			boolean proceed = true;
			
			while (proceed) {
				// add the necessary "Posted on"
				if (!fragment1.substring(fragment1.lastIndexOf("\n"))
						.contains("<dd class=\"notedate\">Posted on")) {
					fragment1 = fragment1 + "\n<dd class=\"notedate\">Posted on\n";
				}
				
				// check if there are futher notes
				proceed = fragment2.substring(fragment2.indexOf("\n"))
						.contains("<dt class=\"note\">");
				
				if (proceed) {
					// reassemble fragments to process further notes
					String previousNoteLine = fragment2.substring(0, fragment2.indexOf("\n"));
					String helper = fragment2.substring(previousNoteLine.length());
					String fragment3 = helper.substring(0, helper.indexOf("<dt class=\"note\">"));
					
					fragment1 += "\n" + previousNoteLine + "\n" + fragment3.trim();
					fragment1 = fragment1.trim();
					
					fragment2 = fragment2.substring(previousNoteLine.length() + fragment3.length());
					fragment2 = fragment2.trim();
				} else {
					// reset content
					content = fragment1 + "\n" + fragment2;
				}
			}
			
			// make sure that the user name stands in it's own line
			content = content.replace("<dd class=\"note\">", "\n<dd class=\"note\">");
		}
		
		// mark parameter
		content = content.replace("<dd class=\"param\">", "\nparam:\n");
		
		// keep code markdown
		content = content.replace("<code>", " " + SQDev.CODE.getOpener());
		content = content.replace("</code>", SQDev.CODE.getCloser() + " ");
		
		String commandInfoLine = content.substring(content.indexOf("<div class=\"gvi\">"));
		commandInfoLine = commandInfoLine.substring(0, commandInfoLine.indexOf("\n"));
		
		// remove tags
		boolean proceed = content.contains("<") && content.contains(">");
		while (proceed) {
			String fragment1 = content.substring(0, content.indexOf("<"));
			String fragment2 = content.substring(content.indexOf("<"));
			String fragment3 = fragment2.substring(fragment2.indexOf(">") + 1);
			
			content = fragment1 + " " + fragment3;
			
			proceed = content.contains("<") && content.contains(">");
		}
		
		// remove tabs
		while (content.contains("\t")) {
			content = content.replace("\t", "");
		}
		
		while (content.contains("\n ")) {
			content = content.replace("\n ", "\n");
		}
		
		// reduce lineBreaks
		while (content.contains("\n\n")) {
			content = content.replace("\n\n", "\n");
		}
		
		content = content.replaceAll("&[^;]*;", " ");
		
		while (content.contains("  ")) {
			content = content.replace("  ", " ");
		}
		
		while (content.contains(" .")) {
			content = content.replace(" .", ".");
		}
		
		content = content.trim();
		
		// add information about the command
		String commandInfo = "";
		
		if (commandInfoLine.contains("arguments_local.gif")) {
			commandInfo += "Arguments: " + ELocality.LOCAL + "\n";
		}
		
		if (commandInfoLine.contains("arguments_global.gif")) {
			commandInfo += "Arguments: " + ELocality.GLOBAL + "\n";
		}
		
		if (commandInfoLine.contains("effects_local.gif")) {
			commandInfo += "Effects: " + ELocality.LOCAL + "\n";
		}
		
		if (commandInfoLine.contains("effects_global.gif")) {
			commandInfo += "Effects: " + ELocality.GLOBAL + "\n";
		}
		
		if (commandInfoLine.contains("Exec_Server.gif")) {
			commandInfo += "ServerExecution: true";
		} else {
			commandInfo += "ServerExecution: false";
		}
		
		// add the gathered information to the content
		if (!commandInfo.isEmpty()) {
			content = "#CommandInfo\n" + commandInfo.trim() + "\n#EndCommandInfo\n\n" + content;
		}
		
		// make sure that commas are set properly
		while (content.contains(" ,")) {
			content = content.replace(" ,", ",");
		}
		
		// make sure points are set properly
		while (content.contains(" .")) {
			content = content.replace(" .", ".");
		}
		
		return content;
	}
	
	/**
	 * This will categorize the formatted content of a commandPage
	 * 
	 * @param content
	 *            The formatted content
	 * @return An array with the categories<br>
	 */
	private String[] categorizeContent(String content) {
		String[] categories = new String[5];
		
		String commandInfo = "\n";
		String description = "\n";
		String syntax = "\n";
		String examples = "\n";
		String notes = "\n";
		
		// add the command infos
		commandInfo += content.substring(content.indexOf("\n") + 1,
				content.indexOf("#EndCommandInfo") - 1);
		
		content = content.substring(content.indexOf("#EndCommandInfo") + 15).trim();
		
		// add the name of the command
		commandInfo = "Name: "
				+ content.substring(0, content.indexOf("\n")).trim().replace(" ", "_") + "\n"
				+ commandInfo;
		
		content = content.substring(content.indexOf("\n") + 1);
		
		String currentCategory = "";
		
		// categorize the content
		for (String currentLine : content.split("\n")) {
			currentLine = currentLine.trim();
			
			if (currentLine.startsWith("Only post proven facts here.")) {
				// skip this line
				continue;
			}
			
			// check for a change of categories
			switch (currentLine) {
				case "Description":
				case "Syntax":
				case "Examples":
				case "Notes":
					currentCategory = currentLine;
					break;
				case "Additional Information":
				case "Bottom Section":
					currentCategory = "";
					break;
			}
			
			if (currentCategory.isEmpty() && currentLine.startsWith("Posted")) {
				currentCategory = "Notes";
			} else {
				if (!currentCategory.isEmpty() && (currentLine.startsWith("Categories")
						|| currentLine.startsWith("Retrieved from"))) {
					currentCategory = "";
				}
			}
			
			// add line to respective category
			switch (currentCategory) {
				case "Description":
					if (!currentLine.equals(currentCategory)) {
						description += currentLine + "\n";
					}
					break;
				case "Syntax":
					if (!currentLine.equals(currentCategory)) {
						syntax += currentLine + "\n";
					}
					break;
				case "Examples":
					if (!currentLine.equals(currentCategory)) {
						examples += currentLine + "\n";
					}
					break;
				case "Notes":
					if (!currentLine.equals(currentCategory)) {
						notes += currentLine + "\n";
					}
					break;
			}
		}
		
		categories[CATEGORY_COMMAND_INFO] = commandInfo;
		categories[CATEGORY_DESCRIPTION] = description.substring(description.indexOf("\n")).trim();
		categories[CATEGORY_SYNTAX] = syntax.substring(syntax.indexOf("\n")).trim();
		categories[CATEGORY_EXAMPLES] = examples.substring(examples.indexOf("\n")).trim();
		categories[CATEGORY_NOTES] = notes.substring(notes.indexOf("\n")).trim();
		
		return categories;
	}
	
	/**
	 * Applies the given commandInfo to the given command
	 * 
	 * @param command
	 *            The command the info should get added to
	 * @param info
	 *            The info to add up
	 * @throws SQDevCollectionException
	 */
	private void applyCommandInfo(SQFCommand command, String info) throws SQDevCollectionException {
		String name = info.substring(info.indexOf("Name:") + 5, info.indexOf("\n")).trim();
		
		if (!name.toLowerCase().equals(command.getKeyword().toLowerCase())) {
			throw new SQDevCollectionException(
					"The given commandInfo does not specify the same name (\"" + name
							+ "\") as the SQFCommand object (\"" + command.getKeyword() + "\")!",
					command, list);
		}
		
		// remove processed line
		info = info.substring(info.indexOf("\n") + 1);
		
		for (String currentLine : info.split("\n")) {
			currentLine = currentLine.trim();
			
			if (currentLine.startsWith("Arguments:")) {
				// store argument's locality
				String argumentsLocality = currentLine.substring(10).trim();
				
				command.setArgumentLocality(ELocality.resolve(argumentsLocality));
			} else {
				if (currentLine.startsWith("Effects:")) {
					// store effects locality
					String effectsLocality = currentLine.substring(8).trim();
					
					command.setEffectLocality(ELocality.resolve(effectsLocality));
				} else {
					if (currentLine.equals("ServerExecution: true")) {
						// add a note when a server execution is necessary
						command.addNote("This scripting command must be executed on the server"
								+ " to work properly in multiplayer");
					}
				}
			}
		}
	}
	
	/**
	 * Adds the given examples to the given <code>SQFCommand</code>
	 * 
	 * @param command
	 *            The command theexamples should get added to
	 * @param examples
	 *            The String containing the examples indexed by "Example 1:" and
	 *            so on
	 */
	private void applyExamples(SQFCommand command, String examples) {
		boolean proceed = true;
		int counter = 1;
		
		if (examples.contains("Example needed")) {
			return;
		}
		
		examples = examples.trim();
		
		while (proceed) {
			String exampleNum = "Example " + counter + ":";
			
			if (examples.startsWith(exampleNum)) {
				// if there is an example with this number
				examples = examples.substring(examples.indexOf(exampleNum) + exampleNum.length())
						.trim();
				
				counter++;
				
				String nextExampleNum = "Example " + counter + ":";
				
				if (examples.contains(nextExampleNum)) {
					// only take the content until next example starts
					String currentExample = examples.substring(0, examples.indexOf(nextExampleNum))
							.trim();
					
					command.addExample(currentExample);
					
					// remove processed examples from the examples String
					examples = examples.substring(currentExample.length()).trim();
					
					proceed = !examples.isEmpty();
				} else {
					// the complete content belongs to the current example
					if (!examples.isEmpty()) {
						command.addExample(examples);
					}
					
					proceed = false;
				}
			} else {
				break;
			}
		}
	}
	
	/**
	 * Applies the given notes to the given command
	 * 
	 * @param command
	 *            The command the notes should be applied to
	 * @param notes
	 *            The notes to apply
	 * @throws SQDevCollectionException
	 */
	private void applyNotes(SQFCommand command, String notes) throws SQDevCollectionException {
		String currentNote = "";
		boolean skippedName = false;
		
		if (!notes.contains("Posted on")) {
			throw new SQDevCollectionException(
					"The notes of " + command.getKeyword() + "arenot in the proper format!",
					command, list);
		}
		
		for (String currentLine : notes.split("\n")) {
			if (currentLine.startsWith("Posted on")) {
				if (!currentNote.isEmpty()) {
					// add the previous note to the command
					command.addNote(currentNote.trim());
					
					// reset currentNote
					currentNote = "";
				}
				
				// a new note is beginning -> start with post date
				String date = currentLine.substring(9).trim();
				
				if (date.contains("-")) {
					// don't store time
					date = date.substring(0, date.indexOf("-")).trim();
				}
				
				if (!date.isEmpty()) {
					currentNote += "(" + date + ")\n";
				}
				
				// indicate that the name has not yet been skipped
				skippedName = false;
			} else {
				if (skippedName) {
					// the following lines are the note
					currentNote += currentLine + "\n";
				} else {
					// indicate that the name has been skipped
					skippedName = true;
				}
			}
		}
		
		if (!currentNote.isEmpty()) {
			command.addNote(currentNote);
		}
	}
	
	/**
	 * Applies the given syntax to the given command after having brought it
	 * into the propre format
	 * 
	 * @param command
	 *            The command the notes should be applied to
	 * @param syntaxContent
	 *            The syntax with it's parameters that should be applied to the
	 *            command
	 * @throws SQDevCollectionException
	 */
	private void applySyntax(SQFCommand command, String syntaxContent)
			throws SQDevCollectionException {
		String[][] syntaxes = splitSyntaxes(syntaxContent, command);
		
		for (String[] currentSyntax : syntaxes) {
			String syntax = currentSyntax[SYNTAXPART_SYNTAX];
			
			// add the raw syntax to the command
			command.addRawSyntax(syntax);
			
			Pattern arrayPattern = Pattern.compile("\\[[^\\[\\]]*\\]");
			Matcher arrayMatcher = arrayPattern.matcher(syntax);
			
			// replace all array constructs with Array type keyword
			while (arrayMatcher.find()) {
				syntax = arrayMatcher.replaceAll("Array");
				
				arrayMatcher = arrayPattern.matcher(syntax);
			}
			
			syntax = processSyntax(syntax, currentSyntax[SYNTAXPART_PARAMETERS], command);
			
			try {
				command.addSyntax(Syntax.parseSyntax(syntax, command.getKeyword()));
			} catch (BadSyntaxException e) {
				throw new SQDevCollectionException(
						"Fauiled at parsing syntax for command \"" + command.getKeyword() + "\"",
						command, list);
			}
		}
		
		if (syntaxes.length > 0) {
			// add return type
			String returnType = syntaxes[0][SYNTAXPART_RETURN_VALUE];
			
			if (returnType != null && !returnType.isEmpty()) {
				command.setReturnType(returnType);
			}
		}
		
		System.out.println(command.getSyntaxes());
	}
	
	/**
	 * Replaces the parameters in the syntax with the actual data types
	 * 
	 * @param syntax
	 *            The raw syntax
	 * @param parameter
	 *            The list of parameters with their corresponding data type
	 * @param The
	 *            <code>SQFElement</code> this syntax applies to
	 * @return The processed syntax with data types instead of placeholders
	 * @throws SQDevCollectionException
	 */
	private String processSyntax(String syntax, String parameter, SQFElement element)
			throws SQDevCollectionException {
		for (String currentParameter : parameter.split("param:\n")) {
			// process each listed parameter
			currentParameter = currentParameter.trim();
			
			if (currentParameter.isEmpty()) {
				continue;
			}
			
			// make a copy in order to avoid case sensitive problems
			String syntaxCopy = syntax.toLowerCase();
			
			String[] elements;
			try {
				elements = TextUtils.getTextAreas(currentParameter);
				
				// make sure parameter are in proper format
				if (!elements[0].contains(":") || elements.length == 1) {
					if (elements.length > 2
							&& (elements[1].equals(":") || elements[1].equals("-"))) {
						// add needed colon
						elements[0] = elements[0] + ":";
						// Move parameter data type
						elements[1] = elements[2];
					} else {
						throw new SQDevCollectionException(
								"Unexpected parameter format - missing\":\" at " + elements[0],
								element, list);
					}
				}
				
				String parameterName = elements[0].substring(0, elements[0].indexOf(":"))
						.toLowerCase();
				
				// remove dots from dataType
				String value = elements[1].replace(".", "");
				
				String[] syntaxElements = TextUtils.getTextAreas(syntax);
				String[] syntaxCopyElements = TextUtils.getTextAreas(syntaxCopy);
				
				for (int i = 0; i < syntaxCopyElements.length; i++) {
					if (syntaxCopyElements[i].equals(parameterName)) {
						// replace placeholder with actual value
						syntaxElements[i] = value;
						break;
					}
				}
				
				// convert syntax back to String
				StringBuilder builder = new StringBuilder();
				
				for (String currentElement : syntaxElements) {
					builder.append(" " + currentElement);
				}
				
				syntax = builder.toString().trim();
			} catch (BadSyntaxException e) {
				throw new SQDevCollectionException("Failed at processing syntax", e, element, list);
			}
		}
		
		return syntax;
	}
	
	/**
	 * Brings the given syntaxContent into the proper format
	 * 
	 * @param syntaxContent
	 *            The syntaxContent to format
	 * @return The formatted content
	 */
	private String formatSyntaxContent(String syntaxContent) {
		syntaxContent = syntaxContent.trim();
		
		while (Character.isDigit(syntaxContent.charAt(0))) {
			syntaxContent = syntaxContent.substring(1).trim();
		}
		
		// check for return value
		if (!syntaxContent.contains("Return Value:")) {
			// add a Nothing-return value
			syntaxContent += " Return Value: Nothing";
		} else {
			// make sure that a return value is specified
			if (syntaxContent.substring(syntaxContent.indexOf("Return Value:")).trim().isEmpty()) {
				syntaxContent += " Nothing";
			}
		}
		
		if (syntaxContent.startsWith("Syntax:")) {
			// make the syntax start directly without prefix "Syntax:"
			syntaxContent = syntaxContent.substring(7).trim();
		}
		
		if (syntaxContent.contains("Parameters:")) {
			// remove unnecessary statement
			syntaxContent = syntaxContent.substring(0, syntaxContent.indexOf("Parameters:")) + "\n"
					+ syntaxContent.substring(syntaxContent.indexOf("Parameters:") + 11);
		}
		
		// make each syntax part stand in it's own line
		
		syntaxContent = syntaxContent.substring(0, syntaxContent.indexOf("Return Value:")) + "\n"
				+ syntaxContent.substring(syntaxContent.indexOf("Return Value:"));
		
		syntaxContent = syntaxContent.substring(0, syntaxContent.indexOf("Return Value:") + 13)
				.trim() + " "
				+ syntaxContent.substring(syntaxContent.indexOf("Return Value:") + 13).trim();
		
		String syntaxLine = syntaxContent.substring(0, syntaxContent.indexOf("\n"));
		
		if (syntaxLine.contains("=")) {
			// remove asignments from syntax
			syntaxLine = syntaxLine.substring(syntaxLine.indexOf("=") + 1).trim();
		}
		
		// remove version tags that are not necessarily between brackets
		syntaxLine = syntaxLine.replaceAll("[sS]ince [aA]rm[aA][^)]*", "");
		
		return syntaxLine + syntaxContent.substring(syntaxContent.indexOf("\n"));
	}
	
	/**
	 * Splits the given syntax(es) and return them in an array (everything
	 * properly formatted)
	 * 
	 * @param syntaxContent
	 *            The syntaxes with the respective information
	 * @param The
	 *            <code>SQFElement</code> the syntaxes belong to
	 * @return A two-dimensional array where the first dimension stands for
	 *         different syntaxes (alternatives syntaxes) and the second
	 *         dimension is as following:
	 *         <li>index 0: The raw syntax</li>
	 *         <li>index 1: The parameters (each prefixed by "param:\n") with
	 *         their description</li>
	 *         <li>index 2: The return value</li>
	 * @throws SQDevCollectionException
	 */
	private String[][] splitSyntaxes(String syntaxContent, SQFElement element)
			throws SQDevCollectionException {
		// split the syntaxes and their components
		ArrayList<ArrayList<String>> syntaxes = new ArrayList<ArrayList<String>>(1);
		
		for (String currentSyntaxContent : syntaxContent.split("Alternative Syntax")) {
			// format the content
			currentSyntaxContent = formatSyntaxContent(currentSyntaxContent);
			
			ArrayList<String> currentSyntaxComponents = new ArrayList<String>(3);
			
			// get the syntax
			String currentSyntax = currentSyntaxContent.substring(0,
					currentSyntaxContent.indexOf("\n"));
			
			// trim syntaxContent to remove processed line
			currentSyntaxContent = currentSyntaxContent.substring(currentSyntax.length()).trim();
			
			// get the parameters
			String currentParameter = currentSyntaxContent.substring(0,
					currentSyntaxContent.indexOf("Return Value:"));
			
			
			// trim syntaxContent to remove processed line
			currentSyntaxContent = currentSyntaxContent.substring(currentParameter.length()).trim();
			
			currentSyntaxContent += "\n";
			
			String currentReturnValue = currentSyntaxContent
					.substring(currentSyntaxContent.indexOf("Return Value:") + 13);
			currentReturnValue = currentReturnValue.substring(0, currentReturnValue.indexOf("\n"));
			
			// store the gathered values
			currentSyntaxComponents.add(SYNTAXPART_SYNTAX, formatRawSyntax(currentSyntax));
			currentSyntaxComponents.add(SYNTAXPART_PARAMETERS,
					formatParameters(currentParameter, element));
			currentSyntaxComponents.add(SYNTAXPART_RETURN_VALUE,
					formatReturnValue(currentReturnValue));
			
			syntaxes.add(currentSyntaxComponents);
		}
		
		String[][] syntaxArray = new String[syntaxes.size()][];
		
		for (int i = 0; i < syntaxes.size(); i++) {
			syntaxArray[i] = syntaxes.get(i).toArray(new String[syntaxes.get(i).size()]);
		}
		
		return syntaxArray;
	}
	
	/**
	 * Brings the given raw syntax into a proper format
	 * 
	 * @param syntax
	 *            The raw syntax that should be formatted
	 * @return The formatted raw syntax
	 */
	private String formatRawSyntax(String syntax) {
		// remove round brackets
		Pattern bracketPattern = Pattern.compile("\\([^\\(\\)]*\\)");
		Matcher bracketMatcher = bracketPattern.matcher(syntax);
		
		while (bracketMatcher.find()) {
			syntax = bracketMatcher.replaceAll("");
			
			bracketMatcher = bracketPattern.matcher(syntax);
		}
		
		return syntax;
	}
	
	/**
	 * Brings the given parameters into the proper format
	 * 
	 * @param parameters
	 *            The parameters to format
	 * @param element
	 *            The <code>SQFElement</code> the given parameters belong to
	 * @return The formatted parameters
	 * @throws SQDevCollectionException
	 */
	private String formatParameters(String parameters, SQFElement element)
			throws SQDevCollectionException {
		String[] paramArray = parameters.split("param:\n");
		parameters = "";
		
		for (String currentParameter : paramArray) {
			if (currentParameter.isEmpty()) {
				continue;
			}
			// process alternatove data types
			parameters += "§PARAM§ " + formatAlternativeDataTypes(currentParameter);
		}
		
		String[] areas;
		try {
			areas = TextUtils.getTextAreas(parameters);
		} catch (BadSyntaxException e) {
			throw new SQDevCollectionException("Can't format parameter", e, element, list);
		}
		
		StringBuilder builder = new StringBuilder();
		
		boolean matchedColon = false;
		boolean matchedDataType = false;
		
		for (String currentArea : areas) {
			if (currentArea.startsWith("(")) {
				if (currentArea.toLowerCase().contains("optional")) {
					// TODO mark optional parameter
				} else {
					if (matchedDataType) {
						// maintain only brackets after dataType has been
						// matched
						builder.append(" " + currentArea);
					}
				}
			} else {
				switch (currentArea.toLowerCase()) {
					case "§param§":
						// maintan proper format
						currentArea = "param:\n";
						
						// new parameter beginning -> reset colon and dataType
						// matches
						matchedColon = false;
						matchedDataType = false;
						break;
					
					case "bool":
						// don't use bool shortcut
						currentArea = "Boolean";
					default:
						if (matchedColon) {
							// colon has been matched therefore this must be the
							// data type
							matchedDataType = true;
						}
				}
				
				builder.append(" " + currentArea);
				
				if (currentArea.endsWith(":")) {
					matchedColon = true;
				}
			}
		}
		
		parameters = builder.toString().trim().replace(" :", ":").replace(" / ", "/");
		
		String paramCopy = parameters.toLowerCase();
		
		// format data types consisting of more than one word
		final String[] dataTypesToReplace = { "structured text", "if type", "team member",
				"any value" };
		final String[] dataTypeReplacements = { "StructuredText", "IfType", "TeamMember",
				"Anything" };
		
		for (int i = 0; i < dataTypesToReplace.length; i++) {
			while (paramCopy.contains(dataTypesToReplace[i])) {
				parameters = parameters.substring(0, paramCopy.indexOf(dataTypesToReplace[i]))
						+ dataTypeReplacements[i]
						+ parameters.substring(paramCopy.indexOf(dataTypesToReplace[i])
								+ dataTypesToReplace[i].length());
				
				paramCopy = parameters.toLowerCase();
			}
		}
		
		return parameters;
	}
	
	/**
	 * Formats alternative data types (this is when more than one dataType is
	 * allowed) by seperating them with a "/"
	 * 
	 * @param input
	 *            The input to process
	 * @return The formatted input
	 */
	private String formatAlternativeDataTypes(String input) {
		// look for alternative data types
		int pos1 = input.indexOf(":");
		String prefix = input.substring(0, pos1 + 1);
		
		int pos2 = input.length();
		
		if (input.contains("-") && input.indexOf("-") > prefix.length() - 1) {
			pos2 = input.indexOf("-");
		}
		
		String relevantPart = input.substring(pos1 + 1, pos2);
		String postfix = input.substring(pos2, input.length());
		
		// replace all seperators by slash
		relevantPart = relevantPart.replace(",", "/").replace("or", "/");
		
		// make sure the seperators are not seperated from the next word
		while (relevantPart.contains(" /")) {
			relevantPart = relevantPart.replace(" /", "/");
		}
		while (relevantPart.contains("/ ")) {
			relevantPart = relevantPart.replace("/ ", "/");
		}
		
		return prefix + relevantPart + postfix;
	}
	
	/**
	 * Brings the given returnValue into the proper format
	 * 
	 * @param returnValue
	 *            The returnValue to format
	 * @return The formatted returnValue
	 */
	private String formatReturnValue(String returnValue) {
		returnValue = returnValue.trim();
		// TODO
		return returnValue;
	}
	
	/**
	 * Sets the respective flag so that the next command will be skipped
	 */
	public void skipFNext() {
		skipNext = true;
	}
}
