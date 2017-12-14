package cn.com.mymodule.exception;

import org.sculptor.framework.errorhandling.ApplicationException;

public class DownloadLogNotFoundException extends ApplicationException {
	private static final long serialVersionUID = 1L;
	private static final String CLASS_NAME = DownloadLogNotFoundException.class.getSimpleName();
	private static final String CLASS_FULL_NAME = DownloadLogNotFoundException.class.getName();

	public DownloadLogNotFoundException(String m, java.io.Serializable... messageParameter) {
		super(CLASS_FULL_NAME, m);
		setMessageParameters(messageParameter);
	}

	public DownloadLogNotFoundException(int errorCode, String m, java.io.Serializable... messageParameter) {
		super(String.format("%1$s-%3$04d", CLASS_FULL_NAME, CLASS_NAME, errorCode), m);
		setMessageParameters(messageParameter);
	}
}
