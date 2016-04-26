package raven.sqdev.infoCollection.base;

public enum ELocality {
	/**
	 * Indicating something to be local
	 */
	LOCAL {
		@Override
		public String toString() {
			return "local";
		}
	},
	
	/**
	 * Indicating something to be global
	 */
	GLOBAL {
		@Override
		public String toString() {
			return "global";
		}
	},
	
	/**
	 * Indicating something does not have a locality (for instance if it's not
	 * existent)
	 */
	UNDEFINED {
		@Override
		public String toString() {
			return "undefined";
		}
	};
	
	/**
	 * Finds the locality enum constant represented by this String
	 * 
	 * @param locality
	 *            The String representation of the desired enum constant
	 * @return The resolved enum constant or <code>null</code> if none could be
	 *         found
	 */
	public static ELocality resolve(String locality) {
		for (ELocality current : ELocality.values()) {
			if (current.toString().toLowerCase().equals(locality.toLowerCase())) {
				return current;
			}
		}
		
		return null;
	}
}
