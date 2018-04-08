package raven.sqdev.editors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocumentListener;

import raven.sqdev.misc.SQDevPreferenceUtil;

/**
 * This listener will cause the editor to re-parse its input when the user
 * stopped typing for a longer time
 * 
 * @author Raven
 *
 */
public class BasicParseTimeListener implements IDocumentListener {
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
	public void documentChanged(DocumentEvent event) {
		if (runningTask != null && !runningTask.isCancelled()
				&& !runningTask.isDone()) {
			runningTask.cancel(true);
			editor.cancelParsing();
		}
		
		runningTask = timer.schedule(parsing,
				SQDevPreferenceUtil.getParseDelay(), TimeUnit.MILLISECONDS);
	}
	
	@Override
	public void documentAboutToBeChanged(DocumentEvent event) {
	}
	
}
