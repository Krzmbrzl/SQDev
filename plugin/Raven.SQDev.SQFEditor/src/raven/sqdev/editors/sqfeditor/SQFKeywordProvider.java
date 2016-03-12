package raven.sqdev.editors.sqfeditor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import raven.sqdev.editors.IKeywordProvider;
import raven.sqdev.editors.exceptions.SQDevEditorException;
import raven.sqdev.editors.sqfeditor.exceptions.IllegalBlankException;
import raven.sqdev.util.ResourceManager;
import raven.sqdev.util.SQDevInfobox;

public class SQFKeywordProvider implements IKeywordProvider {
	
	public SQFKeywordProvider() {
		ArrayList<String> keywordList = new ArrayList<String>();
		
		ResourceManager manager = new ResourceManager();
		
		InputStream in = manager.findResource("/resources/sqf/Keywords.txt");
		
		if (in == null) {
			// something went wrong in the process of finding the respective
			// resource
			setKeywords(new String[0]);
			
			try {
				throw new SQDevEditorException("Couldn't find SQF keywords!");
			} catch (SQDevEditorException e) {
				SQDevInfobox info = new SQDevInfobox("Failed at instantiating SQF editor properly!",
						e);
				info.open();
				
				e.printStackTrace();
			}
		}
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			String line = reader.readLine();
			
			while (line != null) {
				line = line.trim();
				
				if (line.contains(" ")) {
					throw new IllegalBlankException("A keyword mustn't contain a blank!");
				} else {
					keywordList.add(line);
				}
				
				line = reader.readLine();
			}
			
			reader.close();
			
		} catch (IOException | IllegalBlankException e) {
			e.printStackTrace();
		}
		
		String[] keywords = new String[keywordList.size()];
		
		// pack in array
		for (int i = 0; i < keywordList.size(); i++) {
			keywords[i] = keywordList.get(i);
		}
		
		setKeywords(keywords);
	}
	
	/**
	 * The keyword this provider provides
	 */
	protected String[] keywords;
	
	@Override
	public String[] getKeywords() {
		return keywords;
	}
	
	@Override
	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}
	
}
