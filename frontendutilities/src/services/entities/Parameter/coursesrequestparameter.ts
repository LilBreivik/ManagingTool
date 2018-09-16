import {LectureSchedulePOJOList} from "../../../utils/lists/LectureSchedulePOJOList"; 
import {CourseRequestParameter} from "@frontendutilities/src/services/entities/Parameter/courserequestparameter"; 


export class CoursesRequestParameter 
								extends CourseRequestParameter{
			
	// list of lectures 
	public lecturesList: LectureSchedulePOJOList;
}