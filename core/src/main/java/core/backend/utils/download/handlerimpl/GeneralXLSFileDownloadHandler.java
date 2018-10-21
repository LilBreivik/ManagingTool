package core.backend.utils.download.handlerimpl;

import static resources.error.parameter.fileasset.FileAssetParameterViolationError.FileExtension.XLS;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

public class GeneralXLSFileDownloadHandler 

								extends DownloadHandlerAbstractImpl{

	@Override
	public void processDownload(File sourceFile, HttpServletResponse fileToDownload) {
		
		 super.processDownload(sourceFile, fileToDownload);
		
		 fileToDownload.setContentType("application/xls");
		  
		 fileToDownload.setHeader("Content-Disposition", "attachment; filename=" + ( new StringBuilder( "\"" + sourceFile.getName().concat(XLS.toString()) + "\"")).toString() );
		
	}
}
