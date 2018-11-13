package core.backend.REST.nonfileasset.administration.useroverview.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus; 
import core.backend.REST.general.controller.nonequest.response.NonRequestResponseController; 
import core.backend.REST.general.response.result.successfully.SuccessResponse;
import core.backend.REST.nonfileasset.administration.useroverview.task.UserOverviewTask; 
import resources.components.elements.POJO.UserOverview.UserOverviewPOJO;

@Controller
public class UserOverviewController 
						extends NonRequestResponseController< UserOverviewPOJO, UserOverviewTask >{
	/**
	 * Configuration 
	 * Area 
	 * */ 
	 
	protected final String CourseAssetsStockURL =  "/Admin/User/Overview";

	@Override
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = CourseAssetsStockURL, method = RequestMethod.POST, produces="application/json")
	public SuccessResponse< UserOverviewPOJO> handleRequest() {
	 
		return super.handleRequest( );
	} 
	
	/**
	 * Configuration 
	 * Area 
	 * */
	 
	@Override
	@Autowired
	public void setTask( UserOverviewTask task) {
	 
		p_task = task;
	} 
}
