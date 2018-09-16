package resources.components.filehandler.filereader;

@FunctionalInterface
public interface FileNameTranslator {

	public String translateFileName(String filename);
}
