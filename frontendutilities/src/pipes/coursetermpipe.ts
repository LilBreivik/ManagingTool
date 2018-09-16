import { Pipe, PipeTransform } from '@angular/core'; 
import { UniqueFilterPipe } from './uniquepipe';


@Pipe({name: 'courseterms'})  
export class UniqueCourseTermFilterPipe  
                                          extends  UniqueFilterPipe  {

    constructor(){
         super(); 
         this.itemAttribute = (item) => {

            let coursename = item.courseTerm;
            return coursename;
         }
    }                                       
    
}
