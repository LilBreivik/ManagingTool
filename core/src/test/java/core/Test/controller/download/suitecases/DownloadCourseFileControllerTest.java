package core.Test.controller.download.suitecases;
 
import core.backend.REST.fileasset.download.controller.DownloadCourseFileController; 

public class DownloadCourseFileControllerTest 
												extends  DownloadFileControllerTest {
	
	public DownloadCourseFileControllerTest () {
		 
		 p_controllerURL = DownloadCourseFileController.DownloadCourseFileURL;
		 p_fileAssetName = "LectureScheduleAISEBa"; 
		 p_specificDescriptionMessage = "Course File Download";
		 
		 
	}
	
}
 