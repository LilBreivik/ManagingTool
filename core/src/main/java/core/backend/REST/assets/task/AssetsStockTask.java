package core.backend.REST.assets.task;
 
import org.springframework.dao.DataIntegrityViolationException;
import core.backend.REST.general.request.schedule.RESTScheduleRequest;
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.general.task.general.GeneralAbstractTaskImpl;
import resources.components.elements.POJO.Assets.AssetsStockPOJO;
import resources.components.filehandler.general.GeneralFileHandler;
import resources.database.entities.File.Files;
import resources.database.repository.FilesRepository;
import resources.error.InternalError; 


public class AssetsStockTask 
						extends GeneralAbstractTaskImpl< RESTScheduleRequest,  AssetsStockPOJO>{

	private Files retrievedAssetsStockFiles = null; 
	 
	private FilesRepository m_filesRepo; 
	

	public  AssetsStockTask( FilesRepository filesRepo) {
		
		m_filesRepo = filesRepo;
	}
	
	
	@Override
	public void workOnTask(RESTScheduleRequest parameter) {
		 	
		final String assetsStockFileName = parameter.getFileNameResolver().getResolvedFileName(); 
		 
		try {
			  
			retrievedAssetsStockFiles = m_filesRepo.read(assetsStockFileName);
		}
		catch(Exception databaseException) {
			   
			  if(!(databaseException instanceof DataIntegrityViolationException)) {
				  
				  throw new InternalError("Cannot access Database");
			  }
			  
		} 
	 
    
	}


    @Override
    public SuccessResponse<AssetsStockPOJO> getResultsFromTask() {
    
    	System.out.println(retrievedAssetsStockFiles + "");
    	
    	return  new SuccessResponse<AssetsStockPOJO>( AssetsStockPOJO.createAssetsStockPOJO(retrievedAssetsStockFiles)) ;
    }
} 
