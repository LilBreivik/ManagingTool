package resources.database.entities.Accounts;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.Id; 
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType; 

@Entity 
@Table(name ="sessions")
public class Sessions {
 
	 @Id
	 @Column(name = "ACCOUNT_SESSION_ID")
	 protected int accountid;
	
	 @Column(name = "LOGGED_IN_AT")
	 @Temporal(TemporalType.TIMESTAMP)
	 protected  Date loggedInAt;
	 
	 @Column(name = "LAST_TIME_LOGGED_IN_AT")
	 @Temporal(TemporalType.TIMESTAMP)
	 protected  Date lastloggedInAt;
	 
	 
	 @Column(name = "LOGGED_OUT_AT")
	 @Temporal(TemporalType.TIMESTAMP)
	 protected  Date loggedOutAt;
	 
	 
	 public Sessions() {}
	 
	 public  Date getLastLogin() {
		 
		 return lastloggedInAt;
	 }
	 
	 public Sessions(int loggedInAccountId) {
	
		 accountid = loggedInAccountId; 
	 }

	public void handleLogin() {
		 
		 loggedOutAt = null;
		 loggedInAt =  new Date(System.currentTimeMillis());
	 }
	 
     public void handleLogout() {
		 
    	 lastloggedInAt = loggedInAt; 
    	 loggedInAt  = null;
		 loggedOutAt =  new Date(System.currentTimeMillis());
	 }
	
}
