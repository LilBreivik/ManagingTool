
import {LectureSchedulePOJOList} from "../../../utils/lists/LectureSchedulePOJOList"; 
import {LectureSchedulePOJO} from "../../entities/REST/scheduling/lectureschedulepojo";

export class CollisionCheckRequestParameter{

    // Course Name	
	public courseName: string;
													
	// Course Degree 										
	public courseDegree: string;

	// Course Term									
    public courseTerm: string;	

    // list of the scheduled Lectures 
    public lecturesList: LectureSchedulePOJO[] = []; 

}