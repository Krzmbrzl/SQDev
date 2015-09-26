package OutputParser;

import SQF.Functions;


public class test {
	public static void main(String[] args) {
		ParserRule rule1 = new ParserRule("rule1");		
		
		//System.out.println(rule1.toString());
		
		ParserRule subRule1 = new ParserRule("sub1", "rule1");
		ParserRule subSubRule = new ParserRule("subSub1", "sub1");
		ParserRule rule2 = new ParserRule("rule2");
		ParserRule sub2 = new ParserRule("sub2", "rule2");
		ParserRule terminal1 = new ParserRule("testTerminal", true);
		
		syntaxVariant testVariant = new syntaxVariant();
		testVariant.setSyntax("Rule1 commandNameKeyword");
		testVariant.addCommand("mama");
		testVariant.addCommand("papa");
		
		syntaxVariant testVar2 = new syntaxVariant("Rule1 commandNameKeyword Rule2");
		testVar2.addCommand("oma");
		testVar2.addCommand("opa");
		
		syntaxVariant rule1Var = new syntaxVariant();
		rule1Var.setSyntax("commandNameKeyword Sub1");
		rule1Var.addCommand("testCommand1");
		rule1Var.addCommand("testCommand2");
		rule1Var.format();
		
		rule1.addSyntaxVariant(testVariant);
		rule1.addSyntaxVariant(rule1Var);
		rule1.addSyntaxVariant(testVar2);
		
		sub2.addSyntax("Miau is so damn cool");
		rule2.addSyntax("testRuleCall rule1 mamaAndSoOn");
		
		subRule1.addSyntax("This isssss a testSyntax");
		
		Grammar testGrammar = new Grammar();
		testGrammar.addRule(rule2);
		testGrammar.addRule(subSubRule);
		testGrammar.addRule(subRule1);
		testGrammar.addRule(rule1);
		testGrammar.addRule(sub2);
		testGrammar.addRule(terminal1);
		
		testGrammar.associate();
		
		testGrammar.sort();
		
		testGrammar.format();
		
		testGrammar.createAssignments();
		
		//System.out.println(terminal1.toString());
		
		//System.out.println(rule1.getAllSubRules().toString());
		
		System.out.println(testGrammar.toString() + "\n");
		
		Functions.printUnescaped(testGrammar.toString());
		
		System.out.println(Functions.ArrayToString(rule1.getLines()));
		
		System.out.println(5 % 10);
	}
}
