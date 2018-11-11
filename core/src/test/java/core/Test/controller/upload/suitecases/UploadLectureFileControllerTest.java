package core.Test.controller.upload.suitecases;
 
import core.backend.REST.fileasset.upload.controller.UploadLectureFileController;

public class UploadLectureFileControllerTest 
												extends  UploadFileControllerTest {
	
	public UploadLectureFileControllerTest() {
	
		
		
		p_controllerURL = UploadLectureFileController.UploadLectureFileURL;
		 p_fileAssetName = "AISEXLSFileAsset.xls"; 
		p_specificDescriptionMessage = "Lecture File Upload";
	}

}
