package core.backend.REST.nonfileasset.collision.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import core.backend.REST.general.controller.MasterRESTController;
import core.backend.REST.general.response.result.successfully.SuccessResponse; 
import core.backend.REST.nonfileasset.collision.parameter.CollisionCheckParameter;
import core.backend.REST.nonfileasset.collision.task.CollisionCheckTask;
import resources.components.elements.POJO.Scheduling.Lectures.ScheduledLecturesPOJO;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus; 
 

@Controller
public class CollisionCheckController < Request extends CollisionCheckParameter, 
														ResponseType extends ScheduledLecturesPOJO>
															extends MasterRESTController< Request,   ResponseType> {
   
	@Autowired
	@Qualifier("provide CollisionCheckTask")
	protected CollisionCheckTask collisionTask;
	 
	@Override
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/Collision/Check", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	protected SuccessResponse<ResponseType> handleRequest(@RequestBody  Request courseCollisionCheckRequest )  
	{
		collisionTask.setCollisionCheckRequest(courseCollisionCheckRequest );
		 
		p_task = collisionTask; 
		 
		return super.handleRequest(); 
	}
	 
}
