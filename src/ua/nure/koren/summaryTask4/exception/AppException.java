package ua.nure.koren.summaryTask4.exception;

/**
 * An exception that provides information on an application error.
 * 
 * @author D.Koren
 * @version 1.0
 * @since 2019-11-19
 */
public class AppException extends Exception {

	private static final long serialVersionUID = 8288779062647218116L;

	public AppException() {
		super();
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(String message) {
		super(message);
	}

}
