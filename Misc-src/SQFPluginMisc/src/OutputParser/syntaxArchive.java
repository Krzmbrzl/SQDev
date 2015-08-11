package OutputParser;

import java.util.ArrayList;

public class syntaxArchive {
	private ArrayList<syntax> syntaxList;
	private ArrayList<syntaxVariant> synVarList;
	private syntaxVariantArchive synVarArchive;
	private String archiveName;

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
	 * @return The name of this archive
	 */
	public String getName() {
		return this.archiveName;
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
	 * Sets the name for this archive
	 * @param name
	 */
	public void setName(String name) {
		archiveName = name;
	}
	
	/**
	 * sorts all syntaxes because of the syntaxVariants
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
	
	public syntaxVariantArchive getArchive() {
		return this.synVarArchive;
	}
	
	/**
	 * formats the complete archive
	 */
	public void format() {
		synVarArchive.format();
	}
}
