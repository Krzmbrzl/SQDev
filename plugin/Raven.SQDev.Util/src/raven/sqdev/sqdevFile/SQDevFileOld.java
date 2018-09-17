package raven.sqdev.sqdevFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IPath;

import raven.sqdev.exceptions.IllegalAccessStateException;
import raven.sqdev.exceptions.SQDevCoreException;
import raven.sqdev.exceptions.SQDevException;
import raven.sqdev.exceptions.SQDevFileIsInvalidException;
import raven.sqdev.exceptions.SQDevFileNoSuchAttributeException;
import raven.sqdev.exceptions.SQDevIllegalFileChangeException;
import raven.sqdev.misc.SQDevInfobox;
import raven.sqdev.misc.TextUtils;

/**
 * This class is deprecated. Use {@linkplain SQDevFile} instead!<br><br>
 * 
 * A <code>SQDevFile</code> contains some project specific information for the
 * plugin to use.<br>
 * This kind of file has to follow a <b>strict syntax:</b>
 * <li>Every information has to end with a newLine</li>
 * <li>An attribute has to assign it's value via <code>=</code></li>
 * <li>Every attribute may only be used once</li>
 * <li>Every attribute has to be closed with a semicolon on the same line</li>
 * <li>An annotation has to start with <code>@</code></li>
 * <li>The argument for the annotation has to be encapsulated in quotation marks
 * </li><br>
 * 
 * @author Raven
 * 		
 */
@Deprecated
public class SQDevFileOld extends File {
	
	private static final long serialVersionUID = 9142251132106001327L;
	
	/**
	 * Creates a new <code>SQDevFile</code> on the given path
	 * 
	 * @param path
	 *            A pathname string
	 * @throws FileNotFoundException
	 *             The constructor will check if the given path can be resolved.
	 *             If not it will throw this exception. <b>This file can't be
	 *             non-existent!</b>
	 * @throws IllegalAccessStateException
	 *             If the file is not readable or writable this exception will
	 *             be thrown
	 */
	public SQDevFileOld(String path) throws FileNotFoundException, IllegalAccessStateException {
		super(path);
		
		if (!this.exists()) {
			throw new FileNotFoundException("The file " + getPath() + " does not exist!");
		}
		
		if (!this.canRead()) {
			throw new IllegalAccessStateException(
					"The SQDevFile " + getPath() + " is not readable!");
		}
		
		if (!this.canWrite()) {
			throw new IllegalAccessStateException(
					"The SQDevFile " + getPath() + " is not writable!");
		}
	}
	
	/**
	 * Creates a new <code>SQDevFile</code> on the given path
	 * 
	 * @param path
	 *            An absolute, hierarchical URI with a scheme equal to "file", a
	 *            non-empty path component, and undefined authority, query, and
	 *            fragment components
	 * @throws FileNotFoundException
	 *             The constructor will check if the given path can be resolved.
	 *             If not it will throw this exception. <b>This file can't be
	 *             non-existent!</b>
	 * @throws IllegalAccessStateException
	 *             If the file is not readable or writable this exception will
	 *             be thrown
	 */
	public SQDevFileOld(URI path) throws FileNotFoundException, IllegalAccessStateException {
		super(path);
		
		if (!this.exists()) {
			throw new FileNotFoundException("The file " + getPath() + " does not exist!");
		}
		
		if (!this.canRead()) {
			throw new IllegalAccessStateException(
					"The SQDevFile " + getPath() + " is not readable!");
		}
		
		if (!this.canWrite()) {
			throw new IllegalAccessStateException(
					"The SQDevFile " + getPath() + " is not writable!");
		}
	}
	
	/**
	 * Converts the given file to a <code>SQDevFile</code>
	 * 
	 * @param file
	 *            The file to be converted. <b>Must exist!</b>
	 * @throws FileNotFoundException
	 *             The constructor will check if the given path can be resolved.
	 *             If not it will throw this exception. <b>This file can't be
	 *             non-existent!</b>
	 * @throws IllegalAccessStateException
	 *             If the file is not readable or writable this exception will
	 *             be thrown
	 */
	public SQDevFileOld(IFile file) throws FileNotFoundException, IllegalAccessStateException {
		this(file.getRawLocation().toString());
	}
	
	/**
	 * Converts the given path to a <code>SQDevFile</code>
	 * 
	 * @param path
	 *            The path to the file to be converted. <b>Must exist!</b>
	 * @throws FileNotFoundException
	 *             The constructor will check if the given path can be resolved.
	 *             If not it will throw this exception. <b>This file can't be
	 *             non-existent!</b>
	 * @throws IllegalAccessStateException
	 *             If the file is not readable or writable this exception will
	 *             be thrown
	 */
	public SQDevFileOld(IPath path) throws FileNotFoundException, IllegalAccessStateException {
		this(path.toString());
	}
	
	/**
	 * Returns a reader that has this file as an input.
	 * 
	 * @return The created reader
	 * @throws FileNotFoundException
	 */
	public BufferedReader openReader() throws FileNotFoundException {
		return new BufferedReader(new FileReader(this));
	}
	
	/**
	 * Returns a writer that operates on this file
	 * 
	 * @return The created writer
	 * @throws IOException
	 */
	public BufferedWriter openWriter() throws IOException {
		return new BufferedWriter(new FileWriter(this));
	}
	
	/**
	 * Gets the attributes that can be specified in this file
	 * 
	 * @return
	 */
	public ESQDevFileAttribute[] getAttributes() {
		return ESQDevFileAttribute.values();
	}
	
	/**
	 * Gets the attributes that can be specified in this file as a String array
	 * 
	 * @return
	 */
	public String[] getAttributesAsString() {
		ESQDevFileAttribute[] attributes = getAttributes();
		
		String[] strAttributes = new String[attributes.length];
		
		for (int i = 0; i < attributes.length; i++) {
			strAttributes[i] = attributes[i].toString();
		}
		
		return strAttributes;
	}
	
	/**
	 * Gets the annotations that can be specified in this file
	 * 
	 * @return
	 */
	public ESQDevFileAnnotation[] getAnnotations() {
		return ESQDevFileAnnotation.values();
	}
	
	/**
	 * Gets the annotations that can be specified in this file as a String array
	 * 
	 * @return
	 */
	public String[] getAnnotationsAsString() {
		ESQDevFileAnnotation[] annotations = getAnnotations();
		
		String[] strAnnotations = new String[annotations.length];
		
		for (int i = 0; i < annotations.length; i++) {
			strAnnotations[i] = annotations[i].toString();
		}
		
		return strAnnotations;
	}
	
	/**
	 * Check if this file is valid
	 */
	public boolean isValid() {
		String completeFile = "";
		try {
			String currentLine;
			BufferedReader reader = openReader();
			
			while ((currentLine = reader.readLine()) != null) {
				completeFile += currentLine + "\n";
				
				if (currentLine.isEmpty() || currentLine.trim().startsWith("//")) {
					// skip blank lines and comments
					continue;
				} else {
					// check if non-empty lines contains valid information
					if (ESQDevFileAttribute.isAttribute(currentLine)) {
						if (!currentLine.contains("=") || !currentLine.contains(";")) {
							// An attribute has to contain a "=" as well as a
							// ";"
							return false;
						}
					} else {
						if (ESQDevFileAnnotation.isAnnotation(currentLine)) {
							if (!currentLine.contains(" ")) {
								// an annotation always has to specify an
								// argument
								return false;
							} else {
								String argument = currentLine.substring(currentLine.indexOf(" "))
										.trim();
										
								if (!argument.startsWith("\"") || !argument.endsWith("\"")) {
									// The argument has to be encapsulated in
									// quotation marks
									return false;
								}
							}
						} else {
							// if it's neither an attribute nor an annotation
							return false;
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		for (String currentAttribute : getAttributesAsString()) {
			if (TextUtils.countMatches(completeFile, currentAttribute) > 1) {
				// Each attribute may only be specified once
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Gets the complete file content as a String (making sure that it ends with
	 * a newLine)
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String getContent() throws FileNotFoundException, IOException {
		String completeFile = "";
		
		String currentLine;
		BufferedReader reader = openReader();
		while ((currentLine = reader.readLine()) != null) {
			completeFile += currentLine + "\n";
		}
		
		return completeFile;
	}
	
	/**
	 * Sets the content of this file
	 * 
	 * @param content
	 *            The new fileContent
	 * @throws IOException
	 */
	public void setContent(String content) throws IOException {
		FileWriter writer = new FileWriter(this);
		
		writer.write(content);
		
		writer.close();
	}
	
	/**
	 * Takes the given attribute and sets it's value accordingly to this file
	 * 
	 * @param attribute
	 *            The attribute that should be obtained
	 * @return The given attribute with the set value
	 * @throws SQDevFileIsInvalidException
	 *             This exception is thrown when the this file is invalid
	 */
	public ESQDevFileAttribute processAttribute(ESQDevFileAttribute attribute)
			throws SQDevFileIsInvalidException {
		if (!isValid()) {
			throw new SQDevFileIsInvalidException();
		}
		
		String content;
		try {
			content = getContent();
		} catch (IOException e) {
			throw new SQDevCoreException(e);
		}
		
		// remove all comments
		content = content.replaceAll("//.*\n", "");
		
		if (!content.contains(attribute.toString())) {
			if (!attribute.hasDefault()) {
				throw new SQDevFileNoSuchAttributeException(
						"The attribute \"" + attribute.toString() + "\" is not specified in "
								+ getPath() + " nor does it have a default value");
			} else {
				// if the attribute is not specified it will have it's default
				// value
				attribute.setValue(attribute.getDefault());
				return attribute;
			}
		}
		
		boolean found = false;
		String detectedLine = "";
		
		while (!found) {
			detectedLine = content.substring(content.indexOf(attribute.toString()));
			detectedLine = detectedLine.substring(0, detectedLine.indexOf("\n"));
			
			
			String detectedAttribute = detectedLine.substring(0, detectedLine.indexOf("=")).trim();
			
			found = detectedAttribute.equals(attribute.toString());
			
			if (!found) {
				// trim content with each iteration to prevent an endless loop
				content = content.substring(content.indexOf(detectedLine) + detectedLine.length());
			}
		}
		
		// get the value and store it in attribute
		String value = detectedLine
				.substring(detectedLine.indexOf("=") + 1, detectedLine.indexOf(";")).trim();
				
		attribute.setValue(value);
		
		return attribute;
	}
	
	/**
	 * Takes the given annotation and sets it's values accordingly to this file
	 * 
	 * @param annotation
	 *            The desired annotation
	 * @return The annotation with the set values
	 * @throws SQDevFileIsInvalidException
	 *             This exception is thrown when this file is invalid
	 */
	public ESQDevFileAnnotation processAnnotation(ESQDevFileAnnotation annotation)
			throws SQDevFileIsInvalidException {
		// clean the values
		annotation.setValues(new ArrayList<String>());
		
		if (!isValid()) {
			throw new SQDevFileIsInvalidException();
		}
		
		String content;
		try {
			content = getContent();
		} catch (IOException e) {
			throw new SQDevCoreException(e);
		}
		
		// remove all comments
		content = content.replaceAll("//.*\n", "");
		
		// check if the annotation is specified
		if (!content.contains("@" + annotation + " ")) {
			// if the annotation is not specified just set empty values
			annotation.setValues(new ArrayList<String>());
		} else {
			// get the values of this annotation
			while (content.contains("@" + annotation + " ")) {
				String detectedLine = content.substring(content.indexOf("@" + annotation + " "));
				detectedLine = detectedLine.substring(0, detectedLine.indexOf("\n"));
				
				// get the value that is enclosed in quotation marks
				String value = detectedLine
						.substring(detectedLine.indexOf("\"") + 1, detectedLine.lastIndexOf("\""))
						.trim();
						
				// add the value to the annotation
				annotation.addValue(value);
				
				
				// cut content to prevent endless loop
				content = content.substring(content.indexOf(detectedLine) + detectedLine.length());
			}
		}
		
		return annotation;
	}
	
	/**
	 * Checks if the given attribute is used in this file (comments are not
	 * considered)
	 * 
	 * @param attribute
	 *            The attribute to search for
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public boolean contains(ESQDevFileAttribute attribute)
			throws FileNotFoundException, IOException {
		// get content and remove comments
		String content = getContent().replaceAll("//.*\n", "");
		
		while (content.contains("  ")) {
			// prevent double spaces
			content = content.replace("  ", " ");
		}
		
		return (content.contains(attribute + "=") || content.contains(attribute + " ="));
	}
	
	/**
	 * Checks if the given annotation is used in this file (comments are not
	 * considered)
	 * 
	 * @param annotation
	 *            The annotation to search for
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public boolean contains(ESQDevFileAnnotation annotation)
			throws FileNotFoundException, IOException {
		String content = getContent().replaceAll("//.*\n", "");
		
		return content.contains("@" + annotation + " ");
	}
	
	/**
	 * Adds the given attribute with the given value to this file
	 * 
	 * @param attribute
	 *            The attribute to add
	 * @param value
	 *            The value of the attribute
	 * @throws SQDevException
	 */
	public void addAttribute(ESQDevFileAttribute attribute, String value) throws SQDevException {
		Assert.isNotNull(attribute);
		Assert.isNotNull(value);
		Assert.isTrue(!value.isEmpty());
		
		try {
			if (contains(attribute)) {
				throw new SQDevIllegalFileChangeException(
						"Can't add attribute \"" + attribute + "\" because it already exists");
			}
		} catch (IOException e) {
			new SQDevInfobox("Can't add attribute to file \"" + this.getName() + "\"!", e).open();
			e.printStackTrace();
		}
		
		try {
			String content = getContent();
			String insertedAttribute = attribute + " = " + value + ";\n";
			
			if (content.contains("=")) {
				// add the attribute after the other attributes
				String fragment1 = content.substring(0, content.lastIndexOf("="));
				fragment1 = fragment1.substring(fragment1.indexOf("\n") + 1);
				
				String fragment2 = content.substring(fragment1.length());
				
				content = fragment1 + insertedAttribute + fragment2;
			} else {
				// add at the beginning
				content = insertedAttribute + content;
			}
			
			// set the content
			setContent(content);
		} catch (IOException e) {
			// rethrow
			throw new SQDevException(e);
		}
	}
	
	/**
	 * Adds the given annotation with the given value to this file.
	 * 
	 * @param annotation
	 *            The annotation to add
	 * @param value
	 *            The value of the annotation
	 * @throws SQDevException
	 */
	public void addAnnotation(ESQDevFileAnnotation annotation, String value) throws SQDevException {
		Assert.isNotNull(annotation);
		Assert.isNotNull(value);
		Assert.isTrue(!value.isEmpty());
		
		try {
			String content = getContent();
			String insertedAnnotation = "@" + annotation + " \"" + value + "\"\n";
			
			if (content.contains("@" + annotation + " ")) {
				// add right after the last annotation of this type
				String fragment1 = content.substring(0,
						content.lastIndexOf("@" + annotation + " "));
				fragment1 = fragment1.substring(fragment1.indexOf("\n") + 1);
				
				String fragment2 = content.substring(fragment1.length());
				
				content = fragment1 + insertedAnnotation + fragment2;
			} else {
				// add at the end of the file
				content = content + insertedAnnotation;
			}
			
			// set conted
			setContent(content);
		} catch (IOException e) {
			// rethrow
			throw new SQDevException(e);
		}
	}
	
}
