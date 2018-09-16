package core.Test.controller;

import org.springframework.beans.factory.annotation.Autowired; 
 
public class WiInfBaSsLectureScheduleFileUploadControllerTest 
												extends  LectureScheduleFileUploadControllerTest{

	@Autowired
	public WiInfBaSsLectureScheduleFileUploadControllerTest() {
		super("Wirtschaftsinformatik",
					"Sommersemester",
						"Bachelor Of Science", 
							"WiInfXLSFileAsset", 
							"AISEXLSFileAsset" );
 
	}

}
