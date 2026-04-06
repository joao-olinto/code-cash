package domain.exception;

public class DomainException extends RuntimeException {

	/**
	 */
	private static final long serialVersionUID = -859065037924487056L;
	
	public DomainException() {
		super();
	}
	
	public DomainException(String message) {
		super(message);
	}
	

}
