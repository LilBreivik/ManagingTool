package resources.components.filereader.utils;

@FunctionalInterface
public interface FileNameTranslator {

	public String translateFileName(String filename);
}
