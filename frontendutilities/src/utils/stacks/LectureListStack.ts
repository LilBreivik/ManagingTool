
import {GenericStack} from "./GenericStack";
import {LectureSchedulePOJOList} from "../../utils/lists/LectureSchedulePOJOList"; 
import {LectureSchedulePOJO} from "../../services/entities/REST/scheduling/lectureschedulepojo"; 


export class LectureListStack 
                                extends GenericStack<LectureSchedulePOJO>{

    
    constructor(lecturesListSize : number){
        super(lecturesListSize); 
    }
  
    public undo() {
         
        // removes the top element and put it to the undoed Stack elements 
 
        let tets = super.popCurrentList() 
 

        return tets
    }

   

}