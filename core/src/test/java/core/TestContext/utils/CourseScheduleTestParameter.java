package core.TestContext.utils;

import resources.components.elements.POJO.Course.CoursePOJO;

public class CourseScheduleTestParameter {

	private CoursePOJO course;

	
	public CourseScheduleTestParameter( CoursePOJO course ) {
		
		setCourse(course);
	}
	
	public CoursePOJO getCourse() {
		return course;
	}

	public void setCourse(CoursePOJO course) {
		this.course = course;
	} 
	
	
}
