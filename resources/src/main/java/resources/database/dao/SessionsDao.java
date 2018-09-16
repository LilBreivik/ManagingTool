package resources.database.dao;
  
import org.springframework.stereotype.Component;

import resources.database.entities.Accounts.Accounts;
import resources.database.entities.Accounts.Sessions;

@Component
public class SessionsDao  extends GenericDaoImpl<Sessions, Integer>{

	
	public SessionsDao() {
		super(Sessions.class); 
	}

}
