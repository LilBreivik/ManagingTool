import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core'; 
import { CourseScheduleManager } from './courseschedulemanager.component';
import {UniqueCourseNameFilterPipe} from "@frontendutilities/src/pipes/coursenamepipe";
import { UniqueCourseDegreeFilterPipe  } from "@frontendutilities/src/pipes/coursedegreepipe";
import {UniqueCourseTermFilterPipe } from "@frontendutilities/src/pipes/coursetermpipe";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import{ScheduleManager} from "./courseschedule/schedulemanager.component";  
import {Schedule} from "./schedule/schedule.component"; 
import { NgDragDropModule } from 'ng-drag-drop';
import {ParallelCourseNameFilterPipe} from "@frontendutilities/src/pipes/ParallelCourseNameFilterPipe";
import {Lecture} from "./lecture/lecture.component";  
import {ScheduleData} from "@frontendutilities/src/services/Data/schedule.data.services";
 
@NgModule({
  declarations: [
    Schedule, 
    Lecture,  
    ParallelCourseNameFilterPipe, 
    ScheduleManager, 
    ScheduleData,
    CourseScheduleManager, 
    UniqueCourseNameFilterPipe, 
    UniqueCourseDegreeFilterPipe, 
    UniqueCourseTermFilterPipe
  ], 
  imports: [     
    BrowserModule, 
    HttpClientModule, 
    FormsModule ,  
    NgDragDropModule.forRoot()
  ],
  providers: [ScheduleManager, ScheduleData  ],
  bootstrap: [ CourseScheduleManager],
  entryComponents: [Schedule]
})

export class CourseScheduleManagerApplicationModule { }
