package resources.database.entities.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * 
 * @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected int userid;
 * */


@Entity
@Table(name = "users" ) 
public class Users {
  

	@Id
	@Column(name = "USER_ID",  length = 11) 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private  int userId;
	
	@NotNull 
	@Column(name = "USER_EMAIL" , columnDefinition = "VARCHAR(50) UNIQUE ")
	private String userEmail;
	 	
	@NotNull
	@Column(name = "USER_NAME" , length = 50)
	private String userName; 
	
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
 	
	public void setUserId(int userId) {
		
		this.userId = userId;
	}

	public int  getUserId() {
		
		return this.userId;
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
