package raven.sqdev.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import raven.sqdev.misc.FileUtil;
import raven.sqdev.misc.Marker;
import raven.sqdev.misc.SQDevInfobox;
import raven.sqdev.parser.misc.ParseResult;
import raven.sqdev.parser.misc.ParseUtil;
import raven.sqdev.parser.preprocessor.PreprocessorErrorListener;
import raven.sqdev.parser.preprocessor.PreprocessorLexer;
import raven.sqdev.parser.preprocessor.PreprocessorParseListener;
import raven.sqdev.parser.preprocessor.PreprocessorParseResult;
import raven.sqdev.parser.preprocessor.PreprocessorParser;
import raven.sqdev.parser.sqf.SQFLexer;
import raven.sqdev.parser.sqf.SQFParseInformation;
import raven.sqdev.parser.sqf.SQFParseResult;
import raven.sqdev.parser.sqf.SQFParser;
import raven.sqdev.parser.sqf.SQFValidator;

public class SQDevProjectBuilder extends IncrementalProjectBuilder {

	@Override
	protected IProject[] build(int kind, Map<String, String> args, IProgressMonitor monitor)
			throws CoreException {

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
	protected void incrementalBuild(IProgressMonitor monitor)
			throws FileNotFoundException, IOException, CoreException {
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
	private void parseChangedFiles(IResourceDelta delta)
			throws FileNotFoundException, IOException, CoreException {
		for (IResourceDelta currentResourceDelta : delta.getAffectedChildren(IResourceDelta.CHANGED,
				IResource.FILE)) {
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
	protected void fullBuild(IProgressMonitor monitor)
			throws CoreException, FileNotFoundException, IOException {
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
		
		if(!file.getName().equals("RealScriptTester.sqf")) {
			return;
		}
		
		System.out.println("Parsing " + file.getName());

		final String fileContent = FileUtil.readAll(new FileInputStream(file.getLocation().toFile()));

		PreprocessorParseResult prepResult = ParseUtil.parseAndValidatePreprocess(fileContent,
				file.getLocation());

		System.out.println("\tFinished preprocessing - " + System.currentTimeMillis());

		SQFParseInformation info = new SQFParseInformation(prepResult.getMacros());
		
		SQFParseResult sqfResult = ParseUtil.parseSQF(fileContent, info);

		System.out.println("\tFinished parsing - " + System.currentTimeMillis());

		sqfResult
				.mergeWith(ParseUtil.validateSQF(sqfResult.getParseTree(), sqfResult.getTokenStream(), info));

		sqfResult.mergeWith(prepResult);

		System.out.println("\tFinished validating - " + System.currentTimeMillis());

		// clear old markers
		file.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);

		// apply markers
		for (Marker currentMarker : sqfResult.getMarkers()) {
			// find line
			int line = 1;
			for (int i = 0; i < currentMarker.getOffset(); i++) {
				if (fileContent.charAt(i) == '\n') {
					line++;
				}
			}

			IMarker fileMarker = file.createMarker(currentMarker.getType());
			fileMarker.setAttribute(IMarker.LINE_NUMBER, line);
			fileMarker.setAttribute(IMarker.MESSAGE, currentMarker.getMessage());
			fileMarker.setAttribute(IMarker.SEVERITY, currentMarker.getSeverity());
			fileMarker.setAttribute(IMarker.CHAR_START, currentMarker.getOffset());
			fileMarker.setAttribute(IMarker.CHAR_END, currentMarker.getOffset() + currentMarker.getLength());
		}
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
