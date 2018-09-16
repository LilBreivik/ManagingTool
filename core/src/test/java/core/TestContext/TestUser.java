package core.TestContext;


public class TestUser{
	
	private String userName; 
	
	private String userPassword; 
	
	
	public TestUser(String userName, String userPassword) {
		
		setUserName(userName);
		setUserPassword(userPassword);
	}
	
	public void setUserName(String userName) {
		
		this.userName = userName; 
	}
	
	public String getUserName() {
		
		return this.userName; 
	}
	
	public void setUserPassword(String userPassword) {
		
		this.userPassword = userPassword; 
	}
	
	public String getUserPassword() {
		
		return this.userPassword;
	}
	
}