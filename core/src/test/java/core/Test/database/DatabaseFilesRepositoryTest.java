package core.Test.database;

import static core.utils.Constants.UploadFileName.CourseSchedule;
import static core.utils.Constants.UploadFileName.LectureSchedule;
import static org.junit.Assert.assertTrue;
 
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner; 
import core.TestContext.DatabaseTestApplicationContext;
import core.controller.parameter.ScheduleParam;
import core.utils.names.UploadFileNameResolver;
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO;
import resources.components.filehandler.XML.CourseScheduleXMLFileHandler;
import resources.database.entities.File.Files;
import resources.database.repository.FilesRepository;
import resources.utils.names.INameResolver; 

@ContextConfiguration( classes={   DatabaseTestApplicationContext .class  })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource("classpath:application.properties")
@RunWith(SpringJUnit4ClassRunner.class)   
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class  DatabaseFilesRepositoryTest {
 	  
	  @Autowired 
	  private FilesRepository filesRepo;
 
	  @Autowired 
	  private CourseScheduleXMLFileHandler m_xmlFileHandler;
	  
	  @Test
	  public void TEST_A_checkIfWeCanInitializeTheRepo() {
		   
		  assertTrue(filesRepo != null);
	  }
	   
	  
	  @Test
	  public void TEST_B_checkIfWeCanInitializeTheXMLFileHandler() {
		    
		  assertTrue(m_xmlFileHandler!= null);
	  }
	  

	  @Test
	  public void TEST_C_checkIfWeCanAddNewCourseSchedulesInTheDatabase(){
		  
		  try {
			  
			  INameResolver fileNameResolver = (resolvedScheduleFileName) -> CourseSchedule.toString().concat( resolvedScheduleFileName );
				
			  PersistenceCourseSchedulePOJO courseSchedulePOJO =  (PersistenceCourseSchedulePOJO) m_xmlFileHandler.readFile("CourseSchedule");
			
			  
			  courseSchedulePOJO.getCoursesSchedulePOJO()
				 .stream().forEach( pojo ->{
					
					 ScheduleParam param = new ScheduleParam();
					 
					 param.setCourseName(pojo.getCourseName());

					 param.setCourseDegree(pojo.getCourseDegree());
					 
					 param.setCourseTerm(pojo.getCourseTerm());
					 
					 UploadFileNameResolver resolver =  new UploadFileNameResolver( param, fileNameResolver );
					 
				     Files file = new Files();
						 
				      
				     
					 file.setFileName(resolver.getResolvedUploadedFileName());
						 
				     filesRepo.create(file);
					 
				 });

			  
		  }
		  catch(Exception databaseException) {
			    
			  
			  if(databaseException instanceof DataIntegrityViolationException) {
				  
				  assertTrue(true);
			  }
			  
			  else {
				  
				  assertTrue(false);
			  }
		  }
		  
	  }
	  
	  
	  @Test
	  public void TEST_D_checkIfWeCanAddNewCourseSchedulesInTheDatabase(){
		  
		  try {
			  
			  INameResolver fileNameResolver = (resolvedScheduleFileName) -> LectureSchedule.toString().concat( resolvedScheduleFileName );
				
			  PersistenceCourseSchedulePOJO courseSchedulePOJO =  (PersistenceCourseSchedulePOJO) m_xmlFileHandler.readFile("CourseSchedule");
			
			  
			  courseSchedulePOJO.getCoursesSchedulePOJO()
				 .stream().forEach( pojo ->{
					
					 ScheduleParam param = new ScheduleParam();
					 
					 param.setCourseName(pojo.getCourseName());

					 param.setCourseDegree(pojo.getCourseDegree());
					 
					 param.setCourseTerm(pojo.getCourseTerm());
					 
					 UploadFileNameResolver resolver =  new UploadFileNameResolver( param, fileNameResolver );
					 
				     Files file = new Files();
						 
				      
				     
					 file.setFileName(resolver.getResolvedUploadedFileName());
						 
				     filesRepo.create(file);
					 
				 });

			  
		  }
		  catch(Exception databaseException) {
			   
			  if(databaseException instanceof DataIntegrityViolationException) {
				  
				  assertTrue(true);
			  }
			  
			  else {
				  
				  assertTrue(false);
			  }
		  }

	  }
}
