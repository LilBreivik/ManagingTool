import {RESTService} from "@frontendutilities/src/services/REST/rest.service";
import {CourseRequestParameter} from "@frontendutilities/src/services/entities/Parameter/courserequestparameter"; 
import { Observable } from 'rxjs';

export class DownloadFileService 
                            extends RESTService< Blob > {
       
    constructor(downloadAssetsURL : string){
        super(downloadAssetsURL);
    } 
 

    downloadFile(courseRequestParameter: CourseRequestParameter):  Observable<Blob> {
        
       return  this.postRESTObjectNonParsed(courseRequestParameter);  
    }
}  