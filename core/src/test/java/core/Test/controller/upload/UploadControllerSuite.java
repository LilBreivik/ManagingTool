package core.Test.controller.upload;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import core.Test.controller.upload.suitecases.UploadCourseFileControllerTest;
import core.Test.controller.upload.suitecases.UploadLectureFileControllerTest; 

@RunWith(Suite.class)

@Suite.SuiteClasses({UploadCourseFileControllerTest.class, 
					 UploadLectureFileControllerTest.class})

public class UploadControllerSuite {
	
}
