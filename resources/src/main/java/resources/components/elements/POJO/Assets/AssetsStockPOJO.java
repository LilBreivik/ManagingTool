package resources.components.elements.POJO.Assets;

import resources.database.entities.File.Files;

public class AssetsStockPOJO {

	// file is present
	protected  boolean presentFlag;

	protected String uploaderName; 
	
	public static AssetsStockPOJO createAssetsStockPOJO(Files files) {
		
		if(files == null) {
			
			return new AssetsStockDoesNotExist();
		}
		else {
			
			AssetsStockPOJO existingAssetsStockPOJO  = new AssetsStockPOJO();
			
			existingAssetsStockPOJO.setPresentFlag(true);
			
			existingAssetsStockPOJO.setuploaderName(files.getFileUploader());
			
			return existingAssetsStockPOJO; 
			
		}
		
	} 
	
	public void setuploaderName(String uploaderName) {
		
		this.uploaderName = uploaderName; 
	}
	
	public String getuploaderName() {
		
		return this.uploaderName; 
	}
	
	public void setPresentFlag(boolean presentFlag) {
		
		this.presentFlag = presentFlag; 
	}
	
	public boolean getPresentFlag() {
		
		return this.presentFlag; 
	}
	 
}
