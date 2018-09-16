package core.controller.response.result.faulty.failuremessages;

   

import java.io.FileNotFoundException;

import core.controller.response.result.faulty.failuremessages.advice.AdviceMessageFactory;
import core.controller.response.result.faulty.failures.FileNotThereFailureResponse;
import core.controller.response.result.faulty.failures.InternalErrorFailureResponse;
import core.controller.response.result.faulty.failures.ParameterViolatedFailureResponse;
import resources.error.FileIsMissingError;
import resources.error.InternalError;
import resources.error.download.AssetFileIsMissing;
import resources.error.parameter.FileAssetParameterViolationError;
import resources.error.parameter.ParameterViolationError;

public class FailureResponseFactory {

	public static ParameterViolatedFailureResponse createParameterViolationErrorResponse(ParameterViolationError violatedParameter ) {
		
		ParameterViolatedFailureResponse errorResponse = new ParameterViolatedFailureResponse("Die Anfrage war falsch");
		
		if(violatedParameter instanceof FileAssetParameterViolationError) {
			
			FileAssetParameterViolationError fileAssetWrong = (FileAssetParameterViolationError) violatedParameter; 
			
			
			errorResponse .setMoreInfo(AdviceMessageFactory.createWrongFileAdviceMessage(fileAssetWrong.getM_file(),
					fileAssetWrong.getFileExtension()));
			
		}
		
		return errorResponse;
			
	}

	public static InternalErrorFailureResponse createParameterInternalErrorResponse(InternalError internalError) {
		
		InternalErrorFailureResponse internalServerError = new InternalErrorFailureResponse("Ein interner Server-Fehler is unterlaufen");
		 
		internalServerError.setMoreInfo( AdviceMessageFactory.createInternalErrorAdviceMessage(internalError));
		
		 
		return internalServerError;
	}

	 

	public static FileNotThereFailureResponse createParameterViolationErrorResponse(
			FileIsMissingError fileDoesNotExist) {
		

		FileNotThereFailureResponse errorResponse = new FileNotThereFailureResponse("Die Datei ist nicht vorhanden");
		
		if(fileDoesNotExist instanceof AssetFileIsMissing) {
			
			AssetFileIsMissing fileAssetDoesNotExist = (AssetFileIsMissing) fileDoesNotExist; 
			
			
			errorResponse .setMoreInfo(AdviceMessageFactory.createMissingAssetFileAdviceMessage(fileAssetDoesNotExist.getAssetFileName()));
			
		}
		
		return errorResponse;
		
	}
}
