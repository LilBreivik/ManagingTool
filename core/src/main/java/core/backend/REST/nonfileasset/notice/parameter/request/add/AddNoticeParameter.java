package core.backend.REST.nonfileasset.notice.parameter.request.add;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import core.backend.REST.nonfileasset.notice.parameter.request.NoticeParameter;
import resources.error.parameter.ParameterViolationError;
import scheduling.elements.POJO.LecturePOJO;

public class AddNoticeParameter 
								extends NoticeParameter{

	
	@JsonCreator
	public AddNoticeParameter(@JsonProperty("lecturesList") List<LecturePOJO> lecturesList, 
								@JsonProperty("notice") String notice , 
									@JsonProperty("noticeHeadline") String  noticeHeadline) {
		
		m_noticeHeadline = noticeHeadline; 
		
		m_notice = notice; 
		
		m_lecturesList = lecturesList; 
		
		p_verifierJob = () -> { 
    	 	
			if((m_noticeHeadline == null) || (m_lecturesList == null))  {
				throw new  ParameterViolationError(parameterErrorMessageInvalidParameter);
			}
	   };
	}
}
