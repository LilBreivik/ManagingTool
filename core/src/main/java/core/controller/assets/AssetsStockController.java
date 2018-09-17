package core.controller.assets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import core.controller.parameter.assets.AssetsStockParam;
import resources.components.elements.POJO.Assets.AssetsStockPOJO;
import resources.database.entities.File.Files;
import resources.database.repository.FilesRepository;
import resources.error.InternalError;


@Component
public class AssetsStockController {

	
	@Autowired 
	private FilesRepository filesRepo; 
	
	protected AssetsStockPOJO checkAssetsInStock(AssetsStockParam stockParam) {
		
		final String assetsStockFileName = stockParam.getUploadedFileName().getResolvedUploadedFileName(); 
		
		Files retrievedAssetsStockFiles = null;
		
		
		try {
			  
			retrievedAssetsStockFiles = filesRepo.read(assetsStockFileName);
		}
		catch(Exception databaseException) {
			   
			  if(!(databaseException instanceof DataIntegrityViolationException)) {
				  
				  throw new InternalError("Cannot access Database");
			  }
			  
		} 
		
		return AssetsStockPOJO.createAssetsStockPOJO(retrievedAssetsStockFiles);

	}
	
}
