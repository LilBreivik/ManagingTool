package core.Test.database.TestContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration; 

@SpringBootApplication		 	  
@ComponentScan(basePackages = { "database",   "resources"} )
 
public class  DatabaseTestApplicationContext {

	public static void main(String[] args) {
	  
		ApplicationContext context = SpringApplication.run( DatabaseTestApplicationContext .class, new String[]{});
    }
}
