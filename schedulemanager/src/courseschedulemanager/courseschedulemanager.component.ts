import { Component , 
           Input, OnInit ,ViewContainerRef} from '@angular/core';  
import {CourseService} from "@frontendutilities/src/services/REST/course.service"; 
import {CoursesRequestParameter} from "@frontendutilities/src/services/entities/Parameter/coursesrequestparameter";  
import {CoursePOJO} from "@frontendutilities/src/services/entities/REST/scheduling/coursepojo"; 
import {ScheduleManager} from "./courseschedule/schedulemanager.component";  
import {ParallelCourseNameFilterPipe} from "@frontendutilities/src/pipes/ParallelCourseNameFilterPipe"; 
import {ScheduleData} from "@frontendutilities/src/services/Data/schedule.data.services";
import {LectureSchedulePOJOList} from "@frontendutilities/src/utils/lists/LectureSchedulePOJOList"; 
import {CollisionCheckRequestParameter} from "@frontendutilities/src/services/entities/Parameter/collisioncheckrequestparameter"; 
import {CoursesScheduleService} from "@frontendutilities/src/services/REST/coursesschedule.service"; 
import {CollisionCourseScheduleService} from "@frontendutilities/src/services/REST/collisioncourseschedule.service";  
import { LinkedList } from "typescript-collections";
import {CourseSchedulePOJO} from "@frontendutilities/src/services/entities/REST/scheduling/courseschedulepojo";
import {ScheduledLecturePOJO } from "@frontendutilities/src/services/entities/REST/scheduling/scheduledlecturepojo"; 
import { NoticeRequestParameter } from "@frontendutilities/src/services/entities/Parameter/noticerequestparameter"; 
import {CorrectionPOJO} from "@frontendutilities/src/services/entities/REST/scheduling/correctionpojo";
import {NoticeService} from "@frontendutilities/src/services/REST/notice.service"; 


@Component({
    selector: 'courseschedulemanager-root',
    templateUrl: './courseschedulemanager.component.html',
    providers: [CoursesScheduleService, 
                    CollisionCourseScheduleService,
                          CourseService, 
                             NoticeService,
                                ScheduleData,
                                   ScheduleManager , 
                                       ParallelCourseNameFilterPipe ]
  //  styleUrls: ['./selectbox.component.css']
  })
   
 

export class CourseScheduleManager implements OnInit  {
  
    @Input() coursesSchedule:  CourseSchedulePOJO[];
    @Input() courses: CoursePOJO ; 

    private selectedCourseNameValue: string; 
    private selectedCourseDegreeValue: string; 
    private selectedCourseTermValue: string;  
    private scheduleNotices : string = ""; 
    private noticeHeadline : string = "";

    private lecturesToAppend : LectureSchedulePOJOList = new LectureSchedulePOJOList();
    private fetchedLectures : LectureSchedulePOJOList = new LectureSchedulePOJOList();

    constructor(private viewContainerRef: ViewContainerRef, 
                    private scheduleManager: ScheduleManager,
                        private collisioncourseschedule: CollisionCourseScheduleService,
                            private coursescheduleService : CoursesScheduleService, 
                                private noticeService : NoticeService,
                                private courseservice: CourseService, 
                                    private parallelCourseNameFilter :ParallelCourseNameFilterPipe,
                                        public  scheduleData :ScheduleData ){ }
     
    ngOnInit(): void {
     
        this. coursescheduleService .getCourseSchedule().subscribe(courseSchedule => {
           
            this.coursesSchedule = []
            this.noticeHeadline = "Notizen vom " + new Date().toLocaleString();
            courseSchedule.coursesSchedulePOJO.forEach(course =>{this.coursesSchedule.push(course)})
           
        })
    }

    removeCurrentSchedule(){

        this.scheduleManager.lecturesList = null;
        this.scheduleManager.remove()
    }
    
    checkSchedule(){

        let collisionCheckRequestParameter  = new CollisionCheckRequestParameter();
 
        collisionCheckRequestParameter.courseDegree = this.selectedCourseDegreeValue;
        collisionCheckRequestParameter.courseName =  this.selectedCourseNameValue;
        collisionCheckRequestParameter.courseTerm = this.selectedCourseTermValue;
  
        this.scheduleManager.lecturesList.forEach(item =>  {
            
            collisionCheckRequestParameter.lecturesList.push(item) 
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
    }


    addNotice(){
 
        let noticeRequestParameter = new  NoticeRequestParameter ();

        noticeRequestParameter.noticeHeadline = this.noticeHeadline; 

        noticeRequestParameter.notice = this.scheduleNotices;

        this.scheduleManager.lecturesList.forEach(item =>  {
            
            noticeRequestParameter.scheduledLectures.push(item) 
        })
        
        
        this.noticeService.addNotice(noticeRequestParameter).subscribe(notices => {
               
        
        });
 
    }


    buildSchedule(){
  
        let coursesRequestParameter = new CoursesRequestParameter ();
 
        coursesRequestParameter.courseDegree = this.selectedCourseDegreeValue;
        coursesRequestParameter.courseName =  this.selectedCourseNameValue;
        coursesRequestParameter.courseTerm = this.selectedCourseTermValue;
 
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
    }
 

    buildParallelSchedule(value: any){
 
        if(this.scheduleData.choisesDict.getValue(this.scheduleData.choices[value])){
 
            let coursesRequestParameter = new CoursesRequestParameter ();
 
            coursesRequestParameter.courseDegree = this.selectedCourseDegreeValue;
            coursesRequestParameter.courseName = this.parallelCourseNameFilter.transform(   this.coursesSchedule, this.selectedCourseNameValue)[0];
            coursesRequestParameter.courseTerm = this.selectedCourseTermValue;
  
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



