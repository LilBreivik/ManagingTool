package core.backend.REST.general.request;
 
import java.util.stream.Collectors;

import core.backend.utils.verifier.CourseVerifier;
import core.utils.names.FileNameResolver; 
import resources.components.filehandler.ComponentsManufactory;
import resources.components.filehandler.JSON.PersistenceCourseScheduleJSONFileHandler;
import resources.error.parameter.ParameterViolationError;
import resources.utils.names.INameResolver;
import scheduling.utils.IScheduleParam;

public abstract class MasterRESTRequest implements IScheduleParam{

	// requested CourseName 
	protected String courseName; 
	
	// requested CourseDegree 
	protected String courseDegree; 
	
	// requested CourseTerm 
	protected String courseTerm; 
	 
	protected final String parameterErrorMessageInvalidParameter = "Die Request-Parameter sind falsch.";
	
	private CourseVerifier p_verifier;
	
    protected INameResolver p_scheduleFileNameResolver;
	 
	
	private FileNameResolver p_FileNameResolver;
	
	// Name of the finally processed file
	
	private String p_targetFileName; 
	
	public  MasterRESTRequest() {}
	
	public  MasterRESTRequest(String courseName, String courseDegree, String courseTerm ) {
		
		setCourseDegree(courseDegree);
		setCourseName(courseName);
		setCourseTerm(courseTerm);
		
		p_verifier =  new CourseVerifier(ComponentsManufactory.createComponent("persistenceCourseScheduleJSONFileHandler", PersistenceCourseScheduleJSONFileHandler.class));
	}
	
	
	
	@Override
	public String getCourseName() {
	
		return courseName;
	}

	@Override
	public void setCourseName(String courseName) {
		
		this.courseName = courseName; 
	}

	@Override
	public String getCourseDegree() {

		return courseDegree; 
	}

	@Override
	public void setCourseDegree(String courseDegree) {
	
		this.courseDegree = courseDegree; 
	}

	@Override
	public String getCourseTerm() {
		// TODO Auto-generated method stub
		return courseTerm; 
	}

	@Override
	public void setCourseTerm(String courseTerm) {
		
		this.courseTerm = courseTerm; 
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
		 
		System.out.println();
			 
			/* We need to check 
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
