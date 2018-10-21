package resources.database.entities.factory;
 
import java.util.List;
import java.util.stream.Collectors; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import resources.components.elements.POJO.Notices.NoticesPOJO;
import resources.components.filehandler.JSON.general.GeneralJSONFileHandler; 
import resources.database.entities.Accounts.Accounts;
import resources.database.entities.Accounts.Accounts.AccountTypes;
import resources.database.entities.User.Users; 
import resources.database.repository.AccountsRepository;
import resources.database.repository.UsersRepository;
import resources.error.InternalError;
import resources.error.TransactionError;
import resources.utils.pathmanager.PathManager;
import resources.utils.pathmanager.UserPathManager; 


@Component
public class UserAccountsManager {
	 
	@Autowired 
	private  UsersRepository usersRepo;
	  
	@Autowired 
    private  AccountsRepository accountsRepo;
	  
	@Autowired 
	@Qualifier("JSONFileHandler for NoticesJSONFile")
	public GeneralJSONFileHandler<NoticesPOJO>  noticeJSONFileHandler;
	 
	
	public Users createUser(String userEmail, String userName) {
		
		 Users user = new Users(); 
		  

		 user.setUserEmail(userEmail);
		 
		 user.setUserName(userName);
		 
		 return user; 
	}
	
	
	
	public void addUser(Users user) {
		
	  try {
		  
	    	usersRepo.create(user);
	  }
	  catch(Exception databaseException) {
		   
		  if(databaseException instanceof DataIntegrityViolationException) {
			  
			  throw new InternalError("Account does exist");
		  }
		  
		  else {
			  
			  throw new InternalError("Internal Server Error");
		  }
	  } 
		
	}
	
	 
	public void deleteUser(Users user) {
		
		  try {
			  
		    	usersRepo.delete(user );
		  }
		  catch(Exception databaseException) {
			   
			  if(databaseException instanceof DataIntegrityViolationException) {
				  
				  throw new InternalError("Account does exist");
			  }
			  
			  else {
				  
				  throw new InternalError("Internal Server Error");
			  }
		  } 		
	}
	
	public void updateUser(Users user) {
		
		  try {
			  
		    	usersRepo.update(user);
		  }
		  catch(Exception databaseException) {
			   
			  if(databaseException instanceof DataIntegrityViolationException) {
				  
				  throw new InternalError("Account does exist");
			  }
			  
			  else {
				  
				  throw new InternalError("Internal Server Error");
			  }
		  } 
			
	}
	
	
	public boolean verifyAccount(Users user, AccountTypes accountType, String password) {
	    	
		  boolean verifiedFlag  = false; 
			
	  	  try {
	  	    
	  		for(Accounts acc : accountsRepo.read(user).stream().filter(acc -> acc.getAccountType().equals(accountType)).collect(Collectors.toList())) {
	  			
	  			if( Accounts.validateCredentials(password, acc.getAccountPasswordHash())) {
	  				
	  				verifiedFlag = true; 
	  				break;
	  			}
	  		}
	  		
	  		return verifiedFlag;
	  		 
		  }	  
		  catch(Exception databaseException) {
				   
				  if(databaseException instanceof DataIntegrityViolationException) {
					  
					  throw new InternalError("Account does exist");
				  }
				  
				  else  {
					  
					  throw new InternalError("Internal Server Error");
				  }
			  } 
	} 
	

    public List<Accounts> getAccountsByType(Users user, AccountTypes accountType) {
    	
    	return  accountsRepo.read(user).stream().filter(acc -> acc.getAccountType().equals(accountType)).collect(Collectors.toList());
    
    }
	
    
    public void createAccount(Users user, Accounts newCreatedAccount) {
    	
    	  try {
    		  
    		    newCreatedAccount.setAccountOwners(user);
    	    	accountsRepo.create( newCreatedAccount);
    	    	
    	    	if(newCreatedAccount.getAccountType().equals(AccountTypes.COORDINATOR)) 
      			{
      				// path gets created in the constructor
      				 	
    	    		// keep in mind, we are using the UserPathManager at this point 
    	    	 	
    	    		UserPathManager userSpecificPathManager = (UserPathManager)  noticeJSONFileHandler .getPathManager();
    	    		
    	    		userSpecificPathManager.createUserSpecificPath(newCreatedAccount.getAccountId());
      			}
		  }
		   
		  catch(Exception databaseException) {
			   
			  if(databaseException instanceof DataIntegrityViolationException) {
				  
				  throw new InternalError("Account does exist");
			  }
			  
			  else {
				  
				  throw new InternalError("Internal Server Error");
			  }
		  } 
    	   
    }
    
    
     public void updateAccount( Accounts account) {
    	    

	    final Accounts currentAccount = accountsRepo.read(account.getAccountId());
		
		final Accounts updatedAccount = account; 
		
		
		// if the current account is NOT a Coordinator account 
		
		if(!currentAccount.getAccountType().equals(AccountTypes.COORDINATOR)) {
		
			// if the updated Acocunt shall be a Coordinator account, then we have to provide a user directory 
			 
			try {
				
				accountsRepo.update(updatedAccount);
				

				if(updatedAccount.getAccountType().equals(AccountTypes.COORDINATOR)) {
				
					// path gets created in the constructor
					
					PathManager pathManager = new PathManager(noticeJSONFileHandler.getPathManager().toString(), updatedAccount.getAccountOwners().toString());
				}
				
			}
			catch(InternalError error) {
				
				 
				if(error instanceof TransactionError) {
					
					
					// if the account, shall be a coordinator account 
					
					if(updatedAccount.getAccountType().equals(AccountTypes.COORDINATOR)) {
						
						PathManager pathManager = new PathManager(noticeJSONFileHandler.getPathManager().toString(), updatedAccount.getAccountOwners().toString());
						
						pathManager.deletePath();
					}
				 
				}
				else {
					
					// other errors are not caught here
					
					throw error;
				}
			}
		}
		
		// if the current account IS a Coordinator account 
		else {
			
			// if the updated Acocunt shall NOT be a Coordinator account, then we have to delete the user directory 
			
			if(!updatedAccount.getAccountType().equals(AccountTypes.COORDINATOR)) {
							

				PathManager pathManager = new PathManager(noticeJSONFileHandler.getPathManager().toString(), updatedAccount.getAccountOwners().toString());
				
				pathManager.deletePath();
			}
			
			accountsRepo.update(updatedAccount);
		}
 
		
	}
    
	public void deleteAccount( Accounts accountToDelete) {
	
		try {
			
			accountsRepo.delete(accountToDelete);
	
			if(accountToDelete.getAccountType().equals(AccountTypes.COORDINATOR)) 
			{
				noticeJSONFileHandler.getPathManager().deletePath();
			}
		}
		catch(Exception accoutDeletionFailed) {
			
			// we will not further Action ... just logging @FixMe
		} 
	
	}


	public void changeEmailOfUser(Users user,  String NewUserEmail) {
		 
		// here we set the new User-Email 
		
		user.setUserEmail(NewUserEmail);
		
		// add the information back to the repository 
		
		usersRepo.update(user);
		 
	}

 
}
