package core.backend.NonREST.model.views;


import java.util.List;

import core.backend.NonREST.model.views.utils.helper.AssetStockView;
import core.backend.NonREST.model.views.utils.helper.AssetStockViewer;
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO;

public abstract class AssetStockContentView 
										extends ContentView{

	
	protected AssetStockViewer<PersistenceCourseSchedulePOJO> p_courseScheduleAssetStockViewer;
	
	protected AssetStockViewer<PersistenceCourseSchedulePOJO> p_lectureScheduleAssetStockViewer;
	
	public AssetStockContentView (AssetStockViewer<PersistenceCourseSchedulePOJO> courseScheduleAssetStockViewer, 
			AssetStockViewer<PersistenceCourseSchedulePOJO> lectureScheduleAssetStockViewer) {
		
		p_courseScheduleAssetStockViewer = courseScheduleAssetStockViewer;
		
		p_lectureScheduleAssetStockViewer = lectureScheduleAssetStockViewer; 
		
	}
	 
	 
    protected  boolean checkExistingComplementarySchedules() {
		
    
    	
		List<AssetStockView> assetCourseScheduleStockView = p_courseScheduleAssetStockViewer.getAssetsStockViews();
		
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
