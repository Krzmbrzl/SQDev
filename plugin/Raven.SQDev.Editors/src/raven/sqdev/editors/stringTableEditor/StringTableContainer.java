package raven.sqdev.editors.stringTableEditor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.Assert;

import raven.sqdev.misc.UpdateReporter;

/**
 * A container for multiple <code>StringTableKeys</code>
 * 
 * @author Raven
 *
 */
public class StringTableContainer extends UpdateReporter {
	
	/**
	 * A list of <code>StringTableKeys</code> contained in this container
	 */
	private List<StringTableKey> keyList;
	
	private String name;
	
	/**
	 * Creates a new container with the given name
	 * 
	 * @param name
	 *            The name of this container
	 */
	public StringTableContainer(String name) {
		super(false);
		
		Assert.isTrue(name != null && !name.isEmpty());
		
		this.name = name;
		keyList = new ArrayList<StringTableKey>();
	}
	
	/**
	 * Adds a <code>StringTableKey</code> to this container if it is not already
	 * contained
	 * 
	 * @param key
	 *            The key to add
	 */
	public void addKey(StringTableKey key) {
		if (!keyList.contains(key)) {
			keyList.add(key);
			
			notifyUpdateListener();
		}
	}
	
	/**
	 * Adds all <code>StringTableKeys</code> of the given Collection
	 * 
	 * @param keys
	 *            The Collection of keys to add
	 */
	public void addKeys(Collection<StringTableKey> keys) {
		keyList.addAll(keys);
		
		notifyUpdateListener();
	}
	
	/**
	 * Removes the given <code>StringTableKey</code>
	 * 
	 * @param key
	 *            The key to remove
	 */
	public void removeKey(StringTableKey key) {
		int size = keyList.size();
		
		keyList.remove(key);
		
		if (size != keyList.size()) {
			// only if something has changed
			notifyUpdateListener();
		}
	}
	
	/**
	 * Removes all keys of the given Collection
	 * 
	 * @param keys
	 *            The Collection of keys to remove
	 */
	public void removeKeys(Collection<StringTableKey> keys) {
		int size = keyList.size();
		
		keyList.removeAll(keys);
		
		if (size != keyList.size()) {
			// only if something has changed
			notifyUpdateListener();
		}
	}
	
	/**
	 * Checks if the given key is contained in this container
	 * 
	 * @param key
	 *            The <code>StringTableKey</code> to search for
	 */
	public boolean contains(StringTableKey key) {
		return keyList.contains(key);
	}
	
	/**
	 * Gets the list of <code>StringTableKeys</code> contained in this container
	 */
	public List<StringTableKey> getKeys() {
		return keyList;
	}
	
	/**
	 * Gets the nameof this container
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name for this container
	 * 
	 * @param name
	 *            The new name
	 */
	public void setName(String name) {
		Assert.isTrue(name != null && !name.isEmpty());
		
		this.name = name;
		
		notifyUpdateListener();
	}
	
	/**
	 * Gets the XML representation for this container
	 */
	public String getXMLRepresentation() {
		StringBuilder builder = new StringBuilder("<Container name=\"" + getName() + "\">\n");
		
		for (StringTableKey currentKey : keyList) {
			builder.append(currentKey.getXMLRepresentation() + "\n");
		}
		
		// add indent
		String representation = builder.toString().trim().replace("\n", "\n\t");
		
		return representation + "\n</Container>";
	}
}
