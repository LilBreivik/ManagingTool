package core.controller.parameter.schedule.upload;
 
import java.util.UUID;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.DatatypeConverter;

import core.controller.parameter.schedule.GeneralScheduleFileParam;
import core.controller.verifier.CourseVerifier; 
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO;
import resources.error.InternalError;
import resources.error.parameter.ParameterViolationError; 

/**
 * This Class will serve 
 * defines the general Parameter needed to handle 
 * Schedule File Uploads 
 * */

  
public abstract  class GenericScheduleFileUploadParam 
									extends   GeneralScheduleFileParam< PersistenceCourseSchedulePOJO >  {

	private File scheduleFile;
	 

	public GenericScheduleFileUploadParam(CourseVerifier verifier , String courseName, String courseDegree,
			String courseTerm, String scheduleFile) throws InternalError {
		
		super(verifier);
		
		final String tempFileName = UUID.randomUUID().toString();
		
		setCourseName(courseName);
		setCourseTerm(courseTerm);
		setCourseDegree(courseDegree);
		
	
	    try {
	    	
	    	String base64Image = scheduleFile.split(",")[1];
	    	
			Files.write(Paths.get( tempFileName ), DatatypeConverter.parseBase64Binary(base64Image));
		 
			
			setScheduleFile(Paths.get(tempFileName ).toFile());
	    } 
	    
	    
	    catch(IndexOutOfBoundsException indexErr) {
	    	 
	    	throw new ParameterViolationError("Die Request Parameter sind falsch");
	    }
	    catch (IOException e) {
	
			e.printStackTrace();
			
			throw new InternalError("Cannot create tmp File");
		} 
	}


	public File getScheduleFile() {
		return scheduleFile;
	}


	public void setScheduleFile(File scheduleFile) {
		this.scheduleFile = scheduleFile;
	}
  

}
