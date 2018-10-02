package managingtool.resources;

import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import resources.components.elements.POJO.Schedule.CoursePOJO;
import resources.components.filehandler.JSON.CourseScheduleJSONFileHandler;
import resources.components.filehandler.JSON.SynthesizedCourseScheduleJSONFileHandler;
import resources.components.filehandler.filesynthesizer.LectureScheduleSynthesizer;
import testcontext.FileHandlerTestApplicationContext;

@ContextConfiguration( classes={ FileHandlerTestApplicationContext.class})

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class) 
public class SynthesizingLectureScheduleWithAllLecturesTest {

	 
	@Autowired
	private LectureScheduleSynthesizer m_synthesizer;
	
	@Autowired 
	private CourseScheduleJSONFileHandler m_jsonFileHanlder;
	
	
	 
	public void TESTA_testIfWeCanCreateSynthesizer() {
	 
    	
		if(!(m_synthesizer.equals(null))) {
			
			assertTrue(true); 
		}
		else {
			
			assertTrue(false); 
		}
	}
	
	
	 @Test
	public void TESTB_testIfWeCanCreateSynthsizedJSON() {
	 
		CoursePOJO pojo = (CoursePOJO) m_synthesizer.synthesizeAssets("LectureScheduleAISESS"); 
		 
		m_jsonFileHanlder.createFile("CourseScheduleAISS");
		
		m_jsonFileHanlder.writeToFile("CourseScheduleAISS", pojo);
		
		System.out.println(pojo);
		
	} 
}
