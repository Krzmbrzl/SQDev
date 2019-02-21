package raven.sqdev.parser.sqf.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.junit.Test;

import raven.sqdev.constants.ProblemMessages;
import raven.sqdev.exceptions.SQDevException;
import raven.sqdev.infoCollection.base.Keyword;
import raven.sqdev.interfaces.ISQFInformation;
import raven.sqdev.misc.DataTypeList;
import raven.sqdev.misc.ESQFDataType;
import raven.sqdev.misc.FileSystemUtil;
import raven.sqdev.misc.Macro;
import raven.sqdev.misc.Marker;
import raven.sqdev.parser.misc.ParseUtil;
import raven.sqdev.parser.sqf.SQFInformation;
import raven.sqdev.parser.sqf.SQFParseResultOld;

@SuppressWarnings("deprecation")
public class SQFParserTest {

	public static final File KEYWORD_FILE = new File(
			makeOSCompatible(System.getProperty("user.dir") + "/resources/sqf/SQFKeywords.txt"));

	public static final String BASE_PATH = System.getProperty("user.dir")
			+ "/src/raven/sqdev/parser/sqf/tests/TestScripts/";
	public static final String VARIABLE_DECLARATION_PATH = BASE_PATH + "VariableDeclaration.sqf";
	public static final File VARIABLE_DECLARATION = new File(makeOSCompatible(VARIABLE_DECLARATION_PATH));

	@Test
	public void variableDeclarations() {
		SQFParseResultOld result = process(getContent(VARIABLE_DECLARATION));

		// Assert that there are no errors in this file
		assertTrue("The file \"" + VARIABLE_DECLARATION_PATH + "\" is not expected to contain erros!",
				result.getMarkers().size() == 0);

		// Check local variable declarations
		final String[] localVariables = new String[] { "_myTestVar1", "_myTestVar2", "_parameter1", "_parameter2",
				"_parameter4", "_innerVariable", "_forVar", "_innerForVar", "_private1", "_private2", "_private3",
				"_ifVar" };

		List<String> definedLocalVariables = Arrays.asList(localVariables);
		List<String> foundLocalVariables = getKeywords(result.getDeclaredLocalVariables().values());

		if (!collectionContentEqual(definedLocalVariables, foundLocalVariables)) {
			StringBuilder msg = new StringBuilder("The found declarations don't match with the expected ones");

			List<String> missing = new ArrayList<String>(definedLocalVariables);
			missing.removeAll(foundLocalVariables);

			List<String> foundFalse = new ArrayList<String>(foundLocalVariables);
			foundFalse.removeAll(definedLocalVariables);

			if (missing.size() > 0) {
				msg.append("\n\tMissing local variables: " + missing.toString());
			}
			if (foundFalse.size() > 0) {
				msg.append("\n\tFalsely found local variables: " + foundFalse.toString());
			}

			fail(msg.toString());
		}

		// Check global variable declarations
		final String[] globalVariables = new String[] { "GlobalTestVar", "Implicit1", "Implicit2", "Implicit3" };
		List<String> definedGlobalVariables = Arrays.asList(globalVariables);
		List<String> foundGlobalVariables = getKeywords(result.getDeclaredGlobalVariables().values());

		if (!collectionContentEqual(definedGlobalVariables, foundGlobalVariables)) {
			StringBuilder msg = new StringBuilder("The found declarations don't match with the expected ones");

			List<String> missing = new ArrayList<String>(definedGlobalVariables);
			missing.removeAll(foundGlobalVariables);

			List<String> foundFalse = new ArrayList<String>(foundGlobalVariables);
			foundFalse.removeAll(definedGlobalVariables);

			if (missing.size() > 0) {
				msg.append("\n\tMissing global variables: " + missing.toString());
			}
			if (foundFalse.size() > 0) {
				msg.append("\n\tFalsely found global variables: " + foundFalse.toString());
			}

			fail(msg.toString());
		}
	}

	@Test
	public void error_typeMismatch() {
		String input;
		SQFParseResultOld result;
		Marker expectedMarker;


		input = "hint 3";
		result = process(input);
		expectedMarker = createErrorMarker(5, 1,
				ProblemMessages.expectedTypeButGot(
						new DataTypeList(new ESQFDataType[] { ESQFDataType.STRING, ESQFDataType.STRUCTURED_TEXT }),
						new DataTypeList(new ESQFDataType[] { ESQFDataType.NUMBER })));
		// Do the checking
		assertMarkerAmountEquals(result, 1);
		assertMarkersEqual(expectedMarker, result.getMarkers().get(0));


		input = "\"\" append []";
		result = process(input);
		expectedMarker = createErrorMarker(0, 2,
				ProblemMessages.expectedTypeButGotDifferent(ESQFDataType.ARRAY.toString(), ESQFDataType.STRING.toString()));
		// Do the checking
		assertMarkerAmountEquals(result, 1);
		assertMarkersEqual(expectedMarker, result.getMarkers().get(0));


		input = "[] append \"\"";
		result = process(input);
		expectedMarker = createErrorMarker(10, 2,
				ProblemMessages.expectedTypeButGotDifferent(ESQFDataType.ARRAY.toString(), ESQFDataType.STRING.toString()));
		// Do the checking
		assertMarkerAmountEquals(result, 1);
		assertMarkersEqual(expectedMarker, result.getMarkers().get(0));


		input = "+objNull";
		result = process(input);
		expectedMarker = createErrorMarker(1, 7,
				ProblemMessages.expectedTypeButGot(
						new DataTypeList(new ESQFDataType[] { ESQFDataType.NUMBER, ESQFDataType.ARRAY, ESQFDataType.STRING }),
						new DataTypeList(new ESQFDataType[] { ESQFDataType.OBJECT })));
		// Do the checking
		assertMarkerAmountEquals(result, 1);
		assertMarkersEqual(expectedMarker, result.getMarkers().get(0));


		input = "'hello' + objNull";
		result = process(input);
		expectedMarker = createErrorMarker(10, 7,
				ProblemMessages.expectedTypeButGotDifferent(ESQFDataType.STRING.toString(), ESQFDataType.OBJECT.toString()));
		// Do the checking
		assertMarkerAmountEquals(result, 1);
		assertMarkersEqual(expectedMarker, result.getMarkers().get(0));

		input = "velocity ''";
		result = process(input);
		expectedMarker = createErrorMarker(9, 2,
				ProblemMessages.expectedTypeButGotDifferent(ESQFDataType.OBJECT.toString(), ESQFDataType.STRING.toString()));
		// Do the checking
		assertMarkerAmountEquals(result, 1);
		assertMarkersEqual(expectedMarker, result.getMarkers().get(0));
	}

	@Test
	public void error_missingSemicolon() {
		String input;
		SQFParseResultOld result;
		Marker expectedMarker;
		Map<String, Macro> macros = new HashMap<String, Macro>();
		macros.put("CHECK_TRUE", new Macro("CHECK_TRUE"));


		input = "diag_log 3 hint 'hello'";
		result = process(input);
		expectedMarker = createErrorMarker(9, 1, ProblemMessages.missingSemicolon("3"));
		// Do the checking
		assertMarkerAmountEquals(result, 1);
		assertMarkersEqual(expectedMarker, result.getMarkers().get(0));


		input = "CHECK_TRUE(nsdvjJSDNV, SAKFN) diag_log 3 hint 'hello'";
		result = process(input, macros);
		expectedMarker = createErrorMarker(39, 1, ProblemMessages.missingSemicolon("3"));
		// Do the checking
		assertMarkerAmountEquals(result, 1);
		assertMarkersEqual(expectedMarker, result.getMarkers().get(0));


		input = "CHECK_TRUE(nsdvjJSDNV, SAKFN) player setPos [1,2,3] hint 'hello'";
		result = process(input, macros);
		expectedMarker = createErrorMarker(50, 1, ProblemMessages.missingSemicolon("]"));
		// Do the checking
		assertMarkerAmountEquals(result, 1);
		assertMarkersEqual(expectedMarker, result.getMarkers().get(0));
	}

	@Test
	public void error_unbalancedCharacterPair() {
		String input;
		SQFParseResultOld result;
		Marker expectedMarker;


		input = "hint (3;";
		result = process(input);
		expectedMarker = createErrorMarker(5, 1, ProblemMessages.unclosedOpener('('));
		// Do the checking
		assertMarkerAmountEquals(result, 1);
		assertMarkersEqual(expectedMarker, result.getMarkers().get(0));


		input = "hint '3;";
		result = process(input);
		expectedMarker = createErrorMarker(5, 1, ProblemMessages.unclosedOpener('\''));
		// Do the checking
		assertMarkerAmountEquals(result, 1);
		assertMarkersEqual(expectedMarker, result.getMarkers().get(0));


		input = "hint \"3;";
		result = process(input);
		expectedMarker = createErrorMarker(5, 1, ProblemMessages.unclosedOpener('"'));
		// Do the checking
		assertMarkerAmountEquals(result, 1);
		assertMarkersEqual(expectedMarker, result.getMarkers().get(0));

		input = "hint [3;";
		result = process(input);
		expectedMarker = createErrorMarker(5, 1, ProblemMessages.unclosedOpener('['));
		// Do the checking
		assertMarkerAmountEquals(result, 1);
		assertMarkersEqual(expectedMarker, result.getMarkers().get(0));


		input = "hint {3;";
		result = process(input);
		expectedMarker = createErrorMarker(5, 1, ProblemMessages.unclosedOpener('{'));
		// Do the checking
		assertMarkerAmountEquals(result, 1);
		assertMarkersEqual(expectedMarker, result.getMarkers().get(0));


		input = "hint '');";
		result = process(input);
		expectedMarker = createErrorMarker(7, 1, ProblemMessages.invalidClosingCharacter(')'));
		// Do the checking
		assertMarkerAmountEquals(result, 1);
		assertMarkersEqual(expectedMarker, result.getMarkers().get(0));


		input = "hint ''];";
		result = process(input);
		expectedMarker = createErrorMarker(7, 1, ProblemMessages.invalidClosingCharacter(']'));
		// Do the checking
		assertMarkerAmountEquals(result, 1);
		assertMarkersEqual(expectedMarker, result.getMarkers().get(0));


		input = "hint ''};";
		result = process(input);
		expectedMarker = createErrorMarker(7, 1, ProblemMessages.invalidClosingCharacter('}'));
		// Do the checking
		assertMarkerAmountEquals(result, 1);
		assertMarkersEqual(expectedMarker, result.getMarkers().get(0));
	}

	/**
	 * Checks whether the given two markers are equal. It fails the test if not
	 * 
	 * @param expected
	 *            The marker that the actual one is expected to be
	 * @param actual
	 *            The marker to check
	 */
	protected static void assertMarkersEqual(Marker expected, Marker actual) {
		if (expected.equals(actual)) {
			return;
		}

		fail(expected.createDifferenceMessage(actual));
	}

	/**
	 * Checks whether the given parseResult contains the given amount of markers
	 * 
	 * @param result
	 *            The {@link SQFParseResultOld} to check
	 * @param amount
	 *            The amount of markers that are expected to be present
	 */
	protected static void assertMarkerAmountEquals(SQFParseResultOld result, int amount) {
		if (result.getMarkers().size() == amount) {
			return;
		}

		if (result.getMarkers().size() == 0) {
			fail("The expected error has not been found!");
		} else {
			fail("Too many erros have been detected! (" + result.getMarkers().size() + " instead of " + amount + ")");
		}
	}

	/**
	 * Creates an error marker with the given information
	 * 
	 * @param offset
	 *            The marker's offset
	 * @param length
	 *            The marker's length
	 * @param msg
	 *            The marker's message
	 * @return The created marker
	 */
	protected static Marker createErrorMarker(int offset, int length, String msg) {
		return new Marker(IMarker.PROBLEM, offset, length, msg, IMarker.SEVERITY_ERROR);
	}

	/**
	 * Creates a warning marker with the given information
	 * 
	 * @param offset
	 *            The marker's offset
	 * @param length
	 *            The marker's length
	 * @param msg
	 *            The marker's message
	 * @return The created marker
	 */
	protected static Marker createWarningMarker(int offset, int length, String msg) {
		return new Marker(IMarker.PROBLEM, offset, length, msg, IMarker.SEVERITY_WARNING);
	}

	/**
	 * Parses and validates the given input as SQF code
	 * 
	 * @param input
	 *            The input to process
	 * @param macros
	 *            The list of macros that should be existent in the given input
	 * @return The resulting {@link SQFParseResultOld}
	 */
	protected static SQFParseResultOld process(String input, Map<String, Macro> macros) {
		return ParseUtil.parseAndValidateSQFOld(input, getSQFParseInformation(macros));
	}

	/**
	 * Parses and validates the given input as SQF code. It assumes that there are
	 * no macros defined
	 * 
	 * @param input
	 *            The input to process
	 * @return The resulting {@link SQFParseResultOld}
	 */
	protected static SQFParseResultOld process(String input) {
		return process(input, new HashMap<String, Macro>());
	}

	/**
	 * Makes the given path (that uses "/" as a FileSeparator) compatible with the
	 * current OS by using the actual OS-FileSeparator
	 * 
	 * @param path
	 *            The path to process
	 * @return The processed path
	 */
	protected static String makeOSCompatible(String path) {
		return path.replace("/", File.separator);
	}

	/**
	 * Gets the content of the given file as a String
	 * 
	 * @param file
	 *            The respective file
	 * @return The file's content
	 */
	protected static String getContent(File file) {
		try {
			return FileSystemUtil.getContent(file);
		} catch (SQDevException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * Gets the needed SQFInformation
	 * 
	 * @param macros
	 *            The macro-list that should be used
	 */
	protected static ISQFInformation getSQFParseInformation(Map<String, Macro> macros) {
		return new SQFInformation(macros) {
			@Override
			protected String getKeywordContent() {
				try {
					return FileSystemUtil.getContent(KEYWORD_FILE);
				} catch (SQDevException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		};
	}

	/**
	 * Gets the keywords of the all the {@link Keyword}s contained in the given
	 * collection
	 * 
	 * @param list
	 *            The collection of {@link Keyword}s
	 * @return The respective keywords as Strings
	 */
	public static List<String> getKeywords(Collection<? extends Keyword> list) {
		List<String> keywords = new ArrayList<String>();

		for (Keyword current : list) {
			keywords.add(current.getKeyword());
		}

		return keywords;
	}

	/**
	 * Checks whether the given collections have the same content
	 * 
	 * @param collection1
	 *            The first collection
	 * @param collection2
	 *            The second collection
	 * @return <code>True</code> if both collections have the same content.
	 *         <code>False</code> otherwise
	 */
	public static boolean collectionContentEqual(Collection<?> collection1, Collection<?> collection2) {
		if (collection1.size() != collection2.size()) {
			return false;
		}

		return collection1.containsAll(collection2);
	}
}
