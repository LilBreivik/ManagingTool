import * as Collections from "typescript-collections";

import {LectureSchedulePOJOalt} from "./lectureschedulepojoalt"; 

export class SemesterPOJO{

    semesterNr: string;
   
    lecturesInSemester: Collections.LinkedList<LectureSchedulePOJOalt>;
}
 