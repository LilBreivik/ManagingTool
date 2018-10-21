import { Injectable } from '@angular/core'; 
import {CoursePOJO} from "../../services/entities/REST/scheduling/coursepojo";
import {RESTService} from "./rest.service";
import { Observable, of } from 'rxjs';   
import {CoursesRequestParameter} from '../../services/entities/Parameter/coursesrequestparameter';
import {environment} from '../../environments/environment'; 
 
/**
 * Service class for handling 
 * the Course POJOs
 */
 
@Injectable()
export class CourseService 
                            extends RESTService< CoursePOJO >{

        constructor( ){
            super( environment.API_URL +  "/Synthesize/SpecificCourseSchedule");
        }
 
        /** GET hero by id. Will 404 if id not found */
       getCourses(coursesRequestParameter: CoursesRequestParameter): Observable<CoursePOJO >  {
 
            return this.postRESTObject(coursesRequestParameter);
        }

}