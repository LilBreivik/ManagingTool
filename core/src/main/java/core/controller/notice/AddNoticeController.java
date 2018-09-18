package core.controller.notice;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class AddNoticeController {

	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/Notice/Add", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public void test() {}
}
