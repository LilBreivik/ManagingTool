package core.backend.utils.delete;

import java.nio.file.Path;

import core.backend.REST.fileasset.delete.parameter.request.DeleteFileParameter; 
@FunctionalInterface
public interface DeleteFileAssetsHandler {

	public boolean checkIfDeletableAssetsExist( DeleteFileParameter  deleteParameter, 
												    Path pathManagerToSourceAssets, 
												     	Path pathManagerToAssmebledAssets);

 
}
