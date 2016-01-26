package raven.sqdev.util;

import java.io.ByteArrayInputStream;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.texteditor.ITextEditor;

import raven.sqdev.exceptions.FailedAtCreatingFileException;
import raven.sqdev.sqdevFile.ESQDevFileType;

/**
 * An enum representing the different file types available in the SQDev plugin
 * that is able to create the respective file either in the container currently
 * selected in the package explorer or at a given Path within the workspace.
 * 
 * @author Raven
 * 		
 */
public enum EFileType {
	/**
	 * Scriptfile with the extension ".sqf"
	 */
	SQF {
		@Override
		public String getExtension() {
			return ".sqf";
		}
		
		@Override
		public String getInitialContent() {
			return ("scopeName " + getFileName().substring(0, getFileName().length() - 4)
					+ ";\n\n");
		}
	},
	
	/**
	 * The "description.ext"
	 */
	EXT {
		@Override
		public String getExtension() {
			return ".ext";
		}
		
		@Override
		public void create(String name, boolean open) {
			// assure that the fileName is "description"
			name = "description";
			
			super.create(name, open);
		}
		
		@Override
		public String getInitialContent() {
			// TODO use Nickname if possible
			
			String initialContent = "author = " + System.getProperty("user.name") + ";\n";
			return initialContent;
		}
	},
	
	/**
	 * The "mission.sqm"
	 */
	SQM {
		@Override
		public String getExtension() {
			return ".sqm";
		}
		
		@Override
		public String getInitialContent() {
			// TODO Auto-generated method stub
			return "";
		}
	},
	
	/**
	 * Config files with the ectension ".cpp"
	 */
	CPP {
		@Override
		public String getExtension() {
			return ".cpp";
		}
		
		@Override
		public String getInitialContent() {
			// TODO Auto-generated method stub
			return "";
		}
	},
	
	/**
	 * An OOS file with the extension ".oos"
	 */
	OOS {
		@Override
		public String getExtension() {
			return ".oos";
		}
		
		@Override
		public String getInitialContent() {
			// TODO Auto-generated method stub
			return "";
		}
	},
	
	/**
	 * A SQDev specific file type
	 */
	SQDEV {
		private String initialContent;
		
		@Override
		public String getExtension() {
			return ".sqdev";
		}
		
		@Override
		public void create(String name, boolean open) {
			// check that the given name is one of the valid types
			Assert.isTrue(ESQDevFileType.contains(name));
			
			setInitialContent(ESQDevFileType.resolve(name));
			
			super.create(name, open);
		}
		
		private void setInitialContent(ESQDevFileType file) {
			initialContent = file.getInitialInput();
		}
		
		@Override
		public String getInitialContent() {
			return initialContent;
		}
	};
	
	/**
	 * The path where the file should be created
	 */
	private String path;
	/**
	 * The valid fileName including the respective extension
	 */
	private String fileName;
	/**
	 * The file that has been created
	 */
	private IFile file;
	
	
	/**
	 * Creates a file of the selected type with the given name. The newly
	 * created file will be opened afterwards
	 * 
	 * @param name
	 *            The name of the new file (<b>without extension!</b>)<br>
	 *            Will be overriden in {@linkplain #EXT} and {@linkplain #SQM};
	 *            <br>
	 *            For {@linkplain #SQDEV} it has to be of type
	 *            {@link ESQDevFileType}
	 * @see ESQDevFileType
	 */
	public void create(String name) {
		create(name, true);
	}
	
	/**
	 * Creates a file of the selected type with the given name
	 * 
	 *@param name
	 *            The name of the new file (<b>without extension!</b>)<br>
	 *            Will be overriden in {@linkplain #EXT} and {@linkplain #SQM};
	 *            <br>
	 *            For {@linkplain #SQDEV} it has to be of type
	 *            {@link ESQDevFileType}
	 * @param open
	 *            Indicates whether the file should be dirctly opened in the
	 *            respective editor
	 *            @see ESQDevFileType
	 */
	public void create(String name, boolean open) {
		AtomicReference<Display> display = new AtomicReference<Display>(Display.getCurrent());
		
		// create the job for creating the new file
		Job creationJob = new Job("Creating file...") {
			
			@Override
			public IStatus run(IProgressMonitor monitor) {
				try {
					// store the fileName
					setFileName(name);
					
					// state which file is being created
					monitor.beginTask("Creating file " + getFileName() + "...", 1);
					
					// get the container where the file should be created
					IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
					IResource resource = root.findMember(getPath(display.get()));
					
					if (resource instanceof IContainer) {
						IContainer container = (IContainer) resource;
						
						// get the file that has to be created
						IFile file = container.getFile(new Path(getFileName()));
						
						if (!file.exists()) {
							try {
								// create the file with the initial content
								// (if it does not yet exist)
								file.create(getInitialContentStream(), true, monitor);
								
								// update progress monitor
								monitor.worked(1);
							} catch (CoreException e) {
								e.printStackTrace();
								try {
									throw new FailedAtCreatingFileException(e);
								} catch (FailedAtCreatingFileException e1) {
									e1.printStackTrace();
								}
							}
						} else {
							// report that the file does already exist (in UI
							// thread)
							display.get().asyncExec(new Runnable() {
								
								@Override
								public void run() {
									MessageBox box1 = new MessageBox(
											Display.getCurrent().getActiveShell(), SWT.ERROR);
									box1.setMessage("Couldn't create file \"" + getFileName()
											+ "\" because it already exists!");
									box1.setText("File does already exists");
									
									box1.open();
								}
							});
						}
						
						// store the file
						setFile(file);
					} else {
						try {
							throw new FailedAtCreatingFileException(
									"Specified resource is not a container!");
						} catch (FailedAtCreatingFileException e) {
							e.printStackTrace();
						}
					}
					
					if (open) {
						// open the file (in the UI thread)
						display.get().asyncExec(new Runnable() {
							
							@Override
							public void run() {
								try {
									// open the editor on the created file
									IWorkbenchPage page = PlatformUI.getWorkbench()
											.getActiveWorkbenchWindow().getActivePage();
											
									IEditorPart part = IDE.openEditor(page, file, true);
									
									if (part instanceof ITextEditor) {
										// set caret position
										ITextEditor editor = (ITextEditor) part;
										editor.selectAndReveal(getInitialCaretOffset(), 0);
									}
								} catch (PartInitException e) {
								}
							}
						});
					}
					
					monitor.done();
					return Status.OK_STATUS;
					
				} catch (Exception e) {
					monitor.done();
					
					// return error status
					return new Status(IStatus.ERROR, "raven.sqdev.util", "Failed");
				}
			}
		};
		
		creationJob.schedule();
	}
	
	/**
	 * Gets the extension of this fileType
	 * 
	 * @return The extension (including the dot)
	 */
	public abstract String getExtension();
	
	/**
	 * Gets the initial content of the file that will be created
	 * 
	 * @return
	 */
	public abstract String getInitialContent();
	
	/**
	 * Gets the initial content of this file
	 * 
	 * @return The inputstream with the respective content
	 */
	protected ByteArrayInputStream getInitialContentStream() {
		return new ByteArrayInputStream(getInitialContent().getBytes());
	}
	
	/**
	 * Gets the offset of the caret that causes the caret to be at the end of
	 * the content of the newly created file
	 * 
	 * @return The caret offset
	 */
	public int getInitialCaretOffset() {
		return getInitialContent().length();
	}
	
	/**
	 * Gets the path where the file will be created -> The path of the container
	 * <br>
	 * If not specified this method will try to get the path of a suitable
	 * container of a selected resource in the package explorer.
	 * 
	 * @return
	 */
	public IPath getPath(Display display) {
		final AtomicReference<IPath> result = new AtomicReference<IPath>();
		
		// run in UI thread because it may depend on UI elements
		display.syncExec(new Runnable() {
			
			public void run() {
				
				if (path == null || path.isEmpty()) {
					// try to resolve path according to the selected resource in
					// the
					// package explorer
					IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
					ISelectionService service = window.getSelectionService();
					IStructuredSelection structured = (IStructuredSelection) service
							.getSelection("org.eclipse.jdt.ui.PackageExplorer");
							
					// get the selected resource
					Object obj = structured.getFirstElement();
					
					if (obj instanceof IResource && obj != null) {
						IContainer container;
						
						if (obj instanceof IContainer) {
							container = (IContainer) obj;
						} else {
							container = ((IResource) obj).getParent();
						}
						
						path = container.getFullPath().toString();
					}
				}
				
				// validate that there is a path to use
				if (path == null || path.isEmpty()) {
					try {
						throw new FailedAtCreatingFileException("No path given!");
					} catch (FailedAtCreatingFileException e) {
						e.printStackTrace();
						
						// can't procede -> kill process
						throw new RuntimeException(e);
					}
				}
				
				IPath detectedPath = new Path(path);
				
				IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
				
				if (root.getRawLocation().makeAbsolute().isPrefixOf(detectedPath)) {
					// make path relative to the workspace
					detectedPath = detectedPath
							.makeRelativeTo(root.getRawLocation().makeAbsolute());
				}
				
				// check that the container referenced by this path exists
				if (root.findMember(detectedPath) == null) {
					try {
						throw new FailedAtCreatingFileException(
								"Couldn't find specified container in the workspace!");
					} catch (FailedAtCreatingFileException e) {
						e.printStackTrace();
						
						// can't procede -> kill process
						throw new RuntimeException(e);
					}
				}
				
				// pass value to the respective AtomicReference
				result.set(detectedPath);
			}
		});
		
		return result.get();
	}
	
	/**
	 * Sets the path of the container in which this file should get created
	 * 
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	/**
	 * Get the valid fileName (with extension)
	 * 
	 * @return
	 */
	protected String getFileName() {
		Assert.isNotNull(fileName);
		return fileName;
	}
	
	/**
	 * Sets the fileName (adds the extension if necessary
	 * 
	 * @param fileName
	 *            The valid fileName
	 */
	protected void setFileName(String fileName) {
		this.fileName = (fileName.endsWith(getExtension())) ? fileName : fileName + getExtension();
	}
	
	protected IFile getFile() {
		return file;
	}
	
	protected void setFile(IFile file) {
		this.file = file;
	}
}
