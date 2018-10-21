package resources.database.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;  
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
		
		newSessionToLogIn.handleLogin();
		
		m_dao.saveOrUpdate(newSessionToLogIn);
	} 
	
	public void unRegisterSession(Sessions sessionToLogOut) {
		
		sessionToLogOut.handleLogout();
		
		m_dao.saveOrUpdate(sessionToLogOut);
	}

	
	/**
	 * Mehtod that creates a 
	 * Session Object, to add in the repository 
	 * 
	 * 
	 * @param (int) accountId, id of the account that builds up a session 
	 * @return (Sessions) created Session Object 
	 * */
	
	
	public  Sessions createSession(Accounts  account) {
		
		// ask the repository, for a sessions, with the 
		// certain account id 
		
		List<Sessions> sessionsBelongingToAccount  = m_dao.read().stream().
						filter(acc -> acc.getAccount().getAccountId() == account.getAccountId()).collect(Collectors.toList());
		
		
		// if there is no session stored
		// we have to create one 
		
		if(sessionsBelongingToAccount.size() == 0) {
			
			return new Sessions(account);
		}
		else {
			
			// there shall be just one session
			// per account , it the API does some "strange" things, 
			// just the first session will gets updated 
			
			return sessionsBelongingToAccount.get(0); 
		}
		
	}

	
	/*
	 * Method to get session
	 * by its foreqign key related account 
	 * 
	 * @param (Accounts), account, that is used to identify a certain session (account is the foreign key, that shall be unique)
	 * 
	 * @return (Sessions), session that belongs ot a cetain accounts, if the user was 
	 * 						not authenticated with a certain account the mehod will return null 
	 */
	
	public Sessions read(Accounts account) {
		 
		// if the user was ever 
		// authenticated with q certain account 
		
		try {
		// then there  can only be one session per account 
		
		return super.read().stream().filter(acc -> acc.getAccount().getAccountId() == account.getAccountId()).collect(Collectors.toList()).get(0);
	
		
		}
		catch(IndexOutOfBoundsException notAuthenticatedError) {
			
			return null; 
		}
		
		
	}
}
