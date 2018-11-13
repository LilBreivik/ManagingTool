package core.backend.REST.fileasset.upload.parameter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID; 
import javax.xml.bind.DatatypeConverter; 
import core.backend.REST.general.request.schedule.file.RESTScheduleFileRequest;
import resources.components.elements.POJO.Schedule.CourseScheduleFilePOJO;
import resources.error.InternalError;
import resources.error.parameter.ParameterViolationError; 
import resources.fileconnection.GeneralFileConnection;


/**
 * Class that describes the general Parameter 
 * for an upload request
 * 
 * */
  
  
 
public  class UploadFileParameter <Connection extends GeneralFileConnection>

									extends RESTScheduleFileRequest<CourseScheduleFilePOJO> {
 
	// the file will  be encoded to BASE64 
	// so if will be returned as a File Object 
	private File scheduleFile; 
	
	protected Connection p_Connection; 
	 
	protected final String uploadFileRequestParameter = "courseScheduleFile"; 
	
	  
	public  UploadFileParameter(CourseScheduleFilePOJO pojo) {
		super( pojo);
		 
		 
		
		// at first we will temporarily store the file , 
		// with a ranomly choosen id 
		final String tempFileName = UUID.randomUUID().toString();
		  
	    try {
	    	
	    	// after this we will retrieve the base64 encoded file 
	    	String base64Image = pojo.getScheduleFile().split(",")[1];
	    	
	    	// and we save it to the temp files, with the randomly choosen name
			Files.write(Paths.get( tempFileName ), DatatypeConverter.parseBase64Binary(base64Image));
		 	
			scheduleFile = Paths.get(tempFileName ).toFile();
			 
	    } 
	    
	    catch(IndexOutOfBoundsException indexErr) {
	    	 
	    	throw new ParameterViolationError("Die Request Parameter sind falsch");
	    }
	    catch (IOException e) {
	 
			throw new InternalError("Cannot create tmp File");
		} 
	}
  
	 
	@Override
	public void verifyParameter() throws ParameterViolationError {
		
		try {
			
			super.verifyParameter();
		}
		catch(ParameterViolationError parameterValidationError) {
			
			this.scheduleFile.delete();
			
			throw parameterValidationError;
		}
	} 
	
	
	
	public File getScheduleFile() {
		
		
		return scheduleFile;
	}
 
}