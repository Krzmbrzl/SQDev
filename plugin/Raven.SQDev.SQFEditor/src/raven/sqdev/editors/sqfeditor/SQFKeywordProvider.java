package raven.sqdev.editors.sqfeditor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import raven.sqdev.editors.IKeywordProvider;
import raven.sqdev.editors.sqfeditor.exceptions.IllegalBlankException;
import raven.sqdev.util.ResourceManager;

public class SQFKeywordProvider implements IKeywordProvider {
	
	@Override
	public String[] getKeywords() {
		ArrayList<String> keywordList = new ArrayList<String>();
		
		ResourceManager manager = new ResourceManager();
		
		InputStream in = manager.findResource("/resources/sqf/Keywords.txt");
		
		if (in == null) {
			// something went wrong in the process of finding the respective
			// resource
			return new String[0];
		}
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			String line = reader.readLine();
			
			while(line != null) {
				line = line.trim();
				
				if(line.contains(" ")) {
					throw new IllegalBlankException("A keyword mustn't contain a blank!");
				}else {
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
		
		return keywords;
	}
	
}
