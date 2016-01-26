package raven.sqdev.sqdevFile;

public enum ESQDevFileAnnotation {
	IGNORE {
		@Override
		public String toString() {
			return "ignore";
		}
	};
	
	/**
	 * Checks if the given line contains a valid annotation in a SQDevFile.<br>
	 * @param inputLine The line to be checked
	 * @return <code>True</code> if it's an annotation
	 */
	public static boolean isAnnotation(String inputLine) {
		inputLine = inputLine.trim();
		if (!inputLine.startsWith("@")) {
			// An annotation has to start with "@"
			return false;
		}
		
		String possibleAnnotation = (inputLine.contains(" "))
				? inputLine.substring(1, inputLine.indexOf(" ")) : inputLine.substring(1);
				
		for(ESQDevFileAnnotation current : ESQDevFileAnnotation.values()) {
			if(current.toString().equals(possibleAnnotation)) {
				return true;
			}
		}
		
		return false;
	}
}
