package core.backend.REST.nonfileasset.collision.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller; 
import core.backend.REST.general.controller.REST.ResponseController;
import core.backend.REST.general.response.result.successfully.SuccessResponse; 
import core.backend.REST.nonfileasset.collision.parameter.CollisionCheckParameter;
import core.backend.REST.nonfileasset.collision.task.CollisionCheckTask;
import scheduling.POJO.ScheduledLecturesPOJO;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus; 
 

@Controller 
public class CollisionCheckController 
						extends  ResponseController<CollisionCheckParameter, ScheduledLecturesPOJO  , CollisionCheckTask>{



	public static final String CollisionCheckURL = "/Collision/Check";

	@Override
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = CollisionCheckURL, method = RequestMethod.POST, consumes="application/json")
	public SuccessResponse<ScheduledLecturesPOJO> handleRequest( @RequestBody CollisionCheckParameter  collisionCheckParameter ) {
 
		return super.handleRequest( collisionCheckParameter );
	}
	
	
	/**
	 * Configuration 
	 * Area 
	 * */
	

	@Override 
	@Autowired
	public void setTask( CollisionCheckTask task) {
		
		p_task = task;
	}
	
}
