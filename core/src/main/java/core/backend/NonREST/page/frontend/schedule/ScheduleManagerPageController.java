package core.backend.NonREST.page.frontend.schedule;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import core.backend.NonREST.model.views.ScheduleManagerView;
import core.backend.NonREST.page.general.GeneralIndexPageController;
import core.backend.NonREST.page.general.IndexPageController;
 
@Controller
public class ScheduleManagerPageController 
										extends  GeneralIndexPageController<ScheduleManagerView >{
 
	public static final String  scheduleManagerPageURL =  "/ManagingTool/ScheduleManager"; 
 
	 
	@Override
	@GetMapping(scheduleManagerPageURL)
	public String serveTemplate(Model template) {
		 
		return super.serveTemplate(template);
	}
		
}