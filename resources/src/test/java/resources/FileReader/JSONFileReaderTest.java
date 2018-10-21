package resources.FileReader;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import resources.components.elements.POJO.Persistence.AllLecturesPOJO;
import resources.components.elements.POJO.Persistence.LectureScheduleOfCoursePOJO;
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO;
import resources.components.filereader.JSON.GeneralJSONFileReader;
import resources.utils.pathmanager.PathManager;
import testcontext.FileHandlerTestApplicationContext;

@ContextConfiguration( classes={  FileHandlerTestApplicationContext.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(locations = "classpath:application.test.properties") 
@EnableAspectJAutoProxy(proxyTargetClass=true)
@RunWith(SpringJUnit4ClassRunner.class) 
public class JSONFileReaderTest {

	// JSON File Reader to test 

	private static GeneralJSONFileReader testJSONFileReader; 
	 
	// JSON File to test 
		
	private static File testJSONFile; 
	
	@Autowired
	@Qualifier("Path to Test Resources")
	private  PathManager pathManagerToTestFiles; 
	
	@Rule
    public TestName testName = new TestName();
	
	@Before
	public  void setUpTestGeneralJSONFileReader() {
		
		 String JSONTestFileName = ""; 
		 
	     if(testName.getMethodName().equals("TESTA_SubTestA_CheckIfWeCanReadAllLecturesFromAISEXLSFileAssetJSONFile")){
	   		   
	    	 JSONTestFileName = "AllLecturesPOJOFromAISEXLSFileAsset.json";
								
			 testJSONFile = new File(pathManagerToTestFiles.getPathToOperateOn().toString(), JSONTestFileName);
				 
		     testJSONFileReader = new GeneralJSONFileReader<AllLecturesPOJO>(AllLecturesPOJO.class);
		     
    	 } 
	     else if(testName.getMethodName().equals("TESTB_SubTestA_CheckIfWeCanReadCourseScheduleJSONFile")){
	   		 	
			 JSONTestFileName = "CourseSchedule.json";
			 
		     // connection is not established 
				
			 testJSONFile = new File(pathManagerToTestFiles.getPathToOperateOn().toString(),  JSONTestFileName);
				 
		     testJSONFileReader = new GeneralJSONFileReader<PersistenceCourseSchedulePOJO >(PersistenceCourseSchedulePOJO .class);
		     
	     } 
	}
	
	@Test 
	public void TESTA_SubTestA_CheckIfWeCanReadAllLecturesFromAISEXLSFileAssetJSONFile() {
		
        System.out.println();
		
		try {
			
			 AllLecturesPOJO pojo = (AllLecturesPOJO) testJSONFileReader.readFile(testJSONFile);
		
			 assertTrue(true);
			
		} catch (FileNotFoundException e) {
		
			assertThat("Cannot find JSON File",   not( true ));
		} catch (ClassCastException e) {
		
			assertThat("Cannot process the JSON File",   not( true ));
		
		} catch (IOException e) {
		
			assertThat("Cannot access the JSON File",   not( true ));
		}
	} 
	
	@Test 
	public void TESTB_SubTestA_CheckIfWeCanReadCourseScheduleJSONFile() {
		
        System.out.println();
		
		try {
			
			LectureScheduleOfCoursePOJO pojo = (LectureScheduleOfCoursePOJO) testJSONFileReader.readFile(testJSONFile);
		
			 assertTrue(true);
			
		} catch (FileNotFoundException e) {
		
			assertThat("Cannot find JSON File",   not( true ));
		} catch (ClassCastException e) {
		
			assertThat("Cannot process the JSON File",   not( true ));
		
		} catch (IOException e) {
		
			assertThat("Cannot access the JSON File",   not( true ));
		}
	} 
}
