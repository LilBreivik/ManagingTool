import { LinkedList } from "typescript-collections";
import {ScheduledLecturePOJO } from "../scheduling/scheduledlecturepojo"; 
import {CorrectionPOJO} from "../scheduling/correctionpojo"; 

export  class ScheduledLecturesPOJO {

	public  scheduledLectures : LinkedList<ScheduledLecturePOJO> 

	public  correctionMessages : LinkedList<CorrectionPOJO>  
  
}
