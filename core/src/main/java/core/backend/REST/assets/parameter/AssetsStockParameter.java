package core.backend.REST.assets.parameter;
 

import core.backend.REST.general.request.schedule.file.RESTScheduleFileRequest;
import resources.components.elements.POJO.Course.CoursePOJO;

public abstract class AssetsStockParameter 
									extends RESTScheduleFileRequest< CoursePOJO >{

	protected final String assetsStockFileRequestParameter  = "course";
	
	
	public AssetsStockParameter(CoursePOJO  coursePOJO) {
	
		super(coursePOJO);
	}
}
