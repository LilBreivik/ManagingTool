package core.backend.REST.nonfileasset.notice.task.add;
 
 
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.nonfileasset.notice.parameter.NoticeParameter;
import core.backend.REST.nonfileasset.notice.task.NoticeTask;
import resources.components.elements.POJO.Notice.NoticeStatusPOJO;
import resources.components.elements.POJO.Notice.PersistenceNoticesPOJO;
import resources.components.filehandler.JSON.NoticesJSONFileHandler;
import resources.configuration.NoticesJSONFileHandlerProvider;

public class AddNoticeTask 
							extends NoticeTask<PersistenceNoticesPOJO>{

	@Override
	public void workOnTask(NoticeParameter param) {
		
		NoticesJSONFileHandler fileHandler = NoticesJSONFileHandlerProvider.provideNoticesJSONFileHandlerForUser(getLoggedInUser().getLoginName());
		
		 
		// we will store the notices wit a random id 
		final String tempFileName = UUID.randomUUID().toString();
		
		fileHandler.createFile( tempFileName );
		
		fileHandler.writeToFile(tempFileName, param.getRequest());
		
	}
	
	@Override
	public SuccessResponse<PersistenceNoticesPOJO> getResultsFromTask() {
		 
		final List<String> noticeheadlines = getAllNoticesPOJOs().stream().map(notice -> notice.getNoticeHeadline()).collect(Collectors.toList());
		
		final List<NoticeStatusPOJO> noticesStatus  = noticeheadlines.stream().map(headline -> new NoticeStatusPOJO(headline)).collect(Collectors.toList());
		
		PersistenceNoticesPOJO persistentNotices = new PersistenceNoticesPOJO(noticesStatus);
		
		return  new SuccessResponse<PersistenceNoticesPOJO>(persistentNotices);
	}
}
