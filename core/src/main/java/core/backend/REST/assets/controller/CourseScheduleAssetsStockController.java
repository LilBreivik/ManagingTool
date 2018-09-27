package core.backend.REST.assets.controller;
  
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseStatus;
import core.backend.REST.assets.parameter.request.CourseScheduleAssetsStockParameter;
import core.backend.REST.assets.task.AssetsStockTask; 
import core.backend.REST.general.response.result.successfully.SuccessResponse; 
import resources.components.elements.POJO.Assets.AssetsStockPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 

@Controller
public class CourseScheduleAssetsStockController 
													extends  AssetsStockController {
 	
	@Autowired
	public CourseScheduleAssetsStockController(@Qualifier("provide AssetsStockTask") AssetsStockTask  task) {
		super(task); 
	}
 
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/Assets/CourseSchedule", method = RequestMethod.POST, consumes="application/json" , produces="application/json")
	public SuccessResponse<AssetsStockPOJO> assetsFileRequest(@RequestBody  CourseScheduleAssetsStockParameter  courseScheduleAssetsStockParameter)  
	{ 
		return  super.handleRequest(  courseScheduleAssetsStockParameter);
	} 
}
