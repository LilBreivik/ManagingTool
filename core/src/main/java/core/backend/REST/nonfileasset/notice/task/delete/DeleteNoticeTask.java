package core.backend.REST.nonfileasset.notice.task.delete;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.nonfileasset.notice.parameter.NoticeParameter;
import core.backend.REST.nonfileasset.notice.task.NoticeTask;
import resources.components.elements.POJO.Notice.NoticeStatusPOJO;
import resources.components.elements.POJO.Notice.NoticesPOJO;
import resources.components.elements.POJO.Notice.PersistenceNoticesPOJO;
import resources.components.filehandler.JSON.NoticesJSONFileHandler;
import resources.configuration.NoticesJSONFileHandlerProvider;

public class DeleteNoticeTask 
								extends NoticeTask<PersistenceNoticesPOJO>{

	@Override
	public void workOnTask(NoticeParameter param) {
		
		NoticesJSONFileHandler fileHandler = NoticesJSONFileHandlerProvider.provideNoticesJSONFileHandlerForUser(getLoggedInUser().getLoginName());
		
		
		List<File> noticeFiles = getAllNoticesFiles().stream()
				.filter(notice -> ((NoticesPOJO) fileHandler.readFile(notice.getName())).getNoticeHeadline().equals(param.getRequest().getNoticeHeadline()))
			.collect(Collectors.toList());
		
		if(noticeFiles.size() == 1) {
			
			fileHandler.deleteFile(noticeFiles.get(0));
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
