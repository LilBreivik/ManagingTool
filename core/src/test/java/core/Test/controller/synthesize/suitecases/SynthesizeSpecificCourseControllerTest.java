package core.Test.controller.synthesize.suitecases;
 
import core.backend.REST.nonfileasset.synthesize.controller.SpecificSynthesizedCourseScheduleController;

public class SynthesizeSpecificCourseControllerTest 
												extends  SynthesizeControllerTest {

public SynthesizeSpecificCourseControllerTest() {

		    p_controllerURL = SpecificSynthesizedCourseScheduleController.SpecificCourseScheduleSynthesizeURL;
		    p_fileAssetName = "AISEBaSynthesized"; 
		    p_specificDescriptionMessage = "Synthesize Specific Course";
		}

}
