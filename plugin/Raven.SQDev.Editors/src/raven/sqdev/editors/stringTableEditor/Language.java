package raven.sqdev.editors.stringTableEditor;

/**
 * An enum representing all available languages in ArmA 3
 * 
 * @author Raven
 *
 */
public enum Language {
	/**
	 * The default value
	 */
	ORIGINAL {
		@Override
		public String getTooltip() {
			return "The default value";
		}
	},
	CZECH,
	ENGLISH,
	FRENCH,
	GERMAN,
	ITALIAN,
	JAPANESE,
	KOREAN,
	POLISH,
	PORTUGUESE,
	RUSSIAN,
	SPANISH;
	
	@Override
	public String toString() {
		String name = this.name().toLowerCase();
		
		return Character.toUpperCase(name.charAt(0)) + name.substring(1, name.length());
	}
	
	/**
	 * Gets the tooltip for this languge
	 */
	public String getTooltip() {
		return "In case the user has set his/her game to \"" + toString() + "\"";
	}
}
