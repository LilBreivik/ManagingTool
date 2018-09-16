import { Pipe, PipeTransform, Injectable } from '@angular/core'; 
import { UniqueFilterPipe } from './uniquepipe';


@Injectable({
    providedIn: 'root'
  })
  
@Pipe({name: 'parallelCourse'})  
export class ParallelCourseNameFilterPipe  
                                            implements PipeTransform {

       
        private itemAttribute = (item) => {
 
            let coursename = item.courseName;
           
            return coursename;
        
        }
 
        transform(items: any, args: string): any {
         
            let parallelArray = [];

            items.forEach(item => {

                if(( this.itemAttribute(item) !== args) && (parallelArray.indexOf(this.itemAttribute(item)) == -1)) {
                    
                    parallelArray.push(this.itemAttribute(item));
                }
            });
            
            return parallelArray;
    }
}
