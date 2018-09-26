package raven.sqdev.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import dataStructures.AbstractSQFTokenFactory;
import raven.sqdev.interfaces.IParseResult;
import raven.sqdev.interfaces.ISQFParseSupplier;
import raven.sqdev.interfaces.ITreeProcessingResult;
import raven.sqdev.misc.Macro;
import raven.sqdev.misc.Marker;
import raven.sqdev.misc.SQDevInfobox;
import raven.sqdev.parser.misc.ParseUtil;
import raven.sqdev.parser.misc.SQFParseResult;
import raven.sqdev.parser.misc.SQFTokenFactory;
import raven.sqdev.parser.preprocessor.PreprocessorParseResult;
import raven.sqdev.parser.sqf.SQFInformation;

public class SQDevProjectBuilder extends IncrementalProjectBuilder {
	
	public static final String ID = "raven.sqdev.builder.sqdevprojectbuilder";

	@Override
	protected IProject[] build(int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {

		try {
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
		} catch (IOException e) {
			e.printStackTrace();

			SQDevInfobox info = new SQDevInfobox("Errors during project building", e);
			info.open(false);
		}

		return null;
	}

	/**
	 * Performs an incremental build of the project. This method relies on
	 * {@link #getDelta(IProject)}
	 * 
	 * @param monitor
	 *            The <code>IProgressmonitor</code> to report progress to
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws CoreException
	 */
	protected void incrementalBuild(IProgressMonitor monitor) throws FileNotFoundException, IOException, CoreException {
		monitor.beginTask("Building " + getProject().getName(), IProgressMonitor.UNKNOWN);

		IResourceDelta delta = getDelta(getProject());

		// parse changed files only
		parseChangedFiles(delta);

		monitor.done();
	}

	/**
	 * Parses the changed files in the given delta
	 * 
	 * @param delta
	 *            The delta to process
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws CoreException
	 */
	private void parseChangedFiles(IResourceDelta delta) throws FileNotFoundException, IOException, CoreException {
		for (IResourceDelta currentResourceDelta : delta.getAffectedChildren(IResourceDelta.CHANGED, IResource.FILE)) {
			if (currentResourceDelta.getResource() instanceof IFile) {
				parseSQFFile((IFile) currentResourceDelta.getResource());
			} else {
				parseChangedFiles(currentResourceDelta);
			}
		}
	}

	/**
	 * Performs a full build of the project.
	 * 
	 * @param monitor
	 *            The <code>IProgressmonitor</code> to report progress to
	 * @throws CoreException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	protected void fullBuild(IProgressMonitor monitor) throws CoreException, FileNotFoundException, IOException {
		List<IResource> files = getProjectChildren(IResource.FILE);

		monitor.beginTask("Building project " + getProject().getName(), files.size());

		for (IResource currentResource : files) {
			if (monitor.isCanceled()) {
				break;
			}

			parseSQFFile((IFile) currentResource);
			monitor.worked(1);
		}

		monitor.done();
	}

	/**
	 * Parses the given SQF file. If the given file is not a SQF file this method
	 * returns without doing anything
	 * 
	 * @param file
	 *            The SQF file to parse
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws CoreException
	 */
	protected void parseSQFFile(IFile file) throws FileNotFoundException, IOException, CoreException {
		if (file.getFileExtension() == null || !file.getFileExtension().toLowerCase().equals("sqf")) {
			// Only parse SQF files
			return;
		}

		System.out.println("Parsing " + file.getName());

		FileInputStream fileStream = new FileInputStream(file.getLocation().toFile());

		PreprocessorParseResult prepResult = ParseUtil.parseAndValidatePreprocess(fileStream, file.getLocation());

		// "reset" InputStream
		fileStream = new FileInputStream(file.getLocation().toFile());

		// create parse information with default values
		SQFInformation info = new SQFInformation(prepResult.getMacros());

		IParseResult parseResult = ParseUtil.parseSQF(fileStream, new ISQFParseSupplier() {

			@Override
			public AbstractSQFTokenFactory getTokenFactory() {
				return new SQFTokenFactory(info.getBinaryKeywords(), info.getUnaryKeywords());
			}

			@Override
			public Map<String, Macro> getMacros() {
				return prepResult.getMacros();
			}
		});

		// clear old markers
		file.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);

		boolean foundError = false;

		// apply parse- and lex-markers
		for (Marker currentMarker : parseResult.getMarkers()) {
			addMarker(file, currentMarker, parseResult.getLine(currentMarker.getOffset()));
			if (currentMarker.getSeverity() == IMarker.SEVERITY_ERROR) {
				foundError = true;
			}
		}

		if (foundError) {
			// don't continue if there have been any errors so far
			return;
		}

		ITreeProcessingResult processingResult = ParseUtil.processSQF((SQFParseResult) parseResult, info);

		// apply processing-markers
		for (Marker currentMarker : processingResult.getMarkers()) {
			addMarker(file, currentMarker, parseResult.getLine(currentMarker.getOffset()));
		}
	}

	private void addMarker(IFile file, Marker marker, int line) throws CoreException {
		IMarker fileMarker = file.createMarker(marker.getType());
		fileMarker.setAttribute(IMarker.LINE_NUMBER, line);
		fileMarker.setAttribute(IMarker.MESSAGE, marker.getMessage());
		fileMarker.setAttribute(IMarker.SEVERITY, marker.getSeverity());
		fileMarker.setAttribute(IMarker.CHAR_START, marker.getOffset());
		fileMarker.setAttribute(IMarker.CHAR_END, marker.getOffset() + marker.getLength());
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
			if (type == IResource.NONE || (currentResource.getType() & type) == type) {
				resources.add(currentResource);
			}

			if (currentResource instanceof IFolder) {
				for (IResource currentInnerResource : getAllChildrenOf((IFolder) currentResource)) {
					if (type == IResource.NONE || (currentInnerResource.getType() & type) == type) {
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
	private List<IResource> getAllChildrenOf(IFolder folder) throws CoreException {
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
