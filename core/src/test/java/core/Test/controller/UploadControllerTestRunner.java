package core.Test.controller;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure; 

public class UploadControllerTestRunner{

	 
	public static void main(String[] args) {
	     
		Result result = JUnitCore.runClasses( 
											AISEBaSsCourseScheduleFileUploadControllerTest.class,
												WiinfBaSsCourseScheduleFileUploadControllerTest.class,
												 	AISEBaSsLectureScheduleFileUploadControllerTest .class,
													   WiInfBaSsLectureScheduleFileUploadControllerTest.class);
		
		
		
		for (Failure failure : result.getFailures()) {
	    
			System.out.println( failure.toString());
	    }
		
			 
	   System.out.println("-----> " + result.wasSuccessful());
	   
	}
	
}
