package managingtool.resources;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
   
import java.util.ArrayList; 
import java.util.List;
  
import org.junit.FixMethodOrder; 
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import resources.components.elements.POJO.Lecture.LectureInformationPOJO;
import resources.components.elements.POJO.Persistence.LectureScheduleOfCoursePOJO;
import resources.components.elements.POJO.Persistence.Lectures.PersistenceLectureSemesterSchedulePOJO;
import resources.components.filehandler.JSON.LectureScheduleJSONFileHandler;
import resources.components.filehandler.XML.LectureScheduleXMLFileHandler;
import testcontext.FileHandlerTestApplicationContext; 

@ContextConfiguration( classes={ FileHandlerTestApplicationContext.class })

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class) 
public class LectureScheduleXMLFileHandlerTest {

	@Autowired
    private LectureScheduleJSONFileHandler  jsonFileHandlerToTest; 
	
	 
	@Autowired
    private LectureScheduleXMLFileHandler xmlFileHandler;
	
	private  LectureScheduleOfCoursePOJO   m_pojo; 
	
	
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
	public void TESTC_testIfWeReadTheLecturesFile() {
	
		try {
			
		
			m_pojo = ( LectureScheduleOfCoursePOJO ) xmlFileHandler.readFile("LectureSchedule");
		
		}
		catch(ClassCastException  castEx) {
			
			assertTrue(false);
		}
	
	
		assertTrue(true);
	}
	
	@Test
	public void TESTD_testIfWeWriteTheLecturesJSONFile() {
	 
		LectureScheduleOfCoursePOJO  pojo = ( LectureScheduleOfCoursePOJO ) xmlFileHandler.readFile("LectureSchedule");
	  
		jsonFileHandlerToTest.writeToFile("LectureSchedule", pojo);
	}

	@Test
	public void TESTE_testIfWeWriteTheLecturesJSONFile() {
	 
        try {
			
			m_pojo = ( LectureScheduleOfCoursePOJO ) xmlFileHandler.readFile("LectureSchedule");
		
		}
		catch(ClassCastException  castEx) {
			
			assertTrue(false);
		}
		
        
        Object test = jsonFileHandlerToTest.readFile("LectureSchedule"); 
		
		
        LectureScheduleOfCoursePOJO  pojo = ( LectureScheduleOfCoursePOJO ) jsonFileHandlerToTest.readFile("LectureSchedule"); 
		
		System.out.println("");
		
		assertThat("Degree is not equal ",  pojo.getCourseDegree(), is(m_pojo.getCourseDegree()) );
		assertThat("Term is not equal ",  pojo.getCourseTerm(), is(m_pojo.getCourseTerm()) );
		assertThat("Name is not equal ",  pojo.getCourseName(), is(m_pojo.getCourseName()) );
		

		List <PersistenceLectureSemesterSchedulePOJO> lecturesemesterSchedule   =  new ArrayList(pojo.getLecturesinAllSemesters());

		List <PersistenceLectureSemesterSchedulePOJO> m_lecturesemesterSchedule   =  new ArrayList(m_pojo.getLecturesinAllSemesters()); 
		
		
		for(int itr = 0; itr <  m_lecturesemesterSchedule .size(); itr += 1) {
			
			assertThat("semesterNr is not equal  ",  
												lecturesemesterSchedule.get(itr).getSemesterNr(), 
												   is(m_lecturesemesterSchedule.get(itr).getSemesterNr()) );
										
			
			List<LectureInformationPOJO> lecturesInSemester = new ArrayList(lecturesemesterSchedule.get(itr).getM_CollectionOfLecturesInSemester());
			
			List<LectureInformationPOJO> m_lecturesInSemester = new ArrayList(m_lecturesemesterSchedule.get(itr).getM_CollectionOfLecturesInSemester());
			 
			
			for(int ctr = 0; ctr < m_lecturesInSemester.size(); ctr += 1) {
				
				assertThat("lecture name is not equal ",  
						         lecturesInSemester.get(ctr).getName() , 
									   is(m_lecturesInSemester.get(ctr).getName() ) );
				
				assertThat("practice is not equal ",  
				         lecturesInSemester.get(ctr).getPractice() , 
							   is(m_lecturesInSemester.get(ctr).getPractice() ) );
				
				assertThat("shortcut  is not equal ",  
				         lecturesInSemester.get(ctr).getShortcut(), 
							   is(m_lecturesInSemester.get(ctr).getShortcut()) );
				
			}
			
			
		}
		
	}
}
