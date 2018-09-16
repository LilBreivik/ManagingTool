package core.controller.pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import core.controller.pages.model.ModelFactory;
import core.controller.pages.model.views.CourseScheduleAssetsManagerView; 

@Controller
public class CourseScheduleAssetsManagerViewPageController extends AbstractPageController{

	private CourseScheduleAssetsManagerView m_view; 
	
	@Autowired
	public CourseScheduleAssetsManagerViewPageController(CourseScheduleAssetsManagerView  view) {
		
		this.m_view = view; 
	}
	
	@GetMapping("/ManagingTool/CourseScheduleManager")
	@Override
	public String serveTemplate(Model template) {
		  
		template =  ModelFactory.createModel(template , this.m_view  );
		
		return "index";
	}
		
}