package core.backend.utils.delete;

import java.nio.file.Path; 
import core.backend.REST.general.request.schedule.RESTScheduleRequest; 

@FunctionalInterface
public interface DeleteFileAssetsHandler {

	public boolean checkIfDeletableAssetsExist(RESTScheduleRequest  deleteParameter, 
												    Path pathManagerToSourceAssets, 
												     	Path pathManagerToAssmebledAssets);

 
}
