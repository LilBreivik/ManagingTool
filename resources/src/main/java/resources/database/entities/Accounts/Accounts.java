package resources.database.entities.Accounts;
 
import javax.persistence.ForeignKey; 
import java.nio.charset.StandardCharsets; 

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table; 
import javax.validation.constraints.NotNull;
import javax.persistence.DiscriminatorType; 
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.DiscriminatorOptions;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;

import resources.database.entities.User.Users; 
/**
 * This class defines a prototype
 * of the account database.entities that will be used... 
 * */


@Entity 
@Table(name ="accounts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="accountType", discriminatorType = DiscriminatorType.STRING ,columnDefinition = "VARCHAR(50) default 'account'")
@DiscriminatorOptions(force = true)
 
public abstract class  Accounts {

	 private static  final int logRoundsForHashes = 4; 
	
	 public static enum AccountTypes implements GrantedAuthority{
		 
		 ADMIN{
			 
			 @Override
				public String getAuthority() { 
					return "ADMIN";
				} 
		 }, 
		
		 CONTRIBUTOR{
			 
			 @Override
				public String getAuthority() { 
					return "CONTRIBUTOR";
				} 
		 },  
		
		 COORDINATOR{
			 
			 @Override
				public String getAuthority() { 
					return "COORDINATOR";
				} 
		 }

		
	 }
	
	 @Id
	 @Column(name = "ACCOUNT_ID",  length = 11) 
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 protected int accountid;
	
	   
	 @ManyToOne(targetEntity = Users.class ) 
	 @Cascade({ CascadeType.REFRESH})
	 @OnDelete(action = OnDeleteAction.CASCADE)
	 @JoinColumn(name = "USER_EMAIL", foreignKey= @ForeignKey(name="USER_EMAIL_FK"))
	 protected Users acountOwner;

	 @NotNull
	 @Column(name = "ACCOUNT_TYPE" )
	 protected AccountTypes accountType; 
	 
	 @NotNull
	 @Column(name = "ACCOUNT_PASSWORD_HASH" , columnDefinition = "BINARY(60)", length = 60  )
	 protected byte[] accountPasswordHash; 
	   
	 
	 
	 public void createCredentials(String accountPassword) {

		 this.accountPasswordHash = createsPasswordForUserAccount(accountPassword, createSaltForUserAccount());
	 }

		
	 public static boolean validateCredentials(String passwordToValidate, byte[] passwordHashToValidate) {
			
    	 final String accountPasswordHash = new String(passwordHashToValidate, StandardCharsets.UTF_8 );
			
		 return BCrypt.checkpw(passwordToValidate, accountPasswordHash);	
	 }
		
		 
	 private byte[] createsPasswordForUserAccount(String passwordUnhashed, String salt) {
		 	
		return  BCrypt.hashpw(passwordUnhashed, salt).getBytes(StandardCharsets.UTF_8);
 	 }
 
	 private String createSaltForUserAccount() {
		 
		return  BCrypt.gensalt(logRoundsForHashes);
	 }
		
	 public byte[] getAccountPasswordHash() {
		 
		 return accountPasswordHash; 
	 }
	 
     public void setAccountId(int accountId) {
		 
		 accountid = accountId; 
	 }
	 
	 public int getAccountId() {
		 
		 return accountid; 
	 }
	 
     public void  setAccountOwners(Users user){
 		 
		 this.acountOwner = user; ;
	 }
	 
     
	 public Users getAccountOwners(){
		 
		 return acountOwner;
	 }
	  
	 public AccountTypes getAccountType() {
		 
		 return accountType;
	 }

 
	 
}