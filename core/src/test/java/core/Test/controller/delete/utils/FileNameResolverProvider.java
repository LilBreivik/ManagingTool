package core.Test.controller.delete.utils;

import core.utils.names.FileNameResolver;
import resources.components.elements.POJO.Course.CoursePOJO;

@FunctionalInterface
public interface FileNameResolverProvider {

	public FileNameResolver provideFileNameResolver(CoursePOJO pojo);
}
