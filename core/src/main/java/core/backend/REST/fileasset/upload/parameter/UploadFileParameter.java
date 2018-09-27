package core.backend.REST.fileasset.upload.parameter;
 
import core.backend.REST.general.request.MasteRESTFileRequest;

public class UploadFileParameter 
						extends MasteRESTFileRequest{

	public UploadFileParameter(String courseName, String courseDegree, String courseTerm, String scheduleFile) {
		super(courseName, courseDegree, courseTerm, scheduleFile);
	 
	}
	 
}
