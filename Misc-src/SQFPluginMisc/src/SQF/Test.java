package SQF;

public class Test {
	public static void main(String[] args) {
		
		String[] vars = {"miau",null,"Test"};
		
		boolean test = Functions.isIn(vars, null, true);
		
		System.out.println(test);
	}
}