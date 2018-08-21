package raven.sqdev.pluginManagement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.jar.JarInputStream;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;

import raven.sqdev.exceptions.SQDevCoreException;
import raven.sqdev.exceptions.SQDevException;
import raven.sqdev.misc.FileSystemUtil;

/**
 * This <code>ResourceManager</code> is responsible for managing the resources
 * of this plugin including reading and writing
 * 
 * @author Raven
 * 
 */
public class ResourceManager {
	/**
	 * The extension used for backup resources
	 */
	private static final String BACKUP_EXTENSION = ".back";
	
	/**
	 * The ResourceManager itself
	 */
	private static ResourceManager manager;
	
	/**
	 * The name of the resource containing info about the versions of the
	 * plugins
	 */
	public static final String VERSION_RESOURCE = "versions.txt";
	/**
	 * The name of the resource containing the stored keywords and the
	 * respective information about them
	 */
	public static final String KEYWORDS_RESOURCE = "SQFKeywords.txt";
	/**
	 * The internal path to the icons folder
	 */
	public static final String ICON_FOLDER = "/resources/icons";
	/**
	 * The internal path to the wiki icon resource
	 */
	public static final String WIKI_ICON = ICON_FOLDER + "/sqdevWikiIcon.png";
	/**
	 * The internal path to the project icon resource
	 */
	public static final String PROJECT_ICON = ICON_FOLDER + "/prj_obj.gif";
	/**
	 * The internal path to the export icon resource
	 */
	public static final String EXPORT_ICON = ICON_FOLDER + "/sqdevExportIcon.png";
	/**
	 * The internal path to the import icon resource
	 */
	public static final String IMPORT_ICON = ICON_FOLDER + "/sqdevImportIcon.png";
	/**
	 * The internal path to the sqdev-file icon resource
	 */
	public static final String SQDEVFILE_ICON = ICON_FOLDER + "/sqdevFileIcon.png";
	/**
	 * The internal path to the SQF icon resource
	 */
	public static final String SQF_ICON = ICON_FOLDER + "/SQFIcon.png";
	/**
	 * The internal path to the SQFCommand icon resource
	 */
	public static final String SQFCOMMAND_ICON = ICON_FOLDER + "/SQFCommandIcon.png";
	/**
	 * The internal path to the plus icon resource
	 */
	public static final String PLUS_ICON = ICON_FOLDER + "/plusIcon.png";
	/**
	 * The internal path to the remove icon
	 */
	public static final String REMOVE_ICON = ICON_FOLDER + "/removeIcon.png";
	/**
	 * The internal path to the minus icon
	 */
	public static final String MINUS_ICON = ICON_FOLDER + "/minusIcon.png";
	/**
	 * The internal path to the Stringtable icon
	 */
	public static final String STRINGTABLE_ICON = ICON_FOLDER + "/StringTableIcon.png";
	/**
	 * The internal path to the keyword file
	 */
	public static final String INTERNAL_KEYWORDS = "/resources/sqf/SQFKeywords.txt";
	
	private ClassLoader loader;
	private URL locationURL;
	private URI locationURI;
	/**
	 * The path to the resource location of this plugin
	 */
	private IPath resourceLocation;
	/**
	 * The path to the backup resource location of this plugin containg older
	 * versions of resources
	 */
	private IPath backupResourceLocation;
	
	/**
	 * Creates an instance of this <code>ResourceManager</code>
	 */
	private ResourceManager() {
		setLoader(this.getClass().getClassLoader());
		setLocationURL(this.getClass().getProtectionDomain().getCodeSource().getLocation());
		
		try {
			setLocationURI(new URI(getLocationURL().toString().replace(" ", "%20")));
		} catch (URISyntaxException e) {
			e.printStackTrace();
			
			setLocationURI(null);
		}
		
		initializeResourceLocation();
	}
	
	/**
	 * Gets the <code>ResourceManager</code>
	 */
	public static final ResourceManager getManager() {
		if (manager == null) {
			manager = new ResourceManager();
		}
		
		return manager;
	}
	
	/**
	 * Gets the hard coded resource stored directly in this plugin's jar
	 * 
	 * @param path
	 *            The path within this plugin leading to the resource. (has to
	 *            start with "/resources")
	 * @return The <code>InputStream</code> to this resource or
	 *         <code>null</code> if this resource couldn't be found
	 */
	public InputStream getInternalResourceStream(String path) {
		if (!path.startsWith("/resources")) {
			throw new IllegalArgumentException("Given path has to reference the resource-folder!");
		}
		
		InputStream in = null;
		
		switch (getLocationURI().getScheme()) {
			case "file":
				in = getLoader().getResourceAsStream(path);
				break;
			
			case "jar":
				try {
					in = new JarInputStream(getLoader().getResourceAsStream(path));
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
		}
		
		return in;
	}
	
	/**
	 * Updates the resource file of the given name with the given content after
	 * backing up the current content of the resource file.<br>
	 * Note: There's always only one backup for each resource file
	 * 
	 * @param name
	 *            The name of the resource. If it daoes not contain an extension
	 *            the extension ".txt" will be added.<br>
	 *            If no such resource does exist it will be created.
	 * @param content
	 *            The new content of this resourcefile
	 * @throws IOException
	 * @throws SQDevException
	 */
	public void updateResource(String name, String content) throws IOException, SQDevException {
		if (!resourceExists(name)) {
			// create resource + backup
			createResource(name);
		}
		
		// make windows compatible
		content = content.replace("\r", "").replace("\n", "\r\n");
		
		// backup resource
		backupResource(name);
		
		// write new content
		FileWriter writer = new FileWriter(getResource(name).toFile());
		
		writer.write(content);
		
		writer.close();
	}
	
	/**
	 * Gets an <code>InputStream</code> to the resource with the given name
	 * 
	 * @param name
	 *            The name of the resource. If it does not contain an exntension
	 *            the extension ".txt" will be added.
	 * @return The <code>InputStream</code> to this resource file or
	 *         <code>null</code> if the resource couldn't be found
	 */
	public InputStream getResourceStream(String name) {
		if (resourceExists(name)) {
			try {
				return new FileInputStream(getResource(name).toFile());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				
				return null;
			}
		} else {
			return null;
		}
	}
	
	/**
	 * Gets an <code>InputStream</code> to the backup resource with the given
	 * name
	 * 
	 * @param name
	 *            The name of the normal resource. If it does not contain an
	 *            exntension the extension ".txt" will be added.
	 * @return The <code>InputStream</code> to this backup resource file or
	 *         <code>null</code> if the resource couldn't be found
	 */
	public InputStream getBackupResourceStream(String name) {
		if (resourceExists(name)) {
			try {
				return new FileInputStream(getBackupResource(name).toFile());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				
				return null;
			}
		} else {
			return null;
		}
	}
	
	/**
	 * Gets the content of the resource file with the given name
	 * 
	 * @param name
	 *            The name of the normal resource. If it does not contain an
	 *            exntension the extension ".txt" will be added.
	 * @return The content of this resource or <code>null</code> if the
	 *         gathering of this resource's content was not possible.
	 */
	public String getResourceContent(String name) {
		if (!resourceExists(name)) {
			return null;
		}
		
		try {
			return FileSystemUtil.getContent(getResource(name).toFile());
		} catch (SQDevException e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
	/**
	 * Gets the content of the backup resource file corresponding to the given
	 * name
	 * 
	 * @param nameThe
	 *            name of the normal resource. If it does not contain an
	 *            exntension the extension ".txt" will be added.
	 * @return
	 */
	public String getBackupResourceContent(String name) {
		if (!resourceExists(name)) {
			return null;
		}
		
		try {
			return FileSystemUtil.getContent(getBackupResource(name).toFile());
		} catch (SQDevException e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
	private ClassLoader getLoader() {
		return loader;
	}
	
	private void setLoader(ClassLoader loader) {
		this.loader = loader;
	}
	
	public URL getLocationURL() {
		return locationURL;
	}
	
	private void setLocationURL(URL locationURL) {
		this.locationURL = locationURL;
	}
	
	public URI getLocationURI() {
		return locationURI;
	}
	
	private void setLocationURI(URI locationURI) {
		this.locationURI = locationURI;
	}
	
	/**
	 * Initializes the resource location
	 */
	private void initializeResourceLocation() {
		// make sure the resource location exists
		IPath stateLocation = Platform.getStateLocation(Platform.getBundle("raven.sqdev.misc"));
		resourceLocation = stateLocation.append("resources");
		backupResourceLocation = resourceLocation.append("Backup");
		
		if (!resourceLocation.toFile().exists()) {
			// create resource location
			resourceLocation.toFile().mkdir();
			
			if (!backupResourceLocation.toFile().exists()) {
				// create backup resource location
				backupResourceLocation.toFile().mkdir();
			}
			
			// initialize necessary resources
			try {
				createResource("SQFKeywords.txt");
				
				String content = FileSystemUtil
						.readAll(getInternalResourceStream(INTERNAL_KEYWORDS));
				
				// put content in respective resource files
				FileWriter writer = new FileWriter(getResource("SQFKeywords.txt").toFile());
				writer.write(content);
				writer.close();
				
				writer = new FileWriter(getBackupResource("SQFKeywords.txt").toFile());
				writer.write(content);
				writer.close();
				
			} catch (IOException e) {
				throw new SQDevCoreException("Failed at creating resources", e);
			}
		}
		
		if (!backupResourceLocation.toFile().exists()) {
			// create backup resource location
			backupResourceLocation.toFile().mkdir();
		}
		
		if (!resourceExists(VERSION_RESOURCE)) {
			try {
				createResource(VERSION_RESOURCE);
			} catch (IOException e) {
				throw new SQDevCoreException("Failed at initializing reosurce location!", e);
			}
		}
	}
	
	/**
	 * Creates a resource file with the given name
	 * 
	 * @param name
	 *            The name of the resource file. If it does not contain an
	 *            extension the extension ".txt" will be added
	 * @return <code>True</code> if the creation was successfull
	 * @throws IOException
	 */
	public boolean createResource(String name) throws IOException {
		if (!name.contains(".")) {
			// make a text document out of it
			name += ".txt";
		}
		
		IPath resourcePath = resourceLocation.append(name);
		IPath backupResourceFile = backupResourceLocation.append(name + BACKUP_EXTENSION);
		
		if (!resourcePath.toFile().exists()) {
			resourcePath.toFile().createNewFile();
			backupResourceFile.toFile().createNewFile();
			
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Gets the path to the resource with the given name
	 * 
	 * @param name
	 *            The name of the resource file. If it does not contain an
	 *            extension the extension ".txt" will be added
	 * @return The <code>Path</code> to the resource fileor <code>null</code> if
	 *         there is no such resourcefile
	 */
	private IPath getResource(String name) {
		if (!name.contains(".")) {
			name += ".txt";
		}
		
		IPath resourcePath = resourceLocation.append(name);
		
		if (resourcePath.toFile().exists()) {
			return resourcePath;
		} else {
			return null;
		}
	}
	
	/**
	 * Gets the path to the backup resource with the given name
	 * 
	 * @param name
	 *            The name of the backup resource file. If it does not contain
	 *            an extension the extension ".txt" will be added
	 * @return The <code>Path</code> to the resource fileor <code>null</code> if
	 *         there is no such resourcefile
	 */
	private IPath getBackupResource(String name) {
		if (!name.contains(".")) {
			name += ".txt";
		}
		
		if (!name.endsWith(BACKUP_EXTENSION)) {
			// add the backup extension
			name += BACKUP_EXTENSION;
		}
		
		IPath backupResourcePath = backupResourceLocation.append(name);
		
		if (backupResourcePath.toFile().exists()) {
			return backupResourcePath;
		} else {
			return null;
		}
	}
	
	/**
	 * Will write the current content of the resource with the given name into
	 * it's corresponding backup resource file
	 * 
	 * @param name
	 *            The name of the backup resource file. If it does not contain
	 *            an extension the extension ".txt" will be added
	 * @throws SQDevException
	 */
	private void backupResource(String name) throws SQDevException {
		if (!name.contains(".")) {
			name += ".txt";
		}
		
		try {
			if (!resourceExists(name)) {
				// create resources if they don't exist
				createResource(name);
				
				return;
			}
			
			// get the content of current resource
			String content = FileSystemUtil.getContent(getResource(name).toFile());
			
			// write the content into the backup
			FileWriter writer = new FileWriter(getBackupResource(name).toFile());
			writer.write(content);
			writer.close();
			
		} catch (IOException e) {
			throw new SQDevCoreException("Failed at backing up resource!", e);
		}
	}
	
	/**
	 * Checks if a resource with the given name and it's corresponding backup
	 * resource does exist
	 * 
	 * @param name
	 *            The name of the resource file. If it does not contain an
	 *            extension the extension ".txt" will be added
	 */
	public boolean resourceExists(String name) {
		return (getResource(name) != null && getBackupResource(name) != null);
	}
	
	/**
	 * Checks if the given resource is already accessed as it's backup resource
	 * (= the content of the resource is equal to the content of it's backup
	 * resource)
	 * 
	 * @param name
	 *            The name of the resource file. If it does not contain an
	 *            extension the extension ".txt" will be added
	 */
	public boolean isOnBackup(String name) {
		try {
			String content = FileSystemUtil.getContent(getResource(name).toFile());
			String backup = FileSystemUtil.getContent(getBackupResource(name).toFile());
			
			// compare the two
			return content.equals(backup);
		} catch (SQDevException e) {
			throw new SQDevCoreException(e);
		}
	}
	
	/**
	 * Switches the given resource to it's backup resource (The content of the
	 * backup resource is transferred to the resource itself). <b>This cannot be
	 * undone!</b>
	 * 
	 * @param name
	 *            The name of the resource file. If it does not contain an
	 *            extension the extension ".txt" will be added.
	 * @return <code>True</code> if switching was successful
	 */
	public boolean switchToBackup(String name) {
		if (!resourceExists(name)) {
			return false;
		}
		
		try {
			// get backup content
			String content = FileSystemUtil.getContent(getBackupResource(name).toFile());
			
			// write the content into the current resource
			FileWriter writer = new FileWriter(getResource(name).toFile());
			writer.write(content);
			writer.close();
			
			// all good
			return true;
		} catch (SQDevException | IOException e) {
			e.printStackTrace();
			
			// smoething went wrong
			return false;
		}
	}
}
