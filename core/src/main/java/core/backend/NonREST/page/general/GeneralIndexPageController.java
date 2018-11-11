package core.backend.NonREST.page.general;

import org.springframework.stereotype.Component;

import core.backend.NonREST.model.IndexPageView; 

@Component 
public abstract class GeneralIndexPageController<view extends IndexPageView> 

											extends IndexPageController< view >{
 
	public static final String  indexPageTemplate =  "index"; 
	
	public GeneralIndexPageController() {
		
		super(indexPageTemplate);
	}
	  
}
