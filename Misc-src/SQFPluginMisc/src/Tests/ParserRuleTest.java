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
	
	@Test
	public void simplifyTest() {
		ParserRule rule1 = createBasicParserRule("Tester", 3, false);
		ParserRule rule2 = createBasicParserRule("Tester", 3, false);
		
		rule1.simplify();
		
		//shouldn't have changed
		assertEquals(rule2.toString(), rule1.toString());
		
		//optional call
		rule1.addSyntax("(test)? this is");
		rule2.addSyntax("this is");
		rule2.addSyntax("test this is");
		
		rule1.simplify();
		
		assertEquals(rule2.toString(), rule1.toString());
		
		//multi-ruleCall
		rule1.addSyntax("(test)* this is");
		rule2.addSyntax("this is");
		rule2.addSyntax("(test)+ this is");
		
		rule1.simplify();
		
		assertEquals(rule2.toString(), rule1.toString());
		
		ParserRule rule3 = createBasicParserRule("Tester", 3, false);
		ParserRule rule4 = createBasicParserRule("Tester", 3, false);
		
		rule3.addSyntax("(test)? this is");
		rule3.addSyntax("(miau)* that can be");
		
		rule4.addSyntax("this is");
		rule4.addSyntax("test this is");
		rule4.addSyntax("that can be");
		rule4.addSyntax("(miau)+ that can be");
		
		rule3.simplify();
		
		assertEquals(rule4.toString(), rule3.toString());
		
		ParserRule rule5 = createBasicParserRule("Tester", 3, false);
		ParserRule rule6 = createBasicParserRule("Tester", 3, false);
		
		rule5.addSyntax("(test)?");
		rule5.addSyntax("(test2)*");
		rule6.addSyntax("(test)?");
		rule6.addSyntax("(test2)*");
		
		rule5.simplify();
		
		//shouldn't have changed anything because it can't be simplified
		
		assertEquals(rule6.toString(), rule5.toString());
		
		ParserRule rule7 = createBasicParserRule("Tester", 3, false);
		ParserRule rule8 = createBasicParserRule("Tester", 3, false);
		
		rule7.addSyntax("(test1 | test2) this is");
		rule8.addSyntax("test1 this is");
		rule8.addSyntax("test2 this is");
		
		rule7.simplify();
		
		assertEquals(rule8.toString(), rule7.toString());
	}
	
	
	
	
////////////////////////////////////////////MISC///////////////////////////////////////////////////////

	/**
	 * Creates a basic ParserRule with some predefined content
	 * 
	 * @param name
	 *            The name of the rule
	 * @param alternatives
	 *            The amount of alternatives this ruleshould have
	 * @param choice
	 *            Whether or not the rule should contain ParserChoice within an
	 *            alternative
	 * @return The generated ParserRule
	 */
	public static ParserRule createBasicParserRule(String name,
			int alternatives, boolean choice) {
		ParserRule newRule = new ParserRule(name);

		newRule.setAsAtomicRule(true);

		for (int i = 0; i < (alternatives - 1); i++) {
			newRule.addSyntax("Alternative" + i + " this is");
		}

		if (choice) {
			newRule.addSyntax("(Alternative" + (alternatives - 1)
					+ " | Alternative" + alternatives + ") this is");
		} else {
			newRule.addSyntax("Alternative" + (alternatives - 1) + " this is");
		}

		newRule.format();

		return newRule;
	}

	/**
	 * Creates a basic ParserRule with some predefined content and a
	 * ParserChoice
	 * 
	 * @param name
	 *            The name of the rule
	 * @param alternatives
	 *            The amount of alternatives this ruleshould have
	 * @return The generated ParserRule
	 */
	public static ParserRule createBasicParserRule(String name, int alternatives) {
		return createBasicParserRule(name, alternatives, true);
	}

	/**
	 * Creates a basic ParserRule with some predefined content of 3 lines and a
	 * ParserChoice
	 * 
	 * @param name
	 *            The name of the rule
	 * @return The generated ParserRule
	 */
	public static ParserRule createBasicParserRule(String name) {
		return createBasicParserRule(name, 3, true);
	}

	/**
	 * Creates a basic ParserRule (name = "Dummy") with some predefined content
	 * of 3 lines and a ParserChoice
	 * 
	 * @return The generated ParserRule
	 */
	public static ParserRule createBasicParserRule() {
		return createBasicParserRule("Dummy", 3, true);
	}

}
