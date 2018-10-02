package core.backend.REST.nonfileasset.notice.parameter;
 
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import core.backend.REST.general.request.custom.nonschedule.RESTCustomNonScheduleRequest;
import resources.components.elements.POJO.Notice.NoticesPOJO; 
import resources.error.parameter.ParameterViolationError; 


public class NoticeParameter 
							extends RESTCustomNonScheduleRequest<NoticesPOJO>{
 
	protected final String NO_INPUT = ""; 
	
	protected final String NO_HEADLINE  = "Du hast keinen Namen für die Notiz angegeben"; 
	
	protected final String NO_NOTICE  = "Du hast keine Notiz angegeben"; 
	
	
	
	@JsonCreator
	public NoticeParameter(@JsonProperty("notice") NoticesPOJO  request ) {
		super(request ); 
	}
	 
	@Override
	public void verifyParameter() { 
		
		if((getRequest().getNoticeHeadline().equals(NO_INPUT)) )  {
			
			throw new  ParameterViolationError( NO_HEADLINE );
		}
		
		else if((getRequest().getNotice().equals(NO_INPUT)) )  {
			
			throw new  ParameterViolationError( NO_NOTICE );
		}
		
		
		else if((getRequest().getScheduledLectures() == null))  {
		
			throw new  ParameterViolationError(parameterErrorMessageInvalidParameter);
		}
		else {
			
			if(getRequest().getScheduledLectures ().size() == 0) {
				
				throw new  ParameterViolationError(parameterErrorMessageInvalidParameter);
			}
		}
	}
	 
}
  