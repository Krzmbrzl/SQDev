package raven.sqdev.editors.stringTableEditor;

import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import raven.sqdev.editors.BasicCodeEditor;
import raven.sqdev.editors.BasicErrorListener;
import raven.sqdev.editors.stringtableParsing.StringTableLexer;
import raven.sqdev.editors.stringtableParsing.StringTableParser;
import raven.sqdev.editors.stringtableParsing.StringTableWalkListener;

/**
 * The editor used in the StringTableEditor for editing the XML file
 * 
 * @author Raven
 *
 */
public class StringTableXMLEditor extends BasicCodeEditor {
	/**
	 * The token stream corresponding to the current parse tree
	 */
	protected CommonTokenStream stream;
	/**
	 * The package list contained in this editor
	 */
	private List<StringTablePackage> packageList;
	
	@Override
	protected ParseTree doParse(String input) {
		ANTLRInputStream in = new ANTLRInputStream(input);
		
		BasicErrorListener listener = new BasicErrorListener(this);
		
		StringTableLexer lexer = new StringTableLexer(in);
		lexer.removeErrorListeners();
		lexer.addErrorListener(listener);
		
		stream = new CommonTokenStream(lexer);
		
		StringTableParser parser = new StringTableParser(stream);
		parser.removeErrorListeners();
		parser.addErrorListener(listener);
		
		return parser.content();
	}
	
	@Override
	protected void processParseTree(ParseTree tree) {
		ParseTreeWalker walker = new ParseTreeWalker();
		
		StringTableWalkListener listener = new StringTableWalkListener(stream);
		
		walker.walk(listener, tree);
		
		packageList = listener.getPackageList();
	}
	
	/**
	 * Gets the list of <code>StringTablePackages</code> contained in this
	 * editor. May be <code>null</code> if not yet initialized
	 */
	public List<StringTablePackage> getPackageList() {
		return packageList;
	}
	
}
