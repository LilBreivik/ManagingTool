package core.backend.utils.download.handlerimpl;

import static resources.error.parameter.fileasset.FileAssetParameterViolationError.FileExtension.XML;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

public class GeneralXMLFileDownloadHandler

							extends DownloadHandlerAbstractImpl{
 
	@Override
	public void processDownload(File sourceFile, HttpServletResponse fileToDownload) {
		

		 super.processDownload(sourceFile, fileToDownload);
		
		 fileToDownload.setContentType("application/xml");
		  
		 fileToDownload.setHeader("Content-Disposition", "attachment; filename=" + ( new StringBuilder( "\"" + sourceFile.getName().concat(XML.toString()) + "\"")).toString() );
	}
}
