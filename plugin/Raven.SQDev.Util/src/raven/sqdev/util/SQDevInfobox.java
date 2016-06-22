package raven.sqdev.util;

import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * A class used to inform the user about something that happened in the SQDev
 * plugin. This will bring a MessageBox up that is scheduled in the UI-thread
 * automatically
 * 
 * @author Raven
 * 
 */
public class SQDevInfobox {
	
	/**
	 * Defining the style of the infobox
	 */
	protected int style = SWT.NONE;
	
	protected String message;
	
	/**
	 * Creates an instance of this Infobox
	 * 
	 * @param message
	 *            The message to display
	 */
	public SQDevInfobox(String message) {
		this.message = message;
	}
	
	/**
	 * Creates an instance of this Infobox
	 * 
	 * @param message
	 *            The message to display
	 * @param style
	 *            The style of the Infobox. Can be any of
	 *            {@linkplain MessageBox}'s styles
	 */
	public SQDevInfobox(String message, int style) {
		this.message = message;
		this.style = style;
	}
	
	/**
	 * Creates an instance of this Infobox.
	 * 
	 * @param message
	 *            The message exlpaining what went wrong. The message will be
	 *            enhanced by a paragraph that states the reason (The message of
	 *            the given exception)
	 * @param exception
	 *            The exception whose message will be used for the explaination
	 *            for what went wrong.
	 */
	public SQDevInfobox(String message, Exception exception) {
		this((exception.getMessage() != null) ? message + "\n\nReason:\n" + exception.getMessage()
				: message + "\n\nReason: Unknown", SWT.ICON_ERROR);
	}
	
	/**
	 * Makes the dialog visible and brings it to the front of the display.
	 * 
	 * @return the ID of the button that was selected to dismiss the message box
	 *         (e.g. SWT.OK, SWT.CANCEL, etc.)
	 */
	public int open() {
		Display display = PlatformUI.getWorkbench().getDisplay();
		
		AtomicInteger result = new AtomicInteger();
		
		display.syncExec(new Runnable() {
			
			@Override
			public void run() {
				Shell activeShell = Display.getCurrent().getActiveShell();
				
				if (activeShell == null) {
					// can't report error because user is not in eclipse anymore
					return;
				}
				
				MessageBox box = new MessageBox(activeShell, style);
				
				box.setText("SQDev Info");
				box.setMessage(message);
				
				result.set(box.open());
				
				Shell active = Display.getCurrent().getActiveShell();
				
				if (style == SWT.ERROR && !active
						.equals(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell())) {
					// close every other opened window
					active.close();
				}
			}
		});
		
		
		return result.get();
	}
	
	/**
	 * Makes the dialog visible and brings it to the front of the display.<br>
	 * Note: This method can't return any value
	 * 
	 * @param suspend
	 *            Whether or not to suspend the calling thread.
	 */
	public void open(boolean suspend) {
		if (suspend) {
			open();
		} else {
			Display display = PlatformUI.getWorkbench().getDisplay();
			
			display.syncExec(new Runnable() {
				
				@Override
				public void run() {
					Shell activeShell = Display.getCurrent().getActiveShell();
					
					if (activeShell == null) {
						// can't report error because user is not in eclipse
						// anymore
						return;
					}
					
					MessageBox box = new MessageBox(activeShell, style);
					
					box.setText("SQDev Info");
					box.setMessage(message);
					
					box.open();
					
					Shell active = Display.getCurrent().getActiveShell();
					
					if (style == SWT.ERROR && !active.equals(
							PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell())) {
						// close every other opened window
						active.close();
					}
				}
			});
		}
	}
	
}
