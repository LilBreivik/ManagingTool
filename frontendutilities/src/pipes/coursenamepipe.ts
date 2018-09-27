import { Pipe, PipeTransform , Injectable} from '@angular/core'; 
import { UniqueFilterPipe } from './uniquepipe';

 
@Pipe({name: 'coursenames'})  
export class UniqueCourseNameFilterPipe  
                                          extends  UniqueFilterPipe  {

    constructor(){
         super(); 
       
         this.itemAttribute = (item) => {

            
            let coursename = item.courseName;
           
            return coursename;
         }
    }                                       
    
}
