package SQF;
import javax.swing.text.DefaultEditorKit.CutAction;

public class Test2 {
	public static void main(String[] args) {
		
		String test = "Hallo mein Freund und Kupferstecher!"; //Originalstring, aus dem Elemenete entfent werden sollen
		
		String from1 = "mein";
		String to1 = "und";
		
		String test1 = Functions.removeFromTo(test, from1, to1);
		System.out.println(test1);
		
		
		
//		String[][] Array = new String[1][4];
//		
//		Array[0] = Test;
//		
//		System.out.println(Array[0][2]);
	}
}
