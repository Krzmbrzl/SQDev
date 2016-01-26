package raven.sqdev.sqdevFile;

/**
 * An enum representing all possible names for a .sqdev file
 * 
 * @author Raven
 * 		
 */
public enum ESQDevFileType {
	/**
	 * The name of the sqdev-file that contains information about the linking to
	 * the ArmA folder
	 */
	LINK {
		@Override
		public String toString() {
			return "link";
		}

		@Override
		public String getInitialInput() {
			String input = "autoExport = false;\nexportDirectory = ";
			
			return input;
		}
	};
	
	public abstract String getInitialInput();
	
	/**
	 * Will find the respective instance of this enum
	 * 
	 * @param type
	 *            The type of the enum that should be found. Has to be the
	 *            return value of one of the toString()-methods of this enum
	 * @return The respective instance of this enum or <code>null</code> if the
	 *         type couldn't be resolved
	 */
	public static ESQDevFileType resolve(String type) {
		for (ESQDevFileType current : ESQDevFileType.values()) {
			if (current.toString().equals(type)) {
				return current;
			}
		}
		
		return null;
	}
	
	/**
	 * Checks if the given type is contained in this enum
	 * @param type The type name to check
	 * @return
	 */
	public static boolean contains(String type) {
		return (resolve(type) == null)? false : true;
	}
}
