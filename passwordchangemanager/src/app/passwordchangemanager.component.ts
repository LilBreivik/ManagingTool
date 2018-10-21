import { Component, OnChanges, SimpleChanges, DoCheck } from '@angular/core';

import {PasswordChangeRequestParameter} from '/Users/dustin/git/ManagingTool/frontendutilities/src/services/entities/Parameter/passwordchangerequestparameter';
import {PasswordChangeService} from "../services/passwordchange.service"; 
import {PasswordChangeState} from "../app/utils/PasswordChangeState"; 
 
@Component({ 
                            
  selector: 'passwordchangemanager-root',
  templateUrl: './passwordchangemanager.component.html',
  styleUrls: ['./passwordchangemanager.component.css'], 
  providers:[ PasswordChangeService ]
})
    

 export class PasswordChangeManager 
                                    implements DoCheck{
    
  CURRENTPASSWORDCHANGESTATE: PasswordChangeState = PasswordChangeState.PASSWORDCHANGEATTEMPT;

  // model variables

  newPasswordRepeated : string = ''; 
  newPassword : string = '';
  existingPassword : string = '';

  constructor( private passwordChangeService : PasswordChangeService){}
 
  ngDoCheck(){
  
      // check if the new password, matches its repitition
      
      if(this.newPassword === this.newPasswordRepeated ){ 
         
        if(this.CURRENTPASSWORDCHANGESTATE != PasswordChangeState.PASSWORDCHANGED)
        {
          if(this.newPassword !== ''){

                this.CURRENTPASSWORDCHANGESTATE = PasswordChangeState.PASSWORDCANBECHANGED;
            
          }
          else{
    
            this.CURRENTPASSWORDCHANGESTATE = PasswordChangeState.BOTHNOTFILLED;
          }
        }
      }
      else{

        this.CURRENTPASSWORDCHANGESTATE = PasswordChangeState.BOTHNOTMATCH;
      }
  }

  changePassword(){

      let passwordChangeRequestParameter  = new PasswordChangeRequestParameter();

      passwordChangeRequestParameter.passwordChange.existingPassword = this.existingPassword;
      passwordChangeRequestParameter.passwordChange.newPassword =  this.newPassword;
      passwordChangeRequestParameter.passwordChange.newPasswordRepeated = this.newPasswordRepeated;

      this.existingPassword = ""; 
      this.newPassword = ""; 
      this.newPasswordRepeated = ""; 
 
      this.passwordChangeService.requestPasswordChange (passwordChangeRequestParameter).subscribe( passwordChangeResponse => {
   
          if(passwordChangeResponse.successFlag  ){

            this.CURRENTPASSWORDCHANGESTATE = PasswordChangeState.PASSWORDCHANGED;
          }
          else{

            this.CURRENTPASSWORDCHANGESTATE = PasswordChangeState.PASSWORDCHANGEATTEMPT;
          }
    }); 
 
  }

} 