package core.backend.REST.nonfileasset.notice.task;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder; 
import core.backend.REST.general.task.AbstractTaskImpl;
import core.backend.REST.nonfileasset.notice.parameter.NoticeParameter;
import core.configuration.authentication.user.AuthorizedUserAccount; 
import resources.components.elements.POJO.Notice.NoticesPOJO; 
import resources.components.filehandler.JSON.NoticesJSONFileHandler;
import resources.configuration.NoticesJSONFileHandlerProvider;
import resources.utils.general.GeneralPurpose; 

public abstract  class NoticeTask<ResponseType> 

						extends AbstractTaskImpl<NoticeParameter,  ResponseType>{
 
	
	 protected AuthorizedUserAccount getLoggedInUser(){
		
		 Authentication aut =  SecurityContextHolder.getContext().getAuthentication();
		 System.out.println();
		 
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		return  (AuthorizedUserAccount) authentication.getPrincipal();
	}
	
	protected List<File>  getAllNoticesFiles() {
		
		NoticesJSONFileHandler fileHandler = NoticesJSONFileHandlerProvider.provideNoticesJSONFileHandlerForUser(getLoggedInUser().getLoginName());
		
		final File folder = new File(fileHandler.getFileAssetsManager()
													.getPathManager()
														.getPathToOperateOn()
															.toAbsolutePath().toString());
		
		
		return GeneralPurpose.ArrayToList(folder.listFiles());
	}
	
	
	protected List<NoticesPOJO>   getAllNoticesPOJOs() {
		 
		NoticesJSONFileHandler fileHandler = NoticesJSONFileHandlerProvider.provideNoticesJSONFileHandlerForUser(getLoggedInUser().getLoginName());
		
		List<Object> rawNoticeObjects =  getAllNoticesFiles()
											.stream().map(f -> f.getName())
												.map(fileName -> fileHandler.readFile(fileName) ) 
													.collect(Collectors.toList());
		
		
		return rawNoticeObjects.stream().map(ob -> (NoticesPOJO) ob).collect(Collectors.toList());
	}
		
}
