package resources.database.dao;
  
import org.springframework.stereotype.Component;

import resources.database.entities.Accounts.Accounts;

@Component
public class AccountsDao  extends GenericDaoImpl<Accounts, Integer>{

	
	public AccountsDao() {
		super(Accounts.class); 
	}

}
