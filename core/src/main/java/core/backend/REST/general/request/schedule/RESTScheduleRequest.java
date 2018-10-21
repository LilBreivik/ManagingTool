package core.backend.REST.general.request.schedule;
 
import java.util.stream.Collectors;

import core.backend.REST.general.request.MasterRESTRequest;
import core.backend.utils.verifier.CourseVerifier;
import core.utils.names.FileNameResolver;
import resources.components.elements.POJO.Course.CoursePOJO;
import resources.components.filehandler.JSON.provider.nonpersistent.NonPersistentJSONFileHandlerProvider;
import resources.components.utils.ComponentsManufactory;
import resources.error.parameter.ParameterViolationError;
import resources.utils.names.INameResolver;
import scheduling.Utils.IScheduleParam;


/**
 * Class that describes 
 * a REST request that invloves a certain schedule 
 * */

public abstract class RESTScheduleRequest <CourseSchedule extends CoursePOJO>

									extends  MasterRESTRequest<CourseSchedule>

													implements IScheduleParam{
	
	private CourseVerifier p_verifier;
	
    protected INameResolver p_scheduleFileNameResolver;

    
	private FileNameResolver p_FileNameResolver;
	
	// Name of the finally processed file
	
	private String p_targetFileName; 
	
	public RESTScheduleRequest(CourseSchedule pojo)
	{
		super(pojo);
		 
		NonPersistentJSONFileHandlerProvider provider = (NonPersistentJSONFileHandlerProvider) ComponentsManufactory.createComponent("nonPersistentJSONFileHandlerProvider", NonPersistentJSONFileHandlerProvider.class);
		 
		p_verifier =  new CourseVerifier(provider.providePersistenceCourseScheduleJSONFileHandler());	
	}
	 
	
	@Override
	public String getCourseName() {
	
		return getRequest().getCourseName();
	}

	@Override
	public void setCourseName(String courseName) {
		
		getRequest().setCourseName(courseName);
	}

	@Override
	public String getCourseDegree() {

		return getRequest().getCourseDegree();
	}

	@Override
	public void setCourseDegree(String courseDegree) {
	
	    getRequest().setCourseDegree(courseDegree);	
	}

	@Override
	public String getCourseTerm() {
		
		return getRequest().getCourseTerm();
	}

	@Override
	public void setCourseTerm(String courseTerm) {
		
		getRequest().setCourseTerm(courseTerm);	
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
			    						   .filter(pojo -> (getCourseName().equals(pojo.getCourseName())
				    								 &&
				    							     getCourseTerm().equals(pojo.getCourseTerm())
				    								    && 
				    								    getCourseDegree().equals(pojo.getCourseDegree())))
			    						   .collect(Collectors.toList())
			    						   
			    						   // there can be just one Tripel of parameter values 
			    						   .size() != 1 ) {
		    	
		   
		    	throw new  ParameterViolationError(parameterErrorMessageInvalidParameter);
		    }
	}
	
	
	
    public FileNameResolver getFileNameResolver()  {
		
		return p_FileNameResolver;
	}


	public void setFileNameResolver(FileNameResolver p_FileNameResolver) {
		this.p_FileNameResolver = p_FileNameResolver;
		
	}
 
	
	public String getTargetFileName() {
		return p_targetFileName;
	}


	public void setTargetFileName(String p_targetFileName) {
		this.p_targetFileName = p_targetFileName;
	}

}
