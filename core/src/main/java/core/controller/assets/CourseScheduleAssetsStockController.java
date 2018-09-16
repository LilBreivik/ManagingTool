package core.controller.assets;
  
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseStatus;

import core.controller.response.result.successfully.successes.ScheduleAssetsStockResponse;
import resources.components.elements.POJO.Assets.AssetsStockPOJO;

import org.springframework.http.HttpStatus; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 

@Controller
public class CourseScheduleAssetsStockController 
													extends AssetsStockController {
	
	
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/Assets/CourseSchedule", method = RequestMethod.POST, consumes="application/json" , produces="application/json")
	public ScheduleAssetsStockResponse assetsFileRequest()  
	{
	 
		AssetsStockPOJO pojo = new AssetsStockPOJO();
		pojo.presentFlag = false; 
		
		return new ScheduleAssetsStockResponse(pojo);
	
	}
	/*@Autowired
	 private CourseScheduleXMLFileHandler m_xmlFileHandler;
	

	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/Synthesize/GeneralCourseSchedule", method = RequestMethod.GET, produces="application/json")
	public GeneralCourseScheduleSynthesizingResponse synthesizeFile()  
	{
		PersistenceCourseSchedulePOJO courseSchedulePOJO =  (PersistenceCourseSchedulePOJO) m_xmlFileHandler.readFile("CourseSchedule");
		 
		// synthesize
		return new GeneralCourseScheduleSynthesizingResponse((PersistenceCourseSchedulePOJO) courseSchedulePOJO); 	
	}*/
}
