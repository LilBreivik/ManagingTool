package core.backend.REST.nonfileasset.notice.task.read;
   
import java.util.List;
import java.util.stream.Collectors;
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.nonfileasset.notice.task.NoticeTask;
import resources.components.elements.POJO.Notice.NoticeStatusPOJO;
import resources.components.elements.POJO.Notice.PersistenceNoticesPOJO; 

public class ReadGeneralNoticeTask 
								extends NoticeTask<PersistenceNoticesPOJO>{

	
	@Override
	public SuccessResponse<PersistenceNoticesPOJO> getResultsFromTask() {
		 
		final List<String> noticeheadlines = getAllNoticesPOJOs().stream().map(notice -> notice.getNoticeHeadline()).collect(Collectors.toList());
		
		final List<NoticeStatusPOJO> noticesStatus  = noticeheadlines.stream().map(headline -> new NoticeStatusPOJO(headline)).collect(Collectors.toList());
		
		PersistenceNoticesPOJO persistentNotices = new PersistenceNoticesPOJO(noticesStatus);
		
		return  new SuccessResponse<PersistenceNoticesPOJO>(persistentNotices);
	}

}
