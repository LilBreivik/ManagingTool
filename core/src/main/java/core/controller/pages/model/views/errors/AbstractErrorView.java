package core.controller.pages.model.views.errors;

import org.springframework.ui.Model;

import core.controller.pages.model.configuration.IPageView;

public abstract class AbstractErrorView implements IPageView {

    protected final String ERRORCODE_ATTRIBUTE = "ERRORCODE";
	
    protected String ERRORCODE_VALUE; 
    
    
    @Override
	public Model buildRequiredView(Model model) {
		
		model.addAttribute(ERRORCODE_ATTRIBUTE, ERRORCODE_VALUE);
		
		return model;
	}
}
