package core.Test.controller.notice.utils;

import org.springframework.beans.factory.annotation.Value;

import core.TestContext.utils.UserSecurityContextFactory;

public class NoticeTestUserSecurity 
										extends UserSecurityContextFactory {
 
	@Value("${test.user.emai}")
	private static String  TEST_USER_EMAIL; 
	
	@Value("${test.user.name}")
	private static String  TEST_USER_NAME;
	
	@Value("${test.user.password}")
	private static String  TEST_USER_PASSWORD;
 
	
	public NoticeTestUserSecurity() {
		
		p_userEmail = TEST_USER_EMAIL; 
		
		p_userName = TEST_USER_NAME; 
		
		p_userPassword  = TEST_USER_PASSWORD;  
		
	}
}
