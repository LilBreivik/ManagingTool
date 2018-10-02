package resources.configuration;
   
import resources.components.filehandler.JSON.NoticesJSONFileHandler;
import resources.database.entities.User.Users;

public class NoticesJSONFileHandlerProvider  {
	 
	public static NoticesJSONFileHandler provideNoticesJSONFileHandlerForCoordinatorAccountCreation(Users user) {
	
		return new NoticesJSONFileHandler(FileAssetManagerProvider.provideNoticesJSONFileHandlerForUserCreationFileAssetsManager(user));
	}
	   
	public static NoticesJSONFileHandler provideNoticesJSONFileHandlerForUser(String userName) {
		
		return new NoticesJSONFileHandler( FileAssetManagerProvider.provideNoticesJSONFileHandlerForUserFileAssetsManager(userName) );
	}

	 
 
}
