package core.backend.NonREST.model.views;

import org.springframework.stereotype.Component; 
import core.backend.NonREST.model.IndexPageView;
 

@Component
public class CourseScheduleAssetsManagerView
						extends   IndexPageView{

	
	public CourseScheduleAssetsManagerView() {
		
		HEADLINE_DISPLAY = true; 
		HEADLINE = "Studienverlaufsplan Manager";
		
		CONTENT_DISPLAY = true; 
		TEMPLATE_REPLACEMENT_EXPRESSION ="fragments/coursescheduleassetsmanager"; 
		
		FOOTER_DISPLAY = false;
	}

}
