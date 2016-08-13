package raven.sqdev.util;

import java.util.List;
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
	 * The <code>Runnbale</code> that will open the message box and fills it
	 * with the respective content
	 */
	protected final Runnable openDialog = new Runnable() {
		
		@Override
		public void run() {
			Shell eclipseShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
			
			if (eclipseShell == null) {
				// can't report error because user is not in eclipse
				// anymore
				return;
			}
			
			Shell active = Display.getCurrent().getActiveShell();
			if (((style & SWT.ERROR) == SWT.ERROR || (style & SWT.ICON_ERROR) == SWT.ICON_ERROR)
					&& !active.equals(eclipseShell)) {
				// close every other opened window
				active.close();
			}
			
			MessageBox box = new MessageBox(eclipseShell, style);
			
			box.setText("SQDev Info");
			box.setMessage(message);
			
			box.open();
		}
	};
	
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
	 * Creates an instance of this Infobox. This constructor will automatically
	 * use the Yes/No-Option for the popup in order to allow the user to answer
	 * the given question.
	 * 
	 * @param message
	 *            The message exlpaining what went wrong. The message will be
	 *            enhanced by a paragraph that states the reason (The message of
	 *            the given exception)
	 * @param exception
	 *            The exception whose message will be used for the explaination
	 *            for what went wrong.
	 * @param question
	 *            A question the user should answer with yes or no
	 */
	public SQDevInfobox(String message, Exception exception, String question) {
		this(((exception.getMessage() != null) ? message + "\n\nReason:\n" + exception.getMessage()
				: message + "\n\nReason: Unknown") + "\n\n" + question,
				SWT.ICON_ERROR | SWT.YES | SWT.NO);
	}
	
	/**
	 * Creates an instance of this InfoBox. This constructor will automatically
	 * use the style <code>SWT.ICON_ERROR</code>
	 * 
	 * @param message
	 *            The message that should be displayed
	 * @param exceptions
	 *            A list of exceptions that should be shown to the user
	 *            underneath the message
	 */
	public SQDevInfobox(String message, List<Throwable> exceptions) {
		StringBuilder builder = new StringBuilder(message = message.trim() + "\n\n");
		
		builder.append("Occured Exceptions:\n");
		
		for (Throwable current : exceptions) {
			builder.append("\t" + current.getMessage());
		}
		
		this.message = builder.toString();
		this.style = SWT.ICON_ERROR;
	}
	
	/**
	 * Adds the given style to the popup
	 * 
	 * @param style
	 *            The style to add has to be one defined in {@linkplain SWT}
	 */
	public void addStyle(int style) {
		this.style = this.style | style;
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
		
		display.syncExec(openDialog);
		
		
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
			
			display.asyncExec(openDialog);
		}
	}
	
}
