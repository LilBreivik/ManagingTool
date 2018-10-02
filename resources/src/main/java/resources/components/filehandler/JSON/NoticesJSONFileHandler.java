package resources.components.filehandler.JSON;


import resources.components.elements.POJO.Notice.NoticesPOJO;
import resources.components.elements.POJO.Schedule.CoursePOJO;
import resources.components.filehandler.PathManager;
import resources.components.filehandler.assetsmanager.FileAssetsManager;
import resources.database.entities.User.Users;


public class NoticesJSONFileHandler extends JSONFileHandler{
 
	public NoticesJSONFileHandler(FileAssetsManager fileassetsmanager ) {
		super(NoticesPOJO.class,  fileassetsmanager);   

	}
 
 
}
   