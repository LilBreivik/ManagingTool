package core.TestContext.utils.parameter.schedule;
 
import core.TestContext.utils.parameter.test.CourseScheduleTestParameter;
import resources.components.elements.POJO.Course.CoursePOJO; 

public class ScheduleParamWithoutFileAsset 
												extends ScheduleParam<CourseScheduleTestParameter>{
 
	@Override
	public CourseScheduleTestParameter createScheduleParam() {
		
		CoursePOJO  pojo = new CoursePOJO ();
			
		pojo.setCourseDegree(courseDegree);
		pojo.setCourseName(courseName);
	    pojo.setCourseTerm(courseTerm);
	     
		return new CourseScheduleTestParameter(pojo);
	}	 
}
