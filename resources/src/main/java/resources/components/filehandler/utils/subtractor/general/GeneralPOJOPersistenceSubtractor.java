package resources.components.filehandler.utils.subtractor.general;

@FunctionalInterface
public interface GeneralPOJOPersistenceSubtractor<T > {

	public T subtractFromPersistence(T currentContentPOJO, T  contentToSubstractPOJO);
}
