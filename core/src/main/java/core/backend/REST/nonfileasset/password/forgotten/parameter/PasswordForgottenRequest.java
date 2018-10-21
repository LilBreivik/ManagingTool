package core.backend.REST.nonfileasset.password.forgotten.parameter;


import static com.google.common.collect.MoreCollectors.onlyElement;

import java.util.HashMap;
import java.util.Map;

import core.backend.REST.general.request.custom.nonschedule.RESTCustomNonScheduleRequest;
import resources.components.elements.POJO.Password.forgotten.PasswordForgottenPOJO;
import resources.components.utils.ComponentsManufactory;
import resources.database.entities.Accounts.Accounts;
import resources.database.entities.Accounts.Accounts.AccountTypes;
import resources.database.entities.User.Users;
import resources.database.repository.AccountsRepository;
import resources.database.repository.UsersRepository;
import resources.error.password.PasswordForgottenError;
import resources.error.password.PasswordResetError; 

public class PasswordForgottenRequest 

						 extends RESTCustomNonScheduleRequest<PasswordForgottenPOJO> {

	/**
	 * We define the parameter names, of the needed GET-Request 
	 * */ 
	
	// the email, associated with the certain user 
	public final static String USER_E_MAIL_PARAMETER = "USER_E_MAIL"; 
	
	// the accoun that shall be rested 
	public final static String USER_ACCOUNT_PARAMETER = "USER_ACCOUNT"; 
	// the account, that needs to be logged into 
	public final static String ACCOUNT_TYPE_PARAMETER = "ACCOUNT_TYPE"; 
	
 
	// the verified Parameters
	
	private Map<String, Object> verifiedParameters = new HashMap<String, Object>(){{
	
		put(USER_E_MAIL_PARAMETER, null);
		put(USER_ACCOUNT_PARAMETER, null);
	}};
	
	public PasswordForgottenRequest( PasswordForgottenPOJO  request ) {
		super(request ); 
	}
	
	/**
	 * Factory Method, that builds some 
	 * Request Object , from a GET-Request , for the backend API ... 
	 * 
	 * @param (Map<String, String>) passwordForgottenRequest, request that mapes the params from the client
	 * @return (PasswordForgottenRequest) , returnsPasswordForgottenRequest, if the params match the expected parameters , 
	 * 										else return null
	 * */
	
	public static  PasswordForgottenRequest buildPasswordForgottenRequest(Map<String, String> passwordForgottenRequest)
	{
		PasswordForgottenRequest buildedPasswordForgottenRequest = null; 
		
		try {
			
			String userEmail = passwordForgottenRequest.get(USER_E_MAIL_PARAMETER);
			 
			AccountTypes associatedUserAccount = AccountTypes.valueOf(passwordForgottenRequest.get(ACCOUNT_TYPE_PARAMETER));
			
			PasswordForgottenPOJO pojo = new PasswordForgottenPOJO();
			
			pojo.setUserEmail(userEmail);
			
			pojo.setAssociatedUserAccount(associatedUserAccount );
			
			buildedPasswordForgottenRequest = new PasswordForgottenRequest(pojo);	
		}

		catch(ClassCastException | IllegalArgumentException  | NullPointerException parametersAreWrong) {
			
			throw new PasswordResetError();
		} 

		return buildedPasswordForgottenRequest;
	}

	@Override
	public void verifyParameter() {
			
		try {
				
			UsersRepository userRepository = (UsersRepository) ComponentsManufactory.createComponent("usersRepository", UsersRepository.class);
			 
			AccountsRepository accountsRepository = (AccountsRepository) ComponentsManufactory.createComponent("accountsRepository", AccountsRepository.class);
			
			
			
			Users userBelongingToTheGivenMail =  userRepository.read().stream()
														.filter(user -> user.getUserEmail().equals(getRequest().getUserEmail()))
													.collect(onlyElement());
			
			Accounts accountThatNeedsTobeReseted =    accountsRepository.read(userBelongingToTheGivenMail ).stream()
													.filter(account -> account.getAccountType().equals(getRequest().getAssociatedUserAccount()))
												.collect(onlyElement());
						
			
			if(accountThatNeedsTobeReseted == null) {
				
				throw new IllegalArgumentException();
			}
			else {
				
				/* cause of the process pipeline, 
				*  we have to store the verified parameter 
				*  for a certain task 
				*/
				
				
				setVerifiedParameters(userBelongingToTheGivenMail, 
									accountThatNeedsTobeReseted);
				
			}
		}
		catch( Exception  parametersAreInvalid) {
			
			// we wont throw any spepcifc  exception back to the client, 
			// cause we do not want give information, if a certain e-mail, does 
			// exist in the user repository 
			
			// to be sure, any kind of exception is silenced, we will catch any of them 
			
			// debugging processess, needs to analyse the specific instance of  parametersAreInvalid
			
			throw new PasswordForgottenError();
		
		} 
	}

	public Map<String, Object> getVerifiedParameters() {
	
		return verifiedParameters;
	}

	public void setVerifiedParameters(Users userBelongingToTheGivenMail, Accounts accountThatNeedsTobeReseted) {
		
		verifiedParameters.put(USER_E_MAIL_PARAMETER, userBelongingToTheGivenMail);
		verifiedParameters.put(USER_ACCOUNT_PARAMETER, accountThatNeedsTobeReseted);
	}
}
