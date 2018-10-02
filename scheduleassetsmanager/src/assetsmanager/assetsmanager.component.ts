import {  Input, OnInit} from '@angular/core';    
import {CourseSchedulePOJO} from "@frontendutilities/src/services/entities/REST/scheduling/courseschedulepojo";
import {AssetsStockService} from "../services/files/assets/assets.stock.files.service";
import {CourseRequestParameter} from "@frontendutilities/src/services/entities/Parameter/courserequestparameter";
import {CoursesScheduleService} from "@frontendutilities/src/services/REST/coursesschedule.service";
import {DeleteFileService} from "../services/files/delete/delete.files.service"; 
import {DownloadFileService } from "../services/files/download/download.files.service"; 
import {DownloadCourseScheduleTemplateFileService}  from "../services/files/download/download.course.schedule.template.files.service";
import {UploadFileService } from "../services/files/upload/upload.files.service"; 
import {UploadAssetsFileRequestParameter} from "../entities/parameter/assets/request/upload/assets.upload.request.parameter";
import { HttpEvent} from '@angular/common/http';
import * as FileSaver from "file-saver";  


declare function uploadAsset2(input : HTMLInputElement);
 
 
export class AssetsManager implements OnInit  {

    private assetsThere: boolean = false;
    private uploaderName: string; 

    private selectedCourseNameValue: string; 
    private selectedCourseDegreeValue: string; 
    private selectedCourseTermValue: string;  
  
    protected  p_downloadTemplateFileService  : DownloadCourseScheduleTemplateFileService;

    @Input() coursesSchedule:  CourseSchedulePOJO[];
 
    constructor(private m_assetsStockService :AssetsStockService, 
                    private m_deleteFileService : DeleteFileService,
                        private m_downloadFileService : DownloadFileService, 
                            private m_coursescheduleService : CoursesScheduleService,
                                private m_uploadFileService: UploadFileService,
                                    private m_requestParameter: CourseRequestParameter, 
                                        private m_uploadFileRequestParameter : UploadAssetsFileRequestParameter,
                                            private m_filending : string)
    { }

    ngOnInit(): void {
     
         this. m_coursescheduleService .getCourseSchedule().subscribe(courseSchedule => {
           
            this.coursesSchedule = []
            courseSchedule.coursesSchedulePOJO.forEach(course =>{
            
                this.coursesSchedule.push(course)
             
            }) 
            
            // we have to init the default values manually due to the missing capability of the 
            // selected tag in angular
 
            this.selectedCourseNameValue = this.coursesSchedule[0].courseName
            this.selectedCourseDegreeValue = this.coursesSchedule[0].courseDegree
            this.selectedCourseTermValue = this.coursesSchedule[0].courseTerm
    
            this.readAssetsInformation()
         
        })
    }

    readAssetsInformation(){
  
        this. m_requestParameter.course.courseName = this.selectedCourseNameValue 
        this. m_requestParameter.course.courseDegree = this.selectedCourseDegreeValue
        this. m_requestParameter.course.courseTerm =  this.selectedCourseTermValue

        this.m_assetsStockService.requestFileAssetsStock( this. m_requestParameter).subscribe(assetsStock => {
             
             this.uploaderName = assetsStock.uploaderName
             this.assetsThere = assetsStock.presentFlag
        })
    }

    deleteAsset(){

        this. m_requestParameter.course.courseName = this.selectedCourseNameValue 
        this. m_requestParameter.course.courseDegree = this.selectedCourseDegreeValue
        this. m_requestParameter.course.courseTerm =  this.selectedCourseTermValue

        // handle delete answer 
        this.m_deleteFileService.deleteFile(this.m_requestParameter).subscribe(deleteResponse => {
 
            this.readAssetsInformation()
        });
        
    }

    downloadTemplateFileAsset(){
 
        this. m_requestParameter.course.courseName = this.selectedCourseNameValue 
        this. m_requestParameter.course.courseDegree = this.selectedCourseDegreeValue
        this. m_requestParameter.course.courseTerm =  this.selectedCourseTermValue

        this.p_downloadTemplateFileService.downloadFile( ).subscribe((response: Blob) => {
      
            let filename : string = "GeneralCourseScheduleTemplateFile" + "" +this.m_filending;
   
            FileSaver.saveAs(response, filename);
 
            this.readAssetsInformation()
        });
    } 
 
    downloadAsset() {

        this. m_requestParameter.course.courseName = this.selectedCourseNameValue 
        this. m_requestParameter.course.courseDegree = this.selectedCourseDegreeValue
        this. m_requestParameter.course.courseTerm =  this.selectedCourseTermValue

        
        // handle delete answer 
        this.m_downloadFileService.downloadFile(this.m_requestParameter).subscribe((response: Blob) => {
      
            let filename : string = ""
  
            filename = this.selectedCourseNameValue + " " + 
                                this.selectedCourseDegreeValue +" "+
                                     this.selectedCourseTermValue + "" +this.m_filending;


            FileSaver.saveAs(response, filename);
 
            this.readAssetsInformation()
        });
    }



    uploadAsset(test : Event) {
       
      let reader : FileReader = uploadAsset2((test.target as HTMLInputElement))
 
      reader.onload  = (e) => {
      
       this. m_uploadFileRequestParameter.courseScheduleFile.courseName =  this.selectedCourseNameValue 
       this. m_uploadFileRequestParameter.courseScheduleFile.courseDegree = this.selectedCourseDegreeValue
       this. m_uploadFileRequestParameter.courseScheduleFile.courseTerm =  this.selectedCourseTermValue
 
       this.m_uploadFileRequestParameter.courseScheduleFile.scheduleFile = reader.result.toString();
 
       this. m_uploadFileService.uploadFile(this.m_uploadFileRequestParameter).subscribe((response: HttpEvent<any>) => {
 
            this.readAssetsInformation()
        });
      }
    
 
    }
  
}



