package core.controller.collisioncheck;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus; 
import core.controller.parameter.schedule.collisioncheck.CourseScheduleCollisionCheckParam;
import core.controller.response.result.successfully.successes.CollisionCheckResponse;
import scheduling.SchedulingCollisionManager; 
import scheduling.elements.POJO.ScheduledLecturesPOJO; 

@Controller
public class  CourseScheduleCollisionCheckController  {
	
	@Autowired
	private SchedulingCollisionManager m_collisionManager;
	 
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/Synthesize/Collision", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public CollisionCheckResponse collisionCheck(@RequestBody  CourseScheduleCollisionCheckParam courseCollisionCheckRequest )  
	{
		// verify parameter ... 
		 
		courseCollisionCheckRequest.verifyParameter();
		
		// check Collisions 
		 
		m_collisionManager.checkConflicts(courseCollisionCheckRequest.getLecturesList());
		  
			
		
		CollisionCheckResponse test = new CollisionCheckResponse(m_collisionManager.buildCollisionCheckResponse(m_collisionManager.getScheduledTimeBlock(), 
				courseCollisionCheckRequest.getLecturesList()));
		
		
		System.out.println(test);
		
		
		return new CollisionCheckResponse(m_collisionManager.buildCollisionCheckResponse(m_collisionManager.getScheduledTimeBlock(), 
				courseCollisionCheckRequest.getLecturesList()));
	}
}
