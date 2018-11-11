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
							"classpath:application.release.properties"})
@EnableAutoConfiguration
public class ManagingToolReleaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagingToolReleaseApplication.class, args);
    }
}
  