package core.backend.NonREST.model.views.password;

import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import core.backend.NonREST.model.IndexPageView;
import resources.database.entities.Accounts.Accounts.AccountTypes; 
import resources.utils.general.GeneralPurpose;

@Component 
public class PasswordForgottenView 		
									extends IndexPageView{

	private final String ACCOUNT_TYPES = "accounts";
	 
	private final String TITLE = "Passwort vergessen"; 
	 
	@Override
	public Model buildRequiredView(Model model) {
		 

		model.addAttribute(ACCOUNT_TYPES,
				GeneralPurpose.ArrayToList(AccountTypes.values()).stream().map(type -> type.toString()).collect(Collectors.toList()));
		
		model.addAttribute(TITLE_ATTRIBUTE, 
				TITLE); 
		
		return model;
	}

}
