package core.Test.controller.download;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import core.Test.controller.download.suitecases.DownloadCourseFileControllerTest;
import core.Test.controller.download.suitecases.DownloadLectureFileControllerTest; 

@RunWith(Suite.class)

@Suite.SuiteClasses( {DownloadCourseFileControllerTest.class, 
						DownloadLectureFileControllerTest.class})

public class DownloadControllerSuite {
	
}
