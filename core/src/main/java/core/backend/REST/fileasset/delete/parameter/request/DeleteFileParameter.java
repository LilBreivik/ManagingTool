package core.backend.REST.fileasset.delete.parameter.request;

import core.backend.REST.general.request.MasterRESTRequest;

public class DeleteFileParameter 
								extends MasterRESTRequest{

	public DeleteFileParameter(String courseName, String courseDegree, String courseTerm) {
		super(courseName, courseDegree, courseTerm);
		 
	}

}
