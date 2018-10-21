package core.backend.REST.nonfileasset.synthesize.task;

  
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.general.task.general.GeneralAbstractTaskImpl;
import core.backend.REST.nonfileasset.synthesize.parameter.SynthesizedCourseScheduleFileParameter; 

public abstract class SynthesizedTask<ResponseType> 

                      extends GeneralAbstractTaskImpl<SynthesizedCourseScheduleFileParameter, ResponseType>{

	protected ResponseType response; 
	 
	@Override
    public SuccessResponse<ResponseType> getResultsFromTask() {
    
    	return  new SuccessResponse<ResponseType>(response) ;
    	 
    } 
	 
}
