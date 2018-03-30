package raven.sqdev.sqf.processing.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import dataStructures.AbstractSQFTokenFactory;
import raven.sqdev.exceptions.SQDevException;
import raven.sqdev.interfaces.ISQFParseSupplier;
import raven.sqdev.interfaces.ITreeProcessingResult;
import raven.sqdev.misc.FileUtil;
import raven.sqdev.misc.Macro;
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
		macros = new HashMap<>();

		info = getSQFInformation(macros);
		SQFTokenFactory factory = new SQFTokenFactory(info.getBinaryKeywords(), info.getUnaryKeywords());
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
	public void test() throws IOException {
		ITreeProcessingResult result = ParseUtil.parseAndProcessSQF(new ByteArrayInputStream("player setPos getPos player".getBytes()), supplier,
				info);
		// TODO: It claims there is a semicolon missing
		assertTrue(result.getMarkers().size() == 0);
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
