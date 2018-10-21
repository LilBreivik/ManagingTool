import { PasswordChangeResponsePOJO} from "../entities/response/password.change.response.pojo"
import { PasswordChangeRequestParameter } from "../../../frontendutilities/src/services/entities/Parameter/passwordchangerequestparameter";
import {RESTService} from "@frontendutilities/src/services/REST/rest.service";
import {environment} from  "@frontendutilities/src/environments/environment";  
 
export class PasswordChangeService 
                            extends RESTService< PasswordChangeResponsePOJO  >{
        
        constructor(){
            super( environment.API_URL + "/Password/Change");
        } 

        requestPasswordChange(passwordChangeParameter: PasswordChangeRequestParameter){
            
            return this.postRESTObject(passwordChangeParameter);
        } 
}     