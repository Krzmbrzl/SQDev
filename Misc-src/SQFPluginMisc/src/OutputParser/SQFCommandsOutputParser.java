package OutputParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.*;
//import org.antlr.v4.runtime.CharStream;
//import org.antlr.v4.runtime.ANTLRInputStream;
//import org.antlr.v4.runtime.TokenStream;
//import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.*;

import SQFBaseParser.SQFCommandBaseListener;
import SQFBaseParser.SQFCommandLexer;
import SQFBaseParser.SQFCommandParser;

public class SQFCommandsOutputParser{
	public static void main(String[] args) throws Exception {
		String inputString = "";
		String commandFilePath = "NotSet";
		
		for(String argument : args) {
			if(argument.startsWith("/cF=") || argument.startsWith("/cF=")) {
				argument = argument.substring(4);
				commandFilePath = argument;
			}
		}
		
		BufferedReader reader = new BufferedReader(new FileReader(new File (commandFilePath)));
		String line;
		
		while((line = reader.readLine()) != null) {
			inputString = inputString + line + "\n";
		}
		
		reader.close();
		
		
		//create a charStream from input
		ANTLRInputStream input = new ANTLRInputStream(inputString);
		
		//create a lexer that feeds off of input CharStream
		SQFCommandLexer lexer = new SQFCommandLexer(input);
		
		//create a buffer  of tokens pulled from the lexer
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		//create a parser that feeds off the tkens buffer
		SQFCommandParser parser = new SQFCommandParser(tokens);
		
		ParseTree tree = parser.start(); //begin parsing at rule start
		
//		System.out.println(tree.toStringTree(parser));
		
		
		//create a standard ANTLR parse tree walker
		ParseTreeWalker walker = new ParseTreeWalker();
		SQFCommandsOutputParserListener listener = new SQFCommandsOutputParserListener();
		
		walker.walk(listener, tree); //walk parse tree
		
	}
	
	public static class SQFCommandsOutputParserListener extends SQFCommandBaseListener {
		String name;
		ArrayList<String> syntax = new ArrayList<String>();
		static ArrayList<ArrayList<String>> syntaxList = new ArrayList<ArrayList<String>>();
		
		public void exitCommandName(SQFCommandParser.CommandNameContext ctx) {
			name = ctx.getText();
			System.out.println(name);
			syntax.add(name);
			
		}
		
		public void exitParameter(SQFCommandParser.ParameterContext ctx) {
			String value = ctx.getText();
			if(value.toLowerCase().equals(name.toLowerCase())) {
				value = "command";
			}
			
			syntax.add(value);
		}
		
		public void exitCommand(SQFCommandParser.CommandContext ctx) {
			if(syntax.isEmpty()) {
				return;
			}
			
			System.out.println(syntax);
			
			if(ctx.type() != null) {
				String type = ctx.type().getText();
				System.out.println("type: " + type);
				syntax.add(type);
			}
			
			addList(syntax);
			syntax.clear();
			
			System.out.println(syntaxList);
			
			sortSyntax(syntaxList);
			
			syntaxList.clear();
			System.out.println();
		}
		
		public void enterAlternative(SQFCommandParser.AlternativeContext ctx) {
			System.out.println(syntax);
			System.out.println("alternative:");
			
			addList(syntax);
			
			syntax.clear();
		}
		
		public static void addList(ArrayList<String> list) {
			if(list.isEmpty()) {
				return;
			}
			
			ArrayList<String> copy = new ArrayList<String>(list);
			
			syntaxList.add(copy);
		}
		
		
		/**
		 * This function will sort the different syntax regarding their return type and the place of the 
		 * command in the syntax
		 * @param list The list with the different syntax - the first one starts with the commandName and ends
		 * with the return type
		 */
		public static void sortSyntax(ArrayList<ArrayList<String>> list) {
			ArrayList<String> firstList = list.get(0);
			
			String commandName = firstList.get(0);
			String type = firstList.get(firstList.size() - 1);
			
			ArrayList<String> firstListCopy = new ArrayList<String>();
			
			for(int i=1; i<firstList.size()-1; i++) {
				//write the syntax part of firstList in it's copy
				firstListCopy.add(firstList.get(i));
			}
			
			list.set(0, firstListCopy); //replace the old list with commandName and return type with the one only containing the syntax
			
			for(List<String> currentList : list) {
				if(currentList.contains("command")) {
					int pos = currentList.indexOf("command");
					int length = currentList.size();
					int following = length - pos - 1;
					int test = 3;
				}else {
					System.err.println("Nope");
				}
			}
		}
	}
}
