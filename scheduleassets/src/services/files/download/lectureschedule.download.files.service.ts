import {DownloadFileService} from "./download.files.service";
import {environment} from  "@frontendutilities/src/environments/environment";


export class DownloadLectureScheduleFileService 
                                            extends DownloadFileService{
       
            constructor(){
                    super(environment.API_URL + "/Download/Schedule/Lecture");
            }  
}