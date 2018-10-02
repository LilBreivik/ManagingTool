import { Component  } from '@angular/core';  
import {AssetsManager} from '@scheduleassets/src/assetsmanager/assetsmanager.component';
import { CourseScheduleAssetsStockService } from "../service/files/assets/courseschedule.assets.stock.files.service"; 
import {CourseAssetsFileRequestParameter} from "@scheduleassets/src/entities/parameter/assets/request/stock/assets.course.stock.request.parameter"; 
import {CoursesScheduleService} from "@frontendutilities/src/services/REST/coursesschedule.service";
import {DeleteCourseScheduleFileService } from "../service/files/delete/courseschedule.delete.files.service"; 
import { DownloadCourseScheduleFileService } from "../service/files/download/courseschedule.download.files.service"; 
import {DownloadCourseScheduleTemplateFileService} from '@scheduleassets/src/services/files/download/download.course.schedule.template.files.service';
import {UploadAssetsFileRequestParameter} from "@scheduleassets/src/entities/parameter/assets/request/upload/assets.upload.request.parameter";
import {UploadCourseScheduleFileService }  from "../service/files/upload/courseschedule.upload.files.service";
 


@Component({
    selector: 'coursescheduleassetsmanager-root',
    templateUrl: './courseschedulemanager.component.html',
    providers :[CourseScheduleAssetsStockService , 
                    CoursesScheduleService, 
                        CourseAssetsFileRequestParameter, 
                            DeleteCourseScheduleFileService, 
                                DownloadCourseScheduleTemplateFileService,
                                    DownloadCourseScheduleFileService, 
                                        UploadCourseScheduleFileService,
                                            UploadAssetsFileRequestParameter]
}) 

export class CourseScheduleAssetsManager 
                                        extends AssetsManager {
    
        constructor(private coursesScheduleService :  CoursesScheduleService,
                        private courseScheduleAssetsStockService : CourseScheduleAssetsStockService , 
                            private courseScheduleFileDeleteService : DeleteCourseScheduleFileService,
                                private courseDownloadCourseScheduleFileService: DownloadCourseScheduleFileService,
                                    private courseUploadScheduleFileService : UploadCourseScheduleFileService,
                                        private courseAssetsFileRequestParameter : CourseAssetsFileRequestParameter, 
                                            private courseAssetsFileUploadRequestParameter: UploadAssetsFileRequestParameter, 
                                                protected courseDownloadTemplateFileService: DownloadCourseScheduleTemplateFileService){
       
            super(courseScheduleAssetsStockService,
                      courseScheduleFileDeleteService, 
                        courseDownloadCourseScheduleFileService ,
                          coursesScheduleService,
                             courseUploadScheduleFileService ,
                                courseAssetsFileRequestParameter, 
                                     courseAssetsFileUploadRequestParameter,  
                                        ".xml");
                        
            this.p_downloadTemplateFileService = courseDownloadTemplateFileService;
              
        }
}



