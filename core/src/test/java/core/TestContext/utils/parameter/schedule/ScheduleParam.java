package core.TestContext.utils.parameter.schedule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;  
import javax.xml.bind.DatatypeConverter;

import core.TestContext.utils.parameter.test.CourseScheduleFileTestParameter;
import core.TestContext.utils.parameter.test.CourseScheduleTestParameter;
import core.TestContext.utils.parameter.test.TestParameter;
import resources.components.elements.POJO.Course.CoursePOJO;
import resources.components.elements.POJO.Schedule.CourseScheduleFilePOJO; 

public abstract class ScheduleParam<Parameter extends TestParameter> {
	
	
	// requested CourseName 
	protected String courseName; 
		
	// requested CourseDegree 
	protected String courseDegree; 
		
	// requested CourseTerm 
	protected String courseTerm; 
	
	protected String scheduleFile; 
	
	
	public  abstract Parameter  createScheduleParam();
	
	
	public void setCourseName(String courseName) {
		
	
		this.courseName = courseName;
	}
	
	
	public void setCourseDegree(String courseDegree) {
		
		
		this.courseDegree = courseDegree;
	}
	
	 
	public void setCourseTerm(String courseTerm) {
		
		
		this.courseTerm = courseTerm;
	}
	 
	
	public String getScheduleFile() {
		return scheduleFile;
	}
 
}
