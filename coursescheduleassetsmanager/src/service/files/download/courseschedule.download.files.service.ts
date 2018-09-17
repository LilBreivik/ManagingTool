import {DownloadFileService} from "@scheduleassets/src/services/files/download/download.files.service"
import {environment} from  "@frontendutilities/src/environments/environment";

export class DownloadCourseScheduleFileService 
                                            extends DownloadFileService{
       
            constructor(){
                    super(environment.API_URL + "/Download/Schedule/Course");
            }  
}