package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import OutputParser.Grammar;
import OutputParser.ParserRule;

public class GrammarTest {

	@Test
	public void isLeftRecursiveTest() {
		Grammar g1 = createLeftRecursiveGrammar();
		assertTrue(g1.isLeftRecursive());

		Grammar g2 = createLeftRecursiveGrammar(true);
		assertTrue(g2.isLeftRecursive());

		Grammar g3 = createStandardGrammar();
		assertFalse(g3.isLeftRecursive());
	}

	@Test
	public void needsLeftFactoringTest() {
		Grammar g1 = createLeftFactorGrammar();
		assertTrue(g1.needsLeftFactoring_I());
		assertFalse(g1.needsLeftFacoring_II());

		Grammar g2 = createLeftFactorGrammar(true);
		assertTrue(g2.needsLeftFacoring_II());
		assertFalse(g2.needsLeftFactoring_I());

		Grammar g3 = createStandardGrammar();
		assertFalse(g3.needsLeftFactoring());
	}

	@Test
	public void leftFactor_ITest() {
		Grammar g1 = createStandardGrammar();
		Grammar g2 = createStandardGrammar();

		g1.leftFactor_I();

		// Shouldn't have changed
		assertEquals(g2.toString(), g1.toString());

		Grammar g3 = createLeftFactorGrammar();
		assertTrue(g3.needsLeftFactoring_I());

		g3.leftFactor_I();
		assertFalse(g3.needsLeftFactoring_I());
	}

	@Test
	public void leftFactor_IITest() {
		Grammar g1 = createStandardGrammar();
		Grammar g2 = createStandardGrammar();

		g1.leftFactor_II();

		// Shouldn't have changed
		assertEquals(g2.toString(), g1.toString());

		Grammar g3 = createLeftFactorGrammar(true);
		assertTrue(g3.needsLeftFacoring_II());

		g3.leftFactor_II();

		assertFalse(g3.needsLeftFacoring_II());
	}

	// //////////////////////////////////////MISC/////////////////////////////////////////////////

	/**
	 * Creates a standard grammar with the given amount of rules
	 * 
	 * @param ruleNumber
	 *            How many rules should be added?
	 * @param sort
	 *            Should the grammar get sorted by default?
	 * @return The created grammar
	 */
	public static Grammar createStandardGrammar(int ruleNumber, boolean sort) {
		Grammar standardGrammar = new Grammar();

		// create rules and add them to the new grammar
		for (int i = 1; i <= ruleNumber; i++) {
			ParserRule rule = new ParserRule("test_Rule" + i);

			rule.addSyntax("This Test Syntax");
			rule.addSyntax("Another (Test | Syntax)");

			standardGrammar.addRule(rule);
		}

		// add empty standard rules to match the previous added syntax
		ParserRule rule1 = new ParserRule("This");
		ParserRule rule2 = new ParserRule("Test");
		ParserRule rule3 = new ParserRule("Syntax");
		ParserRule rule4 = new ParserRule("Another");

		rule1.setAsAtomicRule(true);
		rule2.setAsAtomicRule(true);
		rule3.setAsAtomicRule(true);
		rule4.setAsAtomicRule(true);

		standardGrammar.addRule(rule1);
		standardGrammar.addRule(rule2);
		standardGrammar.addRule(rule3);
		standardGrammar.addRule(rule4);

		if (sort) {
			standardGrammar.sort();
		}

		standardGrammar.createRuleForecast();
		standardGrammar.createStartRuleForecasts();

		return standardGrammar;
	}

	/**
	 * Creates a standard grammar with the given amount of rules and their
	 * dummyRules (grammar will be sorted)
	 * 
	 * @param ruleNumber
	 *            How many rules should be added?
	 * @return The created grammar
	 */
	public static Grammar createStandardGrammar(int ruleNumber) {
		return createStandardGrammar(ruleNumber, true);
	}

	/**
	 * Creates a standard grammar with 3 rules and their dummyRules (grammar
	 * gets sorted)
	 * 
	 * @return The created grammar
	 */
	public static Grammar createStandardGrammar() {
		return createStandardGrammar(3, true);
	}

	/**
	 * Creates a left recursive grammar
	 * 
	 * @param direct
	 *            Should the left recursion be direct?
	 * @return The created grammar
	 */
	public static Grammar createLeftRecursiveGrammar(boolean direct) {
		Grammar g = createStandardGrammar();

		// add left recursive rule

		if (direct) {
			ParserRule leftRule = new ParserRule("LeftRecRule");
			leftRule.setAsAtomicRule(true);
			leftRule.addLineToRuleContent(leftRule.getName());

			g.addRule(leftRule);
		} else {
			g.getRule(0).addSyntax("LeftRecRule");
			ParserRule leftRule = new ParserRule("LeftRecRule", g.getRule(0)
					.getName());
			leftRule.setAsAtomicRule(true);
			leftRule.addSyntax(g.getRule(0).getName());

			g.addRule(leftRule);
		}

		g.sort();
		g.createRuleForecast();
		g.createStartRuleForecasts();

		return g;
	}

	/**
	 * Creates a indirect left recursive grammar
	 * 
	 * @return The created grammar
	 */
	public static Grammar createLeftRecursiveGrammar() {
		return createLeftRecursiveGrammar(false);
	}

	/**
	 * Creates a grammar that has to get left factored
	 * 
	 * @param LF_II
	 *            Should the LF_II algorithm should be needed?
	 * @param leftRecursive
	 *            Should the grammar be left recursive (Will always be LF_II)
	 * @return The created grammar
	 */
	public static Grammar createLeftFactorGrammar(boolean LF_II,
			boolean leftRecursive) {
		Grammar g;

		if (leftRecursive) {
			g = createLeftRecursiveGrammar();
		} else {
			g = createStandardGrammar();

			if (LF_II) {
				// add rules for the need of being left factored
				ParserRule rule1 = new ParserRule("Rec1");
				rule1.setAsAtomicRule(true);
				rule1.addSyntax("Rec2 Test");
				rule1.addSyntax("Syntax");

				ParserRule rule2 = new ParserRule("Rec2");
				rule2.setAsAtomicRule(true);
				rule2.addSyntax("TestStart");
				rule2.addSyntax("Syntax Test");

				// create empty dummy rule to match syntax "testStart"
				ParserRule dummyRule = new ParserRule("testStart", "Rec2");
				dummyRule.setAsAtomicRule(true);

				g.addRule(rule1);
				g.addRule(rule2);
				g.addRule(dummyRule);
			} else {
				ParserRule rule1 = new ParserRule("Rec");
				rule1.setAsAtomicRule(true);
				rule1.addSyntax("Dummy Test");
				rule1.addSyntax("Syntax Test");
				rule1.addSyntax("Dummy Syntax");

				ParserRule dummy = new ParserRule("Dummy", rule1.getName());
				dummy.setAsAtomicRule(true);

				g.addRule(rule1);
				g.addRule(dummy);
			}

			g.sort();
			g.createRuleForecast();
			g.createStartRuleForecasts();
		}

		return g;
	}

	/**
	 * Creates a grammar that has to get left factored
	 * 
	 * @param LF_II
	 *            Should the LF_II algorithm should be needed?
	 * @return The created grammar
	 */
	public static Grammar createLeftFactorGrammar(boolean LF_II) {
		return createLeftFactorGrammar(LF_II, false);
	}

	/**
	 * Creates a grammar that has to get left factored (LF_I)
	 * 
	 * @return The created grammar
	 */
	public static Grammar createLeftFactorGrammar() {
		return createLeftFactorGrammar(false, false);
	}

}
