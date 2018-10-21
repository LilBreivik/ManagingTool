package core.backend.NonREST.model.views;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import core.backend.NonREST.model.IndexPageView; 

@Component
public class LectureScheduleAssetsManagerView
											extends IndexPageView{

	
	public LectureScheduleAssetsManagerView() {
		
		HEADLINE_DISPLAY = true; 
		HEADLINE = "Vorlesungen Manager";
		
		CONTENT_DISPLAY = true; 
		TEMPLATE_REPLACEMENT_EXPRESSION ="fragments/lecturescheduleassetsmanager"; 
		
		FOOTER_DISPLAY = false;
	} 

}
