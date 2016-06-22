package raven.sqdev.editors;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

import org.eclipse.core.filebuffers.manipulation.ContainerCreator;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.editors.text.FileDocumentProvider;
import org.eclipse.ui.texteditor.ResourceMarkerAnnotationModel;

import raven.sqdev.exceptions.SQDevCoreException;

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
	
	@Override
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
		this.setEncoding(null, null);
		return partitioner;
	}
	
	@Override
	protected void doSaveDocument(IProgressMonitor monitor, Object element, IDocument document,
			boolean overwrite) throws CoreException {
		if (element instanceof IFileEditorInput) {
			
			IFileEditorInput input = (IFileEditorInput) element;
			
			FileInfo info = (FileInfo) getElementInfo(element);
			IFile file = input.getFile();
			
			Charset charset;
			try {
				charset = Charset.forName("UTF-8");
			} catch (UnsupportedCharsetException ex) {
				throw new SQDevCoreException("UTF-8 is not supported!", ex);
			} catch (IllegalCharsetNameException ex) {
				throw new SQDevCoreException("Impossible exception in BasicDocumentProvider!", ex);
			}
			
			CharsetEncoder encoder = charset.newEncoder();
			encoder.onMalformedInput(CodingErrorAction.REPLACE);
			encoder.onUnmappableCharacter(CodingErrorAction.REPORT);
			
			InputStream stream;
			
			try {
				byte[] bytes;
				ByteBuffer byteBuffer = encoder.encode(CharBuffer.wrap(document.get()));
				if (byteBuffer.hasArray())
					bytes = byteBuffer.array();
				else {
					bytes = new byte[byteBuffer.limit()];
					byteBuffer.get(bytes);
				}
				stream = new ByteArrayInputStream(bytes, 0, byteBuffer.limit());
			} catch (CharacterCodingException ex) {
				throw new SQDevCoreException("Error while encoding...", ex);
			}
			
			if (file.exists()) {
				
				if (info != null && !overwrite)
					checkSynchronizationState(info.fModificationStamp, file);
				
				// inform about the upcoming content change
				fireElementStateChanging(element);
				try {
					file.setContents(stream, overwrite, true, monitor);
				} catch (CoreException x) {
					// inform about failure
					fireElementStateChangeFailed(element);
					throw x;
				} catch (RuntimeException x) {
					// inform about failure
					fireElementStateChangeFailed(element);
					throw x;
				}
				
				// If here, the editor state will be flipped to "not dirty".
				// Thus, the state changing flag will be reset.
				
				if (info != null) {
					
					ResourceMarkerAnnotationModel model = (ResourceMarkerAnnotationModel) info.fModel;
					if (model != null)
						model.updateMarkers(info.fDocument);
					
					info.fModificationStamp = computeModificationStamp(file);
				}
				
			} else {
				try {
					monitor.beginTask("Saving file...", 2000);
					ContainerCreator creator = new ContainerCreator(file.getWorkspace(),
							file.getParent().getFullPath());
					creator.createContainer(new SubProgressMonitor(monitor, 1000));
					file.create(stream, false, new SubProgressMonitor(monitor, 1000));
				} finally {
					monitor.done();
				}
			}
			
		} else {
			super.doSaveDocument(monitor, element, document, overwrite);
		}
	}
	
}
