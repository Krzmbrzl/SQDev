package raven.sqdev.utilInterfaces;

import raven.sqdev.util.SQDevInformation;

public interface ISQDevInformationProvider {

	/**
	 * Gets the data of this provider bundled into a SQDevInformation
	 */
	public SQDevInformation getInformation();
}
