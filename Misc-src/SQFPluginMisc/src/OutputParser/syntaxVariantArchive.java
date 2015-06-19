package OutputParser;

import java.util.ArrayList;

import SQF.Functions;

public class syntaxVariantArchive {
	private ArrayList<syntaxVariant> syntaxVariantArchive_list;

	public syntaxVariantArchive() {
		syntaxVariantArchive_list = new ArrayList<syntaxVariant>();
	}

	public void addSyntaxVariant(syntaxVariant synVar) {
		syntaxVariantArchive_list.add(synVar);
	}

	/**
	 * @return The list of syntax variants
	 */
	public ArrayList<syntaxVariant> getArchive() {
		return this.syntaxVariantArchive_list;
	}

	/**
	 * Check if the given syntaxVariant is in this archive
	 * 
	 * @param synVar
	 *            The syntaxVariant to be searched for
	 * @return
	 */
	public boolean contains(syntaxVariant synVar) {
		if (this.syntaxVariantArchive_list.isEmpty()) {
			return false;
		}

		boolean contains = false;

		if (this.syntaxVariantArchive_list.indexOf(synVar) >= 0) {
			contains = true;
		}

		return contains;
	}

	/**
	 * Check if the given syntax is in this archive
	 * 
	 * @param syntax
	 *            The syntax to be searched for
	 * @return
	 */
	public boolean contains(String syntax) {
		if (this.syntaxVariantArchive_list.isEmpty()) {
			return false;
		}

		boolean contains = false;

		for (int i = 0; i < this.syntaxVariantArchive_list.size(); i++) {
			String currentSyntax = this.syntaxVariantArchive_list.get(i)
					.getSyntax();

			if (syntax.equals(currentSyntax)) {
				contains = true;
				break;
			}
		}

		return contains;
	}

	/**
	 * Adds a syntaxVariant to this archive if it is not already in it
	 * 
	 * @param synVar
	 */
	public void add(syntaxVariant synVar) {
		this.add(synVar, false);
	}

	/**
	 * Adds s syntaxVariant to this archive
	 * 
	 * @param synVar
	 *            The syntaxVariant that should be added
	 * @param noCheck
	 *            indicates if it should be checked if this archive already
	 *            contains this syntaxVariant
	 */
	public void add(syntaxVariant synVar, boolean noCheck) {
		if (noCheck && this.contains(synVar)) {
			// if the syntaxVariant is already in this archive don't store it
			// again
			return;
		}

		this.syntaxVariantArchive_list.add(synVar);
	}

	/**
	 * Adds a syntax to this archive (will be converted to a syntaxVariant with
	 * empty commands list)
	 * 
	 * @param syntax
	 */
	public void add(String syntax) {
		syntaxVariant synVar = new syntaxVariant();
		
		if(this.size() == 21) {
			String dummy = "";
		}

		synVar.setSyntax(syntax);

		this.add(synVar);
	}

	/**
	 * @return The size of the archive
	 */
	public int size() {
		return this.syntaxVariantArchive_list.size();
	}

	public String[] getParameter() {
		ArrayList<String> parameter = new ArrayList<String>();

		for (int i = 0; i < this.size(); i++) {
			syntaxVariant synVar = this.syntaxVariantArchive_list.get(i);
			String syntax = synVar.getSyntax();

			// remove all special characters
			if (syntax.indexOf("(") >= 0) {
				syntax = syntax.replaceAll("\\(", "");
			}
			if (syntax.indexOf(")") >= 0) {
				syntax = syntax.replaceAll("\\)", "");
			}
			if (syntax.indexOf("?") >= 0) {
				syntax = syntax.replaceAll("\\?", "");
			}
			if (syntax.indexOf("[") >= 0) {
				syntax = syntax.replaceAll("\\[", "");
			}
			if (syntax.indexOf("]") >= 0) {
				syntax = syntax.replaceAll("\\]", "");
			}
			if (syntax.indexOf(",") >= 0) {
				syntax = syntax.replaceAll(",", " ");
			}
			if (syntax.indexOf("/") >= 0) {
				syntax = syntax.replaceAll("/", " ");
			}

			String[] aSyntax = Functions.getElements(syntax);

			for (String current : aSyntax) {
				// if this parameter wasn't found before store it in parameter
				
				if(current.toLowerCase().equals("addmagazine")) {
					String dummy = "";
				}
				
				if (!parameter.contains(current) && !current.equals("commandNameKeyword")) {
					parameter.add(current);
				}
			}
		}

		int quantity = parameter.size();
		String[] aParameter = new String[quantity];

		for (int j = 0; j < quantity; j++) {
			String currentParameter = parameter.get(j);

			aParameter[j] = currentParameter;
		}

		return aParameter;
	}
	
	/**
	 * Returns a string with all the different syntaxes (no corrsponding commands) represented
	 *  by the stored syntaxVariants seperated with a newLine
	 */
	public String toString() {
		String allSyntaxes = "";
		
		for(syntaxVariant currentSynVar : this.syntaxVariantArchive_list) {
			String currentSyntax = currentSynVar.getSyntax();
			
			allSyntaxes += currentSyntax + "\n";
		}
		
		return allSyntaxes;
	}
	
	public int find(String syntax) {
/*		if(syntax.indexOf("commandNameKeyword") < 0) {
			System.err.println("ERROR: syntax must contain 'commandNameKeyword'");
			return -2;
		}*/
		
		for(int i=0; i<this.syntaxVariantArchive_list.size(); i++) {
			syntaxVariant current = this.syntaxVariantArchive_list.get(i);
			
			String currentSyntax = current.getSyntax();
			
			if(currentSyntax.equalsIgnoreCase(syntax)) {
				return i;
			}
		}
		
		//willbe executed if nothing was found
		System.out.println(syntax + " konnte nicht gefunden werden!");
		return -1;
	}
}
