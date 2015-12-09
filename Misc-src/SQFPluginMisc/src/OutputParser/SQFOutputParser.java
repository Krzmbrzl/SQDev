package OutputParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import SQF.Functions;
import SQFBaseParser.SQFCommandBaseVisitor;
import SQFBaseParser.SQFCommandLexer;
import SQFBaseParser.SQFCommandParser;

public class SQFOutputParser {
	public static void main(String[] args) {
		String antlrInput = "";
		String commandFilePath = "";
		
		@SuppressWarnings("unused")
		String outputDirectoryPath = "";
		String grammarDirectory = "";
		String modelDirectory = "";
		
		for (String argument : args) {
			// The path to the file that should be parsed/processed
			if (argument.startsWith("/cF=") || argument.startsWith("/cf=")) {
				argument = argument.substring(4);
				commandFilePath = argument;
			}
			if (argument.startsWith("/op=") || argument.startsWith("/oP=")) {
				// The path to the directory where the output of the
				// serialization shouldbe stored
				argument = argument.substring(4);
				outputDirectoryPath = argument;
			}
			
			if (argument.startsWith("/gf=") || argument.startsWith("/gF=")) {
				argument = argument.substring(4);
				grammarDirectory = argument;
				
			}
			if (argument.startsWith("/mf=") || argument.startsWith("/mF=")) {
				argument = argument.substring(4);
				modelDirectory = argument;
			}
		}
		
		File inputFile = new File(commandFilePath);
		
		try {
			// read the inputFile into a single string
			
			BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
			
			String line;
			
			while ((line = inputReader.readLine()) != null) {
				antlrInput = antlrInput + line + "\n";
			}
			
			inputReader.close();
			
			System.out.println("Finished reading file");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// write every array as an array and don't care about length, types etc.
		// TODO: remove
		antlrInput = markAsArray(antlrInput);
		
		// create CharStream that reads from the input file
		ANTLRInputStream input = new ANTLRInputStream(antlrInput);
		
		// create a lexer that feeds off of input CharStream
		SQFCommandLexer lexer = new SQFCommandLexer(input);
		
		// create a buffer of tokens pulled from the lexer
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		// create a parser that feeds off the tokens buffer
		SQFCommandParser parser = new SQFCommandParser(tokens);
		
		ParseTree tree = parser.start();
		System.out.println(tree.toStringTree(parser));
		
		SQFCommandVisitor visitor = new SQFCommandVisitor();
		
		visitor.visit(tree);
		
		// data collected
		// now sort them into categories
		
		syntaxVariantArchive archive = new syntaxVariantArchive();
		
		for (int i = 0; i < SQFCommandVisitor.syntaxLists.size(); i++) {
			syntax currentCommand = SQFCommandVisitor.syntaxLists.get(i);
			
			for (int k = 0; k < currentCommand.getSyntaxList().size(); k++) {
				String syntax = currentCommand.getSyntaxList().get(k);
				
				if (!archive.contains(syntax)) {
					archive.add(syntax);
					
					// instantly add the commandName to that syntax
					int pos = archive.find(syntax);
					syntaxVariant synVar = archive.get(pos); // select
																// syntaxVariant
																// the
																// commandName
																// belongs to
					
					String commandName = currentCommand.getCommandName(); // select
																			// commandName
					
					synVar.addCommand(commandName); // add the commandName to
													// the syntaxVariant
				} else {
					// if archive already contains this syntax just add the
					// corresponding command to this syntaxVariant
					int pos = archive.find(syntax); // get pos of the syntax in
													// the archive
					
					if (pos < 0) {
						// if syntax can't be found
						System.err.println("syntax konnte nicht gefunden werden -> wird übersprungen");
						continue;
					}
					
					syntaxVariant synVar = archive.get(pos); // select
																// syntaxVariant
																// the
																// commandName
																// belongs to
					
					String commandName = currentCommand.getCommandName(); // select
																			// commandName
					
					synVar.addCommand(commandName); // add the commandName to
													// the syntaxVariant
				}
			}
		}
		
		archive.format();
		
		System.out.println("number of total variants: " + archive.size() + "\n");
		
		// archive.store(outputDirectoryPath);
		
		String[] types = null;
		
		for (int i = 0; i < SQFCommandVisitor.syntaxLists.size(); i++) {
			String returnType = SQFCommandVisitor.syntaxLists.get(i).getReturnType();
			
			if ((types == null || !Functions.isIn(types, returnType, true)) && !returnType.equals("None")) {
				// if type hasn't matched yet
				
				// create array which is longer about one position than the old
				// types-array
				String[] newTypes;
				int length = -1;
				
				if (types == null) {
					length = 1;
				} else {
					length = types.length;
				}
				
				if (types == null) {
					newTypes = new String[length];
				} else {
					newTypes = new String[length + 1];
				}
				
				for (int k = 0; k <= (length); k++) {
					if (types == null) {
						length = 0;
					}
					if (k < length) {
						// if there are still elements from the old array use
						// those
						newTypes[k] = types[k];
					} else {
						// otherwise write the new one in
						newTypes[k] = returnType;
					}
				}
				// overwrite the old array with the new one
				types = newTypes;
			}
		}
		
		// create a dummy syntax conatining all returnTypes
		String helpSyntax = "commandNameKeyword";
		for (String returnType : types) {
			helpSyntax += " " + returnType;
		}
		
		// add dummy syntax to the archive so getParameter will also process
		// these parameters
		archive.add(helpSyntax);
		
		String[] parameter = archive.getParameter();
		
		// remove dummy syntax from the archive again
		archive.remove(archive.size() - 1);
		
		System.out.println(parameter.length + "\n");
		
		for (String current : parameter) {
			System.out.println(current);
		}
		
		// System.out.println(archive.toString());
		
		System.out.println("\nSortiere nach return \n");
		
		syntaxArchiveList synArchiveList = new syntaxArchiveList();
		
		syntaxArchive numberReturner = new syntaxArchive();
		numberReturner.setName("NumberReturner");
		syntaxArchive objectReturner = new syntaxArchive();
		objectReturner.setName("ObjectReturner");
		syntaxArchive arrayReturner = new syntaxArchive();
		arrayReturner.setName("ArrayReturner");
		syntaxArchive stringReturner = new syntaxArchive();
		stringReturner.setName("StringReturner");
		syntaxArchive codeReturner = new syntaxArchive();
		codeReturner.setName("CodeReturner");
		syntaxArchive anythingReturner = new syntaxArchive();
		anythingReturner.setName("AnythingReturner");
		syntaxArchive booleanReturner = new syntaxArchive();
		booleanReturner.setName("BooleanReturner");
		syntaxArchive positionReturner = new syntaxArchive();
		positionReturner.setName("PositionReturner");
		syntaxArchive controlReturner = new syntaxArchive();
		controlReturner.setName("ControlReturner");
		syntaxArchive groupReturner = new syntaxArchive();
		groupReturner.setName("GroupReturner");
		syntaxArchive teamReturner = new syntaxArchive();
		teamReturner.setName("TeamReturner");
		syntaxArchive sideReturner = new syntaxArchive();
		sideReturner.setName("SideReturner");
		syntaxArchive displayReturner = new syntaxArchive();
		displayReturner.setName("DisplayReturner");
		syntaxArchive positionaslReturner = new syntaxArchive();
		positionaslReturner.setName("PositionASLReturner");
		syntaxArchive positionatlReturner = new syntaxArchive();
		positionatlReturner.setName("PositionATLReturner");
		syntaxArchive locationReturner = new syntaxArchive();
		locationReturner.setName("LocationReturner");
		syntaxArchive configReturner = new syntaxArchive();
		configReturner.setName("ConfigReturner");
		syntaxArchive position2dReturner = new syntaxArchive();
		position2dReturner.setName("Position2DReturner");
		syntaxArchive integerReturner = new syntaxArchive();
		integerReturner.setName("IntegerReturner");
		syntaxArchive position3dReturner = new syntaxArchive();
		position3dReturner.setName("Position3DReturner");
		syntaxArchive colorReturner = new syntaxArchive();
		colorReturner.setName("ColorReturner");
		syntaxArchive scripthandleReturner = new syntaxArchive();
		scripthandleReturner.setName("scriptHandleReturner");
		syntaxArchive waypointReturner = new syntaxArchive();
		waypointReturner.setName("waypointReturner");
		syntaxArchive editorobjectReturner = new syntaxArchive();
		editorobjectReturner.setName("EditorObjectReturner");
		syntaxArchive scriptReturner = new syntaxArchive();
		scriptReturner.setName("ScriptReturner");
		syntaxArchive namespaceReturner = new syntaxArchive();
		namespaceReturner.setName("NamespaceReturner");
		syntaxArchive exceptionReturner = new syntaxArchive();
		exceptionReturner.setName("ExceptionReturner");
		syntaxArchive vector3dReturner = new syntaxArchive();
		vector3dReturner.setName("Vector3DReturner");
		syntaxArchive taskReturner = new syntaxArchive();
		taskReturner.setName("TaskReturner");
		syntaxArchive noneReturner = new syntaxArchive();
		noneReturner.setName("NoneReturner");
		
		for (syntax current : SQFCommandVisitor.syntaxLists) {
			String returnType = current.getReturnType().toLowerCase();
			returnType = format(returnType);
			current.setReturnType(returnType); // set formatted returnType
			
			// sort according to the returnValues of the command
			switch (returnType) {
				case "Number":
					numberReturner.add(current);
					break;
				
				case "Object":
					objectReturner.add(current);
					break;
				
				case "Array":
					arrayReturner.add(current);
					break;
				
				case "String":
					stringReturner.add(current);
					break;
				
				case "Code":
					codeReturner.add(current);
					break;
				
				case "Anything":
					anythingReturner.add(current);
					break;
				
				case "Boolean":
					booleanReturner.add(current);
					break;
				case "Position":
					positionReturner.add(current);
					break;
				
				case "Control":
					controlReturner.add(current);
					break;
				
				case "Group":
					groupReturner.add(current);
					break;
				
				case "Team":
					teamReturner.add(current);
					break;
				
				case "Side":
					sideReturner.add(current);
					break;
				
				case "Display":
					displayReturner.add(current);
					break;
				
				case "PositionASL":
					positionaslReturner.add(current);
					break;
				
				case "PositionATL":
					positionatlReturner.add(current);
					break;
				
				case "Location":
					locationReturner.add(current);
					break;
				
				case "Task":
					taskReturner.add(current);
					break;
				
				case "Config":
					configReturner.add(current);
					break;
				
				case "Position2D":
					position2dReturner.add(current);
					break;
				
				case "Integer":
					integerReturner.add(current);
					break;
				
				case "Position3D":
					position3dReturner.add(current);
					break;
				
				case "Color":
					colorReturner.add(current);
					break;
				
				case "ScriptHandle":
					scripthandleReturner.add(current);
					break;
				
				case "Waypoint":
					waypointReturner.add(current);
					break;
				
				case "EditorObject":
					editorobjectReturner.add(current);
					break;
				
				case "Script":
					scriptReturner.add(current);
					break;
				
				case "Namespace":
					namespaceReturner.add(current);
					break;
				
				case "Exception":
					exceptionReturner.add(current);
					break;
				
				case "Vector3D":
					vector3dReturner.add(current);
					break;
				
				case "None":
					noneReturner.add(current);
					break;
				
				default:
					System.err.println("Unknown parameter '" + returnType + "'");
					break;
			}
		}
		
		synArchiveList.add(numberReturner);
		synArchiveList.add(objectReturner);
		synArchiveList.add(arrayReturner);
		synArchiveList.add(stringReturner);
		synArchiveList.add(codeReturner);
		synArchiveList.add(anythingReturner);
		synArchiveList.add(booleanReturner);
		synArchiveList.add(positionReturner);
		synArchiveList.add(controlReturner);
		synArchiveList.add(groupReturner);
		synArchiveList.add(teamReturner);
		synArchiveList.add(sideReturner);
		synArchiveList.add(displayReturner);
		synArchiveList.add(positionaslReturner);
		synArchiveList.add(positionatlReturner);
		synArchiveList.add(locationReturner);
		synArchiveList.add(configReturner);
		synArchiveList.add(position2dReturner);
		synArchiveList.add(integerReturner);
		synArchiveList.add(position3dReturner);
		synArchiveList.add(colorReturner);
		synArchiveList.add(scripthandleReturner);
		synArchiveList.add(waypointReturner);
		synArchiveList.add(editorobjectReturner);
		synArchiveList.add(scriptReturner);
		synArchiveList.add(namespaceReturner);
		synArchiveList.add(exceptionReturner);
		synArchiveList.add(vector3dReturner);
		synArchiveList.add(taskReturner);
		synArchiveList.add(noneReturner);
		
		synArchiveList.sort();
		synArchiveList.format();
		
		System.out.println("Fertig sortiert");
		
		synArchiveList.write(grammarDirectory, modelDirectory);
		
	}
	
	public static class SQFCommandVisitor extends SQFCommandBaseVisitor<Void> {
		int								count		= 0;
		
		public static ArrayList<syntax>	syntaxLists	= new ArrayList<syntax>();
		
		public Void visitCommandSyntax(SQFCommandParser.CommandSyntaxContext ctx) {
			String commandName = ctx.getChild(0).getText(); // get command name
			
			ParseTree command = ctx.getChild(1); // create direct access to rule
													// command
			
			syntax syntax = getSyntax(command, commandName);
			
			System.out.println(syntax.toString());
			
			syntax.format();
			
			syntaxLists.add(syntax);
			
			System.out.println();
			
			count++;
			
			return null;
		}
		
		/**
		 * This function will find out the syntax of the command
		 * 
		 * @param commandRule
		 *            The ANTLRParserRule that contains the syntax
		 * @param syntax
		 *            The syntax-object the new syntax should be added
		 * @param childIndex
		 *            The index of the child the function should start searching for a syntax
		 * @return The syntax as an syntaxObject
		 */
		public syntax getSyntax(ParseTree commandRule, syntax syntax, int childIndex) {
			int childCount = commandRule.getChildCount();
			
			if (!(childIndex < childCount) || childIndex < 0) {
				// if index is not applicable return the given syntax
				return syntax;
			}
			
			String singleSyntax = "";
			
			for (int i = childIndex; i < childCount; i++) {
				ParseTree currentChild = commandRule.getChild(i);
				String nextElement = currentChild.getText();
				
				if (currentChild.getChildCount() > 1) {
					// if currentChild has more that one children it is an
					// alternative syntax
					syntax.addToSyntaxList(singleSyntax);
					syntax = getSyntax(currentChild.getChild(1), syntax, childIndex);
					return syntax;
				} else {
					if (nextElement.equals("=")) {
						syntax.setReturnType(singleSyntax);
						singleSyntax = "";
					} else {
						singleSyntax += nextElement + " ";
					}
				}
			}
			
			syntax.addToSyntaxList(singleSyntax);
			
			return syntax;
		}
		
		/**
		 * This function will find out the syntax of the command
		 * 
		 * @param commandRule
		 *            The ANTLRParserRule that contains the syntax
		 * @param commandName
		 *            The name of the command
		 * @return The syntax as an syntaxObject
		 */
		public syntax getSyntax(ParseTree commandRule, String commandName) {
			syntax syntax = new syntax();
			syntax.setCommandName(commandName);
			
			syntax returnSyntax = getSyntax(commandRule, syntax);
			
			return returnSyntax;
		}
		
		/**
		 * This function will find out the syntax of the command
		 * 
		 * @param commandRule
		 *            The ANTLRParserRule that contains the syntax
		 * @param syntax
		 *            The syntax-object the new syntax should be added
		 * @return The syntax as an syntaxObject
		 */
		public syntax getSyntax(ParseTree commandRule, syntax syntax) {
			return getSyntax(commandRule, syntax, 0);
		}
		
	}
	
	/**
	 * This function will format a string, so that different parameter-names get corrected and
	 * unnessecary blanks get removed
	 * 
	 * @param string
	 *            The string that shouöd be formatted
	 * @return The formatted string
	 */
	public static String format(String string) {
		// replace some parameter names which are the same
		if (string.indexOf("number") >= 0) {
			string = string.replaceAll("number", "Number");
		}
		if (string.indexOf("NUMBER") >= 0) {
			string = string.replaceAll("NUMBER", "Number");
		}
		if (string.indexOf("object") >= 0) {
			string = string.replaceAll("object", "Object");
		}
		if (string.indexOf("string") >= 0) {
			string = string.replaceAll("string", "String");
		}
		if (string.indexOf("STRING") >= 0) {
			string = string.replaceAll("STRING", "String");
		}
		if (string.indexOf("anything") >= 0) {
			string = string.replaceAll("anything", "Anything");
		}
		if (string.indexOf("boolean") >= 0) {
			string = string.replaceAll("boolean", "Boolean");
		}
		if (string.indexOf("BOOLEAN") >= 0) {
			string = string.replaceAll("BOOLEAN", "Boolean");
		}
		if (string.indexOf("bool") >= 0) {
			string = string.replaceAll("bool", "Boolean");
		}
		if (string.indexOf("positionasl") >= 0) {
			string = string.replaceAll("positionasl", "PositionASL");
		}
		if (string.indexOf("positionatl") >= 0) {
			string = string.replaceAll("positionatl", "PositionATL");
		}
		if (string.indexOf("any") >= 0) {
			string = string.replaceAll("any", "Anything");
		}
		if (string.indexOf("position2d") >= 0) {
			string = string.replaceAll("position2d", "Position2D");
		}
		if (string.indexOf("position3d") >= 0) {
			string = string.replaceAll("position3d", "Position3D");
		}
		if (string.indexOf("scripthandle") >= 0) {
			string = string.replaceAll("scripthandle", "Script");
		}
		if (string.indexOf("scriptHandle") >= 0) {
			string = string.replace("scriptHandle", "Script");
		}
		if (string.contains("ScriptHandle")) {
			string = string.replace("ScriptHandle", "Script");
		}
		if (string.indexOf("editorOBJECT") >= 0) {
			string = string.replaceAll("editorOBJECT", "EditorObject");
		}
		if (string.indexOf("true") >= 0) {
			string = string.replaceAll("true", "Boolean");
		}
		if (string.indexOf("false") >= 0) {
			string = string.replaceAll("false", "Boolean");
		}
		if (string.indexOf("void") >= 0) {
			string = string.replaceAll("void", "Anything");
		}
		if (string.indexOf("vector3d") >= 0) {
			string = string.replaceAll("vector3d", "Vector3D");
		}
		
		String[] elements = Functions.getElements(string);
		
		for (int i = 0; i < elements.length; i++) {
			String current = elements[i];
			
			if (current.startsWith("[")) {
				String giveAway = current.substring(1, current.length() - 1);
				giveAway = format(giveAway);
				current = "[" + giveAway + "]";
			}
			
			if (current.indexOf("/") >= 0) {
				current = current.replaceAll("/", " | ");
				
				if (!current.startsWith("(")) {
					if (current.endsWith(",")) {
						current = current.substring(0, current.length() - 1);
						current = "(" + current + "),";
					} else {
						current = "(" + current + ")";
					}
				}
			}
			
			if (current.startsWith("(")) {
				String giveAway = current.substring(1, current.indexOf(")"));
				String end = current.substring(current.indexOf(")"));
				giveAway = format(giveAway);
				current = "(" + giveAway + end;
			}
			
			if (!current.substring(0, 1).toUpperCase().equals(current.substring(0, 1))
					&& !current.equals("commandNameKeyword")) {
				
				// if string starts with a lowercase letter
				current = current.substring(0, 1).toUpperCase() + current.substring(1);
			}
			
			current = Functions.reduceSpaceBetween(current);
			
			elements[i] = current;
		}
		
		string = Functions.ArrayToString(elements);
		
		while (string.endsWith(" ")) {
			string = string.substring(0, string.length() - 1);
		}
		
		return string;
	}
	
	/**
	 * This function will mark every element within brackets ("[" and "]") as an Array
	 * 
	 * @param string
	 *            The string to be facilitated
	 * @return
	 */
	public static String markAsArray(String string) {
		while (string.indexOf("[") >= 0) {
			String fragment1 = string.substring(0, string.indexOf("["));
			String fragment2 = string.substring(string.indexOf("["));
			String bracketPart = Functions.getBracketElements(fragment2);
			
			// remove the part with the brackets
			fragment2 = fragment2.substring(bracketPart.length());
			
			string = fragment1 + "Array" + fragment2;
		}
		
		return string;
	}
}