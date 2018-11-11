package core.backend.REST.fileasset.delete.parameter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import core.provider.NameResolverProvider;
import resources.components.elements.POJO.Course.CoursePOJO;

public class DeleteCourseFileParameter 
											extends DeleteFileParameter{

	@JsonCreator
	public DeleteCourseFileParameter( @JsonProperty(deleteFileRequestParameter ) CoursePOJO  coursePOJO) 
	{
		super(coursePOJO);

		setFileNameResolver(NameResolverProvider.provideNameResolverForCourseScheduleFile()); 
		
		setTargetFileName(  p_fileNameResolver.getResolvedFileName());
	}
}
