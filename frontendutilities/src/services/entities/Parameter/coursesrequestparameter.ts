import {LectureSchedulePOJOList} from "../../../utils/lists/LectureSchedulePOJOList"; 
import {CourseRequestParameter} from "../Parameter/courserequestparameter"; 


export class CoursesRequestParameter 
								extends CourseRequestParameter{
			
	// list of lectures 
	public lecturesList: LectureSchedulePOJOList;
}