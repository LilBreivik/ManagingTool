package core.provider;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import core.backend.NonREST.model.views.utils.helper.AssetStockViewer;
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO; 
import resources.components.filehandler.XML.CourseScheduleXMLFileHandler;
import resources.database.repository.FilesRepository; 

@Configuration
public class AssetStockViewerProvider 
										extends MasterProvider<AssetStockViewer>{

	@Bean 
	@Qualifier("provide AssetStockViewer for Course Schedule")
	public AssetStockViewer<PersistenceCourseSchedulePOJO> 
				provideAssetStockViewerforCourseSchedule(CourseScheduleXMLFileHandler xmlFileHandler, FilesRepository filesRepo) {
	 
		AssetStockViewer<PersistenceCourseSchedulePOJO> courseScheduleAssetStockViewer =  new AssetStockViewer<PersistenceCourseSchedulePOJO> (xmlFileHandler , 
																								NameResolverProvider.provideNameResolverForCourseScheduleFile(), 
																									filesRepo);

		courseScheduleAssetStockViewer.loadAssetStockView("CourseSchedule");
		
		
		return courseScheduleAssetStockViewer;
	}

	@Bean 
	@Qualifier("provide AssetStockViewer for Lecture Schedule")
	public AssetStockViewer<PersistenceCourseSchedulePOJO> 
				provideAssetStockViewerforLectureSchedule(CourseScheduleXMLFileHandler xmlFileHandler, FilesRepository filesRepo) {
		 
		AssetStockViewer<PersistenceCourseSchedulePOJO> lectureScheduleAssetStockViewer =  new AssetStockViewer<PersistenceCourseSchedulePOJO> (xmlFileHandler , 
																			NameResolverProvider.provideNameResolverForLectureScheduleFile(), 
																		filesRepo);

		
		lectureScheduleAssetStockViewer.loadAssetStockView("CourseSchedule");
		
		
		return lectureScheduleAssetStockViewer;
	
	} 
}
