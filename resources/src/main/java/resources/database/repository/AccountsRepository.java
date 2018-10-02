package resources.database.repository;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import resources.components.filehandler.FileHandler;
import resources.configuration.NoticesJSONFileHandlerProvider;
import resources.database.dao.AccountsDao;
import resources.database.entities.Accounts.Accounts;
import resources.database.entities.Accounts.Accounts.AccountTypes;
import resources.database.entities.User.Users;
import resources.error.InternalError;
import resources.error.TransactionError;

@Component
public class AccountsRepository extends MasterRepository<AccountsDao, Accounts>{
 
	@Autowired
	public  AccountsRepository( AccountsDao  dao) {
		super( dao);
	}
	
	 
	public List<Accounts> read(Users user ) {
		
		return super.read().stream().filter( acc -> acc.getAccountOwners().equals(user)).collect(Collectors.toList());
	}
	
	
	@Override
	public void delete(Serializable PK) {
		

		Accounts accountToDelete = super.read(PK);
		
		FileHandler fileHandler = null; 
		
		if(accountToDelete.getAccountType().equals(AccountTypes.COORDINATOR)) 
		{
			fileHandler = (FileHandler) NoticesJSONFileHandlerProvider.provideNoticesJSONFileHandlerForCoordinatorAccountCreation(accountToDelete.getAccountOwners());

		}
		
		super.delete(PK);
	 
		fileHandler.getFileAssetsManager().getPathManager().deletePath();
	}
	
	@Override
	public  void create(Accounts newCreatedAccount) {
	
		Accounts tempAccount = null; 
		
		FileHandler fileHandler = null; 
		
		if(newCreatedAccount.getAccountType().equals(AccountTypes.COORDINATOR)) {
			
			fileHandler = (FileHandler) NoticesJSONFileHandlerProvider.provideNoticesJSONFileHandlerForCoordinatorAccountCreation(newCreatedAccount.getAccountOwners());
		}
		
		try {
			
			 
			super.create(newCreatedAccount);
			
			tempAccount =  null ;
		}
		catch(InternalError error) {
			
			// if the account could not be created we have to delete the user directory j
			if(error instanceof TransactionError) {
				
				
				// if the account, shall be a coordinator account 
				
				if(newCreatedAccount.getAccountType().equals(AccountTypes.COORDINATOR)) {
					
					fileHandler.getFileAssetsManager().getPathManager().deletePath();
				}
			 
			}
			else {
				
				// other errors are not caught here
				
				throw error;
			}
		}
		 
		 
	}
	
	
	@Override
	public void update(Accounts  obj) {

		final Accounts currentAccount = super.read(obj.getAccountId());
		
		final Accounts updatedAccount = obj; 
		
		FileHandler fileHandler = null; 
		
		// if the current account is NOT a Coordinator account 
		
		if(!currentAccount.getAccountType().equals(AccountTypes.COORDINATOR)) {
		
			// if the updated Acocunt shall be a Coordinator account, then we have to provide a user directory 
			
			if(updatedAccount.getAccountType().equals(AccountTypes.COORDINATOR)) {
				
				fileHandler = (FileHandler) NoticesJSONFileHandlerProvider.provideNoticesJSONFileHandlerForCoordinatorAccountCreation(updatedAccount.getAccountOwners());
			}
			
			
			try {
				
				super.update(updatedAccount);
				
			}
			catch(InternalError error) {
				
				 
				if(error instanceof TransactionError) {
					
					
					// if the account, shall be a coordinator account 
					
					if(updatedAccount.getAccountType().equals(AccountTypes.COORDINATOR)) {
						
						fileHandler.getFileAssetsManager().getPathManager().deletePath();
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
							
				fileHandler = (FileHandler) NoticesJSONFileHandlerProvider.provideNoticesJSONFileHandlerForCoordinatorAccountCreation(updatedAccount.getAccountOwners());
			
				fileHandler.getFileAssetsManager().getPathManager().deletePath();
			}
			
			super.update(updatedAccount);
		}
 
	} 
}
