package core.TestContext.utils.cases;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.TestContext.utils.parameter.schedule.ScheduleParam;
import core.backend.REST.nonfileasset.synthesize.task.GeneralSynthesizedCourseScheduleTask;
import core.backend.REST.nonfileasset.synthesize.task.SynthesizedTask;
import resources.components.elements.POJO.Course.CourseSchedulePOJO;
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO;
import resources.utils.general.GeneralPurpose;


 
public abstract class FileAssetTestCase<TestParameter extends  ScheduleParam> 
														extends AbstractTestCase{

	 
	protected String p_fileAssetName; 
	
	protected static  List<String> p_courseNamesAsList; 
	
	protected static  List<String> p_courseDegreesAsList; 
	
	protected static  List<String> p_courseTermsAsList; 
	
	protected  TestParameter testRequestParameter; 
	
	@Autowired  
	private GeneralSynthesizedCourseScheduleTask courseScheduleTask;
	 
	
	protected  void TESTA_checkInCorrectRequests() throws Exception {
		throw new UnsupportedOperationException("Shall be implemented in the inherited Classes");

	}
	
	
	@Override
	protected void setUpParameter() throws IOException {
		
		throw new UnsupportedOperationException("shall be implemented in the inherited classes");
		
	}
	
	 
	public void INITIALIZE_TEST_PARAMETER() {

		 assertThat("Could not initialize the GeneralSynthesizedCourseScheduleTask", not(courseScheduleTask == null) );
		 
		 courseScheduleTask.workOnTask();
		 
		 PersistenceCourseSchedulePOJO test = courseScheduleTask.getResultsFromTask().getBody(); 
		 
		 System.out.println(test);
		 
	 Collection<CourseSchedulePOJO> schedulePOJOs = courseScheduleTask.getResultsFromTask().getBody().getCoursesSchedulePOJO();
	 
	 List<CourseSchedulePOJO> schedulePOJOsAsList =  GeneralPurpose.CollectionToList(schedulePOJOs);
	 
	 p_courseNamesAsList =  schedulePOJOsAsList.stream().map(pojo -> pojo.getCourseName()).distinct().collect(Collectors.toList());
	 
	 p_courseDegreesAsList =  schedulePOJOsAsList.stream().map(pojo -> pojo.getCourseDegree()).distinct().collect(Collectors.toList());
	 
	 p_courseTermsAsList =  schedulePOJOsAsList.stream().map(pojo -> pojo.getCourseTerm()).distinct().collect(Collectors.toList());
	  
	 }
	
	 
 public void TESTA_handleInCorrectRequestWithWrongCourseName() throws Exception {
      
	 ObjectMapper mapper = new ObjectMapper();
	  
	 Random rand = new Random();
	   
	 final String inCorrectCourseName = p_courseNamesAsList.get(rand.nextInt(p_courseNamesAsList.size() - 1))
																.chars()
																.mapToObj(i -> (char) i)
																.collect(Collectors.toList()).stream().map( t -> {
																 
																 if(rand.nextInt(2) == 1) {
																	 
																	 return t.toString().toUpperCase(); 
																 }
																 else {
																	 
																	 return t.toString().toLowerCase();
																 }
																 
															 } ).collect(Collectors.joining(""));
							 
	 testRequestParameter.setCourseName( inCorrectCourseName);
	 
	 for(String degree : p_courseDegreesAsList) {
	
		 for(String term  : p_courseTermsAsList) {
			 
			 testRequestParameter.setCourseDegree(  degree );
	    	 testRequestParameter.setCourseTerm( term );
			 
	    	 try {
	 			
	    		 final int ammountOfFilesBeforeUpload = pathManagerToWorkingDirectory.getAllFilesOnPath().size();
	    		 
				 String jsonInString = mapper.writeValueAsString(testRequestParameter.createScheduleParam());
			 
				 System.out.println(jsonInString);
				 
				 ResultMatcher badRequest = MockMvcResultMatchers.status()
		                   .isBadRequest();

				 System.out.println(p_controllerURL);
				 System.out.println();
				 
				 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post( p_controllerURL)
		                           .contentType(MediaType.APPLICATION_JSON_VALUE)
		                           .content(jsonInString);
		                           
				 
				 
				  mockMvc.perform(builder)
		         .andExpect(badRequest)
		         .andDo(MockMvcResultHandlers.print());
				  
				 final int ammountOfFilesAfterUpload = pathManagerToWorkingDirectory.getAllFilesOnPath().size();
				 	 
				 assertThat("The Wrong File was not deleted ", not(ammountOfFilesBeforeUpload == ammountOfFilesAfterUpload));
				 
				 
			 } catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
		 }
		 
	 }
	 
 }
   
 public void TESTB_handleInCorrectRequestWithWrongCourseDegree() throws Exception {
      
	 ObjectMapper mapper = new ObjectMapper();
	  
	 Random rand = new Random();
	 
	 System.out.println(p_courseNamesAsList);
	 
	 final String inCorrectCourseDegree = p_courseDegreesAsList.get(rand.nextInt(p_courseDegreesAsList .size() - 1))
																.chars()
																.mapToObj(i -> (char) i)
																.collect(Collectors.toList()).stream().map( t -> {
																 
																 if(rand.nextInt(2) == 1) {
																	 
																	 return t.toString().toUpperCase(); 
																 }
																 else {
																	 
																	 return t.toString().toLowerCase();
																 }
																 
															 } ).collect(Collectors.joining(""));
								 
	  
	 
	 testRequestParameter.setCourseDegree(inCorrectCourseDegree); 
	 
	 for(String name : p_courseNamesAsList) {
	
		 for(String term  : p_courseTermsAsList) {
			 
			 testRequestParameter.setCourseName( name );
	    	 testRequestParameter.setCourseTerm( term );
			 
	    	 try {
	 			
	    		 final int ammountOfFilesBeforeUpload = pathManagerToWorkingDirectory.getAllFilesOnPath().size();
		    		
				 String jsonInString = mapper.writeValueAsString(testRequestParameter.createScheduleParam());
			 
				 System.out.println(jsonInString);
				 
				 ResultMatcher badRequest = MockMvcResultMatchers.status()
		                   .isBadRequest();

				 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post( p_controllerURL)
		                           .contentType(MediaType.APPLICATION_JSON_VALUE)
		                           .content(jsonInString);
		                           
				 
				 
				  mockMvc.perform(builder)
		         .andExpect(badRequest)
		         .andDo(MockMvcResultHandlers.print());
				 
				  final int ammountOfFilesAfterUpload = pathManagerToWorkingDirectory.getAllFilesOnPath().size();
				 	 
				  assertThat("The Wrong File was not deleted ", not(ammountOfFilesBeforeUpload == ammountOfFilesAfterUpload));
					 
				  
			 } catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
		 }
		 
	 }
	 
 }
 
   
 public void TESTC_handleInCorrectRequestWithWrongCourseTerm() throws Exception {
      
	 ObjectMapper mapper = new ObjectMapper();
	  
	 Random rand = new Random();
	 
	 System.out.println(p_courseNamesAsList);
	 
	 final String inCorrectCourseTerm = p_courseTermsAsList.get(rand.nextInt(p_courseTermsAsList .size() - 1))
																.chars()
																.mapToObj(i -> (char) i)
																.collect(Collectors.toList()).stream().map( t -> {
																 
																 if(rand.nextInt(2) == 1) {
																	 
																	 return t.toString().toUpperCase(); 
																 }
																 else {
																	 
																	 return t.toString().toLowerCase();
																 }
																 
															 } ).collect(Collectors.joining(""));
								 
	  
	 
	 testRequestParameter.setCourseTerm(inCorrectCourseTerm); 
	 
	 for(String name : p_courseNamesAsList) {
	
		 for(String degree  : p_courseDegreesAsList) {
			 
			 testRequestParameter.setCourseName( name );
	    	 testRequestParameter.setCourseDegree( degree );
			 
	    	 try {
	 			
	    		 final int ammountOfFilesBeforeUpload = pathManagerToWorkingDirectory.getAllFilesOnPath().size();
		    		
				 String jsonInString = mapper.writeValueAsString(testRequestParameter.createScheduleParam());
			 
				 System.out.println(jsonInString);
				 
				 ResultMatcher badRequest = MockMvcResultMatchers.status()
		                   .isBadRequest();

				 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post( p_controllerURL)
		                           .contentType(MediaType.APPLICATION_JSON_VALUE)
		                           .content(jsonInString);
		                           
				 
				 
				  mockMvc.perform(builder)
		         .andExpect(badRequest)
		         .andDo(MockMvcResultHandlers.print());
				  
				  final int ammountOfFilesAfterUpload = pathManagerToWorkingDirectory.getAllFilesOnPath().size();
				 	 
				  assertThat("The Wrong File was not deleted ", not(ammountOfFilesBeforeUpload == ammountOfFilesAfterUpload));
					 
				 
			 } catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
		 }
		 
	 }
	 
 }

}
