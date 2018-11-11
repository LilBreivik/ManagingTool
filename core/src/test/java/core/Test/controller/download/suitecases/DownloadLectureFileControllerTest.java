package core.Test.controller.download.suitecases;
 
import core.backend.REST.fileasset.download.controller.DownloadLectureFileController;

public class DownloadLectureFileControllerTest 
												extends  DownloadFileControllerTest {
		
	public DownloadLectureFileControllerTest  () {
	
		p_controllerURL = DownloadLectureFileController.DownloadLectureFileURL;
		p_fileAssetName = "WiInfXLSFileAsset.xls"; 
		p_specificDescriptionMessage = "Lecture File Download";
	}

}
