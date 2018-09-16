package core.controller.processor.download;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface DownloadProcessor {

	public void processDownload(File sourceFile, HttpServletResponse fileToDownload);

}
