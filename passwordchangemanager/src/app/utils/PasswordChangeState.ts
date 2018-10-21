export enum PasswordChangeState { 
  
    PASSWORDCHANGEATTEMPT = "PASSWORDCHANGEATTEMPT", // working state 
    PASSWORDCANBECHANGED = "PASSWORDCANBECHANGED", // state that the password can be changed at this point 
    BOTHNOTFILLED = "BOTHNOTFILLED", // state if the new password fields are empty 
    BOTHNOTMATCH = "BOTHNOTMATCH", //  state if the new password fields do not match 
    PASSWORDCHANGED = "PASSWORDCHANGED"  // state if the password was changed successfully 
   
   }
 