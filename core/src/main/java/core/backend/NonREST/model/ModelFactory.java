package core.backend.NonREST.model;

import org.springframework.ui.Model;
 

public class ModelFactory {

	public static Model createModel(Model model, IndexPageView view) {
		
		return view.buildRequiredView(model); 
	}
}
