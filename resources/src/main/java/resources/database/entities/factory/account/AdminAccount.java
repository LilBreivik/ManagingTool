package resources.database.entities.factory.account;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import resources.database.entities.Accounts.Accounts;
import resources.database.entities.Accounts.Accounts.AccountTypes;
import resources.database.entities.User.Users;

 
@Entity
@Table(name = "accounts" )
@DiscriminatorValue( value ="ADMIN")
public class AdminAccount extends Accounts {
 
	public  AdminAccount() {
		
	}
	
    public  AdminAccount(Users accountOwner , String accountPassword) {
		
		this.acountOwner = accountOwner;
			
		this.accountType = AccountTypes.ADMIN;
		
		this.createCredentials(accountPassword);
		
	} 
}
