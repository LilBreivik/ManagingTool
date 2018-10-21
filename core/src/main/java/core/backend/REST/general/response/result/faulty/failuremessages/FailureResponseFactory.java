package core.backend.REST.general.response.result.faulty.failuremessages;
 

import core.backend.REST.general.response.result.faulty.failures.FileNotThereFailureResponse;
import core.backend.REST.general.response.result.faulty.failures.InternalErrorFailureResponse;
import core.backend.REST.general.response.result.faulty.failures.ParameterViolatedFailureResponse; 
import core.backend.advice.AdviceMessageFactory;
import resources.error.FileIsMissingError;
import resources.error.InternalError;
import resources.error.parameter.ParameterViolationError;
import resources.error.parameter.fileasset.FileAssetParameterViolationError;

public class FailureResponseFactory {

	 
	public static ParameterViolatedFailureResponse createParameterViolationErrorResponse(ParameterViolationError violatedParameter ) {
		
		ParameterViolatedFailureResponse errorResponse = new ParameterViolatedFailureResponse("Die Anfrage war falsch");
		
		if(violatedParameter instanceof FileAssetParameterViolationError) {
			
			FileAssetParameterViolationError fileAssetWrong = (FileAssetParameterViolationError) violatedParameter; 
			
			errorResponse .setMoreInfo(AdviceMessageFactory.createWrongFileAdviceMessage(fileAssetWrong.getM_file(),
					fileAssetWrong.getFileExtension()));
			
		}
		
		errorResponse.setErrorMessage(violatedParameter.toString());
		
		return errorResponse;
			
	}

	public static InternalErrorFailureResponse createParameterInternalErrorResponse(InternalError internalError) {
		
		InternalErrorFailureResponse internalServerError = new InternalErrorFailureResponse("Ein interner Server-Fehler is unterlaufen");
		  
		internalServerError.setMoreInfo( AdviceMessageFactory.createInternalErrorAdviceMessage(internalError));
		
		
		internalServerError.setErrorMessage(internalError.toString());
		 
		return internalServerError;
	}

	  

	public static FileNotThereFailureResponse createFileNotThereErrorResponse(
			FileIsMissingError fileDoesNotExist) {
		
		FileNotThereFailureResponse errorResponse = new FileNotThereFailureResponse(fileDoesNotExist);
		   
		errorResponse.setMoreInfo(AdviceMessageFactory.createMissingAssetFileAdviceMessage(fileDoesNotExist.missingFileName, 
																			fileDoesNotExist.missingFileCause));
		
		return errorResponse;
		
	}
}
