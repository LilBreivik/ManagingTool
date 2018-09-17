package core.controller.parameter;
  
import java.util.stream.Collectors;

import core.controller.parameter.upload.ParamVerifierJob;
import core.controller.verifier.CourseVerifier;
import core.controller.verifier.Verifier; 
import core.utils.names.UploadFileNameResolver;
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO;
import resources.error.parameter.ParameterViolationError;
import resources.utils.names.INameResolver;


/**
 * This class defines the common general Schedule
 * File dependent Parameter. 
 * 
 * It is a Wrapper that needs a
 * PersistenceCourseSchedulePOJO inherited Object, 
 * that is needed to verify the request parameter 
 * */


public abstract class GeneralScheduleFileParam<T extends PersistenceCourseSchedulePOJO> 
																		extends ScheduleParam{

	 
	protected INameResolver p_scheduleFileNameResolver;
	
	protected CourseVerifier p_verifier;
	
	private UploadFileNameResolver p_uploadFileNameResolver;
	    
	/**
	 * Verifying Job, needs to be defined 
	 * to check other params than the course dependent 
	 * 
	 * */
	
	protected ParamVerifierJob p_verifierJob;
	
	// Name of the finally processed file
	
	private String p_targetFileName; 
	
	
	private final String parameterERRORMESSAGE = "Die Request-Parameter sind falsch.";
	
	public GeneralScheduleFileParam(Verifier<T> verifier ) {
		
		p_verifier = (CourseVerifier) verifier;  
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
		
		try {
			
			/* At first we need to check 
			 * the schedule dependent information 
			 * 
			 * the if-clause must be true, 
			 * if the param, got a tripel of valid information...
			 * 
			 * */
			
		    if(p_verifier.getVerifier().getCoursesSchedulePOJO().stream()
			    						   .filter(pojo -> (courseName.equals(pojo.getCourseName())
				    								 &&
				    							     courseTerm.equals(pojo.getCourseTerm())
				    								    && 
				    								    courseDegree.equals(pojo.getCourseDegree())))
			    						   .collect(Collectors.toList())
			    						   
			    						   // there can be just one Tripel of parameter values 
			    						   .size() != 1 ) {
		    	
		    	throw new  ParameterViolationError(parameterERRORMESSAGE);
		    }
		    else {
		    
		    	
		    	/* here we check 
		    	 * the additonal verifying parameters
		    	 *
		    	 *
		    	 * the p_verifierJob needs to be defined, 
		    	 * if custom parameters shall be checked 
		    	 */
		    	if(p_verifierJob != null) {
		    		
		    		p_verifierJob.verifyCustomParameter();
		    	}
		    }
		} 
		catch(NullPointerException notEveryParameterGotMatched) {
			
			throw new  ParameterViolationError(parameterERRORMESSAGE);
		}
	}


	public UploadFileNameResolver getUploadedFileName()  {
		return p_uploadFileNameResolver;
	}


	public void setUploadFileNameResolver(UploadFileNameResolver p_uploadFileNameResolver) {
		this.p_uploadFileNameResolver = p_uploadFileNameResolver;
	}
 
	
	public String getTargetFileName() {
		return p_targetFileName;
	}


	public void setTargetFileName(String p_targetFileName) {
		this.p_targetFileName = p_targetFileName;
	}

}
