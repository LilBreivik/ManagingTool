package core.TestContext.utils.parameter.test;
 
import resources.components.elements.POJO.Schedule.CourseScheduleFilePOJO;

public class CourseScheduleFileTestParameter 
											extends TestParameter<CourseScheduleFilePOJO>{
 
	private  CourseScheduleFilePOJO courseScheduleFile;
	
	public CourseScheduleFileTestParameter(CourseScheduleFilePOJO pojo) {
	 
		courseScheduleFile = pojo; 
	}

	public CourseScheduleFilePOJO getCourseScheduleFile() {
		return courseScheduleFile;
	} 

}
