package core.backend.REST.general.request.custom.nonschedule;

import core.backend.REST.general.request.MasterRESTRequest; 

public abstract class RESTCustomNonScheduleRequest<NonSchedulePOJO> 
														extends  MasterRESTRequest<NonSchedulePOJO>{

	public RESTCustomNonScheduleRequest(NonSchedulePOJO request) {
		super(request);
	 
	}

}
