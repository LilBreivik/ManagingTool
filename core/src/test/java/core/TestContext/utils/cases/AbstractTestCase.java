package core.TestContext.utils.cases;

import java.io.IOException;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import resources.utils.pathmanager.PathManager;
 
@EnableAspectJAutoProxy(proxyTargetClass=true)   
@RunWith(SpringJUnit4ClassRunner.class)  
public abstract class AbstractTestCase {

	protected String p_controllerURL; 
		 
	protected String p_SuiteCaseDescription; 
	
	private final String p_descriptionMessageTemplate = "TEST CASE : "; 
	
	protected static  String p_specificDescriptionMessage; 
	
	protected String getDescriptionMessage() {
 		
 		return p_descriptionMessageTemplate.concat(p_specificDescriptionMessage);
 	}
 	
	
	@Autowired
	protected MockMvc mockMvc;
	
	@Autowired  
	@Qualifier("PathManager to Working Directory")
	protected  PathManager pathManagerToWorkingDirectory; 
	 
	protected abstract void setUpParameter() throws IOException;
	 
	
}
