package raven.sqdev.editors;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * A color manager for decoding <code>RGB</code> into <code>Color</code>
 * 
 * @author Raven
 *		
 */
public class ColorManager {
	
	protected Map<RGB, Color> colorTable = new HashMap<RGB, Color>(10);
	
	/**
	 * Disposes all stored colors
	 */
	public void dispose() {
		Iterator<Color> e = colorTable.values().iterator();
		while (e.hasNext())
			e.next().dispose();
	}
	
	/**
	 * Get the respective color to a rgb value
	 * 
	 * @param rgb
	 *            The RGB value of the desired color
	 * @return
	 */
	public Color getColor(RGB rgb) {
		// check if the color has already been stored
		Color color = colorTable.get(rgb);
		
		if (color == null) {
			color = new Color(Display.getCurrent(), rgb);
			colorTable.put(rgb, color);
		}
		
		return color;
	}
}
