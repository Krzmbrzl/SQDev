package raven.sqdev.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.ui.editors.text.TextFileDocumentProvider;

/**
 * A basic document provider using the <code>BasicPartitionScanner</code>
 * 
 * @author Raven
 * 
 * @see {@linkplain BasicPartitionScanner}
 * 
 */
public class BasicDocumentProvider extends TextFileDocumentProvider {
	
	/**
	 * The partition scanner for this document
	 */
	protected BasicPartitionScanner scanner;
	
	/**
	 * The used document partitioner
	 */
	protected IDocumentPartitioner partitioner;
	
	/**
	 * Creates a new instance of this document provider that can be used for all
	 * resources corresponding to a file
	 */
	public BasicDocumentProvider() {
		super();
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
	
	@Override
	public IDocument getDocument(Object element) {
		IDocument document = super.getDocument(element);
		
		if (partitioner == null && document != null) {
			IDocumentPartitioner partitioner = new FastPartitioner(
					getPartitionScanner(),
					getPartitionScanner().getConfiguredContentTypes());
			
			partitioner.connect(document);		
			document.setDocumentPartitioner(partitioner);
			
			// store the partitioner
			this.partitioner = partitioner;
		}
		
		return document;
	}
	
}
