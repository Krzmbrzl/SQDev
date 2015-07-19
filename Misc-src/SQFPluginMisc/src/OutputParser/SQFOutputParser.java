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
import SQF.syntaxArchive;
import SQF.syntaxArchiveList;
import SQFBaseParser.SQFCommandBaseVisitor;
import SQFBaseParser.SQFCommandLexer;
import SQFBaseParser.SQFCommandParser;

public class SQFOutputParser {
	public static void main(String[] args) {
		String antlrInput = "";
		String commandFilePath = "";
		String outputDirectoryPath = "";

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
		}

		File inputFile = new File(commandFilePath);

		try {
			// read the inputFile into a single string

			BufferedReader inputReader = new BufferedReader(new FileReader(
					inputFile));

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
						System.err
								.println("syntax konnte nicht gefunden werden -> wird übersprungen");
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

		System.out.println("number of total variants: " + archive.size() + "\n");

//		archive.store(outputDirectoryPath);

		String[] types = null;

		for (int i = 0; i < SQFCommandVisitor.syntaxLists.size(); i++) {
			String returnType = SQFCommandVisitor.syntaxLists.get(i)
					.getReturnType();

			if ((types == null || !Functions.isIn(types, returnType, true)) && !returnType.equals("None")) {
				//if type hasn't matched yet
				
				//create array which is longer about one position than the old types-array
				String[] newTypes;
				int length = -1;
				
				if(types == null) {
					length = 1;
				}else {
					length = types.length;
				}
				
				if(types == null) {
					newTypes = new String[length];
				}else {
					newTypes = new String[length + 1];
				}
				
				for (int k = 0; k <= (length); k++) {
					if(types == null) {
						length = 0;
					}
					if (k < length) {
						//if there are still elements from the old array use those
						newTypes[k] = types[k];
					}else {
						//otherwise write the new one in
						newTypes[k] = returnType;
					}
				}
				//overwrite the old array with the new one
				types = newTypes;
			}
		}
		
		//create a dummy syntax conatining all returnTypes
		String helpSyntax = "commandNameKeyword";
		for(String returnType : types) {
			helpSyntax += " " + returnType;
		}
		
		//add dummy syntax to the archive so getParameter will also process these parameters
		archive.add(helpSyntax);

		String[] parameter = archive.getParameter();
		
		//remove dummy syntax from the archive again
		archive.remove(archive.size() - 1);

		System.out.println(parameter.length + "\n");

		for (String current : parameter) {
			System.out.println(current);
		}

//		System.out.println(archive.toString());
		
		System.out.println("\nSortiere nach return \n");
		
		syntaxArchiveList synArchiveList = new syntaxArchiveList();
		
		syntaxArchive numberReturner = new syntaxArchive();
		syntaxArchive objectReturner = new syntaxArchive();
		syntaxArchive arrayReturner = new syntaxArchive();
		syntaxArchive stringReturner = new syntaxArchive();
		syntaxArchive codeReturner = new syntaxArchive();
		syntaxArchive anythingReturner = new syntaxArchive();
		syntaxArchive booleanReturner = new syntaxArchive();
		syntaxArchive positionReturner = new syntaxArchive();
		syntaxArchive controlReturner = new syntaxArchive();
		syntaxArchive groupReturner = new syntaxArchive();
		syntaxArchive teamReturner = new syntaxArchive();
		syntaxArchive sideReturner = new syntaxArchive();
		syntaxArchive displayReturner = new syntaxArchive();
		syntaxArchive positionaslReturner = new syntaxArchive();
		syntaxArchive positionatlReturner = new syntaxArchive();
		syntaxArchive locationReturner = new syntaxArchive();
		syntaxArchive configReturner = new syntaxArchive();
		syntaxArchive position2dReturner = new syntaxArchive();
		syntaxArchive integerReturner = new syntaxArchive();
		syntaxArchive position3dReturner = new syntaxArchive();
		syntaxArchive colorReturner = new syntaxArchive();
		syntaxArchive scripthandleReturner = new syntaxArchive();
		syntaxArchive waypointReturner = new syntaxArchive();
		syntaxArchive editorobjectReturner = new syntaxArchive();
		syntaxArchive scriptReturner = new syntaxArchive();
		syntaxArchive namespaceReturner = new syntaxArchive();
		syntaxArchive exceptionReturner = new syntaxArchive();
		syntaxArchive vector3dReturner = new syntaxArchive();
		syntaxArchive taskReturner = new syntaxArchive();
		syntaxArchive noneReturner = new syntaxArchive();
		
		for(syntax current : SQFCommandVisitor.syntaxLists) {
			String returnType = current.getReturnType().toLowerCase();
			returnType = format(returnType);
			current.setReturnType(returnType); //set formatted returnType
			
			//sort according to the returnValues of the command
			switch (returnType) {
			case "NUMBER":
				numberReturner.add(current);
				break;
				
			case "OBJECT":
				objectReturner.add(current);
				break;
				
			case "Array":
				arrayReturner.add(current);
				break;
				
			case "STRING":
				stringReturner.add(current);
				break;
				
			case "Code":
				codeReturner.add(current);
				break;
				
			case "ANYTHING":
				anythingReturner.add(current);
				break;
				
			case "BOOLEAN":
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
		
		System.out.println("Fertig sortiert");
		
	}

	public static class SQFCommandVisitor extends SQFCommandBaseVisitor<Void> {
		int count = 0;

		public static ArrayList<syntax> syntaxLists = new ArrayList<syntax>();

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
		 *            The index of the child the function should start searching
		 *            for a syntax
		 * @return The syntax as an syntaxObject
		 */
		public syntax getSyntax(ParseTree commandRule, syntax syntax,
				int childIndex) {
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
					syntax = getSyntax(currentChild.getChild(1), syntax,
							childIndex);
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

		/**
		 * This function will get one of the possible syntx parts
		 * 
		 * @param commandRule
		 *            The ANTLRParserRule containing the syntax
		 * @param startChildIndex
		 *            Which ChlidElement is the one to start
		 * @return The syntax part as a String
		 */
		/*
		 * public ArrayList<String> processParameter(ParseTree commandRule, int
		 * startChildIndex) { int ChildCount = commandRule.getChildCount();
		 * if(startChildIndex >= ChildCount) { //check that index is reachable
		 * System.err.println("Given Index is greater than actual ChildCount!");
		 * return null; }
		 * 
		 * ArrayList<String> syntaxList = new ArrayList<String>(); String syntax
		 * = "";
		 * 
		 * for(int i=startChildIndex; i<ChildCount; i++) { String nextElement=
		 * commandRule.getChild(i).getText();
		 * 
		 * if(nextElement.equals("=")) { //if the element equals "=" then the
		 * content before was the return type syntax = ""; }else {
		 * if(nextElement.equals("alternative")) {
		 * 
		 * break; } } } }
		 */
	}
	
	/**
	 * This function will format a string, so that different returnType-names get corrected and unnessecary
	 * blanks get removed
	 * @param string The string that shouöd be formatted
	 * @return The formatted string
	 */
	public static String format(String string) {
		//replace some parameter names which are the same
		if(string.indexOf("number") >= 0) {
			string = string.replaceAll("number", "NUMBER");
		}
		if(string.indexOf("object") >= 0) {
			string = string.replaceAll("object", "OBJECT");
		}
		if(string.indexOf("string") >= 0) {
			string = string.replaceAll("string", "STRING");
		}
		if(string.indexOf("anything") >= 0) {
			string = string.replaceAll("anything", "ANYTHING");
		}
		if(string.indexOf("boolean") >= 0) {
			string = string.replaceAll("boolean", "BOOLEAN");
		}
		if(string.indexOf("bool") >= 0) {
			string = string.replaceAll("bool", "BOOLEAN");
		}
		if(string.indexOf("positionasl") >= 0) {
			string = string.replaceAll("positionasl", "PositionASL");
		}
		if(string.indexOf("positionatl") >= 0) {
			string = string.replaceAll("positionatl", "PositionATL");
		}
		if(string.indexOf("any") >= 0) {
			string = string.replaceAll("any", "ANYTHING");
		}
		if(string.indexOf("position2d") >= 0) {
			string = string.replaceAll("position2d", "Position2D");
		}
		if(string.indexOf("position3d") >= 0) {
			string = string.replaceAll("position3d", "Position3D");
		}
		if(string.indexOf("scripthandle") >= 0) {
			string = string.replaceAll("scripthandle", "ScriptHandle");
		}
		if(string.indexOf("editorOBJECT") >= 0) {
			string = string.replaceAll("editorOBJECT", "EditorObject");
		}
		if(string.indexOf("true") >= 0) {
			string = string.replaceAll("true", "BOOLEAN");
		}
		if(string.indexOf("false") >= 0) {
			string = string.replaceAll("false", "BOOLEAN");
		}
		if(string.indexOf("void") >= 0) {
			string = string.replaceAll("void", "ANYTHING");
		}
		if(string.indexOf("vector3d") >= 0) {
			string = string.replaceAll("vector3d", "Vector3D");
		}
		
		if(!string.substring(0,1).toUpperCase().equals(string.substring(0,1))) {
			//if string starts with a lowercase letter
			string = string.substring(0,1).toUpperCase() + string.substring(1);
		}
		
		while(string.endsWith(" ")) {
			string = string.substring(0, string.length() - 1);
		}
		
		return string;
	}
}