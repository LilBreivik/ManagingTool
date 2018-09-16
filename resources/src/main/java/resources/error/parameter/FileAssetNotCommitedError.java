package resources.error.parameter;

/**
 * Error that happens, 
 * if an expected file was not commited in a 
 * web request 
 * */

public class FileAssetNotCommitedError 
										extends ParameterViolationError{

	
	public  FileAssetNotCommitedError () {
		super("No File Committed");
	}
	
}
