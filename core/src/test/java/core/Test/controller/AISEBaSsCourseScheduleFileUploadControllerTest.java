package core.Test.controller;

import org.springframework.beans.factory.annotation.Autowired; 
 
public class AISEBaSsCourseScheduleFileUploadControllerTest 
												extends  CourseScheduleFileUploadControllerTest{

	@Autowired
	public AISEBaSsCourseScheduleFileUploadControllerTest() {
		super("Angewandte Informatik",
					"Sommersemester",
						"Bachelor Of Science", 
							"LectureScheduleAISEBa");

		 
		setM_wrongCourseDegree("BachelorOfScience");
		setM_wrongCourseName("AngewandteInformatik");
		setM_wrongCourseTerm("Sommersemestddder");
	} 

}
