package raven.sqdev.editors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.ui.IFileEditorInput;

import raven.sqdev.exceptions.SQDevEditorException;
import raven.sqdev.interfaces.IManager;

/**
 * This manager is used in order to manage the placement and removal of markers
 * 
 * @author Raven
 *
 */
public class BasicMarkerManager implements IManager {
	/**
	 * The managr type of this manager
	 */
	public static final String TYPE = "BasicMarkerManager";
	/**
	 * The editor this manager is woking on
	 */
	protected BasicCodeEditor editor;
	/**
	 * A list of markers this editor manages
	 */
	protected List<MarkerInformation> markers;
	/**
	 * Indicates whether this manager is in a valid state
	 */
	protected boolean isValid;
	
	
	public BasicMarkerManager(BasicCodeEditor editor) {
		Assert.isNotNull(editor, "Editor may not be null!");
		
		this.editor = editor;
		
		markers = new ArrayList<MarkerInformation>();
		isValid = true;
	}
	
	/**
	 * Adds a marker with the given information to this manager
	 * 
	 * @param type
	 *            The marker's type
	 * @param line
	 *            The line associated with this marker
	 * @param offset
	 *            The marker's offset
	 * @param length
	 *            The marker's length
	 * @param severity
	 *            The marker's severity
	 * @param message
	 *            The marker's message
	 * @return <code>True</Code> on success and <code>False</code> otherwise
	 */
	public boolean addMarker(String type, int line, int offset, int length,
			int severity, String message) {
		synchronized (markers) {
			markers.add(new MarkerInformation(type, line, offset, length,
					severity, message));
		}
		
		return true;
	}
	
	@Override
	public void apply() {
		if (editor.getEditorInput() instanceof IFileEditorInput) {
			IAnnotationModel model = editor.getDocumentProvider()
					.getAnnotationModel(editor.getEditorInput());
			Iterator<Annotation> it = model.getAnnotationIterator();
			
			while (it.hasNext()) {
				Annotation ann = it.next();
				System.out.println(ann.getType());
			}
			
			try {
				((IFileEditorInput) editor.getEditorInput()).getFile()
						.deleteMarkers(null, false, IResource.DEPTH_INFINITE);
			} catch (CoreException e) {
				try {
					throw new SQDevEditorException("Failed at deleting marker!",
							e);
				} catch (SQDevEditorException e1) {
					e1.printStackTrace();
					
					return;
				}
			}
			
			IResource resource = ((IFileEditorInput) editor.getEditorInput())
					.getFile();
			
			isValid = true;
			
			synchronized (markers) {
				for (MarkerInformation currentMarker : markers) {
					if (isValid && currentMarker
							.getSeverity() == IMarker.SEVERITY_ERROR) {
						isValid = false;
					}
					
					// create the actual markers
					currentMarker.applyOn(resource);
				}
				
				markers.clear();
			}
		} else {
			IAnnotationModel model = editor.getDocumentProvider()
					.getAnnotationModel(editor.getEditorInput());
			
			if (model == null) {
				try {
					throw new SQDevEditorException("No annotation model found");
				} catch (SQDevEditorException e) {
					e.printStackTrace();
				}
				
				return;
			}
			
			// clear old annotations
			Iterator<Annotation> it = model.getAnnotationIterator();
			
			while (it.hasNext()) {
				model.removeAnnotation(it.next());
			}
			
			synchronized (markers) {
				for (MarkerInformation currentMarker : markers) {
					if (isValid && currentMarker
							.getSeverity() == IMarker.SEVERITY_ERROR) {
						isValid = false;
					}
					
					// create annotation
					model.addAnnotation(currentMarker.toAnnotation(),
							currentMarker.getPosition());
				}
				
				markers.clear();
			}
		}
	}
	
	@Override
	public String getType() {
		return TYPE;
	}
	
	/**
	 * Checks whether this manager represents a valid state (= no errors)
	 */
	public boolean isValidState() {
		return isValid;
	}
	
}
