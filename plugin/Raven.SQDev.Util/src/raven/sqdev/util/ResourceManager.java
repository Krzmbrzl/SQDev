package raven.sqdev.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.jar.JarInputStream;

public class ResourceManager {
	
	private ClassLoader loader;
	private URL locationURL;
	private URI locationURI;
	
	public ResourceManager() {
		setLoader(this.getClass().getClassLoader());
		setLocationURL(this.getClass().getProtectionDomain().getCodeSource().getLocation());
		
		try {
			setLocationURI(new URI(getLocationURL().toString().replace(" ", "%20")));
		} catch (URISyntaxException e) {
			e.printStackTrace();
			
			setLocationURI(null);
		}
	}
	
	/**
	 * Searches for the resource
	 * 
	 * @param path The path within this structure
	 * @return
	 */
	public InputStream findResource(String path) {
		if(!path.startsWith("/resources")) {
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
	
}
