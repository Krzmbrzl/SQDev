package raven.sqdev.editors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.ui.editors.text.FileDocumentProvider;

/**
 * A basic document provider using the <code>BasicPartitionScanner</code>
 * 
 * @author Raven
 * 		
 * @see {@linkplain BasicPartitionScanner}
 * 		
 */
public class BasicDocumentProvider extends FileDocumentProvider {
	
	/**
	 * The partition scanner for this document
	 */
	protected BasicPartitionScanner scanner;
	
	/**
	 * The used document partitioner
	 */
	protected IDocumentPartitioner partitioner;
	
	protected IDocument createDocument(Object element) throws CoreException {
		IDocument document = super.createDocument(element);
		
		if (document != null) {
			IDocumentPartitioner partitioner = new FastPartitioner(getPartitionScanner(),
					getPartitionScanner().getConfiguredContentTypes());
					
					
			partitioner.connect(document);
			document.setDocumentPartitioner(partitioner);
			
			// store the partitioner
			this.partitioner = partitioner;
		}
		return document;
	}
	
	/**
	 * Gets the partition scanner this documentProvider uses
	 */
	public BasicPartitionScanner getPartitionScanner() {
		if (scanner == null) {
			scanner = new BasicPartitionScanner();
		}
		
		return scanner;
	}
	
	/**
	 * Gets the partitioner this documentProvider uses
	 */
	public IDocumentPartitioner getPartitioner() {
		return partitioner;
	}
	
}
