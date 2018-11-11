package core.backend.REST.assets.parameter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import core.provider.NameResolverProvider;
import resources.components.elements.POJO.Course.CoursePOJO;

public class AssetsStockCourseScheduleParameter 
													extends AssetsStockParameter{

	@JsonCreator
	public AssetsStockCourseScheduleParameter(@JsonProperty( assetsStockFileRequestParameter) CoursePOJO coursePOJO) {
		super(coursePOJO);
		
		setFileNameResolver(NameResolverProvider.provideNameResolverForCourseScheduleFile()); 
	}

}