import {DeleteFileService} from "@scheduleassets/src/services/files/delete/delete.files.service"
import {environment} from  "@frontendutilities/src/environments/environment";


export class DeleteLectureScheduleFileService 
                                            extends DeleteFileService{
       
            constructor(){
                    super(environment.API_URL +   "/Delete/Schedule/Lecture");
            }  
}