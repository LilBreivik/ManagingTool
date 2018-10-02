package core.TestContext.utils;

import resources.components.elements.POJO.Schedule.CoursePOJO;

public class CourseScheduleParam {

	private CoursePOJO course;

	
	public CourseScheduleParam( CoursePOJO course ) {
		
		setCourse(course);
	}
	
	public CoursePOJO getCourse() {
		return course;
	}

	public void setCourse(CoursePOJO course) {
		this.course = course;
	} 
	
	
}
