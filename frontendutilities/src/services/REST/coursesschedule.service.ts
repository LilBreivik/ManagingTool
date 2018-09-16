import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {CoursePOJO} from "../../services/entities/REST/scheduling/coursepojo";
import {RESTService} from "./rest.service";
import { Observable, of } from 'rxjs';  
import { catchError, map, tap } from 'rxjs/operators';
import {CoursesRequestParameter} from '../../services/entities/Parameter/coursesrequestparameter';
import { CoursesSchedulePOJO } from "../../services/entities/REST/scheduling/coursesschedulepojo"; 
import {environment} from '../../environments/environment'; 
 
 
/**
 * Service class for handling 
 * the Course Schedule POJO
 */


@Injectable()
export class CoursesScheduleService
                            extends RESTService<  CoursesSchedulePOJO  >{

        constructor(){
            super( environment.API_URL +  "/Synthesize/GeneralCourseSchedule")
        }

        /** GET hero by id. Will 404 if id not found */
        getCourseSchedule(): Observable<CoursesSchedulePOJO >  {
            
            return this.getRESTObject(); 
        }
}