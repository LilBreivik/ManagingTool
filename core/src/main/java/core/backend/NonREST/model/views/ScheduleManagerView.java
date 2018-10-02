package core.backend.NonREST.model.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import core.backend.NonREST.model.configuration.IPageView;
import core.backend.NonREST.model.views.utils.helper.AssetStockViewer;
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO; 

@Component
public class ScheduleManagerView  
						extends  AssetStockContentView
									implements IPageView{

	
	@Autowired	
	public ScheduleManagerView(@Qualifier("provide AssetStockViewer for Course Schedule") AssetStockViewer<PersistenceCourseSchedulePOJO> courseScheduleAssetStockViewer, 
			@Qualifier("provide AssetStockViewer for Lecture Schedule") AssetStockViewer<PersistenceCourseSchedulePOJO> lectureScheduleAssetStockViewer) {
		
		super(courseScheduleAssetStockViewer, lectureScheduleAssetStockViewer);
		 
		HEADLINE_DISPLAY = true; 
		HEADLINE = "Stundenplan";
		
		CONTENT_DISPLAY = true; 
		
		
		FOOTER_DISPLAY = false;
		
	}
	
	
	
	@Override
	public Model buildRequiredView(Model model) {
		
		p_courseScheduleAssetStockViewer.createAssetStockView();
		
		p_lectureScheduleAssetStockViewer.createAssetStockView();
		
		if(checkExistingComplementarySchedules()) {
			
			TEMPLATE_REPLACEMENT_EXPRESSION ="fragments/schedulemanager"; 
		}
		else {
			
			model.addAttribute("COURSE_STOCK_ASSETS_VIEW", p_courseScheduleAssetStockViewer.getAssetsStockViews());
		
    		model.addAttribute("LECTURE_STOCK_ASSETS_VIEW", p_lectureScheduleAssetStockViewer.getAssetsStockViews());
			 
	    	model.addAttribute("STATE_DESCRIPTION", "col-md-9 col-sm-9 col-xs-12");
			
	    	model.addAttribute("DISPLAY_STATE", true);
				
		
			TEMPLATE_REPLACEMENT_EXPRESSION ="fragments/filesstorage"; 
		}
		
		
		model.addAttribute(HEADLINE_DISPLAY_ATTRIBUTE, HEADLINE_DISPLAY);
		model.addAttribute(HEADLINE_ATTRIBUTE, HEADLINE);
		
		model.addAttribute(CONTENT_DISPLAY_ATTRIBUTE, CONTENT_DISPLAY);
		model.addAttribute(CONTENT_ATTRIBUTE , TEMPLATE_REPLACEMENT_EXPRESSION);
		
		model.addAttribute(FOOTER_DISPLAY_ATTRIBUTE, FOOTER_DISPLAY);
		
		return model;
	}

}
