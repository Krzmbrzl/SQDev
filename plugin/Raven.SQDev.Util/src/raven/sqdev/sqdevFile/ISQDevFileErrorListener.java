package raven.sqdev.sqdevFile;

/**
 * This interface describes an error listener that can be used for validating
 * SQDevFiles
 * 
 * @author Raven
 *
 */
public interface ISQDevFileErrorListener {

	/**
	 * Gets called upon every error
	 * 
	 * @param start
	 *            The start offset of the error
	 * @param length
	 *            The length of the error area
	 * @param errorMsg
	 *            The error message
	 * @return Whether to continue the validation or not
	 */
	public boolean error(int start, int length, String errorMsg);
}
