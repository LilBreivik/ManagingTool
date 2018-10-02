package core.backend.REST.nonfileasset.collision.parameter;
  
import resources.components.elements.POJO.Schedule.CollisionCheckPOJO; 
import resources.components.elements.POJO.Scheduling.Lectures.LecturePOJO;
import resources.error.parameter.ParameterViolationError;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;  
import core.backend.REST.general.request.custom.schedule.RESTCustomScheduleRequest;


public class CollisionCheckParameter 		
										extends RESTCustomScheduleRequest<CollisionCheckPOJO> {
 
	@JsonCreator
	public CollisionCheckParameter(@JsonProperty("collisionCheck") CollisionCheckPOJO request) {
		super(request); 
		 
		 
		 p_verifierJob = () -> { 
	    	 	
				if(getRequest().getLecturesList().size() == 0) {
					throw new  ParameterViolationError(parameterErrorMessageInvalidParameter);
				}
		   };
	}

	 
    public List<LecturePOJO> getLecturesList(){
		
		return getRequest().getLecturesList();
	}
	 
}
