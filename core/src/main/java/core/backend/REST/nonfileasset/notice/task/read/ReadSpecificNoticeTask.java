package core.backend.REST.nonfileasset.notice.task.read;
   

import static com.google.common.collect.MoreCollectors.onlyElement;

import java.io.File;  
import java.util.NoSuchElementException;
import org.springframework.stereotype.Component;
import core.backend.REST.general.response.result.successfully.SuccessResponse; 
import core.backend.REST.nonfileasset.notice.parameter.SpecficNoticeParameter;
import core.backend.REST.nonfileasset.notice.task.NoticeTask;
import notice.PersistenceNoticesPOJO;
import resources.components.elements.POJO.Notices.NoticesPOJO; 
import resources.error.InternalError; 

@Component
public class ReadSpecificNoticeTask 
							extends NoticeTask<NoticesPOJO, SpecficNoticeParameter>{

	private NoticesPOJO m_statusPOJO;
	 
 
	@Override
	public void workOnTask(SpecficNoticeParameter param) {
		
		final String requestedNoticeWithHealdine = param.getRequest().getNoticeHeadline();
		
		try {
			
			// there cannot be 2 or more notices with the same headline, cause, 
			// new notices, that match a certain headline, will overwirte the persistent one 
		
			File noticeFile = getAllNoticesFiles().stream()
					.filter(notice -> ((NoticesPOJO)  p_fileHandler.readFile(notice.getName())).getNoticeHeadline().equals(requestedNoticeWithHealdine))
				.collect(onlyElement());
			
			
			m_statusPOJO =   p_fileHandler.readFile(noticeFile.getName());
			
		}
		catch(NoSuchElementException noticeIsMissing) {
			
			throw new InternalError("Die Notiz " + requestedNoticeWithHealdine + " existiert nicht ");
		}

	}
	
	@Override
	public SuccessResponse<NoticesPOJO> getResultsFromTask() {
	 
		return  new SuccessResponse< NoticesPOJO >(m_statusPOJO);
 
	}
}
