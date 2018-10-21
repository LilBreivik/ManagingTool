package core.backend.REST.nonfileasset.password.change.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.general.task.response.ResponseAbstractTaskImpl;
import core.backend.REST.nonfileasset.password.change.parameter.PasswordChangeParameter;
import core.backend.REST.nonfileasset.password.change.parameter.response.PasswordChangeResponsePOJO;
import core.backend.REST.nonfileasset.password.change.parameter.response.PasswordChangeSuccessfullResponsePOJO;
import resources.database.entities.Accounts.Accounts;
import resources.database.entities.factory.UserAccountsManager;
import resources.utils.user.AuthorizedUserAccount;

@Component
public class PasswordChangeTask 
								extends  ResponseAbstractTaskImpl < PasswordChangeParameter, PasswordChangeResponsePOJO>{
 
	@Autowired 
	private UserAccountsManager m_UserAccountsManager;
	
	@Override
	public void workOnTask(PasswordChangeParameter param) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		  
		AuthorizedUserAccount authorizedAccount = (AuthorizedUserAccount) authentication.getPrincipal();
		  
		Accounts updatedAccount =  authorizedAccount.getAccount();
		
		updatedAccount.createCredentials(param.getChangedPassword());
		
		m_UserAccountsManager.updateAccount(updatedAccount);
		 
	}
	
	@Override
	public SuccessResponse<PasswordChangeResponsePOJO> getResultsFromTask() {
	 
		return new SuccessResponse<PasswordChangeResponsePOJO>(new PasswordChangeSuccessfullResponsePOJO());
	}
}
