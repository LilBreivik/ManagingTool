package core.Test.controller.delete;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import core.Test.controller.delete.suitecases.DeleteCourseFileControllerTest;
import core.Test.controller.delete.suitecases.DeleteLectureFileControllerTest; 


@RunWith(Suite.class)

@Suite.SuiteClasses({DeleteCourseFileControllerTest.class, 
					DeleteLectureFileControllerTest.class})

public class DeleteControllerSuite {
	
}
