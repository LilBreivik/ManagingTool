package core.backend.REST.fileasset.delete.parameter;
 
  

import core.backend.REST.general.request.schedule.file.RESTScheduleFileRequest;
import resources.components.elements.POJO.Course.CoursePOJO;

public class DeleteFileParameter  
										extends RESTScheduleFileRequest< CoursePOJO >{

	protected final String deleteFileRequestParameter  = "course";
	
	 
	public DeleteFileParameter(  CoursePOJO  coursePOJO) 
	{
		super(coursePOJO);
	}
}
