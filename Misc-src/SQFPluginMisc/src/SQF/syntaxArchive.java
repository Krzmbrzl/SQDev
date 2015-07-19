package SQF;

import java.util.ArrayList;

import OutputParser.syntax;
import OutputParser.syntaxVariant;
import OutputParser.syntaxVariantArchive;

public class syntaxArchive {
	private ArrayList<syntax> syntaxList;
	private ArrayList<syntaxVariant> synVarList;
	private syntaxVariantArchive synVarArchive;

	public syntaxArchive() {
		syntaxList = new ArrayList<syntax>();
		synVarList = new ArrayList<syntaxVariant>();
		synVarArchive = new syntaxVariantArchive();
	}

	public syntaxArchive(syntax newSyntax) {
		syntaxList = new ArrayList<syntax>();
		syntaxList.add(newSyntax);
		
		synVarList = new ArrayList<syntaxVariant>();
		synVarArchive = new syntaxVariantArchive();
	}

	/**
	 * Adds a syntax tho this archive
	 * 
	 * @param newSyntax
	 */
	public void add(syntax newSyntax) {
		syntaxList.add(newSyntax);
	}

	/**
	 * sorts all syntaxes because od the syntaxVariants
	 */
	public void sort() {
		for (syntax current : this.syntaxList) {
			current.format();
			
			for (String syntax : current.getSyntaxList()) {
				syntaxVariant helpSyntaxVariant = new syntaxVariant();
				helpSyntaxVariant.setSyntax(syntax);
				
				int pos = this.synVarArchive.find(syntax);
				
				if(pos < 0) {
					helpSyntaxVariant.addCommand(current.getCommandName());
					synVarArchive.add(helpSyntaxVariant);
				}else {
					synVarArchive.get(pos).addCommand(current.getCommandName());
				}
			}
		}
	}
}
