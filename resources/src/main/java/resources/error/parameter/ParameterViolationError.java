package resources.error.parameter;

import resources.error.MasterError;

/**
 * Exception that is thrown, if 
 * a some parameter do not match the expected ones...
 * */

public class ParameterViolationError extends MasterError{

	public ParameterViolationError(String errorMessage) {
		super(errorMessage); 
	}

}
