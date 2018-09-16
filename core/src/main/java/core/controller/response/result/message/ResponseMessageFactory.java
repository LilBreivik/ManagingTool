package core.controller.response.result.message;

import java.io.File;
import java.util.stream.Collectors;

import resources.error.parameter.FileAssetParameterViolationError;
import resources.utils.general.GeneralPurpose; 

public class ResponseMessageFactory {

	
	public static ResponseMessage createWrongFileAdviceMessage(File wrongFile, FileAssetParameterViolationError.FileExtension expectedExtension) {
		
		
	  return new ResponseMessage("Die Datei " + wrongFile.getName() + " scheint beschädigt zu sein . " + "\r\n" + 
		 	   " Bitte versuchen Sie den Upload mit einer anderen Datei erneut. " + "\r\n" +   "\r\n" +  "\r\n" + 
		 	   " Tipp: Stellen Sie fest, ob die hochgeladene Datei, auch die Dateieindung  " +  expectedExtension.toString() + 
		 	   " Besitzt und nicht u.a. eine dieser " + GeneralPurpose.ArrayToCollection(FileAssetParameterViolationError.FileExtension.values())
		 	   														 .stream()
		 	   														 .filter(ext -> !ext.toString().equals( expectedExtension.toString()))
		 	   														 .collect(Collectors.toList()));
	
	}
}
