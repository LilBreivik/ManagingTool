package resources.FileReader;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

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
import resources.components.elements.POJO.Persistence.LectureScheduleOfCoursePOJO;
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO;
import resources.components.filereader.XML.XMLFileReader;
import resources.components.filereader.XML.XMLFileReaderManager; 
import resources.fileconnection.XMLFileConnection; 
import resources.utils.pathmanager.PathManager;
import testcontext.FileHandlerTestApplicationContext;

@ContextConfiguration( classes={  FileHandlerTestApplicationContext.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(locations = "classpath:application.test.properties") 
@EnableAspectJAutoProxy(proxyTargetClass=true)
@RunWith(SpringJUnit4ClassRunner.class) 
public class XMLFileReaderTest {

	// XML File Reader to test 

	private static XMLFileReader testXMLFileReader; 
 
	// XML File to test 
	
	private static File testXMLFile; 
	
	@Autowired
	@Qualifier("Path to Test Resources")
	private  PathManager pathManagerToTestFiles; 
	 
	@Autowired
	@Qualifier("XMLFileReaderManager for LectureScheduleXMLFile")  
	private XMLFileReaderManager<LectureScheduleOfCoursePOJO> fileReaderManagerForLectureScheduleXMLFile;
	
	@Autowired 
	@Qualifier("XMLFileReaderManager for GeneralCourseScheduleTemplateXMLFile")  
	private XMLFileReaderManager<LectureScheduleOfCoursePOJO> fileReaderManagerForGeneralCourseScheduleTemplateXMLFile;
	
	@Autowired
	@Qualifier("XMLFileReaderManager for CourseScheduleXMLFile") 
	private XMLFileReaderManager<PersistenceCourseSchedulePOJO> fileReaderManagerForCourseScheduleXMLFile;
	
	
	@Rule
    public TestName testName = new TestName();
	
	@Before
	public  void setUpTestXMLFileReader() {
		
		 String XMLTestFileName = ""; 
		
		 XMLFileConnection testXMLFileConnection = null; 
		 
	     if(testName.getMethodName().equals("TESTA_SubTestA_CheckIfWeCanReadAnLectureScheduleXMLFile")){
	   		   
	    	 XMLTestFileName = "LectureScheduleAISEBa";
				
			 testXMLFileConnection = new XMLFileConnection( pathManagerToTestFiles.getPathToOperateOn());
				
		     // connection is not established 
				
		     testXMLFile = new File(testXMLFileConnection.getPath().toString(), XMLTestFileName);
				 
		     testXMLFileReader = new XMLFileReader<LectureScheduleOfCoursePOJO>(testXMLFileConnection, fileReaderManagerForLectureScheduleXMLFile);
		     
    	 } 
	     else if(testName.getMethodName().equals("TESTB_SubTestA_CheckIfWeCanReadAGeneralCourseScheduleTemplateFile")){
	   		 	
			 XMLTestFileName = "GeneralCourseScheduleTemplateFile";
				
			 testXMLFileConnection = new XMLFileConnection( pathManagerToTestFiles.getPathToOperateOn());
				
		     // connection is not established 
				
		     testXMLFile = new File(testXMLFileConnection.getPath().toString(), XMLTestFileName);
				 
		     testXMLFileReader = new XMLFileReader<LectureScheduleOfCoursePOJO>(testXMLFileConnection,  fileReaderManagerForGeneralCourseScheduleTemplateXMLFile);
		     
	     }
		else if(testName.getMethodName().equals("TESTC_SubTestA_CheckIfWeCanReadCourseSchedule")){
	   		 	
			 XMLTestFileName = "CourseSchedule";
				
			 testXMLFileConnection = new XMLFileConnection( pathManagerToTestFiles.getPathToOperateOn());
				
		     // connection is not established 
				
		     testXMLFile = new File(testXMLFileConnection.getPath().toString(), XMLTestFileName);
				 
		     testXMLFileReader = new XMLFileReader<PersistenceCourseSchedulePOJO>(testXMLFileConnection, fileReaderManagerForCourseScheduleXMLFile);
	     }
	 
	}
	
	
	@Test 
	public void TESTA_SubTestA_CheckIfWeCanReadAnLectureScheduleXMLFile() {
		
        System.out.println();
		
		try {
			
			 LectureScheduleOfCoursePOJO pojo =  (LectureScheduleOfCoursePOJO) testXMLFileReader.readFile(testXMLFile);
		
			 ObjectMapper mapper = new ObjectMapper();
			 	
			 String parsedJSON = mapper.writeValueAsString(pojo);
			 
			 
			 // we read the expected correct pojo file 
			 
		     byte[] expectedParsedContent = Files.readAllBytes(pathManagerToTestFiles.getPathOfFile( "LectureScheduleAISEBaXML.json"));
				
		  
		     String jsonToCompare  = new String(expectedParsedContent , Charset.forName("UTF-8"));
			
			 assertThat("The parsed pojo does not match the expected one" , parsedJSON .equals(jsonToCompare) , not( false ));
			   
			
		} catch (FileNotFoundException e) {
		
			assertThat("Cannot find XML File",   not( true ));
		} catch (ClassCastException e) {
		
			assertThat("Cannot process the XML File",   not( true ));
		
		} catch (IOException e) {
		
			assertThat("Cannot access the XML File",   not( true ));
		}
	} 
	 
	@Test 
	public void TESTB_SubTestA_CheckIfWeCanReadAGeneralCourseScheduleTemplateFile() {
		
        System.out.println();
		
		try {
			
			 LectureScheduleOfCoursePOJO pojo =  (LectureScheduleOfCoursePOJO) testXMLFileReader.readFile(testXMLFile);
		
			 ObjectMapper mapper = new ObjectMapper();
			 	
			 String parsedJSON = mapper.writeValueAsString(pojo);
			 
			 
			 // we read the expected correct pojo file 
			 
		     byte[] expectedParsedContent = Files.readAllBytes(pathManagerToTestFiles.getPathOfFile( "GeneralCourseScheduleTemplateFile.json"));
				
		  
		     String jsonToCompare  = new String(expectedParsedContent , Charset.forName("UTF-8"));
			
			 assertThat("The parsed pojo does not match the expected one" , parsedJSON .equals(jsonToCompare) , not( false ));
			   
			
		} catch (FileNotFoundException e) {
		
			assertThat("Cannot find XML File",   not( true ));
		} catch (ClassCastException e) {
		
			assertThat("Cannot process the XML File",   not( true ));
		
		} catch (IOException e) {
		
			assertThat("Cannot access the XML File",   not( true ));
		}
	} 
	
	@Test 
	public void TESTC_SubTestA_CheckIfWeCanReadCourseSchedule() {
		
        System.out.println();
		
		try {
			
			 ObjectMapper mapper = new ObjectMapper();
			
			 PersistenceCourseSchedulePOJO pojo =  (PersistenceCourseSchedulePOJO) testXMLFileReader.readFile(testXMLFile);
	  
			 // we read the expected correct pojo file 
			  
			 PersistenceCourseSchedulePOJO pojoToCompare = mapper.readValue(pathManagerToTestFiles.getPathOfFile( "CourseSchedule.json").toFile(), PersistenceCourseSchedulePOJO.class);
			 
			  
			 assertThat("The parsed pojo does not match the expected one" ,  pojo .equals(pojoToCompare) , not( false ));
			   
			
		} catch (FileNotFoundException e) {
		
			assertThat("Cannot find XML File",   not( true ));
		} catch (ClassCastException e) {
		
			assertThat("Cannot process the XML File",   not( true ));
		
		} catch (IOException e) {
		
			assertThat("Cannot access the XML File",   not( true ));
		}
	}
	
	
}
