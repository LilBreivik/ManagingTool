package core.backend.NonREST.model.views.assets;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import core.backend.NonREST.model.IndexPageView;
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO;

@Component 
public abstract class AssetStockContentView  

											extends   IndexPageView{

	 
	@Autowired
	@Qualifier("provide AssetStockViewer for Course Schedule") 
	protected AssetStockViewer<PersistenceCourseSchedulePOJO> p_courseScheduleAssetStockViewer;
	
	@Autowired
	@Qualifier("provide AssetStockViewer for Lecture Schedule")
	protected AssetStockViewer<PersistenceCourseSchedulePOJO> p_lectureScheduleAssetStockViewer;
	
	  
    protected  boolean checkExistingComplementarySchedules() {
		 
    	p_courseScheduleAssetStockViewer.loadAssetStockView("CourseSchedule");
    	
		List<AssetStockView> assetCourseScheduleStockView = p_courseScheduleAssetStockViewer.getAssetsStockViews();
		
		
		p_lectureScheduleAssetStockViewer.loadAssetStockView("CourseSchedule");
		
		List<AssetStockView> assetLectureScheduleStockView =  p_lectureScheduleAssetStockViewer.getAssetsStockViews();
		
		boolean complementarySchedulesExist = false; 
		
		for(int ctr = 0; ctr < assetCourseScheduleStockView.size(); ctr += 1) {
			
			if((assetCourseScheduleStockView.get(ctr).getStatus() == true) && 
						( assetLectureScheduleStockView.get(ctr).getStatus() == true )){
				
				complementarySchedulesExist = true; 
				ctr =  assetCourseScheduleStockView.size(); 
			}
		}
		
		return complementarySchedulesExist; 
	}
}
