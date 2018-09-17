import {UploadFileService } from "@scheduleassets/src/services/files/upload/upload.files.service.ts"
//"./upload.files.service";
import {environment} from  "@frontendutilities/src/environments/environment";
 
export class UploadLectureScheduleFileService 
                                            extends UploadFileService {
       
            constructor(){
                    super(environment.API_URL + "/Upload/Schedule/Lecture");
            }  
}