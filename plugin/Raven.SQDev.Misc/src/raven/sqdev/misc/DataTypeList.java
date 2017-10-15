package raven.sqdev.misc;

import java.util.ArrayList;
import java.util.Collection;

import raven.sqdev.exceptions.SQDevException;

/**
 * This class describes a list that is used for <code>EDataTypes</code>. It
 * contains some utility functioniality for that case
 * 
 * @author Raven
 *
 */
public class DataTypeList extends ArrayList<EDataType> {
	
	private static final long serialVersionUID = -3449702221130512936L;
	
	/**
	 * The seperator used to seperate different data types
	 */
	public static final String TYPE_SEPERATOR = "/";
	
	
	/**
	 * Creates a new DataTypeList
	 */
	public DataTypeList() {
		super();
	}
	
	/**
	 * Creates a new DataTypeList with the given entry
	 * 
	 * @param type
	 *            The entry to directly add
	 */
	public DataTypeList(EDataType type) {
		super();
		
		add(type);
	}
	
	/**
	 * Creates a new DataTypeList with the given set of entries
	 * 
	 * @param types
	 *            The entries to directly add
	 */
	public DataTypeList(Collection<EDataType> types) {
		super(types);
	}
	
	/**
	 * Checks whether this list contains an exchangable data type for the given
	 * one.
	 * 
	 * @param type
	 *            The type that should be checked
	 * @param reverseChecking
	 *            Indicates whether reverse checking should be enabled as well.
	 *            That means that the respective list entry is checked whether
	 *            it can be replaced by the given type instead of only the other
	 *            way round
	 */
	public boolean containsExchangableType(EDataType type,
			boolean reverseChecking) {
		return findExchangableDataType(type, reverseChecking) != -1;
	}
	
	/**
	 * Finds the index of a data type that is exchangable with the given data
	 * type.
	 * 
	 * @param type
	 *            The data type to search for
	 * @param reverseChecking
	 *            Indicates whether reverse checking should be enabled as well.
	 *            That means that the respective list entry is checked whether
	 *            it can be replaced by the given type instead of only the other
	 *            way round
	 * @return The index of the found entry or <code>-1</code> if none could be
	 *         found
	 */
	public int findExchangableDataType(EDataType type,
			boolean reverseChecking) {
		return findExchangableDataType(type, reverseChecking, 0);
	}
	
	/**
	 * Finds the index of a data type that is exchangable with the given data
	 * type.
	 * 
	 * @param type
	 *            The data type to search for
	 * @param reverseChecking
	 *            Indicates whether reverse checking should be enabled as well.
	 *            That means that the respective list entry is checked whether
	 *            it can be replaced by the given type instead of only the other
	 *            way round
	 * @param startIndex
	 *            Defines the index of the element the search should be started
	 *            at
	 * @return The index of the found entry or <code>-1</code> if none could be
	 *         found
	 */
	public int findExchangableDataType(EDataType type, boolean reverseChecking,
			int startIndex) {
		for (int i = startIndex; i < size(); i++) {
			EDataType currentType = get(i);
			
			if (currentType == type) {
				// a data type is exchangable with itself
				return i;
			}
			if (currentType == null || type == null) {
				continue;
			}
			
			if (type.canBeUsedAs(currentType)) {
				return i;
			}
			if (reverseChecking && currentType.canBeUsedAs(type)) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * Adds the given data type if it is not yert contained in the list
	 * 
	 * @param type
	 *            The data type to add
	 */
	public void addUnique(EDataType type) {
		if (!contains(type)) {
			add(type);
		}
	}
	
	/**
	 * Adds the entries of the given collection if they are not yet contained in
	 * this list
	 * 
	 * @param collection
	 *            The collection of data types to add
	 */
	public void addAllUnique(Collection<EDataType> collection) {
		for (EDataType currentType : collection) {
			addUnique(currentType);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for (EDataType currentType : this) {
			builder.append(currentType + TYPE_SEPERATOR);
		}
		
		if (size() > 0) {
			// remove last seperator
			builder.setLength(builder.length() - TYPE_SEPERATOR.length());
		}
		
		return builder.toString();
	}
	
	/**
	 * Creates a new {@link DataTypeList} and tries to fill it with the given
	 * types
	 * 
	 * @param types
	 *            The different data types to store in the list. Each datatype
	 *            has to be seperated by {@link #TYPE_SEPERATOR}
	 * @return The created and populated list
	 */
	public static DataTypeList fillWith(String types) {
		DataTypeList list = new DataTypeList();
		
		// extract all types out of this and add them to the list
		for (String currentStringType : types.split(TYPE_SEPERATOR)) {
			EDataType currentType = EDataType.resolve(currentStringType);
			
			if (currentType == null) {
				try {
					throw new SQDevException(
							"Couldn't resolve type \"" + currentStringType + "\"");
				} catch (SQDevException e) {
					e.printStackTrace();
					
					// TODO: log
				}
			} else {
				list.add(currentType);
			}
		}
		
		return list;
	}
	
	/**
	 * Converts this list into a respective array
	 */
	public EDataType[] toArray() {
		EDataType[] types = new EDataType[size()];
		
		for (int i = 0; i < types.length; i++) {
			types[i] = get(i);
		}
		
		return types;
	}
}
