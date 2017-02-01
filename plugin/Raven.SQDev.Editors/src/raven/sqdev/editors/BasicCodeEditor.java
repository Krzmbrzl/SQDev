package raven.sqdev.editors;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.ITextViewerExtension;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.DefaultCharacterPairMatcher;
import org.eclipse.jface.text.source.ICharacterPairMatcher;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.ISourceViewerExtension2;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionAnnotation;
import org.eclipse.jface.text.source.projection.ProjectionSupport;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;

import raven.sqdev.constants.SQDevPreferenceConstants;
import raven.sqdev.editors.parser.preprocessor.PreprocessorErrorListener;
import raven.sqdev.editors.parser.preprocessor.PreprocessorLexer;
import raven.sqdev.editors.parser.preprocessor.PreprocessorParseListener;
import raven.sqdev.editors.parser.preprocessor.PreprocessorParser;
import raven.sqdev.exceptions.SQDevEditorException;
import raven.sqdev.interfaces.IManager;
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
public class BasicCodeEditor extends TextEditor {
	
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
	 * The name of the rules used for parsing this editor's input
	 */
	protected List<String> parseRuleNames;
	
	/**
	 * The document provider of this editor
	 */
	protected BasicDocumentProvider provider;
	/**
	 * A list of <code>IManager</code> working on this editor
	 */
	protected List<IManager> managerList;
	/**
	 * A list of character pairs that should be used in this editor
	 */
	protected List<CharacterPair> characterPairs;
	
	public BasicCodeEditor() {
		super();
		
		setColorManager(new ColorManager());
		setEditorKeyEventQueue(new EditorKeyEventQueue());
		
		this.setSourceViewerConfiguration(getBasicConfiguration());
		this.setDocumentProvider(getBasicProvider());
		
		managerList = new ArrayList<IManager>();
		characterPairs = getCharacterPairs();
		
		// add a implementation for the autoCompletion of pairing characters
		configureCharacterPairHandler();
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
	public ISourceViewer createSourceViewer(Composite parent,
			IVerticalRuler ruler, int styles) {
		ISourceViewer viewer = new ProjectionViewer(parent, ruler,
				getOverviewRuler(), isOverviewRulerVisible(), styles);
		
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
	protected void configureSourceViewerDecorationSupport(
			SourceViewerDecorationSupport support) {
		super.configureSourceViewerDecorationSupport(support);
		
		char[] matchChars = { '(', ')', '[', ']', '{', '}' }; // which brackets
																// to match
		ICharacterPairMatcher matcher = new DefaultCharacterPairMatcher(
				matchChars, IDocumentExtension3.DEFAULT_PARTITIONING, true);
		
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
	
	public void setEditorKeyEventQueue(
			EditorKeyEventQueue editorKeyEventQueue) {
		this.editorKeyEventQueue = editorKeyEventQueue;
	}
	
	/**
	 * Adds the configured <code>CharacterPairs</code> as a
	 * <code>CharacterPairHandler</code> to this editor.<br>
	 * If you want to change the pairs you have to override
	 * {@link #getCharacterPairs()}
	 * 
	 * @see CharacterPairHandler
	 * @see CharacterPair
	 */
	protected void configureCharacterPairHandler() {
		CharacterPairHandler pairHandler = new CharacterPairHandler(this);
		
		for (CharacterPair currentPair : getConfiguredCharacterPairs()) {
			pairHandler.addPair(currentPair);
		}
		
		getEditorKeyEventQueue().queueEditorKeyHandler(pairHandler);
	}
	
	/**
	 * Gets the <code>CharacterPairs</code> that should be used by this editor
	 * 
	 * @return A <code>List</code> of CharacterPairs
	 */
	protected List<CharacterPair> getCharacterPairs() {
		List<CharacterPair> pairList = new ArrayList<CharacterPair>();
		
		pairList.add(CharacterPair.DOUBLE_QUOTATION_MARKS);
		pairList.add(CharacterPair.SINGLE_QUOTATION_MARKS);
		pairList.add(CharacterPair.ROUND_BRACKETS);
		pairList.add(CharacterPair.SQUARE_BRACKETS);
		pairList.add(CharacterPair.CURLY_BRACKETS);
		
		return pairList;
	}
	
	/**
	 * Gets a list of all configured character pairs from this editor
	 */
	public List<CharacterPair> getConfiguredCharacterPairs() {
		if (characterPairs == null) {
			characterPairs = new ArrayList<CharacterPair>(0);
		}
		
		return characterPairs;
	}
	
	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		
		// infrastructure for code folding
		ProjectionViewer viewer = (ProjectionViewer) getSourceViewer();
		
		ProjectionSupport projectionSupport = new ProjectionSupport(viewer,
				getAnnotationAccess(), getSharedColors());
		
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
		
		createManagers(managerList);
		
		// parse the input for the first time
		parseInput();
	}
	
	/**
	 * Updates the editor. Needed when some changes are made to the way the
	 * editor content should be displayed or when the behaviour of the editor
	 * should change.<br>
	 * <br>
	 * <b>Note:</b> This method can be called from any Thread
	 * 
	 * @param reconfigureSourceViewer
	 *            Whether it is necessary to reconfigure the sourceVieweer
	 */
	public void update(boolean reconfigureSourceViewer) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				if (getSourceViewer() != null) {
					getSourceViewer().invalidateTextPresentation();
					
					if ((getSourceViewer() instanceof ISourceViewerExtension2)
							&& reconfigureSourceViewer) {
						// reconfigure the SourceViewer
						((ISourceViewerExtension2) getSourceViewer())
								.unconfigure();
						getSourceViewer().configure(getBasicConfiguration());
					}
				}
			}
		});
	}
	
	/**
	 * Gets the <code>BasicSourceViewerConfiguration</code> of this editor
	 * 
	 * @see {@linkplain BasicSourceViewerConfiguration}
	 */
	public BasicSourceViewerConfiguration getBasicConfiguration() {
		if (configuration == null) {
			configuration = new BasicSourceViewerConfiguration(
					getColorManager(), this);
		}
		
		return configuration;
	}
	
	/**
	 * Gets the <code>BasicDocumentProvider</code> of this editor
	 * 
	 * @see {@linkplain BasicDocumentProvider}
	 */
	public BasicDocumentProvider getBasicProvider() {
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
	 * Gets the names of the rules used for parsing this editor's input
	 * 
	 * @return The default implementation returns <code>null</code>
	 */
	public List<String> getParseRuleNames() {
		return parseRuleNames;
	}
	
	/**
	 * Parses the input of this editor, updates the parseTree and sends it to
	 * the {@link #processParseTree(ParseTree)} method automatically. Before
	 * doing so it will call the preprocessor parser via
	 * {@link #doPreprocessorParsing(String)}. If you need to specify a custom
	 * preprocessor parser or disable it you have to overwrite that method.
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
		
		// preprocess
		doPreprocessorParsing(input);
		
		long time = Calendar.getInstance().getTimeInMillis();
		
		// parse
		ParseTree output = doParse(input);
		
		System.out.println("Parse time: "
				+ (Calendar.getInstance().getTimeInMillis() - time));
		
		if (output == null) {
			applyParseChanges();
			
			return false;
		} else {
			parseTree = output;
			
			if (!processParseTree(parseTree)) {
				applyParseChanges();
			}
			
			return true;
		}
	}
	
	/**
	 * A default implementation of a preprocessor parser that parses the input
	 * first and sets the found macros if this editor is an instance of
	 * <code>IMacroSupport</code>.
	 */
	protected void doPreprocessorParsing(String input) {
		if (this instanceof IMacroSupport) {
			ANTLRInputStream prepIn = new ANTLRInputStream(input);
			
			PreprocessorLexer prepLexer = new PreprocessorLexer(prepIn);
			
			CommonTokenStream prepTokens = new CommonTokenStream(prepLexer);
			
			PreprocessorParser prepParser = new PreprocessorParser(prepTokens);
			
			prepParser.removeErrorListeners();
			prepParser.addErrorListener(new PreprocessorErrorListener(this, 0));
			
			ParseTreeWalker prepWalker = new ParseTreeWalker();
			
			PreprocessorParseListener preprocessorListener = new PreprocessorParseListener(
					this);
			
			prepWalker.walk(preprocessorListener, prepParser.start());
			
			((IMacroSupport) this)
					.setMacros(preprocessorListener.getDefinedMacros(), true);
		}
	}
	
	/**
	 * Processes whatever needs to be processed when the ParseTree has changed
	 * <br>
	 * Note: You might want to call {@link #applyParseChanges()} after the
	 * processing
	 * 
	 * @param tree
	 *            The generated tree
	 * @return Whether this function has called {@link #applyParseChanges()}. If
	 *         not the default implementation of {@link #parseInput()} will call
	 *         this function afterwards.
	 */
	protected boolean processParseTree(ParseTree parseTree) {
		return false;
	}
	
	/**
	 * Parses the input of this editor in order to set the {@link #parseTree}
	 * for this editor. <br>
	 * It is recommended to do the parsing in an extra thread<br>
	 * Note: You might want to call {@link #applyParseChanges()} after parsing
	 * (or rather after {@link #processParseTree(ParseTree)}.<br>
	 * Not that before this method is called
	 * {@link #doPreprocessorParsing(String)} gets called. If you don't want to
	 * use the default preprocessor parsing strategy you have to overwrite that
	 * method.
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
	 * Creates all managers that should work on this editor
	 * 
	 * @param managerList
	 *            The list of managers. The newly created ones have to be added
	 *            to this list
	 */
	protected void createManagers(List<IManager> managerList) {
		// add folding manager
		managerList.add(
				new BasicFoldingManager(((ProjectionViewer) getSourceViewer())
						.getProjectionAnnotationModel()));
		// add marker manager
		managerList.add(new BasicMarkerManager(this));
	}
	
	/**
	 * Creates a problem marker that will be visible in the editor.<br>
	 * Note: Can only be called if {@link #getEditorInput()} does not return
	 * null and is of type {@linkplain IFileEditorInput}
	 * 
	 * @param type
	 *            The marker type
	 * @param offset
	 *            The offset of this marker area
	 * @param length
	 *            The length of the marker area
	 * @param message
	 *            The message of this marker
	 * @param severity
	 *            The severity of the marker (has to be one specified by
	 *            <code>IMarker</code>)
	 */
	public void createMarker(String type, int offset, int length,
			String message, int severity) {
		if (getEditorInput() == null) {
			return;
		}
		
		int line;
		try {
			line = getBasicProvider().getDocument(getEditorInput())
					.getLineOfOffset(offset);
		} catch (BadLocationException e) {
			try {
				throw new SQDevEditorException("Can't create marker", e);
			} catch (SQDevEditorException e1) {
				e1.printStackTrace();
				
				return;
			}
		}
		
		((BasicMarkerManager) getManager(BasicMarkerManager.TYPE))
				.addMarker(type, line, offset, length, severity, message);
	}
	
	@Override
	public void doSave(IProgressMonitor progressMonitor) {
		super.doSave(progressMonitor);
		
		// reparse on save
		parseInput();
	}
	
	/**
	 * Applies the changes detected by the parsing by notifying the respective
	 * managers to apply their work
	 */
	public void applyParseChanges() {
		for (IManager manager : managerList) {
			manager.apply();
		}
	}
	
	/**
	 * Gets a manager working on this editor of the given type
	 * 
	 * @param type
	 *            The type of the editor
	 * @return The respective editor or <code>null</code> if none could be found
	 */
	public IManager getManager(String type) {
		for (IManager manager : managerList) {
			if (manager.getType().equals(type)) {
				return manager;
			}
		}
		
		return null;
	}
	
	/**
	 * Adds a foldable area to the editor if a {@link BasicFoldingManager} has
	 * been installed.<br>
	 * In order of the changes to take effect {@link #applyParseChanges()} has
	 * to be called
	 * 
	 * @param position
	 *            The <code>Position</code> this area should be on
	 */
	public void addFoldingArea(Position position) {
		ProjectionAnnotation annotation = new ProjectionAnnotation();
		
		BasicFoldingManager foldingManager = (BasicFoldingManager) getManager(
				BasicFoldingManager.getManagerType());
		
		if (foldingManager == null) {
			return;
		}
		
		foldingManager.addFoldingArea(
				new AbstractMap.SimpleEntry<ProjectionAnnotation, Position>(
						annotation, position));
	}
	
	/**
	 * Checks whether this editor is in a valid state (no errors in the source
	 * code)
	 */
	public boolean isValid() {
		return ((BasicMarkerManager) getManager(BasicMarkerManager.TYPE))
				.isValidState();
	}
}
