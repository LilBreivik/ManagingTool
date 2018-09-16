package core.controller.parameter.schedule.collisioncheck;
  
  
import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty; 
import core.controller.parameter.schedule.GenericScheduleFileParam;
import resources.error.parameter.ParameterViolationError;
import scheduling.elements.POJO.LecturePOJO;

public class CourseScheduleCollisionCheckParam
													extends GenericScheduleFileParam  
{
	
	private  List<LecturePOJO> m_lecturesList;
	private final String parameterERRORMESSAGE = "Es wurden keine Fächer übertragen.";
	
	@JsonCreator
    public CourseScheduleCollisionCheckParam(@JsonProperty("courseName") String courseName,
											       @JsonProperty("courseDegree") String courseDegree,
											       		@JsonProperty("courseTerm") String  courseTerm, 
											       			@JsonProperty("lecturesList") List<LecturePOJO> lecturesList) {
		super(courseName, courseDegree, courseTerm);
		
			p_verifierJob = () -> { 
			    	 	
				if(lecturesList.size() == 0) {
					throw new  ParameterViolationError(parameterERRORMESSAGE);
				}
		   };
	
		   this.m_lecturesList = lecturesList;
	}
   
	public List<LecturePOJO> getLecturesList(){
		
		return this.m_lecturesList;
	}
 }