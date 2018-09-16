package core.controller.pages.model;

import org.springframework.ui.Model;

import core.controller.pages.model.configuration.IPageView;

public class ModelFactory {

	public static Model createModel(Model model, IPageView view) {
		
		return view.buildRequiredView(model); 
	}
}
