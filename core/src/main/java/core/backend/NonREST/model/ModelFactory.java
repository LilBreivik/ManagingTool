package core.backend.NonREST.model;

import org.springframework.ui.Model;

import core.backend.NonREST.model.configuration.IPageView;
 

public class ModelFactory {

	public static Model createModel(Model model, IPageView view) {
		
		return view.buildRequiredView(model); 
	}
}
