package resources;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue; 
import static resources.utils.general.Constants.Directory.src;
import static resources.utils.general.Constants.Directory.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Before; 
import org.junit.FixMethodOrder; 
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import resources.components.elements.POJO.Lecture.LectureInformationPOJO;
import resources.components.elements.POJO.Persistence.LectureScheduleOfCoursePOJO; 
import resources.components.elements.POJO.Persistence.Lectures.PersistenceLectureSemesterSchedulePOJO; 
import resources.components.filehandler.JSON.general.GeneralPersistentJSONFileHandler;
import resources.components.filehandler.XML.general.GeneralXMLFileHandler; 
import resources.components.filehandler.utils.adder.general.GeneralPOJOPersistenceAdder;
import resources.components.filehandler.utils.subtractor.general.GeneralPOJOPersistenceSubtractor;
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
public class LectureScheduleXMLFileHandlerTest {

	
   
	private  LectureScheduleOfCoursePOJO   m_pojo; 
	 
	@Autowired 
	private FilesRepository  filesRepository;
	 
	@Autowired 
	@Qualifier("Path to Test Resources")
	private  PathManager pathManagerToTestResources;
	
	// pathManager to use in tests 
	private  PathManager testPathManager;
	
	@Autowired 
	@Qualifier("POJOPersistenceAdder for LectureScheduleOfCoursePOJO")
	private GeneralPOJOPersistenceAdder<LectureScheduleOfCoursePOJO> persistenceAddition;
	
	@Autowired 
	@Qualifier("POJOPersistenceSubstractor for LectureScheduleOfCoursePOJO") 
	private GeneralPOJOPersistenceSubtractor<LectureScheduleOfCoursePOJO> persistenceSubstraction;
	
	@Autowired 
	@Qualifier("XMLFileReaderManager for LectureScheduleXMLFile")
	private XMLFileReaderManager<LectureScheduleOfCoursePOJO> xmlFileReaderManager;
	
	@Autowired 
	@Qualifier("FileNameTranslator that uses the Database")	
	private FileNameTranslator translator;
	
	
	private  GeneralXMLFileHandler<LectureScheduleOfCoursePOJO> lectureScheduleXMLFileHandler;
	
	private GeneralPersistentJSONFileHandler<LectureScheduleOfCoursePOJO> lectureScheduleJSONFileHandlerToTest;
	
	 
	@Before
	public  void setUp() throws IOException {
	 
		 testPathManager =  new PathManager(src, test, test);
		
	     lectureScheduleJSONFileHandlerToTest =  new GeneralPersistentJSONFileHandler< LectureScheduleOfCoursePOJO>(
																					LectureScheduleOfCoursePOJO.class, 
																						pathManagerToTestResources,
																									persistenceAddition,
																										persistenceSubstraction	);
		
	     lectureScheduleXMLFileHandler = new  GeneralXMLFileHandler<LectureScheduleOfCoursePOJO>(
	    		 									filesRepository,
	    		 									testPathManager,
															translator,
																xmlFileReaderManager);
	} 
	
	@Test
	public void TESTA_testIfWeCanInitalizeRequestedXMLFileHandler() {
		
		if(!(  lectureScheduleXMLFileHandler == null)) {
			
			assertTrue(true); 
		}
		else {
			
			assertTrue(false); 
		}
	}
	
	
	
	@Test
	public void TESTB_testIfWeCanSetUpRequestedXMLFile() {
		
		 
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
	 
	}
	
	 
	@Test
	public void TESTC_testIfWeReadTheLecturesFileAndStoreIt() {
	
		try {
	 	
			m_pojo = ( LectureScheduleOfCoursePOJO ) lectureScheduleXMLFileHandler.readXMLFile("LectureScheduleAISEBa.xml");
		
			lectureScheduleJSONFileHandlerToTest.writeToJSONFile("LectureScheduleAISEBa.json", m_pojo);
			 
			assertThat("The file does not exist ", not(  lectureScheduleJSONFileHandlerToTest.getPathManager().doesFileExistOnPath("LectureScheduleAISEBa.json") == false ));
		 
			
			LectureScheduleOfCoursePOJO pojoToCompare = lectureScheduleJSONFileHandlerToTest.readJSONFile("LectureScheduleAISEBa.json");
			
			 

			assertThat("Degree is not equal ",  pojoToCompare.getCourseDegree(), is(m_pojo.getCourseDegree()) );
			assertThat("Term is not equal ",  pojoToCompare.getCourseTerm(), is(m_pojo.getCourseTerm()) );
			assertThat("Name is not equal ",  pojoToCompare.getCourseName(), is(m_pojo.getCourseName()) );
			

			List <PersistenceLectureSemesterSchedulePOJO> lecturesemesterSchedule   =  new ArrayList(pojoToCompare.getLecturesinAllSemesters());

			List <PersistenceLectureSemesterSchedulePOJO> m_lecturesemesterSchedule   =  new ArrayList(pojoToCompare.getLecturesinAllSemesters()); 
			
			
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
		catch(ClassCastException  castEx) {
			
			assertTrue(false);
		}
	
	
		assertTrue(true);
	}
	
   
}
