package raven.sqdev.editors;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.projection.ProjectionAnnotation;
import org.eclipse.jface.text.source.projection.ProjectionAnnotationModel;

import raven.sqdev.interfaces.IManager;

/**
 * This manager takes care about what parts of the document are foldable
 * 
 * @author Raven
 *
 */
public class BasicFoldingManager implements IManager {
	/**
	 * A queue of annotations that should be processed on the next apply
	 */
	protected Map<ProjectionAnnotation, Position> annotationQueue;
	/**
	 * The annotation model of the editor this manager should contribute to
	 */
	protected ProjectionAnnotationModel model;
	
	
	/**
	 * Creates a new instance of this manager
	 * 
	 * @param model
	 *            The <code>ProjectionAnnotationModel</code> this manager should
	 *            contribute to
	 */
	public BasicFoldingManager(ProjectionAnnotationModel model) {
		Assert.isNotNull(model, "The given model may not be null!");
		
		this.model = model;
		
		annotationQueue = new HashMap<ProjectionAnnotation, Position>();
	}
	
	/**
	 * Adds a foldable area
	 * 
	 * @param entry
	 *            The <code>Entry</code> to add consisting of a
	 *            <code>ProjectionAnnotation</code> and the respective position
	 */
	public void addFoldingArea(Entry<ProjectionAnnotation, Position> entry) {
		synchronized (annotationQueue) {
			annotationQueue.put(entry.getKey(), entry.getValue());
		}
	}
	
	@Override
	public void apply() {
		// clear
		model.removeAllAnnotations();
		
		synchronized (annotationQueue) {
			Iterator<Entry<ProjectionAnnotation, Position>> mapIterator = annotationQueue.entrySet()
					.iterator();
			
			while (mapIterator.hasNext()) {
				// add the foladable areas TODO: implement some overlap logic
				Entry<ProjectionAnnotation, Position> entry = mapIterator.next();
				
				model.addAnnotation(entry.getKey(), entry.getValue());
			}
			
			// clear queue as all annotations have been added
			annotationQueue.clear();
		}
	}
	
	@Override
	public String getType() {
		return BasicFoldingManager.getManagerType();
	}
	
	/**
	 * Gets the type representation of a <code>BasicFoldingManger</code>
	 */
	public static String getManagerType() {
		return "BasicFoldingManager";
	}
}
