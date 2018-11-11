package core.TestContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter; 


@SpringBootApplication	  
@ComponentScan(basePackages = {"resources", "core"})

public class ControllerTestApplicationContext {

	public static void main(String[] args) {
	     
		ApplicationContext context = SpringApplication.run( ControllerTestApplicationContext.class, new String[]{});
    }
}
