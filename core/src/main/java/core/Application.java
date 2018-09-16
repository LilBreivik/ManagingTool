package core;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import core.configuration.authentication.AuthorizationConfiguration; 

 
@SpringBootApplication		  
@ComponentScan(basePackages = { "core", "resources", "scheduling"})
/*@ComponentScan(basePackages = { "core", "resources", "scheduling"}, 
								excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, 
											classes =  AuthorizationConfiguration.class))*/
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
