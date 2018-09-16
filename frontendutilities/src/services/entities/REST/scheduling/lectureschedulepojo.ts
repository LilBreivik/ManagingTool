import {ScheduledLecturePOJO} from "../../../entities/REST/scheduling/scheduledlecturepojo"; 

export class LectureSchedulePOJO{

    semesterNo : string[] = []; 

    courses : string[] = [];
  
    degree : string; 
  
    lectureName : string;

    lectureId : string;
	
	lectureNameShortcut : string;
		
	isPractice : boolean;
	
    day : string; 
    
    startTime: string; 
	
    endTime: string; 
     
    public isEqual(lectureToCompare){

        return (this.lectureId === lectureToCompare.lectureId) 
    
    }

    public updateInformation(scheduledLecture: ScheduledLecturePOJO){

        this.day = scheduledLecture.day
        this.startTime = scheduledLecture.startTime
        this.endTime = scheduledLecture.endTime

    }

    /**
     * Method that is need to identify
     * a lecture with its properties.. 
     * 
     * 
     */
    public checkIdentity(pojoToCompare: LectureSchedulePOJO){

     //   alert(pojoToCompare.lectureName +" "+ this.lectureName)
   //     alert(pojoToCompare.lectureName == this.lectureName)

        return (pojoToCompare.lectureName == this.lectureName);
    }


    public static makeid() {
        var text = "";
        var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
      
        for (var i = 0; i < 5; i++)
          text += possible.charAt(Math.floor(Math.random() * possible.length));
      
        return text;
    }
 
}