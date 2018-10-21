package resources.FileHandler.XML;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static resources.utils.general.Constants.Directory.src;
import static resources.utils.general.Constants.Directory.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;
import java.util.UUID;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import resources.components.elements.POJO.Persistence.AllLecturesPOJO;
import resources.components.elements.POJO.Persistence.LectureScheduleOfCoursePOJO;
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO; 
import resources.components.filehandler.XML.general.GeneralXMLFileHandler;
import resources.components.filereader.XML.XMLFileReaderManager;
import resources.components.filereader.utils.FileNameTranslator;
import resources.database.entities.File.Files;
import resources.database.repository.FilesRepository;
import resources.utils.files.OrdinaryFileHandler;
import resources.utils.pathmanager.PathManager;
import testcontext.FileHandlerTestApplicationContext;

@ContextConfiguration( classes={  FileHandlerTestApplicationContext.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(locations = "classpath:application.test.properties") 
@EnableAspectJAutoProxy(proxyTargetClass=true)
@RunWith(SpringJUnit4ClassRunner.class) 
public class CourseScheduleXLSFileHandlerTest {

	// pathManager to use in tests 
	private  PathManager testPathManager;
		
	private static GeneralXMLFileHandler<LectureScheduleOfCoursePOJO> testGeneralXMLFileHandler; 
	 
	@Autowired
	private FilesRepository filesRepository; 
	
	@Autowired
	@Qualifier("Path to Test Resources")
	private PathManager pathManagerToTestResources; 
	
	@Autowired 
	@Qualifier("FileNameTranslator that uses the Database")	
	private FileNameTranslator translator;
	
	@Autowired 
	@Qualifier("XMLFileReaderManager for LectureScheduleXMLFile") 
	private XMLFileReaderManager<LectureScheduleOfCoursePOJO> xmlFileReaderManager;
	
//	private static GeneralPersistentJSONFileHandler<AllLecturesPOJO >  testGeneralPersistentJSONFileHandler; 
	

	

	@Before 
	public void setUp() {
		
		testPathManager =  new PathManager(src, test, test);
		
		testGeneralXMLFileHandler = new GeneralXMLFileHandler<LectureScheduleOfCoursePOJO>(filesRepository, 
												 testPathManager,  
											translator, 
									xmlFileReaderManager);
		   
	}
	
	@Test 
	public void TEST_A_CheckIfWeCanMoveAScheduleFile() {
		
		final String logicallyFileName = "LectureScheduleAISEBa.xml";
		
		final String physicallyFileName = UUID.randomUUID().toString();
		 
		File physicallyStoredXLSFile = new File(testPathManager.getPathToOperateOn().toString(), 
																		physicallyFileName);
		 
		File logicallyStoredXLSFile = new File(pathManagerToTestResources.getPathToOperateOn().toString(), 
																		logicallyFileName);
		
		 
		// move the file to the expected test directory 
				
		try {
		
			OrdinaryFileHandler.copyFile(logicallyStoredXLSFile, physicallyStoredXLSFile.toPath());
		  
		} catch (IOException e) {
			
		
			assertTrue("Some IO Exception happend", true);
		}
		  
		Files fileStateToUpdate = new Files();
	 	
		fileStateToUpdate.setFileName(logicallyFileName); // we store the information to the row with the expected name
		fileStateToUpdate.setFileUploaded(true);
		fileStateToUpdate.setFiledeleted(false);
		fileStateToUpdate.setFileUploadedAt(new Date(System.currentTimeMillis()));
		    
		fileStateToUpdate.setFileUploader("Rainer Winkler");  
		fileStateToUpdate.setFilePath( testPathManager.getPathOfFile(physicallyFileName).toString());
		
		filesRepository.addNewFile(fileStateToUpdate);
	 
		 
		// verify, that the file was moved correctly 
		
		assertThat("Check if the file ", not(testGeneralXMLFileHandler.checkIfFileDoesExist(logicallyFileName) == false));
		
		assertThat("FileName does falely gets resolved " ,  not(  translator.translateFileName(logicallyFileName).equals(physicallyFileName)));
	}
	
	@Test 
	public void TEST_B_CheckIfWeCanReadALectureScheduleFileFromRepository() {
		
		final String logicallyFileName = "LectureScheduleAISEBa.xml";
		  
		final String tempJSONFile = "LectureScheduleAISEBa.json"; 
		
		LectureScheduleOfCoursePOJO pojo = testGeneralXMLFileHandler.readXMLFile(logicallyFileName);
		
		ObjectMapper mapper = new ObjectMapper();
		
		// safe temp file 
		
		try {
		 
			mapper.writeValue(testPathManager.buildFileFromFileName(tempJSONFile), pojo);
		
		
			assertThat("FileName does falely gets resolved " ,  not(  testPathManager.doesFileExistOnPath(tempJSONFile)  == false ));
			
			
			assertArrayEquals("Binary files differ" , java.nio.file.Files.readAllBytes(testPathManager.getPathOfFile(tempJSONFile)), 
					java.nio.file.Files.readAllBytes(pathManagerToTestResources.getPathOfFile( "aisebaLectureSchedule.json"))); 
			
		} catch (IOException e) {
		
			assertTrue("Could not parse json file " , false );
		}
		
	 
		System.out.println();
		  
	} 
	
	
	@Test 
	@WithMockUser(username = "Rainer Winkler", password = "root" )
	public void TEST_C_CheckIfWeCanRemoveALectureScheduleFileFromRepository() {
		
		final String logicallyFileName = "LectureScheduleAISEBa.xml";
		 
		
	    testGeneralXMLFileHandler.deleteFile(logicallyFileName);
				
		assertThat("File does still exist " ,  not(  testPathManager.doesFileExistOnPath(logicallyFileName)  ==  false));
		
		assertThat("File does still exist in repository " ,  not( filesRepository.read(logicallyFileName)  ==   null));
		 
	} 
	
}
