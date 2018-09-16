package resources.components.filehandler;

import java.io.File;
import java.nio.file.Path;

import resources.components.filehandler.assetsmanager.FileAssetsManager;; 

public abstract class FileHandler {

	protected FileAssetsManager p_fileAssetsManager;
	 
	public FileHandler(FileAssetsManager  fileAssetManager){
		
		p_fileAssetsManager = fileAssetManager;
	} 
	public FileAssetsManager getFileAssetsManager() {
		
		return p_fileAssetsManager;
	}
	 
	
	public void moveFile(File file, Path targetPath) {
		
		p_fileAssetsManager.moveFile(file, targetPath);
	}

	public void deleteFile(File file) {
		
		p_fileAssetsManager.deleteFile(file);
	}
	
	public abstract Object readFile(String fileName);
	
	 
}
