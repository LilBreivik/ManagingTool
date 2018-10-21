package resources.FileHandler.XLS;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertArrayEquals;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import resources.components.elements.POJO.Persistence.AllLecturesPOJO; 
import resources.components.elements.POJO.Persistence.LectureSchedulePOJO;
import resources.components.filehandler.JSON.general.GeneralPersistentJSONFileHandler;
import resources.components.filehandler.XLS.general.GeneralXLSFileHandler;
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
 
	@Autowired 
	@Qualifier("FileNameTranslator that uses the Database")	
	private FileNameTranslator translator;
	 
	@Autowired 
	private  LectureConstraint lectureConstraint;
	
	@Autowired
	private FilesRepository filesRepository; 
	
	
	@Autowired
	@Qualifier("Path to Test Resources")
	private PathManager pathManagerToTestResources; 
	
	@Autowired 
	@Qualifier("POJOPersistenceAdder for AllLecturesScheduleJSON")
	private  GeneralPOJOPersistenceAdder<AllLecturesPOJO> persistenceAddition;
	
	@Autowired 
	@Qualifier("POJOPersistenceSubstractor for AllLecturesScheduleJSON")
	private GeneralPOJOPersistenceSubtractor<AllLecturesPOJO > persistenceSubstraction;
		 
	// pathManager to use in tests 
	private  PathManager testPathManager;
	
	private static GeneralXLSFileHandler testGeneralXLSFileHandler; 
	 
	private static GeneralPersistentJSONFileHandler<AllLecturesPOJO >  testGeneralPersistentJSONFileHandler; 
	


	@Rule
    public TestName testName = new TestName();
	
	
	@Before 
	public void setUp() {
		
		testPathManager =  new PathManager(src, test, test);
		
		testGeneralXLSFileHandler = new GeneralXLSFileHandler(filesRepository, 
													lectureConstraint, 
												testPathManager,  
											translator);
		 
		 if(testName.getMethodName().equals("TEST_C_CheckIfWeCanAddDifferentLecutreScheduleFiles")){
	   		   
			 
			 testGeneralPersistentJSONFileHandler = new GeneralPersistentJSONFileHandler<AllLecturesPOJO > (
					 AllLecturesPOJO.class,
						 testPathManager, 
							 persistenceAddition,  
							    persistenceSubstraction);
    	 } 
	}
	
	
	@Test 
	public void TEST_A_CheckIfWeCanMoveALectureScheduleFile() {
		
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
	 
		 
		// verify, that the file was moved correctly 
		
		assertThat("Check if the file ", not(testGeneralXLSFileHandler.checkIfFileDoesExist(logicallyFileName) == false));
		
		assertThat("FileName does falely gets resolved " ,  not(  translator.translateFileName(logicallyFileName).equals(physicallyFileName)));
	}
	
	
	
	@Test 
	public void TEST_B_CheckIfWeCanReadALectureScheduleFileFromRepository() {
		
		final String logicallyFileName = "AISEXLSFileAsset.xls";
		 
		final String tempJSONFile = "allLectures.json"; 
		
		
		AllLecturesPOJO pojo = testGeneralXLSFileHandler.readXLSFile(logicallyFileName);
		
		ObjectMapper mapper = new ObjectMapper();
		
		// safe temp file 
		
		try {
		 
			mapper.writeValue(testPathManager.buildFileFromFileName(tempJSONFile), pojo);
		
		
			assertThat("FileName does falely gets resolved " ,  not(  testPathManager.doesFileExistOnPath(tempJSONFile)  == false ));
			
			
			assertArrayEquals("Binary files differ" , java.nio.file.Files.readAllBytes(testPathManager.getPathOfFile(tempJSONFile)), 
					java.nio.file.Files.readAllBytes(pathManagerToTestResources.getPathOfFile( "AllLecturesPOJOFromAISEXLSFileAsset.json"))); 
			
		} catch (IOException e) {
		
			assertTrue("Could not parse json file " , false );
		}
		
	 
		System.out.println();
		  
	} 
	
	@Test 
	public void TEST_C_CheckIfWeCanAddDifferentLecutreScheduleFiles() {
		
		final String persistentJSONFile = "allLectures.json"; 
		
		final String comparableJSONFileName = "AISEXLSFileAsset.json"; 
	
		final String logicallyFileName = "WiInfXLSFileAsset.xls";
			
		final String physicallyFileName = UUID.randomUUID().toString();
		 
		ObjectMapper mapper = new ObjectMapper();
		
		
		Files fileStateToUpdate = new Files();
			 	
		fileStateToUpdate.setFileName(logicallyFileName); // we store the information to the row with the expected name
		
		fileStateToUpdate.setFileUploaded(true);
		
		fileStateToUpdate.setFiledeleted(false);
		
		fileStateToUpdate.setFileUploadedAt(new Date(System.currentTimeMillis()));
				    
		fileStateToUpdate.setFileUploader("Rainer Winkler");  
		
		fileStateToUpdate.setFilePath( testPathManager.buildFileFromFileName(physicallyFileName).toString());
		
		filesRepository.addNewFile(fileStateToUpdate);
			 
		// move the file to the expected test directory 
		
		try {
		
			
			OrdinaryFileHandler.copyFile(testPathManager.getPathOfFile(persistentJSONFile).toFile(),
					testPathManager.buildFileFromFileName(comparableJSONFileName ).toPath());
			
			
			
			OrdinaryFileHandler.copyFile(pathManagerToTestResources.buildFileFromFileName(logicallyFileName),
					testPathManager.buildFileFromFileName(physicallyFileName ).toPath());
			 
			AllLecturesPOJO newLecturespojo = testGeneralXLSFileHandler.readXLSFile(logicallyFileName);
			
			AllLecturesPOJO persistentLecturespojo = mapper.readValue( testPathManager.buildFileFromFileName(comparableJSONFileName), AllLecturesPOJO.class);
			
			 
			assertThat("the pojos seem to be the same ", not(newLecturespojo.getAllLectures().size() != persistentLecturespojo.getAllLectures().size() ));
			
		
			testGeneralPersistentJSONFileHandler.appendToPersistence(persistentJSONFile, newLecturespojo); 
			 
			AllLecturesPOJO persistentPOJO =  mapper.readValue(testGeneralPersistentJSONFileHandler.getPathManager().getPathOfFile(persistentJSONFile).toFile(), AllLecturesPOJO.class);  
			 
			
			List<LectureSchedulePOJO> persistentLenctures  = persistentPOJO.getAllLectures();
			
			
			persistentLenctures = persistentLenctures.stream().filter(lecture -> newLecturespojo.getAllLectures().indexOf(lecture) < 0 )
															    .collect(Collectors.toList())
															    	.stream()
															    	.filter(lecture -> persistentLecturespojo.getAllLectures().indexOf(lecture) < 0 )
															    	.collect(Collectors.toList());
															
			// persistentLenctures shall now be empty !!!	
			
			
			assertThat("FileName does falely gets resolved " ,  not( persistentLenctures.size()  == 0 ));
			
			 
		 
		} catch (IOException e) {
			
		
			assertTrue("Some IO Exception happend", true);
		}
	 
		 
	}
	 
	// RemoveLecutreScheduleFiles
	@Test 
	public void  TEST_D_CheckIfWeCanRemoveLecutreScheduleFilesFromAllLecturesScheule() {
		
		final String persistentJSONFile = "allLectures.json"; 
		
		final String comparableJSONFileName = "AISEXLSFileAsset.json"; 
	
		final String logicallyFileName = "WiInfXLSFileAsset.xls";
			
		final String physicallyFileName = UUID.randomUUID().toString();
		 
		ObjectMapper mapper = new ObjectMapper();
		
		  
		try {
		
			 
			System.out.println(testPathManager.buildFileFromFileName( logicallyFileName));
			
			AllLecturesPOJO wiinfpojo = testGeneralXLSFileHandler.readXLSFile(logicallyFileName );
		
			AllLecturesPOJO aisepojo =  testGeneralXLSFileHandler.readXLSFile( "AISEXLSFileAsset.xls" );
			
			
			testGeneralPersistentJSONFileHandler.appendToPersistence(persistentJSONFile, wiinfpojo); 
			 
			testGeneralPersistentJSONFileHandler.appendToPersistence(persistentJSONFile,  aisepojo); 
			

			testGeneralPersistentJSONFileHandler.subtractFromPersistence(persistentJSONFile, wiinfpojo); 
			 
			testGeneralPersistentJSONFileHandler.subtractFromPersistence(persistentJSONFile,  aisepojo); 
			
					
			AllLecturesPOJO persistentPOJO =  mapper.readValue(testGeneralPersistentJSONFileHandler.getPathManager().getPathOfFile(persistentJSONFile).toFile(), AllLecturesPOJO.class);  
			 	
			List<LectureSchedulePOJO> persistentLenctures  = persistentPOJO.getAllLectures();
			
			assertThat("FileName does falely gets resolved " ,  not( persistentLenctures.size()  == 0 ));
			
		} catch (IOException e) {
			
		
			assertTrue("Some IO Exception happend", true);
		}
	 
		 
	}
	
	
	@Test 
	@WithMockUser(username = "Rainer Winkler", password = "root" )
	public void TEST_E_CheckIfWeCanRemoveLecutreScheduleFiles() {
		
		final String persistentJSONFile = "allLectures.json"; 
		
		final String comparableJSONFileName = "AISEXLSFileAsset.json"; 
	
		final String logicallyFileName = "WiInfXLSFileAsset.xls";
			
		final String physicallyFileName = UUID.randomUUID().toString();
		 
		ObjectMapper mapper = new ObjectMapper();
		
		  
		try {
			 
			System.out.println(testPathManager.buildFileFromFileName( logicallyFileName));
			
			AllLecturesPOJO wiinfpojo = testGeneralXLSFileHandler.readXLSFile(logicallyFileName );
		
			AllLecturesPOJO aisepojo =  testGeneralXLSFileHandler.readXLSFile( "AISEXLSFileAsset.xls" );
			
			
			testGeneralPersistentJSONFileHandler.appendToPersistence(persistentJSONFile, wiinfpojo); 
			 
			testGeneralPersistentJSONFileHandler.appendToPersistence(persistentJSONFile,  aisepojo); 
			

			
			testGeneralXLSFileHandler.deleteFile(logicallyFileName);
			
			testGeneralXLSFileHandler.readXLSFile( "AISEXLSFileAsset.xls" );
			
					
			AllLecturesPOJO persistentPOJO =  mapper.readValue(testGeneralPersistentJSONFileHandler.getPathManager().getPathOfFile(persistentJSONFile).toFile(), 
					AllLecturesPOJO.class);  
			 	
			List<LectureSchedulePOJO> persistentLenctures  = persistentPOJO.getAllLectures();
			
			assertThat("FileName does falely gets resolved " ,  not( persistentLenctures.size()  == 0 ));
			
		} catch (IOException e) {
			
		
			assertTrue("Some IO Exception happend", true);
		}
	 
		 
	}
	
	 
}
