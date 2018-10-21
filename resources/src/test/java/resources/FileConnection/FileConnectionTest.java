package resources.FileConnection;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue; 

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.stream.XMLStreamException; 
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import resources.error.ConnectionError;
import resources.error.parameter.fileasset.FileAssetNotCommitedError;
import resources.fileconnection.XLSFileConnection;
import resources.fileconnection.XMLFileConnection;
import resources.utils.pathmanager.PathManager;
import testcontext.FileHandlerTestApplicationContext;




/**
 * General Class to test 
 * the needed file connection objects. 
 * 
 * Keep in mind , that we can only
 * check if the certain files are invalid, 
 * not if a valid xls/xml file is one 
 * of the expected !!!! 
 * 
 * */


@ContextConfiguration( classes={  FileHandlerTestApplicationContext.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(locations = "classpath:application.test.properties") 
@EnableAspectJAutoProxy(proxyTargetClass=true)
@RunWith(SpringJUnit4ClassRunner.class) 
public class FileConnectionTest {

	// result messages 
	
	private final static String NOT_INITIALIZED_PATHMANAGER = "The PathManager was not initialized";
	
	private final static String FILE_IS_NOT_THERE = "The demanded File does not exist";
	
	private final static String NO_CONNECTION_POSSIBLE = "Could not establish connection to a certain file";
	
	
	private static XMLFileConnection xmlFileConnection; 
	
	private static XLSFileConnection xlsFileConnection; 
	
	private static final  String XMLTestFileName = "LectureScheduleAISEBa"; 
	
	private static final  String XLSTestFileName = "AISEXLSFileAsset"; 
	
	private static final  String PDFFileName = "PDFFileAsset"; 
	
	
	@Autowired
	@Qualifier("Path to Test Resources")
	private  PathManager pathManagerForTestFiles; // pathManager to use in tests 
	
	@Test
	public void TESTA_SubTestA_CheckIfWeCanInitializePathManagerForTestFiles() {
		
		assertThat(NOT_INITIALIZED_PATHMANAGER , pathManagerForTestFiles, not( pathManagerForTestFiles == null));
	}
	
	@Test
	public void TESTB_SubTestA_CheckIfWeCanEstablishAConnectioToACertainXMLFile() {
		 
		xmlFileConnection = new XMLFileConnection( pathManagerForTestFiles.getPathToOperateOn());
		 
		File testXMLFile = new File(xmlFileConnection.getPath().toString(), XMLTestFileName);
		 
		try {
			
			xmlFileConnection.buildConnectionToAFile(testXMLFile);
			 
			assertThat( "Cannot process the XML File" ,  not(  xmlFileConnection.getM_xmlEventReader() == null));
			 
		}
		
		catch(FileAssetNotCommitedError | FileNotFoundException fileNotCommitedError) {
			
			assertTrue( FILE_IS_NOT_THERE, false); 
		}
		catch(ConnectionError noConnectionPossibleError) {
			  		
			assertTrue( NO_CONNECTION_POSSIBLE, false); 
		}
		catch (XMLStreamException e) {
			
			assertTrue( "Cannot process the XML File", false); 
		}
		 
	}
	
	@Test
	public void TESTB_SubTestB_CheckIfWeCanRecognizeAnInvalidXMLFile() {
		 
		xmlFileConnection = new XMLFileConnection( pathManagerForTestFiles.getPathToOperateOn());
		 
		File testXMLFile = new File(xmlFileConnection.getPath().toString(),  XLSTestFileName);
		 
		try {
			
			xmlFileConnection.buildConnectionToAFile(testXMLFile);
			 
			assertThat( "Could Establish Connection To A Wrong File" ,  not(  xmlFileConnection.getM_xmlEventReader() != null));
			 
		}
		
		catch(FileAssetNotCommitedError | FileNotFoundException fileNotCommitedError) {
			
			assertTrue( FILE_IS_NOT_THERE, false); 
		}
		catch(ConnectionError noConnectionPossibleError) {
			  		
			assertTrue( "Can establish connection to a wrong file", true); 
		}
		catch (XMLStreamException e) {
			
			assertTrue( "Can process an invalid File", true); 
		}
		 
	}
	
	@Test
	public void TESTB_SubTestC_CheckIfWeCanRecognizeAnInvalidXMLFile() {
		 
		xmlFileConnection = new XMLFileConnection( pathManagerForTestFiles.getPathToOperateOn());
		 
		File testXMLFile = new File(xmlFileConnection.getPath().toString(),  PDFFileName );
		 
		try {
			
			xmlFileConnection.buildConnectionToAFile(testXMLFile);
			 
			assertThat( "Could Establish Connection To A Wrong File" ,  not(  xmlFileConnection.getM_xmlEventReader() != null));
			 
		}
		
		catch(FileAssetNotCommitedError | FileNotFoundException fileNotCommitedError) {
			
			assertTrue( FILE_IS_NOT_THERE, false); 
		}
		catch(ConnectionError noConnectionPossibleError) {
			  		
			assertTrue( "Can establish connection to a wrong file", true); 
		}
		catch (XMLStreamException e) {
			
			assertTrue( "Can process an invalid File", true); 
		}
		 
	}
	

	@Test
	public void TESTC_SubTestA_CheckIfWeCanEstablishAConnectioToACertainXLSFile() {
		 
		xlsFileConnection = new XLSFileConnection( pathManagerForTestFiles.getPathToOperateOn());
		 
		File testXLSFile = new File(xlsFileConnection.getPath().toString(), XLSTestFileName);
		 
		try {
			
			xlsFileConnection.buildConnectionToAFile(testXLSFile);
			
			assertThat("Cannot process the XLS File",   not(  xlsFileConnection.getSheet() == null));
	
			assertThat("Cannot process the XLS File",   not(  xlsFileConnection.getWb() == null));
		}
		
		catch(FileAssetNotCommitedError  fileNotCommitedError) {
			
			assertTrue( FILE_IS_NOT_THERE, false); 
		}
		catch(ConnectionError noConnectionPossibleError) {
			  		
			assertTrue( NO_CONNECTION_POSSIBLE, false); 
		} 
		 
	}
	
	
	@Test
	public void TESTC_SubTestB_CheckIfWeCanRecognizeAnInvalidXLSFile() {
		 
		xlsFileConnection = new XLSFileConnection( pathManagerForTestFiles.getPathToOperateOn());
		 
		File testXLSFile = new File(xlsFileConnection.getPath().toString(), XMLTestFileName);
		 
		try {
			
			xlsFileConnection.buildConnectionToAFile(testXLSFile);
			
			assertThat("Can process the invalid XLS File",   not(  xlsFileConnection.getSheet() != null));
	
			assertThat("Can process the invalid XLS File",   not(  xlsFileConnection.getWb() != null));
		}
		
		catch(FileAssetNotCommitedError  fileNotCommitedError) {
			
			assertTrue( FILE_IS_NOT_THERE, false); 
		}
		catch(ConnectionError noConnectionPossibleError) {
			  		
			assertTrue( "Can establish connection to a wrong file", true); 
		} 
		 
	}
	
	@Test
	public void TESTC_SubTestC_CheckIfWeCanRecognizeAnInvalidXLSFile() {
		 
		xlsFileConnection = new XLSFileConnection( pathManagerForTestFiles.getPathToOperateOn());
		 
		File testXLSFile = new File(xlsFileConnection.getPath().toString(), PDFFileName);
		 
		try {
			
			xlsFileConnection.buildConnectionToAFile(testXLSFile);
			
			assertThat("Can process the invalid XLS File",   not(  xlsFileConnection.getSheet() != null));
	
			assertThat("Can process the invalid XLS File",   not(  xlsFileConnection.getWb() != null));
		}
		
		catch(FileAssetNotCommitedError  fileNotCommitedError) {
			
			assertTrue( FILE_IS_NOT_THERE, false); 
		}
		catch(ConnectionError noConnectionPossibleError) {
			  		
			assertTrue( "Can establish connection to a wrong file", true); 
		} 
		 
	}
	
}
