import * as Collections from "typescript-collections";
import {LectureDayTimingsPOJO} from "../timings/lecturedaytimingspojo"; 

export class LectureSchedulePOJOalt{

    lectureName : string;
	
	lectureNameShortcut : string;
		
	practice : boolean;
	
	timingsForALecture : Collections.LinkedList<LectureDayTimingsPOJO>; 

}