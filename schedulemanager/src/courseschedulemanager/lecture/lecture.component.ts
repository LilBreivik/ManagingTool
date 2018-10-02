import { Component, Input, Output, AfterViewChecked ,   AfterContentChecked,
             EventEmitter,
              HostBinding,  } from '@angular/core';
import {LectureSchedulePOJO} from "@frontendutilities/src/services/entities/REST/scheduling/lectureschedulepojo"; 
import {LectureSchedulePOJOList} from "@frontendutilities/src/utils/lists/LectureSchedulePOJOList"; 
import * as moment from 'moment';
import 'moment-duration-format';
import {DateRange} from 'moment-range';
import { ChangeDetectorRef } from '@angular/core';
import {ScheduleData} from "@frontendutilities/src/services/Data/schedule.data.services"; 
import { ScheduleManager } from '../courseschedule/schedulemanager.component';

declare function adjustDimensions();
 
@Component({
  selector: 'lecture-component',
 
  template: `<div  droppable (onDrop)="onLectureDrop($event)"    class="ex4 list-group-item" >
                <div *ngIf="lectureProperties"> 
                      <div *ngFor="let lecture of selectLecturesObject().toArray()">
                           <div *ngIf="setLecture(lecture)">   </div>
                           <div (dblclick)="getLectureInformation()"  style="position: absolute" 
                           [style.font-size] = "fontSize" [style.height.px] = "height" 
                              [style.background-color] = "color"  [style.opacity] = "opacity"  draggable  
                                       [style.width.px] = "width"  [dragData]="lecture" class="list-group-item"> 
                                          {{lecture.lectureNameShortcut}} </div>
                      
                     </div>   
                </div>
            </div>`,

    styles: [`.ex4 { 
      
      overflow: visible;   
  } `] 
})

                
export class Lecture implements  AfterViewChecked ,AfterContentChecked {

  public droppedItems :  LectureSchedulePOJOList;

  public draggedItems :  LectureSchedulePOJOList;

  public element : string;

  @Input() selectedLectures:  LectureSchedulePOJOList;
  @Input() semester: string;
  @Input() parallelSemester: string;

  @Input() course: string; 
  @Input() parallelCourse : string;  

  @Input() day : string; 
  @Input() time : string;  
  @Input() practiceChoice : boolean;
  @Input() lectureProperties: LectureSchedulePOJOList;
  @Input() lectureProperty: LectureSchedulePOJO; 
 

  @Output() delete:EventEmitter<Lecture> = new EventEmitter();

  @HostBinding('style.height.px') height: Number;
  @HostBinding('style.width.px') width: Number;
  @HostBinding('style.background-color') color: string;
  @HostBinding('style.opacity') opacity: Number;
  @HostBinding('style.font-size') fontSize: string;
 

  public color12 : string;
  public height12 : number;
  public lecturePOJO : LectureSchedulePOJO;

  constructor(private cdRef:ChangeDetectorRef , 
                  private scheduleData : ScheduleData, 
                      private scheduleManager: ScheduleManager){

    this.droppedItems = new LectureSchedulePOJOList();
    this.draggedItems = new LectureSchedulePOJOList();
    this.lecturePOJO =  new LectureSchedulePOJO();
  }


  ngAfterViewChecked()
  {
    // catch error ... cause of the changed sizes of th ecomponents
    this.cdRef.detectChanges();
  }

  ngAfterContentChecked(){

    // catch error ... cause of the changed sizes of th ecomponents
    this.cdRef.detectChanges();
  }

  public setLecture(lecture : LectureSchedulePOJO){
  
      if(!(lecture.lectureName === "not defined")){
         
        let start = moment.duration(lecture.startTime); 
 
        let end = moment.duration( lecture.endTime); 
    
        let diff = end.subtract(start );
      
        let minutes  = moment.duration(moment.duration(diff)).format("mm");
  
        this.opacity = 1
        
        var dim = adjustDimensions();
        
        this.height = ( ((parseInt(minutes, 10) / 15) ) *  (  parseInt(dim.height)) ); 
  
        this.width = dim.width * 0.9

        this.color = this.intToRGB(this.hashCode(lecture.lectureName));

        this.fontSize = "1vw";

        this.updateLectureInformation(lecture);

        this.selectedLectures.forEach(selectedLecture => {
   
          let test = new DateRange( moment(lecture.startTime, "HH:mm"),  moment(lecture.endTime, "HH:mm"));

          let test2 = new DateRange( moment(selectedLecture.startTime, "HH:mm"),  moment(selectedLecture.endTime, "HH:mm"));
 
              if(  (lecture.day == selectedLecture.day) &&   test2.overlaps(test) && (! selectedLecture.isEqual(lecture)))

              {    
                  this.color = "#ff0000";
              }
        });
        
      } 
      else{ 

        this.opacity = 0
        this.color = "";
        this.height = 20;
      }
      
  }
 
   
  public getLectureInformation(){
  
     alert(  this.lecturePOJO.lectureName + " findet statt am " + this.lecturePOJO.day + "\r\n" + 
      "zwischen : " +  this.lecturePOJO.startTime + " und " + this.lecturePOJO.endTime + "\r\n" +  "\r\n" + 
      "Das Fach wird gehört im " + this.lecturePOJO.semesterNo + "\r\n" + "Im Studiengang " + JSON.stringify(this.lecturePOJO.courses));
  
  }

  hashCode(str) { // java String#hashCode
    var hash = 0;
    for (var i = 0; i < str.length; i++) {
       hash = str.charCodeAt(i) + ((hash << 5) - hash);
    }
    return hash;
  } 

  intToRGB(i){
    var c = (i & 0x00FFFFFF)
        .toString(16)
        .toUpperCase();

    return "#" + ("00000".substring(0, 6 - c.length) + c);
  }
 
  public selectLecturesObject(){

    this. selectedLectures = new LectureSchedulePOJOList();
   
    this.lectureProperties.forEach(lecture => {
    
          if((lecture.day == this.day) && 
                (lecture.startTime == this.time) &&  
                     (( lecture.semesterNo.indexOf(this.semester) > -1) || 
                     ( lecture.semesterNo.indexOf(this.parallelSemester) > -1) )
                    )
          {   
              
            if(true === this.practiceChoice){
  
              this.updateLectureInformation(lecture);
              this.selectedLectures.add(lecture); 
            }

            else if ( lecture.isPractice === this.practiceChoice) {
 
              this.updateLectureInformation(lecture);
              this.selectedLectures.add(lecture); 
            }
             
          }
    });

    if(this.selectedLectures.size() === 0){

     // console.log("triggered");
      let pojo = new LectureSchedulePOJO();
      pojo.day = this.day;
      pojo.startTime = "23:59";
      pojo.endTime = "00:00";
      pojo.lectureName = "not defined"; 
      this.selectedLectures.add(pojo);
    } ;
   
    
    return this.selectedLectures; 
}

  public onLectureDrop(e: any) {
    // Get the dropped data here 
    

    this.scheduleData.lecturesListStack.pushList(this.scheduleData.lecturesList)

    let obj = JSON.parse( JSON.stringify(e ) ).dragData;
   
    let start = moment.duration(obj.startTime); 
    let end = moment.duration(obj.endTime); 

    let diff = end.subtract(start );
   
    let newStart = moment.duration(this.time).format("HH:mm");
    let newEnd = moment.duration(moment.duration(this.time).add(diff)).format("HH:mm")
  
    let newLecturePOJO =  new LectureSchedulePOJO();
 
    newLecturePOJO.lectureId = obj.lectureId;
    newLecturePOJO.day = this.day;
    newLecturePOJO.endTime = newEnd ;
    newLecturePOJO.startTime = newStart ;
    newLecturePOJO.lectureName = obj.lectureName;
    newLecturePOJO.semesterNo  = obj.semesterNo;
    newLecturePOJO.courses = obj.courses;
    newLecturePOJO.lectureNameShortcut = obj.lectureNameShortcut;
    newLecturePOJO.isPractice = obj.isPractice ;
    newLecturePOJO.degree = obj.degree ;

    
    this.updateLectureInformation(newLecturePOJO);
       
    this.lectureProperties.add(newLecturePOJO);

    obj.startTime = newStart
    obj.endTime = newEnd
    obj.day = this.day
 
    this.lectureProperties.removeElement(obj) 
  
  }
  

  updateLectureInformation(lectureInformationSource){
    
    this.lecturePOJO = lectureInformationSource
 
  }
 
}
