package core.backend.REST.nonfileasset.notice.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths; 
import java.util.List;
import java.util.stream.Collectors; 
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import core.backend.REST.general.task.response.AbstractFileHandlerTaskImpl;
import core.backend.REST.nonfileasset.notice.parameter.NoticeParameter;
import resources.components.elements.POJO.Notices.NoticesPOJO;
import resources.components.filehandler.JSON.general.GeneralJSONFileHandler; 
import resources.database.repository.AccountsRepository;
import resources.utils.general.GeneralPurpose;
import resources.utils.user.AuthorizedUserAccount; 


public abstract  class NoticeTask< ResponsePOJOClass> 

						extends AbstractFileHandlerTaskImpl< GeneralJSONFileHandler<NoticesPOJO> ,NoticeParameter, ResponsePOJOClass>{
   
	
	protected GeneralJSONFileHandler<NoticesPOJO> p_JSONFileHandler;
	
	// every coordinator account got one path, that contains the created notices 
	// noticePath describes this given Path ..
	
	protected Path noticePath; 
	
	
	private AccountsRepository m_accountsRepo;
	
	/**
	 * @param (GeneralJSONFileHandler<NoticesPOJO>) , fileHandler
	 * */
	
	public NoticeTask(  GeneralJSONFileHandler<NoticesPOJO> fileHandler, 
			AccountsRepository accountsRepo) {
		super(fileHandler); 
		 
		m_accountsRepo = accountsRepo;
		  
	}
 
	 
	private AuthorizedUserAccount getLoggedInUser(){
		
		Authentication aut =  SecurityContextHolder.getContext().getAuthentication();
	   
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		return  (AuthorizedUserAccount) authentication.getPrincipal();
	}
	
	protected List<File>  getAllNoticesFiles() {
			
		noticePath = Paths.get(p_fileHandler.getPathManager().getPathToOperateOn().toString());
		
		return GeneralPurpose.ArrayToList(noticePath.toFile().listFiles());
	}
	
	protected List<NoticesPOJO>   getAllNoticesPOJOs() {
		 
		 
		List<Object> rawNoticeObjects =  getAllNoticesFiles()
											.stream().map(f -> f.getName())
												.map(fileName -> p_fileHandler.readJSONFile(fileName) ) 
													.collect(Collectors.toList());
		
		
		return rawNoticeObjects.stream().map(ob -> (NoticesPOJO) ob).collect(Collectors.toList());
	}
	
	
	 
}
