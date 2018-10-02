package core.backend.REST.nonfileasset.collision.task;

   

import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.general.task.AbstractTaskImpl;
import core.backend.REST.nonfileasset.collision.parameter.CollisionCheckParameter;
import resources.components.elements.POJO.Scheduling.Lectures.ScheduledLecturesPOJO;
import resources.components.filehandler.filesynthesizer.ISynthesizer;
import scheduling.SchedulingCollisionManager; 

public class CollisionCheckTask 

                      extends AbstractTaskImpl<CollisionCheckParameter, ScheduledLecturesPOJO>{

	private CollisionCheckParameter m_courseCollisionCheckRequest;
	
	protected ScheduledLecturesPOJO response; 
	
	protected SchedulingCollisionManager p_collisionManager;

	protected ISynthesizer p_synthesizer;
	
	
	public CollisionCheckTask(SchedulingCollisionManager collisionManager ) {

		p_collisionManager  =  collisionManager;
	}
	
	  
	@Override
	public void workOnTask( ){
	
		// check Collisions 
		 
		p_collisionManager.checkConflicts(m_courseCollisionCheckRequest.getLecturesList());
				  
	}
	
	
	@Override
	public SuccessResponse<ScheduledLecturesPOJO> getResultsFromTask() {
		 
		return new SuccessResponse<ScheduledLecturesPOJO>(p_collisionManager.buildCollisionCheckResponse(p_collisionManager.getScheduledTimeBlock(), 
				m_courseCollisionCheckRequest.getLecturesList()));
	}


	public void setCollisionCheckRequest(CollisionCheckParameter courseCollisionCheckRequest) {
		
		m_courseCollisionCheckRequest = courseCollisionCheckRequest; 
	}
}
