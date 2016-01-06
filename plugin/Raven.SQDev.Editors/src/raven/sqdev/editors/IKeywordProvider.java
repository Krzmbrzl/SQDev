package raven.sqdev.editors;

/**
 * An interface for an keyword provider that has to declare a method for providing an array of Strings
 * @author Raven
 *
 */
public interface IKeywordProvider {
	public String[] getKeywords();
}
