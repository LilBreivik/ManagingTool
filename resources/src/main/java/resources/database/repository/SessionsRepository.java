package resources.database.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component; 
import resources.database.dao.AccountsDao;
import resources.database.dao.SessionsDao;
import resources.database.entities.Accounts.Accounts;
import resources.database.entities.Accounts.Sessions; 

@Component
public class SessionsRepository extends MasterRepository<SessionsDao, Sessions>{

	@Autowired
	public  SessionsRepository( SessionsDao  dao) {
		super( dao);
	}

	public void registerSession(Sessions newSessionToLogIn) {
		
		m_dao.saveOrUpdate(newSessionToLogIn);
	} 
	
	public void unRegisterSession(Sessions sessionToLogOut) {
		
		m_dao.saveOrUpdate(sessionToLogOut);
	}

	public  Sessions createSession(int accountId) {
		
		Sessions session = m_dao.read(accountId);
		
		// if the first login happens, we have to create a new one 
		
		if(session == null) {
			
			return new Sessions(accountId);
		}
		else {
			
			return session; 
		}
		
	}
}
