package raven.sqdev.editors;

import java.util.ArrayList;
import java.util.Calendar;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import raven.sqdev.exceptions.SQDevCoreException;

/**
 * A basic implementation of the keywordProvider providing the keywords for
 * syntax highlighting
 * 
 * @author Raven
 * 		
 */
public class BasicKeywordProvider implements IKeywordProvider {
	
	/**
	 * The keywords this provider will return
	 */
	protected String[] keywords;
	
	/**
	 * A list of the given keywords sorted alphabetically
	 */
	protected String[][] keywordsSorted;
	
	/**
	 * A flag indicating whether or not the keywords in keywordsSorted are
	 * currently sorted properly
	 */
	protected boolean keywordsAreSorted;
	
	/**
	 * A flag indicating whether the keywords are currently getting sorted
	 */
	protected boolean isSorting;
	
	public BasicKeywordProvider() {
		keywordsAreSorted = false;
		isSorting = false;
	}
	
	@Override
	public String[] getKeywords() {
		return (keywords == null) ? new String[0] : keywords;
	}
	
	@Override
	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
		
		// sort the new keywords
		Job sortJob = new Job("Sorting keywords") {
			
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				sort();
				
				return Status.OK_STATUS;
			}
		};
		
		sortJob.schedule();
	}
	
	/**
	 * Will sort the given keywords into keywordsSorted. This method should get
	 * executed in it's own thread if there are many keywords to sort
	 */
	protected void sort() {
		isSorting = true;
		keywordsAreSorted = false;
		
		ArrayList<ArrayList<String>> sortedKeywords = new ArrayList<ArrayList<String>>();
		
		// create empty sub-lists
		for (char currentChar = 'a'; currentChar <= 'z'; currentChar++) {
			sortedKeywords.add(new ArrayList<String>());
		}
		
		for (char currentChar = 'a'; currentChar <= 'z'; currentChar++) {
			for (String currentKeyword : keywords) {
				if (currentKeyword.toLowerCase().startsWith(String.valueOf(currentChar))) {
					// add the keyword to the list
					sortedKeywords.get(currentChar - 'a').add(currentKeyword);
				}
			}
		}
		
		// convert the list into an array
		keywordsSorted = new String[sortedKeywords.size()][];
		
		for (int i = 0; i < sortedKeywords.size(); i++) {
			keywordsSorted[i] = sortedKeywords.get(i).toArray(new String[sortedKeywords.size()]);
		}
		
		isSorting = false;
		keywordsAreSorted = true;
	}
	
	public String[][] getSortedKeywords() {
		if (keywordsAreSorted) {
			return keywordsSorted;
		} else {
			if (!isSorting) {
				// first sort and then return the sorted keywords
				Job sortJob = new Job("Sorting keywords") {
					
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						sort();
						
						return Status.OK_STATUS;
					}
				};
				sortJob.schedule();
				try {
					// wait until sorting has finished
					sortJob.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
					
					throw new SQDevCoreException(e);
				}
				
				return keywordsSorted;
				
			} else {
				// wait until sorting has finished
				
				long startTime = Calendar.getInstance().getTimeInMillis();
				
				while (isSorting) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					if (Calendar.getInstance().getTimeInMillis() - startTime > 60000) {
						throw new SQDevCoreException(
								"Waiting for the sorting of keywords has taken too long!");
					}
				}
				
				return keywordsSorted;
			}
		}
	}
	
}
