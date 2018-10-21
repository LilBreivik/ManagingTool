package core.backend.REST.nonfileasset.notice.task.read;
   
import java.io.File; 
import java.util.List;
import java.util.stream.Collectors;
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.nonfileasset.notice.parameter.NoticeParameter;
import core.backend.REST.nonfileasset.notice.task.NoticeTask;
import resources.components.elements.POJO.Notices.NoticesPOJO;
import resources.components.filehandler.JSON.general.GeneralJSONFileHandler;
import resources.database.repository.AccountsRepository;
import resources.error.InternalError; 


public class ReadSpecificNoticeTask 
							extends NoticeTask<NoticesPOJO>{


	public ReadSpecificNoticeTask( GeneralJSONFileHandler<NoticesPOJO> fileHandler, 
			AccountsRepository accountsRepo){
		super(fileHandler, accountsRepo);
		 
	}

	private NoticesPOJO statusPOJO; 
	
	@Override
	public void workOnTask(NoticeParameter param) {
	 
		
		List<File> noticeFiles = getAllNoticesFiles().stream()
				.filter(notice -> ((NoticesPOJO)  p_fileHandler.readJSONFile(notice.getName())).getNoticeHeadline().equals(param.getRequest().getNoticeHeadline()))
			.collect(Collectors.toList());

	 
		if(noticeFiles.size() == 1) {
			
			statusPOJO =   p_fileHandler.readJSONFile(noticeFiles.get(0).getName());
			
			
		}
		else {
			 
			
			throw new InternalError("Die Notiz " + param.getRequest().getNoticeHeadline() + " existiert nicht ");
		}		 
	}

	@Override
	public SuccessResponse<NoticesPOJO> getResultsFromTask() {
		 
		System.out.println(statusPOJO);
		System.out.println();
		
		
		return  new SuccessResponse<NoticesPOJO>(statusPOJO);
	}
	 
}
