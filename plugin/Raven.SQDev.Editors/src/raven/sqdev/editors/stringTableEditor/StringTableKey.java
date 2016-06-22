package raven.sqdev.editors.stringTableEditor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.eclipse.core.runtime.Assert;

import raven.sqdev.misc.UpdateReporter;

public class StringTableKey extends UpdateReporter implements Cloneable {
	
	/**
	 * The respective key
	 */
	private String key;
	
	private HashMap<Language, String> localizedStrings;
	
	public StringTableKey(String key) {
		super(false);
		
		Assert.isTrue(key != null && !key.isEmpty(), "Invalid key (null or empty)");
		
		this.key = key;
		
		localizedStrings = new HashMap<Language, String>();
	}
	
	/**
	 * Gets the String for the given language
	 * 
	 * @param language
	 *            The <code>Language</code> the String should be returned for
	 * @return The respective String or an empty String if none could be found
	 */
	public String getString(Language language) {
		return (localizedStrings.containsKey(language)) ? localizedStrings.get(language) : "";
	}
	
	/**
	 * Sets the String for the given language
	 * 
	 * @param language
	 *            The <code>Language</code> the string should be associated with
	 * @param string
	 *            The string to store
	 */
	public void setString(Language language, String string) {
		Assert.isNotNull(language);
		Assert.isNotNull(string);
		
		localizedStrings.put(language, string);
		
		notifyUpdateListener();
	}
	
	/**
	 * Checks whether a string for the given language is stored
	 * 
	 * @param language
	 *            The <code>Language</code> to search for
	 */
	public boolean containsString(Language language) {
		return localizedStrings.containsKey(language);
	}
	
	/**
	 * @return The key represneted by this object
	 */
	public String getKey() {
		return key;
	}
	
	@Override
	public String toString() {
		return "Key: " + getKey();
	}
	
	/**
	 * Sets the key for this object
	 * 
	 * @param value
	 *            The new key
	 */
	public void setKey(String value) {
		this.key = value;
		
		notifyUpdateListener();
	}
	
	/**
	 * Removes the given language
	 * 
	 * @param language
	 *            The <code>Lamguage</code> to remove
	 */
	public void remove(Language language) {
		int size = localizedStrings.size();
		
		localizedStrings.remove(language);
		
		if (size != localizedStrings.size()) {
			// only if something has changed
			notifyUpdateListener();
		}
	}
	
	/**
	 * Gets the <code>Languages</code> this key is configured for
	 */
	public Language[] getLanguages() {
		return localizedStrings.keySet().toArray(new Language[localizedStrings.keySet().size()]);
	}
	
	/**
	 * Gets the XML representation for this key
	 */
	public String getXMLRepresentation() {
		StringBuilder builder = new StringBuilder("<Key ID=\"" + getKey() + "\">\n");
		
		Iterator<Language> it = localizedStrings.keySet().iterator();
		
		while (it.hasNext()) {
			Language currentLanguage = it.next();
			
			String str = localizedStrings.get(currentLanguage);
			
			// escape special characters
			str = str.replace("'", "&#" + (int) '\'' + ";");
			str = str.replace("\"", "&#" + (int) '"' + ";");
			str = str.replace("<", "&#" + (int) '<' + ";");
			str = str.replace(">", "&#" + (int) '>' + ";");
			
			builder.append("<" + currentLanguage.toString() + ">");
			builder.append(str);
			builder.append("</" + currentLanguage.toString() + ">\n");
		}
		
		String representation = builder.toString().trim().replace("\n", "\n\t");
		
		return representation + "\n</Key>";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!this.getClass().equals(obj.getClass())) {
			return false;
		}
		
		StringTableKey comparer = (StringTableKey) obj;
		
		return getXMLRepresentation().equals(comparer.getXMLRepresentation());
	}
	
	@Override
	public StringTableKey clone() {
		StringTableKey key = new StringTableKey(getKey());
		
		Iterator<Entry<Language, String>> it = localizedStrings.entrySet().iterator();
		
		while (it.hasNext()) {
			Entry<Language, String> entry = it.next();
			
			key.setString(entry.getKey(), entry.getValue());
		}
		
		return key;
	}
}
