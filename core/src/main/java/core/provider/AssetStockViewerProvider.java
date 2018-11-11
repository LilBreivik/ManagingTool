package core.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import core.backend.NonREST.model.views.assets.AssetStockViewer;
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO; 
import resources.components.filehandler.XML.general.RawXMLFileHandler;
import resources.database.repository.FilesRepository; 

@Configuration
public class AssetStockViewerProvider 
										extends MasterProvider<AssetStockViewer>{

	
	@Autowired 
	@Qualifier("XMLFileHandler for CourseScheduleXMLFile")
	private RawXMLFileHandler<PersistenceCourseSchedulePOJO>  xmlFileHandler;
 
	 
	@Bean 
	@Qualifier("provide AssetStockViewer for Course Schedule")
	public AssetStockViewer<PersistenceCourseSchedulePOJO> 
				provideAssetStockViewerforCourseSchedule(FilesRepository filesRepo) {
	 
		AssetStockViewer<PersistenceCourseSchedulePOJO> courseScheduleAssetStockViewer =  new AssetStockViewer<PersistenceCourseSchedulePOJO> (xmlFileHandler , 
																								NameResolverProvider.provideNameResolverForCourseScheduleFile(), 
																									filesRepo);
 
		
		return courseScheduleAssetStockViewer;
	}

	@Bean 
	@Qualifier("provide AssetStockViewer for Lecture Schedule")
	public AssetStockViewer<PersistenceCourseSchedulePOJO> 
				provideAssetStockViewerforLectureSchedule( FilesRepository filesRepo) {
		 
		AssetStockViewer<PersistenceCourseSchedulePOJO> lectureScheduleAssetStockViewer =  new AssetStockViewer<PersistenceCourseSchedulePOJO> (xmlFileHandler , 
																			NameResolverProvider.provideNameResolverForLectureScheduleFile(), 
																		filesRepo);
 
		
		return lectureScheduleAssetStockViewer;
	
	} 
}
