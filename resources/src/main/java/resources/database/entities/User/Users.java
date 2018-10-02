package resources.database.entities.User;

 
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.Id; 
import javax.persistence.Table; 
import javax.validation.constraints.NotNull; 

/**
 * Entitiy Class for handeling the 
 * users table in the database 
 * 
 * 
 * There will be a ONE User MANY Accounts Relationship
 * 
 * uniqueConstraints={@UniqueConstraint(columnNames={"USER_EMAIL"})}
 * */


@Entity
@Table(name = "users" ) 
public class Users {
  
	@Id
	@NotNull
	@Column(name = "USER_EMAIL" , length = 50)
	private String userEmail;
	 	
	@NotNull
	@Column(name = "USER_NAME" , length = 50)
	private String userName;
  
	@NotNull
	@Column(name = "LOGIN_NAME" , columnDefinition = "VARCHAR(50) UNIQUE " )
	private String loginName;
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof Users) {
			
			Users user = (Users) obj; 
			
			return user.getUserEmail().equals(userEmail);
			
		}
		else {
			
			return false; 
		}
		 
	}
 
	public void createLoginName(String userName) {
		
		// here we create a l33t nane 
		
		
		String userNameUpperCase = userName.toUpperCase();
		 
		Random rand = new Random();
		
		this.loginName = userNameUpperCase.toString().concat(rand.nextInt(100) + "");
		
	}
	
	public void setLoginName(String loginName) {
		
		this.loginName = loginName;
	}

	public String  getLoginName() {
		
		return this.loginName;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	  
	  
}
