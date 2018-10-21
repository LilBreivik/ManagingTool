package resources.error;

/**
 * Exception that is thrown, if 
 * an internal Exception happend
 * */

public class InternalError extends MasterError{
 
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = -9000319903601896666L;

	public InternalError(String errorMessage) {
		super(errorMessage);
	}

	
}
