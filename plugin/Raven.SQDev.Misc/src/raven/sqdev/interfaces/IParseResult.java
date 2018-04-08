package raven.sqdev.interfaces;

import java.util.Collection;

import org.antlr.v4.runtime.tree.ParseTree;

import dataStructures.IBuildableIndexTree;
import dataStructures.IToken;
import dataStructures.ITokenSource;
import raven.sqdev.misc.Marker;

/**
 * This interface describes an object that contains the result of parsing some
 * content
 * 
 * @author Raven
 *
 */
public interface IParseResult {


	/**
	 * Gets the {@linkplain IBuildableIndexTree} resulting from parsing the
	 * respective input
	 */
	public IBuildableIndexTree getTree();

	/**
	 * Gets the token buffer holding the tokens corresponding to the indices as
	 * listed in the tree retrieved via {@link #getTree()}
	 */
	public ITokenSource<? extends IToken> getTokenBuffer();

	/**
	 * Gets the markers that have been produced during parsing
	 */
	public Collection<Marker> getMarkers();

	/**
	 * Gets the ANTLR parse tree (if present)
	 */
	public ParseTree getANTRLParseTree();

	/**
	 * Gets the line in the input at the given offset
	 * 
	 * @param offset
	 *            The offset whose line should be determined
	 * @return The respective line
	 */
	public int getLine(int offset);

}
