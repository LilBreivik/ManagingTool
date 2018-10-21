package resources.database.dao;
   

import java.io.Serializable;

import org.springframework.stereotype.Component; 
import resources.database.entities.Accounts.Accounts;
import resources.database.entities.Accounts.Accounts.AccountTypes;
import resources.error.InternalError;
import resources.error.TransactionError;

@Component
public class AccountsDao  extends GenericDaoImpl<Accounts, Integer>{

	
	public AccountsDao() {
		super(Accounts.class);  
	}
	 
}
