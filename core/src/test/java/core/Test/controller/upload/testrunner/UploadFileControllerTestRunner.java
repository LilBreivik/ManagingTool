package core.Test.controller.upload.testrunner;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import core.Test.controller.upload.UploadControllerSuite;

public class UploadFileControllerTestRunner {

	 public static void main(String[] args) {
	      Result result = JUnitCore.runClasses(UploadControllerSuite.class);

	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
			
	      System.out.println(result.wasSuccessful());
	   }
}
