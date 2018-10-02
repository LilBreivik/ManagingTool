import {RESTService} from "@frontendutilities/src/services/REST/rest.service";
import {CourseRequestParameter} from "@frontendutilities/src/services/entities/Parameter/courserequestparameter"; 
import { Observable } from 'rxjs';

export class DownloadCourseScheduleTemplateFileService 
                            extends RESTService< Blob > {
       
    constructor(){
        super("/Download/General/Schedule/Course/Template");
    } 
 
    downloadFile( ):  Observable<Blob> {
        
       return  this.postRESTObjectNonParsed( );  
    }
}