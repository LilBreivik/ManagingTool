package core.TestContext.utils;

import core.backend.REST.general.request.schedule.RESTScheduleRequest;
import resources.components.elements.POJO.Schedule.CoursePOJO;

public class FileParameter extends RESTScheduleRequest{

	public FileParameter(CoursePOJO pojo) {
		super(pojo); 
	}

}
