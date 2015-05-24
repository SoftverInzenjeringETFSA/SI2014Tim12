package ba.unsa.etf.si.tim12;


public class TerminNotFound extends Exception {

	/**
	 * 
	 */
	public TerminNotFound() {
	}

	/**
	 * @param message
	 */
	public TerminNotFound(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public TerminNotFound(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public TerminNotFound(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public TerminNotFound(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	
	}



}
