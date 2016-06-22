package raven.sqdev.editors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.VerifyEvent;

import raven.sqdev.util.SQDevPreferenceUtil;

/**
 * This listener will cause the editor to reparse it's input when the user
 * stopped typing for a longer time
 * 
 * @author Raven
 *
 */
public class BasicParseTimeListener implements VerifyKeyListener {
	/**
	 * The editor this listener works on
	 */
	protected BasicCodeEditor editor;
	/**
	 * The timer used to determine when to parse the editor's input
	 */
	protected ScheduledExecutorService timer;
	/**
	 * The actual parsing task
	 */
	protected final Runnable parsing = new Runnable() {
		
		@Override
		public void run() {
			try {
				editor.parseInput();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	
	protected ScheduledFuture<?> runningTask;
	
	public BasicParseTimeListener(BasicCodeEditor editor) {
		Assert.isNotNull(editor);
		
		this.editor = editor;
		timer = Executors.newSingleThreadScheduledExecutor();
	}
	
	@Override
	public void verifyKey(VerifyEvent event) {
		if (runningTask != null && !runningTask.isCancelled() && !runningTask.isDone()) {
			runningTask.cancel(true);
		}
		
		int basicDelay = SQDevPreferenceUtil.getParseDelay() * 1000;
		
		switch (event.character) {
			case ';':
				// semicolon=end of statement -> parse fastly after key event
				runningTask = timer.schedule(parsing, basicDelay / 4, TimeUnit.MILLISECONDS);
				break;
			
			case ' ':
			case '\n':
			case '\b':
				// whitespace indicates the end of a word -> parse normal after
				// key event
				runningTask = timer.schedule(parsing, basicDelay / 2, TimeUnit.MILLISECONDS);
				break;
			
			default:
				// any other character -> parse slowly after key event
				runningTask = timer.schedule(parsing, basicDelay, TimeUnit.MILLISECONDS);
		}
	}
	
}
