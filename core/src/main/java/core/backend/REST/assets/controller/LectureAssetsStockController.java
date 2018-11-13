package core.backend.REST.assets.controller;
  
 
import org.springframework.stereotype.Controller; 
import core.backend.REST.assets.parameter.AssetsStockLectureScheduleParameter; 
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
import org.springframework.web.bind.annotation.ResponseStatus; 


/**
 * Controller that is need to 
 * describe the amount of Lecture Asset Data 
 * 
 * */

@Controller
public class LectureAssetsStockController 
		extends ResponseController<	AssetsStockLectureScheduleParameter, 	
										AssetsStockPOJO,
										AssetsStockTask<AssetsStockLectureScheduleParameter> >{

	/**
	 * Configuration 
	 * Area 
	 * */ 
	
	protected final String LectureAssetsStockURL =  "/Assets/LectureSchedule";

	@Override
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value =  LectureAssetsStockURL , method = RequestMethod.POST, consumes="application/json" , produces="application/json")
	public SuccessResponse<AssetsStockPOJO> handleRequest(@RequestBody AssetsStockLectureScheduleParameter restRequest) {
	 
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
