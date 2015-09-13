package OutputParser;


public class deserializationTest {
	public static void main(String[] args) {
		ParserRule test1 = new ParserRule("test1");
		test1.create();
		
		ParserRule test2 = new ParserRule("test2");
		test2.create();
		
		Grammar testGrammar = new Grammar();
		testGrammar.addRule(test1);
		testGrammar.addRule(test2);
		
		for(ParserRule current : testGrammar.getRules()) {
			testGrammar.addRule(new ParserRule("depp"));
		}
		
		System.out.println(testGrammar.toString());
	}
}
