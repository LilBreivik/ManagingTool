import {RESTService} from "@frontendutilities/src/services/REST/rest.service"; 
import {CourseRequestParameter} from "@frontendutilities/src/services/entities/Parameter/courserequestparameter"; 
 

export class DeleteFileService 
                                            extends RESTService< Object > {
    constructor(deleteAssetsURL : string){
        super(deleteAssetsURL);
    } 

    deleteFile(courseRequestParameter: CourseRequestParameter){
            
        return this.postRESTObject(courseRequestParameter);
    } 

}