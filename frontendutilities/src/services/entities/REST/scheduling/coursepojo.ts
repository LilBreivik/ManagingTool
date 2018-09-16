import * as Collections from "typescript-collections";
import {SemesterPOJO} from "./semesterpojo"; 

export class CoursePOJO {
    // Course Name
	courseName: string;
	
	// Course Term
	courseTerm: string;
	
	// Course Degree 
	courseDegree: string;
	
	semesters : Collections.LinkedList<SemesterPOJO>;
 
}