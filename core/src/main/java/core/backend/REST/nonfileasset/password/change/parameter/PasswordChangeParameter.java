package core.backend.REST.nonfileasset.password.change.parameter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder; 
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty; 
import core.backend.REST.general.request.RESTRequest;  
import resources.components.elements.POJO.Password.change.PasswordChangePOJO;
import resources.database.entities.Accounts.Accounts;
import resources.error.parameter.ParameterViolationError;
import resources.utils.user.AuthorizedUserAccount; 

public class PasswordChangeParameter 

						extends RESTRequest<PasswordChangePOJO>{
 
	private String m_changedPassword; 
	
	@JsonCreator
	public PasswordChangeParameter(@JsonProperty("passwordChange") PasswordChangePOJO request) {
		super(request); 
		 
	} 

	@Override
	public void verifyParameter() {
	  
		// password from the request, that shall be stored
		
		final String existingPassword = getRequest().getExistingPassword();
		
		final String newPassword  = getRequest().getNewPassword();
		
		final String newRepeatedPassword  = getRequest().getNewPasswordRepeated();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		
		AuthorizedUserAccount authorizedAccount =  (AuthorizedUserAccount)  authentication.getPrincipal();
		
	 	
		if(Accounts.validateCredentials(existingPassword , authorizedAccount.getAccount().getAccountPasswordHash())) {
		
			if(newPassword.equals(newRepeatedPassword)) {
				
				 m_changedPassword = newPassword;
			}
			else {
				 
				throw new ParameterViolationError("Das neue Passwort, spricht nicht mit dem wiederholten überein");
			}
		}
		else {
			
			throw new ParameterViolationError("Das angegebene, persistent gespeicherte Passwort , stimmt nicht mit dem gespeicherten überein");
		}
	}

	public String getChangedPassword() {
		return m_changedPassword;
	}
}
