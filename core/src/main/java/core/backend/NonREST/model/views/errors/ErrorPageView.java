package core.backend.NonREST.model.views.errors;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class ErrorPageView extends AbstractErrorView {
	
	public  Model  buildErrorPageModel(int errorCode, Model model) {
		
		ERRORCODE_VALUE = String.valueOf(errorCode);
		
		return  buildRequiredView(model);
	}
	
}
