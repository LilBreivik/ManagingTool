package resources.database.entities.factory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import resources.database.entities.Accounts.Accounts;
import resources.database.entities.Accounts.Accounts.AccountTypes;
import resources.database.entities.User.Users; 
import resources.database.repository.AccountsRepository;
import resources.database.repository.UsersRepository;
import resources.error.InternalError; 

@Component
public class UserAccountsManager {
	
	@Autowired 
	private  UsersRepository usersRepo;
	  
	@Autowired 
    private  AccountsRepository accountsRepo;
	    
	
	public static Users createUser(String userEmail, String userName) {
		
		 Users user = new Users(); 
		 
		 user.createLoginName(userName);

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
			  
		    	usersRepo.delete(user.getUserEmail());
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
  	   
  		  
  		List<Accounts> accountsBelongToUser = accountsRepo.read().stream()
  							.filter(account -> account.getAccountOwners().equals(user)  && account.getAccountType().equals(accountType))
  									.collect(Collectors.toList());
  		  
  	
  		for(Accounts acc : accountsBelongToUser) {
  			
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
			  
			  else {
				  
				  throw new InternalError("Internal Server Error");
			  }
		  } 
  }
	
    
    public void createAccount(Users user, Accounts account) {
    	
    	  try {
    	    	account.setAccountOwners(user);
    	    	accountsRepo.create(account);
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
    
    
    public void updateAccount(Users user, Accounts account) {
    	
      try {
    	
    	  accountsRepo.read().stream().forEach(acc -> {
 			 
  			if(acc.getAccountType().equals(acc.getAccountType())  && acc.getAccountOwners().equals(user)) {
  				 
  				account.setAccountId(acc.getAccountId());
  				
  				accountsRepo.update(account);
  			}
  			
  		});
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
    
	public void deleteAccount(Users user, AccountTypes accounttype) {
	
		try {
			
			accountsRepo.read().stream().forEach(account -> {
				 
				if(account.getAccountType().equals(accounttype) && account.getAccountOwners().equals(user)) {
					
					accountsRepo.delete(account.getAccountId());
				}
				
			});
			
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


	public void changeEmailUserAccount(String OldUserEmail, String NewUserEmail) {
		 
		try {
			
			List<Accounts> accounts = accountsRepo.read().stream()
					.filter(account -> account.getAccountOwners().getUserEmail().equals(OldUserEmail))
				.collect(Collectors.toList());

			
			Users user = accounts.get(0).getAccountOwners();
			
			final String currentLogin = user.getLoginName();
			
			user.setUserEmail(NewUserEmail);
			
			user.createLoginName("TEMPNAME");
			
			usersRepo.create(user);
			
			accounts.stream().forEach(account -> {
				
				account.setAccountOwners(user);
				 
				accountsRepo.update(account);	
			});
			
			 
			// remove the nnow non existing email 
			
			usersRepo.delete(OldUserEmail);
			
            user.setLoginName(currentLogin);
			
			usersRepo.update(user);
			
			 
		}
		catch(IndexOutOfBoundsException noUserWithThatEmail) {
			
			throw new InternalError("No User with that E-Mail");
		}
		 
		 
	}
}
