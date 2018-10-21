package resources.PathManager;
 
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat; 
import static resources.utils.general.Constants.Directory.src;
import static resources.utils.general.Constants.Directory.test;

import java.io.File;
import java.io.IOException;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters; 
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import resources.utils.pathmanager.PathManager;
import testcontext.FileHandlerTestApplicationContext;

@ContextConfiguration( classes={  FileHandlerTestApplicationContext.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(locations = "classpath:application.test.properties") 
@EnableAspectJAutoProxy(proxyTargetClass=true)
@RunWith(SpringJUnit4ClassRunner.class) 
public class PathManagerTest {

	
	// result messages 
	
	private final static String NOT_INITIALIZED_PATHMANAGER = "The PathManager was not initialized";
	
	private final static String PATH_DOES_NOT_EXIST = "The demanded path does not initialized";
	
	private final static String FILE_DOES_NOT_EXIST = "The demanded file does not exist";

	private final static String FILE_DOES_EXIST = "The demanded file does still exist";
	
	private final static String PATH_DOES_EXIST = "The demanded path does still exist";
	
	
	// pathManager to use in tests 
	private static PathManager testPathManager;
	
	
	 
	@BeforeClass
	public static void setUpTestPath() {
		
		testPathManager =  new PathManager(src, test, test);
	}
	
	
	
	@Test
	public void TESTA_SubTestA_CheckIfWeCanInitializePathManagerToWorkingDirectory() {
		
		assertThat(NOT_INITIALIZED_PATHMANAGER , testPathManager, not( testPathManager == null));
	}
	
  
	
	@Test
	public void TESTA_SubTestB_CheckIfWeCanCheckIfThePathToLectureAssets() {
		
	    assertThat(PATH_DOES_NOT_EXIST , testPathManager.doesPathExist()  , not( false ));
	}
	
	@Test
	public void TESTA_SubTestC_CheckIfWeCanIdentifyCertainFilesOnThePath()  {
		
		try {
			
			final String testFileName = "file.test";
			
			File testFile = new File(testPathManager.getPathToOperateOn().toString(), testFileName);
			
			testFile.createNewFile();
		
			assertThat(FILE_DOES_NOT_EXIST , testPathManager.doesFileExistOnPath(testFileName)  , not( false ));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void TESTA_SubTestD_CheckIfWeCanVerifyThePathOfACertainFile()  {
		
		final String testFileName = "file.test";
			
		File testFile = new File(testPathManager.getPathToOperateOn().toString(), testFileName);
			
		assertThat(FILE_DOES_EXIST , testFile.getAbsolutePath().toString().equals(testPathManager.buildFileFromFileName(testFileName).toString())
					 , not( false ));
	}
	
	 
	@Test
	public void TESTA_SubTestE_CheckIfWeCanDeleteACertainPath()  {
		
		testPathManager.deletePath();
		
		assertThat(PATH_DOES_EXIST , testPathManager.doesPathExist() , not( true ));
	}
	
}
