package resources.FileNameTranslator;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
 
import java.nio.file.Paths;
import java.util.Date; 
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

import resources.components.filereader.utils.FileNameTranslator;
import resources.database.entities.File.Files;
import resources.database.repository.FilesRepository; 
import testcontext.FileHandlerTestApplicationContext;

@ContextConfiguration( classes={  FileHandlerTestApplicationContext.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(locations = "classpath:application.test.properties") 
@EnableAspectJAutoProxy(proxyTargetClass=true)
@RunWith(SpringJUnit4ClassRunner.class) 
public class FileNameTranslatorTest {

	@Autowired
	@Qualifier("FileNameTranslator that uses Raw Files")
	private  FileNameTranslator rawFileNameTranslator;
	
	
	@Autowired
	@Qualifier("FileNameTranslator that uses the Database")
	private  FileNameTranslator databaseFileNameTranslator;
	
	@Autowired 
	private   FilesRepository  m_FileRepository;
	
	private static final String testFileName = "die Antwort auf die Träume";
	 
	private static final String testUserName = "Rainer Winkler";
	
	private static final String testFilePath = "/Test/FileName";
	
	
	@Test
	public void TESTA_checkIfWeCanUseDatabaseFileNameTranslator() {
		
		// to keep the convinience with the other fileHandler , we need to 
		// ensure, the "RawFileHandler" get a FileNameTranslator too, that 
		// just return an empty string 
		 
	
		Files fileStateToUpdate = new Files();
	 	
		fileStateToUpdate.setFileName(testFileName); // we store the information to the row with the expected name
		fileStateToUpdate.setFileUploaded(true);
		fileStateToUpdate.setFiledeleted(false);
		fileStateToUpdate.setFileUploadedAt(new Date(System.currentTimeMillis()));
		   
        
		fileStateToUpdate.setFileUploader(testUserName);  
		fileStateToUpdate.setFilePath( testFilePath);
		
 		m_FileRepository.addNewFile(fileStateToUpdate);
	 
	    System.out.println(databaseFileNameTranslator.translateFileName(testFileName));

		assertThat("FileName does falely gets resolved " ,  not( databaseFileNameTranslator.translateFileName(testFileName).equals(Paths.get(testFilePath).getFileName().toString())));
	 
	}
	
	
	 
	
}
