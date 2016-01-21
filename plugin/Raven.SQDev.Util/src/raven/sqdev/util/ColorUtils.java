package raven.sqdev.util;

import org.eclipse.swt.graphics.RGB;

import raven.sqdev.exceptions.IllegalRGBSyntaxException;

/**
 * A class containing various static Color functions
 * 
 * @author Raven
 * 		
 */
public class ColorUtils {
	
	/**
	 * Creates a String of the form <code>red,green,blue</code> according to the
	 * given <code>RGB</code>
	 * 
	 * @param rgb
	 *            The <code>RGB</code> whose values should be used
	 * @return The newly created String
	 */
	public static String getRGBValuesAsString(RGB rgb) {
		return (String) (rgb.red + "," + rgb.green + "," + rgb.blue);
	}
	
	/**
	 * Decodes a string into a <code>RGB</code>.
	 * 
	 * @param str
	 *            The string of the syntax <code>"red,green,blue"</code> whereas
	 *            red, green and blue are integer values.
	 * @return
	 */
	public static RGB decodeRGB(String str) {
		RGB rgb = new RGB(0, 0, 0);
		
		try {
			if (StringUtils.countMatches(str, ",") != 2) {
				throw new IllegalRGBSyntaxException(
						"The string \"" + str + "\" has an illegal syntax for decoding as RGB!");
			}
			
			int red = Integer.parseInt(str.substring(0, str.indexOf(",")).trim());
			int green = Integer
					.parseInt(str.substring(str.indexOf(",") + 1, str.lastIndexOf(",")).trim());
			int blue = Integer.parseInt(str.substring(str.lastIndexOf(",") + 1).trim());
			
			rgb.red = red;
			rgb.green = green;
			rgb.blue = blue;
		} catch (IllegalRGBSyntaxException e) {
			e.printStackTrace();
		}
		
		return rgb;
	}
}
