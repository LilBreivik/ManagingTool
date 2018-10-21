package core.backend.NonREST.model;

import org.springframework.ui.Model;

public abstract class IndexPageView {

	protected final String HEADLINE_DISPLAY_ATTRIBUTE = "DISPLAY_HEADLINE";
	
	protected final String FOOTER_DISPLAY_ATTRIBUTE = "DISPLAY_FOOTER";

	protected final String CONTENT_DISPLAY_ATTRIBUTE = "DISPLAY_CONTENT";
	
	protected final String TITLE_ATTRIBUTE = "TITLE";
	
	protected final String HEADLINE_ATTRIBUTE = "HEADLINE";
	
	protected final String CONTENT_ATTRIBUTE = "CONTENT";
	
	protected final String FOOTER_ATTRIBUTE = "FOOTER";
	
		 
	protected boolean HEADLINE_DISPLAY;
	
	protected String HEADLINE; 
	
	protected boolean CONTENT_DISPLAY; 
	
	protected String TEMPLATE_REPLACEMENT_EXPRESSION;
	
	protected boolean FOOTER_DISPLAY; 
	
	
	public  Model buildRequiredView(Model model) {
		
		model.addAttribute(HEADLINE_DISPLAY_ATTRIBUTE, HEADLINE_DISPLAY);
		model.addAttribute(HEADLINE_ATTRIBUTE, HEADLINE);
		
		model.addAttribute(CONTENT_DISPLAY_ATTRIBUTE, CONTENT_DISPLAY);
		model.addAttribute(CONTENT_ATTRIBUTE , TEMPLATE_REPLACEMENT_EXPRESSION);
		
		model.addAttribute(FOOTER_DISPLAY_ATTRIBUTE, FOOTER_DISPLAY);
		
		return model;
	}
 
 
}
