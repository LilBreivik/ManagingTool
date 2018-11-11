package core.backend.NonREST.model.views.assets;


import static com.google.common.collect.MoreCollectors.onlyElement;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors; 
import resources.components.elements.POJO.Course.CourseSchedulePOJO;
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO; 
import resources.components.filehandler.XML.general.RawXMLFileHandler;
import resources.database.entities.File.Files;
import resources.database.repository.FilesRepository;
import resources.utils.general.GeneralPurpose;
import resources.utils.names.INameResolver;

/**
 * Class that is need to
 * chekc if certain files exist.. 
 * and to show their status to the client 
 * 
 * */

public class AssetStockViewer<PersistencePOJOType extends PersistenceCourseSchedulePOJO> 
{

	public String name; 
	
	public boolean active; 
	
	private PersistencePOJOType  m_persistentCourseSchedulePOJO ; 
	
	private RawXMLFileHandler<PersistencePOJOType>  m_xmlFileHandler;
 
	private FilesRepository m_filesRepo; 
	
	private List<AssetStockView> m_assetsStockViews; 
	 
	private  INameResolver  m_INameResolver; 
	
	public AssetStockViewer(RawXMLFileHandler<PersistencePOJOType>  xmlFileHandler , 
									INameResolver INameResolver, 
										FilesRepository filesRepo) {
		
		 m_xmlFileHandler = xmlFileHandler;
		 m_INameResolver = INameResolver; 
		 m_filesRepo = filesRepo; 
		
	}
		 
	

	public  void loadAssetStockView(String courseScheduleFileName){
 
		 m_persistentCourseSchedulePOJO = (PersistencePOJOType)  m_xmlFileHandler.readFile(courseScheduleFileName);
	}
	
	 
	public void   createAssetStockView() {
		
		List<CourseSchedulePOJO>  courseSchedulePOJO = GeneralPurpose.CollectionToList(m_persistentCourseSchedulePOJO.getCoursesSchedulePOJO());
		
		List<Files> scheduleFiles = m_filesRepo.read(); 
		
		
		m_assetsStockViews = courseSchedulePOJO.stream().map(pojo -> new AssetStockView(pojo.getCourseName() , 
														pojo.getCourseDegree(), 
															pojo.getCourseTerm() , m_INameResolver)).collect(Collectors.toList())
			 						
								   .stream().map(asset -> {
							
									  try {
											    	

										  Files file = scheduleFiles.stream()
										   		  	 .filter(fileInRepository -> fileInRepository .getFileName().equals(asset.getResolvedFileName()))
										   		 .collect(onlyElement());
										
										  asset.setStatus(file.isFileUploaded());
										  
										  return asset; 
									  }
									
									  catch(NoSuchElementException | IllegalArgumentException fileIsNotUploaded) {
										  
										  return asset; 
									  }
									  
											
								   }).collect(Collectors.toList());
		 
	}

	
	
	public List<AssetStockView> getAssetsStockViews(){
		
		return m_assetsStockViews; 
	}
	
}
