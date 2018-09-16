package resources.database.entities.factory.account;
 
import resources.database.entities.Accounts.Accounts;
import resources.database.entities.User.Users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
  
@Entity
@Table(name = "accounts" )
@DiscriminatorValue( value ="CONTRIBUTOR")
public class ContributorAccount extends Accounts {
 
	public ContributorAccount() {
		
	}
	
    public ContributorAccount(Users accountOwner , String accountPassword) {
		
		this.acountOwner = accountOwner;
			
		this.accountType = AccountTypes.CONTRIBUTOR;
		
		this.createCredentials(accountPassword);
	} 
}
