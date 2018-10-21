package core.backend.REST.nonfileasset.notice.task.delete;
 
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.nonfileasset.notice.parameter.NoticeParameter;
import core.backend.REST.nonfileasset.notice.task.NoticeTask;
import notice.NoticeStatusPOJO;
import notice.PersistenceNoticesPOJO;
import resources.components.elements.POJO.Notices.NoticesPOJO;
import resources.components.filehandler.JSON.general.GeneralJSONFileHandler;
import resources.database.repository.AccountsRepository; 

public class DeleteNoticeTask 
								extends NoticeTask < PersistenceNoticesPOJO>{

	public DeleteNoticeTask(GeneralJSONFileHandler<NoticesPOJO> fileHandler, 
			AccountsRepository accountsRepo) {
		super(fileHandler, accountsRepo);
	}
 
	@Override
	public void workOnTask(NoticeParameter param) {
	 
		
		List<File> noticeFiles = getAllNoticesFiles().stream()
				.filter(notice -> ( p_fileHandler.readJSONFile(notice.getName())).getNoticeHeadline().equals(param.getRequest().getNoticeHeadline()))
			.collect(Collectors.toList());
		
		if(noticeFiles.size() == 1) {
			
			
			p_fileHandler.deleteFile(noticeFiles.get(0).getName());
		}
		else {
			
			throw new InternalError("Die Notiz " + param.getRequest().getNoticeHeadline() + " existiert nicht ");
		}
	 
		 
	}
	
	@Override
	public SuccessResponse<PersistenceNoticesPOJO> getResultsFromTask() {
		 
		
		final List<String> noticeheadlines = getAllNoticesPOJOs().stream().map(notice -> notice.getNoticeHeadline()).collect(Collectors.toList());
		
		final List<NoticeStatusPOJO> noticesStatus  = noticeheadlines.stream().map(headline -> new NoticeStatusPOJO(headline)).collect(Collectors.toList());
		
		PersistenceNoticesPOJO persistentNotices = new PersistenceNoticesPOJO(noticesStatus);
		
		return  new SuccessResponse<PersistenceNoticesPOJO>(persistentNotices);
	}
	
	
}
