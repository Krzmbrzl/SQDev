package raven.sqdev.parser.misc;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.atn.DecisionInfo;
import org.antlr.v4.runtime.atn.ParseInfo;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IPath;

import dataStructures.CharacterInputStream;
import lexer.SQFLexer;
import raven.sqdev.infoCollection.base.Variable;
import raven.sqdev.interfaces.ISQFParseInformation;
import raven.sqdev.misc.CharacterPair;
import raven.sqdev.misc.Pair;
import raven.sqdev.misc.TextUtils;
import raven.sqdev.parser.preprocessor.PreprocessorErrorListener;
import raven.sqdev.parser.preprocessor.PreprocessorLexer;
import raven.sqdev.parser.preprocessor.PreprocessorParseListener;
import raven.sqdev.parser.preprocessor.PreprocessorParseResult;
import raven.sqdev.parser.preprocessor.PreprocessorParser;
import raven.sqdev.parser.sqf.SQFParseInformation;
import raven.sqdev.parser.sqf.SQFParseResult;
import raven.sqdev.parser.sqf.SQFValidatorOLD;

/**
 * A class containing utility methods for parsing processes
 * 
 * @author Raven
 *
 */
public class ParseUtil {
	/**
	 * The lexer instance used by this class
	 */
	protected static SQFLexer sqfLexer;
	/**
	 * The error listener instance used by this class
	 */
	protected static SQFLexAndParseListener errorListener;


	public static final SQFParseResult parseSQF(InputStream input, ISQFParseInformation parseInfo) throws IOException {
		if (sqfLexer == null) {
			sqfLexer = new SQFLexer(errorListener);
		}

		// TODO: set token factory for lexer -> should be retrieved from
		// parseInformation

		SQFParseResult result = new SQFParseResult();

		sqfLexer.reset(true);
		sqfLexer.setMacros(parseInfo.getMacros().keySet());
		errorListener.setResult(result);

		CharacterInputStream inStream = new CharacterInputStream(input);
		sqfLexer.lex(inStream);

		// TODO: adjust ParseResult to carry tokenBuffer and parseTree
	}

	/**
	 * Parses the given input assuming that the input is SQF code
	 * 
	 * @param input
	 *            The input to parse
	 * @param parseInfo
	 *            The necessary parseInformation
	 * @return The parseResult containing all necessary information about the
	 *         parsing (including the parseTree)
	 */
	public static final SQFParseResult parseSQFOld(String input, ISQFParseInformation parseInfo) {
		SQFParseResult result = new SQFParseResult();

		BasicErrorListener listener = new BasicErrorListener();

		ANTLRInputStream in = new ANTLRInputStream(input);

		raven.sqdev.parser.sqf.SQFLexer lexer = new raven.sqdev.parser.sqf.SQFLexer(in, parseInfo.getBinaryKeywords(),
				parseInfo.getUnaryKeywords(), parseInfo.getMacroNames());
		lexer.removeErrorListeners();
		lexer.addErrorListener(listener);

		CommonTokenStream tokenStream = new CommonTokenStream(lexer);

		raven.sqdev.parser.sqf.SQFParser parser = new raven.sqdev.parser.sqf.SQFParser(tokenStream);
		parser.removeErrorListeners();
		parser.addErrorListener(listener);

		result.setParserRuleNames(Arrays.asList(parser.getRuleNames()));

		// parse with SLL(*)
		parser.getInterpreter().setPredictionMode(PredictionMode.SLL);

		// TODO: debug option
		parser.setProfile(true);

		ParseTree tree = parser.start();

		if (listener.getParseResult().getMarkers().size() > 0) {
			// check if unbalanced brackets are the problem
			List<Pair<Integer, String>> unbalancedCharacters = TextUtils.findUnbalancedCharacterPairs(input,
					new CharacterPair[] { CharacterPair.ROUND_BRACKETS, CharacterPair.SQUARE_BRACKETS,
							CharacterPair.CURLY_BRACKETS, CharacterPair.DOUBLE_QUOTATION_MARKS,
							CharacterPair.SINGLE_QUOTATION_MARKS });

			if (!unbalancedCharacters.isEmpty()) {
				// There are unbalanced characters -> make sure they are not
				// a comment or a String
				tokenStream.reset();

				List<Pair<Integer, String>> unbalancedPairsToReport = new ArrayList<Pair<Integer, String>>();

				for (org.antlr.v4.runtime.Token currentToken : tokenStream.getTokens()) {
					if (currentToken.getChannel() == org.antlr.v4.runtime.Token.HIDDEN_CHANNEL
							|| currentToken.getType() == raven.sqdev.parser.sqf.SQFParser.STRING) {
						// Ignore hidden Tokens
						continue;
					} else {
						for (Pair<Integer, String> currentPair : unbalancedCharacters) {
							if (currentPair.getFirst() >= currentToken.getStartIndex()) {
								if (currentPair.getFirst() > currentToken.getStopIndex()) {
									// can only be considered when looking
									// at the next Token
									break;
								} else {
									// The offending token is in a relevant
									// part -> must be reported
									unbalancedPairsToReport.add(currentPair);
								}
							}
						}
					}
				}

				if (unbalancedPairsToReport.size() > 0) {
					// clear previous errors as unbalanced braces mess up too much for the previous
					// errors to be useful
					listener.getParseResult().getMarkers().clear();
				}

				for (Pair<Integer, String> currentPair : unbalancedPairsToReport) {
					listener.reportError(currentPair.getFirst(), 1, currentPair.getSecond());
				}
			}
		}

		result.setParseTree(tree);
		result.setTokenStream(tokenStream);
		result.mergeWith(listener.getParseResult());

		// TODO: debug info
		ParseInfo info = parser.getParseInfo();
		DecisionInfo[] infos = info.getDecisionInfo();
		System.out.println(info);

		return result;
	}

	/**
	 * Validates the given ParseTree assuming that it refers to SQF code
	 * 
	 * @param tree
	 *            The tree to validate
	 * @param tokenStream
	 *            The tokenStream that has been used to create the tree
	 * @param info
	 *            The necessary ParseInformation
	 * @return The result of the validation
	 */
	public static final SQFParseResult validateSQFOld(ParseTree tree, BufferedTokenStream tokenStream,
			ISQFParseInformation info) {
		Assert.isNotNull(tokenStream);
		Assert.isNotNull(tree);
		Assert.isNotNull(info);

		ParseTreeWalker walker = new ParseTreeWalker();

		SQFValidatorOLD validator = new SQFValidatorOLD(info, tokenStream);

		walker.walk(validator, tree);

		return validator.getParseResult();
	}

	/**
	 * Parses and validates the given input as SQF code
	 * 
	 * @param input
	 *            The input to process
	 * @param parseInfo
	 *            The {@link SQFParseInformation}} for this parsing process
	 * @return The {@link SQFParseResult} of this parsing and validating
	 */
	public static final SQFParseResult parseAndValidateSQFOld(String input, ISQFParseInformation parseInfo) {
		SQFParseResult result = parseSQFOld(input, parseInfo);
		result.mergeWith(validateSQFOld(result.getParseTree(), result.getTokenStream(), parseInfo));

		return result;
	}

	/**
	 * Gets the default magic variables that are present in SQF code
	 */
	public static final Map<String, Variable> getDefaultMagicVars() {
		Map<String, Variable> magicVars = new HashMap<String, Variable>();
		magicVars.put("_this", new Variable("_this",
				"This variable is available inside of functions and contains the parameters given to it."));
		magicVars.put("_fnc_scriptname",
				new Variable("_fnc_scriptName",
						"A String containing the function's name. Only awaylable when the function has "
								+ "been compiled via CfgFunctions."));
		magicVars.put("_fnc_scriptnameparent",
				new Variable("_fnc_scriptNameParent",
						"A String containing the function's parent's name. Only awaylable when the function has "
								+ "been compiled via CfgFunctions."));
		magicVars.put("_x", new Variable("_x",
				"References the current object oin the iteration. Available inside count or forEach loops"));
		magicVars.put("_foreachindex", new Variable("_forEachIndex",
				"References the index of the current object in the iteration. Only available in a " + "forEach loop."));
		magicVars.put("_thiseventhandler", new Variable("_thisEventHandler",
				"References the current event handler. Only available inside an EventHandler"));

		return magicVars;
	}

	/**
	 * Preprocesses the given input
	 * 
	 * @param input
	 *            The input to preprocess
	 * @param origin
	 *            The origin-path of the given content. This is used in order to
	 *            resolve relative paths
	 * @return The result of the preprocessing
	 */
	public static final PreprocessorParseResult parseAndValidatePreprocess(String input, IPath origin) {
		ANTLRInputStream prepIn = new ANTLRInputStream(input);

		PreprocessorLexer prepLexer = new PreprocessorLexer(prepIn);

		CommonTokenStream prepTokens = new CommonTokenStream(prepLexer);

		PreprocessorParser prepParser = new PreprocessorParser(prepTokens);

		prepParser.removeErrorListeners();
		PreprocessorErrorListener errorListener = new PreprocessorErrorListener(0);
		prepParser.addErrorListener(errorListener);

		ParseTreeWalker prepWalker = new ParseTreeWalker();

		PreprocessorParseListener prepListener = new PreprocessorParseListener(origin);

		prepWalker.walk(prepListener, prepParser.start());


		PreprocessorParseResult result = prepListener.getParseResult();

		result.mergeWith(errorListener.getParseResult());

		return result;
	}
}
