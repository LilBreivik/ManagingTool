package core.TestContext.utils;

import core.backend.REST.general.request.schedule.RESTScheduleRequest;
import resources.components.elements.POJO.Course.CoursePOJO;

public class FileParameter extends RESTScheduleRequest{

	public FileParameter(CoursePOJO pojo) {
		super(pojo); 
	}

}
