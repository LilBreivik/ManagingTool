package resources.error;

/**
 * Exception that is thrown, if 
 * a Constrain was violated
 * */

public class ConnectionError extends MasterError{

	public ConnectionError(String errorMessage) {
		super(errorMessage); 
	}

}
