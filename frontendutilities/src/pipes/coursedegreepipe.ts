import { Pipe, PipeTransform } from '@angular/core'; 
import { UniqueFilterPipe } from './uniquepipe';


@Pipe({name: 'coursedegrees'})  
export class UniqueCourseDegreeFilterPipe  
                                          extends  UniqueFilterPipe  {

    constructor(){
         super(); 
         this.itemAttribute = (item) => {

            let coursename = item.courseDegree;
            return coursename;
         }
    }                                       
    
}
