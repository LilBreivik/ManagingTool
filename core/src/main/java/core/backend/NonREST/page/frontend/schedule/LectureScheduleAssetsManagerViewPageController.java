package core.backend.NonREST.page.frontend.schedule;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping; 
import core.backend.NonREST.model.views.LectureScheduleAssetsManagerView;
import core.backend.NonREST.page.general.GeneralIndexPageController;
 
@Controller
public class LectureScheduleAssetsManagerViewPageController 
					 										extends GeneralIndexPageController<LectureScheduleAssetsManagerView>{
	
	public static final String  lectureScheduleAssetsManagerViewPagePageURL = "/ManagingTool/LectureScheduleManager"; 
	  
	 
	@Override
	@GetMapping(lectureScheduleAssetsManagerViewPagePageURL)
	public String serveTemplate(Model template) {
		  
		return super.serveTemplate(template);
	}
} 