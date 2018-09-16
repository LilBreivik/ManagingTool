package resources.database.entities.factory.account;

import resources.database.entities.Accounts.Accounts;
import resources.database.entities.User.Users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table; 
 
@Entity
@Table(name = "accounts" )
@DiscriminatorValue( value ="COORDINATOR")
public  class CoordinatorAccount extends Accounts{

    public CoordinatorAccount() {
		
	}
	
    public CoordinatorAccount(Users accountOwner , String accountPassword) {
		
		this.acountOwner = accountOwner;
			
		this.accountType = AccountTypes.COORDINATOR;
		
		this.createCredentials(accountPassword);
	} 
}
