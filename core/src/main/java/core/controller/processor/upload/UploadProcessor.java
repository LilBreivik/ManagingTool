package core.controller.processor.upload;

import java.io.FileNotFoundException;

@FunctionalInterface
public interface UploadProcessor {

	public void processUploadedFile() throws ClassCastException;
}
