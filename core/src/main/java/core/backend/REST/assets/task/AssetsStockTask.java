package core.backend.REST.assets.task;
 
import org.springframework.dao.DataIntegrityViolationException;

import core.backend.REST.assets.parameter.AssetsStockCourseScheduleParameter;
import core.backend.REST.assets.parameter.AssetsStockParameter; 
import core.backend.REST.general.response.result.successfully.SuccessResponse; 
import core.backend.REST.general.task.response.AbstractResponseTaskImpl;
import resources.components.elements.POJO.Assets.AssetsStockPOJO; 
import resources.database.entities.File.Files;
import resources.database.repository.FilesRepository; 


public class AssetsStockTask < Parameter extends AssetsStockParameter> 
						extends  AbstractResponseTaskImpl < Parameter , AssetsStockPOJO  >{

	private Files retrievedAssetsStockFiles = null; 
	 
	private FilesRepository m_filesRepo; 
	

	public  AssetsStockTask( FilesRepository filesRepo) {
		
		m_filesRepo = filesRepo;
	}

	@Override
	public void workOnTask(Parameter param) {
 	
		final String assetsStockFileName = param.getFileNameResolver().getResolvedFileName(); 
			 
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
     
    	return  new SuccessResponse<AssetsStockPOJO>( AssetsStockPOJO.createAssetsStockPOJO(retrievedAssetsStockFiles)) ;
    }
} 
