package core.controller.response.result.successfully.successes; 
import core.controller.response.result.successfully.SuccessResponse;  
import scheduling.elements.POJO.ScheduledLecturePOJO;
import scheduling.elements.POJO.ScheduledLecturesPOJO;

public class  CollisionCheckResponse
									   extends SuccessResponse<ScheduledLecturesPOJO > {
 
	public CollisionCheckResponse(ScheduledLecturesPOJO body) {
		super(body); 
	}
 
}
