package core.backend.REST.general.request.schedule;

import java.util.stream.Collectors;
import core.backend.REST.general.request.RESTRequest;
import core.backend.utils.verifier.CourseVerifier; 
import resources.components.elements.POJO.Course.CoursePOJO;
import resources.components.filehandler.JSON.provider.nonpersistent.NonPersistentJSONFileHandlerProvider;
import resources.components.utils.ComponentsManufactory;
import resources.error.parameter.ParameterViolationError; 


/**
 * Class that describes 
 * a REST request that invloves a certain schedule , 
 * that is represented by a certain CoursePOJO 
 * 
 * */

public abstract class RESTScheduleRequest <Request extends CoursePOJO>

									extends  RESTRequest<Request> {
	
	protected  CourseVerifier p_verifier; 
 
	
	public RESTScheduleRequest(){}
	
	public RESTScheduleRequest(Request request)
	{ 
		
		super(request);
		
		NonPersistentJSONFileHandlerProvider provider = (NonPersistentJSONFileHandlerProvider) ComponentsManufactory.createComponent("nonPersistentJSONFileHandlerProvider", NonPersistentJSONFileHandlerProvider.class);
		 
		p_verifier =  new CourseVerifier(provider.providePersistenceCourseScheduleJSONFileHandler());	
		
		 
	}
	 
	
	 /**
	 * We check , if the Parameter 
	 * got expected values ... 
	 * 
	 * The non File Paramters are Degree , Term, Name, 
	 * to be short , everything that is needed to describe the 
	 * needed schedule file 
	 * 
	 * @throws  ParameterViolationError , if the validation fails
	 * */
	 
	 
	public void verifyParameter()  throws ParameterViolationError{
		  
			 
			/* We need to check 
			 * the schedule dependent information 
			 * 
			 * the if-clause must be true, 
			 * if the param, got a tripel of valid information...
			 * 
			 * */
		
	 	
		    if(p_verifier.getVerifier().getCoursesSchedulePOJO().stream()
			    						   .filter(pojo -> (getRequest().getCourseName().equals(pojo.getCourseName())
				    								 &&
				    								 getRequest().getCourseTerm().equals(pojo.getCourseTerm())
				    								    && 
				    								    getRequest().getCourseDegree().equals(pojo.getCourseDegree())))
			    						   .collect(Collectors.toList())
			    						   
			    						   // there can be just one Tripel of parameter values 
			    						   .size() != 1 ) {
		    	
		   
		    	throw new  ParameterViolationError(parameterErrorMessageInvalidParameter);
		    }
	}
	 
}
