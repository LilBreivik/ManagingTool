package resources.Repositories;
 

import java.util.Date;
import java.util.List;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue; 
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import resources.database.entities.File.Files;
import resources.database.repository.FilesRepository;
import testcontext.FileHandlerTestApplicationContext;


/**
 * This class is used
 * 
 * to test the functionality of the filesRepository 
 * class 
 * 
 * KEEP IN MIND, that this class requires the 
 * TEST-Database.sql , to work correctly... 
 * 
 * */

@ContextConfiguration( classes={  FileHandlerTestApplicationContext.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(locations = "classpath:application.test.properties") 
@EnableAspectJAutoProxy(proxyTargetClass=true)
@RunWith(SpringJUnit4ClassRunner.class) 
public class FileRepositoryTest {

	private static Files testFiles; 
	
	@Autowired 
	private FilesRepository filesRepo;
	
	
	@BeforeClass
	public static void setUp() {
		
		testFiles = new Files();
		
	} 
	
	@Test
	public void TEST_A_Subtest_A_checkIfWeCanCreateLectureScheduleFileToTheRepository(){
			  
		final String expectedFileNameToCreateFreshlyToTheRepository = "LectureScheduleAngewandteinformatikBachelorofscienceSommersemester"; 
	
		Files fileStateToCreate = new Files();
			 	
		fileStateToCreate.setFileName(expectedFileNameToCreateFreshlyToTheRepository); // we store the information to the row with the expected name
		fileStateToCreate.setFileUploaded(true);
		fileStateToCreate.setFiledeleted(false);
		fileStateToCreate.setFileUploadedAt(new Date(System.currentTimeMillis()));
				  
		filesRepo.create(fileStateToCreate);
		
		Files expectedCourseScheduleFile = filesRepo.read( expectedFileNameToCreateFreshlyToTheRepository);
		 
		assertThat("The expected File does not exist ", not(expectedCourseScheduleFile != null));
		
	}
	

	@Test
	public void TEST_A_Subtest_B_checkIfWeCanUpdateLectureScheduleFileToTheRepository(){
			  
		final String expectedFileNameToCreateFreshlyToTheRepository = "LectureScheduleAngewandteinformatikBachelorofscienceSommersemester"; 
	
		
		
		// first we need to access the expected file 
		
		Files readExpectedCourseScheduleFile = filesRepo.read( expectedFileNameToCreateFreshlyToTheRepository);
		
		
		final String expectedUploadedFilePath = "/Test/LectureScheduleAngewandteinformatikBachelorofscienceSommersemester"; 
		
		Files fileStateToUpdate = new Files();
			 	
		fileStateToUpdate.setFileName(expectedFileNameToCreateFreshlyToTheRepository); // we store the information to the row with the expected name
		fileStateToUpdate.setFileUploaded(false); // different upload state 
		fileStateToUpdate.setFiledeleted(true); // different deleted state 
		fileStateToUpdate.setFileUploadedAt(new Date(System.currentTimeMillis())); // different upload date 
				  
		fileStateToUpdate.setFilePath(expectedUploadedFilePath);
		
		filesRepo.update(fileStateToUpdate);
		
		Files readUpdatedCourseScheduleFile = filesRepo.read( expectedFileNameToCreateFreshlyToTheRepository);
		 
		assertThat("The Uploaded State does match after Update ", not(readUpdatedCourseScheduleFile.isFileUploaded() == readExpectedCourseScheduleFile.isFileUploaded()));
		
		assertThat("The Deleted State does match after Update ", not(readUpdatedCourseScheduleFile.isFiledeleted() == readExpectedCourseScheduleFile.isFiledeleted() ));
		
		assertThat("The Upload Date does match after Update ", not(readUpdatedCourseScheduleFile.getFileUploadedAt() == readExpectedCourseScheduleFile.getFileUploadedAt() ));
		
		assertThat("The Upload Date does match after Update ", not(readUpdatedCourseScheduleFile.getFilePath() == null ));
		
	} 

	@Test
	public void TEST_A_Subtest_C_checkIfWeCanDeleteLectureScheduleFileViaAMatchingObjectToTheRepository(){
			  
		final String expectedFileNameToCreateFreshlyToTheRepository = "LectureScheduleAngewandteinformatikBachelorofscienceSommersemester"; 
	  
		final String expectedUploadedFilePath = "/Test/LectureScheduleAngewandteinformatikBachelorofscienceSommersemester"; 
		
		Files fileStateToUpdate = new Files();
			 	
		fileStateToUpdate.setFileName(expectedFileNameToCreateFreshlyToTheRepository); // we store the information to the row with the expected name
		fileStateToUpdate.setFileUploaded(false); // different upload state 
		fileStateToUpdate.setFiledeleted(true); // different deleted state 
		fileStateToUpdate.setFileUploadedAt(new Date(System.currentTimeMillis())); // different upload date 
				  
		fileStateToUpdate.setFilePath(expectedUploadedFilePath);
		
		
		
		filesRepo.delete(fileStateToUpdate);
		
		Files readUpdatedCourseScheduleFile = filesRepo.read( expectedFileNameToCreateFreshlyToTheRepository);
		
		assertThat("The expected File does exist ", not(readUpdatedCourseScheduleFile == null));
	}
	

	@Test
	public void TEST_A_Subtest_D_checkIfWeCanDeleteLectureScheduleFileViaAMatchingObjectToTheRepository(){
			  
		final String expectedFileNameToCreateFreshlyToTheRepository = "LectureScheduleAngewandteinformatikBachelorofscienceSommersemester"; 
		
		Files fileStateToCreate = new Files();
			 	
		fileStateToCreate.setFileName(expectedFileNameToCreateFreshlyToTheRepository); // we store the information to the row with the expected name
		fileStateToCreate.setFileUploaded(true);
		fileStateToCreate.setFiledeleted(false);
		fileStateToCreate.setFileUploadedAt(new Date(System.currentTimeMillis()));
				  
		filesRepo.create(fileStateToCreate);
		
		Files expectedCourseScheduleFile = filesRepo.read( expectedFileNameToCreateFreshlyToTheRepository);
		 
		assertThat("The expected File does not exist ", not(expectedCourseScheduleFile != null));
		
		
		filesRepo.delete(expectedFileNameToCreateFreshlyToTheRepository);
		
		Files readUpdatedCourseScheduleFile = filesRepo.read( expectedFileNameToCreateFreshlyToTheRepository);
		
		assertThat("The expected File does exist ", not(readUpdatedCourseScheduleFile == null));
	}
	
	
	
	
	@Test
	public void TEST_B_Subtest_A_checkIfWeCanCreateCourseScheduleFileToTheRepository(){
			  
		final String expectedFileNameToCreateFreshlyToTheRepository = "CourseScheduleAngewandteinformatikBachelorofscienceSommersemester"; 
	
		Files fileStateToCreate = new Files();
			 	
		fileStateToCreate.setFileName(expectedFileNameToCreateFreshlyToTheRepository); // we store the information to the row with the expected name
		fileStateToCreate.setFileUploaded(true);
		fileStateToCreate.setFiledeleted(false);
		fileStateToCreate.setFileUploadedAt(new Date(System.currentTimeMillis()));
				  
		filesRepo.create(fileStateToCreate);
		
		Files expectedCourseScheduleFile = filesRepo.read( expectedFileNameToCreateFreshlyToTheRepository);
		 
		assertThat("The expected File does not exist ", not(expectedCourseScheduleFile != null));
		
	}
	

	@Test
	public void TEST_B_Subtest_B_checkIfWeCanUpdateCourseScheduleFileToTheRepository(){
			  
		final String expectedFileNameToCreateFreshlyToTheRepository = "CourseScheduleAngewandteinformatikBachelorofscienceSommersemester"; 
	
		
		
		// first we need to access the expected file 
		
		Files readExpectedCourseScheduleFile = filesRepo.read( expectedFileNameToCreateFreshlyToTheRepository);
		
		
		final String expectedUploadedFilePath = "/Test/CourseScheduleAngewandteinformatikBachelorofscienceSommersemester"; 
		
		Files fileStateToUpdate = new Files();
			 	
		fileStateToUpdate.setFileName(expectedFileNameToCreateFreshlyToTheRepository); // we store the information to the row with the expected name
		fileStateToUpdate.setFileUploaded(false); // different upload state 
		fileStateToUpdate.setFiledeleted(true); // different deleted state 
		fileStateToUpdate.setFileUploadedAt(new Date(System.currentTimeMillis())); // different upload date 
				  
		fileStateToUpdate.setFilePath(expectedUploadedFilePath);
		
		filesRepo.update(fileStateToUpdate);
		
		Files readUpdatedCourseScheduleFile = filesRepo.read( expectedFileNameToCreateFreshlyToTheRepository);
		 
		assertThat("The Uploaded State does match after Update ", not(readUpdatedCourseScheduleFile.isFileUploaded() == readExpectedCourseScheduleFile.isFileUploaded()));
		
		assertThat("The Deleted State does match after Update ", not(readUpdatedCourseScheduleFile.isFiledeleted() == readExpectedCourseScheduleFile.isFiledeleted() ));
		
		assertThat("The Upload Date does match after Update ", not(readUpdatedCourseScheduleFile.getFileUploadedAt() == readExpectedCourseScheduleFile.getFileUploadedAt() ));
		
		assertThat("The Upload Date does match after Update ", not(readUpdatedCourseScheduleFile.getFilePath() == null ));
		
	} 
	
	@Test
	public void TEST_B_Subtest_C_checkIfWeCanDeleteCourseScheduleFileViaAMatchingObjectToTheRepository(){
			  
		final String expectedFileNameToCreateFreshlyToTheRepository = "CourseScheduleAngewandteinformatikBachelorofscienceSommersemester"; 
	  
		final String expectedUploadedFilePath = "/Test/CourseScheduleAngewandteinformatikBachelorofscienceSommersemester"; 
		
		Files fileStateToUpdate = new Files();
			 	
		fileStateToUpdate.setFileName(expectedFileNameToCreateFreshlyToTheRepository); // we store the information to the row with the expected name
		fileStateToUpdate.setFileUploaded(false); // different upload state 
		fileStateToUpdate.setFiledeleted(true); // different deleted state 
	//	fileStateToUpdate.setFileUploadedAt(new Date(System.currentTimeMillis())); // different upload date 
				  
		fileStateToUpdate.setFilePath(expectedUploadedFilePath);
		
		filesRepo.delete(fileStateToUpdate);
		
		Files readUpdatedCourseScheduleFile = filesRepo.read( expectedFileNameToCreateFreshlyToTheRepository);
		
		assertThat("The expected File does exist ", not(readUpdatedCourseScheduleFile == null));
	}
	
	 
	@Test
	public void TEST_B_Subtest_D_checkIfWeCanCreateCourseScheduleFileToTheRepository(){
			  
		final String expectedFileNameToCreateFreshlyToTheRepository = "CourseScheduleAngewandteinformatikBachelorofscienceSommersemester"; 
	
		Files fileStateToCreate = new Files();
			 	
		fileStateToCreate.setFileName(expectedFileNameToCreateFreshlyToTheRepository); // we store the information to the row with the expected name
		fileStateToCreate.setFileUploaded(true);
		fileStateToCreate.setFiledeleted(false);
		fileStateToCreate.setFileUploadedAt(new Date(System.currentTimeMillis()));
				  
		filesRepo.create(fileStateToCreate);
		
		Files expectedCourseScheduleFile = filesRepo.read( expectedFileNameToCreateFreshlyToTheRepository);
		 
		assertThat("The expected File does not exist ", not(expectedCourseScheduleFile != null));
		
		
		filesRepo.delete(expectedFileNameToCreateFreshlyToTheRepository);
		
		Files readUpdatedCourseScheduleFile = filesRepo.read( expectedFileNameToCreateFreshlyToTheRepository);
		
		assertThat("The expected File does exist ", not(readUpdatedCourseScheduleFile == null));
		
	}
		
}
