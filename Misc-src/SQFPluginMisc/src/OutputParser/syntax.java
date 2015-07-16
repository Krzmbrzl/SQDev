package OutputParser;

import java.util.ArrayList;

public class syntax {
	private String syntax_commandName;
	private String syntax_returnType;
	private ArrayList<String> syntax_syntaxList;

	public syntax(String commandName, String returnType,
			ArrayList<String> syntaxList) {
		syntax_commandName = commandName;
		syntax_returnType = returnType;
		syntax_syntaxList = syntaxList;
	}

	public syntax(ArrayList<String> syntaxList) {
		syntax_commandName = "NotSet";
		syntax_returnType = "NotSet";
		syntax_syntaxList = syntaxList;
	}

	public syntax() {
		syntax_commandName = "NotSet";
		syntax_returnType = "NotSet";
		syntax_syntaxList = new ArrayList<String>();
	}

	/**
	 * @return The name of the command
	 */
	public String getCommandName() {
		return this.syntax_commandName;
	}

	/**
	 * @return The return-type for this command
	 */
	public String getReturnType() {
		return this.syntax_returnType;
	}

	/**
	 * @return The list of possible syntaxes of this command
	 */
	public ArrayList<String> getSyntaxList() {
		return this.syntax_syntaxList;
	}

	/**
	 * Defines the name of the command
	 * 
	 * @param commandName
	 *            The name of the command
	 */
	public void setCommandName(String commandName) {
		if (commandName == null || commandName.equals("")) {
			// No sense in writing an empty commandName
			return;
		}

		this.syntax_commandName = commandName;
	}

	/**
	 * Defines the return type of this command
	 * 
	 * @param returnType
	 *            The return type
	 */
	public void setReturnType(String returnType) {
		if (returnType == null || returnType.equals("")) {
			// No sense in writing an empty returnType
			return;
		}

		this.syntax_returnType = returnType;
	}

	/**
	 * Sets the list of possible syntaxes of this command
	 * 
	 * @param syntaxList
	 *            The list containing the possible syntaxes
	 */
	public void setSyntaxList(ArrayList<String> syntaxList) {
		if (syntaxList == null || syntaxList.isEmpty()) {
			// No sense in writing an empty SyntaxList
			return;
		}
		this.syntax_syntaxList = syntaxList;
	}

	/**
	 * Adds a syntax to the possible list of syntaxes
	 * 
	 * @param syntax
	 *            The syntax which should be added
	 */
	public void addToSyntaxList(String syntax) {
		if (syntax == null || syntax.equals("")) {
			// no sense in adding an empty syntax to this list
			return;
		}

		this.syntax_syntaxList.add(syntax);
	}

	/**
	 * Converts the syntax-object into a readable form
	 */
	public String toString() {
		String str = "name: " + this.syntax_commandName + "\n";

		for (int i = 0; i < this.syntax_syntaxList.size(); i++) {
			str += this.syntax_syntaxList.get(i) + "\n";
		}

		str += "returnType: " + this.syntax_returnType;

		return str;
	}

	/**
	 * This will overwrite the keyoword of the command in the syntax with
	 * "commandNameKeyword". That will be performed for all syntaxes associated
	 * with this command
	 */
	public void format() {
		String name = this.syntax_commandName;
		String altName = "NotSet";

		if (name.indexOf("_") >= 0) {
			altName = name.substring(0, name.indexOf("_"));
		}

		for (int i = 0; i < this.syntax_syntaxList.size(); i++) {
			//format syntax
			String syntax = this.syntax_syntaxList.get(i);

			int startPos = syntax.indexOf(name.toLowerCase());
			int endPos = startPos + name.length();

			if (startPos >= 0) {
				String fragment1 = syntax.substring(0, startPos);
				String fragment2 = syntax.substring(endPos);
				syntax = fragment1 + "commandNameKeyword" + fragment2;

				this.syntax_syntaxList.set(i, syntax);
			} else {
				// if the name was not found it might be beacause of an
				// underscore in the name
				// -> try without the underscore
				startPos = syntax.indexOf(altName.toLowerCase());
				endPos = startPos + altName.length();

				if (startPos >= 0) {
					String fragment1 = syntax.substring(0, startPos);
					String fragment2 = syntax.substring(endPos);
					syntax = fragment1 + "commandNameKeyword" + fragment2;

					this.setCommandName(altName); // remove the part with the
													// underscore because it
													// actually doesn't show up
													// in the syntax

					this.syntax_syntaxList.set(i, syntax);
				}
			}
			
			//format return
			String returnType = this.syntax_returnType;
			
			if(returnType.equals("NotSet")) {
				this.setReturnType("None");
			}
		}
	}
}
