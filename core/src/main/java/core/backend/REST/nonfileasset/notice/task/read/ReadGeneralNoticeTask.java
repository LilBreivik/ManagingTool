package core.backend.REST.nonfileasset.notice.task.read;
   
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.nonfileasset.notice.parameter.NoticeParameter;
import core.backend.REST.nonfileasset.notice.task.NoticeTask;
import notice.NoticeStatusPOJO;
import notice.PersistenceNoticesPOJO; 

@Component
public class ReadGeneralNoticeTask 
								extends NoticeTask<PersistenceNoticesPOJO, NoticeParameter>{
	 
	 
	@Override
	public void workOnTask() {
	
		// no furhter use ... 
	}

	@Override
	public SuccessResponse<PersistenceNoticesPOJO> getResultsFromTask() {
		 
		
		final List<String> noticeheadlines = getAllNoticesPOJOs().stream().map(notice -> notice.getNoticeHeadline()).collect(Collectors.toList());
		
		final List<NoticeStatusPOJO> noticesStatus  = noticeheadlines.stream().map(headline -> new NoticeStatusPOJO(headline)).collect(Collectors.toList());
		
		PersistenceNoticesPOJO persistentNotices = new PersistenceNoticesPOJO(noticesStatus);
		
		return  new SuccessResponse<PersistenceNoticesPOJO>(persistentNotices);
	}

}
