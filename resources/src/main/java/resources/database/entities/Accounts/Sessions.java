package resources.database.entities.Accounts;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType; 

@Entity 
@Table(name ="sessions")
public class Sessions implements  Serializable{
  
	 /**
	 * 
	 */
	private static final long serialVersionUID = 831743984243131508L;
 
	/**
	 * We cannot combine 
	 * a key that works as primary and foriegn key at the same time !!! 
	 * */
	 @Id
	 @Column(name = "SESSION_ID",  length = 11) 
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private  int sessionid;
	
	 @ManyToOne(targetEntity = Accounts.class )  
     @JoinColumn(name = "SESSION_ACCOUNT_ID"  ,   foreignKey= @ForeignKey(name="ACCOUNT_SESSSION_ID_FK"), referencedColumnName = "ACCOUNT_ID" )
	 protected Accounts  account;
	
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
	 
	 public Sessions( Accounts  loggedInAccountId) {
			
		 this.account = loggedInAccountId; 
	 }
	 
	 public  Date getLastLogin() {
		 
		 return lastloggedInAt;
	 }
	  
	public void handleLogin() {
		 
		 this.loggedOutAt = null;
		 this.loggedInAt =  new Date(System.currentTimeMillis());
	 }
	 
    public void handleLogout() {
		 
     	 this.lastloggedInAt = loggedInAt; 
    	 this.loggedInAt  = null;
		 this.loggedOutAt =  new Date(System.currentTimeMillis());
	 }

	public Date getLoggedOutAt() {
		return loggedOutAt;
	}
 
	public Date getLastloggedInAt() {
		return lastloggedInAt;
	}

 
	public Date getLoggedInAt() {
		return loggedInAt;
	}
 
    public void setAccount( Accounts  accountid) {
		this.account = accountid;
	}

	public  Accounts  getAccount() {
		return account;
	}
	
	public int getSessionid() {
		return sessionid;
	}


	public void setSessionid(int sessionid) {
		this.sessionid = sessionid;
	}

 
}
