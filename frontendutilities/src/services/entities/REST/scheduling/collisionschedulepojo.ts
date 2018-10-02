import {LectureSchedulePOJO} from "./lectureschedulepojo";

export class CollisionSchedulePOJO{

    // Course Name	
	public courseName: string;
													
	// Course Degree 										
	public courseDegree: string;

	// Course Term									
    public courseTerm: string;	

    // list of the scheduled Lectures 
    public lecturesList: LectureSchedulePOJO[] = []; 
}