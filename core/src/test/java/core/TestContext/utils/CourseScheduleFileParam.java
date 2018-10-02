package core.TestContext.utils;

import resources.components.elements.POJO.Schedule.CourseScheduleFilePOJO;

public class CourseScheduleFileParam {
 
	private  CourseScheduleFilePOJO courseScheduleFile;

	public CourseScheduleFileParam(CourseScheduleFilePOJO courseScheduleFile) {
		
		setCourseScheduleFile(courseScheduleFile);
	}
	
	public CourseScheduleFilePOJO getCourseScheduleFile() {
		return courseScheduleFile;
	}

	public void setCourseScheduleFile(CourseScheduleFilePOJO courseScheduleFile) {
		this.courseScheduleFile = courseScheduleFile;
	}
}
