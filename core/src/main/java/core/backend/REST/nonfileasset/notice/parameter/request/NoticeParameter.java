package core.backend.REST.nonfileasset.notice.parameter.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import core.backend.REST.general.request.MasterRESTCustomRequest;
import resources.error.parameter.ParameterViolationError;
import scheduling.elements.POJO.LecturePOJO; 

public class NoticeParameter
								extends MasterRESTCustomRequest{

	protected String m_notice; 
	
	protected String m_noticeHeadline; 
	
	protected List<LecturePOJO> m_lecturesList;
	
	
	 
	
}
  