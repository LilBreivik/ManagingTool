package resources.database.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component; 
import resources.database.dao.AccountsDao;
import resources.database.entities.Accounts.Accounts; 

@Component
public class AccountsRepository extends MasterRepository<AccountsDao, Accounts>{

	@Autowired
	public  AccountsRepository( AccountsDao  dao) {
		super( dao);
	}

	 
}
