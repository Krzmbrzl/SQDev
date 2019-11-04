package raven.sqdev.editors;

import java.util.ArrayList;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.graphics.Point;

import raven.sqdev.interfaces.IEditorKeyHandler;
import raven.sqdev.misc.CharacterPair;
import raven.sqdev.misc.SQDevPreferenceUtil;
import raven.sqdev.misc.TextUtils;

/**
 * This class will handle character inputs that have a predefined partner to
 * match up with (e.g. <code>'('</code> with <code>')'</code> or <code>'
 * "'</code> with the closing <code>'"'</code>)<br>
 * <b>It will only handle <code>CharacterPair</code>s that have been explicitly
 * registered to this handler
 * 
 * @author Raven
 * 
 */
public class CharacterPairHandler implements IEditorKeyHandler {
	/**
	 * The list of pairs to match
	 */
	private ArrayList<CharacterPair> pairs;
	/**
	 * The last matched opening character (used for caret skipping in
	 * handleMatchedClosingCharacter())
	 */
	private char lastMatchedOpeningCharacter;
	/**
	 * The editor this Handler works on
	 */
	private BasicCodeEditor editor;

	public CharacterPairHandler(ArrayList<CharacterPair> list, BasicCodeEditor editor) {
		this.setPairs(list);

		this.editor = editor;
	}

	public CharacterPairHandler(BasicCodeEditor editor) {
		this(new ArrayList<CharacterPair>(), editor);
	}

	@SuppressWarnings("serial")
	public CharacterPairHandler(CharacterPair pair, BasicCodeEditor editor) {
		this(new ArrayList<CharacterPair>() {
			{
				add(pair);
			}
		}, editor);
	}

	@Override
	public boolean willHandle(VerifyEvent event) {
		if (!(event.getSource() instanceof StyledText)) {
			// Don't handle events that are not caused by StyledText
			return false;
		}
		
		if (!SQDevPreferenceUtil.isCharacterPairCompletionEnabled()) {
			// The preferences specify that the CharacterPairHandler shouldn't be used
			return false;
		}

		Point selection = ((StyledText) event.getSource()).getSelection();
		if (selection.x != selection.y) {
			// don't do anything when an area is modified
			return false;
		}

		if (IEditorKeyHandler.isDeletion(event.character)) {
			// handle deletions
			return true;
		}

		if (editor.getBasicProvider().getPartitioner().getContentType(selection.x).toLowerCase().contains("string")) {
			// disable in strings
			return false;
		}

		if (isRegisteredOpeningCharacter(event.character) || isRegisteredClosingCharacter(event.character)) {
			// if the character belongs to a registered CharacterPair handle it
			return true;
		} else {
			// if it doesn't belong to a CharacterPair ignore this event
			return false;
		}
	}

	@Override
	public void handleAddition(VerifyEvent event) {
		boolean isOpener = isRegisteredOpeningCharacter(event.character);
		boolean isCloser = isRegisteredClosingCharacter(event.character);

		// is always StyledText (checked in willHandle())
		StyledText textWidget = (StyledText) event.getSource();
		Point selection = textWidget.getSelection();

		if (selection.x != selection.y) {
			// don't do anything when an area is modified
			return;
		}

		if (isOpener && isCloser) {
			// if it's part of a CharacterPair whose start and end are equal

			// decide whether to use it as a opener or closer
			StyledText text = (StyledText) event.getSource();

			int caretPosition = text.getCaretOffset();

			if (caretPosition == 0 || caretPosition == text.getText().length()) {
				// if the caret is at the beginning or at the end of the
				// document it must be an opener
				isCloser = false;
			} else {
				String previousText = text.getText(0, caretPosition - 1);
				String followingText = text.getText(caretPosition, text.getText().length() - 1);

				int occuranceBefore = TextUtils.countMatches(previousText, String.valueOf(event.character));
				int occuranceAfter = TextUtils.countMatches(followingText, String.valueOf(event.character));

				// TODO: handle escaped character or only consider characters
				// outside of strings/comments

				if (occuranceBefore % 2 == 0 && occuranceAfter % 2 == 0) {
					isCloser = false;
				} else {
					if (occuranceAfter % 2 != 0) {
						// some weird state were the opener is inserted after
						// the closer -> just insert the typed character
						isCloser = false;
						isOpener = false;
					} else {
						// if the amount of this character before is uneven then
						// the one just typed in has to be a closer
						isOpener = false;
					}
				}
			}
		}

		if (isOpener) {
			// memorize this character
			this.setLastMatchedOpeningCharacter(event.character);

			// handle a matched opening character
			this.handleMatchedOpeningCharacter(event);
		} else {
			if (isCloser) {
				// handle a matched closing character
				this.handleMatchedClosingCharacter(event);

				if (this.getPairingCharacter(event.character) == this.getLastMatchedOpeningCharacter()) {
					// if the corrseponding closing character was typed in
					// by the user reset the memorized matched character
					this.resetLastMatchedOpeningCharacter();
				}
			}
		}
	}

	/**
	 * Handles the match of an opening character of a Characterpair
	 * 
	 * @param event
	 *            The event that caused the appearance of the opening character
	 */
	public void handleMatchedOpeningCharacter(VerifyEvent event) {
		char pairingCharacter = this.getPairingCharacter(event.character);

		StyledText textWidget = (StyledText) event.getSource();
		int offset = textWidget.getCaretOffset();

		if (textWidget.getText().length() <= offset + 1) {
			// always complete on EOF
			textWidget.insert(String.valueOf(pairingCharacter));
		} else {
			// check what comes after the addition
			char nextChar = textWidget.getText().charAt(offset);

			if (!TextUtils.isWordPart(nextChar)) {
				// complete only if not directly in front of a word
				textWidget.insert(String.valueOf(pairingCharacter));
			} else {
				resetLastMatchedOpeningCharacter();
			}
		}
	}

	/**
	 * Handles the match of the closing character of a CharacterPair
	 * 
	 * @param event
	 *            The event that caused the appearance of the opening character
	 */
	public void handleMatchedClosingCharacter(VerifyEvent event) {
		StyledText text = (StyledText) event.getSource();
		if (event.character == getPairingCharacter(getLastMatchedOpeningCharacter())
				&& text.getText().charAt(text.getCaretOffset() - 1) == getLastMatchedOpeningCharacter()) {
			// if the closing character is directly typed after the opening one
			// move the caret for one position
			text.setCaretOffset(text.getCaretOffset() + 1);

			// don't insert the character as it has already been inserted by the
			// editor
			event.doit = false;
		}
	}

	@Override
	public void handleDeletion(VerifyEvent event) {
		StyledText text = (StyledText) event.getSource();

		if (!text.getSelectionText().isEmpty()) {
			// if a text area is selected do nothing
			return;
		}

		int offset = text.getCaretOffset();

		if (offset == 0 || offset == text.getText().length()) {
			// do nothing on the beginning and the end of the document
			return;
		}

		char charToDelete = text.getText().charAt(offset - 1);
		char nextChar = text.getText().charAt(offset);

		if (isRegisteredOpeningCharacter(charToDelete) && nextChar == getPairingCharacter(charToDelete)) {
			// delete corresponding closing character if there are no characters
			// in between
			text.replaceTextRange(offset, 1, "");
		}
	}

	public ArrayList<CharacterPair> getPairs() {
		return pairs;
	}

	public void setPairs(ArrayList<CharacterPair> pairs) {
		this.pairs = pairs;
	}

	/**
	 * Add a CharacterPair to the list of CharacterPairs
	 * 
	 * @param pair
	 *            The Characterpair to be added
	 */
	public void addPair(CharacterPair pair) {
		if (!this.getPairs().contains(pair)) {
			// only add pairs that do not already exist in this list
			this.getPairs().add(pair);
		}
	}

	/**
	 * Check if there is a registered CharacterPair that has the given character
	 * as an opener
	 * 
	 * @param c
	 *            The character to search for
	 * @return
	 */
	public boolean isRegisteredOpeningCharacter(char c) {
		for (CharacterPair current : this.getPairs()) {
			if (current.getOpener() == c) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Check if there is a registered CharacterPair that has the given character
	 * as an closer
	 * 
	 * @param c
	 *            The character to search for
	 * @return
	 */
	public boolean isRegisteredClosingCharacter(char c) {
		for (CharacterPair current : this.getPairs()) {
			if (current.getCloser() == c) {
				return true;
			}
		}

		return false;
	}

	public char getLastMatchedOpeningCharacter() {
		return lastMatchedOpeningCharacter;
	}

	public void setLastMatchedOpeningCharacter(char lastMatchedCharacter) {
		this.lastMatchedOpeningCharacter = lastMatchedCharacter;
	}

	public void resetLastMatchedOpeningCharacter() {
		this.setLastMatchedOpeningCharacter((char) 0);
	}

	/**
	 * Gets the pairing character of the given one. If no partner could be found
	 * the bull-character is returned
	 * 
	 * @param c
	 *            The character the function should find the corresponding
	 *            partner to
	 * @return
	 */
	public char getPairingCharacter(char c) {
		for (CharacterPair current : this.getPairs()) {
			if (current.includes(c)) {
				return (current.getOpener() == c) ? current.getCloser() : current.getOpener();
			}
		}

		// if no matching character was found return 0
		return (char) 0;
	}
}
