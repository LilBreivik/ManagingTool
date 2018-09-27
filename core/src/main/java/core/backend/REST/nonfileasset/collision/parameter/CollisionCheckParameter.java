package core.backend.REST.nonfileasset.collision.parameter;

import core.backend.REST.general.request.MasterRESTCustomRequest;
import resources.error.parameter.ParameterViolationError;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;  
import scheduling.elements.POJO.LecturePOJO;


public class CollisionCheckParameter 		
													extends MasterRESTCustomRequest{

	private  List<LecturePOJO> m_lecturesList;
	
	@JsonCreator
	public CollisionCheckParameter(@JsonProperty("courseName") String courseName,
											       @JsonProperty("courseDegree") String courseDegree,
										       		@JsonProperty("courseTerm") String  courseTerm, 
										       		  @JsonProperty("lecturesList") List<LecturePOJO> lecturesList) {
		super(courseName, courseDegree, courseTerm); 
		 
		   p_verifierJob = () -> { 
    	 	
				if(lecturesList.size() == 0) {
					throw new  ParameterViolationError(parameterErrorMessageInvalidParameter);
				}
		   };

	   this.m_lecturesList = lecturesList;
	}
	
	public List<LecturePOJO> getLecturesList(){
		
		return this.m_lecturesList;
	}

}
