package resources.components.elements.POJO.Password.change;

public class PasswordChangePOJO {

	// password that is stored in the repository 
	private String existingPassword; 
	
	private String newPassword; 
	
	private String newPasswordRepeated;

 

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordRepeated() {
		return newPasswordRepeated;
	}

	public void setNewPasswordRepeated(String newPasswordRepeated) {
		this.newPasswordRepeated = newPasswordRepeated;
	}

	public String getExistingPassword() {
		return existingPassword;
	}

	public void setExistingPassword(String existingPassword) {
		this.existingPassword = existingPassword;
	} 
	
}
 