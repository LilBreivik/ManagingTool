package core.controller.response.result.successfully;

import org.springframework.http.HttpStatus;

import core.controller.response.result.ResponseResult;
import scheduling.elements.POJO.ScheduledLecturePOJO;

public class SuccessResponse<T> 
								extends ResponseResult<T> {

	public SuccessResponse(T body) {
		super(body, HttpStatus.OK);
	}

	 
}
