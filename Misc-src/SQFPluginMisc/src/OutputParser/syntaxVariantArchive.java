package OutputParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import SQF.Functions;

public class syntaxVariantArchive {
	private ArrayList<syntaxVariant> syntaxVariantArchive_list;

	public syntaxVariantArchive() {
		syntaxVariantArchive_list = new ArrayList<syntaxVariant>();
	}

	public void addSyntaxVariant(syntaxVariant synVar) {
		syntaxVariantArchive_list.add(synVar);
	}

	/**
	 * @return The list of syntax variants
	 */
	public ArrayList<syntaxVariant> getArchive() {
		return this.syntaxVariantArchive_list;
	}

	/**
	 * Check if the given syntaxVariant is in this archive
	 * 
	 * @param synVar
	 *            The syntaxVariant to be searched for
	 * @return
	 */
	public boolean contains(syntaxVariant synVar) {
		if (this.syntaxVariantArchive_list.isEmpty()) {
			return false;
		}

		boolean contains = false;

		if (this.syntaxVariantArchive_list.indexOf(synVar) >= 0) {
			contains = true;
		}

		return contains;
	}

	/**
	 * Check if the given syntax is in this archive
	 * 
	 * @param syntax
	 *            The syntax to be searched for
	 * @return
	 */
	public boolean contains(String syntax) {
		if (this.syntaxVariantArchive_list.isEmpty()) {
			return false;
		}

		boolean contains = false;

		for (int i = 0; i < this.syntaxVariantArchive_list.size(); i++) {
			String currentSyntax = this.syntaxVariantArchive_list.get(i)
					.getSyntax();

			if (syntax.equals(currentSyntax)) {
				contains = true;
				break;
			}
		}

		return contains;
	}

	/**
	 * Adds a syntaxVariant to this archive if it is not already in it
	 * 
	 * @param synVar
	 */
	public void add(syntaxVariant synVar) {
		this.add(synVar, false);
	}

	/**
	 * Adds s syntaxVariant to this archive
	 * 
	 * @param synVar
	 *            The syntaxVariant that should be added
	 * @param noCheck
	 *            indicates if it should be checked if this archive already
	 *            contains this syntaxVariant
	 */
	public void add(syntaxVariant synVar, boolean noCheck) {
		if (noCheck && this.contains(synVar)) {
			// if the syntaxVariant is already in this archive don't store it
			// again
			return;
		}

		this.syntaxVariantArchive_list.add(synVar);
	}

	/**
	 * Adds a syntax to this archive (will be converted to a syntaxVariant with
	 * empty commands list)
	 * 
	 * @param syntax
	 */
	public void add(String syntax) {
		syntaxVariant synVar = new syntaxVariant();

		synVar.setSyntax(syntax);

		this.add(synVar);
	}

	/**
	 * @return The size of the archive
	 */
	public int size() {
		return this.syntaxVariantArchive_list.size();
	}
	
	/**
	 * @return Returns an array of strings which are all the parameter used in the different syntaxVariants
	 * stored in this archive
	 */
	public String[] getParameter() {
		ArrayList<String> parameter = new ArrayList<String>();

		for (int i = 0; i < this.size(); i++) {
			syntaxVariant synVar = this.syntaxVariantArchive_list.get(i);
			String syntax = synVar.getSyntax();

			// remove all special characters
			if (syntax.indexOf("(") >= 0) {
				syntax = syntax.replaceAll("\\(", "");
			}
			if (syntax.indexOf(")") >= 0) {
				syntax = syntax.replaceAll("\\)", "");
			}
			if (syntax.indexOf("?") >= 0) {
				syntax = syntax.replaceAll("\\?", "");
			}
			if (syntax.indexOf("[") >= 0) {
				syntax = syntax.replaceAll("\\[", "");
			}
			if (syntax.indexOf("]") >= 0) {
				syntax = syntax.replaceAll("\\]", "");
			}
			if (syntax.indexOf(",") >= 0) {
				syntax = syntax.replaceAll(",", " ");
			}
			if (syntax.indexOf("/") >= 0) {
				syntax = syntax.replaceAll("/", " ");
			}
			
			//replace some parameter names which are the same
			if(syntax.indexOf("number") >= 0) {
				syntax = syntax.replaceAll("number", "NUMBER");
			}
			if(syntax.indexOf("object") >= 0) {
				syntax = syntax.replaceAll("object", "OBJECT");
			}
			if(syntax.indexOf("string") >= 0) {
				syntax = syntax.replaceAll("string", "STRING");
			}
			if(syntax.indexOf("anything") >= 0) {
				syntax = syntax.replaceAll("anything", "ANYTHING");
			}
			if(syntax.indexOf("boolean") >= 0) {
				syntax = syntax.replaceAll("boolean", "BOOLEAN");
			}
			if(syntax.indexOf("bool") >= 0) {
				syntax = syntax.replaceAll("bool", "BOOLEAN");
			}
			if(syntax.indexOf("positionasl") >= 0) {
				syntax = syntax.replaceAll("positionasl", "PositionASL");
			}
			if(syntax.indexOf("positionatl") >= 0) {
				syntax = syntax.replaceAll("positionatl", "PositionATL");
			}
			if(syntax.indexOf("any") >= 0) {
				syntax = syntax.replaceAll("any", "ANYTHING");
			}
			if(syntax.indexOf("position2d") >= 0) {
				syntax = syntax.replaceAll("position2d", "Position2D");
			}
			if(syntax.indexOf("position3d") >= 0) {
				syntax = syntax.replaceAll("position3d", "Position3D");
			}
			if(syntax.indexOf("scripthandle") >= 0) {
				syntax = syntax.replaceAll("scripthandle", "ScriptHandle");
			}
			if(syntax.indexOf("editorOBJECT") >= 0) {
				syntax = syntax.replaceAll("editorOBJECT", "EditorObject");
			}


			String[] aSyntax = Functions.getElements(syntax);

			for (String current : aSyntax) {
				// if this parameter wasn't found before store it in parameter
				
				if(!current.substring(0,1).toUpperCase().equals(current.substring(0,1))) {
					//if string starts with a lowercase letter
					current = current.substring(0,1).toUpperCase() + current.substring(1);
				}
				
				if (!parameter.contains(current) && !current.equals("CommandNameKeyword")) {
					parameter.add(current);
				}
			}
		}

		int quantity = parameter.size();
		String[] aParameter = new String[quantity];

		for (int j = 0; j < quantity; j++) {
			String currentParameter = parameter.get(j);

			aParameter[j] = currentParameter;
		}

		return aParameter;
	}
	
	/**
	 * Returns a string with all the different syntaxes (no corrsponding commands) represented
	 *  by the stored syntaxVariants seperated with a newLine
	 */
	public String toString() {
		String allSyntaxes = "";
		
		for(syntaxVariant currentSynVar : this.syntaxVariantArchive_list) {
			String currentSyntax = currentSynVar.getSyntax();
			
			allSyntaxes += currentSyntax + "\n";
		}
		
		return allSyntaxes;
	}
	
	/**
	 * Returns the position of the syntaxVariant corresponding to that syntax in this archive. If the syntax can't
	 * be found -1 is returned
	 * @param syntax The syntax the funtion should search for
	 * @return The position of the syntax in the archive (-1 = notFound)
	 */
	public int find(String syntax) {
		if(syntax.indexOf("commandNameKeyword") < 0) {
			System.err.println("ERROR: syntax must contain 'commandNameKeyword'");
			return -2;
		}
		
		for(int i=0; i<this.syntaxVariantArchive_list.size(); i++) {
			syntaxVariant current = this.syntaxVariantArchive_list.get(i);
			
			String currentSyntax = current.getSyntax();
			
			if(currentSyntax.equalsIgnoreCase(syntax)) {
				return i;
			}
		}
		
		//willbe executed if nothing was found
		System.out.println(syntax + " konnte nicht gefunden werden!");
		return -1;
	}
	
	/**
	 * This function will store all the synaxVariants stored in this archive into the given directory.
	 * Therefor it will create a file for every entry in this archive in the directory.
	 * If the directory and/or the files don't exist yet, they will be created
	 * 
	 * @param directory The path to the directory the syntaxVariants shall be stored
	 */
	public void store(String directory) {
		File directoryFile = new File(directory);
		
		if(!directoryFile.exists()) {
			directoryFile.mkdirs();
		}
		
		int count = this.syntaxVariantArchive_list.size();
		
		for(int i=0; i< count; i++) {
			syntaxVariant currentSynVar = this.syntaxVariantArchive_list.get(i);
			
			String fileName = "/syntaxVariant" + i + ".ser";
			String filePath = directory + fileName;
			
			File outputFile = new File(directory + fileName);
			
			if(!outputFile.exists()) {
				try {
					outputFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			try {
				FileOutputStream fileOut = new FileOutputStream(filePath);
				ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
				
				objOut.writeObject(currentSynVar);
				
				objOut.close();
			} catch (FileNotFoundException e) {
				System.err.println("source: syntaxVariantArchive -> storeInFile()");
				e.printStackTrace();
				System.out.println();
			} catch (IOException e) {
				System.err.println("source: syntaxVariantArchive -> storeInFile()");
				e.printStackTrace();
				System.out.println();
			}
		}
	}
	
	public void load(String directory) {
		File directoryFile = new File(directory);
		
		if(!directoryFile.exists()) {
			System.err.println("Directory '" + directory + "' does not exist");
			return;
		}
		
		File[] files = directoryFile.listFiles();
		
		for(File currentFile : files) {
			try {
				FileInputStream fileIn = new FileInputStream(currentFile);
				ObjectInputStream objIn = new ObjectInputStream(fileIn);
				
				syntaxVariant currentObject = (syntaxVariant) objIn.readObject();
				
				this.add(currentObject);
				
				objIn.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param position The index of the syntaxVariant
	 * @return Returns the syntaxVariant with the given index in this archive
	 */
	public syntaxVariant get(int position) {
		return this.syntaxVariantArchive_list.get(position);
	}
}
