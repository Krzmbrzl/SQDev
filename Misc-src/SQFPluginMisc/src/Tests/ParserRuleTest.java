package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import OutputParser.ParserRule;

public class ParserRuleTest {

	@Test
	public void needsLeftFactoring_ITest() {
		ParserRule rule = new ParserRule("rule");

		rule.setAsAtomicRule(true);

		rule.addSyntax("Start Following");
		rule.addSyntax("Test test");

		assertFalse(rule.needsLeftFactoring_I());

		rule.addSyntax("Start Next");

		assertTrue(rule.needsLeftFactoring_I());
	}

	@Test
	public void removeRuleCallTest() {
		ParserRule rule = new ParserRule("rule");

		rule.setAsAtomicRule(true);

		rule.addSyntax("This is a test");

		rule.removeRuleCall("is");

		assertTrue(rule.isEmpty());

		rule.addSyntax("This (is | are) test");

		rule.removeRuleCall("are");

		assertEquals("This is test", rule.getRuleContent());

		ParserRule cRule = new ParserRule("rule");
		cRule.setAsAtomicRule(true);

		cRule.addSyntax("This (is | are) tist");

		cRule.removeRuleCall("is");

		assertEquals("This are tist", cRule.getRuleContent());

		ParserRule cRule2 = new ParserRule("rule");
		cRule2.setAsAtomicRule(true);

		cRule2.addSyntax("Miau this is");

		cRule2.removeRuleCall("is");

		assertTrue(cRule2.isEmpty());
	}

}
