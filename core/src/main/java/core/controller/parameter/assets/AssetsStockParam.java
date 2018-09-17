package core.controller.parameter.assets;

import core.controller.parameter.GenericScheduleFileParam;

/*
 * Class that defines the parameter 
 * that is expected for the check of the 
 * file assets 
 * */

public abstract class AssetsStockParam 
									extends  GenericScheduleFileParam {

	
	public AssetsStockParam(String courseName, String courseDegree, String courseTerm) {
		super(courseName, courseDegree, courseTerm);
	
	}

	
	/**
	 * Method that is need to
	 * configure the request for the expected AssetsStock .. 
	 * (Lecture or CourseScheduleAssetsStock)
	 * */
	public abstract void configureAssetsStock();
	
}
