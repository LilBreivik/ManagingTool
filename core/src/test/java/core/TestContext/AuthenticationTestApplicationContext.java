package core.TestContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan; 

@SpringBootApplication		  
@ComponentScan(basePackages = { "core", "resources"})

public class AuthenticationTestApplicationContext {

	public static void main(String[] args) {
	     
		ApplicationContext context = SpringApplication.run( AuthenticationTestApplicationContext.class, new String[]{});
    }
}
