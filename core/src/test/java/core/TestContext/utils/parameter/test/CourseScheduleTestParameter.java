package core.TestContext.utils.parameter.test;

import resources.components.elements.POJO.Course.CoursePOJO; 

public class CourseScheduleTestParameter

														extends TestParameter<CoursePOJO>{
	private CoursePOJO course;

	public CourseScheduleTestParameter(CoursePOJO pojo) {
		 
		course  = pojo; 
	}

	public CoursePOJO getCourse() {
		return course;
	}
 
}
