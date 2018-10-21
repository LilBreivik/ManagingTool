package core.backend.utils.download.handler;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

 
public interface DownloadHandler {

	public abstract void processDownload(File sourceFile, HttpServletResponse fileToDownload);

	public abstract void processDownload(HttpServletResponse fileToDownload);
}
