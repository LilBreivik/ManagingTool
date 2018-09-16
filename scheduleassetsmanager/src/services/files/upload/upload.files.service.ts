import {RESTService} from "@frontendutilities/src/services/REST/rest.service"; 
import { Observable } from 'rxjs';
import {HttpEvent} from '@angular/common/http'; 
import { UploadAssetsFileRequestParameter } from "../../../entities/parameter/assets/request/upload/assets.upload.request.parameter"; 

export class UploadFileService 
                            extends RESTService< HttpEvent<any> > {
       
    constructor(uploadAssetsURL : string){
        super(uploadAssetsURL);
    } 
 

    uploadFile(courseRequestParameter: UploadAssetsFileRequestParameter):  Observable<HttpEvent<any>> {
        
       return  this.postRESTObject( courseRequestParameter);  
    }
}