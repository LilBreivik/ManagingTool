import { Pipe, PipeTransform } from '@angular/core'; 

/*
    Utility Class 
    to remove double values from 
    datasources 
*/ 
export class UniqueFilterPipe  implements PipeTransform {

    protected itemAttribute = (item) => {}
 
    transform(items: any , args: string): any {
        let uniqueArray = [];
   
        if (!args){ 
            
            items.forEach(item => { 
                    if(uniqueArray.indexOf(this.itemAttribute(item)) == -1){

                        uniqueArray.push(this.itemAttribute(item));
                    }
            });
 
            return uniqueArray;
        }
           
}

 
}
