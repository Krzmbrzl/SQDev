package raven.sqdev.sqf.processing.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import dataStructures.AbstractSQFTokenFactory;
import raven.sqdev.constants.ProblemMessages;
import raven.sqdev.exceptions.SQDevException;
import raven.sqdev.interfaces.ISQFParseSupplier;
import raven.sqdev.interfaces.ITreeProcessingResult;
import raven.sqdev.misc.DataTypeList;
import raven.sqdev.misc.EDataType;
import raven.sqdev.misc.FileUtil;
import raven.sqdev.misc.Macro;
import raven.sqdev.misc.Marker;
import raven.sqdev.parser.misc.ParseUtil;
import raven.sqdev.parser.misc.SQFTokenFactory;
import raven.sqdev.parser.sqf.SQFInformation;

public class SQFProcessingTest {

	public static final File KEYWORD_FILE = new File(
			makeOSCompatible(System.getProperty("user.dir") + "/resources/sqf/SQFKeywords.txt"));

	protected static SQFInformation info;
	protected static ISQFParseSupplier supplier;
	protected static Map<String, Macro> macros;

	@Before
	public void setUp() throws Exception {
		macros = new HashMap<String, Macro>();

		info = getSQFInformation(macros);
		SQFTokenFactory factory = new SQFTokenFactory(info.getBinaryKeywords(), info.getUnaryKeywords());
		factory.initialize();

		supplier = new ISQFParseSupplier() {

			@Override
			public AbstractSQFTokenFactory getTokenFactory() {
				return factory;
			}

			@Override
			public Map<String, Macro> getMacros() {
				return macros;
			}
		};
	}

	@Test
	public void semicolons() throws IOException {
		ITreeProcessingResult result = ParseUtil
				.parseAndProcessSQF(new ByteArrayInputStream("player; hint ''".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);

		result = ParseUtil.parseAndProcessSQF(
				new ByteArrayInputStream("player setPos getPos leader group player".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);

		result = ParseUtil.parseAndProcessSQF(
				new ByteArrayInputStream("player setPos getPos leader group player; private _i = 4".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 0);

		macros.put("CHECK", new Macro("CHECK"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("CHECK hint \"Test\"".getBytes()), supplier,
				info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);

		result = ParseUtil.parseAndProcessSQF(
				new ByteArrayInputStream("CHECK(some stuff in here) hint \"Test\"".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);

		result = ParseUtil.parseAndProcessSQF(
				new ByteArrayInputStream("CHECK(some, ;stuff in hint str player) hint \"Test\"".getBytes()), supplier,
				info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);

		result = ParseUtil.parseAndProcessSQF(
				new ByteArrayInputStream("CHECK(some stuff in here); hint \"Test\"".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);

		macros.clear();
	}

	@Test
	public void semicolonErrors() throws IOException {
		ITreeProcessingResult result = ParseUtil
				.parseAndProcessSQF(new ByteArrayInputStream("player hint ''".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.missingSemicolon("player"), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("i=1 hint ''".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 1);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.missingSemicolon("1"), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(
				new ByteArrayInputStream("player setPos getPos player disableSerialization".getBytes()), supplier,
				info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.missingSemicolon("player"), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("call {3 disableSerialization}".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.missingSemicolon("3"), result.getMarkers().iterator().next().getMessage());
	}

	@Test
	public void assignments() throws IOException {
		ITreeProcessingResult result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("myVar = 3".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 1);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredGlobalVariables().keySet().contains("myvar"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("myVar = 3;".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 1);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredGlobalVariables().keySet().contains("myvar"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("_myVar = 3".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_myvar"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("_myVar = 3;".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_myvar"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("private _myVar = 3".getBytes()), supplier,
				info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_myvar"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("private _myVar = 3;".getBytes()), supplier,
				info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_myvar"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("_myVar = []".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_myvar"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("_myVar = [];".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_myvar"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("_myVar = {}".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_myvar"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("_myVar = {};".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_myvar"));

		macros.put("GVAR", new Macro("GVAR"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("GVAR(myVar) = {}".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 1);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredGlobalVariables().keySet().contains("GVAR(myVar)"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("GVAR(myVar) = {}".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 1);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredGlobalVariables().keySet().contains("GVAR(myVar)"));

		macros.clear();
	}

	@Test
	public void assignmentErros() throws IOException {
		ITreeProcessingResult result = ParseUtil
				.parseAndProcessSQF(new ByteArrayInputStream("someOperator myVar = 3".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 2);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.missingSemicolon("someOperator"),
				result.getMarkers().iterator().next().getMessage());
		assertTrue(result.getDeclaredGlobalVariables().keySet().contains("myvar"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("player = 3".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.reservedKeyword("player"), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("= 3".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.operatorIsNotUnary("="), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("=".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.operatorIsNotNular("="), result.getMarkers().iterator().next().getMessage());
	}

	@Test
	public void arrays() throws IOException {
		ITreeProcessingResult result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("[]".getBytes()), supplier,
				info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("[[]]".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("[2]".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("[2, 3]".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("[2, \"\"]".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("[2, []]".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("[2, [5, 7]]".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("[2, {}]".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("[2, {test = [2, 'three']}]".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 1);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredGlobalVariables().keySet().contains("test"));
	}

	@Test
	public void arrayErrors() throws IOException {
		ITreeProcessingResult result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("[3,]".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.misplacedToken(","), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("[,]".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.misplacedToken(","), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("[[,]]".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.misplacedToken(","), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("[[3,]]".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.misplacedToken(","), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("[[3],]".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.misplacedToken(","), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("[3 3]".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.missingComma("3"), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("[3 []]".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.missingComma("3"), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("[[] 3]".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.missingComma("]"), result.getMarkers().iterator().next().getMessage());
	}

	@Test
	public void privateTest() throws IOException {
		ITreeProcessingResult result = ParseUtil
				.parseAndProcessSQF(new ByteArrayInputStream("private _myVar = 3".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().iterator().next().equals("_myvar"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("private \"_myVar\"".getBytes()), supplier,
				info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_myvar"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("private ['_myVar', \"_yourVar\"]".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 2);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_myvar"));
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_yourvar"));
	}

	@Test
	public void privateErrors() throws IOException {
		ITreeProcessingResult result = ParseUtil
				.parseAndProcessSQF(new ByteArrayInputStream("private myVar = 3".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.privateVariablesMustBeLocal(), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("private \"myVar\"".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.canOnlyDeclareLocalVariable(), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("private ['myVar', \"_yourVar\"]".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.canOnlyDeclareLocalVariable(), result.getMarkers().iterator().next().getMessage());
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_yourvar"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("private ['_myVar', \"yourVar\"]".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.canOnlyDeclareLocalVariable(), result.getMarkers().iterator().next().getMessage());
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_myvar"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("private ['myVar', \"yourVar\"]".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 2);
		assertEquals(ProblemMessages.canOnlyDeclareLocalVariable(), result.getMarkers().iterator().next().getMessage());
		assertEquals(ProblemMessages.canOnlyDeclareLocalVariable(), result.getMarkers().iterator().next().getMessage());
	}

	@Test
	public void forTest() throws IOException {
		ITreeProcessingResult result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("for \"_i\"".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_i"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("for \"_i\" from 1 to 4".getBytes()), supplier,
				info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_i"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("for \"_i\" from 1 to 4 do {}".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_i"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("for \"_i\" from 1 to 4 do {};".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_i"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("for '_i'".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_i"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("for '_i' from 1 to 4".getBytes()), supplier,
				info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_i"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("for '_i' from 1 to 4 do {}".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_i"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("for '_i' from 1 to 4 do {};".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_i"));

		result = ParseUtil.parseAndProcessSQF(
				new ByteArrayInputStream("for [{_i = 0}, {_i < 5}, {_i = _i + 1}]".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_i"));
	}

	@Test
	public void forErrors() throws IOException {
		ITreeProcessingResult result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("for \"i\"".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.canOnlyDeclareLocalVariable(), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("for 'i'".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.canOnlyDeclareLocalVariable(), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("for '_ i'".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.variableMayNotContainBlank(), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("for [{_i = 0}, {_i < 5}]".getBytes()), supplier,
				info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.expectedArrayLength(3, 2), result.getMarkers().iterator().next().getMessage());
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_i"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("for [{_i = 0}, {_i < 5}, '']".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.expectedTypeButGot(new DataTypeList(EDataType.CODE),
				new DataTypeList(EDataType.STRING)), result.getMarkers().iterator().next().getMessage());
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_i"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("for [{_i = 0}, {_i < 5}, \"\"]".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.expectedTypeButGot(new DataTypeList(EDataType.CODE),
				new DataTypeList(EDataType.STRING)), result.getMarkers().iterator().next().getMessage());
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_i"));
	}

	@Test
	public void params() throws IOException {
		ITreeProcessingResult result = ParseUtil
				.parseAndProcessSQF(new ByteArrayInputStream("params [\"_i\", \"_j\"]".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 2);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_i"));
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_j"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("params [\"_i\", \"\", \"_j\"]".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 2);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_i"));
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_j"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("params ['_i', '_j']".getBytes()), supplier,
				info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 2);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_i"));
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_j"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("params ['_i', '', '_j']".getBytes()), supplier,
				info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 2);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_i"));
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_j"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("params []".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("params [[\"_testVar\", nil]]".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_testvar"));

		result = ParseUtil.parseAndProcessSQF(
				new ByteArrayInputStream("params [[\"_testVar\", nil], ['_secondVar', player]]".getBytes()), supplier,
				info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 2);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_testvar"));
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_secondvar"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("params [[\"_testVar\", nil, []]]".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_testvar"));

		result = ParseUtil.parseAndProcessSQF(
				new ByteArrayInputStream("params [[\"_testVar\", nil, [player, \"\"]]]".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_testvar"));

		result = ParseUtil.parseAndProcessSQF(
				new ByteArrayInputStream("params [[\"_testVar\", nil, [player, \"\"], 3]]".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_testvar"));

		result = ParseUtil.parseAndProcessSQF(
				new ByteArrayInputStream("params [[\"_testVar\", nil, [player, \"\"], [3,4]]]".getBytes()), supplier,
				info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_testvar"));
	}

	@Test
	public void paramsErrors() throws IOException {
		ITreeProcessingResult result = ParseUtil
				.parseAndProcessSQF(new ByteArrayInputStream("params [\"i\"]".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.canOnlyDeclareLocalVariable(), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("params [\"i\", \"\", \"_j\"]".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.canOnlyDeclareLocalVariable(), result.getMarkers().iterator().next().getMessage());
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_j"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("params [[]]".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.expectedMinimumArrayLength(2, 0),
				result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("params [['_test']]".getBytes()), supplier,
				info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.expectedMinimumArrayLength(2, 1),
				result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("params [[nil, nil, nil, nil, nil]]".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.expectedMaximalArrayLength(4, 5),
				result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("params [[\"testVar\", nil]]".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.canOnlyDeclareLocalVariable(), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(
				new ByteArrayInputStream("params [[\"_testVar\", nil], ['secondVar', player]]".getBytes()), supplier,
				info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.canOnlyDeclareLocalVariable(), result.getMarkers().iterator().next().getMessage());
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_testvar"));

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("params [[3, nil]]".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.expectedTypeButGot(new DataTypeList(EDataType.STRING),
				new DataTypeList(EDataType.NUMBER)), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("params [[\"_testVar\", nil, 3]]".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.expectedTypeButGot(new DataTypeList(EDataType.ARRAY),
				new DataTypeList(EDataType.NUMBER)), result.getMarkers().iterator().next().getMessage());
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_testvar"));

		result = ParseUtil.parseAndProcessSQF(
				new ByteArrayInputStream("params [[\"_testVar\", nil, [], \"\"]]".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 1);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.expectedTypeButGot(
				new DataTypeList(new EDataType[] { EDataType.NUMBER, EDataType.ARRAY }),
				new DataTypeList(EDataType.STRING)), result.getMarkers().iterator().next().getMessage());
		assertTrue(result.getDeclaredLocalVariables().keySet().contains("_testvar"));
	}

	@Test
	public void nularExpressions() throws IOException {
		ITreeProcessingResult result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("3".getBytes()), supplier,
				info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("[]".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("{}".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("{player}".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("\"Test\"".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("'Test'".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("player".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("player; disableSerialization".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);
	}

	@Test
	public void nularExpressionErrors() throws IOException {
		ITreeProcessingResult result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("_testVar".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.undefinedLocalVariable("_testVar"),
				result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("[_testVar]".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.undefinedLocalVariable("_testVar"),
				result.getMarkers().iterator().next().getMessage());
	}

	@Test
	public void unaryExpressions() throws IOException {
		ITreeProcessingResult result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("hint 'Test'".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("hint \"Test\"".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("hint format []".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);
	}

	@Test
	public void unaryExpressionErrors() throws IOException {
		ITreeProcessingResult result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("hint 3".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.expectedTypeButGot(
				new DataTypeList(new EDataType[] { EDataType.STRING, EDataType.STRUCTURED_TEXT }),
				new DataTypeList(EDataType.NUMBER)), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("hint []".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.expectedTypeButGot(
				new DataTypeList(new EDataType[] { EDataType.STRING, EDataType.STRUCTURED_TEXT }),
				new DataTypeList(EDataType.ARRAY)), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("hint {}".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.expectedTypeButGot(
				new DataTypeList(new EDataType[] { EDataType.STRING, EDataType.STRUCTURED_TEXT }),
				new DataTypeList(EDataType.CODE)), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("[hint {}]".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.expectedTypeButGot(
				new DataTypeList(new EDataType[] { EDataType.STRING, EDataType.STRUCTURED_TEXT }),
				new DataTypeList(EDataType.CODE)), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("{hint {}}".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.expectedTypeButGot(
				new DataTypeList(new EDataType[] { EDataType.STRING, EDataType.STRUCTURED_TEXT }),
				new DataTypeList(EDataType.CODE)), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("hint player".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.expectedTypeButGot(
				new DataTypeList(new EDataType[] { EDataType.STRING, EDataType.STRUCTURED_TEXT }),
				new DataTypeList(EDataType.OBJECT)), result.getMarkers().iterator().next().getMessage());
	}

	@Test
	public void binaryExpressions() throws IOException {
		ITreeProcessingResult result = ParseUtil
				.parseAndProcessSQF(new ByteArrayInputStream("player setPos [0,0,0]".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("player setPos getPos player".getBytes()),
				supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);

		result = ParseUtil.parseAndProcessSQF(
				new ByteArrayInputStream("player setPos getPos leader group player".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("[] select params []".getBytes()), supplier,
				info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 0);
	}

	@Test
	public void binaryExpressionErrors() throws IOException {
		ITreeProcessingResult result = ParseUtil
				.parseAndProcessSQF(new ByteArrayInputStream("player setPos 3".getBytes()), supplier, info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.expectedTypeButGot(new DataTypeList(EDataType.ARRAY),
				new DataTypeList(EDataType.NUMBER)), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("player setPos time".getBytes()), supplier,
				info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.expectedTypeButGot(new DataTypeList(EDataType.ARRAY),
				new DataTypeList(EDataType.NUMBER)), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("time setPos [0,0,0]".getBytes()), supplier,
				info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.expectedTypeButGot(new DataTypeList(EDataType.OBJECT),
				new DataTypeList(EDataType.NUMBER)), result.getMarkers().iterator().next().getMessage());
		
		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("[time setPos [0,0,0]]".getBytes()), supplier,
				info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 1);
		assertEquals(ProblemMessages.expectedTypeButGot(new DataTypeList(EDataType.OBJECT),
				new DataTypeList(EDataType.NUMBER)), result.getMarkers().iterator().next().getMessage());

		result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("hint setPos [0,0,0]".getBytes()), supplier,
				info);
		assertTrue(result.getDeclaredGlobalVariables().size() == 0);
		assertTrue(result.getDeclaredLocalVariables().size() == 0);
		assertTrue(result.getMarkers().size() == 2);
		Iterator<Marker> it = result.getMarkers().iterator();
		assertEquals(ProblemMessages.operatorIsNotUnary("setPos"), it.next().getMessage());
		assertEquals(ProblemMessages.expectedTypeButGot(
				new DataTypeList(new EDataType[] { EDataType.STRING, EDataType.STRUCTURED_TEXT }),
				new DataTypeList(EDataType.NOTHING)), it.next().getMessage());
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
	 * Gets the needed SQFInformation
	 * 
	 * @param macros
	 *            The macro-list that should be used
	 */
	protected static SQFInformation getSQFInformation(Map<String, Macro> macros) {
		return new SQFInformation(macros) {
			@Override
			protected String getKeywordContent() {
				try {
					return FileUtil.getContent(KEYWORD_FILE);
				} catch (SQDevException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		};
	}

}
