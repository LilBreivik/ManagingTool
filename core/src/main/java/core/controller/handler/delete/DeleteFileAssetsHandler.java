package core.controller.handler.delete;

import java.nio.file.Path;

import core.controller.parameter.GenericScheduleFileParam; 

@FunctionalInterface
public interface DeleteFileAssetsHandler {

	public boolean checkIfDeletableAssetsExist( GenericScheduleFileParam   deleteParameter, 
												    Path pathManagerToSourceAssets, 
												     	Path pathManagerToAssmebledAssets);

 
}
