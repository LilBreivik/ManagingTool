import { LinkedList } from "typescript-collections";
import {LectureSchedulePOJO} from "../../entities/REST/scheduling/lectureschedulepojo";

export  class NoticeRequestParameter  {
 
	public  scheduledLectures : LectureSchedulePOJO[] = []; 

	public  notice : String 

	public noticeHeadline : string; 
}
