package core.Test.controller.download.suitecases;
 
import core.backend.REST.fileasset.download.controller.DownloadTemplateFileController;

public class DownloadTemplateFileControllerTest 
													extends  DownloadFileControllerTest {
		
	public DownloadTemplateFileControllerTest() {
	 
		p_controllerURL = DownloadTemplateFileController.DownloadTemplateFileURL;
		p_fileAssetName = "GeneralCourseScheduleTemplateFile"; 
		p_specificDescriptionMessage = "Template File Download";
	}

}
