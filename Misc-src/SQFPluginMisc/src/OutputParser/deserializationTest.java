package OutputParser;

import SQF.Functions;

public class deserializationTest {
	public static void main(String[] args) {
		String inputDirectoryPath = "NotSet";
		
		for(String argument : args) {
			if(argument.startsWith("/i=")) {
				//set the inputPath of the directory where the serialization is stored
				argument = argument.substring(3);
				inputDirectoryPath = argument;
			}
		}
		
		syntaxVariantArchive archive = new syntaxVariantArchive();
		
		archive.load(inputDirectoryPath);
		
		Functions.printArray(archive.getParameter());
		System.out.println(archive.size());
	}
}
