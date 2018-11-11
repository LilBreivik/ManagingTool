package core.backend.REST.nonfileasset.notice.task.delete;
 

import static com.google.common.collect.MoreCollectors.onlyElement;

import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.nonfileasset.notice.parameter.NoticeParameter;
import core.backend.REST.nonfileasset.notice.task.NoticeTask;
import notice.NoticeStatusPOJO;
import notice.PersistenceNoticesPOJO;
import resources.components.elements.POJO.Notices.NoticesPOJO; 

@Component
public class DeleteNoticeTask 
								extends NoticeTask < PersistenceNoticesPOJO,  NoticeParameter>{
 
	@Override
	public void workOnTask( NoticeParameter  param) {
		 
		final String requestedNoticeWithHealdine = param.getRequest(). getNoticeHeadline();
		
		final List<NoticesPOJO> allNoticesPOJO = getAllNoticesPOJOs(); 
		
		try {
			
			NoticesPOJO pojo = allNoticesPOJO
										.stream().filter(notice -> notice.getNoticeHeadline().equals(requestedNoticeWithHealdine))
											.collect(onlyElement());
			
		
			p_fileHandler.deleteFile(getAllNoticesFiles().get(allNoticesPOJO.indexOf(pojo)).getName());
		}
		
		
		
		catch(NoSuchElementException noticDoesNotExist) {
			
			throw new InternalError("Die Notiz " + requestedNoticeWithHealdine+ " existiert nicht ");
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
