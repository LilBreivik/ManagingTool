package core.controller.verifier.upload;

import resources.error.parameter.ParameterViolationError;

@FunctionalInterface
public interface UploadVerifier {

	public void verifyUploadParameter() throws ParameterViolationError;
}
