package core.Test.controller.delete.suitecases;

 

import core.Test.controller.delete.utils.FileNameResolverProvider;
import core.backend.REST.fileasset.delete.controller.DeleteCourseFileController;
import core.provider.FileNameProvider;
import core.utils.names.FileNameResolver;
import resources.components.elements.POJO.Course.CoursePOJO;

public class DeleteCourseFileControllerTest 
												extends  DeleteFileControllerTest {

	public DeleteCourseFileControllerTest() {
		
		 p_controllerURL = DeleteCourseFileController.DeleteCourseFileURL;
		 p_specificDescriptionMessage = "Course File Delete";
		 
		 p_FileNameResolverProvider = new FileNameResolverProvider() {
				
				@Override
				public FileNameResolver provideFileNameResolver(CoursePOJO pojo) {
					
					return FileNameProvider. provideFileNameResolverForCourseScheduleFile(pojo);
				}
			};
	}
	 
}
