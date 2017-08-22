package raven.sqdev.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class SQDevProjectBuilder extends IncrementalProjectBuilder {
	
	public SQDevProjectBuilder() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected IProject[] build(int kind, Map<String, String> args,
			IProgressMonitor monitor) throws CoreException {
		
		switch (kind) {
			case INCREMENTAL_BUILD:
				incrementalBuild(monitor);
				break;
			case FULL_BUILD:
				fullBuild(monitor);
				break;
			case AUTO_BUILD:
				if (getDelta(getProject()) != null) {
					incrementalBuild(monitor);
				} else {
					fullBuild(monitor);
				}
				break;
		}
		
		return null;
	}
	
	/**
	 * Performs an incremental build of the project. This method relies on
	 * {@link #getDelta(IProject)}
	 * 
	 * @param monitor
	 *            The <code>IProgressmonitor</code> to report progress to
	 */
	protected void incrementalBuild(IProgressMonitor monitor) {
		IResourceDelta delta = getDelta(getProject());
		
		// parse changed files only
		for (IResourceDelta currentResourceDelta : delta
				.getAffectedChildren(IResourceDelta.CHANGED, IResource.FILE)) {
			parseSQFFile((IFile) currentResourceDelta.getResource());
		}
	}
	
	/**
	 * Performs a full build of the project.
	 * 
	 * @param monitor
	 *            The <code>IProgressmonitor</code> to report progress to
	 * @throws CoreException
	 */
	protected void fullBuild(IProgressMonitor monitor) throws CoreException {
		for (IResource currentResource : getProjectChildren(IResource.FILE)) {
			parseSQFFile((IFile) currentResource);
		}
	}
	
	/**
	 * Parses the given SQF file. If the given file is not a SQF file this
	 * method returns without doing anything
	 * 
	 * @param file
	 *            The SQF file to parse
	 */
	protected void parseSQFFile(IFile file) {
		if (file.getFileExtension() == null
				|| !file.getFileExtension().toLowerCase().equals("sqf")) {
			// Only parse SQF files
			return;
		}
		System.out.println("Parsing " + file.getName());
		// TODO: preprocess the file + parse it
	}
	
	/**
	 * Gets the children of this project of the given type.
	 * 
	 * @param type
	 *            The type of resource to retrieve. Use IResource.NONE if all
	 *            members should be retrieved
	 * @return A list of the respective resources
	 * @throws CoreException
	 */
	private List<IResource> getProjectChildren(int type) throws CoreException {
		List<IResource> resources = new ArrayList<IResource>();
		
		for (IResource currentResource : getProject().members()) {
			if (type == IResource.NONE
					|| (currentResource.getType() & type) == type) {
				resources.add(currentResource);
			}
			
			if (currentResource instanceof IFolder) {
				for (IResource currentInnerResource : getAllChildrenOf(
						(IFolder) currentResource)) {
					if (type == IResource.NONE
							|| (currentInnerResource.getType()
									& type) == type) {
						resources.add(currentInnerResource);
					}
				}
			}
		}
		
		return resources;
	}
	
	/**
	 * Gets all child resource in the given folder
	 * 
	 * @param folder
	 *            The folder whose children should be obtained
	 * @return A list of the respective children
	 * @throws CoreException
	 */
	private List<IResource> getAllChildrenOf(IFolder folder)
			throws CoreException {
		List<IResource> resources = new ArrayList<IResource>();
		
		for (IResource currentResource : folder.members()) {
			resources.add(currentResource);
			
			if (currentResource instanceof IFolder) {
				resources.addAll(getAllChildrenOf((IFolder) currentResource));
			}
		}
		
		return resources;
	}
	
}
