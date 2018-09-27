import {GenericList} from "./GenericList";
import {LectureSchedulePOJO} from "../../services/entities/REST/scheduling/lectureschedulepojo"; 


export class LectureSchedulePOJOList
                                        extends GenericList<LectureSchedulePOJO>{

 
    public removeElement(item :  LectureSchedulePOJO){

        let  lastLectureCtr = null; 

        try{
            this.forEach(lecture => {

                if(lecture.isEqual(item)){
            
                   lastLectureCtr = this.indexOf(lecture);
                   this.removeElementAtIndex(lastLectureCtr)
                   
                   throw "";
                }
          
              });
        }
        catch(e){

        }
         
    }
 
    public addUniquely(item : LectureSchedulePOJO){
 
        if(this.size() == 0){
            this.add(item);
        }
        else{

            try{

                this.forEach(lecture => {
  
                    if( (lecture .lectureName === item.lectureName) &&
                            ( lecture.startTime === item.startTime) && 
                                ( lecture.endTime === item.endTime)  && 
                                    ( lecture.day == item.day)   && 
                                        ( (lecture.semesterNo.indexOf(item.semesterNo[0]) < 0) || 
                                            (lecture. courses .indexOf(item. courses [0]) < 0)   )  

                        ){  
                            if(lecture.semesterNo.indexOf(item.semesterNo[0]) < 0){
                                
                                lecture.semesterNo.push(item.semesterNo[0]); 
                            }
                     
                            if(lecture. courses .indexOf(item. courses [item.courses.length -1]) < 0){ 
                                lecture. courses .push(item. courses [item.courses.length -1]); 
                            }
         
                            throw "break out ";
                        }
                     
                }) 

                this.add(item) 
            }
            catch(e){
              
            } 
        } 
    }
 
    private static lectureSortingExpression = (pojo: LectureSchedulePOJO, listToSubstractFrom: GenericList<LectureSchedulePOJO>) => {

        let flag : boolean = true; 

        var BreakException = {};

        try{

            listToSubstractFrom.forEach(item => {
 
                if(item.checkIdentity(pojo)){ 
                    throw BreakException
                } 
            });
        }
        catch(e){
            flag = false;  
            return flag;
        } 
       
        return flag;
    }
    
    public static diffLectureSchedulePOJOList(  listToSubstractFrom: GenericList<LectureSchedulePOJO>, 
        listToSubstract: GenericList<LectureSchedulePOJO> ){
        
        let difference : LectureSchedulePOJOList = new LectureSchedulePOJOList()

        super.diff<LectureSchedulePOJO >( listToSubstractFrom,  listToSubstract, 
                                                      this.lectureSortingExpression).forEach(lecture => difference.add(lecture));
 
        return difference;
    }

    public getElementByIndex(index : number){

       return  this.elementAtIndex(index);
    }


    public ifListsEqual(list : LectureSchedulePOJOList){


        for(var i = 0; i < list.size(); i+= 1){

            if(!(list.getElementByIndex(i).isEqual(this.getElementByIndex(i)))){

                return false;
            }
        }

        return true;
    }
}