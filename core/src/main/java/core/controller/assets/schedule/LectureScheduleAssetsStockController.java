package core.controller.assets.schedule;
  
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseStatus;
import core.controller.assets.AssetsStockController;  
import core.controller.parameter.assets.schedule.LectureScheduleAssetsStockParam;
import core.controller.response.result.successfully.successes.ScheduleAssetsStockResponse;
import resources.components.elements.POJO.Assets.AssetsStockPOJO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 

@Controller
public class LectureScheduleAssetsStockController 
													extends AssetsStockController {
	
	
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/Assets/LectureSchedule", method = RequestMethod.POST, consumes="application/json" , produces="application/json")
	public ScheduleAssetsStockResponse assetsFileRequest(@RequestBody  LectureScheduleAssetsStockParam  lectrueScheduleAssetsStockParam)  
	{
		lectrueScheduleAssetsStockParam.verifyParameter();
		
		AssetsStockPOJO pojo = checkAssetsInStock(lectrueScheduleAssetsStockParam);
		
		return new ScheduleAssetsStockResponse(pojo);
		
		 
	} 
}
