package cn.com.mymodule.exception;

import org.sculptor.framework.errorhandling.ApplicationException;

public class DownloadNotFoundException extends ApplicationException {
	private static final long serialVersionUID = 1L;
	private static final String CLASS_NAME = DownloadNotFoundException.class.getSimpleName();
	private static final String CLASS_FULL_NAME = DownloadNotFoundException.class.getName();

	public DownloadNotFoundException(String m, java.io.Serializable... messageParameter) {
		super(CLASS_FULL_NAME, m);
		setMessageParameters(messageParameter);
	}

	public DownloadNotFoundException(int errorCode, String m, java.io.Serializable... messageParameter) {
		super(String.format("%1$s-%3$04d", CLASS_FULL_NAME, CLASS_NAME, errorCode), m);
		setMessageParameters(messageParameter);
	}
}
