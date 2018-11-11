package core.Test.controller.synthesize.testrunner;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import core.Test.controller.synthesize.SynthesizeControllerSuite; 

public class SynthesizeControllerTestRunner {

	 public static void main(String[] args) {
	      Result result = JUnitCore.runClasses(SynthesizeControllerSuite.class);

	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
			
	      System.out.println(result.wasSuccessful());
	   }
}
