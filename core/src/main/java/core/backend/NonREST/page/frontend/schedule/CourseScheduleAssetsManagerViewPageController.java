package core.backend.NonREST.page.frontend.schedule;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping; 
import core.backend.NonREST.model.views.CourseScheduleAssetsManagerView;
import core.backend.NonREST.page.general.GeneralIndexPageController;
 

@Controller
public class CourseScheduleAssetsManagerViewPageController 

										extends GeneralIndexPageController<CourseScheduleAssetsManagerView>{
	
	public static final String  courseScheduleAssetsManagerViewPageURL = "/ManagingTool/CourseScheduleManager"; 
	   
	@Override
	@GetMapping(courseScheduleAssetsManagerViewPageURL)
	public String serveTemplate(Model template) {
		
		return super.serveTemplate(template);
	}
		
} 