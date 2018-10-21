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
import org.junit.BeforeClass;
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
import resources.components.filereader.XLS.XLSFileReader;
import resources.constraint.LectureConstraint;
import resources.error.ConnectionError;
import resources.fileconnection.XLSFileConnection;
import resources.utils.pathmanager.PathManager;
import testcontext.FileHandlerTestApplicationContext;

@ContextConfiguration( classes={  FileHandlerTestApplicationContext.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(locations = "classpath:application.test.properties") 
@EnableAspectJAutoProxy(proxyTargetClass=true)
@RunWith(SpringJUnit4ClassRunner.class) 
public class XLSFileReaderTest {

	// XLS File Reader to test 

	private static XLSFileReader testXLSFileReader; 
 
	// XS File to test 
	
	private static File testXLSFile; 
	
	@Autowired
	@Qualifier("Path to Test Resources")
	private  PathManager pathManagerToTestFiles; 
	
	
	// constraint that  is used to parse the xls file 
	
	@Autowired 
	private  LectureConstraint lectureConstraint; 
	
	
	@Rule
    public TestName testName = new TestName();
	
	@Before
	public  void setUpTestXLSFileReader() {
		
		 String XLSTestFileName = ""; 
		
		 XLSFileConnection testXLSFileConnection = null; 
		 
	     if(testName.getMethodName().equals("TESTA_SubTestA_CheckIfWeCanReadAnXLSFile")){
	   		   
	    	 XLSTestFileName = "AISEXLSFileAsset";
				
			 testXLSFileConnection = new XLSFileConnection( pathManagerToTestFiles.getPathToOperateOn());
				
		     // connection is not established 
				
		     testXLSFile = new File(testXLSFileConnection.getPath().toString(), XLSTestFileName);
				 
		     testXLSFileReader = new XLSFileReader(testXLSFileConnection, lectureConstraint);
		     
    	 } 
		 else if(testName.getMethodName().equals("TESTA_SubTestB_CheckIfWeCannotReadAWrongXLSFile")){
	   		 	
			 XLSTestFileName = "WrongXLSFileAssets";
				
			 testXLSFileConnection = new XLSFileConnection( pathManagerToTestFiles.getPathToOperateOn());
				
		     // connection is not established 
				
		     testXLSFile = new File(testXLSFileConnection.getPath().toString(), XLSTestFileName);
				 
		     testXLSFileReader = new XLSFileReader(testXLSFileConnection, lectureConstraint);
	     }
		 else if(testName.getMethodName().equals("TESTA_SubTestC_CheckIfWeCannotReadAWrongXLSFile")){
	   		 	
			 XLSTestFileName = "InvalidAISEXLSFileAsset";
				
			 testXLSFileConnection = new XLSFileConnection( pathManagerToTestFiles.getPathToOperateOn());
				
		     // connection is not established 
				
		     testXLSFile = new File(testXLSFileConnection.getPath().toString(), XLSTestFileName);
				 
		     testXLSFileReader = new XLSFileReader(testXLSFileConnection, lectureConstraint);
	     } 
	}
	 
	
	@Test 
	public void TESTA_SubTestA_CheckIfWeCanReadAnXLSFile() {
		
        System.out.println();
		
		try {
			
			 AllLecturesPOJO pojo = 	testXLSFileReader.readFile(testXLSFile);
		
			 ObjectMapper mapper = new ObjectMapper();
			 	
			 String parsedJSON = mapper.writeValueAsString(pojo);
			 
			 
			 // we read the expected correct pojo file 
			 
			 byte[] expectedParsedContent = Files.readAllBytes(pathManagerToTestFiles.getPathOfFile( "AllLecturesPOJOFromAISEXLSFileAsset.json"));
				
			 
			 String jsonToCompare  = new String(expectedParsedContent , Charset.forName("UTF-8"));
			
			 assertThat("The parsed pojo does not match the expected one" , parsedJSON .equals(jsonToCompare) , not( false ));
			  
			  
			
		} catch (FileNotFoundException e) {
		
			assertThat("Cannot find XLS File",   not( true ));
		} catch (ClassCastException e) {
		
			assertThat("Cannot process the XLS File",   not( true ));
		
		} catch (IOException e) {
		
			assertThat("Cannot access the XLS File",   not( true ));
		}
	} 
	
	
	@Test 
	public void TESTA_SubTestB_CheckIfWeCannotReadAWrongXLSFile() {
		
        System.out.println();
		
		try {
			
			 AllLecturesPOJO pojo = testXLSFileReader.readFile(testXLSFile);
		
			 ObjectMapper mapper = new ObjectMapper();
			 	
			 String parsedJSON = mapper.writeValueAsString(pojo);
			 
			 
			 // we read the expected correct pojo file 
			 
			 byte[] expectedParsedContent = Files.readAllBytes(pathManagerToTestFiles.getPathOfFile( "AllLecturesPOJOFromAISEXLSFileAsset.json"));
				
			 
			 String jsonToCompare  = new String(expectedParsedContent , Charset.forName("UTF-8"));
			
			 assertThat("The parsed pojo does  match the invalid one" , parsedJSON .equals(jsonToCompare) , not( true ));
			 
		} catch (FileNotFoundException e) {
		
			assertThat("Cannot find XLS File",   not( true ));
		} catch (ClassCastException e) {
		
			assertThat("Cannot process the XLS File",   not( true ));
		
		} catch (IOException e) {
		
			assertThat("Cannot access the XLS File",   not( true ));
		}
	} 
	
	
	
	@Test 
	public void TESTA_SubTestC_CheckIfWeCannotReadAWrongXLSFile() {
		
        System.out.println();
		
		try {
			
			 AllLecturesPOJO pojo = testXLSFileReader.readFile(testXLSFile);
		
			 ObjectMapper mapper = new ObjectMapper();
			 	
			 String parsedJSON = mapper.writeValueAsString(pojo);
			 
			 
			 // we read the expected correct pojo file 
			 
			 byte[] expectedParsedContent = Files.readAllBytes(pathManagerToTestFiles.getPathOfFile( "AllLecturesPOJOFromAISEXLSFileAsset.json"));
				
			 
			 String jsonToCompare  = new String(expectedParsedContent , Charset.forName("UTF-8"));
			
			 assertThat("The parsed pojo does  match the invalid one" , parsedJSON .equals(jsonToCompare) , not( true ));
			 
		} 
		
		catch (ConnectionError e) {
			
			assertThat("Cannot find XLS File is invalid",   not( true ));
		
		}
		
		catch (FileNotFoundException e) {
		
			assertThat("Cannot find XLS File",   not( true ));
		
		} catch (ClassCastException e) {
		
			assertThat("Cannot process the XLS File",   not( true ));
		
		} catch (IOException e) {
		
			assertThat("Cannot access the XLS File",   not( true ));
		}
	}
	
	 
	
}
