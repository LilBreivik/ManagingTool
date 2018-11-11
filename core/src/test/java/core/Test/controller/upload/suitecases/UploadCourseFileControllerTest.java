package core.Test.controller.upload.suitecases;

 

import core.backend.REST.fileasset.upload.controller.UploadCourseFileController;

public class UploadCourseFileControllerTest 
												extends  UploadFileControllerTest {

	public UploadCourseFileControllerTest() {
		
		 p_controllerURL = UploadCourseFileController.UploadCourseFileURL;
		 p_fileAssetName = "LectureScheduleAISEBa"; 
		 p_specificDescriptionMessage = "Course File Upload";
	}
	 
}
