package resources.utils.pathmanager;
 
import java.nio.file.Path;
import java.nio.file.Paths;  
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import resources.utils.general.Constants.Directory;
import resources.utils.general.GeneralPurpose;
import resources.utils.user.AuthorizedUserAccount;

/**
 * Class that wraps User 
 * Specific data , at the end of the given paths 
 * */
 
public class UserPathManager 
								extends PathManager{
 
	public UserPathManager(Directory... subdirectories) {
		
		super(subdirectories); 
		
	}
	
	
	/**
	 * Method that adds the current account 
	 * id at the end of a path
	 * */
	
	@Override
	public Path getPathToOperateOn() {
	 
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		AuthorizedUserAccount authorizedAccount = (AuthorizedUserAccount) authentication.getPrincipal();
		  	  
		return  buildUserSpecificPath(authorizedAccount.getLoggedInAccountId());  
	}
	
	public void createUserSpecificPath(int userAccountId) {
		
		Path userSpecificPath = buildUserSpecificPath(userAccountId);
		 
		PathManager currentWorkingDirectory = new PathManager(new String[0]);
		 
		super.createPath(GeneralPurpose.ArrayToCollection(userSpecificPath.toString().replace(currentWorkingDirectory.getPathToOperateOn().toString(), "").split("/")));
	}
	
	
	
	private Path buildUserSpecificPath(int userAccountId) {
		
		// user asset 
		
		final String UserAccountDelimeter = "ACC_" + userAccountId;
		  
		 
		return Paths.get(p_pathToOperateOn.toFile().toString(), 
				
				 UserAccountDelimeter);
		 
	}
}
