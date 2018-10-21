package core.backend.REST.nonfileasset.password.general.task;

import core.backend.REST.general.request.MasterRESTRequest;
import core.backend.REST.general.task.general.GeneralAbstractTaskImpl;
import resources.database.entities.Accounts.Accounts;
import resources.database.entities.factory.UserAccountsManager;


/**
 * @FIXME: think about a way to 
 * let this class inheit from another one, that updates accounts
 * */

public abstract class AbstractPasswordTask< ResponseType> 
								extends GeneralAbstractTaskImpl<MasterRESTRequest, ResponseType>{
 
	private UserAccountsManager m_UserAccountsManager;
	
	
	public  AbstractPasswordTask(UserAccountsManager userAccountsManager) {
         m_UserAccountsManager = userAccountsManager; 
	}
	
	
	// general method to handle 
	// the needed password change in the repository

	public void workOnTaskTmp(passwordchange param){
	
		// we have to catch certain exceptions at this point
		// to precise the final error handling 
		
		try {
			
			m_UserAccountsManager.updateAccount(param.getAccountThatGetsChanged());
		}
		catch(Exception acountUpdateError) {
			
			// throw a specific error 
		}
		
	}
	
	
	
	class passwordchange {
		 

		private Accounts accountThatGetsChanged;

		public Accounts getAccountThatGetsChanged() {
			return accountThatGetsChanged;
		}

		public void setAccountThatGetsChanged(Accounts accountThatGetsChanged) {
			this.accountThatGetsChanged = accountThatGetsChanged;
		} 
		
	}
	
	
}
