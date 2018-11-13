package core.backend.REST.assets.parameter;
 

import core.backend.REST.general.request.schedule.file.RESTScheduleFileRequest;
import resources.components.elements.POJO.Course.CoursePOJO;


/**
 * Parameter that 
 * is used to describe 
 * the amount of asset files, 
 * that keep the information of the lecture schedule data, 
 * or the course schedule data
 * 
 * */

 
public abstract class AssetsStockParameter 
									extends RESTScheduleFileRequest< CoursePOJO >{

	protected final String assetsStockFileRequestParameter  = "course";
	
	
	public AssetsStockParameter(CoursePOJO  coursePOJO) {
	
		super(coursePOJO);
	}
}
