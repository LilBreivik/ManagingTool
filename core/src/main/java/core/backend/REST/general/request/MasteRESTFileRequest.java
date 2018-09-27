package core.backend.REST.general.request;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import javax.xml.bind.DatatypeConverter; 
import resources.error.InternalError;
import resources.error.parameter.ParameterViolationError;

/**
 * This class defines the 
 * expected request parameter , that 
 * delivers a certain file 
 * */


public class MasteRESTFileRequest
										extends MasterRESTCustomRequest{

	// the file will  be encoded to BASE64 
	// so if will be returned as a File Object 
	private File scheduleFile;
	
	public MasteRESTFileRequest(String courseName, String courseDegree, String courseTerm, String scheduleFile) {
		super(courseName, courseDegree, courseTerm);
		 
		// at first we will temporarily store the file , 
		// with a ranomly choosen id 
		final String tempFileName = UUID.randomUUID().toString();
		  
	    try {
	    	
	    	// after this we will retrieve the base64 encoded file 
	    	String base64Image = scheduleFile.split(",")[1];
	    	
	    	// and we save it to the temp files, with the randomly choosen name
			Files.write(Paths.get( tempFileName ), DatatypeConverter.parseBase64Binary(base64Image));
		 
			
			setScheduleFile(Paths.get(tempFileName ).toFile());
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
		catch(ParameterViolationError fileValidationError) {
			
			this.scheduleFile.delete();
			throw fileValidationError;
		}
	} 
	
	public File getScheduleFile() {
		return scheduleFile;
	}


	public void setScheduleFile(File scheduleFile) {
		this.scheduleFile = scheduleFile;
	}
   
}