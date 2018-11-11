package core.backend.REST.nonfileasset.notice.parameter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import resources.components.elements.POJO.Notices.NoticesPOJO;
import resources.error.parameter.ParameterViolationError;

public class SpecficNoticeParameter 
											extends NoticeParameter{

	@JsonCreator
	public SpecficNoticeParameter(@JsonProperty("notice") NoticesPOJO request) {
		super(request);
		 
	}

	
	@Override
	public void verifyParameter() { 
		
		if((getRequest().getNoticeHeadline().equals(NO_INPUT)) )  {
			
			throw new  ParameterViolationError( NO_HEADLINE );
		}
	}
}
