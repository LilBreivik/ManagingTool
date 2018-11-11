package core.backend.NonREST.page.frontend;

import java.util.Collection; 
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping; 
import core.backend.NonREST.model.views.DashboardView;
import core.backend.NonREST.page.general.GeneralIndexPageController;    

@Controller
public class DashboardPageController 
									extends GeneralIndexPageController<DashboardView >{
   
	public static final String  dashboardPageURL =  "/ManagingTool/Dashboard"; 
	
	@Override
	@GetMapping(dashboardPageURL)
	public String serveTemplate(Model template) {
	 	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 
		Collection< GrantedAuthority> authorities =  (Collection<GrantedAuthority>) authentication.getAuthorities();
		
	    p_view.buildRequiredView(template);
		
		return indexPageTemplate;
	}	
	 
	
}