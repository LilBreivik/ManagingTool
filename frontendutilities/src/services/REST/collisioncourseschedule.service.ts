import { Injectable , Inject} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {CourseSchedulePOJO  } from "../../services/entities/REST/scheduling/courseschedulepojo";
import {RESTService} from "./rest.service";
import {CollisionCheckRequestParameter} from "../../services/entities/Parameter/collisioncheckrequestparameter"; 
import {ScheduledLecturesPOJO} from "../../services/entities/REST/scheduling/scheduledlecturespojo"; 
import { catchError, map, tap } from 'rxjs/operators';
import {environment} from '../../environments/environment'; 

@Injectable()
export class CollisionCourseScheduleService 
                            extends RESTService< ScheduledLecturesPOJO >{

        constructor(){
            super(environment.API_URL +  "/Synthesize/Collision");
        } 

        checkCollisions(collisioncheckParameter: CollisionCheckRequestParameter){
            
            return this.postRESTObject(collisioncheckParameter);
        } 
}