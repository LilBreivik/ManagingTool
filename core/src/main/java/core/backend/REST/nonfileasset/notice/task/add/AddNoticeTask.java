package core.backend.REST.nonfileasset.notice.task.add;
  
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.nonfileasset.notice.parameter.NoticeParameter;
import core.backend.REST.nonfileasset.notice.task.NoticeTask;
import notice.NoticeStatusPOJO;
import notice.PersistenceNoticesPOJO;
import resources.components.elements.POJO.Notices.NoticesPOJO;
import resources.components.filehandler.JSON.general.GeneralJSONFileHandler;
import resources.database.repository.AccountsRepository;
 
@Component
public class AddNoticeTask
							extends NoticeTask<PersistenceNoticesPOJO, NoticeParameter>{
 
	@Override
	public void workOnTask(NoticeParameter param)  {
		 
		// we will store the notices wit a random id 
		final String tempFileName = UUID.randomUUID().toString();
		
		p_fileHandler.createFile( tempFileName );
		
	 	p_fileHandler.writeToFile(tempFileName, param.getRequest());
		 
	}
	
	@Override
	public SuccessResponse<PersistenceNoticesPOJO> getResultsFromTask() {
		 
		final List<String> noticeheadlines = getAllNoticesPOJOs().stream().map(notice -> notice.getNoticeHeadline()).collect(Collectors.toList());
		
		final List<NoticeStatusPOJO> noticesStatus  = noticeheadlines.stream().map(headline -> new NoticeStatusPOJO(headline)).collect(Collectors.toList());
		
		PersistenceNoticesPOJO persistentNotices = new PersistenceNoticesPOJO(noticesStatus);
		
		return  new SuccessResponse< PersistenceNoticesPOJO >(persistentNotices);
	}
}
 

