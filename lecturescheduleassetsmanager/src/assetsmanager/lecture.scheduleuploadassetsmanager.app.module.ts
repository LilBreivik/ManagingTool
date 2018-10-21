import { BrowserModule } from '@angular/platform-browser'; 
import { NgModule } from '@angular/core';  
import { FormsModule} from '@angular/forms';  
import {FrontendUtilitiesApplicationModule} from "@frontendutilities/src/services/frontendutilities.app.module"; 
import {LectureScheduleAssetsManager} from "./lectureschedule.assetsmanager.component";  
import {UploadAssetsManager } from "@scheduleassets/src/upload/upload.assetsmanager.component";  

@NgModule({
  declarations: [   
    LectureScheduleAssetsManager , 
    UploadAssetsManager 
  ], 
  imports: [
    BrowserModule,    
    FrontendUtilitiesApplicationModule,
    FormsModule
  ],  
   bootstrap: [ LectureScheduleAssetsManager ]
}) 
  
export class LectureUploadAssetsManagerApplicationModule{ }
