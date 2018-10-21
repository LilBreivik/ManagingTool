package core.TestContext.utils;

import resources.components.elements.POJO.Schedule.CourseScheduleFilePOJO;

public class CourseScheduleFileTestParameter {
 
	private  CourseScheduleFilePOJO courseScheduleFile;

	public CourseScheduleFileTestParameter(CourseScheduleFilePOJO courseScheduleFile) {
		
		setCourseScheduleFile(courseScheduleFile);
	}
	
	public CourseScheduleFilePOJO getCourseScheduleFile() {
		return courseScheduleFile;
	} 

	public void setCourseScheduleFile(CourseScheduleFilePOJO courseScheduleFile) {
		this.courseScheduleFile = courseScheduleFile;
	}
}
