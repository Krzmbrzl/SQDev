package raven.sqdev.infoCollection.base;

/**
 * A <code>Keyword</code> corresponding to a variable
 * 
 * @author Raven
 *
 */
public class Variable extends Keyword {
	
	private static final long serialVersionUID = -7476785836679689767L;

	/**
	 * Creates an empty variable
	 */
	public Variable() {
		this("", null);
	}
	
	/**
	 * Creates an instance of a variable
	 * 
	 * @param variableName
	 *            The variable name
	 */
	public Variable(String variableName) {
		this(variableName, null);
	}
	
	/**
	 * Creates an instance of a variable
	 * 
	 * @param variableName
	 *            The variable name
	 * @param description
	 *            The description for this variable
	 */
	public Variable(String variableName, String description) {
		setKeyword(variableName);
		setDescription(description);
	}
	
	/**
	 * Checks whether this variable is local (starts with an underscore)
	 */
	public boolean isLocal() {
		return getKeyword().startsWith("_");
	}
	
	@Override
	public String getSaveableFormat() {
		throw new IllegalAccessError("Not yet saveable!");
	}
	
}
