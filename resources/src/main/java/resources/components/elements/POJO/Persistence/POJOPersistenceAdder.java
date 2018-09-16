package resources.components.elements.POJO.Persistence;


@FunctionalInterface
public interface POJOPersistenceAdder<T> {
  
	public T addToPersistence(T currentContentPOJO, T newContentPOJO);
}
