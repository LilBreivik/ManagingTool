package core.backend.REST.assets.task;
 
import org.springframework.dao.DataIntegrityViolationException; 
import core.backend.REST.assets.parameter.request.AssetsStockParameter; 
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.general.task.AbstractTaskImpl;
import resources.components.elements.POJO.Assets.AssetsStockPOJO;
import resources.database.entities.File.Files;
import resources.database.repository.FilesRepository;
import resources.error.InternalError; 


public class AssetsStockTask 
							extends AbstractTaskImpl<AssetsStockParameter,  AssetsStockPOJO>{

	private Files retrievedAssetsStockFiles = null; 
	 
	private FilesRepository m_filesRepo; 
	

	public  AssetsStockTask( FilesRepository filesRepo) {
		
		m_filesRepo = filesRepo;
	}
	
	
	@Override
	public void workOnTask(AssetsStockParameter parameter) {
		 	
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
