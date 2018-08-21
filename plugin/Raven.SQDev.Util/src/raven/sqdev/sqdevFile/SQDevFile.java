package raven.sqdev.sqdevFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import raven.sqdev.exceptions.IllegalAccessStateException;
import raven.sqdev.exceptions.SQDevException;
import raven.sqdev.exceptions.SQDevFileIsInvalidException;
import raven.sqdev.exceptions.SQDevFileNoSuchAttributeException;
import raven.sqdev.misc.FileSystemUtil;
import raven.sqdev.util.EFileType;

/**
 * A <code>SQDevFile</code> contains some project specific information for the
 * plugin to use.<br>
 * This kind of file has to follow a <b>strict syntax:</b>
 * <li>Each file has to start with a header line specifying the version of the
 * file-format-specification used</li>
 * <li>Every information has to end with a newLine</li>
 * <li>An attribute has to assign it's value via <code>=</code></li>
 * <li>Every attribute may only be used once</li>
 * <li>An annotation has to start with <code>@</code></li>
 * <li>The argument for the annotation has to be encapsulated in quotation marks
 * </li><br>
 * 
 * @author Raven
 * 
 */
public class SQDevFile extends File {

	private static final long serialVersionUID = 7670426522093424394L;

	/**
	 * The version corresponding to this file
	 */
	private ESQDevFileVersion version;
	/**
	 * A fallback solution in case the file uses a format that dates further back,
	 * than the first {@linkplain ESQDevFileVersion}
	 */
	@SuppressWarnings("deprecation")
	private SQDevFileOld fallback;

	/**
	 * Creates a new <code>SQDevFile</code> on the given path
	 * 
	 * @param path
	 *            A pathname string
	 * @throws IllegalAccessStateException
	 *             If the file is not readable or writable this exception will be
	 *             thrown
	 * @throws IOException
	 */
	public SQDevFile(String path) throws IllegalAccessStateException, IOException {
		super(path);

		if (!this.exists()) {
			throw new FileNotFoundException("The file " + getPath() + " does not exist!");
		}

		if (!this.canRead()) {
			throw new IllegalAccessStateException("The SQDevFile " + getPath() + " is not readable!");
		}

		if (!this.canWrite()) {
			throw new IllegalAccessStateException("The SQDevFile " + getPath() + " is not writable!");
		}

		determineVersion();
	}

	/**
	 * Creates a new <code>SQDevFile</code> on the given path
	 * 
	 * @param path
	 *            An absolute, hierarchical URI with a scheme equal to "file", a
	 *            non-empty path component, and undefined authority, query, and
	 *            fragment components
	 * @throws IllegalAccessStateException
	 *             If the file is not readable or writable this exception will be
	 *             thrown
	 * @throws IOException
	 */
	public SQDevFile(URI path) throws IllegalAccessStateException, IOException {
		super(path);

		if (!this.exists()) {
			throw new FileNotFoundException("The file " + getPath() + " does not exist!");
		}

		if (!this.canRead()) {
			throw new IllegalAccessStateException("The SQDevFile " + getPath() + " is not readable!");
		}

		if (!this.canWrite()) {
			throw new IllegalAccessStateException("The SQDevFile " + getPath() + " is not writable!");
		}

		determineVersion();
	}

	/**
	 * Converts the given file to a <code>SQDevFile</code>
	 * 
	 * @param file
	 *            The file to be converted. <b>Must exist!</b>
	 * @throws IllegalAccessStateException
	 *             If the file is not readable or writable this exception will be
	 *             thrown
	 * @throws IOException
	 */
	public SQDevFile(IFile file) throws IllegalAccessStateException, IOException {
		this(file.getRawLocation().toString());
	}

	/**
	 * Converts the given path to a <code>SQDevFile</code>
	 * 
	 * @param path
	 *            The path to the file to be converted. <b>Must exist!</b>
	 * @throws IllegalAccessStateException
	 *             If the file is not readable or writable this exception will be
	 *             thrown
	 * @throws IOException
	 */
	public SQDevFile(IPath path) throws IllegalAccessStateException, IOException {
		this(path.toString());
	}

	/**
	 * Creates a new SQDev-file with the newest file format specification
	 * 
	 * @param path
	 *            The path of the file to be created
	 * @return The created SQDevFile
	 * @throws IOException
	 * @throws FileAlreadyExistsException
	 * @throws IllegalAccessStateException
	 */
	public static SQDevFile create(String path)
			throws FileAlreadyExistsException, IOException, IllegalAccessStateException {
		File file = new File(path);

		file.createNewFile();

		return SQDevFile.create(file);
	}

	/**
	 * Transforms the given file into a valid SQDev file according to the most
	 * recent file format specification
	 * 
	 * @param file
	 *            The file to be transformed. This file has to be empty.
	 * @return
	 * @throws IOException
	 * @throws IllegalAccessStateException
	 */
	public static SQDevFile create(File file) throws IOException, IllegalAccessStateException {
		if (!file.exists()) {
			return SQDevFile.create(file.getAbsolutePath());
		}
		if (!file.isFile()) {
			throw new IllegalArgumentException("The provided file is not actually a file!");
		}

		// Make sure the file is empty
		FileReader reader = new FileReader(file);
		if (reader.read() != -1) {
			reader.close();
			throw new FileAlreadyExistsException(file.getAbsolutePath(), null,
					"The given file already exists and is non-empty!");
		}
		reader.close();

		FileWriter writer = new FileWriter(file);
		writer.write(ESQDevFileVersion.newest().getPreamble());
		writer.close();

		return new SQDevFile(file.toURI());
	}

	/**
	 * Determines which version this file is using. If none can be determined
	 * {@linkplain SQDevFileOld} is tried as a fallback
	 * 
	 * @throws IOException
	 * @throws IllegalAccessStateException
	 */
	@SuppressWarnings("deprecation")
	protected void determineVersion() throws IOException, IllegalAccessStateException {
		version = ESQDevFileVersion.getVersion(this);

		if (version == null) {
			fallback = new SQDevFileOld(this.toURI());

			if (!fallback.isValid()) {
				// it is also invalid with the old fallback version -> might as well use latest
				// version
				version = ESQDevFileVersion.newest();
				fallback = null;
			}
		}
	}

	/**
	 * Gets the version of this SQDev file or <code>null</code> if the content of
	 * this file dates further back than the introduction of the versions. In that
	 * case the file can be accessed as a {@linkplain SQDevFileOld}.
	 */
	public ESQDevFileVersion getVersion() {
		return version;
	}

	/**
	 * Gets the content of this file
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String getContent() throws FileNotFoundException, IOException {
		return FileSystemUtil.readAll(new FileInputStream(this));
	}

	/**
	 * Check if this file is valid
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("deprecation")
	public boolean isValid() throws FileNotFoundException, IOException {
		if (getVersion() != null) {
			return getVersion().isValid(getContent());
		} else {
			return fallback.isValid();
		}
	}

	/**
	 * Starts validating the SQDevFile and notifying listeners about encountered
	 * errors. For files that don't follow a format recognized by
	 * {@linkplain ESQDevFileVersion} no validation will be performed. They can be
	 * checked for validity via {@link #isValid()} though.
	 * 
	 * @param listener
	 *            The {@linkplain ISQDevFileErrorListener} to report all encountered
	 *            errors to
	 */
	public void validate(ISQDevFileErrorListener listener) throws FileNotFoundException, IOException {
		if (getVersion() != null) {
			getVersion().validate(getContent(), listener);
		}
	}

	/**
	 * Processes the given {@link ESQDevFileAttribute} by setting its value to the
	 * one specified in the given input or its default value if it is not specified
	 * in this file. The previously set value will be cleared beforehand
	 * 
	 * @param attribute
	 *            The {@linkplain ESQDevFileAttribute} whose value is to be set
	 * 
	 * @throws SQDevFileNoSuchAttributeException
	 *             If the attribute is not specified in the given input and the
	 *             attribute does not have a default value
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws SQDevFileIsInvalidException
	 */
	@SuppressWarnings("deprecation")
	public void processAttribute(ESQDevFileAttribute attribute)
			throws SQDevFileIsInvalidException, SQDevFileNoSuchAttributeException, FileNotFoundException, IOException {
		if (getVersion() != null) {
			getVersion().processAttribute(attribute, getContent());
		} else {
			fallback.processAttribute(attribute);
		}
	}

	/**
	 * Processes the given {@link ESQDevFileAnnotation} by setting its values to the
	 * ones specified in this file. Any previously set values will be cleared
	 * beforehand.
	 * 
	 * @param attribute
	 *            The {@linkplain ESQDevFileAnnotation} whose values are to be
	 *            determined
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws SQDevFileIsInvalidException
	 */
	@SuppressWarnings("deprecation")
	public void processAnnotation(ESQDevFileAnnotation annotation)
			throws SQDevFileIsInvalidException, FileNotFoundException, IOException {
		if (getVersion() != null) {
			getVersion().processAnnotation(annotation, getContent());
		} else {
			fallback.processAnnotation(annotation);
		}
	}

	/**
	 * Adds the given attribute (with its set value) to this file. While doing so
	 * this method will try to add it after all previously added attributes instead
	 * of simply inserting it at the end of the file. If the given attribute is
	 * already contained in the file, its value will be overwritten accordingly.
	 * 
	 * @param attribute
	 *            The attribute to add
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws SQDevException
	 */
	@SuppressWarnings("deprecation")
	public void addAttribute(ESQDevFileAttribute attribute) throws FileNotFoundException, IOException, SQDevException {
		if (getVersion() != null) {
			Files.write(this.toPath(), getVersion().addAttribute(attribute, getContent()).toString().getBytes(),
					new OpenOption[] {});
		} else {
			fallback.addAttribute(attribute, attribute.getValue());
		}
	}

	/**
	 * Adds the given annotation with all its values to this file. While doing so
	 * this method will try to add it after all previously added annotations instead
	 * of simply inserting it at the end of the file.
	 * 
	 * @param annotation
	 *            The annotation to add
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws SQDevException
	 */
	@SuppressWarnings("deprecation")
	public void addAnnotation(ESQDevFileAnnotation annotation)
			throws FileNotFoundException, IOException, SQDevException {
		if (getVersion() != null) {
			Files.write(this.toPath(), getVersion().addAnnotation(annotation, getContent()).toString().getBytes(),
					new OpenOption[] {});
		} else {
			for (String currentValue : annotation.getValues()) {
				fallback.addAnnotation(annotation, currentValue);
			}
		}
	}

	public static void main(String[] args) throws IOException, SQDevException {
		SQDevFile sqdevFile = null;
		File targetFile = new File(System.getProperty("user.home") + File.separator + "Tester." + EFileType.SQDEV);

		if (targetFile.exists()) {
			sqdevFile = new SQDevFile(targetFile.getAbsolutePath());
		} else {
			sqdevFile = create(targetFile);
		}

		ESQDevFileAttribute.PROFILE.setValue("Miau");
		ESQDevFileAttribute.TERRAIN.setValue("myTerrain");
		ESQDevFileAnnotation.IGNORE.addValue("me");
		ESQDevFileAnnotation.IGNORE.addValue("myself");
		ESQDevFileAnnotation.PRESERVE.addValue("you");
		ESQDevFileAttribute.AUTOEXPORT.setValue(String.valueOf(true));

		sqdevFile.addAttribute(ESQDevFileAttribute.PROFILE);
		sqdevFile.addAnnotation(ESQDevFileAnnotation.IGNORE);
		sqdevFile.addAttribute(ESQDevFileAttribute.TERRAIN);
		sqdevFile.addAnnotation(ESQDevFileAnnotation.PRESERVE);
		sqdevFile.addAttribute(ESQDevFileAttribute.AUTOEXPORT);

		final String content = sqdevFile.getContent();

		sqdevFile.validate(new ISQDevFileErrorListener() {

			@Override
			public boolean error(int start, int length, String errorMsg) {
				System.out.println("Encountered error: " + errorMsg + " - " + content.substring(start, start + length));

				return true;
			}
		});
	}
}
