package core.backend.REST.nonfileasset.administration.useroverview.task;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.general.task.response.nonrequest.AbstractResponseTaskWithoutRequestImpl; 
import resources.components.elements.POJO.UserOverview.UserOverviewPOJO;
import resources.components.elements.POJO.UserOverview.UserSummaryPOJO; 
import resources.database.repository.AccountsRepository; 
import resources.database.repository.SessionsRepository; 

@Service 
public class UserOverviewTask  
									extends  AbstractResponseTaskWithoutRequestImpl <   UserOverviewPOJO  >{
  
	// List that contains the needed information, to display some overview 
	private List<UserSummaryPOJO> m_UserSummaries; 

	@Autowired 
	private AccountsRepository m_accountsRepo; 
	
	@Autowired 
	private SessionsRepository m_sessionRepo; 
	
	
		
 
	@Override
	public void workOnTask( ) {
 
		
		m_UserSummaries =  m_accountsRepo.read().stream()
						 				.map( acc -> {
						 					
						 					UserSummaryPOJO userSummary = new UserSummaryPOJO();
						 					
						 					userSummary.setName(acc.getAccountOwners().getUserName());
						 					userSummary.setEmail(acc.getAccountOwners().getUserEmail());
						 				//	userSummary.setStatus(m_sessionRepo.read(acc).isOnline());
						 					
						 					return userSummary; 
						 				} ).collect(Collectors.toList());
	}

	
	@Override
	public SuccessResponse<UserOverviewPOJO> getResultsFromTask() {
	
		UserOverviewPOJO userOverview = new UserOverviewPOJO();
		
	//	userOverview.setUserOverview(m_UserSummaries);
		
		return  new SuccessResponse<UserOverviewPOJO>(userOverview) ;
	}
} 