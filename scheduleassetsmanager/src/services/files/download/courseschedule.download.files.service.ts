import {DownloadFileService} from "./download.files.service";
import {environment} from  "@frontendutilities/src/environments/environment";


export class DownloadCourseScheduleFileService 
                                            extends DownloadFileService{
       
            constructor(){
                    super(environment.API_URL + "/Download/Schedule/Course");
            }  
}