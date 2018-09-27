import { Injectable , Inject} from '@angular/core';
import {RESTService} from "./rest.service";
import { Observable, of } from 'rxjs';  
import { CoursesSchedulePOJO } from "../../services/entities/REST/scheduling/coursesschedulepojo"; 
import {environment} from '../../environments/environment';  
 
/**
 * Service class for handling 
 * the Course Schedule POJO
 */

 
@Injectable()
export class CoursesScheduleService
                            extends RESTService<  CoursesSchedulePOJO  >{

        constructor(  ){
            super( environment.API_URL +  "/Synthesize/GeneralCourseSchedule" )

        //    this.p_modal = modal; 
        }

        /** GET hero by id. Will 404 if id not found */
        getCourseSchedule(): Observable<CoursesSchedulePOJO >  {
             
            return this.getRESTObject(); 
        }
} 