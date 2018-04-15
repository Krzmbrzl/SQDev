package raven.sqdev.infoCollection;

import java.io.IOException;
import java.net.URL;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import raven.sqdev.exceptions.SQDevException;

/**
 * This class will provide the URLs to the SQF-command's wiki pages
 * 
 * @author Raven
 *
 */
public class SQFCommandPageProvider {
	
	/**
	 * The web-adress to the BIKI API
	 */
	private final String apiAdress;
	/**
	 * The name of the base site where the scriptig commands are listed
	 */
	private final String mainPageName;
	/**
	 * A stack with all the command's URLs
	 */
	private static Stack<URL> commandURLs;
	
	
	/**
	 * Creates a new instance of this class
	 * 
	 * @param apiAdress
	 *            The web-adress to the BIKI API
	 * @param mainPageName
	 *            The name of the base site where the scriptig commands are
	 *            listed
	 * @throws IOException
	 *             Thrown if there are errors during the initialization
	 */
	public SQFCommandPageProvider(String apiAdress, String mainPageName)
			throws IOException {
		this.apiAdress = apiAdress;
		this.mainPageName = mainPageName;
		
		if (commandURLs == null) {
			initialize();
		}
	}
	
	/**
	 * Gets the adress to the BIKI API in form of a String
	 */
	protected String getApiAdress() {
		return apiAdress;
	}
	
	/**
	 * Get the name of the base site that lists the scripting commands
	 */
	protected String getMainPageName() {
		return mainPageName;
	}
	
	/**
	 * Initializes this provider by processing the command listing
	 * 
	 * @throws IOException
	 *             Thrown if something went wrong during the connection to the
	 *             BIKI
	 */
	protected void initialize() throws IOException {
		final String base = getApiAdress()
				+ "?action=query&list=categorymembers&cmtitle="
				+ getMainPageName() + "&cmlimit=500&format=jsonfm&cmtype=page";
		
		URL requestURL = new URL(base);
		
		final StringBuilder siteContent = new StringBuilder();
		
		final Pattern continuePattern = Pattern.compile("cmcontinue.*?\\n");
		
		String content = SQFCommandCollector.getSite(requestURL, true);
		// gather the complete list
		while (content.contains("cmcontinue")) {
			siteContent.append(content);
			
			final Matcher matcher = continuePattern.matcher(content);
			
			matcher.find();
			
			final String cont = content
					.substring(matcher.start(), matcher.end()).replace("\"", "")
					.replace(" ", "").replace(":", "=").replace(",", "");
			
			requestURL = new URL(base + "&" + cont);
			
			content = SQFCommandCollector.getSite(requestURL, true);
		}
		
		siteContent.append(content);
		
		// filter out the respective page IDs
		final Matcher idMatcher = Pattern.compile("\"pageid\":.*?,")
				.matcher(siteContent);
		
		final Stack<String> pageIDs = new Stack<String>();
		
		while (idMatcher.find()) {
			// add page ID by cutting away the irrelevant part of the line
			pageIDs.add(siteContent.substring(idMatcher.start() + 10,
					idMatcher.end() - 1));
		}
		
		// get page URLs based on the page's ID
		final StringBuilder urlPageContent = new StringBuilder();
		
		while (!pageIDs.isEmpty()) {
			final StringBuilder urlSearchAdress = new StringBuilder(
					"https://community.bistudio.com/wikidata/api.php?action=query&format=jsonfm&prop=info&inprop=url&pageids=");
			for (int i = 0; i < 50; i++) {
				urlSearchAdress.append(pageIDs.pop() + "|");
				
				if (pageIDs.isEmpty()) {
					break;
				}
			}
			
			// remove last '|'
			urlSearchAdress.setLength(urlSearchAdress.length() - 1);
			
			urlPageContent.append(SQFCommandCollector
					.getSite(new URL(urlSearchAdress.toString()), true));
		}
		
		// strip out the actual URLs from this page
		commandURLs = new Stack<URL>();
		
		final Matcher urlMatcher = Pattern
				.compile("\"fullurl\":\\s*\".*?\",")
				.matcher(urlPageContent.toString());
		while (urlMatcher.find()) {
			commandURLs.add(new URL(urlPageContent
					.substring(urlMatcher.start() + 10, urlMatcher.end() - 1).replace("\"", "").trim()));
		}
	}
	
	/**
	 * Gets the URL to the wiki page of the next command
	 * 
	 * @return The respective URL or <code>null</code> if there are no more (or
	 *         none could be found)
	 * @throws SQDevException
	 */
	public URL next() {
		return (commandURLs.isEmpty()) ? null : commandURLs.pop();
	}
	
	/**
	 * Gets the amount of pages this provider contains
	 */
	public int size() {
		return commandURLs.size();
	}
	
	/**
	 * Forces this provider to update it's internal list of command URLs
	 * 
	 * @throws IOException
	 */
	public void forceUpdate() throws IOException {
		initialize();
	}
	
	/**
	 * Adds the given URL on top of the Stack of command URLs
	 * 
	 * @param url
	 *            The URL to add to the Stack
	 */
	public void popBack(URL url) {
		commandURLs.add(url);
	}
	
	
	public static void main(String[] args) throws IOException {
		new SQFCommandPageProvider(
				"https://community.bistudio.com/wikidata/api.php",
				"Category:Scripting_Commands_Arma_3");
	}
}
