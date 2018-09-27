package core.backend.NonREST;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import core.backend.NonREST.model.ModelFactory;
import core.backend.NonREST.model.views.DashboardView;   

@Controller
public class IndexPageController  extends AbstractPageController{

	private  DashboardView  m_view; 
	
	@Autowired
	public IndexPageController(DashboardView view) {
		
		this.m_view = view; 
	}
	
	@GetMapping("/ManagingTool")
	@Override
	public String serveTemplate(Model template) {
	 	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Collection< GrantedAuthority> authorities =  (Collection<GrantedAuthority>) authentication.getAuthorities();
		
		authorities.stream().forEach(authoti -> System.out.println(authoti.getAuthority()));
	 
		template =  ModelFactory.createModel(template , this.m_view  );
		
		return "index";
	}	
	
	
	@GetMapping("/invalidSession")
	public String serveInvalidTemplate(Model template) {
	
		return "invalidSession";
	}	
	
	
}