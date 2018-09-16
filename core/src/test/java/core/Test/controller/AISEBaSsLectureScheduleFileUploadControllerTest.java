package core.Test.controller;

import org.springframework.beans.factory.annotation.Autowired; 
 
public class AISEBaSsLectureScheduleFileUploadControllerTest 
												extends  LectureScheduleFileUploadControllerTest{

	@Autowired
	public AISEBaSsLectureScheduleFileUploadControllerTest() {
		super("Angewandte Informatik",
					"Sommersemester",
						"Bachelor Of Science", 
							"AISEXLSFileAsset", 
						     	"WiInfXLSFileAsset");
 
	}

}
