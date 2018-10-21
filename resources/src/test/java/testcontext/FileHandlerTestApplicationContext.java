package testcontext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication		 
@ComponentScan(basePackages = { "resources.components", "resources.fileconnection", "resources.constraint" , "resources.utils", "resources.database"})

public class FileHandlerTestApplicationContext {

	public static void main(String[] args) {
	     
		ApplicationContext context = SpringApplication.run(FileHandlerTestApplicationContext.class, new String[]{});
    }

}
