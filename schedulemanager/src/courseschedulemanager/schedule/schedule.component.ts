import { Component , HostListener, ElementRef} from '@angular/core'; 
import {ScheduleData} from "@frontendutilities/src/services/Data/schedule.data.services"; 
import * as html2canvas from 'html2canvas';
import * as FileSaver from "file-saver";  
import { LectureSchedulePOJOList } from '@frontendutilities/src/utils/lists/LectureSchedulePOJOList';

declare function initUndoSetter();
declare function getUNDO();
declare function setUNDO(undoFlag : boolean);
declare function getREDO();
declare function setREDO(undoFlag : boolean);




@Component({
    selector: 'schedule-root',
    templateUrl: './schedule.component.html', 
    host:{
      '(document:keypress)': "handleRessetting($event)"  
    }
  })
     
export class Schedule   { 
   
  private UNDO = false; 

  constructor(public scheduleData: ScheduleData, private elementRef:ElementRef){
      initUndoSetter(); 
  } 

     
  handleRessetting(event : KeyboardEvent){
 
  
    // Ctrl + z was triggered 
    if(getUNDO() === true){
   
      if( this.scheduleData.lecturesListStack.size() !== 0){

         // the lecturesList, will reveive the top of the last lectures Lists 
         this.scheduleData.lecturesList = new LectureSchedulePOJOList()
 
         this.scheduleData.lecturesListStack.undo().forEach(item => this.scheduleData.lecturesList.add(item))

      }
          
      setUNDO(false)
    }
   
  }
  
  createScreenshot(){

    html2canvas(document.querySelector("#schedule")).then(canvas => {

      let converted = canvas as HTMLCanvasElement;

      converted.toBlob((blob) => {

        FileSaver.saveAs(blob , "ScheduleScreenshot.png")
      })

    });
  }
 
} 
 