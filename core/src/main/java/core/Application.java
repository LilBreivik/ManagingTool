package core;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource; 

 
@SpringBootApplication		  
@ComponentScan(basePackages = { "core",  "resources", "scheduling"}) 
@PropertySource({"classpath:application.properties",
						"classpath:email.properties", 
							"classpath:applicationinformation.properties"})
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
