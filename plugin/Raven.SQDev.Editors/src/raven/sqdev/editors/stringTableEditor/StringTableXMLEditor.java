package raven.sqdev.editors.stringTableEditor;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.Token;

import raven.sqdev.editors.BasicCodeEditor;
import raven.sqdev.editors.BasicSourceViewerConfiguration;
import raven.sqdev.editors.stringtableParsing.StringTableLexer;
import raven.sqdev.editors.stringtableParsing.StringTableParser;
import raven.sqdev.editors.stringtableParsing.StringTableWalkListener;
import raven.sqdev.interfaces.IParseResult;
import raven.sqdev.misc.CharacterPair;
import raven.sqdev.misc.SQDevInfobox;
import raven.sqdev.parser.misc.BasicErrorListener;

/**
 * The editor used in the StringTableEditor for editing the XML file
 * 
 * @author Raven
 *
 */
public class StringTableXMLEditor extends BasicCodeEditor {

	public static final String TAG = "__stringTableEditor_Tag";
	public static final IPredicateRule TAG_RULE = new MultiLineRule("<", ">", new Token(TAG));
	/**
	 * The token stream corresponding to the current parse tree
	 */
	protected CommonTokenStream stream;
	/**
	 * The package list contained in this editor
	 */
	private List<StringTablePackage> packageList;

	public StringTableXMLEditor() {
		getBasicProvider().getPartitionScanner().setRules(new IPredicateRule[] { TAG_RULE });
	}

	@Override
	protected IParseResult doParse(InputStream input) {
		ANTLRInputStream in;
		try {
			in = new ANTLRInputStream(input);
		} catch (IOException e) {
			e.printStackTrace();
			
			SQDevInfobox info = new SQDevInfobox("Error while parsing stringtable", e);
			
			info.open(false);
			
			return null;
		}

		BasicErrorListener listener = new BasicErrorListener();

		StringTableLexer lexer = new StringTableLexer(in);
		lexer.removeErrorListeners();
		lexer.addErrorListener(listener);

		stream = new CommonTokenStream(lexer);

		StringTableParser parser = new StringTableParser(stream);
		parser.removeErrorListeners();
		parser.addErrorListener(listener);

		ParseTree tree = parser.content();

		listener.getParseResult().applyMarkersTo(this);

		return listener.getParseResult();
	}

	@Override
	protected boolean processParseTree(IParseResult result) {
		ParseTreeWalker walker = new ParseTreeWalker();

		StringTableWalkListener listener = new StringTableWalkListener(stream);

		walker.walk(listener, result.getANTRLParseTree());

		packageList = listener.getPackageList();

		applyParseChanges();

		return false;
	}

	@Override
	protected List<CharacterPair> getCharacterPairs() {
		List<CharacterPair> pairs = new ArrayList<CharacterPair>();

		pairs.add(CharacterPair.DOUBLE_QUOTATION_MARKS);
		pairs.add(CharacterPair.SINGLE_QUOTATION_MARKS);
		pairs.add(CharacterPair.TAG);

		return pairs;
	}

	@Override
	public BasicSourceViewerConfiguration getBasicConfiguration() {
		if (configuration == null || !(configuration instanceof StringTableSourceViewerConfiguration)) {
			configuration = new StringTableSourceViewerConfiguration(getColorManager(), this);
		}

		return configuration;
	}

	/**
	 * Gets the list of <code>StringTablePackages</code> contained in this editor.
	 * May be <code>null</code> if not yet initialized
	 */
	public List<StringTablePackage> getPackageList() {
		return packageList;
	}

	@Override
	public boolean isDirty() {
		return super.isDirty();
	}

}
