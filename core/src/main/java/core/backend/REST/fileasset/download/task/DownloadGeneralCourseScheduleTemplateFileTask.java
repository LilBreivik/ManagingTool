package core.backend.REST.fileasset.download.task;

import static resources.error.parameter.FileAssetParameterViolationError.FileExtension.XML;
import javax.servlet.http.HttpServletResponse;
import resources.components.filehandler.XML.GeneralCourseScheduleTemplateXMLFileHandler;
import resources.error.FileIsMissingError;
import resources.error.InternalError;
 
public class DownloadGeneralCourseScheduleTemplateFileTask 
												extends DownloadFileTask {

	private final static String generalCourseScheduleTemplateFile = "GeneralCourseScheduleTemplateFile"; 
	
	public DownloadGeneralCourseScheduleTemplateFileTask(GeneralCourseScheduleTemplateXMLFileHandler generalCourseScheduleTemplateFileHandler) {
		super(generalCourseScheduleTemplateFileHandler,  (SourceFile, Response) ->  {
			
			 Response.setContentType("application/xml");
			  
			 Response.setHeader("Content-Disposition", "attachment; filename=" + ( new StringBuilder( "\"" + generalCourseScheduleTemplateFile.concat(XML.toString()) + "\"")).toString() );
			
		}); 
	}

	
	public void setHTTPResponse(HttpServletResponse response){
		
		System.out.println(p_httpServletResponse == null);
		p_httpServletResponse = response; 
	}
	
	
	@Override
	public void workOnTask() {
		  
		System.out.println(p_httpServletResponse == null);
		
		
		if(p_httpServletResponse != null) {
		
			// first check , if the file does exist at all 
			
			// we have to translate the requested name to the real existing Source on the machine ...
			
			try {
				
				final String physicalNameOfSource = p_downloadFileHandler.getFileAssetsManager()
						.getFileNameTranslator()
							.translateFileName(generalCourseScheduleTemplateFile);
				
				if(p_downloadFileHandler.getFileAssetsManager()
											.getPathManager()
												.getPathOfFile(physicalNameOfSource )
													.toFile().exists()) 
				{
					if(this.p_httpServletResponse != null) {
					
						processDownload( p_downloadFileHandler.getFileAssetsManager().getPathManager().getPathOfFile(physicalNameOfSource ).toFile(),
						p_httpServletResponse);
					}
					else 
					{			
						InternalError internalError = new InternalError("Download Response was not defined");
						
						throw internalError;
					}		
				}
				
				else {
				
					FileIsMissingError missingFileError = new FileIsMissingError("Die Datei " + generalCourseScheduleTemplateFile  + " ist nicht vorhanden");
					
					missingFileError.missingFileName =  generalCourseScheduleTemplateFile ; 
					
					missingFileError.missingFileCause = "heruntergeladen"; 
					
					throw missingFileError;
				}
							
			}
			catch(NullPointerException fileWasDeletedBefore) {
				
				// We cannot build a path to the real existing ressource, if it ws deleted before 
				
				throw new InternalError("Die Datei " + generalCourseScheduleTemplateFile  + " wurde schon gelöscht");
				
			}
			
		}
		
		else {
			
			// We cannot build a path to the real existing ressource, if it ws deleted before 
			
			throw new InternalError("Der Request kann nicht erfüllt werden");
		}
		
	}
}
