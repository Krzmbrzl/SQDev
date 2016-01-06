package raven.sqdev.editors.sqfeditor;

import java.util.Arrays;
import java.util.Comparator;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.PatternRule;

import raven.sqdev.editors.exceptions.IllegalConstructorException;

public class SQFStringPartitionRule extends PatternRule {
	
	/**
	 * Comparator that orders <code>char[]</code> in decreasing array lengths.
	 *
	 * @since 3.1
	 */
	@SuppressWarnings("rawtypes")
	private static class DecreasingCharArrayLengthComparator implements Comparator {
		public int compare(Object o1, Object o2) {
			return ((char[]) o2).length - ((char[]) o1).length;
		}
	}
	
	/**
	 * Cached line delimiters.
	 * 
	 * @since 3.1
	 */
	private char[][] fLineDelimiters;
	/**
	 * Cached sorted {@linkplain #fLineDelimiters}.
	 * 
	 * @since 3.1
	 */
	private char[][] fSortedLineDelimiters;
	/**
	 * Line delimiter comparator which orders according to decreasing delimiter
	 * length.
	 * 
	 * @since 3.1
	 */
	@SuppressWarnings("rawtypes")
	private Comparator fLineDelimiterComparator = new DecreasingCharArrayLengthComparator();
	
	/**
	 * Creates a new rule that matches a String in SQF
	 * 
	 * @param token
	 */
	public SQFStringPartitionRule(IToken token) {
		// create the rule with the SQF specific String pattern
		super("\"", "\"", token, '"', false);
	}
	
	/**
	 * <b>DON'T USE!</b><br>
	 * use {@linkplain #SQFStringPartitionRule(IToken)} instead!
	 */
	public SQFStringPartitionRule(String startSequence, String endSequence, IToken token,
			char escapeCharacter, boolean breaksOnEOL) {
		this(token);
		
		// This constructor shouldn't be used
		try {
			throw new IllegalConstructorException("SQFStringPartitionRule(IToken token)");
		} catch (IllegalConstructorException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * <b>DON'T USE!</b><br>
	 * use {@linkplain #SQFStringPartitionRule(IToken)} instead!
	 */
	public SQFStringPartitionRule(String startSequence, String endSequence, IToken token,
			char escapeCharacter, boolean breaksOnEOL, boolean breaksOnEOF) {
		this(token);
		
		// This constructor shouldn't be used
		try {
			throw new IllegalConstructorException("SQFStringPartitionRule(IToken token)");
		} catch (IllegalConstructorException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * <b>DON'T USE!</b><br>
	 * use {@linkplain #SQFStringPartitionRule(IToken)} instead!
	 */
	public SQFStringPartitionRule(String startSequence, String endSequence, IToken token,
			char escapeCharacter, boolean breaksOnEOL, boolean breaksOnEOF,
			boolean escapeContinuesLine) {
		this(token);
		
		// This constructor shouldn't be used
		try {
			throw new IllegalConstructorException("SQFStringPartitionRule(IToken token)");
		} catch (IllegalConstructorException ex) {
			ex.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	protected boolean endSequenceDetected(ICharacterScanner scanner) {
		
		char[][] originalDelimiters = scanner.getLegalLineDelimiters();
		int count = originalDelimiters.length;
		if (fLineDelimiters == null || fLineDelimiters.length != count) {
			fSortedLineDelimiters = new char[count][];
		} else {
			while (count > 0
					&& Arrays.equals(fLineDelimiters[count - 1], originalDelimiters[count - 1]))
				count--;
		}
		if (count != 0) {
			fLineDelimiters = originalDelimiters;
			System.arraycopy(fLineDelimiters, 0, fSortedLineDelimiters, 0, fLineDelimiters.length);
			Arrays.sort(fSortedLineDelimiters, fLineDelimiterComparator);
		}
		
		int readCount = 1;
		int c;
		while ((c = scanner.read()) != ICharacterScanner.EOF) {
			if (c == fEscapeCharacter) {
				// Skip escaped character(s)
				if (fEscapeContinuesLine) {
					c = scanner.read();
					for (int i = 0; i < fSortedLineDelimiters.length; i++) {
						if (c == fSortedLineDelimiters[i][0] && sequenceDetected(scanner,
								fSortedLineDelimiters[i], fBreaksOnEOF))
							break;
					}
				} else {
					// skip only of the sequence '""' is detected
					
					int next = scanner.read();
					
					if (next == '"') {
						scanner.read();
						continue;
					} else {
						scanner.unread();
					}
				}
			}
			
			if (fEndSequence.length > 0 && c == fEndSequence[0]) {
				// Check if the specified end sequence has been found.
				if (sequenceDetected(scanner, fEndSequence, fBreaksOnEOF))
					return true;
			} else if (fBreaksOnEOL) {
				// Check for end of line since it can be used to terminate the
				// pattern.
				for (int i = 0; i < fSortedLineDelimiters.length; i++) {
					if (c == fSortedLineDelimiters[i][0]
							&& sequenceDetected(scanner, fSortedLineDelimiters[i], fBreaksOnEOF))
						return true;
				}
			}
			readCount++;
		}
		
		if (fBreaksOnEOF)
			return true;
			
		for (; readCount > 0; readCount--)
			scanner.unread();
			
		return false;
	}
	
}
