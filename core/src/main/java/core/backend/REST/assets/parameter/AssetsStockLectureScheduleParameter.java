package core.backend.REST.assets.parameter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import core.provider.NameResolverProvider;
import resources.components.elements.POJO.Course.CoursePOJO;


/**
 * Parameter that 
 * is used to describe 
 * the amount of lecture schedule files
 * 
 * */


public class AssetsStockLectureScheduleParameter 
											extends AssetsStockParameter{

	@JsonCreator
	public AssetsStockLectureScheduleParameter(@JsonProperty( assetsStockFileRequestParameter) CoursePOJO coursePOJO) {
			super(coursePOJO);
	
			setFileNameResolver(NameResolverProvider.provideNameResolverForLectureScheduleFile()); 
	}

}
