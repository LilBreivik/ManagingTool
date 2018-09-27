package core.backend.REST.nonfileasset.synthesize.task;

  
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.general.task.AbstractTaskImpl;
import core.backend.REST.nonfileasset.synthesize.parameter.SynthesizedCourseScheduleFileParameter; 
import resources.components.filehandler.FileHandler;
import resources.components.filehandler.filesynthesizer.ISynthesizer; 

public abstract class SynthesizedTask<ResponseType> 

                      extends AbstractTaskImpl<SynthesizedCourseScheduleFileParameter, ResponseType>{

	protected ResponseType response; 
	
	protected FileHandler p_fileHandler;

	protected ISynthesizer p_synthesizer;
	
	
	public SynthesizedTask(FileHandler synthesizer ) {

		 p_fileHandler = synthesizer;
	}
	
	public SynthesizedTask(ISynthesizer synthesizer ) {

		p_synthesizer = synthesizer;
	}
	
	 
	@Override
    public SuccessResponse<ResponseType> getResultsFromTask() {
    
    	return  new SuccessResponse<ResponseType>(response) ;
    	 
    } 
	 
}
