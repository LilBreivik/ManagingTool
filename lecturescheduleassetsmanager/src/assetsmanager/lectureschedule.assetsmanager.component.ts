import { Component  } from '@angular/core';  
import {AssetsManager} from '@scheduleassets/src/assetsmanager/assetsmanager.component';
import { LectureScheduleAssetsStockService } from "@scheduleassets/src/services/files/assets/lectureschedule.assets.stock.files.service"; 
import {CourseAssetsFileRequestParameter} from "@scheduleassets/src/entities/parameter/assets/request/stock/assets.course.stock.request.parameter"; 
import {CoursesScheduleService} from "@frontendutilities/src/services/REST/coursesschedule.service";
import {DeleteLectureScheduleFileService } from "@scheduleassets/src/services/files/delete/lectureschedule.delete.files.service"; 
import { DownloadLectureScheduleFileService } from "@scheduleassets/src/services/files/download/lectureschedule.download.files.service"; 
import {UploadAssetsFileRequestParameter} from "@scheduleassets/src/entities/parameter/assets/request/upload/assets.upload.request.parameter";
import {UploadLectureScheduleFileService }  from "@scheduleassets/src/services/files/upload/lectureschedule.upload.files.service";

 
@Component({
    selector: 'lectureschedulemanager-root',
    templateUrl: './lectureschedulemanager.component.html',
    providers :[ LectureScheduleAssetsStockService , 
                    CoursesScheduleService, 
                        CourseAssetsFileRequestParameter, 
                            DeleteLectureScheduleFileService, 
                                DownloadLectureScheduleFileService, 
                                    UploadLectureScheduleFileService,
                                        UploadAssetsFileRequestParameter]
}) 

export class LectureScheduleAssetsManager 
                                        extends AssetsManager {
    
        constructor(private coursesScheduleService :  CoursesScheduleService,
                        private lecturescheduleAssetsStockService :  LectureScheduleAssetsStockService , 
                            private lecturescheduleFileDeleteService : DeleteLectureScheduleFileService,
                                private courseDownloadlecturescheduleFileService: DownloadLectureScheduleFileService,
                                    private courseUploadScheduleFileService : UploadLectureScheduleFileService,
                                        private courseAssetsFileRequestParameter : CourseAssetsFileRequestParameter, 
                                            private courseAssetsFileUploadRequestParameter: UploadAssetsFileRequestParameter){
       
            super(lecturescheduleAssetsStockService,
                      lecturescheduleFileDeleteService, 
                        courseDownloadlecturescheduleFileService ,
                          coursesScheduleService,
                             courseUploadScheduleFileService ,
                                courseAssetsFileRequestParameter, 
                                  courseAssetsFileUploadRequestParameter);
                        
              
        }
}



