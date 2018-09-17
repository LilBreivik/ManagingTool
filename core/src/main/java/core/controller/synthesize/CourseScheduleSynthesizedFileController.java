package core.controller.synthesize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import core.controller.parameter.synthesized.SynthesizedCourseScheduleFileParam;
import core.controller.response.result.successfully.successes.CourseScheduleSynthesizingResponse; 
import resources.components.elements.POJO.Schedule.Lectures.CoursePOJO;
import resources.components.filehandler.filesynthesizer.LectureScheduleSynthesizer;

@Controller
public class  CourseScheduleSynthesizedFileController  {
	
	@Autowired
	private LectureScheduleSynthesizer m_synthesizer;
	
	
	
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/Synthesize/SpecificCourseSchedule", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public CourseScheduleSynthesizingResponse synthesizeFile(@RequestBody  SynthesizedCourseScheduleFileParam synthesizedFileRequest )  
	{
		// verify parameter ... 
		 
		synthesizedFileRequest.verifyParameter();
		
		CoursePOJO pojo = (CoursePOJO) m_synthesizer.synthesizeAssets(synthesizedFileRequest.getUploadedFileName().getResolvedUploadedFileName()); 	

		System.out.println(pojo);
		
		// synthesize
		return new CourseScheduleSynthesizingResponse((CoursePOJO) m_synthesizer.synthesizeAssets(synthesizedFileRequest.getUploadedFileName().getResolvedUploadedFileName())); 	
	}
}
