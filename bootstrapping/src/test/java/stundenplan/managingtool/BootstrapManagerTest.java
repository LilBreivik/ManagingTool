package stundenplan.managingtool;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bootstrapping.bootstrap.BootstrapManager;
import stundenplan.managingtool.TestContext.BootStrapManagerTestApplicationContext;  
@ContextConfiguration( classes={ BootStrapManagerTestApplicationContext.class })

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class) 
public class BootstrapManagerTest {

	@Autowired
    private BootstrapManager manager; 
	
	 
	//stundenplan.managingtool.bootstrap
	@Test
	public void TESTA_testIfWeCanInitializeManager() {
	 
    	
		if(!(manager.equals(null))) {
			
			assertTrue(true); 
		}
		else {
			
			assertTrue(false); 
		}
	}
	
	
	@Test
	public void TESTB_runTestProcederes() {
	 
    	try {
			manager.runBootStrapJobs();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
	 
}
