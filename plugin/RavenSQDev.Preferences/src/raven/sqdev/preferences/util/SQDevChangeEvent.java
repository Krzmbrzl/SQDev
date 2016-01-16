package raven.sqdev.preferences.util;

public class SQDevChangeEvent {
	
	public static final String SQDEV_VALUE_CHANGED = "valueChanged";
	public static final String SQDEV_STATUS_CHANGED = "statusChanged";
	public static final String SQDEV_VALUE_LOADED = "valueLoaded";
	
	private String context;
	
	public SQDevChangeEvent(String context) {
		setContext(context);
	}

	public String getContext() {
		return context;
	}

	private void setContext(String context) {
		this.context = context;
	}
	
}
