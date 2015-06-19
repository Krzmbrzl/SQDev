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
		String commandFilePath = "NotSet";
		
		for(String argument : args) {
			if(argument.startsWith("/cF=") || argument.startsWith("/cf=")) {
				argument = argument.substring(4);
				commandFilePath = argument;
			}
		}
		
		File inputFile = new File(commandFilePath);
		
		try {
			//read the inputFile into a single string
			
			BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
			
			String line;
			
			while((line = inputReader.readLine()) != null) {
				antlrInput = antlrInput + line + "\n";
			}
			
			inputReader.close();
						
			System.out.println("Finished reading file");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//create CharStream that reads from the input file
		ANTLRInputStream input = new ANTLRInputStream(antlrInput);
		
		//create a lexer that feeds off of input CharStream
		SQFCommandLexer lexer = new SQFCommandLexer(input);
		
		//create a buffer of tokens pulled from the lexer
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		//create a parser that feeds off the tokens buffer
		SQFCommandParser parser = new SQFCommandParser(tokens);
		
		
		ParseTree tree = parser.start();
		System.out.println(tree.toStringTree(parser));
		
		SQFCommandVisitor visitor = new SQFCommandVisitor();
		
		visitor.visit(tree);
		
		//data collected
		//now sort them into categories
		
		syntaxVariantArchive archive = new syntaxVariantArchive();
		
		for(int i=0; i<SQFCommandVisitor.syntaxLists.size(); i++) {
			syntax currentCommand = SQFCommandVisitor.syntaxLists.get(i);
			
			for(int k=0; k<currentCommand.getSyntaxList().size(); k++) {
				String syntax = currentCommand.getSyntaxList().get(k);
				
				if(!archive.contains(syntax)) {
					
					if(syntax.equals("object addmagazine [string, double] ")) {
						String dummy ="";
					}
					
					archive.add(syntax);
				}
			}
		}
		
		System.out.println("number of variants: " + archive.size() + "\n");
		
		
		String[] parameter = archive.getParameter(); //TODO: i=241 k=0 passt überhaupt net
		System.out.println(parameter.length + "\n");
		
		for(String current : parameter) {
			System.out.println(current);
		}
		
//		System.out.println(archive.toString());
		
	}
	
	
	public static class SQFCommandVisitor extends SQFCommandBaseVisitor<Void> {
		int count = 0;
		
		public static ArrayList<syntax> syntaxLists = new ArrayList<syntax>();
		
		public Void visitCommandSyntax(SQFCommandParser.CommandSyntaxContext ctx) {
			String commandName = ctx.getChild(0).getText(); //get command name
			
			ParseTree command = ctx.getChild(1); //create direct access to rule command
			
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
		 * @param commandRule The ANTLRParserRule that contains the syntax
		 * @param syntax The syntax-object the new syntax should be added
		 * @param childIndex The index of the child the function should start searching for a syntax
		 * @return The syntax as an syntaxObject
		 */
		public syntax getSyntax(ParseTree commandRule, syntax syntax, int childIndex) {
			int childCount = commandRule.getChildCount();
			
			if(!(childIndex < childCount) || childIndex < 0) {
				//if index is not applicable return the given syntax
				return syntax;
			}
			
			String singleSyntax = "";
			
			for(int i=childIndex; i<childCount; i++) {
				ParseTree currentChild = commandRule.getChild(i);
				String nextElement = currentChild.getText();
				
				if(currentChild.getChildCount() > 1) {
					//if currentChild has more that one children it is an alternative syntax
					syntax.addToSyntaxList(singleSyntax);
					syntax = getSyntax(currentChild.getChild(1), syntax, childIndex);
					return syntax;
				}else {
					if(nextElement.equals("=")) {
						syntax.setReturnType(singleSyntax);
						singleSyntax= "";
					}else {
						singleSyntax += nextElement + " ";
					}
				}
			}
			
			syntax.addToSyntaxList(singleSyntax);
			
			return syntax;
		}
		
		/**
		 * This function will find out the syntax of the command
		 * @param commandRule The ANTLRParserRule that contains the syntax
		 * @param commandName The name of the command
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
		 * @param commandRule The ANTLRParserRule that contains the syntax
		 * @param syntax The syntax-object the new syntax should be added
		 * @return The syntax as an syntaxObject
		 */
		public syntax getSyntax(ParseTree commandRule, syntax syntax) {
			return getSyntax(commandRule, syntax, 0);
		}
		
		/**
		 * This function will get one of the possible syntx parts
		 * @param commandRule The ANTLRParserRule containing the syntax
		 * @param startChildIndex Which ChlidElement is the one to start
		 * @return The syntax part as a String
		 */
/*		public ArrayList<String> processParameter(ParseTree commandRule, int startChildIndex) {
			int ChildCount = commandRule.getChildCount();
			if(startChildIndex >= ChildCount) {
				//check that index is reachable
				System.err.println("Given Index is greater than actual ChildCount!");
				return null;
			}
			
			ArrayList<String> syntaxList = new ArrayList<String>();
			String syntax = "";
			
			for(int i=startChildIndex; i<ChildCount; i++) {
				String nextElement= commandRule.getChild(i).getText();
				
				if(nextElement.equals("=")) {
					//if the element equals "=" then the content before was the return type
					syntax = "";
				}else {
					if(nextElement.equals("alternative")) {
						
						break;
					}
				}
			}
		}*/
	}
}