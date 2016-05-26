package raven.sqdev.misc;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;

/**
 * A preferenceStore that allows to combine multiple preferenceStores
 * 
 * @author Raven
 *
 */
public class MultiPreferenceStore extends PreferenceStore {
	
	/**
	 * A list of all preferenceStores that should be combined
	 */
	List<IPreferenceStore> stores;
	
	/**
	 * Creates an instance of this preferenceStore
	 * 
	 * @param store
	 *            The base store to start with
	 */
	public MultiPreferenceStore(IPreferenceStore store) {
		Assert.isNotNull(store);
		
		stores = new ArrayList<IPreferenceStore>(2);
		
		stores.add(store);
	}
	
	@Override
	public void addPropertyChangeListener(IPropertyChangeListener listener) {
		for (IPreferenceStore currentStore : stores) {
			currentStore.addPropertyChangeListener(listener);
		}
	}
	
	@Override
	public boolean contains(String name) {
		for (IPreferenceStore currentStore : stores) {
			if (currentStore.contains(name)) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void firePropertyChangeEvent(String name, Object oldValue, Object newValue) {
		for (IPreferenceStore currentStore : stores) {
			currentStore.firePropertyChangeEvent(name, oldValue, newValue);
		}
	}
	
	/**
	 * Retrieves the respective preferenceStore that contains the given
	 * preference (It will return the first store that matches a preference with
	 * the given name)
	 * 
	 * @param name
	 *            The name of the preference to search for
	 * @return The respective preferenceStore or the first one when the name
	 *         could not be matched by any of the stores
	 */
	private IPreferenceStore getStoreContaining(String name) {
		for (IPreferenceStore currentStore : stores) {
			if (currentStore.contains(name)) {
				return currentStore;
			}
		}
		
		// return the first one in order to avoid NullPointerExceptions
		return stores.get(0);
	}
	
	@Override
	public boolean getBoolean(String name) {
		return getStoreContaining(name).getBoolean(name);
	}
	
	@Override
	public boolean getDefaultBoolean(String name) {
		return getStoreContaining(name).getDefaultBoolean(name);
	}
	
	@Override
	public double getDefaultDouble(String name) {
		return getStoreContaining(name).getDefaultDouble(name);
	}
	
	@Override
	public float getDefaultFloat(String name) {
		return getStoreContaining(name).getDefaultFloat(name);
	}
	
	@Override
	public int getDefaultInt(String name) {
		return getStoreContaining(name).getDefaultInt(name);
	}
	
	@Override
	public long getDefaultLong(String name) {
		return getStoreContaining(name).getDefaultLong(name);
	}
	
	@Override
	public String getDefaultString(String name) {
		return getStoreContaining(name).getDefaultString(name);
	}
	
	@Override
	public double getDouble(String name) {
		return getStoreContaining(name).getDouble(name);
	}
	
	@Override
	public float getFloat(String name) {
		return getStoreContaining(name).getFloat(name);
	}
	
	@Override
	public int getInt(String name) {
		return getStoreContaining(name).getInt(name);
	}
	
	@Override
	public long getLong(String name) {
		return getStoreContaining(name).getLong(name);
	}
	
	@Override
	public String getString(String name) {
		return getStoreContaining(name).getString(name);
	}
	
	@Override
	public boolean isDefault(String name) {
		return getStoreContaining(name).isDefault(name);
	}
	
	@Override
	public boolean needsSaving() {
		for (IPreferenceStore currentStore : stores) {
			if (currentStore.needsSaving()) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void putValue(String name, String value) {
		// put in base store
		stores.get(0).putValue(name, value);
	}
	
	@Override
	public void removePropertyChangeListener(IPropertyChangeListener listener) {
		for (IPreferenceStore currentStore : stores) {
			currentStore.removePropertyChangeListener(listener);
		}
	}
	
	@Override
	public void setDefault(String name, double value) {
		// add to the base store
		stores.get(0).setDefault(name, value);
	}
	
	@Override
	public void setDefault(String name, float value) {
		// add to the base store
		stores.get(0).setDefault(name, value);
	}
	
	@Override
	public void setDefault(String name, int value) {
		// add to the base store
		stores.get(0).setDefault(name, value);
	}
	
	@Override
	public void setDefault(String name, long value) {
		// add to the base store
		stores.get(0).setDefault(name, value);
	}
	
	@Override
	public void setDefault(String name, String defaultObject) {
		// add to the base store
		stores.get(0).setDefault(name, defaultObject);
	}
	
	@Override
	public void setDefault(String name, boolean value) {
		// add to the base store
		stores.get(0).setDefault(name, value);
	}
	
	@Override
	public void setToDefault(String name) {
		getStoreContaining(name).setToDefault(name);
	}
	
	@Override
	public void setValue(String name, double value) {
		// add to the base store
		stores.get(0).setValue(name, value);
	}
	
	@Override
	public void setValue(String name, float value) {
		// add to the base store
		stores.get(0).setValue(name, value);
	}
	
	@Override
	public void setValue(String name, int value) {
		// add to the base store
		stores.get(0).setValue(name, value);
	}
	
	@Override
	public void setValue(String name, long value) {
		// add to the base store
		stores.get(0).setValue(name, value);
	}
	
	@Override
	public void setValue(String name, String value) {
		// add to the base store
		stores.get(0).setValue(name, value);
	}
	
	@Override
	public void setValue(String name, boolean value) {
		// add to the base store
		stores.get(0).setValue(name, value);
	}
	
	@Override
	public void save() throws IOException {
		super.save();
		
		for (IPreferenceStore currentStore : stores) {
			if (currentStore instanceof PreferenceStore) {
				((PreferenceStore) currentStore).save();
			} else {
				try {
					// see if there is a save method and if yes try to invoke it
					Method saveMethod = currentStore.getClass().getMethod("save",
							(Class<?>[]) null);
					
					saveMethod.invoke(currentStore, (Object[]) null);
				} catch (NoSuchMethodException | SecurityException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e) {
					// can't save
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Adds the given <code>IPreferenceStore</code> to this store if it is not
	 * already contained
	 * 
	 * @param store
	 *            The store to add
	 */
	public void addPreferenceStore(IPreferenceStore store) {
		if (!stores.contains(store)) {
			stores.add(store);
		}
	}
	
	/**
	 * Removes the given <code>IPreferenceStore</code> from this store
	 * 
	 * @param store
	 *            The store to remove
	 */
	public void removePreferenceStore(IPreferenceStore store) {
		stores.remove(store);
	}
}
