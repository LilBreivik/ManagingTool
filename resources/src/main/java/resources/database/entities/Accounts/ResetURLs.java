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
import javax.validation.constraints.NotNull;
 

@Entity 
@Table(name ="reset_urls")
public class ResetURLs implements  Serializable{
	  
	/**
	 * 
	 */
	private static final long serialVersionUID = 831743984243131508L;
	 
	public final static int URL_VALUE_LENGTH = 128; 
	
	

	
	/**
	 * We cannot combine 
	 * a key that works as primary and foriegn key at the same time !!! 
	 * */ 
	
	 @Id
	 @Column(name = "RESET_URL_ID",  length = 11) 
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private int reset_urlid;
		
		
	 @ManyToOne(targetEntity = Accounts.class )  
	 @JoinColumn(name = "RESET_URL_ACCOUNT_ID", 
	 				foreignKey= @ForeignKey(name="RESET_URL_ACCOUNT_ID_FK"), 
	 					referencedColumnName = "ACCOUNT_ID" )
	 private  Accounts  account;
		 
	 @Column(name = "URL_VALUE",  columnDefinition = "VARCHAR(" + URL_VALUE_LENGTH + ") UNIQUE" ) // URL_VALUE_LENGTH )
	 @NotNull
	 private  String urlvalue;
	 
	 
	 @Column(name = "EXPIRE_AT")
	 @NotNull
	 @Temporal(TemporalType.TIMESTAMP)
	 private Date expireAt;
	 
 
	 public String getUrlvalue() {
	
		 return urlvalue;
	 }
 
	 public void setUrlvalue(String urlvalue) {
	
		 this.urlvalue = urlvalue;
	 }

	public Date getExpireAt() {
		return expireAt;
	}

	public void setExpireAt(Date expireAt) {
		this.expireAt = expireAt;
	}

	public Accounts getAccount() {
		return account;
	}

	public void setAccount(Accounts account) {
		this.account = account;
	}

	public int getResetURLid() {
		return reset_urlid;
	}

	public void setResetURLid(int reset_urlid) {
		this.reset_urlid = reset_urlid;
	}

	 
}