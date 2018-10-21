package core.configuration.applicationinformation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationInformationConfiguration {

	@Value("${application.information.version}")
	public String VERSION;
	  
	@Value("${application.information.administrator}")
    public String ADMINISTRATOR;
	  
	@Value("${application.information.administrator-email}")
	public String ADMINISTRATOR_EMAIL;
	  
	@Value("${application.information.author}")
	public String AUTHOR;

	@Value("${application.information.author-email}")
	public  String AUTHOR_EMAIL;
}
 