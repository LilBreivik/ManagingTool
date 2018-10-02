package core.backend.NonREST.model.views;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import core.backend.NonREST.model.configuration.IPageView;
import core.backend.NonREST.model.views.utils.helper.AssetStockView;
import core.backend.NonREST.model.views.utils.helper.AssetStockViewer;
import core.configuration.authentication.user.AuthorizedUserAccount;
import resources.components.elements.POJO.Persistence.Course.PersistenceCourseSchedulePOJO;
import resources.database.entities.Accounts.Accounts.AccountTypes;
import resources.database.entities.Accounts.Sessions;
import resources.database.repository.SessionsRepository;
import resources.utils.general.GeneralPurpose;
 

@Component
public class DashboardView  
						extends AssetStockContentView
									implements IPageView{
 
	private SessionsRepository m_sessionRepo; 
	 
	
	@Autowired	
	public  DashboardView(SessionsRepository sessionRepo, 
				@Qualifier("provide AssetStockViewer for Course Schedule") AssetStockViewer<PersistenceCourseSchedulePOJO> courseScheduleAssetStockViewer, 
					@Qualifier("provide AssetStockViewer for Lecture Schedule") AssetStockViewer<PersistenceCourseSchedulePOJO> lectureScheduleAssetStockViewer) {
					
		
		super(courseScheduleAssetStockViewer, lectureScheduleAssetStockViewer);
		
		m_sessionRepo = sessionRepo; 
		
		HEADLINE_DISPLAY = true; 
		HEADLINE = "Dashboard";
		
		CONTENT_DISPLAY = true; 
		TEMPLATE_REPLACEMENT_EXPRESSION ="fragments/dashboard"; 
		
		FOOTER_DISPLAY = false;
	}
	
	
	
	@Override
	public Model buildRequiredView(Model model) {
		
		model.addAttribute(HEADLINE_DISPLAY_ATTRIBUTE, HEADLINE_DISPLAY);
		model.addAttribute(HEADLINE_ATTRIBUTE, HEADLINE);
		
		model.addAttribute(CONTENT_DISPLAY_ATTRIBUTE, CONTENT_DISPLAY);
		model.addAttribute(CONTENT_ATTRIBUTE , TEMPLATE_REPLACEMENT_EXPRESSION);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String currentPrincipalName = authentication.getName();
		
		model.addAttribute("USER_NAME", currentPrincipalName);
		
		Collection< GrantedAuthority> authorities =  (Collection<GrantedAuthority>) authentication.getAuthorities();
		 
		model.addAttribute("USER_ROLE",  AccountTypes.valueOf(GeneralPurpose.CollectionToList(authorities).get(0).toString()).toString() );
		
		AuthorizedUserAccount authorizedAccount = (AuthorizedUserAccount) authentication.getPrincipal();
		
		 
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
		Sessions session = m_sessionRepo.read(authorizedAccount.getLoggedInAccountId());
		
		
		if(session.getLastLogin() == null) {
			
			model.addAttribute("DISPLAY_FIRSTLOGIN", true );
		}
		else {
			
			model.addAttribute("DISPLAY_FIRSTLOGIN", false );
			
			// Using DateFormat format method we can create a string 
			// representation of a date with the defined format.
			String reportDate = df.format(session.getLastLogin());
			
			model.addAttribute("LAST_LOGIN_AT", reportDate );
		}
		
		p_courseScheduleAssetStockViewer.createAssetStockView();
		
		p_lectureScheduleAssetStockViewer.createAssetStockView();
		
		model.addAttribute("COURSE_STOCK_ASSETS_VIEW", p_courseScheduleAssetStockViewer.getAssetsStockViews());
		
		model.addAttribute("LECTURE_STOCK_ASSETS_VIEW", p_lectureScheduleAssetStockViewer.getAssetsStockViews());
		
		
		if(checkExistingComplementarySchedules()) {
			
			model.addAttribute("STATE_DESCRIPTION", "col-md-12 col-sm-12 col-xs-12");
			
			model.addAttribute("DISPLAY_STATE", false);
		}
		else {
			
			model.addAttribute("STATE_DESCRIPTION", "col-md-9 col-sm-9 col-xs-12");
			
			model.addAttribute("DISPLAY_STATE", true);
			
		}
		
		model.addAttribute(FOOTER_DISPLAY_ATTRIBUTE, FOOTER_DISPLAY);
		
		return model;
	}
	
}
