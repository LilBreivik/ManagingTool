import {UploadFileService } from "./upload.files.service";
import {environment} from  "@frontendutilities/src/environments/environment";
 
export class UploadCourseScheduleFileService 
                                            extends UploadFileService {
       
            constructor(){
                    super(environment.API_URL + "/Upload/Schedule/Course");
            }  
}