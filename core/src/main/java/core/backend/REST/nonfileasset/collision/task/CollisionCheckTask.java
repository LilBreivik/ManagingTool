package core.backend.REST.nonfileasset.collision.task;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import core.backend.REST.general.response.result.successfully.SuccessResponse; 
import core.backend.REST.general.task.response.AbstractResponseTaskImpl;
import core.backend.REST.nonfileasset.collision.parameter.CollisionCheckParameter;  
import scheduling.SchedulingCollisionManager;
import scheduling.POJO.ScheduledLecturesPOJO; 

@Component
public class CollisionCheckTask 
 
                      extends AbstractResponseTaskImpl<  CollisionCheckParameter, ScheduledLecturesPOJO>{
	
	private CollisionCheckParameter m_courseCollisionCheckRequest;
	
	@Autowired 
	private SchedulingCollisionManager p_collisionManager;
 
	
	@Override
	public void workOnTask(CollisionCheckParameter courseCollisionCheckRequest ){
	
		// safe request , for futher processing 
		
		m_courseCollisionCheckRequest = courseCollisionCheckRequest; 
		
		// check Collisions 
		 
		p_collisionManager.checkCollisons(  m_courseCollisionCheckRequest.getLecturesList());
				  
	}
	
	 
	@Override
	public SuccessResponse<ScheduledLecturesPOJO> getResultsFromTask() {
		 
		return new SuccessResponse<ScheduledLecturesPOJO>(p_collisionManager.buildCollisionCheckResponse( 
				m_courseCollisionCheckRequest.getLecturesList()));
	}
}
