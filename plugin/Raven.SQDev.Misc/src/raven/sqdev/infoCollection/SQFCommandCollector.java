package raven.sqdev.infoCollection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

import org.eclipse.core.runtime.Assert;

import raven.sqdev.exceptions.SQDevCollectionException;
import raven.sqdev.infoCollection.base.KeywordList;

/**
 * A class for collecting all SQF commands from the BIKI.
 * 
 * @author Raven
 * 		
 */
public class SQFCommandCollector {
	
	/**
	 * The URL to the base site where all commands are listed
	 */
	private URL baseSite;
	
	/**
	 * The name of the first command in the list on the base site
	 */
	private String firstCommandName;
	
	public SQFCommandCollector(URL baseSite, String firstCommandName) {
		Assert.isNotNull(baseSite);
		Assert.isTrue(firstCommandName != null && !firstCommandName.isEmpty());
		
		this.baseSite = baseSite;
		this.firstCommandName = firstCommandName;
	}
	
	public KeywordList collect() throws SQDevCollectionException {
		String siteContent = getSite(baseSite);
		
		// get relevant content only
		siteContent = trimToRelevantListOnly(siteContent);
		
		// compose the line where the collecting should start at
		String relevantLine = "<li><a href=\"/wiki/" + firstCommandName + "\" title=\""
				+ firstCommandName + "\">" + firstCommandName + "</a></li>";
				
		if (!siteContent.contains(relevantLine)) {
			throw new SQDevCollectionException("The specified base site \"" + baseSite.toString()
					+ "\" does not contain a line corresponding to the specified first command \""
					+ firstCommandName + "\"");
		}
		
		// start the list at the first command
		siteContent = siteContent.substring(siteContent.indexOf(relevantLine));
		
		// go through each link and gather respective information
		for (String currentLine : siteContent.split("\n")) {
			// get the postfix for the wiki page of the current command
			String postfix = currentLine.substring(currentLine.indexOf("\"") + 1);
			postfix = postfix.substring(0, postfix.indexOf("\""));
			
			// TODO: call respective site and extract SQFCommand with all
			// necessary information
		}
		
		return null;
	}
	
	/**
	 * Gets the trimmed content of the specified site
	 * 
	 * @param url
	 *            The URL to the site
	 * @return
	 * @throws SQDevCollectionException
	 */
	private String getSite(URL url) throws SQDevCollectionException {
		try {
			BufferedReader siteReader = new BufferedReader(new InputStreamReader(url.openStream()));
			
			String content = "";
			
			String inputLine = "";
			
			while ((inputLine = siteReader.readLine()) != null) {
				content += inputLine + "\n";
			}
			
			siteReader.close();
			
			return content.trim();
			
		} catch (IOException e) {
			e.printStackTrace();
			
			// rethrow
			throw new SQDevCollectionException(e);
		}
	}
	
	/**
	 * Trims the given html content so that it only contains the lines relevant
	 * for the list containing the SQF commands
	 * 
	 * @param htmlContent
	 *            The content to trim
	 * @throws SQDevCollectionException
	 */
	private String trimToRelevantListOnly(String htmlContent) throws SQDevCollectionException {
		BufferedReader reader = new BufferedReader(new StringReader(htmlContent));
		
		String content = "";
		
		String currentLine = "";
		
		try {
			while ((currentLine = reader.readLine()) != null) {
				// only consider lines containing a link to the wiki
				if (currentLine.contains("<li><a href=\"/wiki/")) {
					content += currentLine + "\n";
				}
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			
			// rethrow
			throw new SQDevCollectionException(e);
		}
		
		return content.trim();
	}
	
}
