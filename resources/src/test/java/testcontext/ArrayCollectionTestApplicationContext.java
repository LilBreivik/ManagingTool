package testcontext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.ApplicationContext; 

@SpringBootApplication		
@ComponentScan(basePackages = {"utils"})
  
public class ArrayCollectionTestApplicationContext {

	public static void main(String[] args) {
     
		ApplicationContext context = SpringApplication.run(ArrayCollectionTestApplicationContext.class, new String[]{});
    }
}