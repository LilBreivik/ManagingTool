package resources.error;

public class FileIsMissingError  extends MasterError{

	public String missingFileName;
	
	public String missingFileCause;

	public FileIsMissingError(String errorMessage) {
		super(errorMessage);
	 
	}
}
