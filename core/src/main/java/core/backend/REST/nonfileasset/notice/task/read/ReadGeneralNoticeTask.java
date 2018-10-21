package core.backend.REST.nonfileasset.notice.task.read;
   
import java.util.List;
import java.util.stream.Collectors;
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.nonfileasset.notice.task.NoticeTask;
import notice.NoticeStatusPOJO;
import notice.PersistenceNoticesPOJO;
import resources.components.elements.POJO.Notices.NoticesPOJO;
import resources.components.filehandler.JSON.general.GeneralJSONFileHandler;
import resources.database.repository.AccountsRepository; 


public class ReadGeneralNoticeTask 
								extends NoticeTask<PersistenceNoticesPOJO>{
	
	public ReadGeneralNoticeTask( GeneralJSONFileHandler<NoticesPOJO> fileHandler, 
			AccountsRepository accountsRepo) {
		super(fileHandler, accountsRepo);
	
	}

	@Override
	public SuccessResponse<PersistenceNoticesPOJO> getResultsFromTask() {
		 
		
		final List<String> noticeheadlines = getAllNoticesPOJOs().stream().map(notice -> notice.getNoticeHeadline()).collect(Collectors.toList());
		
		final List<NoticeStatusPOJO> noticesStatus  = noticeheadlines.stream().map(headline -> new NoticeStatusPOJO(headline)).collect(Collectors.toList());
		
		PersistenceNoticesPOJO persistentNotices = new PersistenceNoticesPOJO(noticesStatus);
		
		return  new SuccessResponse<PersistenceNoticesPOJO>(persistentNotices);
	}

}
