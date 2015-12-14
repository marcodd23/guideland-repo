package it.guideland.app.exceptions;

public class ServiceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7507638924236457210L;
	
	private ExceptionCause exceptionCause;
	
	public ServiceException(ExceptionCause exceptionCause) {
		super();
		this.exceptionCause = exceptionCause;
	}

	public ExceptionCause getExceptionCause() {
		return exceptionCause;
	}

	public void setExceptionCause(ExceptionCause exceptionCause) {
		this.exceptionCause = exceptionCause;
	}

	public enum ExceptionCause {

		NOT_FOUND("Entity found"), 
		NOT_AUTHORIZED("Access Denied !!");

		private final String cause;

		private ExceptionCause(final String cause) {
			this.cause = cause;
		}

		@Override
		public String toString() {
			return cause;
		}

	}

}
