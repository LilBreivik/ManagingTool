package resources.components.elements.POJO.Persistence;

@FunctionalInterface
public interface POJOPersistenceSubtracter<T, U> {

	public T subtractFromPersistence(T currentContentPOJO, U contentToSubstractPOJO);
}
