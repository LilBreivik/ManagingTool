import * as Collections from "typescript-collections";

import {LectureTimingPOJO} from "./lecturetimingpojo"; 

export class LectureDayTimingsPOJO {

	day : string; 

    vTimings :  Collections.Set <LectureTimingPOJO>; 

}