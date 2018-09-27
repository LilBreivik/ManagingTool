package core.backend.advice;

import java.io.File;
import java.util.stream.Collectors;

import resources.error.InternalError;
import resources.error.parameter.FileAssetParameterViolationError;
import resources.utils.general.GeneralPurpose; 

public class AdviceMessageFactory {

	
	public static AdviceMessage createWrongFileAdviceMessage(File wrongFile, FileAssetParameterViolationError.FileExtension expectedExtension) {
		
		
	  return new AdviceMessage("Die Datei " + wrongFile.getName() + " scheint beschädigt zu sein . " + "\r\n" + 
		 	   " Bitte versuchen Sie den Upload mit einer anderen Datei erneut. " + "\r\n" +   "\r\n" +  "\r\n" + 
		 	   " Tipp: Stellen Sie fest, ob die hochgeladene Datei, auch die Dateieindung  " +  expectedExtension.toString() + 
		 	   " Besitzt und nicht u.a. eine dieser " + GeneralPurpose.ArrayToCollection(FileAssetParameterViolationError.FileExtension.values())
		 	   														 .stream()
		 	   														 .filter(ext -> !ext.toString().equals( expectedExtension.toString()))
		 	   														 .collect(Collectors.toList()));
	
	}
	
	
	public static AdviceMessage createInternalErrorAdviceMessage(InternalError internalError) {
		 
		return new AdviceMessage("Es kam zu einem Serverseitigen-Fehler, bitte beachten Sie den folgenden Hinweis" + "\r\n" 
				+ " bevor Sie sich an den Admin der Applikation wenden " + "\r\n" + "Hinweis : " + "\r\n" + internalError.toString());
	}


	public static AdviceMessage createMissingAssetFileAdviceMessage(String name, String cause) {
		
		return new AdviceMessage("Die Datei " + name + " kann nicht " + cause  + " werden, da sie nicht exisitiert" + "\r\n" 
				+ "Tipp: Stellen Sie zunächst fest, ob diese Datei (oder die zugehörige) überhaupt hochgeladen wurde ");
	}
}
