import {ComponentFactoryResolver, Injectable, 
    Inject, ReflectiveInjector,OnInit, Component } from '@angular/core'
 
import {Schedule} from "../schedule/schedule.component";  
import {LectureSchedulePOJO} from "@frontendutilities/src/services/entities/REST/scheduling/lectureschedulepojo";   
import {CoursePOJO} from "@frontendutilities/src/services/entities/REST/scheduling/coursepojo"; 
import {LectureSchedulePOJOList} from "@frontendutilities/src/utils/lists/LectureSchedulePOJOList"; 

@Component({
    selector: 'courseschedulemanager-root',
    templateUrl: './courseschedule.component.html' 
  })
   
  
@Injectable()
export class ScheduleManager  {
  
    private rootViewContainer = null; 

    private component : any; 

    public course : string; 

    public degree : string;

    public parallelCourse: string;

    public semester : string; 

    public parallelSemester : string; 

    public lecturesList : LectureSchedulePOJOList;  

    constructor( private factoryResolver: ComponentFactoryResolver){

        this.factoryResolver = factoryResolver;
    }
     
    public getLectures(){

        let currentlecturesList  :LectureSchedulePOJOList = new LectureSchedulePOJOList()

        this.lecturesList.forEach(lecture => currentlecturesList  .add(lecture))

        return currentlecturesList ;
    }

    setRootViewContainerRef(viewContainerRef) {
        this.rootViewContainer = viewContainerRef
    }

    addDynamicComponent() {

        // put information in Schedule here !!!!!
       
        const factory = this.factoryResolver  .resolveComponentFactory(Schedule)
       
        this.component = factory  .create(this.rootViewContainer.parentInjector)
         
        this.component.instance.scheduleData.lecturesList = this.lecturesList;

        this.component.instance.scheduleData.course = this.course;
 
        this.component.instance.scheduleData.parallelCourse = this.parallelCourse;

        this.component.instance.scheduleData.parallelSemester =  this.parallelSemester;

        this.component.instance.scheduleData.semester = this.semester;
  
        this.rootViewContainer.insert(this.component.hostView) 
    }
   
    remove(){

        this.rootViewContainer.remove();
    }
    getDynamicComponent() {

        // put information in Schedule here !!!!!
        return  this.lecturesList ; 
    }


    buildLectures(course: CoursePOJO){
   
        course.semesters.forEach (semester => {
  
            let lectures = semester.lecturesInSemester;
         
            try{

                lectures.forEach(lecture =>{
                     
                    lecture 
                       .timingsForALecture.forEach(timing => 
                           {     
                               timing.vTimings.forEach(time => {
                                        
                                        let pojo = new LectureSchedulePOJO();
 
                                        pojo.lectureId = LectureSchedulePOJO.makeid(); 
                                        pojo.semesterNo[0] = semester.semesterNr;
                                        pojo.courses[0] =   course.courseName;
                                        pojo.lectureName = lecture.lectureName
                                        pojo.startTime = time.startTime;
                                        pojo.endTime = time.endTime;
                                        pojo.isPractice = lecture.practice;
                                        pojo.day = timing.day; 
                                        pojo.degree = this.degree;
                                  
                                        pojo.lectureNameShortcut = lecture.lectureNameShortcut
                                
                                        this.lecturesList.addUniquely(pojo);
                                      
                                    }) 
                                });
 
                });
 
            }
            catch(e){
               
               //"semster is missing not defined ... ;
            }
 
        });
   
       
        return this.lecturesList;
    }
}

