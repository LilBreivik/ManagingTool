import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {CoursePOJO} from "../../services/entities/REST/scheduling/coursepojo";
import {RESTService} from "./rest.service";
import { Observable, of } from 'rxjs';  
import { catchError, map, tap } from 'rxjs/operators';
import {CoursesRequestParameter} from '../../services/entities/Parameter/coursesrequestparameter';
import { CoursesSchedulePOJO } from "../../services/entities/REST/scheduling/coursesschedulepojo"; 
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