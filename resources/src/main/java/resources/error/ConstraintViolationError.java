package resources.error;

/**
 * Exception that is thrown, if 
 * a Constrain was violated
 * */

public class ConstraintViolationError extends MasterError{

	public ConstraintViolationError(String errorMessage) {
		super(errorMessage); 
	}

}
