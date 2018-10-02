package core.TestContext.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.DatatypeConverter;

import resources.components.elements.POJO.Schedule.CoursePOJO;
import resources.components.elements.POJO.Schedule.CourseScheduleFilePOJO;
import resources.components.elements.POJO.Scheduling.Utils.IScheduleParam; 

public class ScheduleFileUploadParam 	implements IScheduleParam {
	
	// requested CourseName 
	private String courseName; 
		
	// requested CourseDegree 
	private String courseDegree; 
		
	// requested CourseTerm 
	private String courseTerm; 
	
	protected String scheduleFile; 
	
	
	public CourseScheduleParam createCourseScheduleParam() {
		
		CoursePOJO  pojo = new CoursePOJO ();
			
		pojo.setCourseDegree(courseDegree);
		pojo.setCourseName(courseName);
	    pojo.setCourseTerm(courseTerm);
	    
		return new CourseScheduleParam(pojo);
	}

	
	
	public CourseScheduleFileParam createCourseScheduleFileParam() {
		
		 CourseScheduleFilePOJO  pojo = new  CourseScheduleFilePOJO ();
		
		pojo.setCourseDegree(courseDegree);
		pojo.setCourseName(courseName);
		pojo.setCourseTerm(courseTerm);
		pojo.setScheduleFile(scheduleFile);
		
		return new CourseScheduleFileParam(pojo);
		
	}
	
	
	
	
	public String getScheduleFile() {
		return scheduleFile;
	}

	public void setScheduleFile(File scheduleFile) throws IOException {
		
		System.out.println();
	 
		try {
			
			byte[] buf = Files.readAllBytes(scheduleFile.toPath());
			buf = java.util.Base64.getEncoder().encode(buf);
			

			// we have to provide a dummy application part to simulate a real xhr upload
		
			this.scheduleFile = "application:data ,"+   DatatypeConverter.printBase64Binary(Files.readAllBytes(scheduleFile.toPath()));
		}
		catch(IOException e) {
			
			e.printStackTrace();
			
			throw e;
		}
		
		
		
	}


	@Override
	public String getCourseName() {
	
		return courseName;
	}

	@Override
	public void setCourseName(String courseName) {
		
		this.courseName = courseName; 
	}

	@Override
	public String getCourseDegree() {

		return courseDegree; 
	}

	@Override
	public void setCourseDegree(String courseDegree) {
	
		this.courseDegree = courseDegree; 
	}

	@Override
	public String getCourseTerm() {
		// TODO Auto-generated method stub
		return courseTerm; 
	}

	@Override
	public void setCourseTerm(String courseTerm) {
		
		this.courseTerm = courseTerm; 
	}

}
