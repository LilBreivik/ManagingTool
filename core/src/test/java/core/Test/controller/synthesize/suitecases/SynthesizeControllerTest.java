package core.Test.controller.synthesize.suitecases;
 
     
import org.junit.Before; 
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.TestContext.ControllerTestApplicationContext;
import core.TestContext.utils.cases.FileAssetTestCase; 
import core.TestContext.utils.parameter.schedule.ScheduleParamWithoutFileAsset;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.context.annotation.EnableAspectJAutoProxy;

import core.backend.REST.general.controller.REST.ResponseController;
import core.backend.REST.nonfileasset.synthesize.controller.SpecificSynthesizedCourseScheduleController; 
import resources.utils.pathmanager.PathManager; 

 
@ContextConfiguration( classes={ ControllerTestApplicationContext.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)   
@WebMvcTest( ResponseController .class)
@TestPropertySource({"classpath:Properties/application.persistent.files.development.properties", 
"classpath:Properties/test.user.properties"})
public class  SynthesizeControllerTest 
														extends FileAssetTestCase<ScheduleParamWithoutFileAsset>{
      
	@Autowired
	private MockMvc mockMvc;
 	  
	 
	@Autowired
	@Qualifier("Path to Test Resources")
	private PathManager pathManagerToLecutreAssets; 
 
 
    @Rule
    public TestName testName = new TestName();
  

    @Override 
    @Before 
    public void setUpParameter() throws IOException {
   
		 testRequestParameter = new  ScheduleParamWithoutFileAsset ();	  	
    }
    
    
    @Test
    @WithMockUser(username = "RUDI", password = "root" )
    public void INITIALIZATION_TEST() {
   	 
   	 super.INITIALIZE_TEST_PARAMETER();
    }
  	  
    @Override 
   // @Test
    @WithMockUser(username = "RUDI", password = "root" )
 	public void TESTA_checkInCorrectRequests() throws Exception {
 	       
        super.TESTA_handleInCorrectRequestWithWrongCourseName();
        super.TESTB_handleInCorrectRequestWithWrongCourseDegree();
      	super.TESTC_handleInCorrectRequestWithWrongCourseTerm();
    	 
 	}
    
    
    @Test
    @WithMockUser(username = "RUDI", password = "root" )
	public void TESTB_checkIfWeCanReceiveSynthesizedSchedles() throws Exception {
	
   	 
		 ObjectMapper mapper = new ObjectMapper();
	 	 
		 for(String name : p_courseNamesAsList) {
				
			 for(String degree  : p_courseDegreesAsList) {
			
				 for(String term : p_courseTermsAsList) {
					 
					 testRequestParameter.setCourseName( name );
			    	 testRequestParameter.setCourseDegree( degree );
			    	 testRequestParameter.setCourseTerm( term );
					    
			  		 try {
			  			
			  			 String jsonInString = mapper.writeValueAsString(testRequestParameter.createScheduleParam() );
			  		 
			  			  
						 System.out.println(jsonInString);
						 
						 ResultMatcher ok = MockMvcResultMatchers.status()
				                   .isOk();

						 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(p_controllerURL)
				                           .contentType(MediaType.APPLICATION_JSON_VALUE)
				                           .content(jsonInString);
				                           
						 
						 
						 
						 
						 
						 
						 String  expectedSynthesizedContent = new String(Files.readAllBytes(pathManagerToLecutreAssets.getPathOfFile( "AISEBaSynthesized")), "UTF-8");
						 
						  mockMvc.perform(builder)
					      .andExpect(ok) 
					    //  .andExpect(content().json(expectedSynthesizedContent))
					      .andDo(MockMvcResultHandlers.print());
						 
						  
			  		 } catch (JsonProcessingException e) {
			  			// TODO Auto-generated catch block
			  			e.printStackTrace();
			  		 }
			    	  
			    	  
			  		 System.exit(0);
				 }
			 }
		 }
		   
	 }
         
}
