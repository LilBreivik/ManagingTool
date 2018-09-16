import {   Input, OnInit} from '@angular/core';    
import {CourseSchedulePOJO} from "@frontendutilities/src/services/entities/REST/scheduling/courseschedulepojo";
import {AssetsStockService} from "../services/files/assets/assets.stock.files.service";
import {CourseRequestParameter} from "@frontendutilities/src/services/entities/Parameter/courserequestparameter";
import {CoursesScheduleService} from "@frontendutilities/src/services/REST/coursesschedule.service";
import {DeleteFileService} from "../services/files/delete/delete.files.service"; 
import {DownloadFileService } from "../services/files/download/download.files.service"; 
import {UploadFileService } from "../services/files/upload/upload.files.service"; 
import {UploadAssetsFileRequestParameter} from "../entities/parameter/assets/request/upload/assets.upload.request.parameter";
import { HttpEvent} from '@angular/common/http';
import * as FileSaver from "file-saver";  
 

declare function uploadAsset2(input : HTMLInputElement);


export class AssetsManager implements OnInit  {

    private assetsThere: boolean = false;

    private selectedCourseNameValue: string; 
    private selectedCourseDegreeValue: string; 
    private selectedCourseTermValue: string;  
  
    @Input() coursesSchedule:  CourseSchedulePOJO[];
 
    constructor(private m_assetsStockService :AssetsStockService, 
                    private m_deleteFileService : DeleteFileService,
                        private m_downloadFileService : DownloadFileService, 
                            private m_coursescheduleService : CoursesScheduleService,
                               private m_uploadFileService: UploadFileService,
                                  private m_requestParameter: CourseRequestParameter , 
                                    private m_uploadFileRequestParameter : UploadAssetsFileRequestParameter){ }

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

        this. m_requestParameter.courseName = this.selectedCourseNameValue 
        this. m_requestParameter.courseDegree = this.selectedCourseDegreeValue
        this. m_requestParameter.courseTerm =  this.selectedCourseTermValue

        this.m_assetsStockService.requestFileAssetsStock( this. m_requestParameter).subscribe(assetsStock => {
           
             
             this.assetsThere = assetsStock.presentFlag
        })
    }

    deleteAsset(){

        this. m_requestParameter.courseName = this.selectedCourseNameValue 
        this. m_requestParameter.courseDegree = this.selectedCourseDegreeValue
        this. m_requestParameter.courseTerm =  this.selectedCourseTermValue

        // handle delete answer 
        this.m_deleteFileService.deleteFile(this.m_requestParameter).subscribe(deleteResponse => {
 
            this.readAssetsInformation()
        });
        
    }

    downloadAsset(){

        this. m_requestParameter.courseName = this.selectedCourseNameValue 
        this. m_requestParameter.courseDegree = this.selectedCourseDegreeValue
        this. m_requestParameter.courseTerm =  this.selectedCourseTermValue

        // handle delete answer 
        this.m_downloadFileService.downloadFile(this.m_requestParameter).subscribe((response: Blob) => {
     
            let filename = 'report.xml';
            FileSaver.saveAs(response, filename);
 
            this.readAssetsInformation()
        });
        
    } 
    uploadAsset(test : Event) {
       
      let reader : FileReader = uploadAsset2((test.target as HTMLInputElement))
 
      reader.onload  = (e) => {
      
       this. m_uploadFileRequestParameter.courseName =  this.selectedCourseNameValue 
       this. m_uploadFileRequestParameter.courseDegree = this.selectedCourseDegreeValue
       this. m_uploadFileRequestParameter.courseTerm =  this.selectedCourseTermValue

       // alert(typeof reader.result)
 
        this.m_uploadFileRequestParameter.scheduleFile = reader.result.toString();

        alert("---> " + JSON.stringify(this.m_uploadFileRequestParameter));

        this. m_uploadFileService.uploadFile(this.m_uploadFileRequestParameter).subscribe((response: HttpEvent<any>) => {

            alert(response)
            this.readAssetsInformation()
        });
      }
    
 
    }
  
}


