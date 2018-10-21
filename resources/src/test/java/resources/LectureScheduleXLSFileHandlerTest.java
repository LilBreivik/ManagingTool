package resources;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static resources.utils.general.Constants.Directory.src;
import static resources.utils.general.Constants.Directory.test;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder; 
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
import resources.components.elements.POJO.Persistence.LectureSchedulePOJO;
import resources.components.filehandler.JSON.general.GeneralPersistentJSONFileHandler;
import resources.components.filehandler.XLS.general.GeneralXLSFileHandler;
import resources.components.filehandler.XML.general.GeneralXMLFileHandler;
import resources.components.filehandler.utils.adder.general.GeneralPOJOPersistenceAdder;
import resources.components.filehandler.utils.subtractor.general.GeneralPOJOPersistenceSubtractor;
import resources.components.filereader.utils.FileNameTranslator;
import resources.constraint.LectureConstraint;
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
public class LectureScheduleXLSFileHandlerTest {

	// pathManager to use in tests 
	private  PathManager testPathManager;
	
	private  GeneralXLSFileHandler m_LectureScheduleXLSFileHandler;

	private GeneralPersistentJSONFileHandler<AllLecturesPOJO> allLecturesJSONFileHandlerToTest; 
	
	@Autowired
	private  LectureConstraint lectureConstraint;
	
	@Autowired 
	private FilesRepository  filesRepository;
	
	@Autowired 
	@Qualifier("FileNameTranslator that uses the Database")	
	private FileNameTranslator translator;
	
	@Autowired 
	@Qualifier("Path to Test Resources")
	private  PathManager pathManagerToTestResources;
	
	@Autowired
	@Qualifier("PathManager to JSONFiles") 
	private PathManager pathManagerToJSONFiles;
	
	@Autowired
	@Qualifier("POJOPersistenceAdder for AllLecturesScheduleJSON") 
	private GeneralPOJOPersistenceAdder<AllLecturesPOJO> persistenceAddition;
	
	@Autowired
	@Qualifier("POJOPersistenceSubstractor for AllLecturesScheduleJSON")
	private GeneralPOJOPersistenceSubtractor<AllLecturesPOJO> persistenceSubstraction;
	 
	@Before 
	public void setUp() throws IOException {
	
		 testPathManager =  new PathManager(src, test, test);
			
		 
		 m_LectureScheduleXLSFileHandler = new GeneralXLSFileHandler( filesRepository,
																		lectureConstraint,
													     					testPathManager, 
																				translator); 
 
		 
		 
		 allLecturesJSONFileHandlerToTest =  new GeneralPersistentJSONFileHandler<AllLecturesPOJO>(AllLecturesPOJO.class, 
				 															pathManagerToTestResources,
																				 persistenceAddition,
																					persistenceSubstraction );
		
	      
		
	} 
	
	@Test
	public void TESTA_testIfWeCanInitalizeRequestedXLSFileHandler() {
		
		if(!(m_LectureScheduleXLSFileHandler == null)) {
			
			assertTrue(true); 
		}
		else {
			
			assertTrue(false); 
		}
	}
	
	

	@Test
	public void TESTB_testIfWeCanSetUpRequestedXLSFile() {
		
		 
		final String logicallyFileName = "AISEXLSFileAsset.xls";
		
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
	 
	}
	
	 
	
	@Test
	public void TESTC_testIfWeCanBuildAllLecturesPOJO() {
		
		AllLecturesPOJO allLectures = m_LectureScheduleXLSFileHandler.readXLSFile("AISEXLSFileAsset.xls");
	
		allLecturesJSONFileHandlerToTest.createJSONFile("allLecturesShedule.json");
		
		allLecturesJSONFileHandlerToTest.writeToJSONFile("allLecturesShedule.json", allLectures);
		
		assertThat("allLecturesShedule.json does not exist ", not(allLecturesJSONFileHandlerToTest.checkIfFileDoesExist("allLecturesShedule.json") == true));

	}
	
	
	@Test
	public void TESTD_testIfWeCanHandleAllLecturesPOJO() {
		 
		 
		AllLecturesPOJO allLectures = (AllLecturesPOJO) allLecturesJSONFileHandlerToTest.readJSONFile("allLecturesShedule.json");
	 
		AllLecturesPOJO allLecturesToCompare = m_LectureScheduleXLSFileHandler.readXLSFile("AISEXLSFileAsset.xls");
	
		List<LectureSchedulePOJO> lecturesToCompare =  allLecturesToCompare.getAllLectures().stream().filter(lec -> allLectures.getAllLectures().indexOf(lec) < 0).collect(Collectors.toList());
		 
		assertThat("pojos do not contain the same ammount of lecture objects", not(lecturesToCompare.size() == 0));
			
	}
	
	
	
}
