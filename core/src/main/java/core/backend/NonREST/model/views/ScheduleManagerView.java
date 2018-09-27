package core.backend.NonREST.model.views;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import core.backend.NonREST.model.configuration.IPageView; 

@Component
public class ScheduleManagerView  
						extends ContentView
									implements IPageView{

	
	public ScheduleManagerView() {
		
		HEADLINE_DISPLAY = true; 
		HEADLINE = "Stundenplan";
		
		CONTENT_DISPLAY = true; 
		TEMPLATE_REPLACEMENT_EXPRESSION ="fragments/schedulemanager"; 
		
		FOOTER_DISPLAY = false;
	}
	
	
	
	@Override
	public Model buildRequiredView(Model model) {
		
		model.addAttribute(HEADLINE_DISPLAY_ATTRIBUTE, HEADLINE_DISPLAY);
		model.addAttribute(HEADLINE_ATTRIBUTE, HEADLINE);
		
		model.addAttribute(CONTENT_DISPLAY_ATTRIBUTE, CONTENT_DISPLAY);
		model.addAttribute(CONTENT_ATTRIBUTE , TEMPLATE_REPLACEMENT_EXPRESSION);
		
		model.addAttribute(FOOTER_DISPLAY_ATTRIBUTE, FOOTER_DISPLAY);
		
		return model;
	}

}
