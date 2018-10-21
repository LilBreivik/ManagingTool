package resources.components.filehandler.utils.adder.general;


@FunctionalInterface
public interface GeneralPOJOPersistenceAdder<T> {
  
	public T addToPersistence(T currentContentPOJO, T newContentPOJO);
}
