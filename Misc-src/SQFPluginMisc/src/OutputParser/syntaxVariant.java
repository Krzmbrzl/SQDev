package OutputParser;

import java.util.ArrayList;

public class syntaxVariant implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String syntaxVariant_syntax;
	private ArrayList<String> syntaxVariant_commands;
	
	//TODO: constructor for a syntax-object
	
	public syntaxVariant(String syntax) {
		syntaxVariant_syntax = syntax;
		syntaxVariant_commands = new ArrayList<String>();
	}
	
	public syntaxVariant() {
		syntaxVariant_syntax = "NotSet";
		syntaxVariant_commands = new ArrayList<String>();
	}
	
	/**
	 * @return The syntax variant represented by this object
	 */
	public String getSyntax() {
		return this.syntaxVariant_syntax;
	}
	
	/**
	 * @return The list of commands using this syntax
	 */
	public ArrayList<String> getCommands() {
		return this.syntaxVariant_commands;
	}
	
	/**
	 * Set the general syntax variant this object shall represent
	 * @param syntax The general syntax
	 */
	public void setSyntax(String syntax) {
		if(syntax == null || syntax.equals("")) {
			//no sense in storing an empty syntax
			return;
		}
		
		this.syntaxVariant_syntax = syntax;
	}
	
	/**
	 * Adds a command which makes use of this syntax
	 * @param command The command name
	 */
	public void addCommand(String command) {
		if(command == null || command.equals("")) {
			//no sense in writing an empty command
			return;
		}
		
		this.syntaxVariant_commands.add(command);
	}
}
