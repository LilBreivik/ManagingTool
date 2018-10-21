package core.backend.REST.nonfileasset.collision.task;

 
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.general.task.general.GeneralAbstractTaskImpl;
import core.backend.REST.nonfileasset.collision.parameter.CollisionCheckParameter; 
import resources.components.filehandler.general.GeneralFileHandler;
import scheduling.SchedulingCollisionManager;
import scheduling.POJO.ScheduledLecturesPOJO; 

public class CollisionCheckTask 

                      extends GeneralAbstractTaskImpl<  CollisionCheckParameter, ScheduledLecturesPOJO>{

	private CollisionCheckParameter m_courseCollisionCheckRequest;
	
	protected ScheduledLecturesPOJO response; 
	
	protected SchedulingCollisionManager p_collisionManager;
 
	
	 
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
