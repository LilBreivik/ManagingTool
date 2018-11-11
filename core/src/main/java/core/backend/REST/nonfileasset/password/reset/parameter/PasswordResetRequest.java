package core.backend.REST.nonfileasset.password.reset.parameter;

import static com.google.common.collect.MoreCollectors.onlyElement;

import java.util.Date;

import core.backend.REST.general.request.RESTRequest; 
import resources.components.elements.POJO.Password.reset.PasswordResetPOJO;
import resources.components.utils.ComponentsManufactory;
import resources.database.entities.Accounts.ResetURLs;
import resources.database.repository.ResetUrlsRepository; 
import resources.error.password.PasswordResetError; 

public class PasswordResetRequest 
 
							extends RESTRequest<PasswordResetPOJO> {

	private ResetURLs m_resetURL;
	
	
	public PasswordResetRequest(PasswordResetPOJO request) {
		super(request); 
	}

	public static PasswordResetRequest buildPasswordResetRequest(String resetURLValue) {
		
		PasswordResetPOJO pojo = new 	PasswordResetPOJO ();
		
		pojo.setResetURLValue(resetURLValue);
		
		return new PasswordResetRequest(pojo);
	}
 
	@Override
	public void verifyParameter() 
	{	  
		try {
		
			// At first we check, th the URL Value is correct 
			
			ResetUrlsRepository resetUrlRepository = (ResetUrlsRepository) ComponentsManufactory.createComponent("resetUrlsRepository", ResetUrlsRepository.class);
			
			ResetURLs resetURL = resetUrlRepository.read().stream()
												.filter(url -> url.getUrlvalue().equals(getRequest().getResetURLValue()))
											.collect(onlyElement());
			
			// Second, we check if the url is expired 
			
			Date verifyingTimePoint = new Date(System.currentTimeMillis());
			
			if(resetURL.getExpireAt().before(verifyingTimePoint)) {
				
				throw new IllegalArgumentException("URL is expired");
			}
			
			 
			setVerifiedResetURL(resetURL);
			
		}
        catch( Exception  parametersAreInvalid) {
			
			// we wont throw any spepcifc  exception back to the client, 
			// cause we do not want give information, if a certain e-mail, does 
			// exist in the user repository 
			
			// to be sure, any kind of exception is silenced, we will catch any of them 
			
			// debugging processess, needs to analyse the specific instance of  parametersAreInvalid
			
			throw new PasswordResetError();
		
		} 
	}

	public ResetURLs getVerifiedResetURL() {
		return m_resetURL;
	}

	public void setVerifiedResetURL(ResetURLs m_resetURL) {
		this.m_resetURL = m_resetURL;
	}

}
