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

public class BasicTextEditor extends TextEditor {
	
	private ColorManager colorManager;
	private EditorKeyEventQueue editorKeyEventQueue;
	
	public BasicTextEditor() {
		super();
		
		setColorManager(new ColorManager());
		setEditorKeyEventQueue(new EditorKeyEventQueue());
		
		// add a implementation for the autoCompletion of pairing characters
		addCharacterPairHandler();
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
			// set the plugin's shared preference store instead of the default one
			fSourceViewerDecorationSupport.install(SQDevPreferenceUtil.getPreferenceStore());
		}
	}
	
	/**
	 * Updates the editor
	 */
	public void update() {
		this.getSourceViewer().invalidateTextPresentation();
	}
}
