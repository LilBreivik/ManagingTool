package core.Test.controller.delete.suitecases;
 
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat; 

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
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

import core.Test.controller.delete.utils.FileNameResolverProvider; 
import core.TestContext.ControllerTestApplicationContext;
import core.TestContext.utils.cases.FileAssetTestCase;
import core.TestContext.utils.parameter.schedule.ScheduleParamWithoutFileAsset;
import core.backend.REST.general.controller.REST.NonResponseController;
import core.provider.FileNameProvider;
import core.utils.names.FileNameResolver;
import resources.database.repository.FilesRepository;
import resources.utils.pathmanager.PathManager;
 

 
@ContextConfiguration( classes={ ControllerTestApplicationContext.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)  
@WebMvcTest(NonResponseController.class)
@EnableAspectJAutoProxy(proxyTargetClass=true)
@TestPropertySource({"classpath:Properties/application.default.development.properties"})
public class DeleteFileControllerTest 
											extends FileAssetTestCase<ScheduleParamWithoutFileAsset>{
 
	
	protected FileNameResolverProvider p_FileNameResolverProvider; 
	
	@Autowired
	private MockMvc mockMvc;
 

	@Autowired 
	private FilesRepository filesRepo; 

	@Autowired
	@Qualifier("Path to Test Resources")
	private PathManager pathManagerToLecutreAssets; 
	
	
	@Override 
    @Before 
	public void setUpParameter() throws IOException {
		
		 testRequestParameter = new ScheduleParamWithoutFileAsset();	  
	}

	 
    @Test
    @WithMockUser(username = "RUDI", password = "root" )
    public void INITIALIZATION_TEST() {
   	 
   	 super.INITIALIZE_TEST_PARAMETER(); 
    }
    
    
    
    @Override 
    @Test
    @WithMockUser(username = "RUDI", password = "root" )
	public void TESTA_checkInCorrectRequests() throws Exception {
	       
   	    super.TESTA_handleInCorrectRequestWithWrongCourseName();
   	    super.TESTB_handleInCorrectRequestWithWrongCourseDegree();
     	super.TESTC_handleInCorrectRequestWithWrongCourseTerm();
   	 
	}
    
    @Test
    @WithMockUser(username = "RUDI", password = "root" )
	public void TESTB_checkIfWeCanDeleteWithCorrectRequestParameter() throws Exception {
	
   	 
		 ObjectMapper mapper = new ObjectMapper();
	 	 
		 for(String name : p_courseNamesAsList) {
				
			 for(String degree  : p_courseDegreesAsList) {
			
				 for(String term : p_courseTermsAsList) {
					 
					 testRequestParameter.setCourseName( name );
			    	 testRequestParameter.setCourseDegree( degree );
			    	 testRequestParameter.setCourseTerm( term );
					   
			         FileNameResolver resolvedFileName = p_FileNameResolverProvider.provideFileNameResolver(testRequestParameter.createScheduleParam().getCourse());
		 	           
			   	     final String deletedFileNameToCheck = resolvedFileName.getResolvedFileName().toString();

	  			     resources.database.entities.File.Files deletedFile = filesRepo.read(deletedFileNameToCheck);
	  		 
	  			     final String pathToPhyscallyStoredFile = deletedFile.getFilePath();
			         
			  		 try {
			  			
			  			 String jsonInString = mapper.writeValueAsString(testRequestParameter.createScheduleParam() );
			  		 
			  			 System.out.println(jsonInString);
			  			 
			  			 ResultMatcher ok  = MockMvcResultMatchers.status()
			  	                   .isOk();

			  			 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(p_controllerURL)
			  	                           .contentType(MediaType.APPLICATION_JSON_VALUE)
			  	                           .content(jsonInString);
			  	                           
			  		  
			  			 
			  			  mockMvc.perform(builder)
			  		      .andExpect( ok) 
			  		      .andDo(MockMvcResultHandlers.print());
			  			  
			  			  deletedFile = filesRepo.read(deletedFileNameToCheck);
			  		    
			  		      assertThat("The file was not recognized as deleted", 	not(deletedFile.isFiledeleted() == true) );
			  	 
			  		      assertThat("The physically stored file does still exist ", not(Paths.get(pathToPhyscallyStoredFile ).toFile().exists() == false));
			  		      
			  		   
			  		 } catch (JsonProcessingException e) {
			  			// TODO Auto-generated catch block
			  			e.printStackTrace();
			  		 }
			    	  
			    	  
				 }
			 }
		 }
		   
	 }
     
}
 