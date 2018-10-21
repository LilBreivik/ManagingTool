import { Component , HostBinding, 
    Input, OnInit ,ViewContainerRef } from '@angular/core';  
import {CourseService} from "@frontendutilities/src/services/REST/course.service"; 
import {CoursesRequestParameter} from "@frontendutilities/src/services/entities/Parameter/coursesrequestparameter";  
import {CoursePOJO} from "@frontendutilities/src/services/entities/REST/scheduling/coursepojo"; 
import {ScheduleManager} from "./courseschedule/schedulemanager.component";  
import {ParallelCourseNameFilterPipe} from "@frontendutilities/src/pipes/ParallelCourseNameFilterPipe"; 
import {LectureSchedulePOJOList} from "@frontendutilities/src/utils/lists/LectureSchedulePOJOList"; 
import {CollisionCheckRequestParameter} from "@frontendutilities/src/services/entities/Parameter/collisioncheckrequestparameter"; 
import {CoursesScheduleService} from "@frontendutilities/src/services/REST/coursesschedule.service"; 
import {CollisionCourseScheduleService} from "@frontendutilities/src/services/REST/collisioncourseschedule.service";  
import { LinkedList } from "typescript-collections";
import {CourseSchedulePOJO} from "@frontendutilities/src/services/entities/REST/scheduling/courseschedulepojo";
import {ScheduledLecturePOJO } from "@frontendutilities/src/services/entities/REST/scheduling/scheduledlecturepojo"; 
import { NoticeRequestParameter } from "@frontendutilities/src/services/entities/Parameter/noticerequestparameter"; 
import {CorrectionPOJO} from "@frontendutilities/src/services/entities/REST/scheduling/correctionpojo";
import {AddNoticeService} from "@frontendutilities/src/services/REST/notice/notice.add.service"; 
import {ReadSpecificNoticeService } from "@frontendutilities/src/services/REST/notice/notice.read.specific.service"; 
import {ReadGeneralNoticeService } from "@frontendutilities/src/services/REST/notice/notice.read.general.service"; 
import {ScheduleData} from "@frontendutilities/src/services/Data/schedule.data.services";  
import{ DeleteNoticeService}  from "@frontendutilities/src/services/REST/notice/notice.delete.service"; 
import {PasswordChangeService} from "/Users/dustin/git/ManagingTool/passwordchangemanager/src/services/passwordchange.service";

declare function  updateNoticeInformation(headline, notice);
 
@Component({ 
selector: 'courseschedulemanager-root',
templateUrl: './courseschedulemanager.component.html',
providers: [CoursesScheduleService, 
             CollisionCourseScheduleService,
                   CourseService, 
                       AddNoticeService, 
                         DeleteNoticeService,
                           ReadSpecificNoticeService,
                               ReadGeneralNoticeService,
                                  ParallelCourseNameFilterPipe ]

}) 



export class CourseScheduleManager implements OnInit   {

public coursesSchedule:  CourseSchedulePOJO[];
private courses: CoursePOJO ; 
private  notices: String[];

// variable needed to activate the notice bar 

private selectedCourseNameValue: string; 
private selectedCourseDegreeValue: string; 
private selectedCourseTermValue: string;  
private scheduleNotices : string = ""; 
private noticeHeadline : string = "";
private noticeWasRead : boolean = false; 
private selectedNoticeHeadline : string;

private lecturesToAppend : LectureSchedulePOJOList = new LectureSchedulePOJOList();
private fetchedLectures : LectureSchedulePOJOList = new LectureSchedulePOJOList();
 
constructor(private viewContainerRef: ViewContainerRef, 
             private scheduleManager: ScheduleManager,
                 private collisioncourseschedule: CollisionCourseScheduleService,
                     private coursescheduleService : CoursesScheduleService, 
                         private addNoticeService : AddNoticeService,
                             private deleteNoticeService :  DeleteNoticeService,
                             private readSpecificNoticeService : ReadSpecificNoticeService,
                               private readGeneralNoticeService : ReadGeneralNoticeService,
                                 private courseservice: CourseService, 
                                     private parallelCourseNameFilter :ParallelCourseNameFilterPipe,
                                         public  scheduleData : ScheduleData) { }

ngOnInit(): void {

 this. coursescheduleService .getCourseSchedule().subscribe(courseSchedule => {
    
     this.notices = []
     this.coursesSchedule = []
     this.scheduleManager.lecturesList  = new LectureSchedulePOJOList();  
     courseSchedule.coursesSchedulePOJO.forEach(course =>{this.coursesSchedule.push(course)})
    
 }) 
}


removeCurrentSchedule(){

  
 this.scheduleManager.remove()
 this.scheduleManager.lecturesList  = new LectureSchedulePOJOList();  
}

checkSchedule(){

 let collisionCheckRequestParameter  = new CollisionCheckRequestParameter();

  
 collisionCheckRequestParameter.collisionCheck.courseDegree = this.selectedCourseDegreeValue;
 collisionCheckRequestParameter.collisionCheck.courseName =  this.selectedCourseNameValue;
 collisionCheckRequestParameter.collisionCheck.courseTerm = this.selectedCourseTermValue;

 this.scheduleManager.lecturesList.forEach(item =>  {
     
     collisionCheckRequestParameter.collisionCheck.lecturesList.push(item) 
 } )

 this.collisioncourseschedule.checkCollisions(collisionCheckRequestParameter ).subscribe( collisionSolutions=> {

     let scheduledLectures : LinkedList<ScheduledLecturePOJO> = collisionSolutions.scheduledLectures

     this.scheduleManager.lecturesList.forEach(lecture => {

         scheduledLectures.forEach(scheduledLecture => {

                 if(lecture.isEqual(scheduledLecture) ){

                     lecture.updateInformation(scheduledLecture)
                 }
         })
     }) 

     this.updateNotices(collisionSolutions.correctionMessages);

 });  

}

private updateNotices( correctionMessages : LinkedList<CorrectionPOJO>){

 correctionMessages.forEach(message => {

     this.scheduleNotices = this.scheduleNotices + "" + message.correctionMessage;
 })

 updateNoticeInformation("",  this.scheduleNotices.toString());
}


readNotice(){

 this.noticeWasRead = true; 
 let noticeRequestParameter = new  NoticeRequestParameter ();

 noticeRequestParameter.notice.noticeHeadline = this.selectedNoticeHeadline;

 // we must read the values via JS; cause of performance issues with the schedule table .. 

 this.readSpecificNoticeService .handleNoticeRequest(noticeRequestParameter).subscribe(receivedNotices => {
     updateNoticeInformation(receivedNotices.noticeHeadline ,  receivedNotices.notice);

    let scheduledLectures : LinkedList<ScheduledLecturePOJO> = new LectureSchedulePOJOList();  
     
    receivedNotices.scheduledLectures.forEach(lecture =>  scheduledLectures.add(lecture))

    this.scheduleManager.lecturesList.forEach(lecture => {

        scheduledLectures.forEach(scheduledLecture => {

                if(lecture.isEqual(scheduledLecture) ){

                    lecture.updateInformation(scheduledLecture)
                }
        })
    })  
 }); 
}


deleteNotice(){

 let noticeRequestParameter = new  NoticeRequestParameter ();

 noticeRequestParameter.notice.noticeHeadline = this.selectedNoticeHeadline;

 // we must read the values via JS; cause of performance issues with the schedule table .. 

 this.deleteNoticeService .handleNoticeRequest(noticeRequestParameter).subscribe(receivedNotices => {
       
     updateNoticeInformation("",  "");
     this.notices = [];

     receivedNotices.noticesStatus.forEach(notice => {

         this.notices.push(notice.noticeStatus)
     })

 }); 
}


addNotice(){

 let noticeRequestParameter = new  NoticeRequestParameter ();
  
 // we must read the values via JS; cause of performance issues with the schedule table .. 

 noticeRequestParameter.notice.noticeHeadline = (document.getElementById("noticeHeadline") as HTMLInputElement).value  == "" ? "Notizen vom " + new Date().toLocaleString() : (document.getElementById("noticeHeadline") as HTMLInputElement).value ; 

 // if the client enters some blank, we have to remove it afterwards
 noticeRequestParameter.notice.noticeHeadline =  noticeRequestParameter.notice.noticeHeadline.trim()

 noticeRequestParameter.notice.notice =  (document.getElementById("noticeMessage") as HTMLTextAreaElement).value;

 this.scheduleManager.lecturesList.forEach(item =>  {
     
     noticeRequestParameter.notice.scheduledLectures.push(item) 
 })
  
 this.addNoticeService.handleNoticeRequest(noticeRequestParameter).subscribe(receivedNotices => {
       
     this.notices = [];

     receivedNotices.noticesStatus.forEach(notice => {

         this.notices.push(notice.noticeStatus)
     })

 }); 
}


buildSchedule(){

 let coursesRequestParameter = new CoursesRequestParameter ();

 coursesRequestParameter.course.courseDegree = this.selectedCourseDegreeValue;
 coursesRequestParameter.course.courseName =  this.selectedCourseNameValue;
 coursesRequestParameter.course.courseTerm = this.selectedCourseTermValue;
 
 this.scheduleManager.lecturesList  = new LectureSchedulePOJOList();  
 
 this.courseservice.getCourses(coursesRequestParameter).subscribe(courses => {
     
     this.scheduleManager.course = this.selectedCourseNameValue
     
     this.scheduleManager.degree = this.selectedCourseDegreeValue
 
     this.scheduleManager.parallelCourse = this.parallelCourseNameFilter.transform(   this.coursesSchedule, this.selectedCourseNameValue)[0];

     this.scheduleManager.semester = this.selectedCourseTermValue

     this.scheduleManager.lecturesList =  this.scheduleManager.buildLectures(courses);
  
     this.scheduleManager.setRootViewContainerRef(this.viewContainerRef);

     this.scheduleManager.addDynamicComponent();    
  
 });

 let noticeRequestParameter = new  NoticeRequestParameter ();

 this.readGeneralNoticeService.handleNoticeRequest(noticeRequestParameter).subscribe(receivedNotices => {
       
     this.notices = [];

     receivedNotices.noticesStatus.forEach(notice => {

         this.notices.push(notice.noticeStatus)
     })
 }); 
}


buildParallelSchedule(value: any){

 if(this.scheduleData.choisesDict.getValue(this.scheduleData.choices[value])){

     let coursesRequestParameter = new CoursesRequestParameter ();

     coursesRequestParameter.course.courseDegree = this.selectedCourseDegreeValue;
     coursesRequestParameter.course.courseName = this.parallelCourseNameFilter.transform(   this.coursesSchedule, this.selectedCourseNameValue)[0];
     coursesRequestParameter.course.courseTerm = this.selectedCourseTermValue;

     if(this.lecturesToAppend.size() === 0){

         this.courseservice.getCourses(coursesRequestParameter).subscribe(courses => {

             this.scheduleManager.remove();
             this.scheduleManager.degree = this.selectedCourseDegreeValue
             this.fetchedLectures  = this.scheduleManager.getLectures();
                
             this.lecturesToAppend  = LectureSchedulePOJOList.diffLectureSchedulePOJOList( this.scheduleManager.buildLectures(courses), 
                                                                                   this.fetchedLectures);  
         
             this.scheduleManager.lecturesList = new LectureSchedulePOJOList() 
             this.scheduleManager.lecturesList.addAll(this.fetchedLectures); 
             this.scheduleManager.lecturesList.addAll(this.lecturesToAppend); 
            
             this.scheduleManager.setRootViewContainerRef(this.viewContainerRef);
             this.scheduleManager.addDynamicComponent();  

         });
     }
     else{
         this.fetchedLectures =  LectureSchedulePOJOList.diffLectureSchedulePOJOList(  this.scheduleManager.lecturesList , 
             this.lecturesToAppend);  

             this.scheduleManager.lecturesList = new LectureSchedulePOJOList()
             this.scheduleManager.remove(); 
             this.scheduleManager.degree = this.selectedCourseDegreeValue
             this.scheduleManager.lecturesList.addAll(this.fetchedLectures); 
             this.scheduleManager.lecturesList.addAll(this.lecturesToAppend); 
             this.scheduleManager.setRootViewContainerRef(this.viewContainerRef);
             this.scheduleManager.addDynamicComponent();  

     }
 }
 else{

     this.fetchedLectures =  LectureSchedulePOJOList.diffLectureSchedulePOJOList(  this.scheduleManager.getLectures() , 
                                                                                      this.lecturesToAppend);  

   
     this.lecturesToAppend =  LectureSchedulePOJOList.diffLectureSchedulePOJOList(  this.scheduleManager.getLectures() , 
                                                                           this.fetchedLectures);


     this.scheduleManager.lecturesList = new LectureSchedulePOJOList() 
     this.scheduleManager.remove();  
     this.scheduleManager.lecturesList.addAll(this.fetchedLectures);
     this.scheduleManager.degree = this.selectedCourseDegreeValue
     this.scheduleManager.setRootViewContainerRef(this.viewContainerRef);
     this.scheduleManager.addDynamicComponent();  

 } 
}
}


