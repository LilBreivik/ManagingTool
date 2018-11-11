package core.TestContext.utils.parameter.schedule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.xml.bind.DatatypeConverter;

import core.TestContext.utils.parameter.test.CourseScheduleFileTestParameter;
import resources.components.elements.POJO.Schedule.CourseScheduleFilePOJO;

public class ScheduleParamWithFileAsset 
											extends ScheduleParam<CourseScheduleFileTestParameter>{
  
	
	protected String scheduleFile; 
	

	@Override
	public CourseScheduleFileTestParameter createScheduleParam() {
		
		 CourseScheduleFilePOJO  pojo = new  CourseScheduleFilePOJO ();
		
		pojo.setCourseDegree(courseDegree);
		pojo.setCourseName(courseName);
		pojo.setCourseTerm(courseTerm);
		pojo.setScheduleFile(scheduleFile);
		
		return new CourseScheduleFileTestParameter(pojo);
		
	}
	  
	
	public String getScheduleFile() {
		return scheduleFile;
	}

	public void setScheduleFile(File scheduleFile) throws IOException {
		 
	 
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
	
	
}
