package managingtool.resources;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
 
import java.io.IOException; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
 
import org.junit.BeforeClass;
import org.junit.FixMethodOrder; 
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import resources.components.elements.POJO.Course.CourseSchedulePOJO;
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO;
import resources.components.filehandler.JSON.PersistenceCourseScheduleJSONFileHandler;
import resources.components.filehandler.XML.CourseScheduleXMLFileHandler;
import testcontext.FileHandlerTestApplicationContext;
 
@ContextConfiguration( classes={  FileHandlerTestApplicationContext.class })

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class) 
public class CourseScheduleXMLFileHandlerTest {

	@Autowired
    private PersistenceCourseScheduleJSONFileHandler jsonFileHandlerToTest; 
	
	 
	@Autowired
    private CourseScheduleXMLFileHandler xmlFileHandler;
	
	
	@BeforeClass 
	public static void initializePojo() throws IOException {
	
		
	} 
	
	@Test
	public void TESTA_testIfWeCanInitalizeRequestedXMLFileHandler() {
		
		if(!(xmlFileHandler.equals(null))) {
			
			assertTrue(true); 
		}
		else {
			
			assertTrue(false); 
		}
	}
	
	@Test
	public void TESTB_testInitialization() {
		  
		PersistenceCourseSchedulePOJO courseSchedulePOJO =  (PersistenceCourseSchedulePOJO) xmlFileHandler.readFile("CourseSchedule");
		
		
		List<String> expectedResults  = Arrays.asList(
		
				"Course Name Angewandte Informatik",
				"Course Degree Bachelor Of Science",
				"Course Term Wintersemester",
				"Course Name Angewandte Informatik",
				"Course Degree Bachelor Of Science",
				"Course Term Sommersemester",
				"Course Name Angewandte Informatik",
				"Course Degree Master Of Science",
				"Course Term Wintersemester",
				"Course Name Angewandte Informatik",
				"Course Degree Master Of Science",
				"Course Term Sommersemester",
				"Course Name Wirtschaftsinformatik",
				"Course Degree Bachelor Of Science",
				"Course Term Wintersemester",
				"Course Name Wirtschaftsinformatik",
				"Course Degree Bachelor Of Science",
				"Course Term Sommersemester",
				"Course Name Wirtschaftsinformatik",
				"Course Degree Master Of Science",
				"Course Term Wintersemester",
				"Course Name Wirtschaftsinformatik",
				"Course Degree Master Of Science",
				"Course Term Sommersemester"
				
		 );
		
		
		List<String> receivedResult = new ArrayList<String>(); 
		
		for(CourseSchedulePOJO pojo : courseSchedulePOJO.getCoursesSchedulePOJO()) {
			
			System.out.println("Course Name " + pojo.getCourseName());
			System.out.println("Course Degree " + pojo.getCourseDegree());
			System.out.println("Course Term " + pojo.getCourseTerm());
			
			receivedResult.add("Course Name " + pojo.getCourseName());
			receivedResult.add("Course Degree " + pojo.getCourseDegree());
			receivedResult.add("Course Term " + pojo.getCourseTerm());
				 
		}
		
		assertThat("The lists are unequal " ,  receivedResult , is(expectedResults));
		
	}
 
	
	 
	@Test
	public void TESTC_testIfUserAndChangingDateWasSubmitted() {
		
		PersistenceCourseSchedulePOJO courseSchedulePOJO =  (PersistenceCourseSchedulePOJO) xmlFileHandler.readFile("CourseSchedule");
		  
		for(CourseSchedulePOJO pojo : courseSchedulePOJO.getCoursesSchedulePOJO()) {
			
			if(!(pojo.getChangedAt() != 0 && pojo.getChangedBy() != null)) {
				
				assertTrue(false);
			}
		}
	}
 
	@Test
	public void TESTD_testIfWeCanCreatePOJOForCourseScheduleXMLFile() {
		
		PersistenceCourseSchedulePOJO courseSchedulePOJO =  (PersistenceCourseSchedulePOJO) xmlFileHandler.readFile("CourseSchedule");
		  
		
		jsonFileHandlerToTest.createFile("CourseSchedule");
		
		jsonFileHandlerToTest.writeToFile("CourseSchedule", courseSchedulePOJO);
	}
	 
	@Test
	public void TESTE_testIfWeCanReadFromJSONAndConvertBackToPOJO() {
	
		try {
			
			PersistenceCourseSchedulePOJO courseSchedulePOJOThatWasread =  (PersistenceCourseSchedulePOJO) jsonFileHandlerToTest.readFile("CourseSchedule");

		}
		catch(ClassCastException classCastEx)
		{
			assertTrue(false);
		}
	
	}
	
}
