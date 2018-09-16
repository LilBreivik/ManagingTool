package resources.error;

/**
 * Exception that is thrown, if 
 * an internal Exception happend
 * */

public class InternalError extends MasterError{

	public InternalError(String errorMessage) {
		super(errorMessage);
	}

	
}
