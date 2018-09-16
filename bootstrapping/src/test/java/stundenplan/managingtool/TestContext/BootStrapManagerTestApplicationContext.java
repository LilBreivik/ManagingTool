package stundenplan.managingtool.TestContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan; 

@SpringBootApplication		  
@ComponentScan(basePackages = { "bootstrap", "properties", "constraint","configuration", "components"})

public class BootStrapManagerTestApplicationContext {

	public static void main(String[] args) {
	     
		ApplicationContext context = SpringApplication.run( BootStrapManagerTestApplicationContext.class, new String[]{});
    }
}
