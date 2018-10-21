package resources;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
 
import java.io.IOException; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before; 
import org.junit.FixMethodOrder; 
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner; 
import resources.components.elements.POJO.Course.CourseSchedulePOJO;
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO;
import resources.components.filehandler.JSON.general.GeneralJSONFileHandler; 
import resources.components.filehandler.XML.general.RawXMLFileHandler;
import resources.components.filereader.XML.XMLFileReaderManager;
import resources.utils.pathmanager.PathManager;
import testcontext.FileHandlerTestApplicationContext;
 
@ContextConfiguration( classes={  FileHandlerTestApplicationContext.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(locations = "classpath:application.test.properties") 
@EnableAspectJAutoProxy(proxyTargetClass=true)
@RunWith(SpringJUnit4ClassRunner.class) 
public class CourseScheduleXMLFileHandlerTest {

	 
	@Autowired 
	@Qualifier("Path to Test Resources")
	private PathManager pathManagerToTestResources;
	
	@Autowired
	@Qualifier("XMLFileReaderManager for CourseScheduleXMLFile")  
	private XMLFileReaderManager<PersistenceCourseSchedulePOJO> xmlFileReaderManager;
	
	
	private  RawXMLFileHandler<PersistenceCourseSchedulePOJO> xmlFileHandler;
	 
	private GeneralJSONFileHandler<PersistenceCourseSchedulePOJO> jsonFileHandlerToTest;
	
	@Before
	public void setUp() throws IOException {
	
		xmlFileHandler = new RawXMLFileHandler<PersistenceCourseSchedulePOJO>(pathManagerToTestResources, xmlFileReaderManager);
		
		jsonFileHandlerToTest =  new GeneralJSONFileHandler<PersistenceCourseSchedulePOJO>(PersistenceCourseSchedulePOJO.class, 
				pathManagerToTestResources);
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
		
		System.out.println();
		
		PersistenceCourseSchedulePOJO courseSchedulePOJO =  (PersistenceCourseSchedulePOJO) xmlFileHandler.readXMLFile("CourseSchedule");
		
		
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
		
		PersistenceCourseSchedulePOJO courseSchedulePOJO =  (PersistenceCourseSchedulePOJO) xmlFileHandler.readXMLFile("CourseSchedule");
		  
		for(CourseSchedulePOJO pojo : courseSchedulePOJO.getCoursesSchedulePOJO()) {
			
			if(!(pojo.getChangedAt() != 0 && pojo.getChangedBy() != null)) {
				
				assertTrue(false);
			}
		}
	}
 
	@Test
	public void TESTD_testIfWeCanCreatePOJOForCourseScheduleXMLFile() {
		
		PersistenceCourseSchedulePOJO courseSchedulePOJO =  (PersistenceCourseSchedulePOJO) xmlFileHandler.readXMLFile("CourseSchedule");
		  
		
		jsonFileHandlerToTest.createJSONFile("CourseSchedule.json");
		
		jsonFileHandlerToTest.writeToJSONFile("CourseSchedule.json", courseSchedulePOJO);
	}
	
	@Test
	public void TESTE_testIfWeCanReadFromJSONAndConvertBackToPOJO() {
	
		try {
			
			PersistenceCourseSchedulePOJO courseSchedulePOJOThatWasread =  (PersistenceCourseSchedulePOJO) jsonFileHandlerToTest.readJSONFile("CourseSchedule.json");

		}
		catch(ClassCastException classCastEx)
		{
			assertTrue(false);
		}
	
	}
	
}
