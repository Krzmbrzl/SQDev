package raven.sqdev.editors.stringTableEditor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.Assert;

import raven.sqdev.misc.UpdateReporter;

/**
 * A container for multiple <code>StringTableContainer</code>
 * 
 * @author Raven
 *
 */
public class StringTablePackage extends UpdateReporter {
	
	/**
	 * The name of this package
	 */
	private String name;
	/**
	 * A list of containers contained in this package
	 */
	private List<StringTableContainer> containerList;
	
	
	/**
	 * Creates a new package with the given name
	 * 
	 * @param name
	 *            The name of the new package
	 */
	public StringTablePackage(String name) {
		super(false);
		
		Assert.isTrue(name != null && !name.isEmpty());
		
		this.name = name;
		containerList = new ArrayList<StringTableContainer>();
	}
	
	/**
	 * Gets the name of this package
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of this package
	 * 
	 * @param name
	 *            The new name for this package
	 */
	public void setName(String name) {
		Assert.isTrue(name != null && !name.isEmpty());
		
		this.name = name;
		
		notifyUpdateListener();
	}
	
	/**
	 * Adds the given container to this package if it is not already added
	 * 
	 * @param container
	 *            The <code>StringTableContainer</code> to add
	 */
	public void addContainer(StringTableContainer container) {
		if (!containerList.contains(container)) {
			containerList.add(container);
			
			notifyUpdateListener();
		}
	}
	
	/**
	 * Adds all container from the given Collection
	 * 
	 * @param containers
	 *            The <code>Collection</code> with the
	 *            <code>StringTableContainer</code> to add
	 */
	public void addContainers(Collection<StringTableContainer> containers) {
		containerList.addAll(containers);
		
		notifyUpdateListener();
	}
	
	/**
	 * Removes the container to remove
	 * 
	 * @param container
	 *            The <code>StringTableContainer</code> to remove
	 */
	public void removeContainer(StringTableContainer container) {
		containerList.remove(container);
		
		notifyUpdateListener();
	}
	
	/**
	 * Removes all containers of the given Collection
	 * 
	 * @param containers
	 *            The <code>Collection</code> of
	 *            <code>StringTableContainer</code> to remove
	 */
	public void removeContainer(Collection<StringTableContainer> containers) {
		containerList.removeAll(containers);
		
		notifyUpdateListener();
	}
	
	/**
	 * Checks whether the given container is contained in this package
	 * 
	 * @param container
	 *            The <code>StringTableContainer</code> to search for
	 */
	public boolean contains(StringTableContainer container) {
		return containerList.contains(container);
	}
	
	/**
	 * Gets the list of <code>StringTableContainer</code> that are contained in
	 * this package
	 */
	public List<StringTableContainer> getContainer() {
		return containerList;
	}
	
	/**
	 * Fets the XML representation for this package
	 */
	public String getXMLRepresentation() {
		StringBuilder builder = new StringBuilder("<Package name=\"" + getName() + "\">\n");
		
		for (StringTableContainer currentContainer : containerList) {
			builder.append(currentContainer.getXMLRepresentation() + "\n");
		}
		
		// add indent
		String representation = builder.toString().trim().replace("\n", "\n\t");
		
		return representation + "\n</Package>";
	}
	
}
