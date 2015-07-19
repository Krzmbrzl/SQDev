package SQF;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class syntaxArchiveList{
	private ArrayList<syntaxArchive> list;
	
	public syntaxArchiveList() {
		list = new ArrayList<syntaxArchive>();
	}
	
	/**
	 * Adds an archive to this list
	 * @param synArchive The archive to add
	 */
	public void add(syntaxArchive synArchive) {
		list.add(synArchive);
	}
	
	/**
	 * Sorts all the syntaxes of the different archives regarding the syntaxVariants
	 */
	public void sort() {
		for(syntaxArchive current : list) {
			current.sort();
		}
	}

}
