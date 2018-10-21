package resources.database.repository;
 
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Component; 
import resources.database.dao.AccountsDao;
import resources.database.entities.Accounts.Accounts; 
import resources.database.entities.User.Users; 

@Component
public class AccountsRepository 
 									extends MasterRepository<AccountsDao, Accounts>{
  
	@Autowired
	public  AccountsRepository( AccountsDao  dao) {
		super( dao);
	}
	 
	public List<Accounts> read(Users user ) {
		
		return super.read().stream().filter( acc -> acc.getAccountOwners().equals(user)).collect(Collectors.toList());
	}
	   
}
