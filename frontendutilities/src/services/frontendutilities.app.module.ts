import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { NgModule   } from '@angular/core';  
import { UniqueCourseNameFilterPipe} from "../pipes/coursenamepipe";
import { UniqueCourseDegreeFilterPipe  } from "../pipes/coursedegreepipe";
import { UniqueCourseTermFilterPipe } from "../pipes/coursetermpipe";  
import {ParallelCourseNameFilterPipe} from "../pipes/parallelcoursenamefilterpipe";   

@NgModule({

  imports: [
    CommonModule,
    HttpClientModule
  ],

declarations: [  
    UniqueCourseNameFilterPipe, 
    UniqueCourseDegreeFilterPipe,
    UniqueCourseTermFilterPipe, 
    ParallelCourseNameFilterPipe
  ],

exports: [  
    UniqueCourseNameFilterPipe, 
    UniqueCourseDegreeFilterPipe,
    UniqueCourseTermFilterPipe, 
    ParallelCourseNameFilterPipe 
  ] 
  
})

export class FrontendUtilitiesApplicationModule  { }