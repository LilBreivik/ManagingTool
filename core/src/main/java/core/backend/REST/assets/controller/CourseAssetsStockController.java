package core.backend.REST.assets.controller;
  
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseStatus; 
import core.backend.REST.assets.parameter.AssetsStockCourseScheduleParameter; 
import core.backend.REST.assets.task.AssetsStockTask; 
import core.backend.REST.general.controller.REST.ResponseController; 
import core.backend.REST.general.response.result.successfully.SuccessResponse; 
import resources.components.elements.POJO.Assets.AssetsStockPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 

/**
 * Controller that is need to 
 * describe the amount of Course Asset Data 
 * 
 * */


@Controller
public class CourseAssetsStockController 
		extends ResponseController<AssetsStockCourseScheduleParameter, 	
										AssetsStockPOJO,
											AssetsStockTask<AssetsStockCourseScheduleParameter> >{

	/**
	 * Configuration 
	 * Area 
	 * */ 
	
	protected final String CourseAssetsStockURL =  "/Assets/CourseSchedule";

	@Override
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = CourseAssetsStockURL, method = RequestMethod.POST, consumes="application/json" , produces="application/json")
	public SuccessResponse<AssetsStockPOJO> handleRequest(@RequestBody AssetsStockCourseScheduleParameter restRequest) {
	 
		return super.handleRequest(restRequest);
	}
	
	/**
	 * Configuration 
	 * Area 
	 * */
	
	
	@Override
	@Autowired
	public void setTask(@Qualifier("provide AssetsStockTask")  AssetsStockTask  task) {
	 
		p_task = task;
	} 
}
