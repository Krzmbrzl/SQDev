package raven.sqdev.infoCollection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;

import raven.sqdev.exceptions.BadSyntaxException;
import raven.sqdev.exceptions.SQDevCollectionException;
import raven.sqdev.exceptions.SQDevException;
import raven.sqdev.infoCollection.base.ELocality;
import raven.sqdev.infoCollection.base.KeywordList;
import raven.sqdev.infoCollection.base.SQFCommand;
import raven.sqdev.infoCollection.base.SQFElement;
import raven.sqdev.misc.ArrayUtils;
import raven.sqdev.misc.DataTypeList;
import raven.sqdev.misc.EDataType;
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
	 * The character that is used in order to mark optional parameters
	 */
	public static final char OPTIONAL_MARKER = '?';
	
	/**
	 * A set of essential commands that are forced to be included in the
	 * collection process
	 */
	public static final String[] ESSENTIAL_COMMANDS = { "if", "then", "else",
			"while", "do", "for", "with", "switch", "from", "to", "step",
			"count", "forEach" };
	
	/**
	 * A list of operators that are not retrieved by the normal command
	 * collection as they are listed in a different format
	 */
	public static final String[] MANUAL_COMMANDS = { "!", "==", "!=", "<", "<=",
			">", ">=", "&&", "||", "+", "-", "*", "/", "^", "%", ">>", ":" };
	
	/**
	 * The corresponding command descriptions to {@link #MANUAL_COMMANDS}
	 */
	public static final String[] MANUAL_COMMANDS_DESCRIPTION = {
			"Negates the given Boolean value. Synonym for 'not'.",
			"Check if one value is equal to another. Both values need to be of the same type.",
			"Returns whether one value is not equal to another. Both values have need be of the same type.",
			"Checks if the left number is smaller than the right one",
			"Checks if the left number is smaller than or equal to the right one",
			"Checks if the left number is greater than the right one",
			"Checks if the left number is greater than or equal to the right one",
			"Returns true only if both conditions are true. In case of the alternative syntax (code as right argument),"
					+ " lazy evaluation is used"
					+ " (if left operand is false, evaluation of the right side is skipped completely). Identical to: a and b",
			"Returns true only if one or both conditions are true. In case of the alternative syntax(code as right argument),"
					+ " lazy evaluation is used"
					+ " (if left operand is true, evaluation of the right side is skipped completely). Identical to: a or b",
			"Adds the given two values together or if used only with one argument it returns a copy of that argument",
			"Subtracts the right value from the left one. Both need to be of the same type, both Numbers or both Arrays. "
					+ "In Arma 3 it is possible to subtract nested arrays.",
			"Multiplies the given numbers.",
			"Divides the left number by the right one. Division by 0 throws \"Division by zero\" error, however script doesn't "
					+ "stop and the result of such division is assumed to be 0. If used with config and String as "
					+ "arguments it returns the subentry of the given config entry with the given name.",
			"Returns the left number to the power of the right one.",
			"Returns the remainder of a corresponding division",
			"Returns subentry of config entry with given name. Identical to config/name.",
			"Helper construct used in a switch-construct" };
	
	/**
	 * The corresponding syntaxes to {@link #MANUAL_COMMANDS}
	 */
	public static final String[][] MANUAL_COMMANDS_SYNTAX = {
			{ "! " + EDataType.BOOLEAN },
			{ EDataType.ANYTHING + " == " + EDataType.ANYTHING },
			{ EDataType.ANYTHING + " != " + EDataType.ANYTHING },
			{ EDataType.NUMBER + " < " + EDataType.NUMBER },
			{ EDataType.NUMBER + " <= " + EDataType.NUMBER },
			{ EDataType.NUMBER + " > " + EDataType.NUMBER },
			{ EDataType.NUMBER + " >= " + EDataType.NUMBER },
			{ EDataType.BOOLEAN + " && " + EDataType.BOOLEAN
					+ DataTypeList.TYPE_SEPERATOR + EDataType.CODE },
			{ EDataType.BOOLEAN + " || " + EDataType.BOOLEAN
					+ DataTypeList.TYPE_SEPERATOR + EDataType.CODE },
			{ EDataType.NUMBER + " + " + EDataType.NUMBER,
					EDataType.STRING + " + " + EDataType.STRING,
					EDataType.ARRAY + " + " + EDataType.ARRAY,
					"+ " + EDataType.NUMBER, "+ " + EDataType.ARRAY,
					"+ " + EDataType.STRING },
			{ EDataType.NUMBER + " - " + EDataType.NUMBER,
					EDataType.ARRAY + " - " + EDataType.ARRAY,
					"- " + EDataType.NUMBER },
			{ EDataType.NUMBER + " * " + EDataType.NUMBER },
			{ EDataType.NUMBER + " / " + EDataType.NUMBER,
					EDataType.CONFIG + " / " + EDataType.STRING },
			{ EDataType.NUMBER + " ^ " + EDataType.NUMBER },
			{ EDataType.NUMBER + " % " + EDataType.NUMBER },
			{ EDataType.CONFIG + " >> " + EDataType.STRING },
			{ EDataType.SWITCH_TYPE + " : " + EDataType.CODE } };
	
	/**
	 * The corresponding wiki page adresses for {@link #MANUAL_COMMANDS}
	 */
	public static final String[] MANUAL_COMMANDS_WIKI = {
			"https://community.bistudio.com/wiki/!_a",
			"https://community.bistudio.com/wiki/a_%3D%3D_b",
			"https://community.bistudio.com/wiki/a_!%3D_b",
			"https://community.bistudio.com/wiki/a_less_b",
			"https://community.bistudio.com/wiki/a_less%3D_b",
			"https://community.bistudio.com/wiki/a_greater_b",
			"https://community.bistudio.com/wiki/a_greater%3D_b",
			"https://community.bistudio.com/wiki/a_%26%26_b",
			"https://community.bistudio.com/wiki/a_or_b",
			"https://community.bistudio.com/wiki/valuea_plus_valueb",
			"https://community.bistudio.com/wiki/a_-_b",
			"https://community.bistudio.com/wiki/a_*_b",
			"https://community.bistudio.com/wiki/a_/_b",
			"https://community.bistudio.com/wiki/a_%5E_b",
			"https://community.bistudio.com/wiki/a_%25_b",
			"https://community.bistudio.com/wiki/config_greater_greater_name",
			"https://community.bistudio.com/wiki/a:b" };
	
	/**
	 * The corresponding return values to {@link #MANUAL_COMMANDS}
	 */
	public static final String[][] MANUAL_COMMANDS_RETURN_TYPE = {
			{ EDataType.BOOLEAN.toString() }, { EDataType.BOOLEAN.toString() },
			{ EDataType.BOOLEAN.toString() }, { EDataType.BOOLEAN.toString() },
			{ EDataType.BOOLEAN.toString() }, { EDataType.BOOLEAN.toString() },
			{ EDataType.BOOLEAN.toString() }, { EDataType.BOOLEAN.toString() },
			{ EDataType.BOOLEAN.toString() },
			{ EDataType.NUMBER.toString(), EDataType.STRING.toString(),
					EDataType.ARRAY.toString(), EDataType.NUMBER.toString(),
					EDataType.ARRAY.toString(), EDataType.STRING.toString() },
			{ EDataType.NUMBER.toString(), EDataType.ARRAY.toString(),
					EDataType.NUMBER.toString() },
			{ EDataType.NUMBER.toString() },
			{ EDataType.NUMBER.toString(), EDataType.CONFIG.toString() },
			{ EDataType.NUMBER.toString() }, { EDataType.NUMBER.toString() },
			{ EDataType.CONFIG.toString() }, { EDataType.NOTHING.toString() } };
	
	/**
	 * The string represnetation of the webadress to the BIKI API
	 */
	private final String apiAdress;
	/**
	 * The name of the main page where all the commands are listed
	 */
	private final String mainPageName;
	
	/**
	 * The list of processed Keywords
	 */
	private KeywordList list;
	
	/**
	 * Creates an instance of this collector
	 * 
	 * @param apiAdress
	 *            The string represnetation of the webadress to the BIKI API
	 * @param mainPageName
	 *            The name of the main page where all commands are listed
	 */
	public SQFCommandCollector(String apiAdress, String mainPageName) {
		Assert.isTrue(apiAdress != null && !apiAdress.isEmpty());
		Assert.isTrue(mainPageName != null && !mainPageName.isEmpty());
		
		this.apiAdress = apiAdress;
		this.mainPageName = mainPageName;
	}
	
	/**
	 * Starts the collection of the SQF commands
	 * 
	 * @param monitor
	 *            The <code>IProgressMonitor</code> used to watch this
	 *            collection
	 * @param repeat
	 *            The URL to repeat. This is only if one command has failed and
	 *            should be repeated. If there is no such command this argument
	 *            should be <code>null</code>
	 * @return The <code>KeywordList</code> conatining the gathered keywords
	 * @throws SQDevCollectionException
	 */
	public KeywordList collect(IProgressMonitor monitor, URL repeat)
			throws SQDevCollectionException {
		monitor.subTask("Initializing");
		
		SQFCommandPageProvider provider;
		try {
			provider = new SQFCommandPageProvider(apiAdress, mainPageName);
		} catch (IOException e1) {
			e1.printStackTrace();
			
			// rethrow exception
			throw new SQDevCollectionException(e1.getMessage(), null,
					new KeywordList());
		}
		
		if (repeat != null) {
			provider.popBack(repeat);
		}
		
		// create keywordList
		list = new KeywordList();
		
		monitor.beginTask("Gathering SQF commands",
				provider.size() + MANUAL_COMMANDS.length);
		
		// Add a few operators manually
		for (int i = 0; i < MANUAL_COMMANDS.length; i++) {
			if (monitor.isCanceled()) {
				// if the job was cancelled return the current state of the list
				return list;
			}
			
			String currentCommand = MANUAL_COMMANDS[i];
			String currentDescription = MANUAL_COMMANDS_DESCRIPTION[i];
			
			monitor.subTask("Current Command: \"" + currentCommand + "\"");
			
			SQFCommand command = new SQFCommand(currentCommand,
					currentDescription);
			
			for (int k = 0; k < MANUAL_COMMANDS_SYNTAX[i].length; k++) {
				String currentSyntax = MANUAL_COMMANDS_SYNTAX[i][k];
				DataTypeList returnTypes = DataTypeList
						.fillWith(MANUAL_COMMANDS_RETURN_TYPE[i][k]);
				try {
					Syntax syntax = Syntax.parseSyntax(currentSyntax,
							currentCommand);
					
					command.addSyntax(syntax);
					
					command.setReturnType(syntax, returnTypes);
				} catch (BadSyntaxException e) {
					throw new SQDevCollectionException(e.getMessage(), null,
							list);
				}
			}
			
			try {
				String url = MANUAL_COMMANDS_WIKI[i];
				
				if (!url.isEmpty()) {
					command.setWikiPage(new URL(url));
				}
			} catch (MalformedURLException e) {
				throw new SQDevCollectionException(e.getMessage(), null, list);
			}
			
			list.addKeyword(command);
			
			monitor.worked(1);
		}
		
		int size = provider.size();
		// go through each link and gather respective information
		for (int i = 0; i < size; i++) {
			if (monitor.isCanceled()) {
				// if the job was cancelled return the current state of the list
				return list;
			}
			
			URL currentCommandURL = provider.next();
			
			// get the name of the current command
			String strURL = currentCommandURL.toString();
			String name = strURL.substring(strURL.lastIndexOf("/") + 1).trim();
			
			// display which cammand currently is processed
			monitor.subTask("Current Command: \"" + name + "\"");
			
			SQFCommand command = processCommand(new SQFCommand(name),
					currentCommandURL);
			
			if (command != null) {
				list.addKeyword(command);
			}
			
			monitor.worked(1);
		}
		
		return list;
	}
	
	/**
	 * Gets the trimmed content of the specified site
	 * 
	 * @param url
	 *            The URL to the site
	 * @return The site's content as a String
	 * @throws SQDevCollectionException
	 */
	public static String getSite(URL url) throws IOException {
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
		
		return content.replace("&quot;", "\"").replace("&amp;", "&");
	}
	
	/**
	 * Will process the given commandPage and feed the gathered information into
	 * the given <code>SQFCommand</code>
	 * 
	 * @param command
	 *            The command the gathered information should be associated with
	 * @param commandPage
	 *            The <code>URL</code> to the command's wiki page
	 * @return The command filled with information or <code>null</code>
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
			throw new SQDevCollectionException(e, commandPage, list);
		}
		
		String[] categories = categorizeContent(siteContent);
		
		if (!commandPage.toString().endsWith(command.getKeyword())
				|| !categories[CATEGORY_SYNTAX].toLowerCase()
						.contains(command.getKeyword().toLowerCase())) {
			// The current command should be integrated in the previous command
			// with this name or stripped out TODO
			return null;
		}
		
		// store information
		try {
			// commandInfo
			String commandInfo = categories[CATEGORY_COMMAND_INFO];
			applyCommandInfo(command, commandInfo);
			
			// wikiPage
			command.setWikiPage(commandPage);
			
			// description
			String description = categories[CATEGORY_DESCRIPTION];
			if (description.startsWith("Description:\n")) {
				// remove that unnecessary line
				description = description
						.substring(description.indexOf("\n") + 1);
			}
			
			if (!description.isEmpty()) {
				command.setDescription(description);
			}
			
			// syntax
			String syntax = categories[CATEGORY_SYNTAX];
			
			if (!syntax.isEmpty()) {
				if (command.getKeyword().toLowerCase().equals("private")) {
					// exception for private as a keyword
					syntax = syntax.substring(0, syntax.toLowerCase()
							.lastIndexOf("alternative syntax"));
				}
				
				applySyntax(command, syntax);
			} else {
				throw new SQDevCollectionException(
						"The command \"" + command.getKeyword()
								+ "\" does not specify a syntax!",
						commandPage, list);
			}
			
			// examples
			String examples = categories[CATEGORY_EXAMPLES];
			
			if (!examples.isEmpty()) {
				applyExamples(command, examples);
			}
			
			// Notes
			String notes = categories[CATEGORY_NOTES];
			
			if (!notes.isEmpty()) {
				applyNotes(command, notes);
			}
		} catch (SQDevException e) {
			e.printStackTrace();
			
			throw new SQDevCollectionException(e, commandPage, list);
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
				String fragment1 = content.substring(0,
						content.indexOf("<" + currentTag));
				String fragment2 = content
						.substring(content.indexOf("<" + currentTag));
				String fragment3 = fragment2
						.substring(fragment2.indexOf("</" + currentTag + ">")
								+ 3 + currentTag.length());
				
				content = fragment1 + fragment3;
				
				proceed = content.contains("<" + currentTag)
						&& content.contains("</" + currentTag + ">");
			}
		}
		
		if (content.contains("<dt class=\"note\">")) {
			// if there are notes attached make sure that every note has a date
			// attached (even an empty one)
			
			String fragment1 = content
					.substring(0, content.indexOf("<dt class=\"note\">"))
					.trim();
			String fragment2 = content
					.substring(content.indexOf("<dt class=\"note\">")).trim();
			
			boolean proceed = true;
			
			while (proceed) {
				// add the necessary "Posted on"
				if (!fragment1.substring(fragment1.lastIndexOf("\n"))
						.contains("<dd class=\"notedate\">Posted on")) {
					fragment1 = fragment1
							+ "\n<dd class=\"notedate\">Posted on\n";
				}
				
				// check if there are futher notes
				proceed = fragment2.substring(fragment2.indexOf("\n"))
						.contains("<dt class=\"note\">");
				
				if (proceed) {
					// reassemble fragments to process further notes
					String previousNoteLine = fragment2.substring(0,
							fragment2.indexOf("\n"));
					String helper = fragment2
							.substring(previousNoteLine.length());
					String fragment3 = helper.substring(0,
							helper.indexOf("<dt class=\"note\">"));
					
					fragment1 += "\n" + previousNoteLine + "\n"
							+ fragment3.trim();
					fragment1 = fragment1.trim();
					
					fragment2 = fragment2.substring(
							previousNoteLine.length() + fragment3.length());
					fragment2 = fragment2.trim();
				} else {
					// reset content
					content = fragment1 + "\n" + fragment2;
				}
			}
			
			// make sure that the user name stands in it's own line
			content = content.replace("<dd class=\"note\">",
					"\n<dd class=\"note\">");
		}
		
		// mark parameter
		content = content.replace("<dd class=\"param\">", "\nparam:\n");
		
		// keep code markdown
		content = content.replace("<code>", " " + SQDev.CODE.getOpener());
		content = content.replace("</code>", SQDev.CODE.getCloser() + " ");
		
		String commandInfoLine = content
				.substring(content.indexOf("<div class=\"gvi\">"));
		commandInfoLine = commandInfoLine.substring(0,
				commandInfoLine.indexOf("\n"));
		
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
		
		// remove all unnecessary HTML escape stuff
		content = content.replaceAll("&[^;\\s]*;", " ");
		
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
			content = "#CommandInfo\n" + commandInfo.trim()
					+ "\n#EndCommandInfo\n\n" + content;
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
		
		content = content.substring(content.indexOf("#EndCommandInfo") + 15)
				.trim();
		
		// add the name of the command
		commandInfo = "Name: " + content.substring(0, content.indexOf("\n"))
				.trim().replace(" ", "_") + "\n" + commandInfo;
		
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
				if (!currentCategory.isEmpty()
						&& (currentLine.startsWith("Categories")
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
		categories[CATEGORY_DESCRIPTION] = description
				.substring(description.indexOf("\n")).trim();
		categories[CATEGORY_SYNTAX] = syntax.substring(syntax.indexOf("\n"))
				.trim();
		categories[CATEGORY_EXAMPLES] = examples
				.substring(examples.indexOf("\n")).trim();
		categories[CATEGORY_NOTES] = notes.substring(notes.indexOf("\n"))
				.trim();
		
		return categories;
	}
	
	/**
	 * Applies the given commandInfo to the given command
	 * 
	 * @param command
	 *            The command the info should get added to
	 * @param info
	 *            The info to add up
	 * @throws SQDevException
	 */
	private void applyCommandInfo(SQFCommand command, String info)
			throws SQDevException {
		String name = info
				.substring(info.indexOf("Name:") + 5, info.indexOf("\n"))
				.trim();
		
		if (!name.toLowerCase().equals(command.getKeyword().toLowerCase())) {
			throw new SQDevException(
					"The given commandInfo does not specify the same name (\""
							+ name + "\") as the SQFCommand object (\""
							+ command.getKeyword() + "\")!");
		}
		
		// remove processed line
		info = info.substring(info.indexOf("\n") + 1);
		
		for (String currentLine : info.split("\n")) {
			currentLine = currentLine.trim();
			
			if (currentLine.startsWith("Arguments:")) {
				// store argument's locality
				String argumentsLocality = currentLine.substring(10).trim();
				
				command.setArgumentLocality(
						ELocality.resolve(argumentsLocality));
			} else {
				if (currentLine.startsWith("Effects:")) {
					// store effects locality
					String effectsLocality = currentLine.substring(8).trim();
					
					command.setEffectLocality(
							ELocality.resolve(effectsLocality));
				} else {
					if (currentLine.equals("ServerExecution: true")) {
						// add a note when a server execution is necessary
						command.addNote(
								"This scripting command must be executed on the server"
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
				examples = examples.substring(
						examples.indexOf(exampleNum) + exampleNum.length())
						.trim();
				
				counter++;
				
				String nextExampleNum = "Example " + counter + ":";
				
				if (examples.contains(nextExampleNum)) {
					// only take the content until next example starts
					String currentExample = examples
							.substring(0, examples.indexOf(nextExampleNum))
							.trim();
					
					command.addExample(currentExample);
					
					// remove processed examples from the examples String
					examples = examples.substring(currentExample.length())
							.trim();
					
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
	 * @throws SQDevException
	 */
	private void applyNotes(SQFCommand command, String notes)
			throws SQDevException {
		String currentNote = "";
		boolean skippedName = false;
		
		if (!notes.contains("Posted on")) {
			throw new SQDevException("The notes of " + command.getKeyword()
					+ "are not in the proper format!");
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
	 * into the proper format
	 * 
	 * @param command
	 *            The command the notes should be applied to
	 * @param syntaxContent
	 *            The syntax with it's parameters that should be applied to the
	 *            command
	 * @throws SQDevException
	 */
	private void applySyntax(SQFCommand command, String syntaxContent)
			throws SQDevException {
		
		String[][] syntaxes = splitSyntaxes(syntaxContent, command);
		
		for (String[] currentSyntax : syntaxes) {
			String syntax = currentSyntax[SYNTAXPART_SYNTAX];
			
			// add the raw syntax to the command
			command.addRawSyntax(syntax);
			
			Pattern arrayPattern = Pattern.compile("\\[[^\\[\\]]*\\]");
			Matcher arrayMatcher = arrayPattern.matcher(syntax);
			
			// replace all array constructs with Array type keyword
			while (arrayMatcher.find()) {
				syntax = arrayMatcher.replaceAll(EDataType.ARRAY.toString());
				
				arrayMatcher = arrayPattern.matcher(syntax);
			}
			
			syntax = processSyntax(syntax, currentSyntax[SYNTAXPART_PARAMETERS],
					command);
			
			DataTypeList returnTypes = DataTypeList
					.fillWith(currentSyntax[SYNTAXPART_RETURN_VALUE]);
			
			try {
				if (syntax.contains(String.valueOf(OPTIONAL_MARKER))) {
					// create own syntaxes for each optional parameter
					String[] areas = TextUtils.getTextAreas(syntax);
					int commandIndex = -1;
					
					List<String> possibleLeadingArgCombinations = new ArrayList<String>();
					List<String> possibleTrailingArgCombinations = new ArrayList<String>();
					
					for (int i = 0; i < areas.length; i++) {
						String currentArea = areas[i];
						
						if (currentArea.toLowerCase()
								.equals(command.getKeyword().toLowerCase())) {
							commandIndex = i;
							continue;
						}
						
						if (currentArea
								.endsWith(String.valueOf(OPTIONAL_MARKER))) {
							if (commandIndex < 0) {
								// optional arg before command
								StringBuilder builder = new StringBuilder();
								
								for (int j = 0; j < i; j++) {
									// assemble parameter combi
									builder.append(areas[j].replace(
											String.valueOf(OPTIONAL_MARKER), "")
											+ " ");
								}
								
								possibleLeadingArgCombinations
										.add(builder.toString().trim());
							} else {
								// optional arg after command
								StringBuilder builder = new StringBuilder();
								
								for (int j = commandIndex + 1; j <= i; j++) {
									// assemble parameter combi
									builder.append(areas[j].replace(
											String.valueOf(OPTIONAL_MARKER), "")
											+ " ");
								}
								
								possibleTrailingArgCombinations
										.add(builder.toString().trim());
							}
						}
					}
					
					// make sure the loops below iterates at least once
					if (possibleLeadingArgCombinations.size() == 0) {
						// In case there are only non-optional leading params
						StringBuilder builder = new StringBuilder();
						
						for (int i = 0; i < commandIndex; i++) {
							builder.append(areas[i] + " ");
						}
						
						possibleLeadingArgCombinations
								.add(builder.toString().trim());
					}
					if (possibleTrailingArgCombinations.size() == 0) {
						// In case there are only non-optional trailing params
						StringBuilder builder = new StringBuilder();
						
						for (int i = commandIndex + 1; i < areas.length; i++) {
							builder.append(areas[i] + " ");
						}
						
						possibleTrailingArgCombinations
								.add(builder.toString().trim());
					}
					
					//TODO: optional args don't process properly... See addMenu
					
					// add all possible syntax variants
					for (String currentLeadingCombination : possibleLeadingArgCombinations) {
						for (String currentTrailingCombination : possibleTrailingArgCombinations) {
							Syntax parsedSyntax = Syntax.parseSyntax(
									currentLeadingCombination + " "
											+ command.getKeyword() + " "
											+ currentTrailingCombination,
									command.getKeyword());
							
							command.addSyntax(parsedSyntax);
							
							command.setReturnType(parsedSyntax, returnTypes);
						}
					}
					
					if (possibleLeadingArgCombinations.size()
							+ possibleTrailingArgCombinations.size() != 0) {
						// add complete syntax
						Syntax parsedSyntax = Syntax.parseSyntax(syntax
								.replace(String.valueOf(OPTIONAL_MARKER), ""),
								command.getKeyword());
						
						command.addSyntax(parsedSyntax);
						
						command.setReturnType(parsedSyntax, returnTypes);
					}
				} else {
					Syntax parsedSyntax = Syntax.parseSyntax(syntax,
							command.getKeyword());
					
					command.addSyntax(parsedSyntax);
					
					command.setReturnType(parsedSyntax, returnTypes);
				}
			} catch (BadSyntaxException e) {
				throw new SQDevException(
						"Failed at parsing syntax for command \""
								+ command.getKeyword() + "\"");
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
	 * @throws SQDevException
	 */
	private String processSyntax(String syntax, String parameter,
			SQFElement element) throws SQDevException {
		for (String currentParameter : parameter.split("param:\n")) {
			// process each listed parameter
			currentParameter = currentParameter.trim();
			
			if (currentParameter.isEmpty()) {
				continue;
			}
			
			// make a copy in order to avoid case sensitive problems
			String syntaxCopy = syntax.toLowerCase();
			
			String[] elements = TextUtils.getTextAreas(currentParameter);
			
			// make sure parameter are in proper format
			if (!elements[0].contains(":") || elements.length == 1) {
				if (elements.length > 2 && (elements[1].equals(":")
						|| elements[1].equals("-"))) {
					// add needed colon
					elements[0] = elements[0] + ":";
					// Move parameter data type
					elements[1] = elements[2];
				} else {
					throw new SQDevException(
							"Unexpected parameter format - missing\":\" at "
									+ elements[0]);
				}
			}
			
			String parameterName = elements[0]
					.substring(0, elements[0].indexOf(":")).toLowerCase();
			
			// remove dots from dataType
			String value = elements[1].replace(".", "");
			
			String[] syntaxElements = TextUtils.getTextAreas(syntax);
			String[] syntaxCopyElements = TextUtils.getTextAreas(syntaxCopy);
			
			for (int i = 0; i < syntaxCopyElements.length; i++) {
				if (syntaxCopyElements[i].equals(parameterName)) {
					// replace placeholder with actual value
					value = formatDataType(value, element.getKeyword());
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
			if (syntaxContent.substring(syntaxContent.indexOf("Return Value:"))
					.trim().isEmpty()) {
				syntaxContent += " Nothing";
			}
		}
		
		if (syntaxContent.startsWith("Syntax:")) {
			// make the syntax start directly without prefix "Syntax:"
			syntaxContent = syntaxContent.substring(7).trim();
		}
		
		if (syntaxContent.contains("Parameters:")) {
			// remove unnecessary statement
			syntaxContent = syntaxContent.substring(0,
					syntaxContent.indexOf("Parameters:")) + "\n"
					+ syntaxContent.substring(
							syntaxContent.indexOf("Parameters:") + 11);
		}
		
		// make each syntax part stand in it's own line
		
		syntaxContent = syntaxContent.substring(0,
				syntaxContent.indexOf("Return Value:")) + "\n"
				+ syntaxContent
						.substring(syntaxContent.indexOf("Return Value:"));
		
		syntaxContent = syntaxContent
				.substring(0, syntaxContent.indexOf("Return Value:") + 13)
				.trim()
				+ " "
				+ syntaxContent
						.substring(syntaxContent.indexOf("Return Value:") + 13)
						.trim();
		
		String syntaxLine = syntaxContent.substring(0,
				syntaxContent.indexOf("\n"));
		
		if (syntaxLine.contains("=")) {
			// remove asignments from syntax
			syntaxLine = syntaxLine.substring(syntaxLine.indexOf("=") + 1)
					.trim();
		}
		
		// remove version tags that are not necessarily between brackets
		syntaxLine = syntaxLine.replaceAll("[sS]ince [aA]rm[aA][^)]*", "");
		
		return syntaxLine
				+ syntaxContent.substring(syntaxContent.indexOf("\n"));
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
	 * @throws SQDevException
	 */
	private String[][] splitSyntaxes(String syntaxContent, SQFElement element)
			throws SQDevException {
		// split the syntaxes and their components
		ArrayList<ArrayList<String>> syntaxes = new ArrayList<ArrayList<String>>(
				1);
		
		for (String currentSyntaxContent : syntaxContent
				.split("Alternative Syntax")) {
			// format the content
			currentSyntaxContent = formatSyntaxContent(currentSyntaxContent);
			
			ArrayList<String> currentSyntaxComponents = new ArrayList<String>(
					3);
			
			// get the syntax
			String currentSyntax = currentSyntaxContent.substring(0,
					currentSyntaxContent.indexOf("\n"));
			
			// trim syntaxContent to remove processed line
			currentSyntaxContent = currentSyntaxContent
					.substring(currentSyntax.length()).trim();
			
			// get the parameters
			String currentParameter = currentSyntaxContent.substring(0,
					currentSyntaxContent.indexOf("Return Value:"));
			
			// trim syntaxContent to remove processed line
			currentSyntaxContent = currentSyntaxContent
					.substring(currentParameter.length()).trim();
			
			currentSyntaxContent += "\n";
			
			String currentReturnValue = currentSyntaxContent.substring(
					currentSyntaxContent.indexOf("Return Value:") + 13);
			currentReturnValue = currentReturnValue.substring(0,
					currentReturnValue.indexOf("\n"));
			
			// store the gathered values
			currentSyntaxComponents.add(SYNTAXPART_SYNTAX,
					formatRawSyntax(currentSyntax));
			currentSyntaxComponents.add(SYNTAXPART_PARAMETERS,
					formatParameters(currentParameter, element));
			currentSyntaxComponents.add(SYNTAXPART_RETURN_VALUE,
					formatReturnValue(currentReturnValue,
							element.getKeyword()));
			
			syntaxes.add(currentSyntaxComponents);
		}
		
		String[][] syntaxArray = new String[syntaxes.size()][];
		
		for (int i = 0; i < syntaxes.size(); i++) {
			syntaxArray[i] = syntaxes.get(i)
					.toArray(new String[syntaxes.get(i).size()]);
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
		
		final List<Character> allowedSpecials = new ArrayList<Character>() {
			private static final long serialVersionUID = -7392233157557632681L;

			{
				add('[');
				add(']');
				add(',');
				add('_');
				add('-');
			}
		};
		OutputStream out = new ByteArrayOutputStream();
		// remove all characters that seem to not fit in there
		for (char c : syntax.toCharArray()) {
			if (Character.isLetterOrDigit(c) || Character.isWhitespace(c) || allowedSpecials.contains(c)) {
				try {
					out.write(c);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Omitting character " + c);
			}
		}
		
		return out.toString();
	}
	
	/**
	 * Brings the given parameters into the proper format
	 * 
	 * @param parameters
	 *            The parameters to format
	 * @param element
	 *            The <code>SQFElement</code> the given parameters belong to
	 * @return The formatted parameters
	 * @throws SQDevException
	 */
	private String formatParameters(String parameters, SQFElement element)
			throws SQDevException {
		String[] paramArray = parameters.split("param:\n");
		parameters = "";
		
		for (String currentParameter : paramArray) {
			if (currentParameter.isEmpty()) {
				continue;
			}
			// process alternative data types
			parameters += "$PARAM$ "
					+ formatAlternativeDataTypes(currentParameter);
		}
		
		String[] areas;
		try {
			areas = TextUtils.getTextAreas(parameters);
		} catch (BadSyntaxException e) {
			throw new SQDevException("Can't format parameter", e);
		}
		
		StringBuilder builder = new StringBuilder();
		
		boolean matchedColon = false;
		boolean matchedDataType = false;
		boolean markedOptional = false;
		boolean optionalMarkingNeeded = false;
		int dataTypeEndOffset = -1;
		
		for (int i = 0; i < areas.length; i++) {
			String currentArea = areas[i];
			
			if (currentArea.toLowerCase().contains("optional")) {
				if (!markedOptional) {
					// check that it's at an appropriate position
					if (dataTypeEndOffset < 0
							|| builder.length() - dataTypeEndOffset < 40
							|| ArrayUtils.containsElementInmaxDistance(areas,
									"$param$", i, 3)) {
						
						// mark optional parameter
						if (dataTypeEndOffset < 0) {
							optionalMarkingNeeded = true;
						} else {
							builder.insert(dataTypeEndOffset, OPTIONAL_MARKER);
						}
						
						markedOptional = true;
					}
				}
			}
			
			if (currentArea.startsWith("(")) {
				if (matchedDataType) {
					// only maintain brackets after dataType has been
					// matched
					builder.append(" " + currentArea);
				}
			} else {
				switch (currentArea.toLowerCase()) {
					case "$param$":
						// maintan proper format
						currentArea = "param:\n";
						
						// new parameter beginning -> reset colon and dataType
						// matches
						matchedColon = false;
						matchedDataType = false;
						markedOptional = false;
						optionalMarkingNeeded = false;
						dataTypeEndOffset = -1;
						break;
					
					default:
						if (matchedColon) {
							// colon has been matched therefore this must be the
							// data type
							matchedDataType = true;
							
							if (optionalMarkingNeeded && Character
									.isLetter(currentArea.trim().charAt(0))) {
								currentArea += OPTIONAL_MARKER;
								optionalMarkingNeeded = false;
							}
						}
				}
				
				if (matchedDataType && dataTypeEndOffset < 0
						&& Character.isLetter(currentArea.trim().charAt(0))) {
					dataTypeEndOffset = builder.length() + currentArea.length()
							+ 1;
				}
				
				builder.append(" " + currentArea);
				
				if (currentArea.endsWith(":")) {
					matchedColon = true;
				}
			}
		}
		
		parameters = builder.toString().trim().replace(" :", ":").replace(" / ",
				DataTypeList.TYPE_SEPERATOR);
		
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
		
		
		// prevent trailing commas from being misinterpreted
		Pattern pattern = Pattern.compile("\\bor\\b");
		
		Matcher matcher = pattern.matcher(relevantPart);
		
		if (matcher.find()) {
			relevantPart = relevantPart.substring(0, matcher.start())
					+ relevantPart.substring(matcher.start()).replace(",", " ");
		}
		
		
		// replace all seperators by slash
		relevantPart = relevantPart.replace(",", DataTypeList.TYPE_SEPERATOR)
				.replaceAll("\\bor\\b", DataTypeList.TYPE_SEPERATOR);
		
		// make sure the seperators are not seperated from the next word
		while (relevantPart.contains(" /")) {
			relevantPart = relevantPart.replace(" /",
					DataTypeList.TYPE_SEPERATOR);
		}
		while (relevantPart.contains("/ ")) {
			relevantPart = relevantPart.replace("/ ",
					DataTypeList.TYPE_SEPERATOR);
		}
		
		return prefix + relevantPart + postfix;
	}
	
	/**
	 * Brings the given returnValue into the proper format
	 * 
	 * @param returnValue
	 *            The returnValue to format
	 * @param commandName
	 *            The name of the currently processed command
	 * @return The formatted returnValue
	 * @throws SQDevException
	 */
	private String formatReturnValue(String returnValue, String commandName)
			throws SQDevException {
		returnValue = returnValue.replace(".", " ").trim().toLowerCase();
		
		// remove everything that stands in between round brackets
		Pattern bracketPattern = Pattern.compile("\\([^\\(\\)]*\\)");
		Matcher bracketMatcher = bracketPattern.matcher(returnValue);
		
		while (bracketMatcher.find()) {
			returnValue = bracketMatcher.replaceAll("");
			
			bracketMatcher = bracketPattern.matcher(returnValue);
		}
		
		// prevent trailing commas from being misinterpreted
		Pattern pattern = Pattern.compile("\\bor\\b");
		
		Matcher matcher = pattern.matcher(returnValue);
		
		if (matcher.find()) {
			returnValue = returnValue.substring(0, matcher.start())
					+ returnValue.substring(matcher.start()).replace(",", " ");
		}
		
		
		// process multiple return values
		returnValue = returnValue.replace(",", DataTypeList.TYPE_SEPERATOR)
				.replaceAll("\\bor\\b", DataTypeList.TYPE_SEPERATOR);
		
		// remove leading whitespace in fromt of seperator
		while (returnValue.contains(" " + DataTypeList.TYPE_SEPERATOR)) {
			returnValue = returnValue.replace(" " + DataTypeList.TYPE_SEPERATOR,
					DataTypeList.TYPE_SEPERATOR);
		}
		// remove trailing whitespace in fromt of seperator
		while (returnValue.contains(DataTypeList.TYPE_SEPERATOR + " ")) {
			returnValue = returnValue.replace(DataTypeList.TYPE_SEPERATOR + " ",
					DataTypeList.TYPE_SEPERATOR);
		}
		
		if (returnValue.contains("-")) {
			// remove extra info
			returnValue = returnValue.substring(0, returnValue.indexOf("-"));
			returnValue = returnValue.trim();
		}
		
		if (returnValue.contains(":")) {
			// remove extra info
			returnValue = returnValue.substring(0, returnValue.indexOf(":"));
			returnValue = returnValue.trim();
		}
		
		if (returnValue.contains(" ")) {
			// remove additional stuff
			returnValue = returnValue.substring(0, returnValue.indexOf(" "));
			returnValue = returnValue.trim();
		}
		
		returnValue = formatDataType(returnValue, commandName);
		
		return returnValue;
	}
	
	/**
	 * Formats the given dataType according to <code>EDataType</code>
	 * 
	 * @param type
	 *            The raw data type format
	 * @param commandName
	 *            The name of the currently processed command
	 * @return The fornatted dataType
	 * @throws SQDevException
	 *             If the dataType couldn't be resolved
	 */
	private String formatDataType(String type, String commandName)
			throws SQDevException {
		StringBuilder newDataType = new StringBuilder();
		
		boolean isOptionalType = type.trim()
				.endsWith(String.valueOf(OPTIONAL_MARKER));
		
		if (isOptionalType) {
			type = type.substring(0, type.indexOf(OPTIONAL_MARKER));
		}
		
		// remove unfitting characters
		for (char currentChar : new char[] { '.', ':', '-' }) {
			type = type.replace(currentChar, ' ');
		}
		
		type = type.trim();
		
		for (String currentType : type.split(DataTypeList.TYPE_SEPERATOR)) {
			if (currentType.isEmpty()) {
				continue;
			}
			
			EDataType dataType = EDataType.resolve(currentType);
			
			if (dataType == null) {
				throw new SQDevException("Unknown dataType \"" + currentType
						+ "\" in command \"" + commandName + "\"");
			} else {
				newDataType.append((newDataType.length() == 0)
						? dataType.toString()
						: DataTypeList.TYPE_SEPERATOR + dataType.toString());
			}
		}
		
		if (newDataType.length() == 0) {
			throw new SQDevException(
					"Empty data type in command \"" + commandName + "\"");
		}
		
		return (isOptionalType) ? newDataType.toString() + OPTIONAL_MARKER
				: newDataType.toString();
	}
}
