import { Directive, HostListener, HostBinding } from '@angular/core';

@Directive({
  selector: '[passwordBar]'
})


export class PasswordBar {
   
   @HostListener('click', ['$event']) onClick(event){
 
        Array.from(document.querySelectorAll("input"))
                
            .forEach(inputField => {

                if ( inputField.getAttribute("type") == "password" ) {
                                  
                    inputField.setAttribute("type", "text");
                }
                else if ( inputField.getAttribute("type") == "text" ) {
                
                    inputField.setAttribute("type", "password");
                }
            });           
    }

}