import { Injectable , Inject} from '@angular/core'; 
import {RESTService} from "./rest.service";
import {CollisionCheckRequestParameter} from "../../services/entities/Parameter/collisioncheckrequestparameter"; 
import {ScheduledLecturesPOJO} from "../../services/entities/REST/scheduling/scheduledlecturespojo";  
import {environment} from '../../environments/environment'; 

declare var $: any;

@Injectable()
export class CollisionCourseScheduleService 
                            extends RESTService< ScheduledLecturesPOJO >{

        constructor(){
            super(environment.API_URL +  "/Collision/Check");
        } 

        checkCollisions(collisioncheckParameter: CollisionCheckRequestParameter){
           
            $("#progressModal").modal('show')

            let test = this.postRESTObject(collisioncheckParameter);
            
            $("#progressModal").modal('hide')
            
            return test;
        }
}