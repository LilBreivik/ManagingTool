import {RESTService} from "../rest.service";
import { Observable, of } from 'rxjs';   
import {environment} from '../../../environments/environment'; 
import { NoticeRequestParameter } from '../../entities/Parameter/noticerequestparameter'; 
 
/**
 * Service class for handling 
 * the Course Schedule POJO
 */
 
export abstract class NoticeService<ResponseType>
                            extends RESTService< ResponseType >{

        constructor(NoticeCommand : string){
            super( environment.API_URL +  "/Notice/" + NoticeCommand)
        }

       
        handleNoticeRequest(noticeRequestParameter: NoticeRequestParameter): Observable<  ResponseType >  {
        
            return this.postRESTObject(noticeRequestParameter);
        }
}