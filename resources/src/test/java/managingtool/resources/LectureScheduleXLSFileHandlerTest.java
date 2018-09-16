package managingtool.resources;

import org.junit.Test;
 
import static org.junit.Assert.assertTrue;
 
import java.io.IOException;  
import org.junit.BeforeClass;
import org.junit.FixMethodOrder; 
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import resources.components.elements.POJO.Persistence.AllLecturesPOJO;
import resources.components.filehandler.JSON.AllLecturesScheduleJSONFileHandler;
import resources.components.filehandler.XLS.LectureScheduleXLSFileHandler;
import resources.constraint.LectureConstraint;
import testcontext.FileHandlerTestApplicationContext; 

@ContextConfiguration( classes={  FileHandlerTestApplicationContext.class })

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class) 
public class LectureScheduleXLSFileHandlerTest {

	@Autowired
	private  LectureScheduleXLSFileHandler m_LectureScheduleXLSFileHandler;

	@Autowired
    private AllLecturesScheduleJSONFileHandler jsonFileHandlerToTest; 
	
	@Autowired
	private  LectureConstraint constraint;
	
	
	@BeforeClass 
	public static void initializePojo() throws IOException {
	
		
	} 
	
	@Test
	public void TESTA_testIfWeCanInitalizeRequestedXLSFileHandler() {
		
		if(!(m_LectureScheduleXLSFileHandler.equals(null))) {
			
			assertTrue(true); 
		}
		else {
			
			assertTrue(false); 
		}
	}
	
	@Test
	public void TESTB_testIfWeCanBuildAllLecturesPOJO() {
		
		AllLecturesPOJO allLectures = (AllLecturesPOJO) m_LectureScheduleXLSFileHandler.readFile("LectureScheduleAISESS");
	
		System.out.println("");
		jsonFileHandlerToTest.createFile("AllLecturesSchedule");
		System.out.println("");
		jsonFileHandlerToTest.writeToFile("AllLecturesSchedule", allLectures);
	}
	
	
	@Test
	public void TESTC_testIfWeCanHandleAllLecturesPOJO() {
		 
		 
		AllLecturesPOJO allLectures = (AllLecturesPOJO) jsonFileHandlerToTest.readFile("AllLecturesSchedule");
		
		System.out.println("");
		
		
	}
	
	
	
}
