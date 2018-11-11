package core.Test.controller.delete.suitecases;

import core.Test.controller.delete.utils.FileNameResolverProvider;
import core.backend.REST.fileasset.delete.controller.DeleteLectureFileController;
import core.provider.FileNameProvider;
import core.utils.names.FileNameResolver;
import resources.components.elements.POJO.Course.CoursePOJO;

public class DeleteLectureFileControllerTest 
												extends  DeleteFileControllerTest {

	public DeleteLectureFileControllerTest() {
	
		p_controllerURL = DeleteLectureFileController.DeleteLectureFileURL;
		p_specificDescriptionMessage = "Lecture File Delete";
		
		p_FileNameResolverProvider = new FileNameResolverProvider() {
			
			@Override
			public FileNameResolver provideFileNameResolver(CoursePOJO pojo) {
				
				return FileNameProvider. provideFileNameResolverForLectureScheduleFile(pojo);
			}
		};
	}

}
