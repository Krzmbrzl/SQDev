package raven.sqdev.parser.misc;

import org.eclipse.core.resources.IMarker;

import dataStructures.IErrorListener;
import dataStructures.SQFToken;
import raven.sqdev.parser.sqf.SQFParseResult;

public class SQFLexAndParseListener implements IErrorListener {

	/**
	 * The parse result to add the errors to
	 */
	protected SQFParseResult result;

	public SQFLexAndParseListener() {
		this(new SQFParseResult());
	}

	public SQFLexAndParseListener(SQFParseResult result) {
		this.result = result;
	}

	@Override
	public void error(String msg, SQFToken token) {
		result.addMarker(IMarker.PROBLEM, token.start(), token.length(), msg, IMarker.SEVERITY_ERROR);
	}

	/**
	 * Gets the parse result
	 */
	public SQFParseResult getResult() {
		return result;
	}

	/**
	 * Sets the parse result
	 * 
	 * @param result
	 *            The {@linkplain SQFParseResult} object to report to
	 */
	public void setResult(SQFParseResult result) {
		this.result = result;
	}

}
