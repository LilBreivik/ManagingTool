package core.TestContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan; 

@SpringBootApplication		  
@ComponentScan(basePackages = { "resources"})

public class DatabaseTestApplicationContext {

	public static void main(String[] args) {
	     
		ApplicationContext context = SpringApplication.run( DatabaseTestApplicationContext.class, new String[]{});
    }
}
