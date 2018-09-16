package core.controller.pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import core.controller.pages.model.ModelFactory; 
import core.controller.pages.model.views.DashboardView;
import core.controller.pages.model.views.ScheduleManagerView;

@Controller
public class ScheduleManagerPageController extends AbstractPageController{

	private ScheduleManagerView m_view; 
	
	@Autowired
	public ScheduleManagerPageController(ScheduleManagerView  view) {
		
		this.m_view = view; 
	}
	
	@GetMapping("/ManagingTool/ScheduleManager")
	@Override
	public String serveTemplate(Model template) {
		  
		template =  ModelFactory.createModel(template , this.m_view  );
		
		return "index";
	}
		
}