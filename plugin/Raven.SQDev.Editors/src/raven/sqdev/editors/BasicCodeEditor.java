package raven.sqdev.editors;

import org.antlr.v4.runtime.tree.ParseTree;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.ITextViewerExtension;
import org.eclipse.jface.text.source.DefaultCharacterPairMatcher;
import org.eclipse.jface.text.source.ICharacterPairMatcher;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.ISourceViewerExtension2;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionSupport;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;

import raven.sqdev.constants.SQDevPreferenceConstants;
import raven.sqdev.exceptions.SQDevCoreException;
import raven.sqdev.misc.CharacterPair;
import raven.sqdev.misc.MultiPreferenceStore;
import raven.sqdev.util.SQDevPreferenceUtil;

/***
 * A default implementation of a code editor. This contains the autoCompletion
 * for some basic <code>CharacterPairs</code>. If you want to change those you
 * have to override addCharacterPairHandler().<br>
 * Also this editor provides an <code>EditorKeyEventQueue</code> that can be
 * used.<br>
 * Furthermore it adds an CharacterPairMatcher that will highlight corresponding
 * pairs when selected. It also installs the PreferenceStore of
 * <code>raven.sqdev.preferences</code>.
 * 
 * @author Raven
 * 
 * @see {@linkplain CharacterPairHandler}
 * @see {@linkplain CharacterPair}
 * @see {@linkplain EditorKeyEventQueue}
 * @see {@linkplain EditorKeyEventManager}
 * 
 */
public abstract class BasicCodeEditor extends TextEditor {
	
	/**
	 * The color manager
	 */
	protected ColorManager colorManager;
	
	/**
	 * The queue for the keyEvents
	 */
	protected EditorKeyEventQueue editorKeyEventQueue;
	
	/**
	 * The source viewer configuration for this editor
	 */
	protected BasicSourceViewerConfiguration configuration;
	
	/**
	 * The parse tree representing the input of this editor
	 */
	protected ParseTree parseTree;
	
	/**
	 * The document provider of this editor
	 */
	protected BasicDocumentProvider provider;
	
	public BasicCodeEditor() {
		super();
		
		setColorManager(new ColorManager());
		setEditorKeyEventQueue(new EditorKeyEventQueue());
		
		// add a implementation for the autoCompletion of pairing characters
		addCharacterPairHandler();
		
		this.setSourceViewerConfiguration(getBasicConfiguration());
		this.setDocumentProvider(getBasicProvider());
	}
	
	@Override
	public void dispose() {
		this.getColorManager().dispose();
		super.dispose();
	}
	
	public ColorManager getColorManager() {
		return colorManager;
	}
	
	public void setColorManager(ColorManager colorManager) {
		this.colorManager = colorManager;
	}
	
	@Override
	public ISourceViewer createSourceViewer(Composite parent, IVerticalRuler ruler, int styles) {
		ISourceViewer viewer = new ProjectionViewer(parent, ruler, getOverviewRuler(),
				isOverviewRulerVisible(), styles);
		
		getSourceViewerDecorationSupport(viewer);
		
		if (viewer instanceof ITextViewerExtension) {
			EditorKeyEventManager manager = new EditorKeyEventManager();
			
			// associate the newly created manager with the EditorKeyEventQueue
			// of this editor
			getEditorKeyEventQueue().setManager(manager);
			
			((ITextViewerExtension) viewer).appendVerifyKeyListener(manager);
			
			// add listener that parses the input during typing
			((ITextViewerExtension) viewer)
					.appendVerifyKeyListener(new BasicParseTimeListener(this));
		}
		
		return viewer;
	}
	
	@Override
	protected void configureSourceViewerDecorationSupport(SourceViewerDecorationSupport support) {
		super.configureSourceViewerDecorationSupport(support);
		
		char[] matchChars = { '(', ')', '[', ']', '{', '}' }; // which brackets
																// to match
		ICharacterPairMatcher matcher = new DefaultCharacterPairMatcher(matchChars,
				IDocumentExtension3.DEFAULT_PARTITIONING, true);
		
		// character pair matching
		support.setCharacterPairMatcher(matcher);
		support.setMatchingCharacterPainterPreferenceKeys(
				SQDevPreferenceConstants.SQDEV_EDITOR_MATCHING_BRACKETS_KEY,
				SQDevPreferenceConstants.SQDEV_EDITOR_MATCHING_BRACKETS_COLOR_KEY);
		
		// newLine highlighting
		support.setCursorLinePainterPreferenceKeys(
				SQDevPreferenceConstants.SQDEV_EDITOR_HIGHLIGHT_CURRENTLINE_KEY,
				SQDevPreferenceConstants.SQDEV_EDITOR_HIGHLIGHT_CURRENTLINE_COLOR_KEY);
		
	}
	
	/**
	 * Gets the <code>EditorKeyEventQueue</code> of this editor.
	 * 
	 * @see EditorKeyEventQueue
	 */
	public EditorKeyEventQueue getEditorKeyEventQueue() {
		return editorKeyEventQueue;
	}
	
	public void setEditorKeyEventQueue(EditorKeyEventQueue editorKeyEventQueue) {
		this.editorKeyEventQueue = editorKeyEventQueue;
	}
	
	/**
	 * Adds a default implementation of <code>CharacterPairHandler</code>
	 * including <b>all</b> pairs defined in <code>CharacterPair</code><br>
	 * <br>
	 * If you want a different <code>CharacterPairHandler</code> override this
	 * method and provide your own CharacterPairHandler and add it to the
	 * <code>EditorKeyEventQueue</code> of <code>BasicTextEditor</code>
	 * 
	 * @see CharacterPairHandler
	 * @see CharacterPair
	 */
	public void addCharacterPairHandler() {
		CharacterPairHandler pairHandler = new CharacterPairHandler(this);
		
		// TODO: make all completions optional via preference
		pairHandler.addPair(CharacterPair.DOUBLE_QUOTATION_MARKS);
		pairHandler.addPair(CharacterPair.SINGLE_QUOTATION_MARKS);
		pairHandler.addPair(CharacterPair.ROUND_BRACKETS);
		pairHandler.addPair(CharacterPair.SQUARE_BRACKETS);
		pairHandler.addPair(CharacterPair.CURLY_BRACKETS);
		
		getEditorKeyEventQueue().queueEditorKeyHandler(pairHandler);
	}
	
	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		
		// infrastructure for code folding
		ProjectionViewer viewer = (ProjectionViewer) getSourceViewer();
		
		ProjectionSupport projectionSupport = new ProjectionSupport(viewer, getAnnotationAccess(),
				getSharedColors());
		
		projectionSupport.install();
		
		// turn projection mode on
		viewer.doOperation(ProjectionViewer.TOGGLE);
		
		
		if (fSourceViewerDecorationSupport != null) {
			// combine the SQDev PreferenceStore with the editor's one
			
			// use the SQDev preferenceStore as the baseStore
			MultiPreferenceStore multiStore = new MultiPreferenceStore(
					SQDevPreferenceUtil.getPreferenceStore());
			
			// add the editor's preferenceStore if available
			IPreferenceStore editorStore = this.getPreferenceStore();
			if (editorStore != null) {
				multiStore.addPreferenceStore(editorStore);
			}
			
			fSourceViewerDecorationSupport.install(multiStore);
		}
		
		// parse the input for the first time
		parseInput();
	}
	
	/**
	 * Updates the editor. Needed when some changes are made to the way the
	 * editor content should be displayed or when the behaviour of the editor
	 * should change
	 */
	public void update() {
		if (getSourceViewer() != null) {
			getSourceViewer().invalidateTextPresentation();
			
			if (getSourceViewer() instanceof ISourceViewerExtension2) {
				// reconfigure the SourceViewer
				((ISourceViewerExtension2) getSourceViewer()).unconfigure();
				getSourceViewer().configure(getBasicConfiguration());
			}
			
			System.out.println("Updated");
		}
	}
	
	/**
	 * Gets the <code>BasicSourceViewerConfiguration</code> of this editor
	 * 
	 * @see {@linkplain BasicSourceViewerConfiguration}
	 */
	protected BasicSourceViewerConfiguration getBasicConfiguration() {
		if (configuration == null) {
			configuration = new BasicSourceViewerConfiguration(getColorManager(), this);
		}
		
		return configuration;
	}
	
	/**
	 * Gets the <code>BasicDocumentProvider</code> of this editor
	 * 
	 * @see {@linkplain BasicDocumentProvider}
	 */
	protected BasicDocumentProvider getBasicProvider() {
		if (provider == null) {
			provider = new BasicDocumentProvider();
		}
		
		return provider;
	}
	
	/**
	 * Gets the <code>ParseTree</code> representing the input of this editor
	 * 
	 * @return The <code>ParseTree</code> or <code>null</code> if none has been
	 *         set so far
	 */
	public ParseTree getParseTree() {
		return parseTree;
	}
	
	/**
	 * Parses the input of this editor, updates the parseTree and sends it to
	 * the {@link #processParseTree(ParseTree)} method automatically
	 * 
	 * @return <code>True</code> if the parsing could be done successfully and
	 *         <code>False</code> otherwise
	 */
	public boolean parseInput() {
		if (getEditorInput() == null) {
			return false;
		}
		
		IDocument document = getBasicProvider().getDocument(getEditorInput());
		
		if (document == null) {
			return false;
		}
		
		String input = document.get();
		
		if (input == null) {
			return false;
		}
		
		ParseTree output = doParse(input);
		
		if (output == null) {
			return false;
		} else {
			parseTree = output;
			
			processParseTree(parseTree);
			
			return true;
		}
	}
	
	/**
	 * Processes whatever needs to be processed when the ParseTree has cahnged
	 * 
	 * @param tree
	 *            The generated tree
	 */
	protected void processParseTree(ParseTree parseTree) {
	}
	
	/**
	 * Parses the input of this editor in order to set the {@link #parseTree}
	 * for this editor. <br>
	 * It is recommended to do the parsing in an extra thread
	 * 
	 * @param input
	 *            The input to parse
	 * 
	 * @return The resulting <code>ParseTree</code> or <code>null</code> if the
	 *         parsing failed (if not overridden by subclasses this method
	 *         always returns <code>null</code>
	 */
	protected ParseTree doParse(String input) {
		// parsing diabled
		return null;
	}
	
	/**
	 * Creates a problem marker that will be visible in the editor
	 * 
	 * @param offset
	 *            The offset of this marker area
	 * @param length
	 *            The length of the marker area
	 * @param message
	 *            The message of this marker
	 * @param severity
	 *            The severity of the marker (has to be one specified by
	 *            <code>IMarker</code>)
	 * @return The created marker or <code>null</code> if the marker couldn't be
	 *         created
	 */
	public IMarker createProblemMarker(int offset, int length, String message, int severity) {
		if (getEditorInput() == null || !(getEditorInput() instanceof IFileEditorInput)) {
			// can't access the editor input as needed
			return null;
		}
		
		Assert.isTrue(offset > 0 && length > 0);
		
		IDocument document = getBasicProvider().getDocument(getEditorInput());
		
		if (document == null) {
			return null;
		}
		
		if (offset + length > document.getLength()) {
			throw new SQDevCoreException(
					"The given offset and length do not correspond to the document!");
		}
		
		try {
			// create marker
			int line = document.getLineOfOffset(offset) + 1;
			
			IFile file = ((IFileEditorInput) getEditorInput()).getFile();
			
			IMarker marker = file.createMarker(IMarker.PROBLEM);
			marker.setAttribute(IMarker.LINE_NUMBER, line);
			marker.setAttribute(IMarker.MESSAGE, message);
			marker.setAttribute(IMarker.SEVERITY, severity);
			marker.setAttribute(IMarker.CHAR_START, offset);
			marker.setAttribute(IMarker.CHAR_END, offset + length);
			
			return marker;
		} catch (BadLocationException | CoreException e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
	public void doSave(IProgressMonitor progressMonitor) {
		super.doSave(progressMonitor);
		
		// reparse on save
		parseInput();
	}
}
