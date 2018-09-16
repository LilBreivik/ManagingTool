package core.TestContext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.DatatypeConverter;

import core.controller.parameter.schedule.ScheduleParam; 

/**
 * This class defines 
 * 
 * the parameter needed to upload
 * a schedule File ....
 * 
 * Actually it extends the common parameter 
 * with a file asset
 * 
 * */
 
public class ScheduleFileUploadParam 	
										extends ScheduleParam {

	protected String scheduleFile; 
	
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
}
