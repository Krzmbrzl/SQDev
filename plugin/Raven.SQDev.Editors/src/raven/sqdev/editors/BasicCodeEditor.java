package raven.sqdev.editors;

import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.ITextViewerExtension;
import org.eclipse.jface.text.source.DefaultCharacterPairMatcher;
import org.eclipse.jface.text.source.ICharacterPairMatcher;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;

import raven.sqdev.preferences.util.SQDevPreferenceConstants;
import raven.sqdev.preferences.util.SQDevPreferenceUtil;

/**
 * A default implementation of a code editor. This contains the autoCompletion
 * for some baisc <code>CharacterPairs</code>. If you want to change those you
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
		ISourceViewer viewer = super.createSourceViewer(parent, ruler, styles);
		
		if (viewer instanceof ITextViewerExtension) {
			EditorKeyEventManager manager = new EditorKeyEventManager();
			
			// associate the newly created manager with the EditorKeyEventQueue
			// of this editor
			getEditorKeyEventQueue().setManager(manager);
			
			((ITextViewerExtension) viewer).appendVerifyKeyListener(manager);
		}
		
		return viewer;
	}
	
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
		CharacterPairHandler pairHandler = new CharacterPairHandler();
		
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
		
		if (fSourceViewerDecorationSupport != null) {
			// set the plugin's shared preference store instead of the default
			// one
			fSourceViewerDecorationSupport.install(SQDevPreferenceUtil.getPreferenceStore());
		}
	}
	
	/**
	 * Updates the editor. Needed when some changes are made to the way the
	 * editor content should be displayed
	 */
	public void update() {
		if (this.getSourceViewer() != null) {
			this.getSourceViewer().invalidateTextPresentation();
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
}
