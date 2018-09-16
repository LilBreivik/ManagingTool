import { LinkedList } from "typescript-collections";
 
export class GenericList<T> 
                                extends LinkedList<T>
{

    public addAll(listToAddElementsFrom: GenericList<T>){
 
        listToAddElementsFrom.forEach(item => {

            super.add(item);
        });
    }
  
    public splice(lastIndexToCutFrom : number){

        let contentsFromList = this.toArray();

        contentsFromList.splice(lastIndexToCutFrom, 1);

        this.clear()

        contentsFromList.forEach(item => this.add(item));
    }
     

    public static diff<U>( listToSubstractFrom: GenericList<U>, 
                                listToSubstract: GenericList<U>, 
                                    sortingExpression: any){

        let differenceList = new GenericList<U>();
  
        listToSubstractFrom.forEach(item => {
   
            if(sortingExpression(item, listToSubstract)){
  
                differenceList.add(item);  
            }       
        }); 
   
       return  differenceList;
    }
}