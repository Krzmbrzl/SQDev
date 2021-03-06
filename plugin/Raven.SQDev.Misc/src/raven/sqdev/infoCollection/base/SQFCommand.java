package raven.sqdev.infoCollection.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.Assert;

import raven.sqdev.exceptions.BadSyntaxException;
import raven.sqdev.exceptions.SQDevException;
import raven.sqdev.misc.DataTypeList;
import raven.sqdev.misc.EDataType;
import raven.sqdev.misc.SQDev;
import raven.sqdev.syntax.Syntax;

/**
 * A <code>SQFElement</code> representing a SQF command and all necessary
 * information about it
 * 
 * @author Raven
 * 
 */
public class SQFCommand extends SQFElement {
	
	/**
	 * The seperator used to seperate different data types. this field has been
	 * relocated to {@link EDataType} as it makes more sense to specify it
	 * there. This field does only support for backwards-compability reasons.
	 * 
	 * @deprecated
	 */
	public static final String TYPE_SEPERATOR = DataTypeList.TYPE_SEPERATOR;
	/**
	 * The seperator used in order to show that the different sets of return
	 * values correspond to a different syntax
	 */
	public static final String RETURN_VALUE_SYNTAX_SEPERATOR = " - ";
	
	/**
	 * The sequence indicating the start of the syntax attribute in the saveable
	 * String format of this class
	 */
	public static final String SYNTAX_START_SAVESEQUENCE = "<Syntax>";
	/**
	 * The sequence indicating the end of the syntax attribute in the saveable
	 * String format of this class
	 */
	public static final String SYNTAX_END_SAVESEQUENCE = "</Syntax>";
	/**
	 * The sequence seperating multiple syntaxes in the saveable String format
	 * of this class
	 */
	public static final String SYNTAX_SEPERATOR_SAVESEQUENCE = "</NextSyntax>";
	/**
	 * The sequence indicating the start of the rawSyntax attribute in the
	 * saveable String format of this class
	 */
	public static final String RAWSYNTAX_START_SAVESEQUENCE = "<RawSyntax>";
	/**
	 * The sequence indicating the end of the rawSyntax attribute in the
	 * saveable String format of this class
	 */
	public static final String RAWSYNTAX_END_SAVESEQUENCE = "</RawSyntax>";
	/**
	 * The sequence seperating multiple rawSyntaxes in the saveable String
	 * format of this class
	 */
	public static final String RAWSYNTAX_SEPERATOR_SAVESEQUENCE = "</NextRawSyntax>";
	/**
	 * The sequence indicating the start of the example attribute in the
	 * saveable String format of this class
	 */
	public static final String EXAMPLE_START_SAVESEQUENCE = "<Example>";
	/**
	 * The sequence indicating the end of the example attribute in the saveable
	 * String format of this class
	 */
	public static final String EXAMPLE_END_SAVESEQUENCE = "</Example>";
	/**
	 * The sequence seperating multiple examples in the saveable String format
	 * of this class
	 */
	public static final String EXAMPLE_SEPERATOR_SAVESEQUENCE = "</NextExample>";
	/**
	 * The sequence indicating the start of the locality attribute in the
	 * saveable String format of this class
	 */
	public static final String LOCALITY_START_SAVESEQUENCE = "<Locality>";
	/**
	 * The sequence indicating the end of the locality attribute in the saveable
	 * String format of this class
	 */
	public static final String LOCALITY_END_SAVESEQUENCE = "</Locality>";
	/**
	 * The seperator used to seperate the effect's locality from the argument's
	 * locality
	 */
	public static final String LOCALITY_SEPERATOR_SAVESEQUENCE = "/";
	/**
	 * The sequence indicating the start of the note attribute in the saveable
	 * String format of this class
	 */
	public static final String NOTE_START_SAVESEQUENCE = "<Note>";
	/**
	 * The sequence indicating the end of the locality attribute in the saveable
	 * String format of this class
	 */
	public static final String NOTE_END_SAVESEQUENCE = "</Note>";
	/**
	 * The sequence seperating multiple notes in the saveable String format of
	 * this class
	 */
	public static final String NOTE_SEPERATOR_SAVESEQUENCE = "</NextNote>";
	/**
	 * The sequence indicating the start of the returnValue attribute in the
	 * saveable String format of this class
	 */
	public static final String RETURNVALUE_START_SAVESEQUENCE = "<ReturnValue>";
	/**
	 * The sequence indicating the end of the returnValue attribute in the
	 * saveable String format of this class
	 */
	public static final String RETURNVALUE_END_SAVESEQUENCE = "</ReturnValue>";
	
	/**
	 * The syntaxes of this command
	 */
	private ArrayList<Syntax> syntaxes;
	/**
	 * The syntaxes of this command in their raw form without having been
	 * processed with their parameters
	 */
	private ArrayList<String> rawSytaxes;
	
	/**
	 * An example of the usage of this command
	 */
	private ArrayList<String> examples;
	
	/**
	 * An array containing the locality of this command's arguments (index 0)
	 * and the locality of it's effect (index 1)
	 */
	private ELocality[] locality;
	
	/**
	 * The notes attached to this command
	 */
	private ArrayList<String> notes;
	
	/**
	 * A map that maps a return value to each syntax
	 */
	private Map<Syntax, DataTypeList> returnValues;
	
	/**
	 * Creates an instance of a SQF command
	 */
	public SQFCommand() {
		this("", null);
	}
	
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
		
		locality = new ELocality[2];
		locality[0] = ELocality.UNDEFINED;
		locality[1] = ELocality.UNDEFINED;
		
		examples = new ArrayList<String>(0);
		syntaxes = new ArrayList<Syntax>(1);
		notes = new ArrayList<String>(0);
		rawSytaxes = new ArrayList<String>(1);
		
		returnValues = new HashMap<Syntax, DataTypeList>();
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
	 * Gets the syntaxes of this command as a String representation.
	 * 
	 * @return The syntaxes of this command
	 */
	public ArrayList<String> getStringSyntaxes() {
		ArrayList<String> syntaxes = new ArrayList<String>();
		
		for (Syntax currentSyntax : getSyntaxes()) {
			syntaxes.add(currentSyntax.toString());
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
	 * Checks if this command has a stored syntax
	 */
	public boolean hasSyntax() {
		return !getSyntaxes().isEmpty();
	}
	
	/**
	 * Gets the examples associated with this command
	 * 
	 * @return A list containing the examples (empty list if there are no
	 *         examples)
	 */
	public ArrayList<String> getExamples() {
		if (examples == null) {
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
		example = example.trim();
		
		Assert.isTrue(example != null && !example.isEmpty());
		
		String codeOpener = SQDev.CODE.getOpener();
		String codeCloser = SQDev.CODE.getCloser();
		
		if (!example.contains(codeOpener) && !example.contains(codeCloser)) {
			// surround with Code tags
			example = codeOpener + example.trim() + codeCloser;
		}
		
		String taggedExample = "";
		
		for (String current : example.split(Pattern.quote(codeOpener))) {
			// put the example part that is not yet in code bracket into it as
			// a comment
			
			if (current.isEmpty()) {
				// don't process empty parts
				continue;
			}
			
			// get the part that might has to be put into a comment
			String comment = "\n// ";
			if (current.contains(codeCloser)) {
				comment += current
						.substring(current.indexOf(codeCloser)
								+ codeCloser.length())
						.trim().replace("\n", "\n//");
			} else {
				comment += current.trim().replace("\n", "\n//") + "\n";
				
				current = codeCloser;
			}
			
			// reassemble the example so that everything is contained in code
			// tags
			taggedExample += current.substring(
					(current.startsWith(codeOpener)) ? codeOpener.length() : 0,
					current.indexOf(codeCloser))
					+ ((comment.equals("\n// ")) ? "" : comment);
		}
		
		taggedExample = taggedExample.trim();
		taggedExample = taggedExample.replace("\n ", "\n");
		
		// make sure the example is surrounded by code tags
		taggedExample = ((taggedExample.startsWith(codeOpener)) ? ""
				: codeOpener) + taggedExample
				+ ((taggedExample.endsWith(codeCloser)) ? "" : codeCloser);
		
		// add example if it has not been added yet
		if (!getExamples().contains(taggedExample)) {
			getExamples().add(taggedExample);
		}
	}
	
	/**
	 * Checks if this command has stored examples
	 */
	public boolean hasExample() {
		return !getExamples().isEmpty();
	}
	
	/**
	 * Gets the locality of this command's arguments (index 0) and the locality
	 * of it's effect (index 1)
	 * 
	 * @return The array containg the localities
	 */
	public ELocality[] getLocality() {
		return locality;
	}
	
	/**
	 * Sets the localities of this command
	 * 
	 * @param locality
	 */
	protected void setLocality(ELocality[] locality) {
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
		ELocality[] loc = getLocality();
		loc[0] = locality;
		
		setLocality(loc);
	}
	
	/**
	 * Gets this command's argument's locality
	 */
	public ELocality getArgumentLocality() {
		return getLocality()[0];
	}
	
	/**
	 * Checks whether this command has a defined argument locality
	 * 
	 * @return <code>True</code> if an argument locality is defined
	 */
	public boolean isArgumentLocalityDefined() {
		return !getArgumentLocality().equals(ELocality.UNDEFINED);
	}
	
	/**
	 * Sets the locality of the command's effect
	 * 
	 * @param locality
	 *            The locality of the effect
	 */
	public void setEffectLocality(ELocality locality) {
		ELocality[] loc = getLocality();
		loc[1] = locality;
		
		setLocality(loc);
	}
	
	/**
	 * Gets this command's effect's locality
	 */
	public ELocality getEffectLocality() {
		return getLocality()[1];
	}
	
	/**
	 * Checks whether this command has a defined effect locality
	 * 
	 * @return <code>True</code> if an effect locality is defined
	 */
	public boolean isEffectLocalityDefined() {
		return !getEffectLocality().equals(ELocality.UNDEFINED);
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
		note = note.trim();
		
		Assert.isTrue(note != null && !note.isEmpty());
		
		if (!getNotes().contains(note)) {
			getNotes().add(note);
		}
	}
	
	/**
	 * Checks if this command has notes attached to it
	 */
	public boolean hasNote() {
		return !getNotes().isEmpty();
	}
	
	/**
	 * Gets the return types of this command when used in the given Syntax
	 * 
	 * @return The respective <code>DataTypeList</code> or <code>null</code> if
	 *         no return value for the given syntax could be found
	 */
	public DataTypeList getReturnTypes(Syntax syntax) {
		DataTypeList types = returnValues.get(syntax);
		
		// return copy of list in order to prevent accidental modifactions of
		// the original list
		return (types == null) ? types : new DataTypeList(types);
	}
	
	/**
	 * Gets the list of all possible return values of this command
	 */
	public DataTypeList getAllReturnTypes() {
		synchronized (returnValues) {
			DataTypeList types = new DataTypeList();
			
			Iterator<Entry<Syntax, DataTypeList>> it = returnValues.entrySet()
					.iterator();
			
			while (it.hasNext()) {
				types.addAllUnique(it.next().getValue());
			}
			
			return types;
		}
	}
	
	/**
	 * Sets the return values for when this command is used in the given syntax.
	 * Different return values have to be seperated with type seperator
	 * specified in {@link EDataType}
	 * 
	 * @param syntax
	 *            The syntax this return value applies to
	 * @param types
	 *            The return value
	 */
	public void setReturnType(Syntax syntax, String types) {
		if (types == null || types.trim().isEmpty()) {
			setReturnType(syntax, new DataTypeList(EDataType.NOTHING));
			return;
		}
		
		DataTypeList typeList = DataTypeList.fillWith(types);
		
		setReturnType(syntax, typeList);
	}
	
	/**
	 * Sets the return value for when this command is used in the given syntax
	 * 
	 * @param syntax
	 *            The syntax this return value applies to
	 * @param types
	 *            The corresponding return values. If it is <code>null</code> or
	 *            empty a new list will be created that only contains the
	 *            NOTHING type
	 */
	public void setReturnType(Syntax syntax, DataTypeList types) {
		Assert.isNotNull(syntax);
		
		if (types == null || types.isEmpty()) {
			types = new DataTypeList(EDataType.NOTHING);
		}
		
		// Use a copy of the list in order to prevent accidentally modifications
		returnValues.put(syntax, new DataTypeList(types));
	}
	
	/**
	 * Checks whether this command has a return type
	 */
	public boolean hasReturnValue() {
		return !getAllReturnTypes().isEmpty();
	}
	
	/**
	 * Gets the raw syntaxes of this command. These are for display purposes
	 * only as they have not been processed with their parameters
	 */
	public ArrayList<String> getRawSytaxes() {
		return rawSytaxes;
	}
	
	/**
	 * Sets the raw syntaxes of this command
	 * 
	 * @param rawSytaxes
	 *            The raw (unprocessed) syntaxes for this command that can be
	 *            used for display purposes
	 */
	public void setRawSytaxes(ArrayList<String> rawSytaxes) {
		Assert.isNotNull(rawSytaxes);
		
		this.rawSytaxes = rawSytaxes;
	}
	
	/**
	 * Adds a raw syntax to this command
	 * 
	 * @param rawSyntax
	 *            The raw syntax to add
	 */
	public void addRawSyntax(String rawSyntax) {
		rawSyntax = rawSyntax.trim();
		
		Assert.isTrue(rawSyntax != null && !rawSyntax.isEmpty());
		
		getRawSytaxes().add(rawSyntax);
	}
	
	/**
	 * Checks if this command has raw syntaxes attached to it
	 */
	public boolean hasRawSyntax() {
		return !getRawSytaxes().isEmpty();
	}
	
	/**
	 * Checks whether this command can be used as a binary operator
	 */
	public boolean isBinaryOperator() {
		for (Syntax currentSyntax : getSyntaxes()) {
			if (currentSyntax.isBinary()) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Checks whether this command can be used as a unary operator
	 */
	public boolean isUnaryOperator() {
		for (Syntax currentSyntax : getSyntaxes()) {
			if (currentSyntax.isUnary()) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Checks whether this command can be used as a nular operator
	 */
	public boolean isNularOperator() {
		for (Syntax currentSyntax : getSyntaxes()) {
			if (currentSyntax.isNular()) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		String representation = "";
		
		representation += "Name: " + getKeyword() + "\n";
		representation += "hasDescription: " + hasDescription() + "\n";
		representation += "hasSyntax: " + hasSyntax() + "\n";
		representation += "hasRawSyntax: " + hasRawSyntax() + "\n";
		representation += "hasExamples: " + hasExample() + "\n";
		representation += "hasNotes: " + hasNote() + "\n";
		representation += "hasReturnValue: " + hasReturnValue() + "\n";
		
		return representation;
	}
	
	@Override
	public String getSaveableFormat() {
		String format = super.getSaveableFormat() + "\n";
		
		// add syntaxes
		format += SYNTAX_START_SAVESEQUENCE + "\n\t";
		for (Syntax currentSyntax : getSyntaxes()) {
			format += currentSyntax.toString() + "\n";
			format += SYNTAX_SEPERATOR_SAVESEQUENCE + "\n\t";
		}
		if (getSyntaxes().size() > 0) {
			// remove last seperator
			format = format.substring(0, format.length()
					- (SYNTAX_SEPERATOR_SAVESEQUENCE.length() + 2));
		}
		format += SYNTAX_END_SAVESEQUENCE + "\n";
		
		// add rawSyntaxes
		format += RAWSYNTAX_START_SAVESEQUENCE + "\n\t";
		for (String currentSyntax : getRawSytaxes()) {
			format += currentSyntax + "\n";
			format += RAWSYNTAX_SEPERATOR_SAVESEQUENCE + "\n\t";
		}
		if (getRawSytaxes().size() > 0) {
			// remove last seperator
			format = format.substring(0, format.length()
					- (RAWSYNTAX_SEPERATOR_SAVESEQUENCE.length() + 2));
		}
		format += RAWSYNTAX_END_SAVESEQUENCE + "\n";
		
		// add examples
		format += EXAMPLE_START_SAVESEQUENCE + "\n\t";
		for (String currentExample : getExamples()) {
			format += currentExample + "\n";
			format += EXAMPLE_SEPERATOR_SAVESEQUENCE + "\n\t";
		}
		if (getExamples().size() > 0) {
			// remove last seperator
			format = format.substring(0, format.length()
					- (EXAMPLE_SEPERATOR_SAVESEQUENCE.length() + 2));
		}
		format += EXAMPLE_END_SAVESEQUENCE + "\n";
		
		// add locality
		format += LOCALITY_START_SAVESEQUENCE + "\n\t";
		format += getLocality()[0].toString() + " "
				+ LOCALITY_SEPERATOR_SAVESEQUENCE + " "
				+ getLocality()[1].toString() + "\n";
		format += LOCALITY_END_SAVESEQUENCE + "\n";
		
		// add notes
		format += NOTE_START_SAVESEQUENCE + "\n\t";
		for (String currentNote : getNotes()) {
			format += currentNote + "\n";
			format += NOTE_SEPERATOR_SAVESEQUENCE + "\n\t";
		}
		if (getNotes().size() > 0) {
			// remove last seperator and unnecessary whitespace
			format = format.substring(0,
					format.length()
							- (NOTE_SEPERATOR_SAVESEQUENCE.length() + 2))
					+ "\t";
		}
		
		// remove last tab
		format = format.substring(0, format.length() - 1);
		
		format += NOTE_END_SAVESEQUENCE + "\n";
		
		// add returnTypes
		format += RETURNVALUE_START_SAVESEQUENCE + "\n\t";
		// make sure the return values are listed in the same order as
		for (Syntax currentSyntax : getSyntaxes()) {
			DataTypeList types = getReturnTypes(currentSyntax);
			
			if (types == null) {
				try {
					throw new SQDevException(
							"Unable to retrieve return values for syntax \""
									+ currentSyntax + "\"");
				} catch (SQDevException e) {
					// TODO log
					e.printStackTrace();
				}
				
				// fallback solution: pretend return value is nothing
				types = new DataTypeList(EDataType.NOTHING);
			}
			
			format += types + RETURN_VALUE_SYNTAX_SEPERATOR;
		}
		
		if (getSyntaxes().size() > 0) {
			// remove last seperator
			format = format.substring(0, format.length() - RETURN_VALUE_SYNTAX_SEPERATOR.length());
		}
		
		format += "\n" + RETURNVALUE_END_SAVESEQUENCE;
		
		return format;
	}
	
	@Override
	public boolean recreateFrom(String savedFormat) throws BadSyntaxException {
		if (!super.recreateFrom(savedFormat) || !isSaveFormat(savedFormat)) {
			return false;
		}
		
		// syntax
		String syntaxContent = savedFormat.substring(
				savedFormat.indexOf(SYNTAX_START_SAVESEQUENCE)
						+ SYNTAX_START_SAVESEQUENCE.length(),
				savedFormat.indexOf(SYNTAX_END_SAVESEQUENCE)).trim();
		
		for (String currentSyntax : syntaxContent
				.split(SYNTAX_SEPERATOR_SAVESEQUENCE)) {
			// process each syntax
			currentSyntax = currentSyntax.trim();
			
			if (!currentSyntax.isEmpty()) {
				addSyntax(Syntax.parseSyntax(currentSyntax, getKeyword()));
			}
		}
		
		// rawSyntax
		String rawSyntaxContent = savedFormat.substring(
				savedFormat.indexOf(RAWSYNTAX_START_SAVESEQUENCE)
						+ RAWSYNTAX_START_SAVESEQUENCE.length(),
				savedFormat.indexOf(RAWSYNTAX_END_SAVESEQUENCE)).trim();
		
		for (String currentRawSyntax : rawSyntaxContent
				.split(RAWSYNTAX_SEPERATOR_SAVESEQUENCE)) {
			// process each rawSyntax
			currentRawSyntax = currentRawSyntax.trim();
			
			if (!currentRawSyntax.isEmpty()) {
				addRawSyntax(currentRawSyntax);
			}
		}
		
		// examples
		String exampleContent = savedFormat.substring(
				savedFormat.indexOf(EXAMPLE_START_SAVESEQUENCE)
						+ EXAMPLE_START_SAVESEQUENCE.length(),
				savedFormat.indexOf(EXAMPLE_END_SAVESEQUENCE)).trim();
		
		for (String currentExample : exampleContent
				.split(EXAMPLE_SEPERATOR_SAVESEQUENCE)) {
			// process each example
			currentExample = currentExample.trim();
			
			if (!currentExample.isEmpty()) {
				addExample(currentExample);
			}
		}
		
		// locality
		String localityContent = savedFormat.substring(
				savedFormat.indexOf(LOCALITY_START_SAVESEQUENCE)
						+ LOCALITY_START_SAVESEQUENCE.length(),
				savedFormat.indexOf(LOCALITY_END_SAVESEQUENCE)).trim();
		
		String argumentLocality = localityContent
				.substring(0,
						localityContent
								.indexOf(LOCALITY_SEPERATOR_SAVESEQUENCE))
				.trim();
		String effectsLocality = localityContent
				.substring(
						localityContent.indexOf(LOCALITY_SEPERATOR_SAVESEQUENCE)
								+ LOCALITY_SEPERATOR_SAVESEQUENCE.length())
				.trim();
		
		if (ELocality.resolve(argumentLocality) == null
				|| ELocality.resolve(effectsLocality) == null) {
			return false;
		}
		setArgumentLocality(ELocality.resolve(argumentLocality));
		setEffectLocality(ELocality.resolve(effectsLocality));
		
		// notes
		String noteContent = savedFormat.substring(
				savedFormat.indexOf(NOTE_START_SAVESEQUENCE)
						+ NOTE_START_SAVESEQUENCE.length(),
				savedFormat.indexOf(NOTE_END_SAVESEQUENCE)).trim();
		
		for (String currentNote : noteContent
				.split(NOTE_SEPERATOR_SAVESEQUENCE)) {
			// process each note
			currentNote = currentNote.trim();
			
			if (!currentNote.isEmpty()) {
				addNote(currentNote);
			}
		}
		
		// return type
		String returnTypes = savedFormat
				.substring(
						savedFormat.indexOf(RETURNVALUE_START_SAVESEQUENCE)
								+ RETURNVALUE_START_SAVESEQUENCE.length(),
						savedFormat.indexOf(RETURNVALUE_END_SAVESEQUENCE))
				.trim();
		
		// There is a return value entry for each syntax
		List<Syntax> syntaxes = getSyntaxes();
		String[] returnValues = returnTypes
				.split(RETURN_VALUE_SYNTAX_SEPERATOR);
		
		if (syntaxes.size() != returnValues.length) {
			try {
				throw new SQDevException(
						"savable format has to contain as many return values as syntaxes");
			} catch (SQDevException e) {
				// TODO: log
				e.printStackTrace();
			}
			
			return false;
		}
		
		// map the return values to the respective syntax
		for (int i = 0; i < returnValues.length; i++) {
			setReturnType(syntaxes.get(i),
					DataTypeList.fillWith(returnValues[i]));
		}
		
		return true;
	}
	
	@Override
	public boolean isSaveFormat(String format) {
		if (!super.isSaveFormat(format)) {
			return false;
		}
		
		if (!format.contains(SYNTAX_START_SAVESEQUENCE)
				|| !format.contains(SYNTAX_END_SAVESEQUENCE)
				|| !format.contains(RAWSYNTAX_START_SAVESEQUENCE)
				|| !format.contains(RAWSYNTAX_END_SAVESEQUENCE)
				|| !format.contains(EXAMPLE_START_SAVESEQUENCE)
				|| !format.contains(EXAMPLE_END_SAVESEQUENCE)
				|| !format.contains(LOCALITY_START_SAVESEQUENCE)
				|| !format.contains(LOCALITY_END_SAVESEQUENCE)
				|| !format.contains(LOCALITY_SEPERATOR_SAVESEQUENCE)
				|| !format.contains(NOTE_START_SAVESEQUENCE)
				|| !format.contains(NOTE_END_SAVESEQUENCE)
				|| !format.contains(RETURNVALUE_START_SAVESEQUENCE)
				|| !format.contains(RETURNVALUE_END_SAVESEQUENCE)) {
			// an info is not properly given
			return false;
		}
		
		int syntaxStart = format.indexOf(SYNTAX_START_SAVESEQUENCE);
		int syntaxEnd = format.indexOf(SYNTAX_END_SAVESEQUENCE);
		int rawSyntaxStart = format.indexOf(RAWSYNTAX_START_SAVESEQUENCE);
		int rawSyntaxEnd = format.indexOf(RAWSYNTAX_END_SAVESEQUENCE);
		int exampleStart = format.indexOf(EXAMPLE_START_SAVESEQUENCE);
		int exampleEnd = format.indexOf(EXAMPLE_END_SAVESEQUENCE);
		int localityStart = format.indexOf(LOCALITY_START_SAVESEQUENCE);
		int localityEnd = format.indexOf(LOCALITY_END_SAVESEQUENCE);
		int noteStart = format.indexOf(NOTE_START_SAVESEQUENCE);
		int noteEnd = format.indexOf(NOTE_END_SAVESEQUENCE);
		int returnStart = format.indexOf(RETURNVALUE_START_SAVESEQUENCE);
		int returnEnd = format.indexOf(RETURNVALUE_END_SAVESEQUENCE);
		
		if (syntaxEnd < syntaxStart || rawSyntaxEnd < rawSyntaxStart
				|| exampleEnd < exampleStart || localityEnd < localityStart
				|| noteEnd < noteStart || returnEnd < returnStart) {
			return false;
		}
		
		return true;
	}
}
