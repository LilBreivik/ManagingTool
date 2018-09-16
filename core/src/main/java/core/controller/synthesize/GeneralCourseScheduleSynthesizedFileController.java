package core.controller.synthesize;
  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus; 
import core.controller.response.result.successfully.successes.GeneralCourseScheduleSynthesizingResponse; 
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO; 
import resources.components.filehandler.XML.CourseScheduleXMLFileHandler; 

@Controller
public class GeneralCourseScheduleSynthesizedFileController  {
	
	@Autowired
	private CourseScheduleXMLFileHandler m_xmlFileHandler;
	

	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/Synthesize/GeneralCourseSchedule", method = RequestMethod.GET, produces="application/json")
	public GeneralCourseScheduleSynthesizingResponse synthesizeFile()  
	{
		PersistenceCourseSchedulePOJO courseSchedulePOJO =  (PersistenceCourseSchedulePOJO) m_xmlFileHandler.readFile("CourseSchedule");
		 
		// synthesize
		return new GeneralCourseScheduleSynthesizingResponse((PersistenceCourseSchedulePOJO) courseSchedulePOJO); 	
	}
}
