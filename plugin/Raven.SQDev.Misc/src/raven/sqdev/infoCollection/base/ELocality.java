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
}
