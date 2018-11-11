package core;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan; 
import org.springframework.context.annotation.PropertySource; 

   
@SpringBootApplication		  
@ComponentScan(basePackages = { "core",  "resources", "scheduling"}) 
@PropertySource({"classpath:email.properties", 
	"classpath:applicationinformation.properties",
		"classpath:application.development.properties"})
@EnableAutoConfiguration
public class ManagingToolDevelopmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagingToolDevelopmentApplication.class, args);
    }
}
 