package raven.sqdev.infoCollection.base;

import java.net.URL;
import java.util.ArrayList;

import org.eclipse.core.runtime.Assert;

import raven.sqdev.syntax.Syntax;

/**
 * A class representing a SQF command and all necessary information about it
 * 
 * @author Raven
 * 		
 */
public class SQFCommand extends Keyword {
	
	/**
	 * The syntaxes of this command
	 */
	private ArrayList<Syntax> syntaxes;
	
	/**
	 * An example of the usage of this command
	 */
	private ArrayList<String> examples;
	
	/**
	 * An array containing the locality of this command's arguments (index 0)
	 * and the locality of it's effect (index 1)
	 */
	private String[] locality;
	
	/**
	 * The url to the wiki page of this command
	 */
	private URL wikiPage;
	
	/**
	 * The notes attached to this command
	 */
	private ArrayList<String> notes;
	
	/**
	 * Creates an instance of a SQF command
	 * 
	 * @param command
	 *            The command itself
	 */
	public SQFCommand(String command) {
		this(command, null);
	}
	
	/**
	 * Creates an instance of a SQF command
	 * 
	 * @param command
	 *            The command itself
	 * @param description
	 *            The description for this command
	 */
	public SQFCommand(String command, String description) {
		super(command, description);
		
		
	}
	
	/**
	 * Gets the syntaxes of this command. If the syntaxlist has not been
	 * initialized yet this will initialize it as an empty list and then returns
	 * that newly created list.
	 * 
	 * @return The syntaxes of this command
	 */
	public ArrayList<Syntax> getSyntaxes() {
		if (syntaxes == null) {
			syntaxes = new ArrayList<Syntax>();
		}
		
		return syntaxes;
	}
	
	/**
	 * Sets the syntaxes of this command
	 * 
	 * @param syntaxes
	 *            The syntax to add
	 */
	public void setSyntaxes(ArrayList<Syntax> syntaxes) {
		Assert.isNotNull(syntaxes);
		
		this.syntaxes = syntaxes;
	}
	
	/**
	 * Adds a syntax to this command if it is not already registered
	 * 
	 * @param syntax
	 *            The syntax to add
	 */
	public void addSyntax(Syntax syntax) {
		Assert.isTrue(syntax != null && !syntax.isEmpty());
		
		if (!getSyntaxes().contains(syntax)) {
			getSyntaxes().add(syntax);
		}
	}
	
	/**
	 * Gets the examples associated with this command
	 * 
	 * @return A list containing the examples (empty list if there are no
	 *         examples)
	 */
	public ArrayList<String> getExamples() {
		if (examples != null) {
			examples = new ArrayList<String>();
		}
		
		return examples;
	}
	
	/**
	 * Sets the examples of this command
	 * 
	 * @param examples
	 *            The new set of examples
	 */
	public void setExamples(ArrayList<String> examples) {
		Assert.isNotNull(examples);
		
		this.examples = examples;
	}
	
	/**
	 * Adds an example to this command
	 * 
	 * @param example
	 *            The example to add
	 */
	public void addExample(String example) {
		Assert.isTrue(example != null && !example.isEmpty());
		
		if (!getExamples().contains(example)) {
			getExamples().add(example);
		}
	}
	
	/**
	 * Gets the locality of this command's arguments (index 0) and the locality
	 * of it's effect (index 1)
	 * 
	 * @return The array containg the localities
	 */
	public String[] getLocality() {
		return locality;
	}
	
	/**
	 * Sets the localities of this command
	 * 
	 * @param locality
	 */
	protected void setLocality(String[] locality) {
		Assert.isNotNull(locality);
		Assert.isTrue(locality.length == 2);
		
		this.locality = locality;
	}
	
	/**
	 * Sets the locality of the command's arguments
	 * 
	 * @param locality
	 *            The locality of the arguemnts
	 */
	public void setArgumentLocality(ELocality locality) {
		String[] loc = getLocality();
		loc[0] = locality.toString();
		
		setLocality(loc);
	}
	
	/**
	 * Gets this command's argument's locality
	 */
	public ELocality getArgumentLocality() {
		return ELocality.valueOf(getLocality()[0]);
	}
	
	/**
	 * Sets the locality of the command's effect
	 * 
	 * @param locality
	 *            The locality of the effect
	 */
	public void setEffectLocality(ELocality locality) {
		String[] loc = getLocality();
		loc[1] = locality.toString();
		
		setLocality(loc);
	}
	
	/**
	 * Gets this command's effect's locality
	 */
	public ELocality getEffectLocality() {
		return ELocality.valueOf(getLocality()[1]);
	}
	
	/**
	 * Gets the URL of this command's wiki page
	 */
	public URL getWikiPage() {
		return wikiPage;
	}
	
	/**
	 * Sets the URL to this command's wiki page
	 * 
	 * @param wikiPage
	 *            The wiki page URL
	 */
	public void setWikiPage(URL wikiPage) {
		Assert.isNotNull(wikiPage);
		
		this.wikiPage = wikiPage;
	}
	
	/**
	 * Checks whether the wiki page of this command has been set
	 */
	public boolean isWikiGiven() {
		return getWikiPage() != null;
	}
	
	/**
	 * Gets the notes of this command
	 */
	public ArrayList<String> getNotes() {
		if (notes == null) {
			notes = new ArrayList<String>();
		}
		
		return notes;
	}
	
	/**
	 * Sets the notes for this command
	 * 
	 * @param notes
	 *            The new notes
	 */
	public void setNotes(ArrayList<String> notes) {
		this.notes = notes;
	}
	
	/**
	 * Adds a note ot he ones already associated to this command
	 * 
	 * @param note
	 */
	public void addNote(String note) {
		if (!getNotes().contains(note)) {
			getNotes().add(note);
		}
	}
}
