import { Stack, LinkedList } from "typescript-collections";

export class GenericStack<T>
{  
    private currentStack : T[] = [];
 

    constructor(private sizeOfStack : number){}

    public size(){

        return this.currentStack.length
    }

    private push(element :  T){
        
        this.currentStack.unshift(element) //  push(element)
    }
   

    private pop(){
         
        return this.currentStack.shift();
    }
  
    public pushList(elementList :  LinkedList<T>){ 

        elementList.forEach( ele => this.push (ele))
    }
 
    public popCurrentList(){
            
        let stackToDeliver : T[] = [];

        for(var itr = 0; itr < this.sizeOfStack; itr += 1){

            let popped = this.pop()
 
            stackToDeliver.unshift(popped)
        }

        return stackToDeliver
    }

    
}
 