package raven.sqdev.editors.sqfeditor.testing;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import raven.sqdev.editors.sqfeditor.SQF_Editor;
import raven.sqdev.exceptions.SQDevException;
import raven.sqdev.infoCollection.base.Keyword;
import raven.sqdev.infoCollection.base.KeywordList;
import raven.sqdev.infoCollection.base.SQFCommand;
import raven.sqdev.interfaces.IMarkerSupport;
import raven.sqdev.misc.FileUtil;
import raven.sqdev.misc.Macro;
import raven.sqdev.parser.misc.BasicErrorListener;
import raven.sqdev.parser.sqf.SQFLexer;
import raven.sqdev.parser.sqf.SQFParser;
import raven.sqdev.parser.sqf.SQFValidator;
import raven.sqdev.util.StringUtils;

public class SQFParserTest {
	
	private static List<String> binaryCommands;
	private static List<String> unaryCommands;
	private static List<String> nularCommands;
	
	@BeforeClass
	public static void setUp() {
		KeywordList keywordList;
		try {
			String input = System.getProperty("user.home")
					+ "/Documents/Eclipse-Workspace/Eclipse.rcp/Raven.SQDev.Util/"
					+ "resources/sqf/SQFKeywords.txt";
			keywordList = new KeywordList(FileUtil.getContent(new File(input)));
		} catch (SQDevException e) {
			e.printStackTrace();
			
			System.exit(-1);
			return;
		}
		
		binaryCommands = new ArrayList<String>();
		unaryCommands = new ArrayList<String>();
		nularCommands = new ArrayList<String>();
		
		for (Keyword currentKeyword : keywordList.getKeywords()) {
			if (currentKeyword instanceof SQFCommand) {
				SQFCommand currentCommand = (SQFCommand) currentKeyword;
				
				if (currentCommand.isBinaryOperator()) {
					binaryCommands.add(currentCommand.getKeyword());
				}
				
				if (currentCommand.isUnaryOperator()) {
					unaryCommands.add(currentCommand.getKeyword());
				}
				
				if (currentCommand.isNularOperator()) {
					nularCommands.add(currentCommand.getKeyword());
				}
			}
		}
	}
	
	@Test
	public void speedTest() throws InterruptedException {
		int lines = 100;
		
		StringBuilder builder = new StringBuilder("{[");
		for (int i = 0; i < lines; i++) {
			builder.append("{hint \"" + i + "\";},");
		}
		builder.setLength(builder.length() - 1);
		builder.append("]}");
		String input = builder.toString();
		
		double avgTime = 0;
		
		int loops = 50;
		
		SQF_TestEditor editor = new SQF_TestEditor(binaryCommands,
				unaryCommands, nularCommands, new ArrayList<Macro>());
		
		for (int i = 0; i < loops; i++) {
			long time = Calendar.getInstance().getTimeInMillis();
			
			Object[] result = parseInput(input, editor.getMacroNames(), false,
					editor);
			
			validateParseTree((ParseTree) result[0],
					(BufferedTokenStream) result[1], editor);
			
			time = Calendar.getInstance().getTimeInMillis() - time;
			
			System.out.println(
					"Speed test: " + time + "ms -- " + (time / lines) + "ms/l");
			
			avgTime += (time / (double)loops);
		}
		
		System.out.println("\nAvg. time: " + avgTime + "ms -- " + (avgTime / lines) + "ms/l\n\n");
		
		Assert.assertTrue("Time exceeds limit! (" + avgTime + " instead of max "
				+ (0.5 * lines), avgTime < (0.5 * lines));
	}
	
	@Test
	public void ourAltisScriptTest() throws SQDevException {
		String scriptsPath = "/home/robert/Documents/GitHub/OurAltis/OurAltis_Mission.Altis/scripts";
		
		List<Macro> macros = new ArrayList<Macro>();
		
		macros.add(new Macro("FUNC"));
		macros.add(new Macro("QFUNC"));
		macros.add(new Macro("GVAR"));
		macros.add(new Macro("QGVAR"));
		macros.add(new Macro("PGVAR"));
		macros.add(new Macro("QPGVAR"));
		macros.add(new Macro("MGVAR"));
		macros.add(new Macro("RGVAR"));
		macros.add(new Macro("QRGVAR"));
		macros.add(new Macro("QUOTE"));
		macros.add(new Macro("FORMAT"));
		macros.add(new Macro("CHECK_TRUE"));
		macros.add(new Macro("CHECK_FALSE"));
		macros.add(new Macro("DEBUG_EXEC"));
		macros.add(new Macro("WARNING_LOG"));
		macros.add(new Macro("ERROR_LOG"));
		macros.add(new Macro("NOTIFICATION_LOG"));
		macros.add(new Macro("FORMAT_LOG"));
		macros.add(new Macro("COMPILE_LOADOUT"));
		macros.add(new Macro("LOADOUT_FUNC"));
		macros.add(new Macro("CHECK_DB_RESULT"));
		
		
		for (File currentScript : FileUtil
				.getAllSubFiles(new File(scriptsPath))) {
			if (currentScript.getName().contains(".sqf")
					&& !currentScript.getName().equals("fn_workWithRequest.sqf")
					&& !currentScript.getName().equals("fn_createCamp.sqf")
					&& !currentScript.getName()
							.equals("fn_createVehicles.sqf")) {
				
				SQF_TestEditor editor = new SQF_TestEditor(binaryCommands,
						unaryCommands, nularCommands, macros);
				
				String input = FileUtil.getContent(currentScript);
				int lines = StringUtils.countMatches(input, "\n") + 1;
				
				long time = Calendar.getInstance().getTimeInMillis();
				
				Object[] result = parseInput(input, editor.getMacroNames(),
						false, editor);
				
				long timeDiff = Calendar.getInstance().getTimeInMillis() - time;
				System.out.print(
						timeDiff + "ms" + " : " + (timeDiff / lines) + "ms/pl");
				
				
				time = Calendar.getInstance().getTimeInMillis();
				
				validateParseTree((ParseTree) result[0],
						(BufferedTokenStream) result[1], editor);
				
				Assert.assertFalse(
						"Unexpected errors in file \""
								+ currentScript.getAbsolutePath() + "\"!",
						editor.containsMarker());
				
				timeDiff = Calendar.getInstance().getTimeInMillis() - time;
				System.out.print(" -- " + timeDiff + "ms" + " : "
						+ (timeDiff / lines) + "ms/pl");
				
				System.out.println("\t\t - " + currentScript.getAbsolutePath());
			}
		}
	}
	
	@Test
	public void GeneralTest01() {
		String input = "_test = {}; hint str _test;";
		
		List<Macro> macros = new ArrayList<Macro>();
		
		SQF_TestEditor editor = new SQF_TestEditor(binaryCommands,
				unaryCommands, nularCommands, macros);
		
		Object[] result = parseInput(input, new ArrayList<String>(), false,
				editor);
		
		validateParseTree((ParseTree) result[0],
				(BufferedTokenStream) result[1], editor);
		
		Assert.assertFalse("Assumed no markers but got some!",
				editor.containsMarker());
	}
	
	
	/**
	 * Parses the given input with the SQF parser
	 * 
	 * @param input
	 *            The input for the parser
	 * @param useLL
	 *            Whether the parser should use the more complex LL algorithm
	 *            instead of the faster SLL
	 * @param acceptor
	 *            The IMarkerAcceptor to report any errors to
	 */
	private Object[] parseInput(String input, List<String> macroNames,
			boolean useLL, IMarkerSupport acceptor) {
		BasicErrorListener listener = new BasicErrorListener();
		
		ANTLRInputStream in = new ANTLRInputStream(input);
		
		SQFLexer lexer = new SQFLexer(in, binaryCommands, unaryCommands,
				macroNames);
		lexer.removeErrorListeners();
		lexer.addErrorListener(listener);
		
		CommonTokenStream stream = new CommonTokenStream(lexer);
		
		SQFParser parser = new SQFParser(stream);
		parser.removeErrorListeners();
		parser.addErrorListener(listener);
		
		parser.getInterpreter().setPredictionMode(
				(useLL) ? PredictionMode.LL : PredictionMode.SLL);
		
		return new Object[] { parser.start(), stream };
	}
	
	/**
	 * Validates the given input
	 * 
	 * @param tree
	 *            The ParseTree to validate
	 * @param stream
	 *            The BufferedTokenStream that has been used for parsing
	 * @param editor
	 *            The editor to interact with. It is recommended to use a
	 *            {@link SQF_TestEditor} for this purpose
	 */
	private void validateParseTree(ParseTree tree, BufferedTokenStream stream,
			SQF_Editor editor) {
		ParseTreeWalker walker = new ParseTreeWalker();
		
		walker.walk(new SQFValidator(editor, stream), tree);
	}
}
