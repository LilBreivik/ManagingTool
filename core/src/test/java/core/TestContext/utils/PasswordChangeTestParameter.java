package core.TestContext.utils;

import resources.components.elements.POJO.Password.change.PasswordChangePOJO;

public class PasswordChangeTestParameter {

	private PasswordChangePOJO passwordChange;

	public PasswordChangeTestParameter(PasswordChangePOJO passwordChangePojo) {
		this.passwordChange = passwordChangePojo;
	}

	public PasswordChangePOJO getPasswordChange() {
		return passwordChange;
	}

	 
}
 