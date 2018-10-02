package core.backend.REST.nonfileasset.notice.task.read;
   
import java.io.File; 
import java.util.List;
import java.util.stream.Collectors;
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.nonfileasset.notice.parameter.NoticeParameter;
import core.backend.REST.nonfileasset.notice.task.NoticeTask; 
import resources.components.elements.POJO.Notice.NoticesPOJO; 
import resources.components.filehandler.JSON.NoticesJSONFileHandler;
import resources.configuration.NoticesJSONFileHandlerProvider;
import resources.error.InternalError; 

public class ReadSpecificNoticeTask 
							extends NoticeTask<NoticesPOJO>{

	private NoticesPOJO statusPOJO; 
	
	@Override
	public void workOnTask(NoticeParameter param) {
		 
		
		NoticesJSONFileHandler fileHandler = NoticesJSONFileHandlerProvider.provideNoticesJSONFileHandlerForUser(getLoggedInUser().getLoginName());
		
		
		List<File> noticeFiles = getAllNoticesFiles().stream()
				.filter(notice -> ((NoticesPOJO) fileHandler.readFile(notice.getName())).getNoticeHeadline().equals(param.getRequest().getNoticeHeadline()))
			.collect(Collectors.toList());
		
		if(noticeFiles.size() == 1) {
			
			statusPOJO = (NoticesPOJO) fileHandler.readFile(noticeFiles.get(0).getName());
			
			
		}
		else {
			 
			
			throw new InternalError("Die Notiz " + param.getRequest().getNoticeHeadline() + " existiert nicht ");
		}		 
	}

	@Override
	public SuccessResponse<NoticesPOJO> getResultsFromTask() {
		 
		
		return  new SuccessResponse<NoticesPOJO>(statusPOJO);
	}
	 
}
