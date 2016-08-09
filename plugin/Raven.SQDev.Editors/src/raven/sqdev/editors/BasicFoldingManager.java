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
	 * Indicates that the annotation Queue has changed
	 */
	protected boolean annotationQueueChanged;
	
	
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
		annotationQueue.put(entry.getKey(), entry.getValue());
		
		// indicate change
		annotationQueueChanged = true;
	}
	
	@Override
	public void apply() {
		annotationQueueChanged = false;
		
		// clear
		model.removeAllAnnotations();
		
		Iterator<Entry<ProjectionAnnotation, Position>> mapIterator = annotationQueue.entrySet()
				.iterator();
		
		while (mapIterator.hasNext()) {
			if (annotationQueueChanged) {
				// the queue has been modified by another thread -> reapply to
				// avoid concurrent exception
				apply();
				break;
			}
			
			// add the foladable areas TODO: implement some overlap logic
			Entry<ProjectionAnnotation, Position> entry = mapIterator.next();
			
			model.addAnnotation(entry.getKey(), entry.getValue());
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
