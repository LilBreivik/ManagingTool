package resources.FileWriter;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException; 
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
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO; 
import resources.components.filewriter.JSON.GeneralJSONFileWriter;
import resources.utils.pathmanager.PathManager;
import testcontext.FileHandlerTestApplicationContext;

@ContextConfiguration( classes={  FileHandlerTestApplicationContext.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(locations = "classpath:application.test.properties") 
@EnableAspectJAutoProxy(proxyTargetClass=true)
@RunWith(SpringJUnit4ClassRunner.class) 
public class JSONFileWriterTest {
	
	// JSON File Writer to test 

	private static GeneralJSONFileWriter testJSONFileWriter; 
			 
	// JSON File to test 
	
	private static File testJSONFile; 
				
	@Autowired
    @Qualifier("Path to Test Resources")
	private  PathManager pathManagerToTestFiles; 
				
	@Rule
	public TestName testName = new TestName();
				
	@Before
	public  void setUpTestGeneralJSONFileWriter() {
					
	      String JSONTestFileName = ""; 
					 
		  if(testName.getMethodName().equals("TESTA_SubTestA_CheckIfWeCanWriteToATemporaryAllLecturesJSONFile")){
				   		   
				JSONTestFileName = "TempAllLecturesPOJOFromAISEJSONFileAsset.json";
											
				testJSONFile = new File(pathManagerToTestFiles.getPathToOperateOn().toString(), JSONTestFileName);
							 
			    testJSONFileWriter = new GeneralJSONFileWriter<AllLecturesPOJO>( );
					     
		  } 
		  else if(testName.getMethodName().equals("TESTB_SubTestA_CheckIfWeCanWriteToATemporaryCourseScheduleJSONFile")){
				   		 	
				JSONTestFileName = "TempCourseSchedule.json";
						
			    // connection is not established 
							
			    testJSONFile = new File(pathManagerToTestFiles.getPathToOperateOn().toString(),  JSONTestFileName);
							 
			    testJSONFileWriter = new GeneralJSONFileWriter<PersistenceCourseSchedulePOJO >( );
					     
		  } 
    }
	
	@SuppressWarnings("unchecked")
	@Test
	public void TESTA_SubTestA_CheckIfWeCanWriteToATemporaryAllLecturesJSONFile() {
		
        System.out.println();
		 
		try {
			
			// At first we will create the files 
	        // we do it without the fileHandler, cause it will be tested in another unit test 
			
			
			testJSONFile.createNewFile();
			
			ObjectMapper mapper = new ObjectMapper();
			
			AllLecturesPOJO allLecturesPOJO = mapper.readValue(pathManagerToTestFiles.getPathOfFile( "AllLecturesPOJOFromAISEXLSFileAsset.json").toFile(), AllLecturesPOJO.class);
			 
			testJSONFileWriter.writeToFile(testJSONFile,  allLecturesPOJO);
			 
			assertArrayEquals("Binary files differ" , Files.readAllBytes(testJSONFile.toPath()), 
				Files.readAllBytes(pathManagerToTestFiles.getPathOfFile( "AllLecturesPOJOFromAISEXLSFileAsset.json"))); 
			  
			  
			
		} catch (FileNotFoundException e) {
		
			assertThat("Cannot find JSON File",   not( true ));
		} catch (ClassCastException e) {
		
			assertThat("Cannot process the JSON File",   not( true ));
		
		} catch (IOException e) {
		
			assertThat("Cannot create the JSON File",   not( true ));
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void TESTB_SubTestA_CheckIfWeCanWriteToATemporaryCourseScheduleJSONFile() {
		
        System.out.println();
		 
		try {
			
			// At first we will create the files 
	        // we do it without the fileHandler, cause it will be tested in another unit test 
			
			
			testJSONFile.createNewFile();
			
			ObjectMapper mapper = new ObjectMapper();
			
			PersistenceCourseSchedulePOJO  allLecturesPOJO = mapper.readValue(pathManagerToTestFiles.getPathOfFile( "CourseSchedule.json").toFile(), PersistenceCourseSchedulePOJO .class);
			 
			testJSONFileWriter.writeToFile(testJSONFile,  allLecturesPOJO);
			 
			assertArrayEquals("Binary files differ" , Files.readAllBytes(testJSONFile.toPath()), 
				Files.readAllBytes(pathManagerToTestFiles.getPathOfFile( "CourseSchedule.json"))); 
			  
			  
			
		} catch (FileNotFoundException e) {
		
			assertThat("Cannot find JSON File",   not( true ));
		} catch (ClassCastException e) {
		
			assertThat("Cannot process the JSON File",   not( true ));
		
		} catch (IOException e) {
		
			assertThat("Cannot create the JSON File",   not( true ));
		}
	}
	 

}
 