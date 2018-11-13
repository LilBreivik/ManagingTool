package core.backend.REST.nonfileasset.notice.task.general;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier; 
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import core.backend.REST.general.task.response.nonrequest.AbstractFileHandlerResponseTaskWithoutRequestImpl; 
import resources.components.elements.POJO.Notices.NoticesPOJO;
import resources.components.filehandler.JSON.general.GeneralJSONFileHandler; 

@Service 
public class NoticeTaskWithoutRequestImpl<NoticePOJO > 
 
						extends AbstractFileHandlerResponseTaskWithoutRequestImpl< NoticePOJO,  
																			GeneralJSONFileHandler<NoticesPOJO> >{
	@Override
	@Autowired 
	public void setFileHandler(@Qualifier("JSONFileHandler for NoticesJSONFile")  GeneralJSONFileHandler<NoticesPOJO> fileHandler) {
	
		p_fileHandler = fileHandler; 
	}
     
}
 