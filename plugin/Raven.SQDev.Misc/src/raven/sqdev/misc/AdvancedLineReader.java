package raven.sqdev.misc;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.Assert;

public class AdvancedLineReader implements Closeable {
	
	/**
	 * The limit of the unread buffer
	 */
	public static final int UNREAD_BUFFER_LIMIT = 12000;
	
	/**
	 * The read ahead limit used for marking the reader
	 */
	public static final int READ_AHEAD_LIMIT = 12000;
	
	/**
	 * The offset of the start of the currently returned line
	 */
	private int lineStartOffset;
	/**
	 * The length of the line returned last
	 */
	private int lastLineLength;
	/**
	 * A flag indicating whether the end of the buffer has been reached
	 */
	private boolean endOfBufferReached;
	/**
	 * An array containing the saved values after the mark method
	 */
	private int[] savedValues;
	/**
	 * The lineDelimiter of the last read line
	 */
	private StringBuilder lastLineDelimiter;
	/**
	 * For the storage of {@link #lastLineDelimiter} in case of {@link #mark()}
	 */
	private StringBuilder savedLineDelimiter;
	/**
	 * The stack containing unread lines
	 */
	private Stack<String> unreadLineStack;
	/**
	 * The stack to save {@link #unreadLineStack}
	 */
	private Stack<String> savedUnreadLineStack;
	/**
	 * The stack containing unread characters
	 */
	private Stack<Character> unreadCharacterStack;
	/**
	 * The stack to save {@link #savedUnreadCharacterStack}
	 */
	private Stack<Character> savedUnreadCharacterStack;
	/**
	 * The reader to use underneath
	 */
	private Reader in;
	
	
	/**
	 * The save index of the offset
	 */
	private static final int OFFSET = 0;
	/**
	 * The save index of the line length
	 */
	private static final int LENGTH = 1;
	
	/**
	 * Creates a buffering character-input stream that uses a default-sized
	 * input buffer.
	 *
	 * @param in
	 *            A Reader
	 */
	public AdvancedLineReader(Reader in) {
		Assert.isNotNull(in);
		
		this.in = in;
		
		lineStartOffset = 0;
		lastLineLength = 0;
		endOfBufferReached = false;
		lastLineDelimiter = new StringBuilder();
		unreadLineStack = new Stack<String>();
		savedUnreadLineStack = new Stack<String>();
		unreadCharacterStack = new Stack<Character>();
		savedUnreadCharacterStack = new Stack<Character>();
		
		savedValues = new int[2];
	}
	
	/**
	 * Reads the next line and returns it. If the end of the stream is reached
	 * <code>null</code> is returned
	 * 
	 * @throws IOException
	 */
	public String readLine() throws IOException {
		lineStartOffset += lastLineLength;
		
		String line = doReadLine();
		
		if (endOfBufferReached && line.isEmpty()) {
			return null;
		}
		
		return line;
	}
	
	/**
	 * Does the internal reading
	 * 
	 * @throws IOException
	 */
	private String doReadLine() throws IOException {
		lastLineDelimiter.setLength(0); // clear
		
		int currentChar;
		StringBuffer charBuffer = new StringBuffer();
		
		lastLineLength = 0;
		
		// return unread lines first
		if (!unreadLineStack.empty()) {
			String line = unreadLineStack.pop();
			
			lastLineLength = line.length();
			
			if (line.endsWith("\r\n")) {
				lastLineDelimiter.append("\r\n");
				line = line.substring(0, line.length() - 2);
			} else {
				lastLineDelimiter.append("\n");
				line = line.substring(0, line.length() - 1);
			}
			
			return line;
		}
		
		while ((currentChar = read()) != '\n' && currentChar != -1) {
			charBuffer.append((char) currentChar);
			lastLineLength++;
		}
		
		if (currentChar == '\n') {
			lastLineLength++; // NL also is part of line length
			lastLineDelimiter.append("\n");
			
			if (charBuffer.length() > 0 && charBuffer.charAt(charBuffer.length() - 1) == '\r') {
				// delete carriage return
				charBuffer.deleteCharAt(charBuffer.length() - 1);
				lastLineDelimiter.insert(0, "\r");
			}
		} else {
			if (currentChar == -1) {
				endOfBufferReached = true;
			}
		}
		
		return charBuffer.toString();
	}
	
	/**
	 * Unreads the given line by putting it on top of the character stream, so
	 * that it will be the next line to be read by {@link #readLine()}. The line
	 * seperator will be added by the function
	 * 
	 * @param line
	 *            The line to be unread
	 * @param useLastLineDelimiter
	 *            Whether the line delimiter of the last read line should be
	 *            appended to the line. If not the system default line delimiter
	 *            will be appended.<br>
	 *            If you want to use your own delimiter use
	 *            {@link #unread(String)}
	 * @throws IOException
	 */
	public void unreadLine(String line, boolean useLastDelimiter) throws IOException {
		unreadLineStack.push((line
				+ ((useLastDelimiter) ? getLineDelimiter() : String.format("%n").toCharArray())));
		
		lastLineLength = 0;
	}
	
	/**
	 * Unreads the given line by putting it on top of the character stream, so
	 * that it will be the next line to be read by {@link #readLine()}.<br>
	 * Be aware that unread lines get read out first before the unread
	 * character-strings are read
	 * 
	 * @param content
	 *            The content to be unread
	 * @throws IOException
	 */
	public void unread(String content) throws IOException {
		for (char currentChar : content.toCharArray()) {
			unreadCharacterStack.push(currentChar);
		}
		
		lastLineLength = 0;
	}
	
	/**
	 * Reads the next line of the Inputstream and removes the prefix of it
	 * 
	 * @param regex
	 *            The regular expression describing the prefix that should be
	 *            removed
	 * @return The next line without the prefix
	 * @throws IOException
	 */
	public String readLineWithoutPrefix(String regex) throws IOException {
		String line = readLine();
		
		if (line == null) {
			return line;
		}
		
		Matcher matcher = Pattern.compile(regex).matcher(line);
		
		
		if (matcher.find() && matcher.start() == 0) {
			line = line.substring(matcher.end());
		}
		
		return line;
		
	}
	
	/**
	 * Whether the end of the buffer has been reached
	 */
	public boolean endOfBufferReached() {
		return endOfBufferReached;
	}
	
	/**
	 * The offset of the start of the currently returned line
	 */
	public int getLineStartOffset() {
		return lineStartOffset;
	}
	
	/**
	 * Marks this reader so it can be reset to this state
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void mark() throws IOException {
		savedValues[OFFSET] = lineStartOffset;
		savedValues[LENGTH] = lastLineLength;
		savedLineDelimiter = lastLineDelimiter;
		savedUnreadLineStack = (Stack<String>) unreadLineStack.clone();
		savedUnreadCharacterStack = (Stack<Character>) unreadCharacterStack.clone();
		
		this.in.mark(READ_AHEAD_LIMIT);
	}
	
	/**
	 * Resets the reader to the last marked state
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void reset() throws IOException {
		lineStartOffset = savedValues[OFFSET];
		lastLineLength = savedValues[LENGTH];
		lastLineDelimiter = savedLineDelimiter;
		unreadLineStack = (Stack<String>) savedUnreadLineStack.clone();
		unreadCharacterStack = (Stack<Character>) savedUnreadCharacterStack.clone();
		
		this.in.reset();
	}
	
	/**
	 * Gets the line delimiter of the last read line
	 * 
	 * @return The line delimiter (max. 2 chars) or an empty String if was no
	 *         delimiter
	 */
	public String getLineDelimiter() {
		return lastLineDelimiter.toString();
	}
	
	/**
	 * Checks whether this reader supports marking whcih depends on the reader
	 * used to initialize it
	 */
	public boolean markSupported() {
		return in.markSupported();
	}
	
	/**
	 * Reads the next character
	 * 
	 * @return The read character or -1 if the end of the stream is reached
	 * @throws IOException
	 */
	private int read() throws IOException {
		// return unread characters first
		if (!unreadCharacterStack.isEmpty()) {
			return unreadCharacterStack.pop();
		}
		
		return in.read();
	}
	
	@Override
	public void close() throws IOException {
		this.in.close();
	}
	
	// TODO: get rid of the Pushbackreader extension + write custom
	// read-function (be aware of the unread-Stack -> Might need to change to
	// char instead of String)
}
