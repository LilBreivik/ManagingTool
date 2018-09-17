import {DownloadFileService} from "@scheduleassets/src/services/files/download/download.files.service"
import {environment} from  "@frontendutilities/src/environments/environment";

export class DownloadLectureScheduleFileService 
                                            extends DownloadFileService{
       
            constructor(){
                    super(environment.API_URL + "/Download/Schedule/Lecture");
            }  
}