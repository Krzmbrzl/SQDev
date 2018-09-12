package raven.sqdev.interfaces;

import java.io.IOException;
import java.io.InputStream;

/**
 * An interface describing a StreamProvider that will return an
 * {@linkplain InputStream} for a given path
 * 
 * @author Raven
 *
 */
public interface IStreamProvider {

	/**
	 * Gets the {@linkplain InputStream} for the given path
	 * 
	 * @param path
	 *            The path to get a stream for
	 * @return The respective stream or <code>null</code> if there is none
	 * @throws IOException 
	 */
	public InputStream getStreamFor(String path) throws IOException;
}
