import { Injectable } from '@angular/core'; 
import {RESTService} from "./rest.service";
import { Observable, of } from 'rxjs';   
import {environment} from '../../environments/environment'; 
import { NoticeRequestParameter } from '../entities/Parameter/noticerequestparameter';
 
 
/**
 * Service class for handling 
 * the Course Schedule POJO
 */

 
@Injectable()
export class NoticeService
                            extends RESTService<  Object  >{

        constructor(){
            super( environment.API_URL +  "/Notice/Add")
        }

       
        addNotice(noticeRequestParameter: NoticeRequestParameter): Observable< Object >  {
            
            return this.postRESTObject(noticeRequestParameter);
        }
}