package raven.sqdev.editors.sqfeditor.testing;

import raven.sqdev.interfaces.IMarkerSupport;

/**
 * This object does serve as a sort of "dummy"-marker-acceptor that allows to check whether all expected markers have been reported
 * @author Raven
 *
 */
public class TestMarkerAcceptor implements IMarkerSupport {
	
	public TestMarkerAcceptor() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void createMarker(String type, int offset, int length,
			String message, int severity) {
		// TODO store
		
	}
	
}
