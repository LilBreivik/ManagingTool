package core.controller.assets.schedule;
  
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseStatus;
import core.controller.assets.AssetsStockController; 
import core.controller.parameter.assets.schedule.CourseScheduleAssetsStockParam;
import core.controller.response.result.successfully.successes.ScheduleAssetsStockResponse;
import resources.components.elements.POJO.Assets.AssetsStockPOJO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 

@Controller
public class CourseScheduleAssetsStockController 
													extends AssetsStockController {
	
	
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/Assets/CourseSchedule", method = RequestMethod.POST, consumes="application/json" , produces="application/json")
	public ScheduleAssetsStockResponse assetsFileRequest(@RequestBody  CourseScheduleAssetsStockParam  courseScheduleAssetsStockParam)  
	{
		courseScheduleAssetsStockParam.verifyParameter();
		 
		AssetsStockPOJO pojo = checkAssetsInStock(courseScheduleAssetsStockParam);
		
		return new ScheduleAssetsStockResponse(pojo);
		
		 
	} 
}
