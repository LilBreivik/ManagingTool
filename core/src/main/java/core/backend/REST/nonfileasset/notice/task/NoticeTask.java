package core.backend.REST.nonfileasset.notice.task;

import java.io.File; 
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import core.backend.REST.general.task.response.AbstractFileHandlerResponseTaskImpl;
import core.backend.REST.nonfileasset.notice.parameter.NoticeParameter;
import resources.components.elements.POJO.Notices.NoticesPOJO;
import resources.components.filehandler.JSON.general.GeneralJSONFileHandler;  
import resources.utils.user.AuthorizedUserAccount; 


@Component
public class NoticeTask<NoticePOJO, Parameter extends NoticeParameter> 
 
						extends AbstractFileHandlerResponseTaskImpl<Parameter, 
																		NoticePOJO,  
																			GeneralJSONFileHandler<NoticesPOJO> >{
   
	@Override
	@Autowired 
	public void setFileHandler(@Qualifier("JSONFileHandler for NoticesJSONFile")  GeneralJSONFileHandler<NoticesPOJO> fileHandler) {
	
		p_fileHandler = fileHandler; 
	}
	
   
	  
	private AuthorizedUserAccount getLoggedInUser(){
		
		Authentication aut =  SecurityContextHolder.getContext().getAuthentication();
	   
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		return  (AuthorizedUserAccount) authentication.getPrincipal();
	}
	 
	protected List<File>  getAllNoticesFiles() {
			
		
		return p_fileHandler.getPathManager().getAllFilesOnPath();
	}
	
	protected List<NoticesPOJO>   getAllNoticesPOJOs() {
		 
		  
		return  getAllNoticesFiles()
							.stream().map(f -> f.getName())
								.map(fileName -> (NoticesPOJO) p_fileHandler.readFile(fileName) ) 
									.collect(Collectors.toList());
		
	} 
}
