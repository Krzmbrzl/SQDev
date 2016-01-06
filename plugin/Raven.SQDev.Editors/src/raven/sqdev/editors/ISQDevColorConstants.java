package raven.sqdev.editors;

import org.eclipse.swt.graphics.RGB;

public interface ISQDevColorConstants {
	public static final RGB DEFAULT = new RGB(0,0,0);
	public static final RGB BRACKETMATCH = new RGB(192,192,192);
	public static final RGB CURRENTLINE = new RGB(220,220,255);
	public static final RGB KEYWORD = new RGB(127,0,85);
	public static final RGB COMMENT = new RGB(63,127,95);
	public static final RGB STRING = new RGB(42,0,255);
	
	/**
	 * Creates a String of the form <code>red,green,blue</code> according to the given <code>RGB</code>
	 * @param rgb The <code>RGB</code> whose values should be used
	 * @return The newly created String
	 */
	public static String getRGBValuesAsString(RGB rgb) {
		return (String) (rgb.red + "," + rgb.green + "," +rgb.blue);
	}
}
